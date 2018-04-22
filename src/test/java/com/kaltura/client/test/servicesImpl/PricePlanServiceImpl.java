package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PricePlanService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PricePlan;
import com.kaltura.client.types.PricePlanFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PricePlanService.*;
import static org.awaitility.Awaitility.await;

public class PricePlanServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<PricePlan>> pricePlanListResponse;
    private static Response<PricePlan> pricePlanResponse;

    // list
    public static Response<ListResponse<PricePlan>> list(Client client, @Nullable PricePlanFilter pricePlanFilter) {
        ListPricePlanBuilder listPricePlanBuilder = PricePlanService.list(pricePlanFilter)
                .setCompletion((ApiCompletion<ListResponse<PricePlan>>) result -> {
                    pricePlanListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listPricePlanBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return pricePlanListResponse;
    }

    // update
    public static Response<PricePlan> update(Client client, long id, PricePlan pricePlan) {
        UpdatePricePlanBuilder updatePricePlanBuilder = PricePlanService.update(id, pricePlan)
                .setCompletion((ApiCompletion<PricePlan>) result -> {
                    pricePlanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePricePlanBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return pricePlanResponse;
    }
}
