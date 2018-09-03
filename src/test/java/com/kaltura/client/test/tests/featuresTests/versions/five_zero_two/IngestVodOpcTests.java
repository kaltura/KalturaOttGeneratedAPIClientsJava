package com.kaltura.client.test.tests.featuresTests.versions.five_zero_two;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.kaltura.client.services.AssetService.get;
import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOPCUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOPCUtils.delayBetweenRetriesInSeconds;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOPCUtils.maxTimeExpectingValidResponseInSeconds;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static io.restassured.path.xml.XmlPath.from;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5428
 */
@Test(groups = { "opc", "ingest VOD for OPC" })
// tag @Test allow to exclude class from testng using it's group name in form <exclude name="ingest VOD for OPC"/>  
public class IngestVodOpcTests extends BaseTest {

    private MediaAsset movie;
    private MediaAsset episode;
    private MediaAsset series;
    private int movieType;
    private int episodeType;
    private int seriesType;

    private String localCoguid = "";
    private String ingestInsertXml;
    private String ingestDeleteXml;
    private static final String suffix4Coguid = "123";
    private static String coguid4NegativeTests = "";

    private static List<String> fileTypeNames;
    private static List<String> ppvNames;

    @BeforeClass()
    public void setUp() {
        fileTypeNames = DBUtils.getMediaFileTypeNames(2);
        ppvNames = DBUtils.getPpvNames(2);

        String prefix = "Movie_";
        localCoguid = getCurrentDateInFormat("yyMMddHHmmssSS");
        name = prefix + "Name_" + localCoguid;
        description = prefix + "Description_" + localCoguid;

        movieAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));

        VodData vodData;
        generateDefaultValues4Insert(MOVIE);
        vodData = getVodData(MOVIE, movieAssetFiles);
        movie = insertVod(vodData, true);
        movieType = movie.getType();

        // generate ingest XMLs for negative cases
        coguid4NegativeTests = movie.getExternalId() + suffix4Coguid;
        ingestInsertXml = ingestXmlRequest.replaceAll(movie.getExternalId(), coguid4NegativeTests);
        ingestDeleteXml = DELETE_VOD_XML.replaceAll("180822092522774", movie.getExternalId());

        episodeAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));;
        generateDefaultValues4Insert(EPISODE);
        vodData = getVodData(EPISODE, episodeAssetFiles);
        episode = insertVod(vodData, true);
        episodeType = episode.getType();

        seriesAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));;
        generateDefaultValues4Insert(SERIES);
        vodData = getVodData(SERIES, seriesAssetFiles);
        series = insertVod(vodData, true);
        seriesType = series.getType();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodMediaBaseFields() {
        generateDefaultValues4Insert(MOVIE);
        List<VodFile> movieAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));
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

        // without cleanup as we have below tests that can delete ingested item
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodEpisodeBaseFields() {
        generateDefaultValues4Insert(EPISODE);
        List<VodFile> episodeAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));
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

        // without cleanup as we have below tests that can delete ingested item
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodSeriesBaseFields() {
        generateDefaultValues4Insert(SERIES);
        List<VodFile> seriesAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));
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
        // without cleanup as we have below tests that can delete ingested item
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD with filled base meta fields")
    public void updateVodMediaBaseFields() {
        String coguid = getCoguidOfActiveMediaAsset(movieType);
        generateDefaultValues4Update(true, MOVIE);
        IngestVodUtils.VodData vodData = getVodData(MOVIE, new ArrayList<>());

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
                .replaceAll("</doubles>", "")
                // to remove thumb
                .replaceAll("<thumb url=\"" + DEFAULT_THUMB + "\"/>", "");
        Response resp = getResponseBodyFromIngestVod(updateRequest);
        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("OK");

        AssetService.GetAssetBuilder assetBuilder = AssetService.get(String.valueOf(movie.getId()), AssetReferenceType.MEDIA)
                .setKs(getAnonymousKs());
        com.kaltura.client.utils.response.base.Response<Asset> assetGetResponse = executor.executeSync(assetBuilder);
        MediaAsset asset2 = (MediaAsset)assetGetResponse.results;
        assertThat(asset2.getId()).isEqualTo(movie.getId());
        assertThat(asset2.getName()).isEqualTo(name);
        assertThat(asset2.getDescription()).isEqualTo("");
        assertThat(((MultilingualStringValue)asset2.getMetas().get(mediaTextFieldName)).getValue()).isEqualTo(textValue);
        assertThat(asset2.getMetas().get(mediaNumberFieldName)).isEqualTo(null);
        assertThat(getFormattedDate(((LongValue)asset2.getMetas().get(mediaDateFieldName)).getValue(), getTimeZone("UTC"), "MM/dd/yyyy")).isEqualTo(dateValue);
        assertThat(((BooleanValue)asset2.getMetas().get(mediaBooleanFieldName)).getValue()).isEqualTo(booleanValue);

        tags = asset2.getTags();
        entry = tags.entrySet().iterator().next();
        tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            assertThat(tagValues).contains(tagValue.getValue());
        }
        assertThat(tagsValues.size()).isEqualTo(tagsMetaMap.entrySet().iterator().next().getValue().size());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD episode with filled base meta fields")
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
    @Test(description = "update VOD series with filled base meta fields")
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
    @Test(description = "delete")
    public void deleteMovie() {
        String coguid = getCoguidOfActiveMediaAsset(movieType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "delete episode")
    public void deleteEpisode() {
        String coguid = getCoguidOfActiveMediaAsset(episodeType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "delete series")
    public void deleteSeries() {
        String coguid = getCoguidOfActiveMediaAsset(seriesType);
        checkVODDeletion(coguid);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try insert without coguid")
    public void insertWithEmptyCoguid() {
        String invalidXml = ingestInsertXml.replaceAll("co_guid=\"" + coguid4NegativeTests + "\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = ingestInsertXml.replaceAll("co_guid=\"" + coguid4NegativeTests + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try delete without coguid", priority =-1)
    public void deleteWithEmptyCoguid() {
        String invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + movie.getExternalId() + "\"", "co_guid=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");

        invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + movie.getExternalId() + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).contains("External identifier is missing");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try delete with non-existed coguid")
    public void deleteWithNonExistedCoguid() {
        int positionCoguidTag = ingestDeleteXml.indexOf("co_guid=\"");
        int positionBeginOfCoguid = ingestDeleteXml.indexOf("\"", positionCoguidTag + 1);
        int positionEndOfCoguid = ingestDeleteXml.indexOf("\"", positionBeginOfCoguid + 1);
        String coguid = ingestDeleteXml.substring(positionBeginOfCoguid + 1, positionEndOfCoguid);
        String invalidXml = ingestDeleteXml.replaceAll("co_guid=\"" + coguid + "\"", "co_guid=\"123456\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("Media Id not exist");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with empty entry_id")
    public void insertWithEmptyEntryId() {
        String invalidXml = ingestInsertXml.replaceAll("entry_id=\"entry_" + coguid4NegativeTests + "\"", "entry_id=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");

        invalidXml = ingestInsertXml.replaceAll("entry_id=\"entry_" + coguid4NegativeTests + "\"", "");
        resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("entry_id is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert inactive item")
    public void insertInactiveItem() {
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
    @Test(description = "try insert with empty isActive parameter")
    public void insertEmptyIsActive() {
        String invalidXml = ingestInsertXml.replaceAll("is_active=\"true\"", "is_active=\"\"");
        Response resp = getResponseBodyFromIngestVod(invalidXml);

        assertThat(from(resp.asString()).getString(ingestAssetStatusMessagePath)).isEqualTo("media.IsActive cannot be empty");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "try insert with empty name", priority =-1)
    public void insertWithEmptyName() {
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
    @Test(description = "try insert with invalid credentials")
    public void insertWithInvalidCredentials() {
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

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "try insert with invalid meta or tag field")
    public void insertWithInvalidMetaOrTagField() {
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

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "insert multilingual fields")
    public void insertMultiLingualFields() {
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
        // TODO: update multilingual fields
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "ingest VOD with emtpy images and files fields")
    public void insertVodMediaBaseEmptyImagesAndFields() {
        String suffix = "123";
        String ingestXmlWithEmptyFiles = ingestInsertXml
                .replaceAll("co_guid=\"" + coguid4NegativeTests + "\"", "co_guid=\"" + coguid4NegativeTests + suffix + "\"");
        String ingestXmlBeforeTransformations = ingestXmlWithEmptyFiles;
        // check empty files
        String emptyFiles = "<files>" + EMPTY_FILE_1_TAG + EMPTY_FILE_2_TAG + "</files>";
        String ingestXml = getUpdatedIngestXml(ingestXmlWithEmptyFiles, "<files>", "</files>", emptyFiles);

        Response resp = getResponseBodyFromIngestVod(ingestXml);
        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("MediaFileExternalIdMustBeUnique");

        /* TODO: Shir said that current logic should allow to ingest without any errors - that can be checked after Alon complete image update
        // check empty images
        ingestXml = ingestXmlBeforeTransformations
                .replaceAll("co_guid=\"" + coguid4NegativeTests + suffix + "\"", "co_guid=\"" + coguid4NegativeTests + suffix + "1\"");
        ingestXml = getIngestXmlWithoutFiles(ingestXml);
        String emptyImages = "<pic_ratios>" + EMPTY_IMAGE_TAG + "</pic_ratios>";
        ingestXml = getUpdatedIngestXml(ingestXml, "<pic_ratios>", "</pic_ratios>", emptyImages);

        resp = getResponseBodyFromIngestVod(ingestXml);
        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("MediaFileExternalIdMustBeUniqueMediaFileExternalIdMustBeUnique");

        // checkEmptyThumb
        ingestXml = ingestXmlBeforeTransformations
                .replaceAll("co_guid=\"" + coguid4NegativeTests + suffix + "\"", "co_guid=\"" + coguid4NegativeTests + suffix + "2\"");
        ingestXml = getIngestXmlWithoutFiles(ingestXml);
        ingestXml = getUpdatedIngestXml(ingestXml, "<thumb", "/>", EMPTY_THUMB_TAG);

        resp = getResponseBodyFromIngestVod(ingestXml);
        assertThat(from(resp.asString()).getString(ingestAssetStatusWarningMessagePath)).contains("InvalidUrlForImageMediaFileExternalIdMustBeUniqueMediaFileExternalIdMustBeUnique");*/
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with different Ppv")
    public void updateVodMediaPpv() {
        generateDefaultValues4Insert(MOVIE);
        List<VodFile> movieAssetFiles = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));
        VodData vodData = getVodData(MOVIE, movieAssetFiles);
        MediaAsset movie = insertVod(vodData, true);
        String ingestRequest = ingestXmlRequest;

        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getDescription()).isEqualTo(description);
        checkFiles(movieAssetFiles, movie.getId().toString());

        Household household = HouseholdUtils.createHousehold();
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

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD images")
    public void updateImages() {
        // insert vod
        generateDefaultValues4Insert(MOVIE);
        VodData vodData = getVodData(MOVIE, movieAssetFiles);
        MediaAsset mediaAsset = insertVod(vodData, true);

        // get list of original images
        List<MediaImage> originalImages = mediaAsset.getImages();
        assertThat(originalImages.size()).isEqualTo(3);

        // update vod images - enter 2 new images
        List<String> newRatios = Arrays.asList("2:1", "2:3");
        String fakeImageUrl = "https://picsum.photos/200/300/?random";

        VodData updateVodData = new VodData()
                .thumbUrl(fakeImageUrl)
                .thumbRatios(newRatios);
        mediaAsset = updateVod(mediaAsset.getExternalId(), updateVodData);

        // assert update
        List<MediaImage> images = mediaAsset.getImages();
        assertThat(images.size()).isEqualTo(5);

        List<MediaImage> newImages = images.stream()
                .filter(image -> originalImages.stream()
                        .map(MediaImage::getRatio)
                        .noneMatch(s1 -> s1.equals(image.getRatio())))
                .collect(Collectors.toList());

        newImages.forEach(image -> assertThat(image.getUrl()).isNotEmpty());
        assertThat(newImages).extracting("ratio").containsExactlyInAnyOrderElementsOf(newRatios);

        // cleanup
        deleteVod(mediaAsset.getExternalId());
    }

    @Issue("BEO-5536")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "update VOD files")
    public void updateFiles() {
        // insert vod
        List<VodFile> files = get2AssetFiles(fileTypeNames.get(0), fileTypeNames.get(1), ppvNames.get(0), ppvNames.get(1));

        generateDefaultValues4Insert(MOVIE);
        VodData vodData = getVodData(MOVIE, files);

        MediaAsset mediaAsset = insertVod(vodData, true);

        // update vod images
        long e = getEpoch();
        String r = getRandomValue();

        String coguid1 = "file_1_" + e + "_" + r;
        String coguid2 = "file_2_" + e + "_" + r;

        files.get(0).coguid(coguid1).assetDuration("5");
        files.get(1).coguid(coguid2).assetDuration("5");

        VodData updateVodData = new VodData()
                .assetFiles(files);
        List<MediaFile> mediaFiles = updateVod(mediaAsset.getExternalId(), updateVodData).getMediaFiles();

        // assert update
        assertThat(mediaFiles.size()).isEqualTo(2);
        mediaFiles.forEach(file -> assertThat(file.getDuration()).isEqualTo(5));
        assertThat(mediaFiles).extracting("externalId").containsExactlyInAnyOrder(coguid1, coguid2);

        // cleanup
        deleteVod(mediaAsset.getExternalId());
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
    private void checkFiles(List<VodFile> ingestAssetFiles, String assetId) {
        boolean isFileWasFound = false;

        AssetService.GetAssetBuilder assetBuilder = AssetService.get(assetId, AssetReferenceType.MEDIA).setKs(getAnonymousKs());
        com.kaltura.client.utils.response.base.Response<Asset> assetGetResponse = executor.executeSync(assetBuilder);
        List<MediaFile> getMediaFiles = assetGetResponse.results.getMediaFiles();

        for (VodFile ingestFile: ingestAssetFiles) {
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
}