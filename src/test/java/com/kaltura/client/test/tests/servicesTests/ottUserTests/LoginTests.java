package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.helper.Helper.generateOttUser;
import static com.kaltura.client.test.helper.Helper.getAPIExceptionFromList;
import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> loginResponse;

    @BeforeClass
    public void ottUser_login_tests_setup() {
        user = generateOttUser();
        register(PARTNER_ID, user, password);
    }

    // login tests
    @Description("ottUser/action/login - login")
    @Test
    private void login() {
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession()).isNotNull();
        assertThat(loginResponse.results.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Description("ottUser/action/login - login with wrong password - error 1011")
    @Test
    public void login_with_wrong_password() {
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password + "1", null, null);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());
    }

    @Description("ottUser/action/login - login with wrong username - error 2000")
    @Test
    public void login_with_wrong_username() {
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername() + "1", password, null, null);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());
    }

    @Description("ottUser/action/login - login with wrong partnerId - error 500006")
    @Test()
    public void login_with_wrong_partnerId() {
        loginResponse = OttUserServiceImpl.login(PARTNER_ID + 1, user.getUsername(), password, null, null);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500006).getCode());
    }
}
