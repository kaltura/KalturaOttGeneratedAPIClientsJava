package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.services.UserRoleService;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.UserRole;
import com.kaltura.client.types.UserRoleFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserRoleService.ListUserRoleBuilder;
import static com.kaltura.client.test.tests.BaseTest.*;
import static org.awaitility.Awaitility.await;

public class UserRoleServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<UserRole>> userRoleListResponse;

    //list
    public static Response<ListResponse<UserRole>> list(String ks, @Nullable UserRoleFilter filter) {
        ListUserRoleBuilder listUserRoleBuilder = UserRoleService.list(filter)
                .setCompletion((ApiCompletion<ListResponse<UserRole>>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/27/2018 fix schema assertions
                    }
                    userRoleListResponse = result;
                    done.set(true);
                });

        listUserRoleBuilder.setKs(ks);

        APIOkRequestsExecutor.getExecutor().queue(listUserRoleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userRoleListResponse;
    }
}
