package com.kaltura.client.test.tests.servicesTests.AssetTests;

import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.MediaAsset;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.services.AssetService.*;

import org.hamcrest.collection.IsEmptyCollection;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

import static org.hamcrest.MatcherAssert.assertThat;

public class AssetListTests extends BaseTest {

    private MediaAsset asset;
    private MediaAsset asset2;
    private MediaAsset asset3;
    private String ksqlQuery;
    private AssetFilter assetFilter = null;
    private ListAssetBuilder listAssetBuilder = null;
    private Map map = new HashMap();
    private ArrayList<Long> assetList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private String tagName = "Genre";
    private String tagValue = BaseUtils.getRandomValue(tagName +"_",999999);

    @BeforeClass
    private void Asset_list_before_class() {
        // Get asset from shared asset method
        asset = BaseTest.getSharedMediaAsset();
        // Ingest asset2
        list.add(tagValue);
        map.put(tagName, list);
        asset2 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE, map);
        asset3 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE, map);

        assetList.add(asset2.getId());
        assetList.add(asset3.getId());
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


    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - not query")
    @Test
    private void listVodAssetsWithNotKsqlQuery() {
        ksqlQuery = "(and media_id != '" + asset3.getId() + "' " + tagName + " = '" + tagValue + "')";
        System.out.println(ksqlQuery + "1234");
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - like query")
    @Test
    private void listVodAssetsWithLikeKsqlQuery() {
        ksqlQuery = "" + tagName + " ~ '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        List<Asset> assets = executor.executeSync(listAssetBuilder).results.getObjects();
        assertThat(assets.size()).isEqualTo(2);

        List<Long> assetIds = assets.stream().map(Asset::getId).collect(Collectors.toList());
        assertThat(assetIds).contains(asset3.getId(),asset2.getId());
        assertThat(assetIds).doesNotContain(asset.getId());
    }

    // TODO - complete test after ingest util was updated
    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {

    }


}
