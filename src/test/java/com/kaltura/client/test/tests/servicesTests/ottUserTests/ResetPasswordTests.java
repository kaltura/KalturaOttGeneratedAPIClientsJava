package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.Client;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class ResetPasswordTests extends BaseTest {

    private Client client;
    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<Boolean> booleanResponse;

    @BeforeClass
    private void ottUser_resetPassword_tests_setup() {
        client = getClient(null);
        Response<OTTUser> ottUserResponse = register(client, PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/resetPassword - resetPassword")
    @Test(enabled = false)
    private void resetPassword() {
        client.setKs(administratorKs);
        booleanResponse = OttUserServiceImpl.resetPassword(client, PARTNER_ID, user.getUsername());

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        client = getClient(null);
        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(client, PARTNER_ID, user.getUsername(), password, null, null);
        // TODO: 4/1/2018 finsih the test after bug BEO-4884 will be fixed
    }
}
