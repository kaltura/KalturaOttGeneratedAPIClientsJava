package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.enums.UserState;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.DBUtils;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ActivateTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    @BeforeClass
    private void ottUser_activate_tests_setup() {
        user = generateOttUser();
        register(PARTNER_ID, user, password);
        login(PARTNER_ID, user.getUsername(), password, null, null);
    }

    @Description("ottUser/action/activate - activate")
    @Test
    private void activate() {
        String activationToken = DBUtils.getActivationToken(user.getUsername());
        if (activationToken == null)
            fail("activationToken can't be null");

        Response<OTTUser> ottUserResponse = OttUserServiceImpl.activate(PARTNER_ID, user.getUsername(),activationToken);
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);
    }
}
