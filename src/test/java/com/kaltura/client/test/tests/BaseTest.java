package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.test.helper.Helper;
import com.kaltura.client.types.APIException;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.helper.Config.API_BASE_URL;
import static com.kaltura.client.test.helper.Config.API_URL_VERSION;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    protected static ILogger logger = Logger.getLogger("java-test");
    public static Client client;
    private List<APIException> exceptions;

    @BeforeSuite
    public void setup() {
        Configuration config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);

        client = new Client(config);

        setDefaultTimeout(15, TimeUnit.SECONDS);

        exceptions = Helper.getApiExceptionList();
    }


    public APIException getAPIExceptionFromList(int code) {
        for (APIException exception : exceptions) {
            if (exception.getCode().equals(String.valueOf(code))) {
                return exception;
            }
        }
        logger.error("No such error code in the API schema");
        return null;
    }

}
