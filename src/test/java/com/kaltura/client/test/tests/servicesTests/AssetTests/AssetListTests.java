package com.kaltura.client.test.tests.servicesTests.AssetTests;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.MediaAsset;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Optional;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.services.AssetService.*;

public class AssetListTests extends BaseTest {

    MediaAsset assetId;
    String ksqlQuery;

    @BeforeClass
    private void Asset_list_before_class() {
        assetId = BaseTest.getSharedMediaAsset();
        ksqlQuery = "name = '" + assetId.getName() + "'";
    }

    // KalturaSearchAssetFilter
    // ******************************************

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - equal query")
    @Test
    private void listVodAssetsWithExactKsqlQuery() {
        AssetFilter assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery,null,null,null,null,
                null,null);
        ListAssetBuilder listAssetBuilder = AssetService.list(assetFilter,null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - not like query")
    @Test
    private void listVodAssetsWithNotLikeKsqlQuery() {

    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - like query")
    @Test
    private void listVodAssetsWithLikeKsqlQuery() {

    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {

    }




}
