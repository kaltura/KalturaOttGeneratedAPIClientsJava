package com.kaltura.client.test.tests.featuresTests.versions.five_zero_three;

import com.kaltura.client.services.*;
import com.kaltura.client.services.AssetStructService.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Link;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static com.kaltura.client.test.utils.AssetStructMetaUtils.loadAssetStructMeta;
import static com.kaltura.client.test.utils.AssetStructUtils.getAssetStruct;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5423
 */
@Link(name = "Parent Child Metadata Inheritance", url = "BEO-5423")
@Test(groups = { "opc", "Parent Child Metadata Inheritance" })
public class ParentChildMetadataInheritanceTests extends BaseTest {

    private String metaIds;
    private Response<ListResponse<AssetStruct>> sharedAssetStructListResponse;
    private AssetStruct sharedAssetStruct1, sharedAssetStruct2;
    private AssetStructMeta sharedMetaString1, sharedMetaString2, sharedMetaNumber1, sharedMetaNumber2;
    private AssetStructMeta sharedMetaDate1, sharedMetaDate2, sharedMetaBoolean1, sharedMetaBoolean2;
    private String sharedBasicMetaIds;

    @BeforeClass()
    public void setUp() {
        // TODO: check if assetStructList be useful at the end
        // assetStructList to fill default metaIds
//        AssetStructFilter filter = new AssetStructFilter();
//        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
//        sharedAssetStructListResponse = executor.executeSync(listAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(sharedAssetStructListResponse.results.getTotalCount()).isGreaterThan(0);
//        metaIds = sharedAssetStructListResponse.results.getObjects().get(0).getMetaIds();

        // identify shared assetStructMetas
        List<String> assetStructNames = DBUtils.getAllAssetStructMetas("", 2);
        sharedMetaString1 = loadAssetStructMeta(assetStructNames.get(0));
        sharedMetaString2 = loadAssetStructMeta(assetStructNames.get(1));
        sharedMetaNumber1 = loadAssetStructMeta(assetStructNames.get(2));
        sharedMetaNumber2 = loadAssetStructMeta(assetStructNames.get(3));
        sharedMetaDate1 = loadAssetStructMeta(assetStructNames.get(4));
        sharedMetaDate2 = loadAssetStructMeta(assetStructNames.get(5));
        sharedMetaBoolean1 = loadAssetStructMeta(assetStructNames.get(6));
        sharedMetaBoolean2 = loadAssetStructMeta(assetStructNames.get(7));
        // TODO: ask Lior why ONLY these ids are obvious when DB has more basic metas?
        sharedBasicMetaIds = "58,629,1482,1493,1494,1495,1496,1497,566";//loadBasicAssetStructMetaId();

        // create shared assetStruct1
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        sharedAssetStruct1 = createAssetStruct(prefix, metaIds);

        // create shared assetStruct2
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        sharedAssetStruct2 = createAssetStruct(prefix, metaIds);
    }

    @Test
    public void testAssetStructListSecurity() {
        // assetStructList Master
        AssetStructFilter filter = new AssetStructFilter();
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(SharedHousehold.getSharedMasterUserKs()));
        assertThat(assetStructListResponse.error.getMessage()).isEqualToIgnoringCase("Service Forbidden");

        // assetStructList Operator
        filter = new AssetStructFilter();
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(assetStructListResponse.results).isNotNull();
        assertThat(assetStructListResponse.error).isNull();
    }

    @Test
    public void testCreateInheritance() {
        // create assetStruct1
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStruct1 = createAssetStruct(prefix, metaIds);
        // create assetStruct2
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStruct2 = createAssetStruct(prefix, metaIds);

        // set inheritance
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStruct2);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStruct1.getId(),
                sharedMetaString1.getMetaId(), sharedMetaString2.getMetaId());
        updateAssetStruct(assetStruct2.getId(), assetStruct2Update);

        // check parent doesn't have changes in related inheritance fields
        AssetStructFilter filter = new AssetStructFilter();
        filter.setIdIn(assetStruct1.getId().toString());
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(0L);
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(0L);
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(0L);

        // check children asset struct has changes
        filter.setIdIn(assetStruct2.getId().toString());
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStruct1.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // remove assetStructs
        deleteAssetStruct(assetStruct2.getId()); // firstly should be deleted children
        deleteAssetStruct(assetStruct1.getId());
    }

    AssetStruct copyAssetStructObject(AssetStruct assetStruct2Copy) {
        AssetStruct result = new AssetStruct();
        result.setConnectedParentMetaId(assetStruct2Copy.getConnectedParentMetaId());
        result.setConnectingMetaId(assetStruct2Copy.getConnectingMetaId());
        result.setParentId(assetStruct2Copy.getParentId());
        result.setMetaIds(assetStruct2Copy.getMetaIds());
        result.setSystemName(assetStruct2Copy.getSystemName());
        result.setIsProtected(assetStruct2Copy.getIsProtected());
        result.setPluralName(assetStruct2Copy.getPluralName());
        result.setMultilingualName(assetStruct2Copy.getMultilingualName());
        result.setFeatures(assetStruct2Copy.getFeatures());

        return result;
    }

    void setInheritanceFieldsInAssetStruct(AssetStruct assetStruct, Long parentAssetStructId,
                                           Long connectedParentMetaId, Long connectingMetaId) {
        assetStruct.setParentId(parentAssetStructId);
        assetStruct.setConnectedParentMetaId(connectedParentMetaId);
        assetStruct.setConnectingMetaId(connectingMetaId);
    }

    void updateAssetStruct(Long assetStructId, AssetStruct updatedAssetStruct) {
        UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(
                assetStructId, updatedAssetStruct);
        Response<AssetStruct> assetStructResponse = executor.executeSync(updateAssetStructBuilder
                        .setKs(getOperatorKs()).setLanguage("*"));
        assertThat(assetStructResponse.results).isNotNull();
    }

    // TODO: Check if it used after implementation
    private List<AssetStruct> loadAssetStructsWithoutHierarchy(int countOfAssetStructs, List<AssetStruct> listOfAssetStructs) {
        List<AssetStruct> result = new ArrayList<>();
        for (AssetStruct assetStruct: listOfAssetStructs) {
            if ((result.size() < countOfAssetStructs) && (assetStruct.getParentId() == 0)) {
                result.add(assetStruct);
            }
        }
        return result;
    }

    // added to play with methods that are going to be checked
//    @Test
//    public void sandbox() {
//        String prefix = "MaxTest_assetStruct_";
//
//        Long assetStructId = sharedAssetStructListResponse.results.getObjects().get(0).getId();
//
//        AssetStructMetaFilter assetStructMetaFilter = new AssetStructMetaFilter();
//        assetStructMetaFilter.setAssetStructIdEqual(assetStructId);
//        ListAssetStructMetaBuilder listAssetStructMetaBuilder = AssetStructMetaService.list(assetStructMetaFilter);
//        Response<ListResponse<AssetStructMeta>> listAssetMetaStructResponse = executor.executeSync(listAssetStructMetaBuilder
//                .setKs(getOperatorKs()));
//        assertThat(listAssetMetaStructResponse.results.getTotalCount()).isGreaterThan(0);
//        Long metaId = listAssetMetaStructResponse.results.getObjects().get(0).getMetaId();
//
//        String defaultIngestValue = prefix + "_defaultIngestValue";
//        String ingestReferencePath = prefix + "ingestReferencePath";
//        boolean isProtectFromIngest = false;
//        boolean isInherited = true;
//        AssetStructMeta assetStructMeta = getAssetStructMeta(defaultIngestValue,
//                ingestReferencePath, isProtectFromIngest, isInherited);
//        UpdateAssetStructMetaBuilder updateAssetStructMetaBuilder =
//                new UpdateAssetStructMetaBuilder(assetStructId, metaId, assetStructMeta);
//        Response<AssetStructMeta> assetStructMetaResponse = executor.executeSync(updateAssetStructMetaBuilder
//                .setKs(getOperatorKs()));
//        assertThat(assetStructMetaResponse.results.getAssetStructId()).isEqualTo(assetStructId);
//        assertThat(assetStructMetaResponse.results.getMetaId()).isEqualTo(metaId);
//        assertThat(assetStructMetaResponse.results.getDefaultIngestValue()).isEqualToIgnoringCase(defaultIngestValue);
//        assertThat(assetStructMetaResponse.results.getIngestReferencePath()).isEqualToIgnoringCase(ingestReferencePath);
//        assertThat(assetStructMetaResponse.results.getProtectFromIngest()).isEqualTo(isProtectFromIngest);
//        //TODO: update library to have options compare it
//        //assertThat(assetStructMetaResponse.results.getInherited()).isEqualTo(isInherited);
//
//        // assetStructAdd
//        AssetStruct assetStruct = getAssetStruct(prefix, "eng", false, metaIds, null, null, null);
//        AddAssetStructBuilder addAssetStructBuilder = AssetStructService.add(assetStruct);
//        Response<AssetStruct> assetStructResponse = executor.executeSync(addAssetStructBuilder
//                .setKs(getOperatorKs()).setLanguage("*"));
//        AssetStruct assetStructFromResponse = assetStructResponse.results;
//        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name");
//
//        assetStruct.setSystemName(prefix + "_System_name_upd");
//
//        // assetStructUpdate
//        UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(assetStructFromResponse.getId(), assetStruct);
//        assetStructResponse = executor.executeSync(updateAssetStructBuilder
//                .setKs(getOperatorKs())
//                .setLanguage("*"));
//        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name_upd");
//
//        // assetStructDelete
//        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructFromResponse.getId());
//        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
//    }

//    @Test
//    public void sandboxRemoveMetasAndTags() {
//        IngestVodUtils.VodData vodData = getVodData(MOVIE, INSERT);
//        MediaAsset movie = insertVod(vodData, true);
//
//        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
//        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
//        // get all metas
//        Set<String> movieMetasNames = movie.getMetas().keySet();
//        int metasSizeAfterCreation = movieMetasNames.size();
//        int nonBasicMediaMetasCount = 0;
//        assertThat(metasSizeAfterCreation).isGreaterThan(0);
//
//        // get all tags
//        Set<String> movieTagsNames = movie.getTags().keySet();
//        int tagsSizeAfterCreation = movieTagsNames.size();
//        assertThat(tagsSizeAfterCreation).isGreaterThan(0);
//
//        // get all metas and tags info to remove them from media
//        List<String> metaIds = new ArrayList<>();
//        int metaId;
//        for (String metaName: movieMetasNames) {
//            metaId = getMetaIdByName(metaName, false);
//            if (metaId !=-1) {
//                metaIds.add(String.valueOf(metaId));
//                nonBasicMediaMetasCount++;
//            }
//        }
//        for (String metaName: movieTagsNames) {
//            metaId = getMetaIdByName(metaName, true);
//            if (metaId !=-1) {
//                metaIds.add(String.valueOf(metaId));
//            }
//        }
//        String metaIdsIn = String.join(",", metaIds);
//
//        // removeMetasAndTags
//        RemoveMetasAndTagsAssetBuilder removeMetasAndTagsAssetBuilder =
//                AssetService.removeMetasAndTags(movie.getId(), AssetReferenceType.MEDIA, metaIdsIn);
//        Response<Boolean> booleanResponse = executor.executeSync(removeMetasAndTagsAssetBuilder
//                .setKs(getOperatorKs()));
//        assertThat(booleanResponse.results).isEqualTo(true);
//
//        // check results
//        GetAssetBuilder getAssetBuilder = AssetService.get(movie.getId().toString(), AssetReferenceType.MEDIA);
//        Response<Asset> assetResponse = executor.executeSync(getAssetBuilder
//                .setKs(getOperatorKs()));
//        movie = (MediaAsset) assetResponse.results;
//
//        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
//        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
//        assertThat(movie.getMetas().keySet().size()).isEqualTo(metasSizeAfterCreation - nonBasicMediaMetasCount);
//        assertThat(movie.getTags().keySet().size()).isEqualTo(0);
//
//        // cleanup
//        deleteVod(movie.getExternalId());
//    }

//    @Test
//    public void sandboxDelete() {
//        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(2381);
//        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
//    }

    @AfterClass()
    public void tearDown() {
        // cleaning created shared assetStructs
        deleteAssetStruct(sharedAssetStruct1.getId());
        deleteAssetStruct(sharedAssetStruct2.getId());
    }

    void deleteAssetStruct(Long assetStructId) {
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructId);
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    AssetStruct createAssetStruct(String prefix, String metaIds) {
        AssetStruct result = getAssetStruct(prefix, "eng", false, metaIds, null,
                null, null);

        AddAssetStructBuilder addAssetStructBuilder = AssetStructService.add(result);
        Response<AssetStruct> assetStructResponse = executor.executeSync(addAssetStructBuilder
                .setKs(getOperatorKs()).setLanguage("*"));
        result = assetStructResponse.results;

        assertThat(result.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name");

        return result;
    }
}
