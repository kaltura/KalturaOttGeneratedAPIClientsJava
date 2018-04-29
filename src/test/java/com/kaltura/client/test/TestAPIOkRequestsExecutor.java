package com.kaltura.client.test;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.utils.request.RequestElement;
import com.kaltura.client.utils.response.base.ResponseElement;
import okhttp3.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @hide
 * That class allows to validate json schemas of responses
 */
//
public class TestAPIOkRequestsExecutor extends APIOkRequestsExecutor {

    public static final String TAG = "TestAPIOkRequestsExecutor";
    private static ILogger logger = Logger.getLogger(TAG);

    private static TestAPIOkRequestsExecutor executor;

    private TestAPIOkRequestsExecutor() {}

    public static TestAPIOkRequestsExecutor getExecutor() {
        if (executor == null) {
            executor = new TestAPIOkRequestsExecutor();
        }
        return executor;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected ResponseElement onGotResponse(Response response, RequestElement action) {
        ResponseElement responseElement = super.onGotResponse(response, action);
//        logger.debug("response body:\n" + responseElement.getResponse()); // was found in base class
        logger.debug("response headers:\n" + response.headers());

        if (responseElement.isSuccess()) {
                com.kaltura.client.utils.response.base.Response response1 = action.parseResponse(responseElement);

                if (response1.results != null && response1.isSuccess()) {
                    String s1 = "schemas/";
                    String s2 = response1.results.getClass().getSimpleName();
                    String s3 = ".json";

                    String schema = s1 + s2 + s3;
                    Logger.getLogger(TestAPIOkRequestsExecutor.class).debug(s2 + " schema");
                    /*SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    Date date = new Date();
                    System.out.println("BEFORE VALIDATION: " + formatter.format(date));*/
                    assertThat(responseElement.getResponse(), matchesJsonSchemaInClasspath(schema));
                    /*date = new Date();
                    System.out.println("AFTER VALIDATION: " + formatter.format(date));*/
                }
        }
        return responseElement;
    }
}
