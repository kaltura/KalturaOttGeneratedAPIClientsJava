package com.kaltura.client.test.tests.servicesTests.ottUserTests;

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

public class ResendActivationTokenTests extends BaseTest {
    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    @BeforeClass
    private void ottUser_resendActivationToken_tests_setup() {
        user = generateOttUser();
        register(PARTNER_ID, user, password);
        login(PARTNER_ID, user.getUsername(), password, null, null);
    }

    @Description("ottUser/action/resendActivationToken - resendActivationToken")
    @Test(enabled = false) // TODO: 4/1/2018 find how to tag test as bug (bug BEO-4908)
    private void resendActivationToken() {
        String activationToken = DBUtils.getActivationToken(user.getUsername());

        Response<Boolean> booleanResponse = OttUserServiceImpl.resendActivationToken(PARTNER_ID, user.getUsername());
        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        String resentActivationToken = DBUtils.getActivationToken(user.getUsername());
        assertThat(activationToken).isNotEqualTo(resentActivationToken);
    }
}
