package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdLimitationsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdLimitationsService.*;
import static org.awaitility.Awaitility.await;

public class HouseholdLimitationsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdLimitations> householdLimitationsResponse;

    // get
    public static Response<HouseholdLimitations> get(Client client, int id) {
        GetHouseholdLimitationsBuilder getHouseholdLimitationsBuilder = HouseholdLimitationsService.get(id)
                .setCompletion((ApiCompletion<HouseholdLimitations>) result -> {
                    householdLimitationsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getHouseholdLimitationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdLimitationsResponse;
    }
}
