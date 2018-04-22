package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.UserRoleService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.UserRole;
import com.kaltura.client.types.UserRoleFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserRoleService.*;
import static com.kaltura.client.services.UserRoleService.ListUserRoleBuilder;
import static org.awaitility.Awaitility.await;

public class UserRoleServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<UserRole>> userRoleListResponse;
    private static Response<UserRole> userRoleResponse;
    private static Response<Boolean> booleanResponse;

    // list
    public static Response<ListResponse<UserRole>> list(Client client, @Nullable UserRoleFilter userRoleFilter) {
        ListUserRoleBuilder listUserRoleBuilder = UserRoleService.list(userRoleFilter)
                .setCompletion((ApiCompletion<ListResponse<UserRole>>) result -> {
                    userRoleListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listUserRoleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userRoleListResponse;
    }

    // add
    public static Response<UserRole> add(Client client, UserRole userRole) {
        AddUserRoleBuilder addUserRoleBuilder = UserRoleService.add(userRole)
                .setCompletion((ApiCompletion<UserRole>) result -> {
                    userRoleResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addUserRoleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userRoleResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, long id) {
        DeleteUserRoleBuilder deleteUserRoleBuilder = UserRoleService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteUserRoleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // update
    public static Response<UserRole> update(Client client, long id, UserRole userRole) {
        UpdateUserRoleBuilder updateUserRoleBuilder = UserRoleService.update(id, userRole)
                .setCompletion((ApiCompletion<UserRole>) result -> {
                    userRoleResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateUserRoleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userRoleResponse;
    }
}
