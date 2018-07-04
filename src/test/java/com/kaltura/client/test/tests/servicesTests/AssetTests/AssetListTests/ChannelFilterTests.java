package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInDate;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getHouseholdMasterUserKs;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.VodData;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelFilterTests extends BaseTest {
    private final Long maxValue = 999999L;
    private final String tagName = "Genre";
    private final String metaName = "synopsis";
    private final String metaName2 = "Short title";
    private final String metaValue1 = getRandomValue(" A_", maxValue);
    private final String metaValue2 = getRandomValue("B_", maxValue);

    private MediaAsset asset, asset2, asset3;
    private ProgramAsset program, program2;
    private String tagValue, masterUserKs;
    private AssetFilter assetFilter;
    private Channel channel;


    @BeforeClass
    private void asset_list_channelFilter_before_class() {
        // Get asset from shared asset method
        tagValue = getRandomValue(tagName + "_", 999999);

        ArrayList<String> list = new ArrayList<>();
        list.add(tagValue);

        HashMap<String, List<String>> tagMap = new HashMap<>();
        tagMap.put(tagName, list);

        HashMap<String, String> stringMetaMap1 = new HashMap<>();
        stringMetaMap1.put(metaName, metaValue1);
        stringMetaMap1.put(metaName2, metaValue1);

        HashMap<String, String> stringMetaMap2 = new HashMap<>();
        stringMetaMap2.put(metaName, metaValue2);

        JSONArray ja = getLinearAssetIdAndEpgChannelNameJsonArray();
        String epgChannelName = ja.getJSONObject(0).getString("name");
        String epgChannelName2 = ja.getJSONObject(1).getString("name");

        // ingest movie
        VodData vodData1 = new VodData()
                .mediaType(MOVIE_MEDIA_TYPE);
        System.out.println("1! " + vodData1.toString());
        asset = insertVod(vodData1);

        // ingest series
        VodData vodData2 = new VodData()
                .mediaType(SERIES_MEDIA_TYPE)
                .isVirtual(true);
        System.out.println("2! " + vodData2.toString());
        asset2 = insertVod(vodData2);

        // ingest episode
        System.out.println("3!");
        VodData vodData3 = new VodData()
                .mediaType(EPISODE_MEDIA_TYPE)
                .catalogStartDate(getTimeInDate(-10))
                .tags(tagMap)
                .strings(stringMetaMap2);
        asset3 = insertVod(vodData3);

//        // ingest epg 1
//        EpgData epgData1 = new EpgData(epgChannelName).episodesNum(1);
//        program = insertEpg(epgData1).get(0);
//
//        // ingest epg 2
//        EpgData epgData2 = new EpgData(epgChannelName2).episodesNum(1);
//        program2 = insertEpg(epgData2).get(0);

        Household household = createHousehold();
        masterUserKs = getHouseholdMasterUserKs(household);

        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(asset.getId().intValue()), Optional.empty(), Optional.empty());

        // add channel
        channel = new Channel();
        channel.setName(getRandomValue("channel_", maxValue));
        channel.description("Description of " + channel.getName());
        channel.setIsActive(true);
        channel.setFilterExpression("Free='" + channel.getName() + "'");
//        channel.setOrder(AssetOrderBy.NAME_ASC);

        channel = executor.executeSync(ChannelService.add(channel)
                .setKs(getOperatorKs())).results;
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - vod - channelFilter")
    @Test
    private void listVodAssetsByChannel() {
//        String query = new KsqlBuilder()
//                .openAnd()
//                .openOr()
//                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset.getId()))
//                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset2.getId()))
//                .closeOr()
//                .equal(ENTITLED_ASSETS.getValue(), "entitled")
//                .closeAnd()
//                .toString();

        ChannelFilter filter = new ChannelFilter();
        filter.setIdEqual(Math.toIntExact(channel.getId()));

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(filter)
                .setKs(masterUserKs));

        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(asset.getId());
    }

}
