package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.types.Household;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdService.AddHouseholdBuilder;
import static com.kaltura.client.services.HouseholdService.add;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class HouseholdServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Household> householdResponse;


    //add
    public static Response<Household> addImpl(String ks, Household household) {
        AddHouseholdBuilder addHouseholdBuilder = add(household)
                .setCompletion((ApiCompletion<Household>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    householdResponse = result;
                    done.set(true);
                });

        addHouseholdBuilder.setKs(ks);
        APIOkRequestsExecutor.getExecutor().queue(addHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdResponse;
    }
}
