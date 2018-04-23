package com.kaltura.client.test.tests.servicesTests.sessionTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.UserState;
import com.kaltura.client.test.servicesImpl.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionSwitchUserTests extends BaseTest {

    private String UserId = "1543798";
    private String userKs;
    public static Client client;

    @BeforeClass
    private void switchUser_tests_before_class() {
        userKs = OttUserUtils.getKs(Integer.valueOf(UserId), null);
    }

    @Description("/session/action/switchUser")
    @Test
    private void SwitchUser() {

        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, udid);
        String secondUserId = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0).getUserId();
        client = getClient(masterUserKs);

        // Invoke session/action/switchUser - second user replace master user in the session
        Response<LoginSession> loginSessionResponse = SessionServiceImpl.switchUser(client, secondUserId);
        // Verify new session ks returned
        assertThat(loginSessionResponse.results.getKs()).isNotEmpty();
        String secondUserKs = loginSessionResponse.results.getKs();

        ///----- After User was switched ------

        // Invoke OttUser/action/get - with master user (expired) ks
        Response<OTTUser> ottUserResponse = OttUserServiceImpl.get(client);
        // Verify master user ks is now expired (after the switch)
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());

        client = getClient(secondUserKs);
        // Invoke OttUser/action/get with second user new ks
        Response<OTTUser> ottUserResponse2 = OttUserServiceImpl.get(client);
        // Verify second user id return in the response
        assertThat(ottUserResponse2.results.getId()).isEqualTo(secondUserId);
        assertThat(ottUserResponse2.results.getUserState()).isEqualTo(UserState.OK);

        client = getClient(administratorKs);
        // Invoke session/action/get
        Response<Session> getSessionResponse = SessionServiceImpl.get(client, secondUserKs);
        // Verify second user id returned in the response
        assertThat(getSessionResponse.results.getUserId()).isEqualTo(secondUserId);
        assertThat(getSessionResponse.results.getUdid()).isEqualTo(udid);
    }

    @Description("/session/action/switchUser - user switch to himself")
    @Test
    private void SwitchUserToHimself() {
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, udid);
        String masterUserId = HouseholdUtils.getMasterUserFromHousehold(household).getUserId();
        client = getClient(masterUserKs);

        // Invoke session/action/switchUser - Should return an error (user can't switched to himself
        Response<LoginSession> loginSessionResponse = SessionServiceImpl.switchUser(client, masterUserId);

        // Verify exception returned
        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1).getCode());
    }

    @Description("/session/action/switchUser - switch to inactive user")
    @Test
    private void SwitchInactiveUser() {

        String inactiveUserId = "1543797";
        String UserKs = OttUserUtils.getKs(Integer.valueOf(UserId), null);
        client = getClient(UserKs);

        Response<LoginSession> loginSessionResponse = SessionServiceImpl.switchUser(client, inactiveUserId);
        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2016).getCode());
    }

    @Description("/session/action/switchUser - switch to user from another HH")
    @Test
    private void switchToUserFromAnotherHousehold() {
        String userIdFromHousehold1 = "1543798";
        String Use1rKs = OttUserUtils.getKs(Integer.valueOf(userIdFromHousehold1), null);
        client = getClient(Use1rKs);
        String userIdFromHousehold2 = "638731";

        Response<LoginSession> loginSessionResponse = SessionServiceImpl.switchUser(client, userIdFromHousehold2);
        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }


    @Description("/session/action/switchUser - No user id to switch provided")
    @Test
    private void switchToUserWithoutUserId() {
        client = getClient(userKs);
        Response<LoginSession> loginSessionResponse = SessionServiceImpl.switchUser(client, null);
        assertThat(loginSessionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500053).getCode());
    }
}
