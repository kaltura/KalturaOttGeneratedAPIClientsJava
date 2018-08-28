package com.kaltura.client.test.tests.featuresTests.versions.five_zero_two;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.enums.AssetType.MEDIA;
import static com.kaltura.client.services.AssetService.get;
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

    private String localCoguid = "";
    private String ingestInsertXml;
    private String ingestUpdateXml;
    private String ingestDeleteXml;
    private static final String suffix4Coguid = "123";
    private static String coguid4NegativeTests = "";

    private boolean wasNOTSetupExecuted = true;

    @BeforeClass
    public void setUp() {
        String prefix = "Movie_";
        localCoguid = getCurrentDateInFormat("yyMMddHHmmssSS");
        name = prefix + "Name_" + localCoguid;
        description = prefix + "Description_" + localCoguid;

        movieAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_1" + localCoguid,
                "new file type1_1" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");

        VodData vodData;
        generateDefaultValues4Insert(MOVIE);
        vodData = getVodData(MOVIE, movieAssetFiles);
        movie = insertVod(vodData, true);
        movieType = movie.getType();

        // generate ingest XMLs for negative cases
        coguid4NegativeTests = movie.getExternalId() + suffix4Coguid;
        ingestInsertXml = ingestXmlRequest.replaceAll(movie.getExternalId(), coguid4NegativeTests);
        ingestDeleteXml = DELETE_VOD_XML.replaceAll("180822092522774", movie.getExternalId());
        ingestUpdateXml = UPDATE_VOD_XML.replaceAll("180828080027358", movie.getExternalId());

        episodeAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_2" + localCoguid,
                "new file type1_2" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");
        generateDefaultValues4Insert(EPISODE);
        vodData = getVodData(EPISODE, episodeAssetFiles);
        episode = insertVod(vodData, true);
        episodeType = episode.getType();

        seriesAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_3" + localCoguid,
                "new file type1_3" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");
        generateDefaultValues4Insert(SERIES);
        vodData = getVodData(SERIES, seriesAssetFiles);
        series = insertVod(vodData, true);
        seriesType = series.getType();

        wasNOTSetupExecuted = false;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, priority =-2, description = "ingest VOD with filled base meta fields")
    public void insertVodMediaBaseFields() {
        // that should be called in case running from testng.xml as a group
        // priority set to -2 to guarantee that setUp will be executed before all other tests as part of that test case.
        if (wasNOTSetupExecuted) {
            setUp();
        }

        generateDefaultValues4Insert(MOVIE);
        List<VODFile> movieAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_11" + localCoguid,
                "new file type1_11" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");
        VodData vodData = getVodData(MOVIE, movieAssetFiles);
        MediaAsset movie = insertVod(vodData, true);
        String ingestRequest = ingestXmlRequest;

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

        checkFiles(movieAssetFiles, movie.getId().toString());

        assertThat(ingestRequest).contains("ratio=\"" + movie.getImages().get(0).getRatio() + "\"");
        assertThat(ingestRequest).contains("ratio=\"" + movie.getImages().get(1).getRatio() + "\"");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "ingest VOD with filled base meta fields")
    public void insertVodEpisodeBaseFields() {
        generateDefaultValues4Insert(EPISODE);
        List<VODFile> episodeAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_21" + localCoguid,
                "new file type1_21" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");
        VodData vodData = getVodData(EPISODE, episodeAssetFiles);
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

        checkFiles(episodeAssetFiles, episode.getId().toString());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "ingest VOD with filled base meta fields")
    public void insertVodSeriesBaseFields() {
        generateDefaultValues4Insert(SERIES);
        List<VODFile> seriesAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_31" + localCoguid,
                "new file type1_31" + localCoguid,"Shai_Regression_PPV", "Subscription_only_PPV");
        VodData vodData = getVodData(SERIES, seriesAssetFiles);
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

        checkFiles(seriesAssetFiles, series.getId().toString());
    }

    @Issue("BEO-5516")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "update VOD with filled base meta fields")
    public void updateVodMediaBaseFields() {
        String coguid = getCoguidOfActiveMediaAsset(movieType);
        // TODO: asked Shir if I can update file and if yes what fields
        generateDefaultValues4Update(true, MOVIE);
        IngestVodUtils.VodData vodData = getVodData(MOVIE, movieAssetFiles);

        MediaAsset asset = updateVod(coguid, vodData);
        String updateRequest = ingestXmlRequest;

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

        // check update with erase="true"
        updateRequest = updateRequest
                .replaceAll("erase=\"false\"", "erase=\"true\"")
                // to remove description from XML
                .replaceAll("<description>", "")
                .replaceAll("<value lang=\"eng\">" + description + "</value>", "")
                .replaceAll("</description>", "")
                // to remove boolean meta from XML
                .replaceAll("<doubles>", "")
                .replaceAll("<meta ml_handling=\"unique\" name=\"" + mediaNumberFieldName + "\">" + doubleValue + "</meta>", "")
                .replaceAll("</doubles>", "");
        Response resp = getResponseBodyFromIngestVod(updateRequest);
        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("OK");

        AssetService.GetAssetBuilder assetBuilder = AssetService.get(String.valueOf(movie.getId()), AssetReferenceType.MEDIA)
                .setKs(getAnonymousKs());
        com.kaltura.client.utils.response.base.Response<Asset> assetGetResponse = executor.executeSync(assetBuilder);
        assertThat(assetGetResponse.results.getId()).isEqualTo(movie.getId());
        assertThat(assetGetResponse.results.getDescription()).isEqualTo(null);
        assertThat(assetGetResponse.results.getMetas().get(mediaNumberFieldName)).isEqualTo(null);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "update VOD episode with filled base meta fields")
    public void updateVodEpisodeBaseFields() {
        generateDefaultValues4Update(false, EPISODE);
        IngestVodUtils.VodData vodData = getVodData(EPISODE, episodeAssetFiles);

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
        IngestVodUtils.VodData vodData = getVodData(SERIES, seriesAssetFiles);

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
        String invalidXml = ingestInsertXml.replaceAll("co_guid=\"" + coguid4NegativeTests + "\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = ingestInsertXml.replaceAll("co_guid=\"" + coguid4NegativeTests + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try delete without coguid", priority =-1 )
    public void tryDeleteWithEmptyCoguid() {
        String invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + movie.getExternalId() + "\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + movie.getExternalId() + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try delete with non-existed coguid")
    public void tryDeleteWithNonexistedCoguid() {
        int positionCoguidTag = ingestDeleteXml.indexOf("co_guid=\"");
        int positionBeginOfCoguid = ingestDeleteXml.indexOf("\"", positionCoguidTag + 1);
        int positionEndOfCoguid = ingestDeleteXml.indexOf("\"", positionBeginOfCoguid + 1);
        String coguid = ingestDeleteXml.substring(positionBeginOfCoguid + 1, positionEndOfCoguid);
        String invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + coguid + "\"", "co_guid=\"123456\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("Media Id not exist");
    }

    @Issue("BEO-5516")
    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc", "OPC"}, description = "try update with empty erase")
    public void tryUpdateWithEmptyErase() {
        String invalidXml = ingestUpdateXml
                .replaceAll("erase=\"false\"", "erase=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("is missing");

        // NO REASON TO CHECK AS DEFAULT VALUE IS FALSE and it allows to ingest
        /*String invalidXml = ingestUpdateXml
                .replaceAll("erase=\"false\"", "");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("is missing");*/
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with empty entry_id")
    public void tryInsertWithEmptyEntryId() {
        String invalidXml = ingestInsertXml.replaceAll("entry_id=\"entry_" + coguid4NegativeTests + "\"", "entry_id=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");

        invalidXml = ingestInsertXml.replaceAll("entry_id=\"entry_" + coguid4NegativeTests + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert inactive item")
    public void tryInsertInactiveItem() {
        String invalidXml = ingestInsertXml.replaceAll("is_active=\"true\"", "is_active=\"false\"");
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
        String invalidXml = ingestInsertXml.replaceAll("is_active=\"true\"", "is_active=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.IsActive cannot be empty"); 
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with empty name", priority =-1)
    public void tryInsertWithEmptyName() {
        String invalidXml = ingestInsertXml.replaceAll(">" + movie.getName() + "<", "><");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.basic.name.value.text cannot be empty");

        invalidXml = ingestInsertXml
                .replaceAll("<name>", "")
                .replaceAll("<value lang=\"eng\">" + movie.getName() + "</value>", "")
                .replaceAll("</name>", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.Basic.Name cannot be empty");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with invalid credentials")
    public void tryInsertWithInvalidCredentials() {
        String statusMessage = "Invalid credentials";
        String status = "ERROR";

        // invalid user name
        String invalidXml = ingestInsertXml.replaceAll("Name>Test_API_27_03<", "Name>aTest_API_27_03<");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo(statusMessage);
        assertThat(from(resp.asString()).getString(ingestStatusPath)).isEqualTo(status);

        // invalid password
        invalidXml = ingestInsertXml.replaceAll("passWord>Test_API_27_03<", "passWord>aTest_API_27_03<");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo(statusMessage);
        assertThat(from(resp.asString()).getString(ingestStatusPath)).isEqualTo(status);
    }

    @Issue("BEO-5520")
    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "try insert with invalid meta or tag field")
    public void tryInsertWithInvalidMetaOrTagField() {
        String suffix = "UPDATE654987321";
        String ingestXml = ingestInsertXml.replaceAll(localCoguid, localCoguid + suffix);

        String updatedField = mediaNumberFieldName + suffix;
        String invalidXml = ingestXml.replaceAll(mediaNumberFieldName, updatedField);
        validateInvalidMovieField(invalidXml, updatedField, "meta");

        updatedField = mediaDateFieldName + suffix;
        invalidXml = ingestXml.replaceAll(mediaDateFieldName, updatedField);
        validateInvalidMovieField(invalidXml, updatedField, "meta");

        updatedField = mediaBooleanFieldName + suffix;
        invalidXml = ingestXml.replaceAll(mediaBooleanFieldName, updatedField);
        validateInvalidMovieField(invalidXml, updatedField, "meta");

        updatedField = mediaTagFieldName + suffix;
        invalidXml = ingestXml.replaceAll(mediaTagFieldName, updatedField);
        validateInvalidMovieField(invalidXml, updatedField, "tag");

        updatedField = mediaTextFieldName + suffix;
        invalidXml = ingestXml.replaceAll(mediaTextFieldName, updatedField);
        validateInvalidMovieField(invalidXml, updatedField, "meta");
    }

    @Issue("BEO-5521")
    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "insert multilingual fields")
    public void ingestMultiLingualFields() {
        // ingested Movie for checking multilanguage
        final String JAP = "jap";
        final String ENG = "eng";
        String suffix = "multilingual";
        name = "Name_" + localCoguid.substring(0, localCoguid.length() - 2); // to not update name automatically
        description = "Description_" + localCoguid.substring(0, localCoguid.length() - 2); // to not update description automatically
        generateDefaultValues4Insert(MOVIE);
        VodData vodData = getVodData(MOVIE, movieAssetFiles);
        movie = insertVod(vodData, true);
        String nameData = "<value lang=\"eng\">" + movie.getName() + "</value>";
        String descriptionData = "<value lang=\"eng\">" + movie.getDescription() + "</value>";
        String stringMetaDataValue = ((MultilingualStringValue)movie.getMetas().get(mediaTextFieldName)).getValue();
        String stringMetaData = "<value lang=\"eng\">" + stringMetaDataValue + "</value>";
        String tagData = "<value lang=\"eng\">" + tagValue1 + "</value>";

        // to get xml having all fields supporting multilingual
        String ingestXml = ingestXmlRequest.replaceAll(localCoguid, localCoguid + suffix);
        ingestXml = ingestXml.replaceAll(nameData, nameData + nameData.replaceAll(ENG, JAP)
                                            .replaceAll(movie.getName(), movie.getName() + JAP));
        ingestXml = ingestXml.replaceAll(descriptionData, descriptionData + descriptionData.replaceAll(ENG, JAP)
                                            .replaceAll(movie.getDescription(), movie.getDescription() + JAP));
        ingestXml = ingestXml.replaceAll(stringMetaData, stringMetaData + stringMetaData.replaceAll(ENG, JAP)
                                            .replaceAll(stringMetaDataValue, stringMetaDataValue + JAP));
        ingestXml = ingestXml.replaceAll(tagData, tagData + tagData.replaceAll(ENG, JAP))
                .replaceAll("lang=\"jap\">" + tagValue1, "lang=\"jap\">" + tagValue1 + JAP);

        Response resp = getResponseBodyFromIngestVod(ingestXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("OK");
        String id = from(resp.asString()).get(ingestAssetIdPath).toString();
        assertThat(id).isEqualTo(movie.getId().toString());

        AssetService.GetAssetBuilder getAssetBuilder = get(id, AssetReferenceType.MEDIA)
                .setKs(getAnonymousKs())
                .setLanguage(JAP);
        Asset asset = executor.executeSync(getAssetBuilder).results;
        assertThat(asset.getName()).isEqualTo(movie.getName() + JAP);
        assertThat(asset.getDescription()).isEqualTo(movie.getDescription() + JAP);
        assertThat(((MultilingualStringValue)asset.getMetas().get(mediaTextFieldName)).getValue())
                .isEqualTo(stringMetaDataValue + JAP);
        // check tag value
        boolean isTagValueFound = isTagValueFound(tagValue1 + JAP, asset);
        assertThat(isTagValueFound).isEqualTo(true);

        getAssetBuilder = get(id, AssetReferenceType.MEDIA)
                .setKs(getAnonymousKs())
                .setLanguage(ENG);
        asset = executor.executeSync(getAssetBuilder).results;
        assertThat(asset.getName()).isEqualTo(movie.getName());
        assertThat(asset.getDescription()).isEqualTo(movie.getDescription());
        assertThat(((MultilingualStringValue)asset.getMetas().get(mediaTextFieldName)).getValue()).isEqualTo(stringMetaDataValue);
        // check tag value
        isTagValueFound = isTagValueFound(tagValue1, asset);
        assertThat(isTagValueFound).isEqualTo(true);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"ingest VOD for OPC", "opc"}, description = "ingest VOD with different Ppv")
    public void insertUpdateVodMediaPpv() {
        generateDefaultValues4Insert(MOVIE);
        List<VODFile> movieAssetFiles = loadAssetFiles("Test130301","new file type1", "Test130301_11" + localCoguid,
                "new file type1_11" + localCoguid,"Shai_Regression_PPV;Subscription_only_PPV", "Subscription_only_PPV");
        VodData vodData = getVodData(MOVIE, movieAssetFiles);
        MediaAsset movie = insertVod(vodData, true);
        String ingestRequest = ingestXmlRequest;

        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getDescription()).isEqualTo(description);
        checkFiles(movieAssetFiles, movie.getId().toString());

        Household household = HouseholdUtils.createHousehold(1, 1, true);
        String classMasterUserKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesList(household).get(0).getUdid());
        AssetService.GetAssetBuilder assetBuilder = AssetService.get(movie.getId().toString(), AssetReferenceType.MEDIA).setKs(classMasterUserKs);
        com.kaltura.client.utils.response.base.Response<Asset> assetGetResponse = executor.executeSync(assetBuilder);
        List<MediaFile> getMediaFiles = assetGetResponse.results.getMediaFiles();
        int fileId1 = getMediaFiles.get(0).getId();
        int fileId2 = getMediaFiles.get(1).getId();

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setFileIdIn(String.valueOf(fileId1));
        ppFilter.setIsLowest(false);
        ProductPriceService.ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        com.kaltura.client.utils.response.base.Response<ListResponse<ProductPrice>> productPriceResponse =
                executor.executeSync(productPriceListBeforePurchase.setKs(classMasterUserKs));
        assertThat(((PpvPrice)productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(fileId1);
        assertThat(((PpvPrice)productPriceResponse.results.getObjects().get(0)).getPpvDescriptions().get(0).getValue()).isEqualTo(fileId1);
        // TODO: complete

    }

    boolean isTagValueFound(String value2Found, Asset asset) {
        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            if (value2Found.equals(tagValue.getValue())) {
                return true;
            }
        }
        return false;
    }

    void validateInvalidMovieField(String ingestXml, String fieldName, String fieldType) {
        Response resp = getResponseBodyFromIngestVod(ingestXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath))
                .isEqualTo(fieldType + ": " + fieldName + " does not exist for group");
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

    // to check that ingested file data are corresponding to getAsset file data
    private void checkFiles(List<VODFile> ingestAssetFiles, String assetId) {
        boolean isFileWasFound = false;

        AssetService.GetAssetBuilder assetBuilder = AssetService.get(assetId, AssetReferenceType.MEDIA).setKs(getAnonymousKs());
        com.kaltura.client.utils.response.base.Response<Asset> assetGetResponse = executor.executeSync(assetBuilder);
        List<MediaFile> getMediaFiles = assetGetResponse.results.getMediaFiles();

        for (VODFile ingestFile: ingestAssetFiles) {
            for (MediaFile getFile: getMediaFiles) {
                if (getFile.getType().equals(ingestFile.type())) {
                    isFileWasFound = true;
                    assertThat(getFile.getDuration().toString()).isEqualTo(ingestFile.assetDuration());
                    assertThat(getFile.getUrl()).isEqualTo(ingestFile.cdn_code());
                    assertThat(getFile.getAltStreamingCode()).isEqualTo(ingestFile.alt_cdn_code());
                    assertThat(getFile.getExternalStoreId()).isEqualTo(ingestFile.product_code());
                    assertThat(getFile.getExternalId()).isEqualTo(ingestFile.coguid());
                }
            }
        }
        assertThat(isFileWasFound).isEqualTo(true);
    }

    // TODO: try empty files
    // TODO: try empty images
}
