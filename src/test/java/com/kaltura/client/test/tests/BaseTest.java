package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.test.helper.Helper;
import com.kaltura.client.test.tests.utils.Utils;
import com.kaltura.client.types.APIException;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.helper.Properties.API_BASE_URL;
import static com.kaltura.client.test.helper.Properties.API_URL_VERSION;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    protected static ILogger logger = Logger.getLogger("java-test");
    public static Client client;
    private List<APIException> exceptions;

    @BeforeSuite
    public void setup() {

        // Set client
        Configuration config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);
        client = new Client(config);

        // Set default awaitility timeout
        setDefaultTimeout(15, TimeUnit.SECONDS);

        // Get api exception list from schema xml
        exceptions = Helper.getApiExceptionList();

        // Generate shared tests data
        Utils.createHouseHold();
    }

    // Help functions
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
