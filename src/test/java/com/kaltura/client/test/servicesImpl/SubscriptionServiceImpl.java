package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.SubscriptionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Subscription;
import com.kaltura.client.types.SubscriptionFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SubscriptionService.ListSubscriptionBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class SubscriptionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Subscription>> subscriptionListResponse;

    // list
    public static Response<ListResponse<Subscription>> list(String ks, SubscriptionFilter subscriptionFilter) {
        ListSubscriptionBuilder listSubscriptionBuilder = SubscriptionService.list(subscriptionFilter)
                .setCompletion((ApiCompletion<ListResponse<Subscription>>) result -> {
                    subscriptionListResponse = result;
                    done.set(true);
                });

        listSubscriptionBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(listSubscriptionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return subscriptionListResponse;
    }

// validateCoupon
}
