package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Entitlement;
import com.kaltura.client.types.EntitlementFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class EntitlementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Entitlement>> entitlementResponse;

    // list
    public static Response<ListResponse<Entitlement>> list(String ks, EntitlementFilter filter, @Nullable FilterPager pager) {
        ListEntitlementBuilder productPriceBuilder = EntitlementService.list(filter, pager)
                .setCompletion((ApiCompletion<ListResponse<Entitlement>>) result -> {
                    entitlementResponse = result;
                    done.set(true);
                });

        productPriceBuilder.setKs(ks);
//        if (pager.isPresent()) {
//            // TODO: find how to work with pager
//        }

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementResponse;
    }
}


