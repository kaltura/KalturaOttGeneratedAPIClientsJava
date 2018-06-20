package com.kaltura.client.test.tests.servicesTests.sessionTests;

import com.kaltura.client.enums.UserState;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.services.SessionService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.Session;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionSwitchUserTests extends BaseTest {

    private final int numOfUsers = 2;
    private final int numOfDevices = 1;

    private String userId;
    private String userKs;

    @BeforeClass
    private void switchUser_tests_before_class() {
        userId = executor.executeSync(OttUserService.register(partnerId, OttUserUtils.generateOttUser(), defaultUserPassword))
                .results.getId();
        userKs = OttUserUtils.getKs(Integer.valueOf(userId));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("session/action/switchUser")
    @Test
    private void switchUser() {
        Household household = createHousehold(numOfUsers, numOfDevices, false);
        String udid = getDevicesList(household).get(0).getUdid();
        String masterUserId = getMasterUser(household).getUserId();
        String secondUserId = getRegularUsersList(household).get(0).getUserId();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUserId), udid);

        // Invoke session/action/switchUser - second user replace master user in the session
        Response<LoginSession> loginSessionResponse = executor.executeSync(SessionService.switchUser(secondUserId)
                .setKs(masterUserKs));

        // Verify new session ks returned
        assertThat(loginSessionResponse.results).isNotNull();
        String secondUserKs = loginSessionResponse.results.getKs();
        assertThat(secondUserKs).isNotEmpty();

        ///----- After User was switched ------
        // Invoke OttUser/action/get - with master user (expired) ks
        Response<OTTUser> ottUserResponse = executor.executeSync(OttUserService.get()
                .setKs(masterUserKs));

        // Verify master user ks is now expired (after the switch)
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());

        // Invoke OttUser/action/get with second user new ks
        ottUserResponse = executor.executeSync(OttUserService.get()
                .setKs(secondUserKs));

        // Verify second user id return in the response
        assertThat(ottUserResponse.results.getId()).isEqualTo(secondUserId);
        assertThat(ottUserResponse.results.getUserState()).isEqualTo(UserState.OK);

        // Invoke session/action/get
        Response<Session> getSessionResponse = executor.executeSync(SessionService.get(secondUserKs)
                .setKs(getAdministratorKs()));

        // Verify second user id returned in the response
        assertThat(getSessionResponse.results.getUserId()).isEqualTo(secondUserId);
        assertThat(getSessionResponse.results.getUdid()).isEqualTo(udid);
        
        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("/session/action/switchUser - user switch to himself")
    @Test
    private void switchUserToHimself() {
        Household household = createHousehold(numOfUsers, numOfDevices, false);
        String udid = getDevicesList(household).get(0).getUdid();
        String masterUserId = getMasterUser(household).getUserId();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUserId), udid);

        // Invoke session/action/switchUser - Should return an error (user can't switched to himself
        Response<LoginSession> loginSessionResponse = executor.executeSync(SessionService.switchUser(masterUserId)
                .setKs(masterUserKs));

        // Verify exception returned
        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1).getCode());
        // TODO: 6/19/2018 need to open a bug on error 1 

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("/session/action/switchUser - switch to inactive user")
    @Test(enabled = false)
    private void switchToInactiveUser() {
        //TODO - replace hardcoded user id
        String inactiveUserId = "1543797";

        Response<LoginSession> loginSessionResponse = executor.executeSync(SessionService.switchUser(inactiveUserId)
                .setKs(userKs));

        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2016).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("/session/action/switchUser - switch to user from another HH")
    @Test
    private void switchToUserFromAnotherHousehold() {
        Household household1 = createHousehold(numOfUsers, numOfDevices, false);
        String masterUserKs1 = getHouseholdMasterUserKs(household1);

        Household household2 = createHousehold(numOfUsers, numOfDevices, false);
        String masterUserId2 = getMasterUser(household2).getUserId();

        Response<LoginSession> loginSessionResponse = executor.executeSync(SessionService.switchUser(masterUserId2)
                .setKs(masterUserKs1));

        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("session/action/switchUser - No user id to switch provided")
    @Test
    private void switchToUserWithoutUserId() {
        Response<LoginSession> loginSessionResponse = executor.executeSync(SessionService.switchUser(null)
                .setKs(userKs));

        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500053).getCode());
    }
}
