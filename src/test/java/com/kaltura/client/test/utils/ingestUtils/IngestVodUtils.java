package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.MediaAsset;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Verify.verify;
import static com.kaltura.client.services.AssetService.GetAssetBuilder;
import static com.kaltura.client.services.AssetService.get;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static com.kaltura.client.test.utils.BaseUtils.getOffsetDateInFormat;
import static com.kaltura.client.test.utils.XmlUtils.asList;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class IngestVodUtils extends BaseIngestUtils {
    private static final String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";
    private static final String ingestDataResultPath = "Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.";
    private static final String ingestStatusMessagePath = ingestDataResultPath + "IngestStatus.Message";
    private static final String ingestAssetIdPath = ingestDataResultPath + "AssetsStatus.IngestAssetStatus.InternalAssetId";


    @Accessors(fluent = true)
    @Data
    public static class VodData {
        @Setter(AccessLevel.NONE) private String coguid;

        private boolean isActive = true;
        private boolean isVirtual = false;

//      @Setter(AccessLevel.NONE)
        private String name;
        private String description;
        private String thumbUrl;
        private String catalogStartDate;
        private String catalogEndDate;
        private String startDate;
        private String endDate;
        private String mediaType;
        private String ppvWebName;
        private String ppvMobileName;
        private String geoBlockRule;

        private Map<String, List<String>> tags;
        private Map<String, String> strings;
        private Map<String, String> dates;
        private Map<String, Integer> numbers;
    }

    /** IMPORTANT: In order to update or delete existing asset use asset.getName() as "coguid" **/

    public static MediaAsset insertVod(VodData vodData) {
        final String coguidDatePattern = "yyMMddHHmmssSS";
        final String datePattern = "dd/MM/yyyy hh:mm:ss";
        final String offsetDateValue = getOffsetDateInFormat(-1, datePattern);
        final String endDateValue = "14/10/2099 17:00:00";
        final String ppvModuleName = "Shai_Regression_PPV"; // TODO: update on any generated value

        vodData.coguid = getCurrentDateInFormat(coguidDatePattern);

        if (vodData.name == null) { vodData.name = vodData.coguid; }
        if (vodData.description == null) { vodData.description = "description of " + vodData.coguid; }
        if (vodData.thumbUrl == null) { vodData.thumbUrl = DEFAULT_THUMB; }
        if (vodData.catalogStartDate == null) { vodData.catalogStartDate = offsetDateValue; }
        if (vodData.catalogEndDate == null) { vodData.catalogEndDate = endDateValue; }
        if (vodData.startDate == null) { vodData.startDate = offsetDateValue; }
        if (vodData.endDate == null) { vodData.endDate = endDateValue; }
        if (vodData.mediaType == null) { vodData.mediaType = MOVIE_MEDIA_TYPE; }
        if (vodData.ppvWebName == null) { vodData.ppvWebName = ppvModuleName; }
        if (vodData.ppvMobileName == null) { vodData.ppvMobileName = ppvModuleName; }
        if (vodData.tags == null) { vodData.tags = getDefaultTags(); }
        if (vodData.strings == null) { vodData.strings = getDefaultStrings(); }
        if (vodData.dates == null) { vodData.dates = getDefaultDates(); }
        if (vodData.numbers == null) { vodData.numbers = getDefaultNumbers(); }

        String reqBody = buildIngestVodXml(vodData, INGEST_ACTION_INSERT);

        Response resp = executeIngestVodRequest(reqBody);
        String id = from(resp.asString()).get(ingestAssetIdPath).toString();

        GetAssetBuilder getAssetBuilder = get(id, AssetReferenceType.MEDIA).setKs(getAnonymousKs());
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> (executor.executeSync(getAssetBuilder).error == null));

        Asset asset = executor.executeSync(getAssetBuilder).results;
        verify(asset.getId().toString().equals(id));
        return (MediaAsset) asset;
    }

    public static MediaAsset updateVod(String coguid, VodData vodData) {
        vodData.coguid = coguid;
        String reqBody = buildIngestVodXml(vodData, INGEST_ACTION_UPDATE);

        Response resp = executeIngestVodRequest(reqBody);
        String id = from(resp.asString()).get(ingestAssetIdPath).toString();

        GetAssetBuilder getAssetBuilder = get(id, AssetReferenceType.MEDIA).setKs(getAnonymousKs());
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> (executor.executeSync(getAssetBuilder).error == null));

        Asset asset = executor.executeSync(getAssetBuilder).results;
        verify(asset.getId().toString().equals(id));
        return (MediaAsset) asset;
    }

    public static void deleteVod(String coguid) {
        VodData vodData = new VodData();
        vodData.coguid = coguid;
        String reqBody = buildIngestVodXml(vodData, INGEST_ACTION_DELETE);

        Response resp = executeIngestVodRequest(reqBody);
        assertThat(from(resp.asString()).getInt(ingestAssetIdPath)).isEqualTo(0);
    }

    // private methods
    private static Response executeIngestVodRequest(String reqBody) {
        Response resp = given()
                .header(contentTypeXml)
                .header(soapActionIngestTvinciData)
                .body(reqBody)
                .when()
                .post(url);

        Logger.getLogger(IngestVodUtils.class).debug(reqBody + "\n");
        Logger.getLogger(IngestVodUtils.class).debug(resp.asString());

        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");

        return resp;
    }

    private static String buildIngestVodXml(VodData vodData, String action) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestVOD.xml");

        // user and password
        if (vodData.isVirtual()) {
            doc.getElementsByTagName("userName").item(0).setTextContent(getIngestVirualAssetUserName());
            doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestVirualAssetUserPassword());
        } else {
            doc.getElementsByTagName("userName").item(0).setTextContent(getIngestAssetUserName());
            doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestAssetUserPassword());
        }

//        // add CDATA section
//        CDATASection cdata = doc.createCDATASection("");
//        doc.getElementsByTagName("tem:request").item(0).appendChild(cdata);

        // media
        Element media = (Element) doc.getElementsByTagName("media").item(0);
        media.setAttribute("co_guid", vodData.coguid());
        media.setAttribute("entry_id", "entry_" + vodData.coguid());
        media.setAttribute("action", action);
        media.setAttribute("is_active", Boolean.toString(vodData.isActive()));

        if (action.equals(INGEST_ACTION_DELETE)) {
            return uncommentCdataSection(docToString(doc));
        }

        // name
        Element nameElement = (Element) media.getElementsByTagName("name").item(0);
        nameElement.getElementsByTagName("value").item(0).setTextContent(vodData.name());

        // thumb
        Element thumb = (Element) media.getElementsByTagName("thumb").item(0);
        thumb.setAttribute("url", vodData.thumbUrl());

        // description
        Element descriptionElement = (Element) media.getElementsByTagName("description").item(0);
        descriptionElement.getElementsByTagName("value").item(0).setTextContent(vodData.description());

        // dates
        Element datesElement = (Element) media.getElementsByTagName("dates").item(0);
        datesElement.getElementsByTagName("catalog_start").item(0).setTextContent(vodData.catalogStartDate());
        datesElement.getElementsByTagName("start").item(0).setTextContent(vodData.startDate());
        datesElement.getElementsByTagName("catalog_end").item(0).setTextContent(vodData.catalogEndDate());
        datesElement.getElementsByTagName("end").item(0).setTextContent(vodData.endDate());

        // pic_ratios
        Element picRatios = (Element) media.getElementsByTagName("pic_ratios").item(0);
        for (Node n : asList(picRatios.getElementsByTagName("ratio"))) {
            Element e = (Element) n;
            e.setAttribute("thumb", vodData.thumbUrl());
        }

        // media type
        media.getElementsByTagName("media_type").item(0).setTextContent(vodData.mediaType());

        // geo block rule
        media.getElementsByTagName("geo_block_rule").item(0).setTextContent(vodData.geoBlockRule());

        // strings
        if (vodData.strings() != null) {
            Element stringsElement = (Element) media.getElementsByTagName("strings").item(0);
            for (Map.Entry<String, String> entry : vodData.strings().entrySet()) {
                // meta node
                Element meta = generateAndAppendMetaNode(doc, stringsElement, entry.getKey());

                // value node
                Element value = doc.createElement("value");
                value.setAttribute("lang", "eng");
                value.setTextContent(entry.getValue());
                meta.appendChild(value);
            }
        }

        // doubles
        if (vodData.numbers() != null) {
            Element doublesElement = (Element) media.getElementsByTagName("doubles").item(0);
            for (Map.Entry<String, Integer> entry : vodData.numbers().entrySet()) {
                // meta node
                Element meta = generateAndAppendMetaNode(doc, doublesElement, entry.getKey());
                meta.setTextContent(String.valueOf(entry.getValue()));
            }
        }

        // dates
        if (vodData.dates() != null) {
            Element datesMetaElement = (Element) media.getElementsByTagName("dates").item(1);
            for (Map.Entry<String, String> entry : vodData.dates().entrySet()) {
                // meta node
                Element metaElement = generateAndAppendMetaNode(doc, datesMetaElement, entry.getKey());
                metaElement.setTextContent(entry.getValue());
            }
        }

        // metas
        if (vodData.tags() != null) {
            Element metasElement = (Element) media.getElementsByTagName("metas").item(0);
            for (Map.Entry<String, List<String>> entry : vodData.tags().entrySet()) {
                // meta node
                Element metaElement = generateAndAppendMetaNode(doc, metasElement, entry.getKey());
                if (entry.getValue() != null) {
                    // container node
                    for (String s : entry.getValue()) {
                        Element container = doc.createElement("container");
                        metaElement.appendChild(container);

                        // value node
                        Element value = doc.createElement("value");
                        value.setTextContent(s);
                        value.setAttribute("lang", "eng");
                        container.appendChild(value);
                    }
                }
            }
        }

        // file types
        Element file1 = (Element) media.getElementsByTagName("file").item(0);
        file1.setAttribute("type", getProperty(WEB_FILE_TYPE));
        file1.setAttribute("co_guid", "web_" + vodData.coguid());
        file1.setAttribute("PPV_MODULE", vodData.ppvWebName());

        Element file2 = (Element) media.getElementsByTagName("file").item(1);
        file2.setAttribute("type", getProperty(MOBILE_FILE_TYPE));
        file2.setAttribute("co_guid", "ipad_" + vodData.coguid());
        file2.setAttribute("PPV_MODULE", vodData.ppvMobileName());

        // uncomment cdata
        String docAsString = docToString(doc);

        return uncommentCdataSection(docAsString);
    }

    private static Element generateAndAppendMetaNode(Document doc, Element rootElement, String name) {
        // meta node
        Element meta = doc.createElement("meta");
        meta.setAttribute("name", name);
        meta.setAttribute("ml_handling", "unique");
        rootElement.appendChild(meta);

        return meta;
    }

    // TODO: these values should be get in another way than now
    private static Map<String, List<String>> getDefaultTags() {
        Map<String, List<String>> tags = new HashMap<>();

        List<String> tagValues = new ArrayList<>();
        tagValues.add("Costa Rica;Israel");
        tags.put("Country", tagValues);

        tagValues = new ArrayList<>();
        tagValues.add("GIH");
        tagValues.add("ABC");
        tagValues.add("DEF");
        tags.put("Genre", tagValues);

        tagValues = new ArrayList<>();
        tagValues.add("Shay_Series");
        tags.put("Series name", tagValues);

        tagValues = new ArrayList<>();
        tagValues.add("KSQL channel_573349");
        tags.put("Free", tagValues);

        tagValues = new ArrayList<>();
        tags.put("Parental Rating", tagValues);

        return tags;
    }

    // TODO: these values should be get in another way than now
    private static Map<String, String> getDefaultStrings() {
        Map<String, String> strings = new HashMap<>();
        strings.put("Synopsis", "syno pino sister");
        strings.put("meta_name", "meta_value");

        return strings;
    }

    // TODO: these values should be get in another way than now
    private static Map<String, Integer> getDefaultNumbers() {
        Map<String, Integer> doubles = new HashMap<>();
        doubles.put("Release year", 1900);

        return doubles;
    }

    // TODO: these values should be get in another way than now
    private static Map<String, String> getDefaultDates() {
        Map<String, String> dates = new HashMap<>();
        dates.put("Life cycle start date", "23/03/2017 12:34:56");

        return dates;
    }
}
