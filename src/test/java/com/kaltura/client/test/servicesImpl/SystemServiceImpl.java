package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SystemService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.SubscriptionSet;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SystemService.*;
import static org.awaitility.Awaitility.await;

public class SystemServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Long> longResponse;
    private static Response<String> stringResponse;
    private static Response<Boolean> booleanResponse;

    // getTime
    public static Response<Long> getTime(Client client, SubscriptionSet subscriptionSet) {
        GetTimeSystemBuilder getTimeSystemBuilder = SystemService.getTime()
                .setCompletion((ApiCompletion<Long>) result -> {
                    longResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getTimeSystemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return longResponse;
    }

    // getVersion
    public static Response<String> getTime(Client client) {
        GetVersionSystemBuilder getVersionSystemBuilder = SystemService.getVersion()
                .setCompletion((ApiCompletion<String>) result -> {
                    stringResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getVersionSystemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return stringResponse;
    }

    // ping
    public static Response<Boolean> ping(Client client) {
        PingSystemBuilder pingSystemBuilder = SystemService.ping()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(pingSystemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
