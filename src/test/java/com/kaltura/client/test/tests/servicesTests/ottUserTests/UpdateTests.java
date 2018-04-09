package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;


public class UpdateTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;
    private String originalUserEmail;

    private Response<OTTUser> ottUserResponse;

    @BeforeClass
    private void ottUser_update_tests_setup() {
        ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
        originalUserEmail = user.getEmail();
    }

    @Description("ottUser/action/update - update")
    @Test
    private void update() {
        // get self ks
        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(PARTNER_ID, user.getUsername(), password, null, null);
        String ks = loginResponse.results.getLoginSession().getKs();

        // update
        String newUserInfo = "abc";

        user.setFirstName(newUserInfo);
        user.setLastName(newUserInfo);
        ottUserResponse = OttUserServiceImpl.update(ks, user, null);

        assertThat(ottUserResponse.error).isNull();

        // get user after update
        ottUserResponse = OttUserServiceImpl.get(ks, Optional.empty());
        user = ottUserResponse.results;

        // assert
        assertThat(ottUserResponse.error).isNull();
        assertThat(user.getFirstName()).isEqualTo(newUserInfo);
        assertThat(user.getLastName()).isEqualTo(newUserInfo);
        assertThat(user.getEmail()).isEqualTo(originalUserEmail);
    }

    @Description("ottUser/action/update - update with administratorKs")
    @Test(enabled = false) // TODO: 4/2/2018 bug BEO-4919 
    private void update_with_administratorKs() {

        // update
        String newUserInfo = "def";

        user.setFirstName(newUserInfo);
        user.setLastName(newUserInfo);
//        user.setAffiliateCode(null); 
        ottUserResponse = OttUserServiceImpl.update(administratorKs, user, user.getId());

        assertThat(ottUserResponse.error).isNull();

        // get user after update
        ottUserResponse = OttUserServiceImpl.get(administratorKs, Optional.of(Integer.valueOf(user.getId())));
        user = ottUserResponse.results;

        // assert
        assertThat(ottUserResponse.error).isNull();
        assertThat(user.getFirstName()).isEqualTo(newUserInfo);
        assertThat(user.getLastName()).isEqualTo(newUserInfo);
        assertThat(user.getEmail()).isEqualTo(originalUserEmail);
    }

    @AfterClass
    private void ottUser_update_tests_tearDown() {
        OttUserServiceImpl.delete(administratorKs, Optional.of(Integer.valueOf(user.getId())));
    }
}
