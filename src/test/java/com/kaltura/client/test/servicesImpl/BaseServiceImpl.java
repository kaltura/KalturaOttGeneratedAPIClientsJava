package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.utils.request.RequestBuilder;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class BaseServiceImpl {

    protected static final AtomicBoolean done = new AtomicBoolean(false);

    public static Response executeRequest(Client client, RequestBuilder requestBuilder, Response response) {
        TestAPIOkRequestsExecutor.getExecutor().queue(requestBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return response;
    }
}
