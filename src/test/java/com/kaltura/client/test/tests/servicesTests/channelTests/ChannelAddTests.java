package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.test.servicesImpl.ChannelServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelAddTests extends BaseTest {

    private Client client;
    private Channel channel = new Channel();
    private String channelName;
    private String Description;
    private Boolean isActive = true;
    private String filterExpression;
    private IntegerValue integerValue = new IntegerValue();
    private List<IntegerValue> assetTypes = new ArrayList<>();


    @BeforeClass
    private void get_tests_before_class() {
        client = getClient(getManagerKs());
        channelName = "Channel_12345";
        Description = "description of channel";
    }

    @Description("channel/action/add - with all asset types")
    @Test
    private void addChannel() {
        filterExpression = "name ~ 'movie'";
        client = getClient(getManagerKs());
        channel = ChannelUtils.addChannel(channelName, Description, isActive, filterExpression, AssetOrderBy.LIKES_DESC, null, null);
        //channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);
        assertThat(channelResponse.results.getName()).isEqualTo(channelName);
    }

    // TODO
    @Description("channel/action/add - order by NAME_DESC")
    @Test
    private void checkOrderOfAssetsInChannel() {

        String asset1Name = "Movie_" + BaseUtils.getCurrentDataInFormat("yyMMddHHmmss");
        String asset2Name = "Episode_" + BaseUtils.getCurrentDataInFormat("yyMMddHHmmss");

        // Ingest first asset
        MediaAsset movieAsset = IngestUtils.ingestBasicVOD(Optional.of(asset1Name), MOVIE_MEDIA_TYPE);

        // Ingest second asset
        MediaAsset episodeAsset = IngestUtils.ingestBasicVOD(Optional.of(asset2Name), MOVIE_MEDIA_TYPE);

        filterExpression = "(or name = '" + movieAsset.getName() + "' name = '" + episodeAsset.getName() + "')";
        client = getClient(getManagerKs());
        channel = ChannelUtils.addChannel(channelName, Description, isActive, filterExpression, AssetOrderBy.NAME_DESC, null, null);

        //channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);
        assertThat(channelResponse.results.getName()).isEqualTo(channelName);

        int channelId = Math.toIntExact(channelResponse.results.getId());

        ChannelFilter channelFilter = AssetUtils.getChannelFilter(channelId, null, null, null);

        //asset/action/list
        Response<ListResponse<Asset>> listResponse = AssetServiceImpl.list(client, channelFilter, null);
        assertThat(listResponse.results.getTotalCount()).isEqualTo(2);
        // Verify movie asset id returned first (because order is by name_desc)
        assertThat(listResponse.results.getObjects().get(0).getId()).isEqualTo(movieAsset.getId());

        // Cleanup - channel/action/delete
        ChannelServiceImpl.delete(client, channelId);
    }


    @Description("channel/action/add - with invalid asset type")
    @Test
    private void addChannelWithInvalidAssetType() {
        integerValue.setValue(666);
        assetTypes.add(integerValue);
        channel = ChannelUtils.addChannel(channelName, Description, isActive, null, AssetOrderBy.LIKES_DESC, assetTypes, null);
        //channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);
        // KalturaAPIException","code":"4020","message":"KSQL Channel media type 666 does not belong to group"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4020).getCode());
    }

    @Description("channel/action/add - mandatory channel name not provided")
    @Test
    private void addChannelWithNoName() {
        channel = ChannelUtils.addChannel(null, Description, isActive, null, AssetOrderBy.LIKES_DESC, null, null);
        //channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);

        // KalturaAPIException","code":"5005","message":"KSQL Channel must have a name"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(5005).getCode());
    }

    @Description("channel/action/add - syntax error in filter expression")
    @Test
    private void addChannelWithSyntaxErrorInFilterExpression() {
        filterExpression = "name = 'syntax error";
        channel = ChannelUtils.addChannel(channelName, Description, isActive, filterExpression, AssetOrderBy.LIKES_DESC, null, null);
        //channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);

        // KalturaAPIException","code":"4004","message":"Invalid expression structure"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4004).getCode());
    }
}
