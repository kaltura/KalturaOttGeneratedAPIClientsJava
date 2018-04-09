package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class GetTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> loginResponse;
    private Response<OTTUser> ottUserResponse;

    @BeforeClass
    private void ottUser_login_tests_setup() {
        ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;

        loginResponse = login(PARTNER_ID, user.getUsername(), password, null, null);
        user = loginResponse.results.getUser();
    }

    // get tests
    @Description("ottUser/action/get - get")
    @Test
    private void get() {
        ottUserResponse = OttUserServiceImpl.get(loginResponse.results.getLoginSession().getKs(), Optional.empty());
        assertThat(loginResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType");
    }
}
