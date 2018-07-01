package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.tests.enums.DurationPeriod;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.ProgramAsset;
import com.kaltura.client.types.SearchAssetFilter;
import com.kaltura.client.utils.response.base.Response;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.AssetService.ListAssetBuilder;
import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class IngestEpgUtils extends BaseIngestUtils {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    private static Response<ListResponse<Asset>> assetListResponse;
    private static int epgChannelId;


    @Accessors(fluent = true)
    @Data
    public static class EpgData {
        @Setter(AccessLevel.NONE) private String coguid;
        @NonNull private String epgChannelName;

        private boolean isCridUnique4AllPrograms = true;

        private String crid;
        private String seriesId;
        private String thumb;
        private String programNamePrefix;

        private int episodesNum;
        private int seasonsNum;
        private int programDuration;

        private Calendar startDate;
        private DurationPeriod programDurationPeriod;
    }

    public static List<ProgramAsset> insertEpg(EpgData epgData) {
        final String coguidDatePattern = "yyMMddHHmmssSS";
        final int DEFAULT_SEASONS_COUNT = 2;
        final int DEFAULT_PROGRAMMES_COUNT = 2;
        final int DEFAULT_PROGRAM_DURATION = 30;

        // TODO: complete one-by-one needed fields to cover util ingest_epg from old project
        epgData.coguid = getCurrentDateInFormat(coguidDatePattern);

        if (epgData.crid == null) { epgData.crid = epgData.coguid; }
        if (epgData.seriesId == null) { epgData.seriesId = epgData.coguid; }
        if (epgData.episodesNum == 0) { epgData.episodesNum = DEFAULT_PROGRAMMES_COUNT; }
        if (epgData.seasonsNum == 0) { epgData.seasonsNum = DEFAULT_SEASONS_COUNT; }
        if (epgData.startDate == null) { epgData.startDate = Calendar.getInstance(); }
        if (epgData.programDuration == 0) { epgData.programDuration = DEFAULT_PROGRAM_DURATION; }
        if (epgData.programDurationPeriod == null) { epgData.programDurationPeriod = DurationPeriod.MINUTES; }
        if (epgData.thumb == null) { epgData.thumb = DEFAULT_THUMB; }
        if (epgData.programNamePrefix == null) { epgData.programNamePrefix = "Program"; }

        epgChannelId = IngestFixtureData.getEpgChannelId(epgData.epgChannelName);
        String reqBody = buildIngestEpgXml(epgData);
        executeIngestEpgRequest(reqBody);

        // TODO: create method getting epoch value from String and pattern
        String firstProgramStartDateEpoch = String.valueOf(epgData.startDate.getTime().getTime() / 1000);
        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setOrderBy(AssetOrderBy.START_DATE_ASC.getValue());
        // TODO: 6/29/2018 create ksql builder util
        assetFilter.setKSql("(and epg_channel_id='" + epgChannelId + "' start_date >= '" + firstProgramStartDateEpoch
                + "' Series_ID='" + epgData.seriesId + "' end_date >= '" + firstProgramStartDateEpoch + "')");

        ListAssetBuilder listAssetBuilder = list(assetFilter).setKs(getAnonymousKs());
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(isDataReturned(listAssetBuilder, epgData.seasonsNum * epgData.episodesNum));

        // TODO: complete Asset.json at least for programs
        return (List<ProgramAsset>) (Object) assetListResponse.results.getObjects();
    }

    // private methods
    private static Callable<Boolean> isDataReturned(ListAssetBuilder listAssetBuilder, int totalCount) {
        return () -> {
            assetListResponse = executor.executeSync(listAssetBuilder);
            return assetListResponse.error == null &&
                    assetListResponse.results.getTotalCount() == totalCount;
        };
    }

    private static io.restassured.response.Response executeIngestEpgRequest(String reqBody) {
        final String ingestDataResultPath = "Envelope.Body.IngestKalturaEpgResponse.IngestKalturaEpgResult.";
        final String ingestStatusMessagePath = ingestDataResultPath + "IngestStatus.Message";
        final String ingestInternalAssetIdPath = ingestDataResultPath + "AssetsStatus.IngestAssetStatus.InternalAssetId";

        io.restassured.response.Response resp = given()
                .header(contentTypeXml)
                .header(soapActionIngestKalturaEpg)
                .body(reqBody)
                .when()
                .post(url);

        Logger.getLogger(IngestVodUtils.class).debug(reqBody + "\n");
        Logger.getLogger(IngestVodUtils.class).debug(resp.asString());

        // response assertions
        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");
        assertThat(from(resp.asString()).getList(ingestInternalAssetIdPath)).containsOnly(String.valueOf(epgChannelId));

        return resp;
    }

    private static String buildIngestEpgXml(EpgData epgData) {
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
        channel.setAttribute("id", epgData.epgChannelName);

        // programme
        int seasonNum = 1;
        while (seasonNum <= epgData.seasonsNum) {
            int episodeNum = 1;
            while (episodeNum <= epgData.episodesNum) {
                Date endDate = loadEndDate(epgData.startDate.getTime(), epgData.programDuration, epgData.programDurationPeriod);
                String startDateFormatted = df.format(epgData.startDate.getTime());
                String endDateFormatted = df.format(endDate.getTime());

                Element programmeNode = getProgrammeNode(doc, episodeNum, seasonNum, startDateFormatted, endDateFormatted, epgData);
                epgChannels.appendChild(programmeNode);

                epgData.startDate.setTime(endDate);
                episodeNum++;
            }
            seasonNum++;
        }

        // uncomment cdata
        return uncommentCdataSection(docToString(doc));
    }

    private static Element getProgrammeNode(Document doc, int episodeNum, int seasonNum, String startDate, String endDate, EpgData epgData) {
        String name = epgData.programNamePrefix + "_" + startDate + "_ser" + epgData.seriesId + "_seas" + seasonNum + "_e" + episodeNum;
        String crid = epgData.isCridUnique4AllPrograms ? epgData.crid + "_" + seasonNum + "_" + episodeNum : epgData.crid + "_" + episodeNum;

        // programme
        Element programme = doc.createElement("programme");
        programme.setAttribute("start", startDate);
        programme.setAttribute("stop", endDate);
        programme.setAttribute("channel", epgData.epgChannelName);
        programme.setAttribute("external_id", epgData.coguid + "_" + seasonNum + "_" + episodeNum);

        // title
        Element title = doc.createElement("title");
        title.setAttribute("lang", "eng");
        title.setTextContent(name);
        programme.appendChild(title);

        // crid
        Element cridElement = doc.createElement("crid");
        cridElement.setTextContent(crid);
        programme.appendChild(cridElement);

        // desc
        Element desc = doc.createElement("desc");
        desc.setAttribute("lang", "eng");
        desc.setTextContent(startDate + " until " + endDate);
        programme.appendChild(desc);

        // date
        Element date = doc.createElement("date");
        date.setTextContent(df.format(Calendar.getInstance().getTime()));
        programme.appendChild(date);

        // language
        Element lang = doc.createElement("language");
        lang.setAttribute("lang", "eng");
        lang.setTextContent("eng");
        programme.appendChild(lang);

        // icon
        Element icon = doc.createElement("icon");
        icon.setAttribute("ratio", "16:9");
        icon.setAttribute("src", epgData.thumb);
        programme.appendChild(icon);

        // season num meta
        programme.appendChild(generateMetasNode(doc, "season_num", String.valueOf(seasonNum)));

        // series id meta
        programme.appendChild(generateMetasNode(doc, "series_id", epgData.seriesId));

        // episode num meta
        programme.appendChild(generateMetasNode(doc, "episode_num", String.valueOf(episodeNum)));

        // TODO: 6/19/2018 add missing parameters according to needed tests

        return programme;
    }

    private static Element generateMetasNode(Document doc, String metaTypeString, String metaValuesString) {
        // metas node
        Element metas = doc.createElement("metas");

        // metaType
        Element metaType = doc.createElement("MetaType");
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
        Element tagType = doc.createElement("TagType");
        tagType.setTextContent(tagTypeString);
        tags.appendChild(tagType);

        // TagValues
        Element tagValues = doc.createElement("TagValues");
        tagValues.setAttribute("lang", "eng");
        tagValues.setTextContent(tagValuesString);
        tags.appendChild(tagValues);

        return tags;
    }

    private static Date loadEndDate(Date startDate, int durationValue, DurationPeriod durationPeriod) {
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
