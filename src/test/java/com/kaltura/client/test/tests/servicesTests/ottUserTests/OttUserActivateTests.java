package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.enums.UserState;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.delete;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserActivateTests extends BaseTest {

    private OTTUser user;
    private Response<OTTUser> ottUserResponse;

    @BeforeClass
    private void ottUser_activate_tests_setup() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        user = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword)).results.getUser();

        // assert user is not activated
        assertThat(user.getUserState()).isEqualTo(UserState.USER_NOT_ACTIVATED);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/activate - activate")
    @Test
    private void activate() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        user = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword)).results.getUser();

        // assert user is not activated
        assertThat(user.getUserState()).isEqualTo(UserState.USER_NOT_ACTIVATED);
        
        // get activation token
        String activationToken = DBUtils.getActivationToken(user.getUsername());

        // activate user
        ottUserResponse = executor.executeSync(OttUserService.activate(partnerId, user.getUsername(), activationToken));

        // assert user activated
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/activate - activate twice with the same token - error 2000")
    @Test
    private void activate_twice_with_same_token() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        user = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword)).results.getUser();

        // assert user is not activated
        assertThat(user.getUserState()).isEqualTo(UserState.USER_NOT_ACTIVATED);

        // get activation token
        String activationToken = DBUtils.getActivationToken(user.getUsername());

        // activate user - first time
        ottUserResponse = executor.executeSync(OttUserService.activate(partnerId, user.getUsername(), activationToken));

        // assert user activated
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);

        // activate user - second time
        ottUserResponse = executor.executeSync(OttUserService.activate(partnerId, user.getUsername(), activationToken));

        // assert error return
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/activate - invalid user name - error 2000")
    @Test
    private void activate_with_invalid_username() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        user = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword)).results.getUser();

        // assert user is not activated
        assertThat(user.getUserState()).isEqualTo(UserState.USER_NOT_ACTIVATED);

        // get activation token
        String activationToken = DBUtils.getActivationToken(user.getUsername());

        // activate user - with invalid user name
        String invalidUserName = user.getUsername() + 1;
        ottUserResponse = executor.executeSync(OttUserService.activate(partnerId, invalidUserName, activationToken));

        // assert error return
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/activate - invalid activationToken - error 2000")
    @Test
    private void activate_with_invalid_activationToken() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        user = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword)).results.getUser();

        // assert user is not activated
        assertThat(user.getUserState()).isEqualTo(UserState.USER_NOT_ACTIVATED);

        // get activation token
        String invalidActivationToken = "1";

        // activate user - with invalid activationToken
        ottUserResponse = executor.executeSync(OttUserService.activate(partnerId, user.getUsername(), invalidActivationToken));

        // assert error return
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());
    }

    @AfterClass
    private void activate_afterClass() {
        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }
}
