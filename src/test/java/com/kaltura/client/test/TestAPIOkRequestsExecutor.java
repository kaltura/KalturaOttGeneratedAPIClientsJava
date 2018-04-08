package com.kaltura.client.test;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.ErrorElement;
import com.kaltura.client.utils.request.ExecutedRequest;
import com.kaltura.client.utils.request.RequestElement;
import com.kaltura.client.utils.response.base.ResponseElement;
import okhttp3.Response;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @hide
 */
// that class was implemented to get access to full response body as it's superclass APIOkRequestsExecutor doesn't allow to do it
public class TestAPIOkRequestsExecutor extends APIOkRequestsExecutor {


    public static final String TAG = "TestAPIOkRequestsExecutor";
    private static ILogger logger = Logger.getLogger(TAG);
    protected static TestAPIOkRequestsExecutor self;

    public static TestAPIOkRequestsExecutor getExecutor() {
        if (self == null) {
            self = new TestAPIOkRequestsExecutor();
        }
        return self;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected ResponseElement onGotResponse(Response response, RequestElement action) {

        // print request headers
//        logger.debug("request headers\n" + action.getHeaders().toString());

        String requestId = getRequestId(response);

        if (!response.isSuccessful()) { // in case response has failure status
            return new ExecutedRequest().requestId(requestId).error(ErrorElement.fromCode(response.code(), response.message())).success(false);

        } else {
            String responseString = null;
            try {
                responseString = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("failed to retrieve the response body!");
            }

            // print response body
            logger.debug("response body:\n" + responseString);

            // print response headers
//            logger.debug("response headers:\n" + response.headers());

            ResponseElement responseElement = new ExecutedRequest().requestId(requestId).response(responseString).code(response.code()).success(responseString != null);
            com.kaltura.client.utils.response.base.Response response1 = action.parseResponse(responseElement);

            if (response1.isSuccess()) {
                String s1 = "schemas/";
                String s3 = ".json";
                String s2 = response1.results.getClass().getSimpleName();

                if (s2.equals("ListResponse")) {
                    com.kaltura.client.utils.response.base.Response<ListResponse> listResponse = response1;
                    if (listResponse.results.getTotalCount() == 0) {
                        return null;
                    }

                    String s = listResponse.results.getObjects().get(0).getClass().getSimpleName();
                    s2 = s2 + "_" + s;
                }

                String schema = s1 + s2 + s3;

                Logger.getLogger(TestAPIOkRequestsExecutor.class).debug("Start " + s2 + " schema assertion!");
                assertThat(responseString, matchesJsonSchemaInClasspath(schema));
                Logger.getLogger(TestAPIOkRequestsExecutor.class).debug("Finish " + s2 + " schema assertion!");
            }
            return responseElement;
        }
    }
}
