package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PriceDetailsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PriceDetails;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PriceDetailsService.*;
import static org.awaitility.Awaitility.await;

public class PriceDetailsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<PriceDetails>> priceDetailsListResponse;

    // list
    public static Response<ListResponse<PriceDetails>> list(Client client) {
        ListPriceDetailsBuilder listPriceDetailsBuilder = PriceDetailsService.list()
                .setCompletion((ApiCompletion<ListResponse<PriceDetails>>) result -> {
                    priceDetailsListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listPriceDetailsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return priceDetailsListResponse;
    }
}
