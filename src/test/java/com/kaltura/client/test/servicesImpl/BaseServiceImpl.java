package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.utils.request.RequestBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class BaseServiceImpl {

    protected static final AtomicBoolean done = new AtomicBoolean(false);

    static void executeRequest(Client client, RequestBuilder requestBuilder) {
        TestAPIOkRequestsExecutor.getExecutor().queue(requestBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }
}
