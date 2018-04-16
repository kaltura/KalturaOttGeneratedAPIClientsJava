package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AppTokenService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.types.SessionInfo;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AppTokenService.*;
import static org.awaitility.Awaitility.await;

public class AppTokenServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);
    private static Response<AppToken> appTokenResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<SessionInfo> sessionInfoResponse;

    // AppToken/action/add
    public static Response<AppToken> add(Client client, AppToken appToken) {
        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setCompletion((ApiCompletion<AppToken>) result -> {
                    appTokenResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addAppTokenBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return appTokenResponse;
    }

    //AppToken/acton/get
    public static Response<AppToken> get(Client client, String id) {
        GetAppTokenBuilder getAppTokenBuilder = AppTokenService.get(id)
                .setCompletion((ApiCompletion<AppToken>) result -> {
                    appTokenResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getAppTokenBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return appTokenResponse;
    }

    // AppToken/action/delete
    public static Response<Boolean> delete(Client client, String id) {
        DeleteAppTokenBuilder deleteAppTokenBuilder = AppTokenService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteAppTokenBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    //AppToken/action/startSession
    public static Response<SessionInfo> startSession(Client client, String id, String hashToken, String userId, int expiry, String udid) {
        StartSessionAppTokenBuilder startSessionAppTokenBuilder = AppTokenService.startSession(id, hashToken, userId, expiry, udid)
                .setCompletion((ApiCompletion<SessionInfo>) result -> {
                    sessionInfoResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(startSessionAppTokenBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return sessionInfoResponse;
    }
}