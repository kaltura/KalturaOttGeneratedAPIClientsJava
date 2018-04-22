package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdQuotaService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdQuota;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class HouseholdQuotaServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdQuota> householdQuotaResponse;

    // get
    public static Response<HouseholdQuota> get(Client client) {
        HouseholdQuotaService.GetHouseholdQuotaBuilder getHouseholdQuotaBuilder = HouseholdQuotaService.get()
                .setCompletion((ApiCompletion<HouseholdQuota>) result -> {
                    householdQuotaResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getHouseholdQuotaBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdQuotaResponse;
    }
}
