package com.kaltura.client.test.tests.featuresTests.versions.five_zero_three;

import com.kaltura.client.services.AssetStructService;
import com.kaltura.client.services.AssetStructService.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.AssetStruct;
import com.kaltura.client.types.AssetStructFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.TranslationToken;
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
        assetStructResponse = executor.executeSync(updateAssetStructBuilder.setKs(getOperatorKs()).setLanguage("*"));
        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name_upd");

        // assetStructDelete
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructFromResponse.getId());
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder.setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    @Test
    public void sandboxDelete() {
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(2381);
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder.setKs(getOperatorKs()));
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
