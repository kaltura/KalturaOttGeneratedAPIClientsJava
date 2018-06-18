package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.types.MediaAsset;
import io.restassured.RestAssured;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.IngestConstants.*;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.BaseTest.getAnonymousKs;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static com.kaltura.client.test.utils.BaseUtils.getOffsetDateInFormat;
import static com.kaltura.client.test.utils.XmlUtils.asList;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class IngestVodUtils extends BaseIngestUtils {

    /**
     * IMPORTANT: please delete inserted by that method items
     *
     * @param action           - can be "insert", "update" and "delete"
     * @param coguid           - should have value in case "action" one of {"update" and "delete"}
     * @param isActive
     * @param name
     * @param thumbUrl
     * @param description
     * @param catalogStartDate
     * @param catalogEndDate
     * @param startDate
     * @param endDate
     * @param mediaType
     * @param ppvWebName
     * @param ppvMobileName
     * @param tags
     * @param strings
     * @param numbers
     * @param dates
     * @return to update or delete existed VOD use corresponded action and value vod.getName() as "coguid"
     * (where vod is a variable that contains VOD data)
     * <p>
     * !!!Only created by that method VOD can be deleted/update!!!
     */
    // ingest new VOD (Media) // TODO: complete one-by-one needed fields to cover util ingest_vod from old project
    public static MediaAsset ingestVOD(Optional<String> action, Optional<String> coguid, boolean isActive, Optional<String> name, Optional<String> thumbUrl, Optional<String> description,
                                       Optional<String> catalogStartDate, Optional<String> catalogEndDate, Optional<String> startDate, Optional<String> endDate, Optional<String> mediaType,
                                       Optional<String> ppvWebName, Optional<String> ppvMobileName, Optional<Map<String, List<String>>> tags, Optional<Map<String, String>> strings,
                                       Optional<Map<String, Integer>> numbers, Optional<Map<String, String>> dates) {
        String startEndDatePattern = "dd/MM/yyyy hh:mm:ss";
        String coguidDatePattern = "yyMMddHHmmssSS";
        String maxEndDateValue = "14/10/2099 17:00:00";
        String ppvModuleName = "Shai_Regression_PPV"; // TODO: update on any generated value
        int defaultDayOffset = -1;

        String actionValue = action.orElse(INGEST_ACTION_INSERT);
        String coguidValue = coguid.orElse(getCurrentDateInFormat(coguidDatePattern));
        String nameValue = INGEST_ACTION_INSERT.equals(actionValue) ? coguidValue : name.orElse(coguidValue);
        String thumbUrlValue = thumbUrl.orElse(INGEST_VOD_DEFAULT_THUMB);
        String descriptionValue = description.orElse("description of " + coguidValue);
        String catalogStartDateValue = catalogStartDate.orElse(getOffsetDateInFormat(defaultDayOffset, startEndDatePattern));
        String catalogEndDateValue = catalogEndDate.orElse(maxEndDateValue);
        String startDateValue = startDate.orElse(getOffsetDateInFormat(defaultDayOffset, startEndDatePattern));
        String endDateValue = endDate.orElse(maxEndDateValue);
        String mediaTypeValue = mediaType.orElse(MOVIE_MEDIA_TYPE);
        String ppvWebNameValue = ppvWebName.orElse(ppvModuleName);
        String ppvMobileNameValue = ppvMobileName.orElse(ppvModuleName);
        Map<String, List<String>> tagsValue = tags.orElse(getDefaultTags());
        Map<String, String> stringsValue = strings.orElse(getDefaultStrings());
        Map<String, Integer> numbersValue = numbers.orElse(getDefaultNumbers());
        Map<String, String> datesValue = dates.orElse(getDefaultDates());
        // TODO: check if ingest url is the same for all ingest actions
        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody = buildIngestVodXml(actionValue, coguidValue, isActive, nameValue, thumbUrlValue, descriptionValue, catalogStartDateValue,
                catalogEndDateValue, startDateValue, endDateValue, mediaTypeValue, ppvWebNameValue, ppvMobileNameValue, tagsValue, stringsValue, numbersValue, datesValue);

        io.restassured.response.Response resp = RestAssured
                .given()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .header("SOAPAction", "http://tempuri.org/IService/IngestTvinciData")
                .body(reqBody)
                .when()
                .post(url);

//        Logger.getLogger(IngestUtils.class).debug(reqBody);
        Logger.getLogger(IngestUtils.class).debug("Ingest response: \n" + resp.asString());
        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).get("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.IngestStatus.Message").toString()).isEqualTo("OK");

        String id;
        if (INGEST_ACTION_INSERT.equals(actionValue)) {
            id = from(resp.asString()).get("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.AssetsStatus.IngestAssetStatus.InternalAssetId").toString();
        } else {
            id = from(resp.asString()).get("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.tvmID").toString();
        }

        MediaAsset mediaAsset = new MediaAsset();
        mediaAsset.setName(nameValue);
        mediaAsset.setId(Long.valueOf(id.trim()));
        mediaAsset.setDescription(descriptionValue);
        //mediaAsset.setStartDate(startDate);
        //mediaAsset.setEndDate(endDate);

        if (!INGEST_ACTION_DELETE.equals(actionValue)) {
            int delayBetweenRetriesInSeconds = 3;
            int maxTimeExpectingValidResponseInSeconds = 90;
            await()
                    .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                    .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                    .until(isDataReturned(getAnonymousKs(), id, actionValue));

            mediaAsset.setMediaFiles(executor.executeSync(
                    AssetService.get(id, AssetReferenceType.MEDIA).setKs(getAnonymousKs())).results.getMediaFiles());
        }

        // TODO: 4/15/2018 add log for ingest and index failures
        return mediaAsset;
    }

    private static String buildIngestVodXml(String action, String coguid, boolean isActive, String name, String thumbUrl, String description, String catalogStartDate, String catalogEndDate,
                                           String startDate, String endDate, String mediaType, String ppvWebName, String ppvMobileName, Map<String, List<String>> tags, Map<String, String> strings,
                                           Map<String, Integer> numbers, Map<String, String> dates)  {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestVOD.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("userName").item(0).setTextContent(getIngestAssetUserName());
        doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestAssetUserPassword());

//        // add CDATA section
//        CDATASection cdata = doc.createCDATASection("");
//        doc.getElementsByTagName("tem:request").item(0).appendChild(cdata);

        // media
        Element media = (Element) doc.getElementsByTagName("media").item(0);
        media.setAttribute("co_guid", coguid);
        media.setAttribute("entry_id", "entry_" + coguid);
        media.setAttribute("action", action);
        media.setAttribute("is_active", Boolean.toString(isActive));

        // name
        Element nameElement = (Element) media.getElementsByTagName("name").item(0);
        nameElement.getElementsByTagName("value").item(0).setTextContent(name);

        // thumb
        Element thumb = (Element) media.getElementsByTagName("thumb").item(0);
        thumb.setAttribute("url", thumbUrl);

        // description
        Element descriptionElement = (Element) media.getElementsByTagName("description").item(0);
        descriptionElement.getElementsByTagName("value").item(0).setTextContent(description);

        // dates
        Element datesElement = (Element) media.getElementsByTagName("dates").item(0);
        datesElement.getElementsByTagName("catalog_start").item(0).setTextContent(catalogStartDate);
        datesElement.getElementsByTagName("start").item(0).setTextContent(startDate);
        datesElement.getElementsByTagName("catalog_end").item(0).setTextContent(catalogEndDate);
        datesElement.getElementsByTagName("end").item(0).setTextContent(endDate);

        // pic_ratios
        Element picRatios = (Element) media.getElementsByTagName("pic_ratios").item(0);
        for (Node n : asList(picRatios.getElementsByTagName("ratio"))) {
            Element e = (Element) n;
            e.setAttribute("thumb", thumbUrl);
        }

        // media type
        media.getElementsByTagName("media_type").item(0).setTextContent(mediaType);

        // strings
        Element stringsElement = (Element) media.getElementsByTagName("strings").item(0);
        for (Map.Entry<String, String> entry : strings.entrySet()) {
            // meta node
            Element meta = generateAndAppendMetaNode(doc, stringsElement, entry.getKey());

            // value node
            Element value = doc.createElement("value");
            value.setAttribute("lang", "eng");
            value.setTextContent(entry.getValue());
            meta.appendChild(value);
        }

        // doubles
        Element doublesElement = (Element) media.getElementsByTagName("doubles").item(0);
        for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
            // meta node
            Element meta = generateAndAppendMetaNode(doc, doublesElement, entry.getKey());
            meta.setTextContent(String.valueOf(entry.getValue()));
        }

        // dates
        Element datesMetaElement = (Element) media.getElementsByTagName("dates").item(1);
        for (Map.Entry<String, String> entry : dates.entrySet()) {
            // meta node
            Element metaElement = generateAndAppendMetaNode(doc, datesMetaElement, entry.getKey());
            metaElement.setTextContent(entry.getValue());
        }

        // metas
        Element metasElement = (Element) media.getElementsByTagName("metas").item(0);
        for (Map.Entry<String, List<String>> entry : tags.entrySet()) {
            // meta node
            generateAndAppendMetaNode(doc, metasElement, entry.getKey());
            if (entry.getValue() != null) {
                // container node
                for (String s : entry.getValue()) {
                    Element container = doc.createElement("container");
                    metasElement.appendChild(container);

                    // value node
                    Element value = doc.createElement("value");
                    value.setTextContent(s);
                    value.setAttribute("lang", "eng");
                    container.appendChild(value);
                }
            }
        }

        // file types
        Element file1 = (Element) media.getElementsByTagName("file").item(0);
        file1.setAttribute("type", getProperty(WEB_FILE_TYPE));
        file1.setAttribute("co_guid", "web_" + coguid);
        file1.setAttribute("PPV_MODULE", ppvWebName);

        Element file2 = (Element) media.getElementsByTagName("file").item(1);
        file2.setAttribute("type", getProperty(MOBILE_FILE_TYPE));
        file2.setAttribute("co_guid", "ipad_" + coguid);
        file2.setAttribute("PPV_MODULE", ppvMobileName);

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
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

    private static Callable<Boolean> isDataReturned(String ks, String mediaId, String action) {
        AssetService.GetAssetBuilder getAssetBuilder = AssetService.get(mediaId, AssetReferenceType.MEDIA).setKs(ks);
        if (INGEST_ACTION_DELETE.equals(action)) {
            return () -> (executor.executeSync(getAssetBuilder).error != null);
        } else {
            return () -> (executor.executeSync(getAssetBuilder).error == null);
        }
    }

    // Provide only media type (mandatory) and media name (Optional - if not provided will generate a name)
    public static MediaAsset ingestVOD(String mediaType, Map<String, List<String>> tags, String catalogStartDate) {
        MediaAsset mediaAsset = ingestVOD(Optional.empty(), Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(catalogStartDate), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(mediaType), Optional.empty(), Optional.empty(),
                Optional.of(tags), Optional.empty(), Optional.empty(), Optional.empty());

        return mediaAsset;
    }

    public static MediaAsset ingestVOD(String mediaType) {
        MediaAsset mediaAsset = ingestVOD(Optional.empty(), Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(mediaType), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

        return mediaAsset;
    }

    public static MediaAsset updateVODName(MediaAsset asset, String name) {
        MediaAsset mediaAsset = ingestVOD(Optional.of(INGEST_ACTION_UPDATE), Optional.of(asset.getName()), true, Optional.of(name), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

        return mediaAsset;
    }
}
