package com.kaltura.client.test.tests.servicesTests.AssetTests;

import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.*;
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

import static com.kaltura.client.test.IngestConstants.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.Properties.MOVIE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.services.AssetService.*;

public class AssetListTests extends BaseTest {

    private MediaAsset asset;
    private MediaAsset asset2;
    private MediaAsset asset3;
    private ProgramAsset program;
    private ProgramAsset program2;

    private String ksqlQuery;
    private AssetFilter assetFilter = null;
    private ListAssetBuilder listAssetBuilder = null;
    private Map map = new HashMap();
    private ArrayList<Long> assetList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private String tagName = "Genre";
    private String tagValue = BaseUtils.getRandomValue(tagName +"_",999999);
    private  String epgChannelName = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(0).getString("name");
    private  String epgChannelName2 = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(1).getString("name");

    @BeforeClass
    private void Asset_list_before_class() {
        // Get asset from shared asset method
        asset = BaseTest.getSharedMediaAsset();
        list.add(tagValue);
        map.put(tagName, list);
        asset2 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE, map);
        asset3 = IngestUtils.ingestVOD(EPISODE_MEDIA_TYPE, map);

        assetList.add(asset2.getId());
        assetList.add(asset3.getId());

        program = IngestUtils.ingestEPG(epgChannelName,1).get(0);
        program2 = IngestUtils.ingestEPG(epgChannelName2,1).get(0);

    }

    // KalturaSearchAssetFilter
    // ******************************************

    // VOD
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
    @Description("Asset/action/list - VOD - OR query")
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

        assertThat(assets).extracting("id").contains(asset3.getId(),asset2.getId()).doesNotContain(asset.getId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {


        ksqlQuery = "" + tagName + " ^ '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        List<Asset> assets = executor.executeSync(listAssetBuilder).results.getObjects();
        assertThat(assets.size()).isEqualTo(2);

        assertThat(assets).extracting("id").contains(asset3.getId(),asset2.getId()).doesNotContain(asset.getId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - VOD - filtered by type (Movie)")
    @Test
    private void listVodAssetsFilteredByType() {

        ksqlQuery = "" + tagName + " = '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery,null , getProperty(MOVIE_MEDIA_TYPE_ID),  null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
    }

    // EPG
    // ******************************************

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - EPG - name equal query")
    @Test
    private void listEpgProgramWithExactKsqlQuery() {
        ksqlQuery = "name = '" + program.getName() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(program.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - EPG - epg channel id equal query")
    @Test
    private void listEpgProgramWithExactKsqlQuery2() {
        ksqlQuery = "epg_channel_id = '" + program.getEpgChannelId() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        List<Asset> epgPrograms = executor.executeSync(listAssetBuilder).results.getObjects();
        assertThat(epgPrograms.size()).isGreaterThan(1);

        assertThat(epgPrograms).extracting("epgChannelId").contains(program.getEpgChannelId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Asset/action/list - EPG - filter by epg channel id")
    @Test
    private void listEpgProgramsFilteredByEpgChannel() {
        ksqlQuery = "(or name = '" + program.getName() + "' name = '" + program2.getName() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, program2.getEpgChannelId().toString(), "0", null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(program2.getId());
    }
}
