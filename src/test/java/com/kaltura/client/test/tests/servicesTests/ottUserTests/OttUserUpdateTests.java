package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.OttUserUtils.dynamicDataMapBuilder;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;


public class OttUserUpdateTests extends BaseTest {

    private OTTUser user;
    private String userKs;

    @BeforeClass
    private void ottUser_updateTests_beforeClass() {
        // set household
        user = generateOttUser();
        user.setDynamicData(dynamicDataMapBuilder("key", "value"));

        // register user
        user = executor.executeSync(register(partnerId, user, defaultUserPassword)).results;

        // login user
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        user = loginResponse.results.getUser();
        userKs = loginResponse.results.getLoginSession().getKs();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - update")
    @Test(enabled = false)
    private void update() {
        // update user info
        String newUserInfo = "newUserInfo";
        OTTUser updatedUser = new OTTUser();
        updatedUser.setFirstName(newUserInfo);
        updatedUser.setLastName(newUserInfo);
        updatedUser.setAddress(newUserInfo);

        updatedUser = executor.executeSync(OttUserService.update(updatedUser).setKs(userKs)).results;

        // assert user new info
        assertThat(updatedUser.getFirstName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getLastName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getAddress()).isEqualTo(newUserInfo);
        assertThat(updatedUser).isEqualToIgnoringGivenFields(user, "firstName", "lastName", "address");

//        // cleanup
//        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - with administratorKs")
    @Test(enabled = false)
    private void update_with_administratorKs() {
//        // register user
//        OTTUser user = generateOttUser();
//        user.setDynamicData(dynamicDataMapBuilder("key", "value"));
//
//        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
//        user = ottUserResponse.results;
//        String originalUserEmail = user.getEmail();
//
//        // update user info
//        String newUserInfo = "newUserInfo";
//        user.setFirstName(newUserInfo);
//        user.setLastName(newUserInfo);
//        user.setAddress(newUserInfo);
//
//        ottUserResponse = executor.executeSync(OttUserService.update(user)
//                .setKs(getAdministratorKs())
//                .setUserId(Integer.valueOf(user.getId())));
//        assertThat(ottUserResponse.error).isNull();
//
//        // get user after update
//        ottUserResponse =  executor.executeSync(get()
//                .setKs(getAdministratorKs())
//                .setUserId(Integer.valueOf(user.getId())));
//        user = ottUserResponse.results;
//
//        // assert user new info
//        assertThat(ottUserResponse.error).isNull();
//        assertThat(user.getFirstName()).isEqualTo(newUserInfo);
//        assertThat(user.getLastName()).isEqualTo(newUserInfo);
//        assertThat(user.getAddress()).isEqualTo(newUserInfo);
//        assertThat(user.getEmail()).isEqualTo(originalUserEmail);
//
//        // cleanup
//        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/update - update user externalId - error ")
    @Test(enabled = false)
    private void update_user_externalId() {
//        // register user
//        OTTUser user = generateOttUser();
//        user.setDynamicData(dynamicDataMapBuilder("key", "value"));
//
//        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
//        user = ottUserResponse.results;
//
//        // login user
//        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
//        String userKs = loginResponse.results.getLoginSession().getKs();
//
//        // update user externalId
//        String newExternalId = "newExternalId";
//        user.setExternalId(newExternalId);
//
//        ottUserResponse = executor.executeSync(OttUserService.update(user).setKs(userKs));
//        assertThat(ottUserResponse.results).isNull();
//        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500051).getCode());
//
//        // cleanup
//        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }
}
