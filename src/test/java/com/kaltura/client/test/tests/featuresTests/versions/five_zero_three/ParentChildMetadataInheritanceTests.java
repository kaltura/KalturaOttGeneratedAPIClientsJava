package com.kaltura.client.test.tests.featuresTests.versions.five_zero_three;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.*;
import com.kaltura.client.services.AssetService.RemoveMetasAndTagsAssetBuilder;
import com.kaltura.client.services.AssetService.GetAssetBuilder;
import com.kaltura.client.services.AssetStructMetaService.ListAssetStructMetaBuilder;
import com.kaltura.client.services.AssetStructMetaService.UpdateAssetStructMetaBuilder;
import com.kaltura.client.services.AssetStructService.AddAssetStructBuilder;
import com.kaltura.client.services.AssetStructService.DeleteAssetStructBuilder;
import com.kaltura.client.services.AssetStructService.ListAssetStructBuilder;
import com.kaltura.client.services.AssetStructService.UpdateAssetStructBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Link;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static com.kaltura.client.test.tests.enums.IngestAction.INSERT;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.AssetStructMetaUtils.getAssetStructMeta;
import static com.kaltura.client.test.utils.AssetStructUtils.getAssetStruct;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMetaIdByName;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOpcUtils.getVodData;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.deleteVod;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
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

    @BeforeClass()
    public void setUp() {
        // assetStructList to fill default metaIds
        AssetStructFilter filter = new AssetStructFilter();
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        sharedAssetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(sharedAssetStructListResponse.results.getTotalCount()).isGreaterThan(0);
        metaIds = sharedAssetStructListResponse.results.getObjects().get(0).getMetaIds();
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
        List<AssetStruct> assetStructs = loadAssetStructsWithoutHierarchy(3, sharedAssetStructListResponse.results.getObjects());
        AssetStruct assetStructGrandParent = assetStructs.get(0);
        AssetStruct assetStructParent = assetStructs.get(1);
        AssetStruct assetStructChildren = assetStructs.get(2);

        /*System.out.println(assetStructGrandParent.getId());
        System.out.println(assetStructParent.getId());
        System.out.println(assetStructChildren.getId());

        System.out.println(assetStructGrandParent.getMetaIds());
        System.out.println(assetStructParent.getMetaIds());
        System.out.println(assetStructChildren.getMetaIds());*/

        // TODO: complete
        //assetStructParent.setConnectedParentMetaId();
        //UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(assetStructParent.getId(), assetStructParent);
    }

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
    @Test
    public void sandbox() {
        String prefix = "MaxTest_assetStruct_";

        Long assetStructId = sharedAssetStructListResponse.results.getObjects().get(0).getId();

        AssetStructMetaFilter assetStructMetaFilter = new AssetStructMetaFilter();
        assetStructMetaFilter.setAssetStructIdEqual(assetStructId);
        ListAssetStructMetaBuilder listAssetStructMetaBuilder = AssetStructMetaService.list(assetStructMetaFilter);
        Response<ListResponse<AssetStructMeta>> listAssetMetaStructResponse = executor.executeSync(listAssetStructMetaBuilder
                .setKs(getOperatorKs()));
        assertThat(listAssetMetaStructResponse.results.getTotalCount()).isGreaterThan(0);
        Long metaId = listAssetMetaStructResponse.results.getObjects().get(0).getMetaId();

        String defaultIngestValue = prefix + "_defaultIngestValue";
        String ingestReferencePath = prefix + "ingestReferencePath";
        boolean isProtectFromIngest = false;
        boolean isInherited = true;
        AssetStructMeta assetStructMeta = getAssetStructMeta(defaultIngestValue,
                ingestReferencePath, isProtectFromIngest, isInherited);
        UpdateAssetStructMetaBuilder updateAssetStructMetaBuilder =
                new UpdateAssetStructMetaBuilder(assetStructId, metaId, assetStructMeta);
        Response<AssetStructMeta> assetStructMetaResponse = executor.executeSync(updateAssetStructMetaBuilder
                .setKs(getOperatorKs()));
        assertThat(assetStructMetaResponse.results.getAssetStructId()).isEqualTo(assetStructId);
        assertThat(assetStructMetaResponse.results.getMetaId()).isEqualTo(metaId);
        assertThat(assetStructMetaResponse.results.getDefaultIngestValue()).isEqualToIgnoringCase(defaultIngestValue);
        assertThat(assetStructMetaResponse.results.getIngestReferencePath()).isEqualToIgnoringCase(ingestReferencePath);
        assertThat(assetStructMetaResponse.results.getProtectFromIngest()).isEqualTo(isProtectFromIngest);
        //TODO: update library to have options compare it
        //assertThat(assetStructMetaResponse.results.getInherited()).isEqualTo(isInherited);

        // assetStructAdd
        AssetStruct assetStruct = getAssetStruct(prefix, "eng", false, metaIds, null, null, null);
        AddAssetStructBuilder addAssetStructBuilder = AssetStructService.add(assetStruct);
        Response<AssetStruct> assetStructResponse = executor.executeSync(addAssetStructBuilder
                .setKs(getOperatorKs()).setLanguage("*"));
        AssetStruct assetStructFromResponse = assetStructResponse.results;
        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name");

        assetStruct.setSystemName(prefix + "_System_name_upd");

        // assetStructUpdate
        UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(assetStructFromResponse.getId(), assetStruct);
        assetStructResponse = executor.executeSync(updateAssetStructBuilder
                .setKs(getOperatorKs())
                .setLanguage("*"));
        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name_upd");

        // assetStructDelete
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructFromResponse.getId());
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    @Test
    public void sandboxRemoveMetasAndTags() {
        IngestVodUtils.VodData vodData = getVodData(MOVIE, INSERT);
        MediaAsset movie = insertVod(vodData, true);

        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
        // get all metas
        Set<String> movieMetasNames = movie.getMetas().keySet();
        int metasSizeAfterCreation = movieMetasNames.size();
        int nonBasicMediaMetasCount = 0;
        assertThat(metasSizeAfterCreation).isGreaterThan(0);

        // get all tags
        Set<String> movieTagsNames = movie.getTags().keySet();
        int tagsSizeAfterCreation = movieTagsNames.size();
        assertThat(tagsSizeAfterCreation).isGreaterThan(0);

        // get all metas and tags info to remove them from media
        List<String> metaIds = new ArrayList<>();
        int metaId;
        for (String metaName: movieMetasNames) {
            metaId = getMetaIdByName(metaName, false);
            if (metaId !=-1) {
                metaIds.add(String.valueOf(metaId));
                nonBasicMediaMetasCount++;
            }
        }
        for (String metaName: movieTagsNames) {
            metaId = getMetaIdByName(metaName, true);
            if (metaId !=-1) {
                metaIds.add(String.valueOf(metaId));
            }
        }
        String metaIdsIn = String.join(",", metaIds);

        // removeMetasAndTags
        RemoveMetasAndTagsAssetBuilder removeMetasAndTagsAssetBuilder =
                AssetService.removeMetasAndTags(movie.getId(), AssetReferenceType.MEDIA, metaIdsIn);
        Response<Boolean> booleanResponse = executor.executeSync(removeMetasAndTagsAssetBuilder
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results).isEqualTo(true);

        // check results
        GetAssetBuilder getAssetBuilder = AssetService.get(movie.getId().toString(), AssetReferenceType.MEDIA);
        Response<Asset> assetResponse = executor.executeSync(getAssetBuilder
                .setKs(getOperatorKs()));
        movie = (MediaAsset) assetResponse.results;

        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
        assertThat(movie.getMetas().keySet().size()).isEqualTo(metasSizeAfterCreation - nonBasicMediaMetasCount);
        assertThat(movie.getTags().keySet().size()).isEqualTo(0);

        // cleanup
        deleteVod(movie.getExternalId());
    }

    @Test
    public void sandboxDelete() {
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(2381);
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    @AfterClass()
    public void tearDown() {
    }
}
