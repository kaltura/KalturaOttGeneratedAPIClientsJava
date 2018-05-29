package com.kaltura.client.test.tests.servicesTests.ottUserTests;


import com.kaltura.client.enums.UserState;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.SuspendHouseholdBuilder;
import static com.kaltura.client.services.HouseholdService.suspend;
import static com.kaltura.client.services.OttUserService.delete;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static com.kaltura.client.test.utils.OttUserUtils.getOttUserById;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserLoginTests extends BaseTest {

    private OTTUser user;
    private Response<LoginResponse> loginResponse;

    @BeforeClass
    private void ottUser_login_tests_setup() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ottUser/action/login - login")
    private void login() {
        // login user
        loginResponse = executor.executeSync(OttUserService.login(partnerId, user.getUsername(), defaultUserPassword));

        // assertions
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession()).isNotNull();
        assertThat(loginResponse.results.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/login - login with invalid password - error 1011")
    @Test
    private void login_with_invalid_password() {
        String invalidPassword = "invalid";

        // login user
        loginResponse = executor.executeSync(OttUserService.login(partnerId, user.getUsername(), invalidPassword));

        // assertions
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/login - login with invalid username - error 2000")
    @Test
    private void login_with_invalid_username() {
        String invalidUsername = user.getUsername() + "1";

        // login user
        loginResponse = executor.executeSync(OttUserService.login(partnerId, invalidUsername, defaultUserPassword));

        // assertions
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/login - login with invalid partnerId - error 500006")
    @Test()
    private void login_with_invalid_partnerId() {
        int invalidPartnerId = partnerId + 1;

        // login user
        loginResponse = executor.executeSync(OttUserService.login(invalidPartnerId, user.getUsername(), defaultUserPassword));

        // assertions
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500006).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/login - login with suspended user - error ")
    @Test()
    private void login_with_suspended_user() {
        // set household
        int numberOfUsersInHousehold = 2;
        int numberOfDevicesInHousehold = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);

        // get regular user
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);

        // suspend household
        SuspendHouseholdBuilder suspendHouseholdBuilder = suspend()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getUserId()));
        executor.executeSync(suspendHouseholdBuilder);

        // login user
        String username = getOttUserById(Integer.parseInt(user.getUserId())).getUsername();
        loginResponse = executor.executeSync(OttUserService.login(partnerId, username, defaultUserPassword));

        // assertions
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getUser().getUserState().getValue()).isEqualTo(UserState.USER_NOT_ACTIVATED.getValue());
    }

    // TODO: 5/14/2018 ask Ira how to reproduce the below errors
//    @Severity(SeverityLevel.NORMAL)
//    @Description("ottUser/action/login - login with user twice - error ")
//    @Test()
//    private void login_with_user_twice() {
//        // register user
//        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;
//
//        // login user - first time
//        executor.executeSync(OttUserService.login(partnerId, user.getUsername(), defaultUserPassword));
//
//        // login user - second time
//        loginResponse = executor.executeSync(OttUserService.login(partnerId, user.getUsername(), defaultUserPassword));
//
//        // assertions
//        assertThat(loginResponse.results).isNull();
////        assertThat(loginResponse.error.getCode()).isEqualTo(UserState.USER_NOT_ACTIVATED.getValue());
//    }
//
//    @Severity(SeverityLevel.NORMAL)
//    @Description("ottUser/action/login - login with not registered device - error ")
//    @Test()
//    private void login_with_not_registered_devcie() {
//        // set household
//        int numberOfUsersInHousehold = 2;
//        int numberOfDevicesInHousehold = 1;
//        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
//
//        // get regular user
//        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);
//
//        // login user with invalid udid
//        String username = getOttUserById(Integer.parseInt(user.getUserId())).getUsername();
//        String invalidDeviceId = "invalid_device_id";
//        loginResponse = executor.executeSync(OttUserService.login(partnerId, username, defaultUserPassword, null, invalidDeviceId));
//
//        // assertions
//        assertThat(loginResponse.results).isNull();
//    }

    // UserAllreadyLoggedIn
    // UserDoubleLogIn
    // InsideLockTime
    // DeviceNotRegistered - login with invalid registered
    // UserNotMasterApproved - user in pending status

    @AfterClass
    private void login_afterClass() {
        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }
}
