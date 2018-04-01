package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateLoginDataTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<Boolean> booleanResponse;

    @BeforeClass
    private void ottUser_updateLoginData_tests_setup() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/updateLoginData - updateLoginData")
    @Test
    private void updateLoginData() {
        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        String ks = loginResponse.results.getLoginSession().getKs();

        booleanResponse = OttUserServiceImpl.updateLoginData(ks, user.getUsername(), password, password + 1);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // try login with old password
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());

        // try login with new password
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password + 1, null, null);
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession().getKs()).isNotNull();
    }

    @Description("ottUser/action/updateLoginData - updateLoginData with administratorKs")
    @Test
    private void updateLoginData_with_administratorKs() {
        booleanResponse = OttUserServiceImpl.updateLoginData(administratorKs, user.getUsername(), password, password + 1);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // try login with old password
        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());

        // try login with new password
        loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password + 1, null, null);
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession().getKs()).isNotNull();
    }
}
