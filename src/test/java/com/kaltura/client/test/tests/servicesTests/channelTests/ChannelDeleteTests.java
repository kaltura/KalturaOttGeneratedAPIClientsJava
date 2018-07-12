package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.enums.ChannelOrderBy;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.types.Channel;
import com.kaltura.client.types.ChannelOrder;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.ChannelService.GetChannelBuilder;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelDeleteTests extends BaseTest {

    private Channel channel;
    private int channelId;

    private final String channelName = "Channel_12345";
    private final String description = "description of channel";
    private final String filterExpression = "name ~ 'movie'";

    @Severity(SeverityLevel.CRITICAL)
    @Description("channel/action/delete")
    @Test
    private void DeleteChannel() {
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, true, filterExpression, channelOrder, null);

        // channel/action/add
        ChannelService.AddChannelBuilder addChannelBuilder = ChannelService.add(channel).setKs(getManagerKs());
        Response<Channel> channelResponse = executor.executeSync(addChannelBuilder);

        channelId = Math.toIntExact(channelResponse.results.getId());

        // channel/action/delete
        ChannelService.DeleteChannelBuilder deleteChannelBuilder = ChannelService.delete(channelId).setKs(getManagerKs());
        Response<Boolean> deleteResponse = executor.executeSync(deleteChannelBuilder);

        assertThat(deleteResponse.results.booleanValue()).isTrue();

        // channel/action/get - verify channel wasn't found
        GetChannelBuilder getChannelBuilder = ChannelService.get(channelId).setKs(getManagerKs());
        Response<Channel> getResponse = executor.executeSync(getChannelBuilder);

        assertThat(getResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500007).getCode());
    }
}
