package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.UserInterestService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.UserInterest;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserInterestService.*;
import static org.awaitility.Awaitility.await;

public class UserInterestServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<UserInterest> userInterestResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<UserInterest>> userInterestListResponse;

    // add
    public static Response<UserInterest> add(Client client, UserInterest userInterest) {
        AddUserInterestBuilder addUserInterestBuilder = UserInterestService.add(userInterest)
                .setCompletion((ApiCompletion<UserInterest>) result -> {
                    userInterestResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addUserInterestBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userInterestResponse;
    }

    // delete
    public static Response<Boolean> add(Client client, String id) {
        DeleteUserInterestBuilder deleteUserInterestBuilder = UserInterestService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteUserInterestBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<UserInterest>> list(Client client, String id) {
        ListUserInterestBuilder listUserInterestBuilder = UserInterestService.list()
                .setCompletion((ApiCompletion<ListResponse<UserInterest>>) result -> {
                    userInterestListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listUserInterestBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userInterestListResponse;
    }
}
