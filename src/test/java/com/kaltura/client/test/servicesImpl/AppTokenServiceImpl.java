package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AppTokenService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AppTokenService.*;
import static org.awaitility.Awaitility.await;

public class AppTokenServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);
    private static Response<AppToken> appTokenResponse;

    public static AddAppTokenBuilder add(Client client, AppToken appToken) {
        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setCompletion((ApiCompletion<AppToken>) result -> {
                    appTokenResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addAppTokenBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return addAppTokenBuilder;
    }
}