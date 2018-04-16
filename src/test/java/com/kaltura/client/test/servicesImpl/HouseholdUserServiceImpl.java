package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdUserService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.HouseholdUserFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdUserService.*;
import static com.kaltura.client.services.HouseholdUserService.AddHouseholdUserBuilder;
import static com.kaltura.client.services.HouseholdUserService.ListHouseholdUserBuilder;
import static org.awaitility.Awaitility.await;

public class HouseholdUserServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdUser> householdUserResponse;
    private static Response<ListResponse<HouseholdUser>> householdUserListResponse;
    private static Response<Boolean> booleanResponse;


    // add
    public static Response<HouseholdUser> add(Client client, HouseholdUser householdUser) {
        AddHouseholdUserBuilder addHouseholdUserBuilder = HouseholdUserService.add(householdUser)
                .setCompletion((ApiCompletion<HouseholdUser>) result -> {
                    householdUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHouseholdUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdUserResponse;
    }

    // list
    public static Response<ListResponse<HouseholdUser>> list(Client client, @Nullable HouseholdUserFilter householdUserFilter) {
        ListHouseholdUserBuilder listHouseholdUserBuilder = HouseholdUserService.list(householdUserFilter)
                .setCompletion((ApiCompletion<ListResponse<HouseholdUser>>) result -> {
                    householdUserListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdUserListResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String id) {
        DeleteHouseholdUserBuilder deleteHouseholdUserBuilder = HouseholdUserService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteHouseholdUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
