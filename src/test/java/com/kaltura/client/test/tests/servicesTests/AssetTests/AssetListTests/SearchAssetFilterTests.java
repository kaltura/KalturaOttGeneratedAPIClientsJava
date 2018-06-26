package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.MetaTagOrderBy;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.AssetService.ListAssetBuilder;
import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.Properties.MOVIE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.getSharedMasterUserKs;
import static com.kaltura.client.test.utils.AssetUtils.*;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInDate;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.MOVIE_MEDIA_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchAssetFilterTests extends BaseTest {

    private MediaAsset asset;
    private MediaAsset asset2;
    private MediaAsset asset3;
    private ProgramAsset program;
    private ProgramAsset program2;
    private final String tagName = "Genre";
    private String tagValue;
    private final String metaName = "synopsis";
    private String metaValue1 = "A" + getRandomValue("_", 999999);
    private String metaValue2 = "B" + getRandomValue("_", 999999);
    private String ksqlQuery;
    private AssetFilter assetFilter;
    String masterUserKs;


    @BeforeClass
    private void asset_list_before_class() {
        // Get asset from shared asset method
        tagValue = getRandomValue(tagName + "_", 999999);

        ArrayList<String> list = new ArrayList<>();
        list.add(tagValue);

        HashMap<String, List<String>> tagMap = new HashMap<>();
        tagMap.put(tagName, list);

        HashMap<String, String> stringMetaMap1 = new HashMap<>();
        stringMetaMap1.put(metaName, metaValue1);

        HashMap<String, String> stringMetaMap2 = new HashMap<>();
        stringMetaMap2.put(metaName, metaValue2);

        JSONArray ja = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray();
        String epgChannelName = ja.getJSONObject(0).getString("name");
        String epgChannelName2 = ja.getJSONObject(1).getString("name");

        asset = IngestVodUtils.ingestVOD(MOVIE_MEDIA_TYPE);
        asset2 = IngestVodUtils.ingestVOD(MOVIE_MEDIA_TYPE, tagMap, stringMetaMap1, getTimeInDate(-100));
        asset3 = IngestVodUtils.ingestVOD(EPISODE_MEDIA_TYPE, tagMap, stringMetaMap2, getTimeInDate(-10));
        program = IngestEpgUtils.ingestEPG(epgChannelName, 1).get(0);
        program2 = IngestEpgUtils.ingestEPG(epgChannelName2, 1).get(0);

        Household household = HouseholdUtils.createHousehold(1, 1, true);
        masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household);

        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(asset.getId().intValue()), Optional.empty(), Optional.empty());

    }

    // Filter by KSQL
    // *********************

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - filter by entitled asset")
    @Test
    private void listVodAssetsByEntitled() {
        ksqlQuery = "(and (or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "') entitled_assets = 'entitled')";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(masterUserKs));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - filter by asset name")
    @Test
    private void listVodAssetsByAssetName() {
        ksqlQuery = "name = '" + asset.getName() + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Reserved key: asset/action/list - VOD - filter by media_id")
    @Test
    private void listVodAssetsByMediaId() {
        ksqlQuery = "media_id = '" + asset.getId() + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - filter by meta")
    @Test
    private void listVodAssetsByMeta() {
        ksqlQuery = "" + metaName + " = '" + metaValue1 + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Logical conjunction: asset/action/list - VOD - OR query")
    @Test
    private void listVodAssetsWithOrQuery() {
        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Logical conjunction: asset/action/list - VOD - AND query")
    @Test
    private void listVodAssetsWithAndQuery() {
        ksqlQuery = "(and media_id = '" + asset3.getId() + "' " + tagName + " = '" + tagValue + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Alpha numeric field: asset/action/list - VOD - not query")
    @Test
    private void listVodAssetsWithNotKsqlQuery() {
        ksqlQuery = "(and media_id != '" + asset3.getId() + "' " + tagName + " = '" + tagValue + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Alpha numeric field: asset/action/list - VOD - like query")
    @Test
    private void listVodAssetsWithLikeKsqlQuery() {
        ksqlQuery = "" + tagName + " ~ '" + tagValue + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        List<Asset> assets = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()))
                .results
                .getObjects();

        assertThat(assets).isNotNull();
        assertThat(assets.size()).isEqualTo(2);
        assertThat(assets).extracting("id").contains(asset3.getId(), asset2.getId()).doesNotContain(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Alpha numeric field: asset/action/list - VOD - start with query")
    @Test
    private void listVodAssetsWithStartWithKsqlQuery() {
        ksqlQuery = "" + tagName + " ^ '" + tagValue + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        List<Asset> assets = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()))
                .results
                .getObjects();

        assertThat(assets).as("assets list").isNotNull();
        assertThat(assets.size()).isEqualTo(2);
        assertThat(assets).extracting("id").contains(asset3.getId(), asset2.getId()).doesNotContain(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - filtered by type (Movie)")
    @Test
    private void listVodAssetsFilteredByType() {
        ksqlQuery = "(and " + tagName + " = '" + tagValue + "' asset_type = '" + getProperty(MOVIE_MEDIA_TYPE_ID) + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, null);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results).isNotNull();
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD - order by VIEWS")
    @Test
    private void orderVodAssetsByViews() {
        addViewsToAsset(asset.getId(), 3, AssetType.MEDIA);
        addViewsToAsset(asset2.getId(), 2, AssetType.MEDIA);
        addViewsToAsset(asset3.getId(), 1, AssetType.MEDIA);

        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.VIEWS_DESC.getValue());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by NAME (DESC/ASC")
    @Test(enabled = false)
    private void orderVodAssetsByName() {
        IngestVodUtils.updateVODName(asset, "AAA");
        IngestVodUtils.updateVODName(asset2, "BBB");
        IngestVodUtils.updateVODName(asset3, "CCC");

        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.NAME_ASC.getValue());

        ListAssetBuilder listAssetBuilder = list(assetFilter)
                .setKs(getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());

        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.NAME_DESC.getValue());

        listAssetBuilder = list(assetFilter).setKs(getSharedMasterUserKs());
        assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by LIKES")
    @Test
    private void orderVodAssetsByLikes() {
        addLikesToAsset(asset3.getId(), 3, AssetType.MEDIA);
        addLikesToAsset(asset2.getId(), 2, AssetType.MEDIA);
        addLikesToAsset(asset.getId(), 1, AssetType.MEDIA);

        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.LIKES_DESC.getValue());

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  order by (num of) VOTES and RATING")
    @Test
    private void orderVodAssetsByVotesAndRating() {
        addVotesToAsset(asset2.getId(), 2, AssetType.MEDIA, 1);
        addVotesToAsset(asset3.getId(), 1, AssetType.MEDIA, 5);

        // Order by number of votes (highest to lowest)
        ksqlQuery = "(or media_id = '" + asset.getId() + "' media_id = '" + asset2.getId() + "'media_id = '" + asset3.getId() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.VOTES_DESC.getValue());

        ListAssetBuilder listAssetBuilder = list(assetFilter)
                .setKs(getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());

        // Order by Ratings (highest to lowest)
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.RATINGS_DESC.getValue());

        listAssetBuilder = list(assetFilter).setKs(getSharedMasterUserKs());
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
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.START_DATE_DESC.getValue());

        ListAssetBuilder listAssetBuilder = list(assetFilter)
                .setKs(getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset3.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset.getId());

        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, null, null, null, AssetOrderBy.START_DATE_ASC.getValue());

        listAssetBuilder = list(assetFilter).setKs(getSharedMasterUserKs());
        assetListResponse = executor.executeSync(listAssetBuilder);

        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(2).getId()).isEqualTo(asset3.getId());
    }

    @Issue("BEO-5254")
    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - VOD -  dynamicOrderBy meta (ASC/DESC")
    @Test(enabled = false)

    private void dynamicOrderByMeta() {
        ksqlQuery = "(and (or media_id = '" + asset2.getId() + "' media_id = '" + asset3.getId() + "') asset_type = '" + getProperty(MOVIE_MEDIA_TYPE_ID) + "')";
        DynamicOrderBy dynamicOrderBy = new DynamicOrderBy();
        dynamicOrderBy.setName(metaName);
        dynamicOrderBy.setOrderBy(MetaTagOrderBy.META_ASC);
        assetFilter = getSearchAssetFilter(ksqlQuery, null, null, dynamicOrderBy, null, null, null);

        ListAssetBuilder listAssetBuilder = list(assetFilter)
                .setKs(getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(listAssetBuilder);
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset2.getId());
        assertThat(assetListResponse.results.getObjects().get(1).getId()).isEqualTo(asset3.getId());
    }


    //TODO - add test for  KalturaPersistedFilter in searchHistory class

    // EPG
    // ***************************************************
    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - EPG - name equal query")
    @Test
    private void listEpgProgramByNamr() {
        ksqlQuery = "name = '" + program.getName() + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery, null, "0", null, null, null, null);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(program.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - EPG - epg channel id equal query")
    @Test
    private void listEpgProgramByChannelId() {
        ksqlQuery = "epg_channel_id = '" + program.getEpgChannelId() + "'";
        assetFilter = getSearchAssetFilter(ksqlQuery);

        List<Asset> epgPrograms = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()))
                .results
                .getObjects();

        assertThat(epgPrograms.size()).isGreaterThan(1);
        assertThat(epgPrograms).extracting("epgChannelId").contains(program.getEpgChannelId());
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - EPG - filter by epg channel id")
    @Test
    private void listEpgProgramsFilteredByEpgChannel() {
        ksqlQuery = "(or name = '" + program.getName() + "' name = '" + program2.getName() + "')";
        assetFilter = getSearchAssetFilter(ksqlQuery, program2.getEpgChannelId().toString(), "0", null, null, null, null);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter)
                .setKs(getSharedMasterUserKs()));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(program2.getId());
    }
}