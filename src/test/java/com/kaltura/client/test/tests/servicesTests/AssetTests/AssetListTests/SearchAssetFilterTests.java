package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
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

import static com.kaltura.client.services.AssetService.ListAssetBuilder;
import static com.kaltura.client.test.Properties.MOVIE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.MOVIE_MEDIA_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchAssetFilterTests extends BaseTest {

    private MediaAsset asset;
    private MediaAsset asset2;
    private MediaAsset asset3;

    private ProgramAsset program;
    private ProgramAsset program2;

    private String ksqlQuery;
    private AssetFilter assetFilter = null;
    private ListAssetBuilder listAssetBuilder = null;
    private HashMap<String, List<String>> map = new HashMap<>();
    private ArrayList<String> list = new ArrayList<>();
    private String tagName = "Genre";
    private String tagValue = BaseUtils.getRandomValue(tagName + "_", 999999);
    private String epgChannelName = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(0).getString("name");
    private String epgChannelName2 = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(1).getString("name");

    @BeforeClass
    private void Asset_list_before_class() {
        // Get asset from shared asset method
        list.add(tagValue);
        map.put(tagName, list);

        asset = IngestVodUtils.ingestVOD(MOVIE_MEDIA_TYPE);
        asset2 = IngestVodUtils.ingestVOD(MOVIE_MEDIA_TYPE, map, String.valueOf(BaseUtils.getTimeInDate(-100)));
        asset3 = IngestVodUtils.ingestVOD(EPISODE_MEDIA_TYPE, map, String.valueOf(BaseUtils.getTimeInDate(-10)));
        program = IngestEpgUtils.ingestEPG(epgChannelName, 1).get(0);
        program2 = IngestEpgUtils.ingestEPG(epgChannelName2, 1).get(0);
    }

    // VOD
    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - name equal query")
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
    @Description("asset/action/list - VOD - id equal query")
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
    @Description("asset/action/list - VOD - OR query")
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
    @Description("asset/action/list - VOD - AND query")
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
    @Description("asset/action/list - VOD - not query")
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
    @Description("asset/action/list - VOD - like query")
    @Test
    private void listVodAssetsWithLikeKsqlQuery() {
        ksqlQuery = "" + tagName + " ~ '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        List<Asset> assets = executor.executeSync(listAssetBuilder).results.getObjects();

        assertThat(assets).isNotNull();
        assertThat(assets.size()).isEqualTo(2);
        assertThat(assets).extracting("id").contains(asset3.getId(), asset2.getId()).doesNotContain(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {
        ksqlQuery = "" + tagName + " ^ '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        List<Asset> assets = executor.executeSync(listAssetBuilder).results.getObjects();

        assertThat(assets).isNotNull().as("assets list");
        assertThat(assets.size()).isEqualTo(2);
        assertThat(assets).extracting("id").contains(asset3.getId(), asset2.getId()).doesNotContain(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - filtered by type (Movie)")
    @Test
    private void listVodAssetsFilteredByType() {
        ksqlQuery = "" + tagName + " = '" + tagValue + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, getProperty(MOVIE_MEDIA_TYPE_ID), null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results).isNotNull();
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - order by VIEWS")
    @Test
    private void OrderVodAssetsByViews() {
        AssetUtils.addViewsToAsset(asset.getId(), 3, AssetType.MEDIA);
        AssetUtils.addViewsToAsset(asset2.getId(), 2, AssetType.MEDIA);
        AssetUtils.addViewsToAsset(asset3.getId(), 1, AssetType.MEDIA);


        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.VIEWS_DESC.getValue());

        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());
    }

    //TODO - Enable test after fixing updateVodName method
    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by NAME (DESC/ASC")
    @Test(enabled = false)
    private void OrderVodAssetsByName() {
        IngestVodUtils.updateVODName(asset, "AAA");
        IngestVodUtils.updateVODName(asset2, "BBB");
        IngestVodUtils.updateVODName(asset3, "CCC");

        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.NAME_ASC.getValue());

        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());

        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.NAME_DESC.getValue());

        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by LIKES")
    @Test
    private void orderVodAssetsByLikes() {
        AssetUtils.addLikesToAsset(asset3.getId(), 3, AssetType.MEDIA);
        AssetUtils.addLikesToAsset(asset2.getId(), 2, AssetType.MEDIA);
        AssetUtils.addLikesToAsset(asset.getId(), 1, AssetType.MEDIA);

        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.LIKES_DESC.getValue());

        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by (num of) VOTES and RATING")
    @Test
    private void orderVodAssetsByVotesAndRating() {
        AssetUtils.addVotesToAsset(asset2.getId(), 2, AssetType.MEDIA, 1);
        AssetUtils.addVotesToAsset(asset3.getId(), 1, AssetType.MEDIA, 5);

        // Order by number of votes (highest to lowest)
        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.VOTES_DESC.getValue());
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());

        // Order by Ratings (highest to lowest)
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.RATINGS_DESC.getValue());
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by CATALOG START DATE")
    @Test
    private void orderVodAssetsByCatalogStartDate() {
        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.START_DATE_DESC.getValue());
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());

        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.START_DATE_ASC.getValue());
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());
    }

    //TODO - add test for  KalturaPersistedFilter in searchHistory class
    // EPG
    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - EPG - name equal query")
    @Test
    private void listEpgProgramWithExactKsqlQuery() {
        ksqlQuery = "name = '" + program.getName() + "'";
        assetFilter = AssetUtils.getSearchAssetFilter(ksqlQuery, null, "0", null, null,
                null, null);
        listAssetBuilder = AssetService.list(assetFilter, null)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(program.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - EPG - epg channel id equal query")
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
    @Description("asset/action/list - EPG - filter by epg channel id")
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