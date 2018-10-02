package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.test.tests.enums.IngestAction;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.MediaAsset;
import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Verify.verify;
import static com.kaltura.client.services.AssetService.GetAssetBuilder;
import static com.kaltura.client.services.AssetService.get;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.enums.IngestAction.*;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class IngestVodUtils extends BaseIngestUtils {

    // response paths
    private static final String ingestDataResultPath = "Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.";
    private static final String ingestAssetStatusPath = ingestDataResultPath + "AssetsStatus.IngestAssetStatus[0].";

    public static final String ingestStatusMessagePath = ingestDataResultPath + "IngestStatus.Message";
    public static final String ingestStatusPath = ingestDataResultPath + "status";
    public static final String ingestAssetStatusMessagePath = ingestAssetStatusPath + "Status.Message";
    public static final String ingestAssetStatusWarningMessagePath = ingestAssetStatusPath + "Warnings.Status.Message";
    public static final String ingestAssetIdPath = ingestAssetStatusPath + "InternalAssetId";

    private static final String datePattern = "dd/MM/yyyy hh:mm:ss";
    private static final String endDateValue = "14/10/2099 17:00:00";
    private static final String offsetDateValue = getOffsetDateInFormat(-1, datePattern);

    private static final List<String> ppvNames = DBUtils.getPpvNames(2);

    @Accessors(fluent = true)
    @Data
    public static class VodData {
        private boolean isActive = true;
        private boolean isVirtual = false;
        private boolean isErase = false;

        private String coguid;
        private String name;
        private String description;
        private String lang;
        private String thumbUrl;
        private String catalogStartDate;
        private String catalogEndDate;
        private String startDate;
        private String endDate;
        private String ppvWebName;
        private String ppvMobileName;
        private String geoBlockRule;

        private MediaType mediaType;

        private Map<String, List<String>> tags;
        private Map<String, String> stringsMeta;
        private Map<String, String> datesMeta;
        private Map<String, Double> numbersMeta;
        private Map<String, Boolean> booleansMeta;

        private Map<String, String> multilingualName;
        private Map<String, String> multilingualDescription;
        private Map<String, Map<String, String>> multilingualStringsMeta;
        private Map<String, List<Map<String, String>>> multilingualTags;

        private List<String> thumbRatios;
        private List<VodFile> files;

        private String customMediaType;

        public VodData setDefaultValues() {
            coguid = String.valueOf(getEpochInMillis());
            name = coguid;
            description = "description of " + coguid;
            lang = DEFAULT_LANGUAGE;
            thumbUrl = DEFAULT_THUMB;
            catalogStartDate = offsetDateValue;
            catalogEndDate = endDateValue;
            startDate = offsetDateValue;
            endDate = endDateValue;
            mediaType = MediaType.MOVIE;
            ppvWebName = ppvNames.get(0);
            ppvMobileName = ppvNames.get(1);
            files = getDefaultAssetFiles(ppvWebName, ppvMobileName);
            thumbRatios = Arrays.asList("4:3", "16:9");

            return this;
        }

        public VodData setDefaultTagsAndMetas() {
            tags = getDefaultTags();
            stringsMeta = getDefaultStrings();
            datesMeta = getDefaultDates();
            numbersMeta = getDefaultNumbers();

            return this;
        }

        // custom setters for multilingual fields to reset the parallel regular fields
        public VodData multilingualName(Map<String, String> multilingualName) {
            this.name = null;
            this.multilingualName = multilingualName;
            return this;
        }

        public VodData multilingualDescription(Map<String, String> multilingualDescription) {
            this.description = null;
            this.multilingualDescription = multilingualDescription;
            return this;
        }

        public VodData multilingualStringsMeta(Map<String, Map<String, String>> multilingualStringsMeta) {
            this.stringsMeta = null;
            this.multilingualStringsMeta = multilingualStringsMeta;
            return this;
        }

        public VodData multilingualTags(Map<String, List<Map<String, String>>> multilingualTags) {
            this.tags = null;
            this.multilingualTags = multilingualTags;
            return this;
        }
    }

    @Accessors(fluent = true)
    @Getter
    public static class VodFile {
        private String quality;
        private String handling_type;
        private String cdn_name;
        private String cdn_code;
        private String alt_cdn_code;
        private String billing_type;
        private String product_code;

        @Setter
        private String coguid;
        @Setter
        private String assetDuration;
        @Setter
        private String type;
        @Setter
        private String ppvModule;

        public VodFile(String type, String ppvModule) {
            quality = "HIGH";
            handling_type = "CLIP";
            cdn_name = "Default CDN";
            cdn_code = "http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m";
            alt_cdn_code = "http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m";
            billing_type = "Tvinci";
            product_code = "productExampleCode";
            assetDuration = "1000";

            coguid = "file_" + getEpoch() + "_" + getRandomLong();
            this.type = type;
            this.ppvModule = ppvModule;
        }
    }

    /**
     * IMPORTANT: In order to update or delete existing asset use asset.getName() as "coguid"
     **/
    public static MediaAsset insertVod(VodData vodData, boolean useDefaultValues) {
        if (vodData.coguid == null) {
            vodData.coguid = String.valueOf(getEpochInMillis());
        }

        if (useDefaultValues) {
            if (vodData.name == null) {
                vodData.name = vodData.coguid;
            }
            if (vodData.description == null) {
                vodData.description = "description of " + vodData.coguid;
            }
            if (vodData.lang == null) {
                vodData.lang = DEFAULT_LANGUAGE;
            }
            if (vodData.thumbUrl == null) {
                vodData.thumbUrl = DEFAULT_THUMB;
            }
            if (vodData.catalogStartDate == null) {
                vodData.catalogStartDate = offsetDateValue;
            }
            if (vodData.catalogEndDate == null) {
                vodData.catalogEndDate = endDateValue;
            }
            if (vodData.startDate == null) {
                vodData.startDate = offsetDateValue;
            }
            if (vodData.endDate == null) {
                vodData.endDate = endDateValue;
            }
            if (vodData.mediaType == null) {
                vodData.mediaType = MediaType.MOVIE;
            }
            if (vodData.tags == null) {
                vodData.tags = getDefaultTags();
            }
            if (vodData.stringsMeta == null) {
                vodData.stringsMeta = getDefaultStrings();
            }
            if (vodData.datesMeta == null) {
                vodData.datesMeta = getDefaultDates();
            }
            if (vodData.numbersMeta == null) {
                vodData.numbersMeta = getDefaultNumbers();
            }
            if (vodData.ppvWebName == null) {
                vodData.ppvWebName = ppvNames.get(0);
            }
            if (vodData.ppvMobileName == null) {
                vodData.ppvMobileName = ppvNames.get(1);
            }
            if (vodData.files == null) {
                vodData.files = getDefaultAssetFiles(vodData.ppvWebName, vodData.ppvMobileName);
            }
            if (vodData.thumbRatios == null) {
                vodData.thumbRatios = Arrays.asList("4:3", "16:9");
            }
        }

        String reqBody = buildIngestVodXml(vodData, INSERT);

        Response resp = executeIngestVodRequestWithAssertion(reqBody);
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
        String reqBody = buildIngestVodXml(vodData, UPDATE);

        Response resp = executeIngestVodRequestWithAssertion(reqBody);
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
        String reqBody = buildIngestVodXml(vodData, DELETE);

        Response resp = executeIngestVodRequestWithAssertion(reqBody);

        // on delete it returns media id
        // assertThat(from(resp.asString()).getInt(ingestAssetIdPath)).isEqualTo(0);
    }

    public static Response executeIngestVodRequest(String reqBody) {
        Response resp = given()
                .header(contentTypeXml)
                .header(soapActionIngestTvinciData)
                .body(reqBody)
                .when()
                .post(ingestUrl);

        assertThat(resp).isNotNull();

        Logger.getLogger(IngestVodUtils.class).debug(reqBody + "\n");
        Logger.getLogger(IngestVodUtils.class).debug(resp.asString());

        return resp;
    }

    // private methods
    private static Response executeIngestVodRequestWithAssertion(String reqBody) {
        Response resp = executeIngestVodRequest(reqBody);

        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");
        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("OK");

        return resp;
    }

    public static String buildIngestVodXml(VodData vodData, IngestAction action) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestVOD.xml");

        // user and password
        if (vodData.isVirtual()) {
            doc.getElementsByTagName("userName").item(0).setTextContent(getIngestVirtualAssetUserName());
            doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestVirualAssetUserPassword());
        } else {
            doc.getElementsByTagName("userName").item(0).setTextContent(getIngestAssetUserName());
            doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestAssetUserPassword());
        }

        // add CDATA section
//          CDATASection cdata = doc.createCDATASection("");
//          doc.getElementsByTagName("tem:request").item(0).appendChild(cdata);

        // media
        Element media = (Element) doc.getElementsByTagName("media").item(0);
        media.setAttribute("co_guid", vodData.coguid());
        media.setAttribute("entry_id", "entry_" + vodData.coguid());
        media.setAttribute("action", action.getValue());
        media.setAttribute("is_active", Boolean.toString(vodData.isActive()));
        media.setAttribute("erase", Boolean.toString(vodData.isErase()));

        if (action.equals(DELETE)) {
            return uncommentCdataSection(docToString(doc));
        }

        // name
        if (vodData.name() != null) {
            Element nameElement = (Element) media.getElementsByTagName("name").item(0);
            Element value = doc.createElement("value");
            value.setAttribute("lang", vodData.lang != null ? vodData.lang : DEFAULT_LANGUAGE);
            value.setTextContent(vodData.name);
            nameElement.appendChild(value);
        }

        // thumb
        if (vodData.thumbUrl() != null) {
            Element thumb = (Element) media.getElementsByTagName("thumb").item(0);
            thumb.setAttribute("url", vodData.thumbUrl());
        }

        // description
        if (vodData.description() != null) {
            Element descriptionElement = (Element) media.getElementsByTagName("description").item(0);
            Element value = doc.createElement("value");
            value.setAttribute("lang", vodData.lang != null ? vodData.lang : DEFAULT_LANGUAGE);
            value.setTextContent(vodData.description);
            descriptionElement.appendChild(value);
        }

        // dates
        Element datesElement = (Element) media.getElementsByTagName("dates").item(0);
        if (vodData.catalogStartDate() != null) {
            datesElement.getElementsByTagName("catalog_start").item(0).setTextContent(vodData.catalogStartDate());
        }
        if (vodData.startDate() != null) {
            datesElement.getElementsByTagName("start").item(0).setTextContent(vodData.startDate());
        }
        if (vodData.catalogEndDate() != null) {
            datesElement.getElementsByTagName("catalog_end").item(0).setTextContent(vodData.catalogEndDate());
        }
        if (vodData.endDate() != null) {
            datesElement.getElementsByTagName("end").item(0).setTextContent(vodData.endDate());
        }

        // pic_ratios
        if (vodData.thumbRatios() != null && vodData.thumbRatios().size() > 0) {
            Element picRatios = (Element) media.getElementsByTagName("pic_ratios").item(0);
            vodData.thumbRatios().forEach(s -> {
                Element ratio = doc.createElement("ratio");
                ratio.setAttribute("ratio", s);
                ratio.setAttribute("thumb", vodData.thumbUrl() != null ? vodData.thumbUrl() : BaseIngestUtils.DEFAULT_THUMB);
                picRatios.appendChild(ratio);
            });
        }

        // media type
        if (vodData.customMediaType() != null) {
            vodData.mediaType(null);
            media.getElementsByTagName("media_type").item(0).setTextContent(vodData.customMediaType());
        }

        if (vodData.mediaType() != null) {
            media.getElementsByTagName("media_type").item(0).setTextContent(vodData.mediaType().getValue());
        }

        // geo block rule
        if (vodData.geoBlockRule() != null) {
            media.getElementsByTagName("geo_block_rule").item(0).setTextContent(vodData.geoBlockRule());
        }

        // stringsMeta
        if (vodData.stringsMeta() != null) {
            Element stringsElement = (Element) media.getElementsByTagName("strings").item(0);
            for (Map.Entry<String, String> entry : vodData.stringsMeta().entrySet()) {
                // meta node
                Element meta = generateAndAppendMetaNode(doc, stringsElement, entry.getKey());

                // value node
                Element value = doc.createElement("value");
                value.setAttribute("lang", DEFAULT_LANGUAGE);
                value.setTextContent(entry.getValue());
                meta.appendChild(value);
            }
        }

        // booleansMeta
        if (vodData.booleansMeta() != null) {
            Element booleansElement = (Element) media.getElementsByTagName("booleans").item(0);
            for (Map.Entry<String, Boolean> entry : vodData.booleansMeta().entrySet()) {
                // meta node
                Element meta = generateAndAppendMetaNode(doc, booleansElement, entry.getKey());
                meta.setTextContent(String.valueOf(entry.getValue()));
            }
        }

        // doublesMeta
        if (vodData.numbersMeta() != null) {
            Element doublesElement = (Element) media.getElementsByTagName("doubles").item(0);
            for (Map.Entry<String, Double> entry : vodData.numbersMeta().entrySet()) {
                // meta node
                Element meta = generateAndAppendMetaNode(doc, doublesElement, entry.getKey());
                meta.setTextContent(String.valueOf(entry.getValue()));
            }
        }

        // datesMeta
        if (vodData.datesMeta() != null) {
            Element datesMetaElement = (Element) media.getElementsByTagName("dates").item(1);
            for (Map.Entry<String, String> entry : vodData.datesMeta().entrySet()) {
                // meta node
                Element metaElement = generateAndAppendMetaNode(doc, datesMetaElement, entry.getKey());
                metaElement.setTextContent(entry.getValue());
            }
        }

        // tags
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
                        value.setAttribute("lang", DEFAULT_LANGUAGE);
                        container.appendChild(value);
                    }
                }
            }
        }

        // files
        if (vodData.files != null && vodData.files.size() > 0) {
            Element files = (Element) media.getElementsByTagName("files").item(0);

            for (VodFile vodFile : vodData.files) {
                files.appendChild(addFile(doc, vodFile));
            }
        }

        // multilingual fields
        // multilingual name
        if (vodData.multilingualName() != null) {
            Element nameElement = (Element) media.getElementsByTagName("name").item(0);
            vodData.multilingualName().forEach((lang, name) -> {
                Element value = doc.createElement("value");
                value.setAttribute("lang", lang);
                value.setTextContent(name);
                nameElement.appendChild(value);
            });
        }

        // multilingual description
        if (vodData.multilingualDescription() != null) {
            Element descriptionElement = (Element) media.getElementsByTagName("description").item(0);
            vodData.multilingualDescription.forEach((lang, name) -> {
                Element value = doc.createElement("value");
                value.setAttribute("lang", lang);
                value.setTextContent(name);
                descriptionElement.appendChild(value);
            });
        }

        // multilingual stringsMeta
        if (vodData.multilingualStringsMeta() != null) {
            Element stringsElement = (Element) media.getElementsByTagName("strings").item(0);
            vodData.multilingualStringsMeta().forEach((meta, langValueMap) -> {
                // meta node
                Element metaElement = generateAndAppendMetaNode(doc, stringsElement, meta);
                langValueMap.forEach((lang, name) -> {
                    // value node
                    Element value = doc.createElement("value");
                    value.setAttribute("lang", lang);
                    value.setTextContent(name);
                    metaElement.appendChild(value);
                });
            });
        }

        // multilingual tags
        if (vodData.multilingualTags() != null) {
            Element metasElement = (Element) media.getElementsByTagName("metas").item(0);
            vodData.multilingualTags().forEach((tag, tagsValuesMap) -> {
                // meta node
                Element metaElement = generateAndAppendMetaNode(doc, metasElement, tag);
                tagsValuesMap.forEach(langValueMap -> {
                    // container node
                    Element container = doc.createElement("container");
                    metaElement.appendChild(container);
                    langValueMap.forEach((lang, name) -> {
                        // value node
                        Element value = doc.createElement("value");
                        value.setAttribute("lang", lang);
                        value.setTextContent(name);
                        container.appendChild(value);
                    });
                });
            });
        }

        // uncomment cdata
        String docAsString = docToString(doc);
        return uncommentCdataSection(docAsString);
    }

    private static Element addFile(Document doc, VodFile vodFile) {
        // file node
        Element file = doc.createElement("file");

        if (vodFile.assetDuration != null) {
            file.setAttribute("assetDuration", vodFile.assetDuration);
        }
        if (vodFile.quality != null) {
            file.setAttribute("quality", vodFile.quality);
        }
        if (vodFile.handling_type != null) {
            file.setAttribute("handling_type", vodFile.handling_type);
        }
        if (vodFile.cdn_name != null) {
            file.setAttribute("cdn_name", vodFile.cdn_name);
        }
        if (vodFile.cdn_code != null) {
            file.setAttribute("cdn_code", vodFile.cdn_code);
        }
        if (vodFile.alt_cdn_code != null) {
            file.setAttribute("alt_cdn_code", vodFile.alt_cdn_code);
        }
        if (vodFile.billing_type != null) {
            file.setAttribute("billing_type", vodFile.billing_type);
        }
        if (vodFile.product_code != null) {
            file.setAttribute("product_code", vodFile.product_code);
        }
        if (vodFile.coguid != null) {
            file.setAttribute("co_guid", vodFile.coguid);
        }
        if (vodFile.type != null) {
            file.setAttribute("type", vodFile.type);
        }
        if (vodFile.ppvModule != null) {
            file.setAttribute("PPV_MODULE", vodFile.ppvModule);
        }

        return file;
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
    private static Map<String, Double> getDefaultNumbers() {
        Map<String, Double> doubles = new HashMap<>();
        doubles.put("Release year", 1900d);

        return doubles;
    }

    // TODO: these values should be get in another way than now
    private static Map<String, String> getDefaultDates() {
        Map<String, String> dates = new HashMap<>();
        dates.put("Life cycle start date", "23/03/2017 12:34:56");

        return dates;
    }

    public static List<VodFile> getDefaultAssetFiles(String ppvModuleName1, String ppvModuleName2) {
        List<VodFile> assetFiles = new ArrayList<>();
        List<String> fileTypeNames = DBUtils.getMediaFileTypeNames(2);

        VodFile file1 = new VodFile(fileTypeNames.get(0), ppvModuleName1);
        VodFile file2 = new VodFile(fileTypeNames.get(1), ppvModuleName2);

        assetFiles.add(file1);
        assetFiles.add(file2);

        return assetFiles;
    }
}
