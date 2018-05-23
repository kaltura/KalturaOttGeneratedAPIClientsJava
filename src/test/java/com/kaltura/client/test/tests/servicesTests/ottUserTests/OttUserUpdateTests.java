package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.dynamicDataMapBuilder;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;


public class OttUserUpdateTests extends BaseTest {

    private Response<OTTUser> ottUserResponse;


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - update")
    @Issue("BEO-5114")
    @Test(enabled = false)
    private void update() {
        // register user
        OTTUser user = generateOttUser();
        user.setDynamicData(dynamicDataMapBuilder("key", "value"));

        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
        user = ottUserResponse.results;
        String originalUserEmail = user.getEmail();

        // login user
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        String userKs = loginResponse.results.getLoginSession().getKs();

        // update user info
        String newUserInfo = "newUserInfo";
        user.setFirstName(newUserInfo);
        user.setLastName(newUserInfo);
        user.setAddress(newUserInfo);

        ottUserResponse = executor.executeSync(OttUserService.update(user).setKs(userKs));
        assertThat(ottUserResponse.error).isNull();

        // get user after update
        user = executor.executeSync(get().setKs(userKs)).results;

        // assert user new info
        assertThat(ottUserResponse.error).isNull();
        assertThat(user.getFirstName()).isEqualTo(newUserInfo);
        assertThat(user.getLastName()).isEqualTo(newUserInfo);
        assertThat(user.getAddress()).isEqualTo(newUserInfo);
        assertThat(user.getEmail()).isEqualTo(originalUserEmail);

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - update with administratorKs")
    @Test(enabled = true)
    private void update_with_administratorKs() {
        // register user
        OTTUser user = generateOttUser();
        user.setDynamicData(dynamicDataMapBuilder("key", "value"));

        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
        user = ottUserResponse.results;
        String originalUserEmail = user.getEmail();

        // update user info
        String newUserInfo = "newUserInfo";
        user.setFirstName(newUserInfo);
        user.setLastName(newUserInfo);
        user.setAddress(newUserInfo);

        ottUserResponse = executor.executeSync(OttUserService.update(user)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId())));
        assertThat(ottUserResponse.error).isNull();

        // get user after update
        ottUserResponse =  executor.executeSync(get()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId())));
        user = ottUserResponse.results;

        // assert user new info
        assertThat(ottUserResponse.error).isNull();
        assertThat(user.getFirstName()).isEqualTo(newUserInfo);
        assertThat(user.getLastName()).isEqualTo(newUserInfo);
        assertThat(user.getAddress()).isEqualTo(newUserInfo);
        assertThat(user.getEmail()).isEqualTo(originalUserEmail);

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/update - update user externalId - error ")
    @Test(enabled = true)
    private void update_user_externalId() {
        // register user
        OTTUser user = generateOttUser();
        user.setDynamicData(dynamicDataMapBuilder("key", "value"));

        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
        user = ottUserResponse.results;

        // login user
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        String userKs = loginResponse.results.getLoginSession().getKs();

        // update user externalId
        String newExternalId = "newExternalId";
        user.setExternalId(newExternalId);

        ottUserResponse = executor.executeSync(OttUserService.update(user).setKs(userKs));
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500051).getCode());

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }


}
