package com.kaltura.client.test;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.ILogger;
import com.kaltura.client.Logger;
import com.kaltura.client.utils.ErrorElement;
import com.kaltura.client.utils.request.ExecutedRequest;
import com.kaltura.client.utils.request.RequestElement;
import com.kaltura.client.utils.response.base.ResponseElement;
import okhttp3.*;
import java.io.IOException;

/**
 * @hide
 */
// that class was implemented to get access to full response body as it's superclass APIOkRequestsExecutor doesn't allow to do it
public class TestAPIOkRequestsExecutor extends APIOkRequestsExecutor {


    public static final String TAG = "TestAPIOkRequestsExecutor";
    private static ILogger logger = Logger.getLogger(TAG);
    protected static TestAPIOkRequestsExecutor self;

    public static String fullResponseAsString = "";

    public static TestAPIOkRequestsExecutor getExecutor() {
        if (self == null) {
            self = new TestAPIOkRequestsExecutor();
        }
        return self;
    }

    @SuppressWarnings("rawtypes")
    @Override
	protected ResponseElement onGotResponse(Response response, RequestElement action) {
        logger.debug("Request Headers\n" + action.getHeaders().toString());
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

            logger.debug("response body:\n" + responseString);

            fullResponseAsString = responseString.substring(0);

            return new ExecutedRequest().requestId(requestId).response(responseString).code(response.code()).success(responseString != null);
        }
    }
}
