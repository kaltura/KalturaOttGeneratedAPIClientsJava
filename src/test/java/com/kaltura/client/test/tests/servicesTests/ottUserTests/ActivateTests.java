package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.enums.UserState;
import com.kaltura.client.test.helper.DBHelper;
import com.kaltura.client.test.helper.Helper;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ActivateTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    @BeforeClass
    public void ottUser_activate_tests_setup() {
        user = Helper.generateOttUser();
        registerImpl(PARTNER_ID, user, password);
        loginImpl(PARTNER_ID, user.getUsername(), password, null, null);
    }

    @Description("ottUser/action/activate - activate")
    @Test
    private void activate() {
        String activationToken = DBHelper.getActivationToken(user.getUsername());
        if (activationToken == null)
            fail("activationToken can't be null");

        Response<OTTUser> ottUserResponse = activateImpl(PARTNER_ID, user.getUsername(),activationToken);
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);
    }
}
