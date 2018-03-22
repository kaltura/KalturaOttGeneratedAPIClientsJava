package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.helper.Helper;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.helper.Config.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Config.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.loginImpl;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.registerImpl;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> loginResponse;

    @BeforeClass
    public void ottUser_tests_setup() {
        user = Helper.generateOttUser();
        registerImpl(PARTNER_ID, user, password);
    }

    // login tests
    @Description("ottUser/action/login - login")
    @Test
    private void login() {
        loginResponse = loginImpl(PARTNER_ID, user.getUsername(), password, null, null);
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession()).isNotNull();
        assertThat(loginResponse.results.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Description("ottUser/action/login - login with wrong password")
    @Test
    public void login_with_wrong_password() {
        loginResponse = loginImpl(PARTNER_ID, user.getUsername(), password + "1", null, null);
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo("1011");
        assertThat(loginResponse.error.getMessage()).isEqualTo("Wrong username or password");
    }

    @Description("ottUser/action/login - login with wrong username")
    @Test
    public void login_with_wrong_username() {
        loginResponse = loginImpl(PARTNER_ID, user.getUsername() + "1", password, null, null);
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo("2000");
        assertThat(loginResponse.error.getMessage()).isEqualTo("User does not exist");
    }
}
