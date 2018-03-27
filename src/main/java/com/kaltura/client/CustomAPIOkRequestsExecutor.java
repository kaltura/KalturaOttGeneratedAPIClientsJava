package com.kaltura.client;

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
public class CustomAPIOkRequestsExecutor extends APIOkRequestsExecutor {


    public static final String TAG = "CustomAPIOkRequestsExecutor";
    private static ILogger logger = Logger.getLogger(TAG);
    protected static CustomAPIOkRequestsExecutor self;

    public static String fullResponseAsString = "";

    public static CustomAPIOkRequestsExecutor getExecutor() {
        if (self == null) {
            self = new CustomAPIOkRequestsExecutor();
        }
        return self;
    }

    @SuppressWarnings("rawtypes")
    @Override
	protected ResponseElement onGotResponse(Response response, RequestElement action) {
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
