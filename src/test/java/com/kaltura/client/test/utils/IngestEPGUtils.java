package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.MediaAsset;
import com.kaltura.client.utils.response.base.Response;
import io.restassured.RestAssured;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.anonymousKs;
import static io.restassured.path.xml.XmlPath.from;
import static org.awaitility.Awaitility.await;

public class IngestEPGUtils extends BaseUtils {
    // TODO: think about ENUMS if we really need it here? should we create new ENUM class for it? where?
    public static final String DURATION_PERIOD_DAYS = "days";
    public static final String DURATION_PERIOD_HOURS = "hours";
    public static final String DURATION_PERIOD_MINUTES = "minutes";
    public static final String DURATION_PERIOD_SECONDS = "seconds";

    // default values
    public static final int DEFAULT_COUNT_OF_SEASONES = 1;
    public static final int DEFAULT_COUNT_OF_PROGRAMMES = 2;
    public static final int DEFAULT_PROGRAM_DURATION = 30;
    public static final String DEFAULT_PROGRAM_DURATION_PERIOD_NAME = DURATION_PERIOD_MINUTES;

    private static List<String> durationPeriodNames = new ArrayList<>();
    static {
        durationPeriodNames.add(DURATION_PERIOD_DAYS);
        durationPeriodNames.add(DURATION_PERIOD_HOURS);
        durationPeriodNames.add(DURATION_PERIOD_MINUTES);
        durationPeriodNames.add(DURATION_PERIOD_SECONDS);
    }

    // ingest new EPG (Programmes) // TODO: complete one-by-one needed fields to cover util ingest_epg from old project
    public static MediaAsset ingestEPG(String epgChannelName, Optional<Integer> programCount, Optional<String> firstProgramStartDate,
                                       Optional<Integer> programDuration, Optional<String> programDurationPeriodName,
                                       Optional<Boolean> isCridUnique4AllPrograms, Optional<Integer> seasonCount,
                                       Optional<String> coguid, Optional<String> crid, Optional<String> seriesId) {

        int programCountValue = programCount.orElse(DEFAULT_COUNT_OF_PROGRAMMES);
        if (programCountValue <= 0) {
            Logger.getLogger(IngestEPGUtils.class).error("Invalid programCount value " + programCount.get() +
                    ". Should be bigger than 0");
            return null;
        }
        int seasonCountValue = seasonCount.orElse(DEFAULT_COUNT_OF_SEASONES);

        String datePattern = "MM/yy/dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String firstProgramStartDateValue = firstProgramStartDate.orElse(getCurrentDataInFormat(datePattern));
        Calendar startDate = Calendar.getInstance();
        try {
            startDate.setTime(dateFormat.parse(firstProgramStartDateValue));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int programDurationValue = programDuration.orElse(DEFAULT_PROGRAM_DURATION);
        if (programDurationValue <= 0) {
            Logger.getLogger(IngestEPGUtils.class).error("Invalid programDuration value " + programDuration.get() +
                    ". Should be bigger than 0");
            return null;
        }
        String programDurationPeriodNameValue = programDurationPeriodName.orElse(DEFAULT_PROGRAM_DURATION_PERIOD_NAME);
        if (!durationPeriodNames.contains(programDurationPeriodNameValue)) {
            Logger.getLogger(IngestEPGUtils.class).error("Invalid programDurationPeriodName value " + programDurationPeriodName.get() +
                    ". Should be one from " + durationPeriodNames);
            return null;
        }
        boolean isCridUnique4AllProgramsValue = isCridUnique4AllPrograms.orElse(true);

        String coguidValue = coguid.orElseGet(() -> getCurrentDataInFormat("yyMMddHHmmssSS"));
        String cridValue = crid.orElse(coguidValue);
        String seriesIdValue = seriesId.orElse(coguidValue);
        int seasonId = 1;
        Date endDate;
        String output = "";
        String oneProgrammOutput = "";
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = Calendar.getInstance().getTime();
        while (seasonId <= seasonCountValue) {
            int idx = 1;
            while(idx <= programCountValue) {
                endDate = loadEndDate(startDate.getTime(), programDurationValue, programDurationPeriodNameValue);
                oneProgrammOutput = getProgrammeXML(idx, df2.format(startDate.getTime()), df2.format(endDate),
                        epgChannelName, coguidValue, cridValue, "Program", df2.format(now),
                        seriesIdValue, String.valueOf(seasonId), isCridUnique4AllProgramsValue);
                startDate.setTime(endDate);
                output = output + oneProgrammOutput;
                idx = idx + 1;
            }
            seasonId = seasonId + 1;
        }
        String epgChannelIngestXml = getChannelXML(PARTNER_ID, epgChannelName, output);

        String url = SOAP_BASE_URL + "/Ingest_" + API_URL_VERSION + "/Service.svc?wsdl";
        HashMap headermap = new HashMap<>();
        headermap.put("Content-Type", "text/xml;charset=UTF-8");
        headermap.put("SOAPAction", "\"http://tempuri.org/IService/IngestKalturaEpg\"");
        String reqBody = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <IngestKalturaEpg xmlns=\"http://tempuri.org/\">" +
                "           <request xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "           <userName>" + getProperty(INGEST_USER_NAME) + "</userName><passWord>" + getProperty(INGEST_USER_PASSWORD) + "</passWord><data xmlns=\"\">" +
                                epgChannelIngestXml + "\n" +
                "           </data>\n" +
                "           </request>\n" +
                "           </IngestKalturaEpg>\n" +
                "   </soapenv:Body>\n" +
                "</s:Envelope>";
        io.restassured.response.Response resp = RestAssured.given()
                .log().all()
                .headers(headermap)
                .body(reqBody)
                .post(url);
        System.out.println("RESPONSE: " + resp.asString());
        /*String id = from(resp.asString()).get("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.AssetsStatus.IngestAssetStatus.InternalAssetId").toString();

        MediaAsset mediaAsset = new MediaAsset();
        //mediaAsset.setName(nameValue);
        mediaAsset.setId(Long.valueOf(id));
        mediaAsset.setDescription(descriptionValue);
        //mediaAsset.setStartDate(startDate);
        //mediaAsset.setEndDate(endDate);

        await().pollInterval(3, TimeUnit.SECONDS).atMost(30, TimeUnit.SECONDS).until(isDataReturned(id));
        Response<Asset> mediaAssetDetails = AssetServiceImpl.get(anonymousKs, id, AssetReferenceType.MEDIA);
        mediaAsset.setMediaFiles(mediaAssetDetails.results.getMediaFiles());
        return mediaAsset;*/
        return null;
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

    private static Date loadEndDate(Date startDate, int durationValue, String periodName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        switch (periodName) {
            case DURATION_PERIOD_DAYS:
                calendar.add(Calendar.DATE, durationValue);
                break;
            case DURATION_PERIOD_HOURS:
                calendar.add(Calendar.HOUR, durationValue);
                break;
            case DURATION_PERIOD_MINUTES:
                calendar.add(Calendar.MINUTE, durationValue);
                break;
            case DURATION_PERIOD_SECONDS:
                calendar.add(Calendar.SECOND, durationValue);
        }
        return calendar.getTime();
    }

    private static Callable<Boolean> isDataReturned(String mediaId) {
        return () -> AssetServiceImpl.get(anonymousKs, mediaId, AssetReferenceType.MEDIA).error == null;
    }
}
