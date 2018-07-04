package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.KsqlBuilder;
import com.kaltura.client.types.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.tests.enums.KsqlKey.NAME;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInEpoch;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getHouseholdMasterUserKs;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.SERIES_MEDIA_TYPE;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.EpgData;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.insertEpg;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelFilterTests extends BaseTest {

    private MediaAsset asset1, asset2, asset3;
    private ProgramAsset program;
    private String masterUserKs;
    private Channel channel;


    @BeforeClass
    private void asset_list_channelFilter_before_class() {
        // ingest movie
        VodData movieData = new VodData()
                .mediaType(MOVIE_MEDIA_TYPE);
        asset1 = insertVod(movieData);

        // ingest series
        VodData seriesData = new VodData()
                .mediaType(SERIES_MEDIA_TYPE)
                .isVirtual(true);
        asset2 = insertVod(seriesData);

        // ingest episode
        VodData episodeData = new VodData()
                .mediaType(EPISODE_MEDIA_TYPE);
        asset3 = insertVod(episodeData);

        // ingest epg
        EpgData epgData = new EpgData(getSharedEpgChannelName())
                .episodesNum(1);
        program = insertEpg(epgData).get(0);

        // add assets to channel query
        String query = new KsqlBuilder()
                .openOr()
                    .equal(NAME.getValue(), program.getName())
                    .equal(NAME.getValue(), asset1.getName())
                    .equal(NAME.getValue(), asset2.getName())
                    .equal(NAME.getValue(), asset3.getName())
                .closeOr()
                .toString();

        // add channel
        channel = new Channel();
        channel.setName("channel_" + getTimeInEpoch());
        channel.description("Description of " + channel.getName());
        channel.setIsActive(true);
        channel.setFilterExpression(query); // "Free='" + channel.getName() + "'"

        channel = executor.executeSync(ChannelService.add(channel)
                .setKs(getOperatorKs())).results;

        // create household
        Household household = createHousehold();
        masterUserKs = getHouseholdMasterUserKs(household);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - vod - channelFilter - idEqual")
    @Test
    private void list_vod_assets_with_channelFilter_by_channelId() {
        // set channelFilter
        ChannelFilter filter = new ChannelFilter();
        filter.setIdEqual(Math.toIntExact(channel.getId()));

        // get list
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(4);
        assertThat(assetListResponse.getObjects()).extracting("id")
                .containsExactlyInAnyOrder(asset1.getId(), asset2.getId(), asset3.getId(), program.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - vod - channelFilter - idEqual and KSql")
    @Test
    private void list_vod_assets_with_channelFilter_by_channelId_and_ksql() {
        // build query
        String query =  new KsqlBuilder()
                .openOr()
                    .equal(NAME.getValue(), asset1.getName())
                    .equal(NAME.getValue(), program.getName())
                .closeOr()
                .toString();

        // set channelFilter
        ChannelFilter filter = new ChannelFilter();
        filter.setIdEqual(Math.toIntExact(channel.getId()));
        filter.setKSql(query);

        // get list
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(2);
        assertThat(assetListResponse.getObjects()).extracting("id")
                .containsExactlyInAnyOrder(asset1.getId(), program.getId());
    }

    @AfterClass
    private void asset_list_channelFilter_after_class() {
        // delete ingests
        deleteVod(asset1.getName());
        deleteVod(asset2.getName());
        deleteVod(asset3.getName());

        // delete channel
        executor.executeSync(ChannelService.delete(Math.toIntExact(channel.getId()))
                .setKs(getOperatorKs()));
    }

}
