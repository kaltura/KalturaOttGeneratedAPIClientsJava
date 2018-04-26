package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.UserState;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.DBUtils;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class ActivateTests extends BaseTest {

    private Client client;
    private OTTUser user;

    @BeforeClass
    private void ottUser_activate_tests_setup() {
        client = getClient(null);
        user = generateOttUser();

        register(client, partnerId, user, defaultUserPassword);
        login(client, partnerId, user.getUsername(), defaultUserPassword, null, null);
    }

    @Description("ottUser/action/activate - activate")
    @Test
    private void activate() {
        String activationToken = DBUtils.getActivationToken(user.getUsername());

        Response<OTTUser> ottUserResponse = OttUserServiceImpl.activate(client, partnerId, user.getUsername(),activationToken);
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);
    }
}
