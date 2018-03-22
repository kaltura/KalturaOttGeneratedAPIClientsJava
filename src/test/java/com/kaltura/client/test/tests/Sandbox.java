package com.kaltura.client.test.tests;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.helper.Config.*;
import static com.kaltura.client.test.helper.Helper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class Sandbox extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> response;

    private static final String LOGIN_RESPONSE_SCHEMA = "KalturaLoginResponse_Schema.json";

    @BeforeClass
    public void sandbox_tests_setup() {
        user = generateOttUser();
        RegisterOttUserBuilder registerOttUserBuilder = register(PARTNER_ID, user, password);
        APIOkRequestsExecutor.getExecutor().queue(registerOttUserBuilder.build(client));
    }

    private Response<LoginResponse> sandboxLoginTest() {
        final AtomicBoolean done = new AtomicBoolean(false);

        LoginOttUserBuilder loginOttUserBuilder = login(PARTNER_ID, user.getUsername(), password)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    if (result.isSuccess()) {
                        assertThat(result.results.getLoginSession()).isNotNull();
                        assertThat(result.results.getUser()).isNotNull();

//                        MatcherAssert.assertThat(result.results.toParams().toString(), matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA));
                    }
                    response = result;
                    done.set(true);
                });
        APIOkRequestsExecutor.getExecutor().queue(loginOttUserBuilder.build(client));
        await().untilTrue(done);

        return response;
    }

    @Test
    private void test11() {
        sandboxLoginTest();
        System.out.println(response.results.getUser().getUsername());
    }
}
