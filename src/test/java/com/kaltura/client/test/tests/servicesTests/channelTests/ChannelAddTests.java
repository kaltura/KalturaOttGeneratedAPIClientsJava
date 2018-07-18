package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.enums.ChannelOrderBy;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.AssetService.ListAssetBuilder;
import static com.kaltura.client.services.ChannelService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.VodData;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelAddTests extends BaseTest {

    private DynamicChannel channel;
    private String channelName;
    private String description;
    private Boolean isActive = true;
    private String ksqlExpression;
    private IntegerValue integerValue = new IntegerValue();
    private List<IntegerValue> assetTypes = new ArrayList<>();


    @BeforeClass
    private void channel_addTests_before_class() {
        channelName = "Channel_12345";
        description = "description of channel";
    }

    @Description("channel/action/add - with all asset types")
    @Test
    private void addChannel() {
        ksqlExpression = "name ~ 'movie'";
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results.getName()).isEqualTo(channelName);
    }

    @Description("channel/action/add - order by NAME_DESC")
    @Test
    private void checkOrderOfAssetsInChannel() {
        String asset1Name = "Movie_" + BaseUtils.getCurrentDateInFormat("yyMMddHHmmss");
        String asset2Name = "Episode_" + BaseUtils.getCurrentDateInFormat("yyMMddHHmmss");

        // Ingest first asset
        VodData vodData = new VodData()
                .name(asset1Name)
                .mediaType(MediaType.MOVIE);
        MediaAsset movieAsset = insertVod(vodData);

        // Ingest second asset
        VodData vodData1 = new VodData()
                .name(asset2Name)
                .mediaType(MediaType.EPISODE);
        MediaAsset episodeAsset = insertVod(vodData1);

        ksqlExpression = "(or name = '" + movieAsset.getName() + "' name = '" + episodeAsset.getName() + "')";
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.NAME_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results.getMultilingualName().get(0).getValue()).isEqualTo(channelName);

        int channelId = Math.toIntExact(channelResponse.results.getId());

        ChannelFilter channelFilter = AssetUtils.getChannelFilter(channelId, Optional.empty(), Optional.empty(), Optional.empty());

        //asset/action/list
        ListAssetBuilder listAssetBuilder = AssetService.list(channelFilter)
                .setKs(getManagerKs());
        Response<ListResponse<Asset>> listResponse = executor.executeSync(listAssetBuilder);

        assertThat(listResponse.results.getTotalCount()).isEqualTo(2);
        // Verify movie asset id returned first (because order is by name_desc)
        assertThat(listResponse.results.getObjects().get(0).getId()).isEqualTo(movieAsset.getId());

        // Cleanup - channel/action/delete
        DeleteChannelBuilder deleteChannelBuilder = delete(channelId).setKs(getManagerKs());
        executor.executeSync(deleteChannelBuilder);
    }

    @Description("channel/action/add - with invalid asset type")
    @Test
    private void addChannelWithInvalidAssetType() {
        integerValue.setValue(666);
        assetTypes.add(integerValue);
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, null, channelOrder, assetTypes);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"4020","message":"KSQL Channel media type 666 does not belong to group"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4020).getCode());
    }

    @Description("channel/action/add - mandatory channel name not provided")
    @Test
    private void addChannelWithNoName() {
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(null, description, isActive, null, channelOrder, null);

        //channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"5005","message":"KSQL Channel must have a name"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(5005).getCode());
    }

    @Description("channel/action/add - syntax error in filter expression")
    @Test
    private void addChannelWithSyntaxErrorInFilterExpression() {
        ksqlExpression = "name = 'syntax error";
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        //channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"4004","message":"Invalid expression structure"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4004).getCode());
    }
}
