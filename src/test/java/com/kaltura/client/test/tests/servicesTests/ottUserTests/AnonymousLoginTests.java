package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;

import static com.kaltura.client.test.helper.Config.PARTNER_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class AnonymousLoginTests extends BaseTest {

    @Description("ottUser/action/anonymousLogin - anonymousLogin")
    @Test()
    public void anonymousLoginTest() {
        Response<LoginSession> loginSessionResponse = OttUserServiceImpl.anonymousLoginImpl(PARTNER_ID, null);
        assertThat(loginSessionResponse.error).isNull();
        assertThat(loginSessionResponse.results.getKs()).isNotNull();
    }

    // TODO: 3/15/2018 add tests with wrong partnerId
}
