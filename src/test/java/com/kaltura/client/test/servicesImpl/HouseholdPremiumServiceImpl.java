package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdPremiumServiceService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdPremiumService;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdPremiumServiceService.*;
import static org.awaitility.Awaitility.await;

public class HouseholdPremiumServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<HouseholdPremiumService>> householdPremiumServiceListResponse;

    // list
    public static Response<ListResponse<HouseholdPremiumService>> list(Client client) {
        ListHouseholdPremiumServiceBuilder listHouseholdPremiumServiceBuilder = HouseholdPremiumServiceService.list()
                .setCompletion((ApiCompletion<ListResponse<HouseholdPremiumService>>) result -> {
                    householdPremiumServiceListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdPremiumServiceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdPremiumServiceListResponse;
    }
}
