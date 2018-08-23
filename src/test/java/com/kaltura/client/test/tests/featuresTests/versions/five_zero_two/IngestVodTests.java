package com.kaltura.client.test.tests.featuresTests.versions.five_zero_two;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static io.restassured.path.xml.XmlPath.from;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5428
 */
public class IngestVodTests extends BaseTest {

    private static final String defaultThumbUrl =
            "http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png";

    // media types
    private static final String EPISODE = "Episode";
    private static final String MOVIE = "Movie";
    private static final String SERIES = "Series";

    // TODO: how to get these data from DB or request?
    //MEDIA fields
    private static final String mediaTextFieldName = "BoxOffice";
    private static final String mediaDateFieldName = "ReleaseDate";
    private static final String mediaNumberFieldName = "Runtime2";
    private static final String mediaBooleanFieldName = "IsAgeLimited";
    private static final String mediaTagFieldName = "Actors";

    //Episode fields
    private static final String episodeTextFieldName = "TwitterHashtag";
    private static final String episodeDateFieldName = "Date";
    private static final String episodeNumberFieldName = "CommonIpAddress";
    private static final String episodeBooleanFieldName = "CyyNCAh";
    private static final String episodeTagFieldName = "Studio";

    //Series fields
    private static final String seriesTextFieldName = "SeriesID";
    private static final String seriesDateFieldName = "DateField";
    private static final String seriesNumberFieldName = "ReleaseYear";
    private static final String seriesBooleanFieldName = "IsWestern";
    private static final String seriesTagFieldName = "Studio";

    // fields & values
    private HashMap<String, String> stringMetaMap = new HashMap<>();
    private HashMap<String, Double> numberMetaMap = new HashMap<>();
    private HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    private HashMap<String, String> datesMetaMap = new HashMap<>();
    private HashMap<String, List<String>> tagsMetaMap = new HashMap<>();
    private List<IngestVodUtils.VODFile> assetFiles = new ArrayList<>();
    private String name;
    private String description;
    private String textValue;
    private String dateValue;
    private double doubleValue;
    private boolean booleanValue;
    private List<String> tagValues;

    private static final String INGEST_VOD_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
            "<soapenv:Header/>\n" +
            "<soapenv:Body>\n" +
            "<tem:IngestTvinciData>\n" +
            "<tem:request>\n" +
            "<userName>Test_API_27_03</userName>\n" +
            "<passWord>Test_API_27_03</passWord>\n" +
            "<data>\n" +
            "<![CDATA[\n" +
            "<feed>\n" +
            "<export>\n" +
            "<media action=\"insert\" co_guid=\"180822092522774\" entry_id=\"entry_180822092522774\" erase=\"false\" is_active=\"true\">\n" +
            "<basic>\n" +
            "<name>\n" +
            "<value lang=\"eng\">Movie_Name_1808220925223281</value>\n" +
            "</name>\n" +
            "<thumb ingestUrl=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\"/>\n" +
            "<description>\n" +
            "<value lang=\"eng\">Movie_Description_1808220925223281</value>\n" +
            "</description>\n" +
            "<dates>\n" +
            "<catalog_start/>\n" +
            "<start/>\n" +
            "<catalog_end/>\n" +
            "<end/>\n" +
            "</dates>\n" +
            "<pic_ratios>\n" +
            "<ratio ratio=\"4:3\" thumb=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\"/>\n" +
            "<ratio ratio=\"16:9\" thumb=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\"/>\n" +
            "</pic_ratios>\n" +
            "<media_type>Movie</media_type>\n" +
            "<rules>\n" +
            "<geo_block_rule/>\n" +
            "<watch_per_rule>Parent Allowed</watch_per_rule>\n" +
            "<device_rule/>\n" +
            "</rules>\n" +
            "</basic>\n" +
            "<structure>\n" +
            "<strings>\n" +
            "<meta ml_handling=\"unique\" name=\"BoxOffice\">\n" +
            "<value lang=\"eng\">BoxOfficevalue</value>\n" +
            "</meta>\n" +
            "</strings>\n" +
            "<booleans/>\n" +
            "<doubles>\n" +
            "<meta ml_handling=\"unique\" name=\"Runtime2\">123456</meta>\n" +
            "</doubles>\n" +
            "<dates>\n" +
            "<meta ml_handling=\"unique\" name=\"ReleaseDate\">12/12/2012</meta>\n" +
            "</dates>\n" +
            "<metas>\n" +
            "<meta ml_handling=\"unique\" name=\"Actors\">\n" +
            "<container>\n" +
            "<value lang=\"eng\">Jack Nicholson</value>\n" +
            "</container>\n" +
            "<container>\n" +
            "<value lang=\"eng\">Natalie Portman</value>\n" +
            "</container>\n" +
            "</meta>\n" +
            "</metas>\n" +
            "</structure>\n" +
            "<files>\n" +
            "<file PPV_MODULE=\"Shai_Regression_PPV\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" assetDuration=\"1000\" billing_type=\"Tvinci\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" cdn_name=\"Default CDN\" co_guid=\"Test130301_1180822092522328\" handling_type=\"CLIP\" product_code=\"productExampleCode\" quality=\"HIGH\" type=\"Test130301\"/>\n" +
            "<file PPV_MODULE=\"Subscription_only_PPV\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" assetDuration=\"1000\" billing_type=\"Tvinci\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" cdn_name=\"Default CDN\" co_guid=\"new file type1_1180822092522328\" handling_type=\"CLIP\" product_code=\"productExampleCode\" quality=\"HIGH\" type=\"new file type1\"/>\n" +
            "</files>\n" +
            "</media>\n" +
            "</export>\n" +
            "</feed>\n" +
            "]]>\n" +
            "</data>\n" +
            "</tem:request>\n" +
            "</tem:IngestTvinciData>\n" +
            "</soapenv:Body>\n" +
            "</soapenv:Envelope>";

    @BeforeClass
    public void setUp() {
        String prefix = "Movie_";
        String localCoguid = getCurrentDateInFormat("yyMMddHHmmssSS");
        name =  prefix + "Name_" + localCoguid;
        description = prefix + "Description_" + localCoguid;

        IngestVodUtils.VODFile file1 = new IngestVodUtils.VODFile()
                .assetDuration("1000")
                .quality("HIGH")
                .handling_type("CLIP")
                .cdn_name("Default CDN")
                .cdn_code("http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .alt_cdn_code("http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .billing_type("Tvinci")
                .product_code("productExampleCode")
                .type("Test130301")
                .coguid("Test130301_1" + localCoguid)
                .ppvModule("Shai_Regression_PPV");
        IngestVodUtils.VODFile file2 = new IngestVodUtils.VODFile()
                .assetDuration("1000")
                .quality("HIGH")
                .handling_type("CLIP")
                .cdn_name("Default CDN")
                .cdn_code("http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .alt_cdn_code("http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .billing_type("Tvinci")
                .product_code("productExampleCode")
                .type("new file type1")
                .coguid("new file type1_1" + localCoguid)
                .ppvModule("Subscription_only_PPV");
        assetFiles.add(file1);
        assetFiles.add(file2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodMediaBaseFields() {
        generateDefaultValues4Insert(MOVIE);
        VodData vodData = getVodData(MOVIE);

        MediaAsset asset = insertVod(vodData, true);
        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)asset.getMetas().get(mediaTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(mediaNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(mediaDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(mediaBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodEpisodeBaseFields() {
        generateDefaultValues4Insert(EPISODE);
        VodData vodData = getVodData(EPISODE);

        MediaAsset asset = insertVod(vodData, true);
        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)asset.getMetas().get(episodeTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(episodeNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(episodeDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(episodeBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodSeriesBaseFields() {
        generateDefaultValues4Insert(SERIES);
        VodData vodData = getVodData(SERIES);

        MediaAsset asset = insertVod(vodData, true);
        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((StringValue)asset.getMetas().get(seriesTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(seriesNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(seriesDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(seriesBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD with filled base meta fields")
    public void updateVodMediaBaseFields() {
//        AssetFilter assetFilter = new AssetFilter();
//        assetFilter.setOrderBy(AssetOrderBy.CREATE_DATE_DESC.getValue());
//        FilterPager pager = new FilterPager();
//        pager.setPageSize(1);
//        pager.setPageIndex(1);
//        /*Map<String, Object> params = new HashMap<>();
//        params.put("orderBy", AssetOrderBy.CREATE_DATE_DESC);*/
//        com.kaltura.client.utils.response.base.Response<ListResponse<Asset>> assetListResponse =
//                executor.executeSync(list(assetFilter, pager)
//                .setKs(getAnonymousKs()));
//        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);

        // TODO: get previous value
        generateDefaultValues4Update(true, MOVIE);
        IngestVodUtils.VodData vodData = getVodData(MOVIE);

        String coguid = "180823143608237"; // TODO: update logic to get these data from
        MediaAsset asset = updateVod(coguid, vodData);

        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)asset.getMetas().get(mediaTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(mediaNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(mediaDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(mediaBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD with filled base meta fields")
    public void updateVodEpisodeBaseFields() {
        // TODO: get previous value
        generateDefaultValues4Update(false, EPISODE);
        IngestVodUtils.VodData vodData = getVodData(EPISODE);

        String coguid = "180823171226961"; // TODO: update logic to get these data from
        MediaAsset asset = updateVod(coguid, vodData);

        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)asset.getMetas().get(episodeTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(episodeNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(episodeDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(episodeBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD with filled base meta fields")
    public void updateVodSeriesBaseFields() {
        // TODO: get previous value
        generateDefaultValues4Update(true, SERIES);
        IngestVodUtils.VodData vodData = getVodData(SERIES);

        String coguid = "180823181201121"; // TODO: update logic to get these data from
        MediaAsset asset = updateVod(coguid, vodData);

        assertThat(asset.getName()).isEqualTo(name);
        assertThat(asset.getDescription()).isEqualTo(description);
        assertThat(((StringValue)asset.getMetas().get(seriesTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)asset.getMetas().get(seriesNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)asset.getMetas().get(seriesDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset.getMetas().get(seriesBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "delete")
    public void deleteMovie() {
        String coguid = "180823171226961"; // TODO: update logic to get these data from
        deleteVod(coguid);
        // TODO: add logic to check delete
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "delete episode")
    public void deleteEpisode() {
        String coguid = "180823171226961"; // TODO: update logic to get these data from
        deleteVod(coguid);
        // TODO: add logic to check delete
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "delete episode")
    public void deleteSeries() {
        String coguid = "180823181201121"; // TODO: update logic to get these data from
        deleteVod(coguid);
        // TODO: add logic to check delete
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try insert without coguid")
    public void tryInsertWithEmptyCoguid() {
        String invalidXml = INGEST_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with invalid action")
    public void tryInsertWithInvalidAction() {
        // TODO: ask Shir why it returns 0 instead of tvmId
        String invalidXml = INGEST_VOD_XML.replaceAll("action=\"insert\"", "action=\"linsert\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).doesNotContain("OK");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with empty entry_id")
    public void tryInsertWithEmptyEntryId() {
        String invalidXml = INGEST_VOD_XML.replaceAll("entry_id=\"entry_180822092522774\"", "entry_id=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");
    }

    /* TODO: check the usual logic
    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert inactive item")
    public void tryInsertinactiveItem() {
        String invalidXml = INGEST_VOD_XML.replaceAll("is_active=\"true\"", "is_active=\"false\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).doesNotContain("OK");
    }*/

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with empty isActive parameter")
    public void tryInsertEmptyIsActive() {
        String invalidXml = INGEST_VOD_XML.replaceAll("is_active=\"true\"", "is_active=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.IsActive cannot be empty");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with empty name")
    public void tryInsertWithEmptyName() {
        String invalidXml = INGEST_VOD_XML.replaceAll(">Movie_Name_1808220925223281<", "><");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.basic.name.value.text cannot be empty");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try insert with invalid credentials")
    public void tryInsertWithInvalidCredentials() {
        String statusMessage = "Invalid credentials";
        String status = "ERROR";

        // invalid user name
        String invalidXml = INGEST_VOD_XML.replaceAll("Name>Test_API_27_03<", "Name>aTest_API_27_03<");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo(statusMessage);
        assertThat(from(resp.asString()).getString(ingestStatusPath)).isEqualTo(status);

        // invalid password
        invalidXml = INGEST_VOD_XML.replaceAll("passWord>Test_API_27_03<", "passWord>aTest_API_27_03<");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo(statusMessage);
        assertThat(from(resp.asString()).getString(ingestStatusPath)).isEqualTo(status);
    }

    VodData getVodData(String mediaType) {
        switch (mediaType) {
            case "Movie":
                return new VodData()
                        .name(name)
                        .description(description)
                        .mediaType(MediaType.MOVIE)
                        .thumbUrl(defaultThumbUrl)
                        .strings(stringMetaMap)
                        .booleans(booleanHashMap)
                        .numbers(numberMetaMap)
                        .dates(datesMetaMap)
                        .tags(tagsMetaMap)
                        .isVirtual(false)
                        .assetFiles(assetFiles);
            case "Episode":
                return new VodData()
                        .name(name)
                        .description(description)
                        .mediaType(MediaType.EPISODE)
                        .thumbUrl(defaultThumbUrl)
                        .strings(stringMetaMap)
                        .booleans(booleanHashMap)
                        .numbers(numberMetaMap)
                        .dates(datesMetaMap)
                        .tags(tagsMetaMap)
                        .isVirtual(false)
                        .assetFiles(assetFiles);
            case "Series":
            return new VodData()
                    .name(name)
                    .description(description)
                    .mediaType(MediaType.SERIES)
                    .thumbUrl(defaultThumbUrl)
                    .strings(stringMetaMap)
                    .booleans(booleanHashMap)
                    .numbers(numberMetaMap)
                    .dates(datesMetaMap)
                    .tags(tagsMetaMap)
                    .isVirtual(true)
                    .assetFiles(assetFiles);
            default:
                return null;
        }
    }

    void fillMediaMapsWithData() {
        stringMetaMap.put(mediaTextFieldName, textValue);
        numberMetaMap.put(mediaNumberFieldName, doubleValue);
        datesMetaMap.put(mediaDateFieldName, dateValue);
        booleanHashMap.put(mediaBooleanFieldName, booleanValue);
        tagsMetaMap.put(mediaTagFieldName, tagValues);
    }

    void fillEpisodeMapsWithData() {
        stringMetaMap.put(episodeTextFieldName, textValue);
        numberMetaMap.put(episodeNumberFieldName, doubleValue);
        datesMetaMap.put(episodeDateFieldName, dateValue);
        booleanHashMap.put(episodeBooleanFieldName, booleanValue);
        tagsMetaMap.put(episodeTagFieldName, tagValues);
    }

    void fillSeriesMapsWithData() {
        stringMetaMap.put(seriesTextFieldName, textValue);
        numberMetaMap.put(seriesNumberFieldName, doubleValue);
        datesMetaMap.put(seriesDateFieldName, dateValue);
        booleanHashMap.put(seriesBooleanFieldName, booleanValue);
        tagsMetaMap.put(seriesTagFieldName, tagValues);
    }

    void generateDefaultValues4Update(boolean previousValue, String mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy") + "_updated";
        dateValue = getOffsetDateInFormat(1,"MM/dd/yyyy");
        // to round up 2 decimal places
        doubleValue = Math.round(getRandomDoubleValue() * 100.0) / 100.0;
        booleanValue = !previousValue;
        tagValues = new ArrayList<>();
        tagValues.add("Jack NicholsonUpd");
        tagValues.add("Natalie PortmanUpd");
        tagValues.add(textValue);

        fillMapsWithData(mediaType);
    }

    void generateDefaultValues4Insert(String mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy");
        dateValue = getCurrentDateInFormat("MM/dd/yyyy");
        // to round up 2 decimal places
        doubleValue = Math.round(getRandomDoubleValue() * 100.0) / 100.0;
        booleanValue = getRandomBooleanValue();
        tagValues = new ArrayList<>();
        tagValues.add("Jack Nicholson");
        tagValues.add("Natalie Portman");
        tagValues.add(textValue);

        fillMapsWithData(mediaType);
    }

    private void fillMapsWithData(String mediaType) {
        switch (mediaType) {
            case MOVIE:
                fillMediaMapsWithData();
                break;
            case EPISODE:
                fillEpisodeMapsWithData();
                break;
            case SERIES:
                fillSeriesMapsWithData();
                break;
            default:
                break;
        }
    }
}
