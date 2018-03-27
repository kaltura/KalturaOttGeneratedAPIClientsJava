package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.types.Household;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class HouseholdServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Household> householdResponse;
    private static Response<Boolean> booleanResponse;


    //add
    public static Response<Household> add(String ks, Household household) {
        AddHouseholdBuilder addHouseholdBuilder = HouseholdService.add(household)
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

    //delete
    public static Response<Boolean> delete(String ks, @Nullable int householdId) {
        DeleteHouseholdBuilder deleteHouseholdBuilder = HouseholdService.delete(householdId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        deleteHouseholdBuilder.setKs(ks);
        APIOkRequestsExecutor.getExecutor().queue(deleteHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
