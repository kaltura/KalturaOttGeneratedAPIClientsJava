package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class EntitlementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Entitlement>> entitlementResponse;

    // list
    public static Response<ListResponse<Entitlement>> list(String ks, EntitlementFilter filter, Optional<FilterPager> pager) {
        client.setKs(ks);
        if (pager.isPresent()) {
            // TODO: find how to work with pager
        }
        ListEntitlementBuilder productPriceBuilder = EntitlementService.list(filter)
                .setCompletion((ApiCompletion<ListResponse<Entitlement>>) result -> {
                    entitlementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementResponse;
    }
}


