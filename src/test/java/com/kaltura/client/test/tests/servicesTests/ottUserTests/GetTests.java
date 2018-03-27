package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.helper.Helper;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.getImpl;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.loginImpl;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.registerImpl;
import static org.assertj.core.api.Assertions.assertThat;

public class GetTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<LoginResponse> loginResponse;

    @BeforeClass
    public void ottUser_login_tests_setup() {
        user = Helper.generateOttUser();
        registerImpl(PARTNER_ID, user, password);
        loginResponse = loginImpl(PARTNER_ID, user.getUsername(), password, null, null);
    }

    // get tests
    @Description("ottUser/action/get - get")
    @Test
    private void get() {
        Response<OTTUser> ottUserResponse = getImpl(loginResponse.results.getLoginSession().getKs(), Optional.empty());
        assertThat(loginResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToComparingFieldByField(user);
    }
}
