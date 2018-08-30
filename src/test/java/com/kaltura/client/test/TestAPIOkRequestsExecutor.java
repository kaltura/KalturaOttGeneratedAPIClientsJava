package com.kaltura.client.test;

import com.google.common.primitives.Ints;
import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.types.APIException;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.RequestBuilder;
import com.kaltura.client.utils.request.RequestElement;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import com.kaltura.client.utils.response.base.ResponseElement;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.LOG_HEADERS;
import static com.kaltura.client.test.tests.BaseTest.client;
import static com.kaltura.client.utils.ErrorElement.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;

//import static com.kaltura.client.test.tests.BaseTest.objectType2JsonValidationSchemaFile4Lists;

/**
 * @hide That class allows to validate json schemas of responses
 */
//
public class TestAPIOkRequestsExecutor extends APIOkRequestsExecutor {

    private static final String TAG = "TestAPIOkRequestsExecutor";

    private static ILogger logger = Logger.getLogger(TAG);
    private static TestAPIOkRequestsExecutor executor;

    private final int[] genericErrors = {
            ConnectionError.getCode(),
            BadRequestError.getCode(),
            GeneralError.getCode(),
            NotFound.getCode(),
            LoadError.getCode(),
            ServiceUnavailableError.getCode(),
            SessionError.getCode()
    };

    private TestAPIOkRequestsExecutor() {
    }

    public static TestAPIOkRequestsExecutor getExecutor() {
        if (executor == null) {
            executor = new TestAPIOkRequestsExecutor();
        }
        return executor;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected ResponseElement onGotResponse(okhttp3.Response okhttpResponse, RequestElement action) {
        ResponseElement responseElement = super.onGotResponse(okhttpResponse, action);
//        logger.debug("response body:\n" + responseElement.getResponse()); // was found in base class

        if (LOG_HEADERS) {
            logger.debug("Session: " + okhttpResponse.headers().get("X-Kaltura-Session"));
        }

        if (responseElement.isSuccess()) {
            Response response = action.parseResponse(responseElement);

            if (response.results != null && response.isSuccess()) {
                String s1 = "schemas/";
                String s2 = response.results.getClass().getSimpleName();
                String s3 = ".json";

                // if returned list empty or greater than 'MAX_OBJECTS_AT_LIST_RESPONSE' skip schema validation
                if (s2.equals("ListResponse")) {
                    com.kaltura.client.utils.response.base.Response<ListResponse> listResponse = response;
                    if (listResponse.results.getTotalCount() == 0 ||
                            listResponse.results.getTotalCount() > MAX_OBJECTS_AT_LIST_RESPONSE) {
                        return responseElement;
                    }
                    /* TODO: COMPLETE PART OF MAX TASK RELATED JSON VALIDATION
                    //choose proper json validation schema by object type from response
                    String objectType = getObjectType(responseElement.getResponse());
                    if (StringUtils.isNotEmpty(objectType)) {
                        s2 = objectType2JsonValidationSchemaFile4Lists.get(objectType);
                        s3 = "";
                    } else {
                        logger.error("objectType in list wasn't recognized");
                        return responseElement;
                    }*/
                }

                String schema = s1 + s2 + s3;
                // TODO: COMPLETE PART OF MAX TASK RELATED JSON VALIDATION Logger.getLogger(TestAPIOkRequestsExecutor.class).debug(schema + " schema");
                Logger.getLogger(TestAPIOkRequestsExecutor.class).debug(s2 + " schema");
                    /*SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    Date date = new Date();
                    System.out.println("BEFORE VALIDATION: " + formatter.format(date));*/
                assertThat(responseElement.getResponse(), matchesJsonSchemaInClasspath(schema));
                    /*date = new Date();
                    System.out.println("AFTER VALIDATION: " + formatter.format(date));*/

                if ("true".equals(getProperty(WRITE_REGRESSION_LOGS))) {
                    String serviceMethod = action.getUrl().split("service")[1];
                    String kalturaSession = okhttpResponse.headers().get("X-Kaltura-Session");
                    writeLogs(serviceMethod, kalturaSession);
                }
            }
        }
        return responseElement;
    }

    /* TODO: COMPLETE PART OF MAX TASK RELATED JSON VALIDATION
    private String getObjectType(String response) {
        JsonParser jsonParser = new JsonParser();
        JsonElement objectType = jsonParser.parse(response)
                .getAsJsonObject().get("result")
                .getAsJsonObject().get("objectType");
        return objectType.getAsString();
    }*/

    private void writeLogs(String serviceMethod, String kalturaSession) {
        File logFile = new File(getProperty(LOGS_DIR) + getProperty(REGRESSION_LOGS_FILE));

        try {
            FileUtils.writeStringToFile(logFile, serviceMethod + ":" + kalturaSession + "\n", Charset.forName("UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> Response<T> executeSync(RequestBuilder<T, ?, ?> requestBuilder) {
        SyncExecutor<T> syncExecutor = new SyncExecutor<>();
        return syncExecutor.exec(requestBuilder);
    }

    public class SyncExecutor<T> {
        private AtomicBoolean done = new AtomicBoolean(false);
        private Response<T> response;

        public Response<T> exec(RequestBuilder<T, ?, ?> requestBuilder) {
            requestBuilder
                    .setCompletion((ApiCompletion<T>) result -> {
                        response = result;
                        done.set(true);
                    });

            queue(requestBuilder.build(client));
            await().untilTrue(done);
            done.set(false);

            APIException error = response.error;
            if (error != null) {
                int errorCode = Integer.parseInt(error.getCode());
                if (Ints.asList(genericErrors).contains(errorCode)) {
                    logger.error(error.getMessage() + " : " + error.getCode());
                    try { throw error; } catch (APIException e) { e.printStackTrace(); }
                }
            }

            return response;
        }
    }
}
