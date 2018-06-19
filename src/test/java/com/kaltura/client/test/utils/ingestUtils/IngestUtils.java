package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.AssetService.ListAssetBuilder;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.ProgramAsset;
import com.kaltura.client.types.SearchAssetFilter;
import com.kaltura.client.utils.response.base.Response;
import io.restassured.RestAssured;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.*;
import static org.awaitility.Awaitility.await;

public class IngestUtils extends BaseUtils {

    // ingest new EPG (Programmes) // TODO: complete one-by-one needed fields to cover util ingest_epg from old project
    public static List<ProgramAsset> ingestEPG(String epgChannelName, Optional<Integer> programCount, Optional<String> firstProgramStartDate,
                                               Optional<Integer> programDuration, Optional<String> programDurationPeriodName,
                                               Optional<Boolean> isCridUnique4AllPrograms, Optional<Integer> seasonCount,
                                               Optional<String> coguid, Optional<String> crid, Optional<String> seriesId) {

        int programCountValue = programCount.orElse(EPG_DEFAULT_COUNT_OF_PROGRAMMES);
        if (programCountValue <= 0) {
            Logger.getLogger(IngestUtils.class).error("Invalid programCount value " + programCount.get() + ". Should be bigger than 0");
            return null;
        }

        int seasonCountValue = seasonCount.orElse(EPG_DEFAULT_COUNT_OF_SEASONS);

        String datePattern = "MM/yy/dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String firstProgramStartDateValue = firstProgramStartDate.orElse(getCurrentDateInFormat(datePattern));
        Calendar startDate = Calendar.getInstance();
        try {
            startDate.setTime(dateFormat.parse(firstProgramStartDateValue));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int programDurationValue = programDuration.orElse(EPG_DEFAULT_PROGRAM_DURATION);
        if (programDurationValue <= 0) {
            Logger.getLogger(IngestUtils.class).error("Invalid programDuration value " + programDuration.get() +
                    ". Should be bigger than 0");
            return null;
        }
        String programDurationPeriodNameValue = programDurationPeriodName.orElse(EPG_DEFAULT_PROGRAM_DURATION_PERIOD_NAME);
        if (!durationPeriodNames.contains(programDurationPeriodNameValue)) {
            Logger.getLogger(IngestUtils.class).error("Invalid programDurationPeriodName value " + programDurationPeriodName.get() +
                    ". Should be one from " + durationPeriodNames);
            return null;
        }
        boolean isCridUnique4AllProgramsValue = isCridUnique4AllPrograms.orElse(true);

        String coguidValue = coguid.orElseGet(() -> getCurrentDateInFormat("yyMMddHHmmssSS"));
        String cridValue = crid.orElse(coguidValue);
        String seriesIdValue = seriesId.orElse(coguidValue);
        int seasonId = 1;
        String output = "";
        String oneProgrammOutput = "";
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = Calendar.getInstance().getTime();
        while (seasonId <= seasonCountValue) {
            int idx = 1;
            while (idx <= programCountValue) {
                Date endDate = loadEndDate(startDate.getTime(), programDurationValue, programDurationPeriodNameValue);
                oneProgrammOutput = getProgrammeXML(idx, df2.format(startDate.getTime()), df2.format(endDate),
                        epgChannelName, coguidValue, cridValue, "Program", df2.format(now),
                        seriesIdValue, String.valueOf(seasonId), isCridUnique4AllProgramsValue);
                startDate.setTime(endDate);
                output = output + oneProgrammOutput;
                idx = idx + 1;
            }
            seasonId = seasonId + 1;
        }
        String epgChannelIngestXml = getChannelXML(partnerId, epgChannelName, output);

        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <s:Header/>\n" +
                "   <s:Body>\n" +
                "      <IngestKalturaEpg xmlns=\"http://tempuri.org/\">" +
                "           <request xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "           <userName xmlns=\"\">" + getIngestAssetUserName() + "</userName><passWord xmlns=\"\">" + getIngestAssetUserPassword() + "</passWord><data xmlns=\"\">" +
                epgChannelIngestXml + "\n" +
                "           </data>\n" +
                "           </request>\n" +
                "           </IngestKalturaEpg>\n" +
                "   </s:Body>\n" +
                "</s:Envelope>";

        io.restassured.response.Response resp = RestAssured
                .given()
                    .header("Content-Type", "text/xml;charset=UTF-8")
                    .header("SOAPAction", "http://tempuri.org/IService/IngestKalturaEpg")
                    .body(reqBody)
                .when()
                    .post(url);

        Logger.getLogger(IngestUtils.class).debug(reqBody);
        Logger.getLogger(IngestUtils.class).debug(resp.asString());

        int epgChannelId = IngestFixtureData.getEpgChannelId(epgChannelName);
        // TODO: create method getting epoch value from String and pattern
        long epoch = 0L;
        try {
            Date firstProgramStartDateAsDate = dateFormat.parse(firstProgramStartDateValue);
            epoch = firstProgramStartDateAsDate.getTime() / 1000; // 1000 milliseconds in 1 second
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String firstProgramStartDateEpoch = String.valueOf(epoch);

        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setOrderBy(AssetOrderBy.START_DATE_ASC.getValue());
        assetFilter.setKSql("(and epg_channel_id='" + epgChannelId + "' start_date >= '" + firstProgramStartDateEpoch + "' Series_ID='" + seriesIdValue + "' end_date >= '" + firstProgramStartDateEpoch + "')");

        int delayBetweenRetriesInSeconds = 3;
        int maxTimeExpectingValidResponseInSeconds = 90;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(isDataReturned(getAnonymousKs(), assetFilter, programCountValue * seasonCountValue));

        Response<ListResponse<Asset>> ingestedProgrammes = executor.executeSync(
                AssetService.list(assetFilter, null).setKs(getAnonymousKs()));

        // TODO: complete Asset.json at least for programs

        return (List<ProgramAsset>) (Object) ingestedProgrammes.results.getObjects();
    }

    public static List<ProgramAsset> ingestEPG(String epgChannelName, Integer programCount) {
        return ingestEPG(epgChannelName, Optional.of(programCount), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    private static Callable<Boolean> isDataReturned(String ks, SearchAssetFilter assetFilter, int totalCount) {
        return () -> {
            ListAssetBuilder listAssetBuilder = AssetService.list(assetFilter, null).setKs(ks);
            return executor.executeSync(listAssetBuilder).error == null &&
                    executor.executeSync(listAssetBuilder).results.getTotalCount() == totalCount;
        };
    }

    private static String getChannelXML(int partnerId, String epgChannelName, String programsXml) {
        return "<![CDATA[" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
                "<EpgChannels xmlns=\"http://tempuri.org/xmltv\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "parent-group-id=\"" + String.valueOf(partnerId) + "\" group-id=\"" + String.valueOf(partnerId + 1) + "\" main-lang=\"eng\" updater-id=\"587\">" +
                "<channel id=\"" + epgChannelName + "\"/>" +
                programsXml +
                "</EpgChannels>" +
                "]]>";
    }

    // generate XML for ingest of 1 program
    private static String getProgrammeXML(int idx, String startDate, String endDate, String channel, String coguid, String crid,
                                          String programNamePrefix, String currentDate, String seriesId, String seasonNumber,
                                          boolean isCridUnique4AllPrograms) {
        String name = programNamePrefix + "_" + startDate + "_ser" + seriesId + "_seas" + seasonNumber + "_e" + idx;

        // TODO: complete to cover util from old project completely
        String CRID = "<crid>" + crid + "_" + idx + "</crid>";
        if (isCridUnique4AllPrograms) {
            CRID = "<crid>" + crid + "_" + seasonNumber + "_" + idx + "</crid>";
        }
        String output =
                "<programme start=\"" + startDate + "\" stop=\"" + endDate + "\" channel=\"" + channel + "\" external_id=\"" + coguid + "_" + seasonNumber + "_" + idx + "\">" +
                        "<title lang=\"eng\">" + name + "</title>" +
                        CRID +
                        "<desc lang=\"eng\">" + startDate + " until " + endDate + "</desc>" +
                        "<date>" + currentDate + "</date>" +
                        "<language lang=\"eng\">eng</language>" +
                        //"<icon ratio=\"" + ratio + "\" src=\"" + thumb + "\"/>" +
                        //"<enable-cdvr>" + enableCDVR + "</enable-cdvr>" +
                        //"<enable-catch-up>" + enableCatchUp + "</enable-catch-up>"+
                        //"<enable-start-over>" + enableStartOver + "</enable-start-over>" +
                        //"<enable-trick-play>" + enableTrickPlay + "</enable-trick-play>" +
                        //"<metas>" +
                        //"<MetaType>" + metaName + "</MetaType>" +
                        //"<MetaValues lang=\"eng\">" + metaValue + "</MetaValues>" +
                        //"</metas>" +
                        "<metas>" +
                        // TODO: that meta should be added into property file
                        "<MetaType>season_num</MetaType>" +
                        "<MetaValues lang=\"eng\">" + seasonNumber + "</MetaValues>" +
                        "</metas>" +
                        "<metas>" +
                        // TODO: that meta should be added into property file
                        "<MetaType>Series_ID</MetaType>" +
                        "<MetaValues lang=\"eng\">" + seriesId + "</MetaValues>" +
                        "</metas>" +
                        "<metas>" +
                        // TODO: that meta should be added into property file
                        "<MetaType>Episode number</MetaType>" +
                        "<MetaValues lang=\"eng\">" + idx + "</MetaValues>" +
                        "</metas>" +
            /*			"<tags>" +
                            "<TagType>Season</TagType>" +
                            "<TagValues lang=\"eng\">" + seasonNumber + "</TagValues>" +
                        "</tags>" +

                        "<tags>" +
                            "<TagType>Episode</TagType>" +
                            "<TagValues lang=\"eng\">" + id + "</TagValues>" +
                        "</tags>" +
            */
                        //"<metas>" +
                        //"<MetaType>Country</MetaType>" +
                        //"<MetaValues lang=\"eng\">Israel</MetaValues>" +
                        //"</metas>" +
                        //"<metas>" +
                        //"<MetaType>YEAR</MetaType>" +
                        //"<MetaValues lang=\"eng\">2015</MetaValues>" +
                        //"</metas>" +
                        //"<tags>" +
                        //"<TagType>Genre</TagType>" +
                        //"<TagValues lang=\"eng\">" + genre + "</TagValues>" +
                        //"</tags>" +
                        //"<tags>" +
                        //"<TagType>Actors</TagType>" +
                        //"<TagValues lang=\"eng\">Shay</TagValues>" +
                        //"<TagValues lang=\"eng\">Ortal</TagValues>" +
                        //"</tags>" +
                        //"<tags>" +
                        //"<TagType>" + tagName + "</TagType>" +
                        //"<TagValues lang=\"eng\">" + tagValue + "</TagValues>" +
                        //"</tags>" +
                        //"<tags>" +
                        //"<TagType>" + parentalFieldName + "</TagType>" +
                        //"<TagValues lang=\"eng\">" + parentalValue + "</TagValues>" +
                        //"</tags>" +
                        //"<tags>" +
                        //"<TagType>" + parentalFieldName + "</TagType>" +
                        //"<TagValues lang=\"eng\">" + parentalValue2 + "</TagValues>" +
                        //"</tags>" +
                        "</programme>";
        return output;
    }
}