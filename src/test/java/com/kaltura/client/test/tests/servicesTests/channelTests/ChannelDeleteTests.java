package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.servicesImpl.ChannelServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.types.Channel;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelDeleteTests extends BaseTest {

    private Client client;
    private Channel channel = new Channel();
    private String channelName = "Channel_12345";
    private String Description = "description of channel";
    private Boolean isActive = true;
    private String filterExpression;
    private int channelId;


    @BeforeClass
    private void get_tests_before_class() {
    }

    @Description("channel/action/delete")
    @Test
    private void DeleteChannel() {
        filterExpression = "name ~ 'movie'";
        client = getClient(getManagerKs());
        channel = ChannelUtils.addChannel(channelName, Description, isActive, filterExpression, AssetOrderBy.LIKES_DESC, null, null);

        // channel/action/add
        Response<Channel> channelResponse = ChannelServiceImpl.add(client, channel);

        channelId = Math.toIntExact(channelResponse.results.getId());

        // channel/action/delete
        Response<Boolean> deleteResponse = ChannelServiceImpl.delete(client, Math.toIntExact(channelId));
        assertThat(deleteResponse.results.booleanValue()).isTrue();

        // channel/action/get - verify channel wasn't found
        Response<Channel> getResponse = ChannelServiceImpl.get(client, channelId);
        assertThat(getResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500007).getCode());
    }
}
