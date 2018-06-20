package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Channel;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class ChannelGetTests extends BaseTest {

    private Channel channel;
    private String filterExpression;

    private final String channelName = "Channel_12345";
    private final String description = "description of channel";

    public class ChannelActionGet extends BaseTest {

        @Severity(SeverityLevel.NORMAL)
        @Description()
        @Test
        private void channelGetTests() {

        }
    }

}
