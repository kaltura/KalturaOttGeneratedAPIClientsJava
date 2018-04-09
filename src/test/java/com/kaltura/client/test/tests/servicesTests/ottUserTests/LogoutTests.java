package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
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

public class LogoutTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> loginResponse;
    private Response<Boolean> booleanResponse;


    @BeforeClass
    private void ottUser_logout_tests_setup() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/logout - logout")
    @Test
    private void logout() {
        loginResponse = login(PARTNER_ID, user.getUsername(), password, null, null);
        String ks = loginResponse.results.getLoginSession().getKs();

        booleanResponse = OttUserServiceImpl.logout(ks, Optional.empty());
        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        Response<OTTUser> ottUserResponse = OttUserServiceImpl.get(ks, Optional.empty());
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500016).getCode());
    }

}
