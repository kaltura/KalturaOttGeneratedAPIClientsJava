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
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelGetTests extends BaseTest {

    private Client client;
    private Channel channel = new Channel();
    private String channelName = "Channel_12345";
    private String Description = "description of channel";
    private Boolean isActive = true;
    private String filterExpression;

public class ChannelActionGet extends BaseTest {

    @BeforeClass
    private void get_tests_before_class() {
    }

    @Description
    @Test
    private void channelGetTests() {

    }
}

}
