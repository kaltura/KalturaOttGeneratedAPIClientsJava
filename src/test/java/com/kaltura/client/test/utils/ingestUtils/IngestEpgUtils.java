package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.enums.DurationPeriod;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.ProgramAsset;
import com.kaltura.client.types.SearchAssetFilter;
import com.kaltura.client.utils.response.base.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;

public class IngestEpgUtils extends BaseIngestUtils {

    // INGEST EPG PARAMS
    static final int EPG_DEFAULT_COUNT_OF_SEASONS = 2;
    static final int EPG_DEFAULT_COUNT_OF_PROGRAMMES = 2;
    static final int EPG_DEFAULT_PROGRAM_DURATION = 30;


    // ingest new EPG (Programmes)
    public static List<ProgramAsset> ingstEPG(String epgChannelName, Optional<Integer> programCount, Optional<Calendar> startDate,
                                               Optional<Integer> programDuration, Optional<DurationPeriod> programDurationPeriod,
                                               Optional<Boolean> isCridUnique4AllPrograms, Optional<Integer> seasonCount,
                                               Optional<String> coguid, Optional<String> crid, Optional<String> seriesId,
                                               Optional<String> thumb, Optional<String> programNamePrefix) {

        // TODO: complete one-by-one needed fields to cover util ingest_epg from old project
        int programCountValue = programCount.orElse(EPG_DEFAULT_COUNT_OF_PROGRAMMES);
        int seasonCountValue = seasonCount.orElse(EPG_DEFAULT_COUNT_OF_SEASONS);
        Calendar startDateValue = startDate.orElse(Calendar.getInstance());
        int programDurationValue = programDuration.orElse(EPG_DEFAULT_PROGRAM_DURATION);
        DurationPeriod programDurationPeriodValue = programDurationPeriod.orElse(DurationPeriod.MINUTES);
        boolean isCridUnique4AllProgramsValue = isCridUnique4AllPrograms.orElse(true);
        String coguidValue = coguid.orElseGet(() -> getCurrentDateInFormat("yyMMddHHmmssSS"));
        String cridValue = crid.orElse(coguidValue);
        String seriesIdValue = seriesId.orElse(coguidValue);
        String thumbValue = thumb.orElse(DEFAULT_THUMB);
        String programNamePrefixValue = programNamePrefix.orElse("Program");

        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody1 = IngestEpgUtils.buildIngestEpgXml(epgChannelName, programCountValue, startDateValue, programDurationValue,
                programDurationPeriodValue, isCridUnique4AllProgramsValue, seasonCountValue, coguidValue, cridValue, seriesIdValue,
                thumbValue, programNamePrefixValue);

        io.restassured.response.Response resp =
                given()
                    .header(contentTypeXml)
                    .header(soapActionIngestKalturaEpg)
                    .body(reqBody1)
                .when()
                    .post(url);

        Logger.getLogger(IngestEpgUtils.class).debug(reqBody1);
        Logger.getLogger(IngestEpgUtils.class).debug(resp.asString());

        // TODO: 6/20/2018 add response assertion

        int epgChannelId = IngestFixtureData.getEpgChannelId(epgChannelName);
        // TODO: create method getting epoch value from String and pattern
        Date firstProgramStartDateAsDate = startDateValue.getTime();
        long epoch = firstProgramStartDateAsDate.getTime() / 1000; // 1000 milliseconds in 1 second

        String firstProgramStartDateEpoch = String.valueOf(epoch);

        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setOrderBy(AssetOrderBy.START_DATE_ASC.getValue());
        assetFilter.setKSql("(and epg_channel_id='" + epgChannelId + "' start_date >= '" + firstProgramStartDateEpoch + "' Series_ID='" + seriesIdValue + "' end_date >= '" + firstProgramStartDateEpoch + "')");

        int delayBetweenRetriesInSeconds = 5;
        int maxTimeExpectingValidResponseInSeconds = 120;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(isDataReturned(getAnonymousKs(), assetFilter, programCountValue * seasonCountValue));

        Response<ListResponse<Asset>> ingestedProgrammes = executor.executeSync(
                AssetService.list(assetFilter, null).setKs(getAnonymousKs()));

        // TODO: complete Asset.json at least for programs

        return (List<ProgramAsset>) (Object) ingestedProgrammes.results.getObjects();
    }

    private static Callable<Boolean> isDataReturned(String ks, SearchAssetFilter assetFilter, int totalCount) {
        return () -> {
            AssetService.ListAssetBuilder listAssetBuilder = AssetService.list(assetFilter, null).setKs(ks);
            return executor.executeSync(listAssetBuilder).error == null &&
                    executor.executeSync(listAssetBuilder).results.getTotalCount() == totalCount;
        };
    }

    public static List<ProgramAsset> ingestEPG(String epgChannelName, Integer programCount) {
        Optional o = Optional.empty();
        return ingstEPG(epgChannelName, Optional.of(programCount), o, o, o, o, o, o, o, o, o, o);
    }

    public static List<ProgramAsset> ingestEPG(String epgChannelName) {
        Optional o = Optional.empty();
        return ingstEPG(epgChannelName, o, o, o, o, o, o, o, o, o, o, o);
    }

    private static String buildIngestEpgXml(String epgChannelName, int episodesNum, Calendar startDate, int programDuration,
                                    DurationPeriod programDurationPeriod, boolean isCridUnique4AllPrograms, int seasonsNum,
                                    String coguid, String crid, String seriesId, String thumb, String programNamePrefix) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestEPG.xml");

        // user and password
        doc.getElementsByTagName("userName").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // EpgChannels
        Element epgChannels = (Element) doc.getElementsByTagName("EpgChannels").item(0);
        epgChannels.setAttribute("parent-group-id", String.valueOf(partnerId));
        epgChannels.setAttribute("group-id", String.valueOf(partnerId + 1));

        // channel
        Element channel = (Element) epgChannels.getElementsByTagName("channel").item(0);
        channel.setAttribute("id", epgChannelName);

        // programme
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        int seasonNum = 1;
        while (seasonNum <= seasonsNum) {
            int episodeNum = 1;
            while (episodeNum <= episodesNum) {
                Date endDate = loadEndDate(startDate.getTime(), programDuration, programDurationPeriod);
                String startDateFormatted = df.format(startDate.getTime());
                String endDateFormatted = df.format(endDate.getTime());
                String nowDateFormatted = df.format(Calendar.getInstance().getTime());

                Element programmeNode = getProgrammeNode(doc, episodeNum, startDateFormatted, endDateFormatted, epgChannelName,
                        coguid, crid, programNamePrefix, nowDateFormatted, seriesId, seasonNum, isCridUnique4AllPrograms, thumb);
                epgChannels.appendChild(programmeNode);

                startDate.setTime(endDate);
                episodeNum++;
            }
            seasonNum++;
        }

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }

    private static Element getProgrammeNode(Document doc, int episodeNumber, String startDate, String endDate, String channel, String coguid, String crid,
                                            String programNamePrefix, String currentDate, String seriesId, int seasonNumber, boolean isCridUnique4AllPrograms,
                                            String thumb) {
        String name = programNamePrefix + "_" + startDate + "_ser" + seriesId + "_seas" + seasonNumber + "_e" + episodeNumber;
        String cridValue = isCridUnique4AllPrograms ? crid + "_" + seasonNumber + "_" + episodeNumber : crid + "_" + episodeNumber;

        // programme
        Element programme = doc.createElement("programme");
        programme.setAttribute("start", startDate);
        programme.setAttribute("stop", endDate);
        programme.setAttribute("channel", channel);
        programme.setAttribute("external_id", coguid + "_" + seasonNumber + "_" + episodeNumber);

        // title
        Element title = doc.createElement("title");
        title.setAttribute("lang", "eng");
        title.setTextContent(name);
        programme.appendChild(title);

        // crid
        Element cridElement = doc.createElement("crid");
        cridElement.setTextContent(cridValue);
        programme.appendChild(cridElement);

        // desc
        Element desc = doc.createElement("desc");
        desc.setAttribute("lang", "eng");
        desc.setTextContent(startDate + " until " + endDate);
        programme.appendChild(desc);

        // date
        Element date = doc.createElement("date");
        date.setTextContent(currentDate);
        programme.appendChild(date);

        // language
        Element lang = doc.createElement("language");
        lang.setAttribute("lang", "eng");
        lang.setTextContent("eng");
        programme.appendChild(lang);

        // icon
        Element icon = doc.createElement("icon");
        icon.setAttribute("ratio", "16:9");
        icon.setAttribute("src", thumb);

        // season num meta
        programme.appendChild(generateMetasNode(doc, "season_num", String.valueOf(seasonNumber)));

        // series id meta
        programme.appendChild(generateMetasNode(doc, "series_id", seriesId));

        // episode num meta
        programme.appendChild(generateMetasNode(doc, "episode_num", String.valueOf(episodeNumber)));

        // TODO: 6/19/2018 add missing parameters according to needed tests

        return programme;
    }

    private static Element generateMetasNode(Document doc, String metaTypeString, String metaValuesString) {
        // metas node
        Element metas = doc.createElement("metas");

        // metaType
        Element metaType =  doc.createElement("MetaType");
        metaType.setTextContent(metaTypeString);
        metas.appendChild(metaType);

        // metaValues
        Element metaValues = doc.createElement("MetaValues");
        metaValues.setAttribute("lang", "eng");
        metaValues.setTextContent(metaValuesString);
        metas.appendChild(metaValues);

        return metas;
    }

    private static Element generateTagsNode(Document doc, String tagTypeString, String tagValuesString) {
        // tags node
        Element tags = doc.createElement("tags");

        // TagType
        Element tagType =  doc.createElement("TagType");
        tagType.setTextContent(tagTypeString);
        tags.appendChild(tagType);

        // TagValues
        Element tagValues = doc.createElement("TagValues");
        tagValues.setAttribute("lang", "eng");
        tagValues.setTextContent(tagValuesString);
        tags.appendChild(tagValues);

        return tags;
    }

    static Date loadEndDate(Date startDate, int durationValue, DurationPeriod durationPeriod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        switch (durationPeriod) {
            case DAYS:
                calendar.add(Calendar.DATE, durationValue);
                break;
            case HOURS:
                calendar.add(Calendar.HOUR, durationValue);
                break;
            case MINUTES:
                calendar.add(Calendar.MINUTE, durationValue);
                break;
            case SECONDS:
                calendar.add(Calendar.SECOND, durationValue);
        }
        return calendar.getTime();
    }
}
