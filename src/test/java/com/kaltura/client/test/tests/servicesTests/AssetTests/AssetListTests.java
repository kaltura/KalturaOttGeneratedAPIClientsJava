package com.kaltura.client.test.tests.servicesTests.AssetTests;

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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.services.AssetService.*;

public class AssetListTests extends BaseTest {

    private MediaAsset asset;
    private MediaAsset asset2;
    private MediaAsset asset3;
    private String ksqlQuery;
    private AssetFilter assetFilter = null;
    private ListAssetBuilder listAssetBuilder = null;
    private Map map = new HashMap();
    private ArrayList<String> list = new ArrayList<>();
    private String tagName = "Genre";
    private String tagValue = "shmulik_1233";

    @BeforeClass
    private void Asset_list_before_class() {
        // Get asset from shared asset method
        asset = BaseTest.getSharedMediaAsset();
        // Ingest asset2
        list.add(tagValue);
        map.put(tagName, list);
        asset2 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE, map);
        asset3 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE, map);

    }

    // KalturaSearchAssetFilter
    // ******************************************

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - name equal query")
    @Test
    private void listVodAssetsWithExactKsqlQuery() {
        ksqlQuery = "name = '" + asset.getName() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - id equal query")
    @Test
    private void listVodAssetsWithExactKsqlQuery2() {
        ksqlQuery = "media_id = '" + asset.getId() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - or query")
    @Test
    private void listVodAssetsWithOrQuery() {
        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - AND query")
    @Test
    private void listVodAssetsWithAndQuery() {
        ksqlQuery = "(and media_id = '" + asset3.getId() + "' " + tagName + " = '" + tagValue + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
    }


    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - not query")
    @Test
    private void listVodAssetsWithNotKsqlQuery() {
        

    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - like query")
    @Test
    private void listVodAssetsWithLikeKsqlQuery() {
        ksqlQuery = "name ~ '" + asset.getName() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {

    }


}
