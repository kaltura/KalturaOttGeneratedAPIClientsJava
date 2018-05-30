package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.BaseUtils.getConcatenatedString;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static com.kaltura.client.test.utils.OttUserUtils.getOttUserById;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserListTests extends BaseTest {

    private Household household;
    private Response<ListResponse<OTTUser>> householdUserListResponse;

    private final int numberOfUsersInHousehold = 4;
    private final int numberOfDevicesInHousehold = 1;


    @BeforeClass
    private void ottUser_list_tests_setup() {
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/list - list with master user ks")
    @Test
    private void list_with_masterUserKs() {
        // get master user from household
        HouseholdUser masterUser = getMasterUserFromHousehold(household);

        // login master user
        String username = getOttUserById(Integer.parseInt(masterUser.getUserId())).getUsername();
        LoginOttUserBuilder loginOttUserBuilder = login(partnerId, username, defaultUserPassword);
        Response<LoginResponse> loginResponse = executor.executeSync(loginOttUserBuilder);

        // list household users
        ListOttUserBuilder listOttUserBuilder = list()
                .setKs(loginResponse.results.getLoginSession().getKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        // assert users list size
        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(numberOfUsersInHousehold + 2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/list - list with regular user ks")
    @Test
    private void list_with_regularUserKs() {
        // get master user from household
        HouseholdUser user = getRegularUsersListFromHouseHold(household).get(0);

        // login regular user
        String username = getOttUserById(Integer.parseInt(user.getUserId())).getUsername();
        LoginOttUserBuilder loginOttUserBuilder = login(partnerId, username, defaultUserPassword);
        Response<LoginResponse> loginResponse = executor.executeSync(loginOttUserBuilder);

        // list household users
        ListOttUserBuilder listOttUserBuilder = list()
                .setKs(loginResponse.results.getLoginSession().getKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        // assert users list size
        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(numberOfUsersInHousehold + 2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/list - get list with filter using idIn")
    @Test
    private void list_with_id_filter() {
        // get users from household
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        // set user filter
        OTTUserFilter ottUserFilter = new OTTUserFilter();
        String idIn = getConcatenatedString(householdUsers.get(0).getUserId(), householdUsers.get(1).getUserId());
        ottUserFilter.setIdIn(idIn);

        // list household users
        ListOttUserBuilder listOttUserBuilder = list(ottUserFilter)
                .setKs(getOperatorKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        // assert users list size
        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/list - get list with filter using usernameEqual")
    @Test
    private void list_with_username_filter() {
        // get users from household
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        // set user filter
        OTTUserFilter ottUserFilter = new OTTUserFilter();
        String usernameEqual = getOttUserById(Integer.valueOf(householdUsers.get(0).getUserId())).getUsername();
        ottUserFilter.setUsernameEqual(usernameEqual);

        // list household users
        ListOttUserBuilder listOttUserBuilder = list(ottUserFilter)
                .setKs(getOperatorKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        // assert users list size
        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/list - get list with invalid filter")
    @Test
    private void list_with_invalid_filter() {
        // get users from household
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        // set invalid user filter
        OTTUserFilter ottUserFilter = new OTTUserFilter();
        ottUserFilter.setIdIn(householdUsers.get(0).getUserId());
        ottUserFilter.setUsernameEqual(getOttUserById(Integer.valueOf(householdUsers.get(1).getUserId())).getUsername());

        // list household users
        ListOttUserBuilder listOttUserBuilder = list(ottUserFilter)
                .setKs(getOperatorKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);

        // assert error 500038 is return
        assertThat(householdUserListResponse.results).isNull();
        assertThat(householdUserListResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500038).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/list - get list with filter using usernameEqual")
    @Test
    private void list_with_externalId_filter() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // set user filter
        OTTUserFilter ottUserFilter = new OTTUserFilter();
        ottUserFilter.setExternalIdEqual(user.getExternalId());

        // list household users
        ListOttUserBuilder listOttUserBuilder = list(ottUserFilter)
                .setKs(getOperatorKs());
        householdUserListResponse = executor.executeSync(listOttUserBuilder);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        // assert users list size
        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @AfterClass
    private void list_AfterClass() {
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = HouseholdService.delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }
}
