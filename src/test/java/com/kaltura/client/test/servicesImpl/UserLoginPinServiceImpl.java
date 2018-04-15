package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.UserLoginPinService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.UserLoginPin;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserLoginPinService.*;
import static org.awaitility.Awaitility.await;

public class UserLoginPinServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<UserLoginPin> userLoginPinResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<UserLoginPin> add(Client client, @Nullable String secret) {
        AddUserLoginPinBuilder addUserLoginPinBuilder = UserLoginPinService.add(secret)
                .setCompletion((ApiCompletion<UserLoginPin>) result -> {
                    userLoginPinResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addUserLoginPinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userLoginPinResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String pinCode) {
        DeleteUserLoginPinBuilder deleteUserLoginPinBuilder = UserLoginPinService.delete(pinCode)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteUserLoginPinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // deleteAll
    public static Response<Boolean> deleteAll(Client client) {
        DeleteAllUserLoginPinBuilder deleteAllUserLoginPinBuilder = UserLoginPinService.deleteAll()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteAllUserLoginPinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // update
    public static Response<UserLoginPin> update(Client client, String pinCode, @Nullable String secret) {
        UpdateUserLoginPinBuilder updateUserLoginPinBuilder = UserLoginPinService.update(pinCode, secret)
                .setCompletion((ApiCompletion<UserLoginPin>) result -> {
                    userLoginPinResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateUserLoginPinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userLoginPinResponse;
    }
}
