package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.enums.ChannelOrderBy;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.types.Channel;
import com.kaltura.client.types.ChannelOrder;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.ChannelService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInLocalTime;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelDeleteTests extends BaseTest {

    private Channel channel;
    private int channelId;

    @Severity(SeverityLevel.CRITICAL)
    @Description("channel/action/delete")
    @Test
    private void DeleteChannel() {
        String channelName = "Channel_" + getEpochInLocalTime();
        String description = "description of " + channelName;
        String ksqlExpression = "name ~ 'movie'";

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);
        channel = ChannelUtils.addDynamicChannel(channelName, description, true, ksqlExpression, channelOrder, null);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        channelId = Math.toIntExact(channelResponse.results.getId());

        // channel/action/delete
        Response<Boolean> deleteResponse = executor.executeSync(delete(channelId)
                .setKs(getManagerKs()));

        assertThat(deleteResponse.results.booleanValue()).isTrue();

        // channel/action/get - verify channel wasn't found
        Response<Channel> getResponse = executor.executeSync(get(channelId)
                .setKs(getManagerKs()));

        assertThat(getResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4064).getCode());
    }
}
