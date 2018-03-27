package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.services.HouseholdUserService;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.HouseholdUserFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdUserService.*;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class HouseholdUserServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdUser> householdUserResponse;
    private static Response<ListResponse<HouseholdUser>> householdUserListResponse;


    //add
    public static Response<HouseholdUser> add(String ks, HouseholdUser householdUser) {
        AddHouseholdUserBuilder addHouseholdUserBuilder = HouseholdUserService.add(householdUser)
                .setCompletion((ApiCompletion<HouseholdUser>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    householdUserResponse = result;
                    done.set(true);
                });

        addHouseholdUserBuilder.setKs(ks);
        APIOkRequestsExecutor.getExecutor().queue(addHouseholdUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdUserResponse;
    }

    public static Response<ListResponse<HouseholdUser>> list(String ks, @Nullable HouseholdUserFilter householdUserFilter) {
        ListHouseholdUserBuilder listHouseholdUserBuilder = HouseholdUserService.list(householdUserFilter)
                .setCompletion((ApiCompletion<ListResponse<HouseholdUser>>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    householdUserListResponse = result;
                    done.set(true);
                });

        listHouseholdUserBuilder.setKs(ks);
        APIOkRequestsExecutor.getExecutor().queue(listHouseholdUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdUserListResponse;
    }
}
