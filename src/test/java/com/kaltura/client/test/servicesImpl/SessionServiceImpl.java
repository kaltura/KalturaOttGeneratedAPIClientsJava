package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SessionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.Session;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SessionService.*;
import static org.awaitility.Awaitility.await;

public class SessionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Session> sessionResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<LoginSession> loginSessionResponse;

    // get
    public static Response<Session> get(Client client, @Nullable String session) {
        GetSessionBuilder getSessionBuilder = SessionService.get(session)
                .setCompletion((ApiCompletion<Session>) result -> {
                    sessionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getSessionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return sessionResponse;
    }

    // revoke
    public static Response<Boolean> revoke(Client client) {
        RevokeSessionBuilder revokeSessionBuilder = SessionService.revoke().setCompletion((ApiCompletion<Boolean>) result -> {
            booleanResponse = result;
            done.set(true);
        });

        TestAPIOkRequestsExecutor.getExecutor().queue(revokeSessionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // switchUser
    public static Response<LoginSession> switchUser(Client client, String userIdToSwitch) {
        SwitchUserSessionBuilder switchUserSessionBuilder = SessionService.switchUser(userIdToSwitch)
                .setCompletion((ApiCompletion<LoginSession>) result -> {
                    loginSessionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(switchUserSessionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginSessionResponse;
    }
}
