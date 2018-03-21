package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.test.helper.Config;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.helper.Config.*;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    public static Client client;

    @BeforeSuite
    public void setup() {
        Configuration config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);

        client = new Client(config);

        setDefaultTimeout(15, TimeUnit.SECONDS);
    }
}
