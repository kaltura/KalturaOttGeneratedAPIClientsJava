package com.kaltura.client.test.tests.featuresTests.versions.five_zero_two;

import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOPCUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static io.restassured.path.xml.XmlPath.from;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5428
 */
public class IngestVodTests extends BaseTest {

    private MediaAsset movie;
    private MediaAsset episode;
    private MediaAsset series;
    private int movieType;
    private int episodeType;
    private int seriesType;

    private String ingestXml;

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

        VodData vodData;
        generateDefaultValues4Insert(MOVIE);
        vodData = getVodData(MOVIE);
        movie = insertVod(vodData, true);
        movieType = movie.getType();

        generateDefaultValues4Insert(EPISODE);
        vodData = getVodData(EPISODE);
        episode = insertVod(vodData, true);
        episodeType = episode.getType();

        generateDefaultValues4Insert(SERIES);
        vodData = getVodData(SERIES);
        series = insertVod(vodData, true);
        seriesType = series.getType();

        generateDefaultValues4Insert(MOVIE);
        vodData = getVodData(MOVIE);
        ingestXml = buildIngestVodXml(vodData, INGEST_ACTION_INSERT);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, priority =-1, description = "ingest VOD with filled base meta fields")
    public void insertVodMediaBaseFields() {
        // that should be called in case running from testng.xml as a group
        // priority set to -1 to guarantee that setUp will be executed before all other tests as part of that test case.
        setUp();

        generateDefaultValues4Insert(MOVIE);
        VodData vodData = getVodData(MOVIE);
        MediaAsset movie = insertVod(vodData, true);

        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)movie.getMetas().get(mediaTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)movie.getMetas().get(mediaNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)movie.getMetas().get(mediaDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)movie.getMetas().get(mediaBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = movie.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "ingest VOD with filled base meta fields")
    public void insertVodEpisodeBaseFields() {
        generateDefaultValues4Insert(EPISODE);
        VodData vodData = getVodData(EPISODE);
        MediaAsset episode = insertVod(vodData, true);

        assertThat(episode.getName()).isEqualTo(name);
        assertThat(episode.getDescription()).isEqualTo(description);
        assertThat(((MultilingualStringValue)episode.getMetas().get(episodeTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)episode.getMetas().get(episodeNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)episode.getMetas().get(episodeDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)episode.getMetas().get(episodeBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = episode.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "ingest VOD with filled base meta fields")
    public void insertVodSeriesBaseFields() {
        generateDefaultValues4Insert(SERIES);
        VodData vodData = getVodData(SERIES);
        MediaAsset series = insertVod(vodData, true);

        assertThat(series.getName()).isEqualTo(name);
        assertThat(series.getDescription()).isEqualTo(description);
        assertThat(((StringValue)series.getMetas().get(seriesTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(((DoubleValue)series.getMetas().get(seriesNumberFieldName)).getValue()).isEqualTo(doubleValue);
        assertThat(getFormattedDate(((LongValue)series.getMetas().get(seriesDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)series.getMetas().get(seriesBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        Map<String, MultilingualStringValueArray> tags = series.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "update VOD with filled base meta fields")
    public void updateVodMediaBaseFields() {
        String coguid = getCoguidOfActiveMediaAsset(movieType);

        generateDefaultValues4Update(true, MOVIE);
        IngestVodUtils.VodData vodData = getVodData(MOVIE);

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
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "update VOD episode with filled base meta fields")
    public void updateVodEpisodeBaseFields() {
        generateDefaultValues4Update(false, EPISODE);
        IngestVodUtils.VodData vodData = getVodData(EPISODE);

        String coguid = getCoguidOfActiveMediaAsset(episodeType);
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
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "update VOD series with filled base meta fields")
    public void updateVodSeriesBaseFields() {
        generateDefaultValues4Update(true, SERIES);
        IngestVodUtils.VodData vodData = getVodData(SERIES);

        String coguid = getCoguidOfActiveMediaAsset(seriesType);
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
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "delete")
    public void deleteMovie() {
        String coguid = getCoguidOfActiveMediaAsset(movieType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "delete episode")
    public void deleteEpisode() {
        String coguid = getCoguidOfActiveMediaAsset(episodeType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "delete series")
    public void deleteSeries() {
        String coguid = getCoguidOfActiveMediaAsset(seriesType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert without coguid")
    public void tryInsertWithEmptyCoguid() {
        String invalidXml = INGEST_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = INGEST_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try delete without coguid")
    public void tryDeleteWithEmptyCoguid() {
        String invalidXml = DELETE_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = DELETE_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try delete with non-existed coguid")
    public void tryDeleteWithNonexistedCoguid() {
        String invalidXml = DELETE_VOD_XML.replaceAll("co_guid=\"180822092522774\"", "co_guid=\"123456\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("Media Id not exist");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc", "OPC"}, description = "try update with empty erase")
    public void tryUpdateWithEmptyErase() {
        // TODO: check and if it work then add logic related null erase and positive test related update
        String coguid = getCoguidOfActiveMediaAsset(movieType);
        String invalidXml = UPDATE_VOD_XML
                .replaceAll("erase=\"false\"", "erase=\"\"")
                .replaceAll("co_guid=\"180822092522774\"", "co_guid=\"" + coguid + "\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with empty entry_id")
    public void tryInsertWithEmptyEntryId() {
        String invalidXml = INGEST_VOD_XML.replaceAll("entry_id=\"entry_180822092522774\"", "entry_id=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");

        invalidXml = INGEST_VOD_XML.replaceAll("entry_id=\"entry_180822092522774\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert inactive item")
    public void tryInsertInactiveItem() {
        String invalidXml = INGEST_VOD_XML.replaceAll("is_active=\"true\"", "is_active=\"false\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        String id = from(resp.asString()).get(ingestAssetIdPath).toString();

        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setKSql("media_id='" + id + "'");
        com.kaltura.client.utils.response.base.Response<ListResponse<Asset>> assetListResponse =
                executor.executeSync(list(assetFilter)
                        .setKs(getAnonymousKs()));
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(0);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with empty isActive parameter")
    public void tryInsertEmptyIsActive() {
        String invalidXml = ingestXml;//.replaceAll("is_active=\"true\"", "is_active=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.IsActive cannot be empty");

        invalidXml = ingestXml.replaceAll("is_active=\"true\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.IsActive cannot be empty");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with empty name")
    public void tryInsertWithEmptyName() {
        String invalidXml = INGEST_VOD_XML.replaceAll(">Movie_Name_1808220925223281<", "><");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.basic.name.value.text cannot be empty");

        invalidXml = INGEST_VOD_XML
                .replaceAll("<name>", "")
                .replaceAll("<value lang=\"eng\">Movie_Name_1808220925223281</value>", "")
                .replaceAll("</name>", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.basic.name.value.text cannot be empty");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with invalid credentials")
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

    void checkVODDeletion(String coguid) {
        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setKSql("externalId='" + coguid + "'");
        com.kaltura.client.utils.response.base.Response<ListResponse<Asset>> assetListResponse =
                executor.executeSync(list(assetFilter)
                        .setKs(getAnonymousKs()));
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);

        deleteVod(coguid);
        AssetService.ListAssetBuilder listAssetBuilder = list(assetFilter).setKs(getAnonymousKs());
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> (executor.executeSync(listAssetBuilder).results.getTotalCount() == 0));
    }
}
