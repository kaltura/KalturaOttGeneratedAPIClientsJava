package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.servicesImpl.UserLoginPinServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.UserLoginPin;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginWithPinTests extends BaseTest {

    private OTTUser user;

    private Response<LoginResponse> loginResponse;
    private Response<UserLoginPin> userLoginPinResponse;

    private final String SECRET = "secret";

    @BeforeClass
    private void ottUser_login_tests_setup() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/loginWithPin - loginWithPin with secret")
    @Test
    private void loginWithPin_with_secret() throws InterruptedException {
        userLoginPinResponse = UserLoginPinServiceImpl.add(administratorKs, Integer.parseInt(user.getId()), SECRET);

        String pin = userLoginPinResponse.results.getPinCode();
        loginResponse = OttUserServiceImpl.loginWithPin(PARTNER_ID, pin, null, SECRET);

        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession()).isNotNull();
        assertThat(loginResponse.results.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Description("ottUser/action/loginWithPin - loginWithPin with wrong secret - error 2008")
    @Test
    private void loginWithPin_with_wrong_secret() {
        userLoginPinResponse = UserLoginPinServiceImpl.add(administratorKs, Integer.parseInt(user.getId()), SECRET);

        String pin = userLoginPinResponse.results.getPinCode();
        loginResponse = OttUserServiceImpl.loginWithPin(PARTNER_ID, pin, null, SECRET + 1);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2008).getCode());
    }

    @Description("ottUser/action/loginWithPin - loginWithPin with expired pinCode - error 2004")
    @Test(enabled = true)
    private void loginWithPin_with_expired_pinCode() {
        userLoginPinResponse = UserLoginPinServiceImpl.add(administratorKs, Integer.parseInt(user.getId()), SECRET);

        String pin = userLoginPinResponse.results.getPinCode();
        // sleep for 1.5 minutes
        try { Thread.sleep(120000); } catch (InterruptedException e) { e.printStackTrace(); }
        loginResponse = OttUserServiceImpl.loginWithPin(PARTNER_ID, pin, null, SECRET);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2004).getCode());
    }
}
