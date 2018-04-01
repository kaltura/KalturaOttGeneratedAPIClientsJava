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
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class ResetPasswordTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<Boolean> booleanResponse;

    @BeforeClass
    private void ottUser_resetPassword_tests_setup() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/resetPassword - resetPassword")
    @Test(enabled = false)
    private void resetPassword() {
        booleanResponse = OttUserServiceImpl.resetPassword(PARTNER_ID, administratorKs, user.getUsername());

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        // TODO: 4/1/2018 finsih the test after bug BEO-4884 will be fixed
    }
}
