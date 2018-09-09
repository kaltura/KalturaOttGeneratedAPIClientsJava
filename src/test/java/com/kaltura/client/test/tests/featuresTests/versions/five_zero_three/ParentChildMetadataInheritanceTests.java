package com.kaltura.client.test.tests.featuresTests.versions.five_zero_three;

import com.kaltura.client.services.AssetStructMetaService;
import com.kaltura.client.services.AssetStructMetaService.*;
import com.kaltura.client.services.AssetStructService;
import com.kaltura.client.services.AssetStructService.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5423
 */
@Test(groups = { "opc", "Parent children metadata inheritance" })
public class ParentChildMetadataInheritanceTests extends BaseTest {

    @BeforeClass()
    public void setUp() {
    }

    @Test
    public void sandbox() {
        String prefix = "MaxTest_assetStruct_";
        // added to play with methods that are going to be checked

        // assetStructList
        AssetStructFilter filter = new AssetStructFilter();
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(assetStructListResponse.results.getTotalCount()).isGreaterThan(0);
        String metaIds = assetStructListResponse.results.getObjects().get(0).getMetaIds();
        Long assetStructId = assetStructListResponse.results.getObjects().get(0).getId();

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
        AssetStruct assetStruct = getAssetStruct(prefix, "eng");
        assetStruct.setMetaIds(metaIds);
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

    private AssetStructMeta getAssetStructMeta(String defaultIngestValue, String ingestReferencePath,
                                               boolean isProtectFromIngest, boolean isInherited) {
        AssetStructMeta assetStructMeta = new AssetStructMeta();
        assetStructMeta.setDefaultIngestValue(defaultIngestValue);
        assetStructMeta.setIngestReferencePath(ingestReferencePath);
        assetStructMeta.setProtectFromIngest(isProtectFromIngest);
        // TODO: update library to have options update it
        //assetStructMeta.setIsInherited(isInherited);
        return assetStructMeta;
    }

    @Test
    public void sandboxDelete() {
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(2381);
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    AssetStruct getAssetStruct(String prefix, String language) {
        AssetStruct assetStruct = new AssetStruct();
        assetStruct.setSystemName(prefix + "_System_name");
        List<TranslationToken> translationTokens = new ArrayList<>();
        TranslationToken translationToken = new TranslationToken();
        translationToken.setValue(prefix + "_multiLingualName");
        translationToken.setLanguage(language);
        translationTokens.add(translationToken);
        assetStruct.setMultilingualName(translationTokens);
        assetStruct.setIsProtected(false);
        assetStruct.setMetaIds("");
        return assetStruct;
    }

    @AfterClass()
    public void tearDown() {
    }
}
