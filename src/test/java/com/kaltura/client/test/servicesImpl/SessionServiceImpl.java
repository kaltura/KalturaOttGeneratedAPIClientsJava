package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SessionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Session;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class SessionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);
    private static Response<Session> getSessionResponse;


    // session/action/get

    public static Response<Session> get(Client client, String session) {
        SessionService.GetSessionBuilder sessionBuilder = SessionService.get(session)
                .setCompletion((ApiCompletion<Session>) result -> {
                    getSessionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(sessionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return getSessionResponse;
    }
}
