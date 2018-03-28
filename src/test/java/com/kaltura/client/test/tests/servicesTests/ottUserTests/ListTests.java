package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.list;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.HouseholdUtils.getMasterUserFromHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getUsersListFromHouseHold;
import static com.kaltura.client.test.utils.OttUserUtils.getUserNameFromId;
import static org.assertj.core.api.Assertions.assertThat;

public class ListTests extends BaseTest {

    private Household household;
    private int numberOfUsersInHousehold = 4;
    private Response<ListResponse<OTTUser>> householdUserListResponse;

    @BeforeClass
    private void ottUser_list_tests_setup() {
        household = HouseholdUtils.createHouseHold(numberOfUsersInHousehold, 1);
    }

    @Description("ottUser/action/list - list from master ks")
    @Test
    private void list_from_master_ks() {
        HouseholdUser masterUser = getMasterUserFromHousehold(household);

        Response<LoginResponse> loginResponse = login(PARTNER_ID, getUserNameFromId(Integer.parseInt(masterUser.getUserId())),
                GLOBAL_USER_PASSWORD, null, null);
        String masterUserKs = loginResponse.results.getLoginSession().getKs();

        householdUserListResponse = list(masterUserKs, null);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        assertThat(loginResponse.error).isNull();
        assertThat(users.size()).isEqualTo(numberOfUsersInHousehold + 1);
    }

    @Description("ottUser/action/list - get list with filter using idIn")
    @Test
    private void list_with_filter_idIn() {
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        OTTUserFilter filter = new OTTUserFilter();
        String idIn = householdUsers.get(0).getUserId() + "," + householdUsers.get(1).getUserId();
        filter.setIdIn(idIn);

        householdUserListResponse = list(administratorKs, filter);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @Description("ottUser/action/list - get list with filter using usernameEqual")
    @Test
    private void list_withd_filter_usernameEqual() {
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        OTTUserFilter filter = new OTTUserFilter();
        String usernameEqual = getUserNameFromId(Integer.valueOf(householdUsers.get(0).getUserId()));

        filter.setUsernameEqual(usernameEqual);

        householdUserListResponse = list(administratorKs, filter);
        List<OTTUser> users = householdUserListResponse.results.getObjects();

        assertThat(householdUserListResponse.error).isNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Description("ottUser/action/list - get list with not valid filter")
    @Test
    private void list_with_not_valid_filter() {
        List<HouseholdUser> householdUsers = getUsersListFromHouseHold(household);

        OTTUserFilter filter = new OTTUserFilter();
        filter.setIdIn(householdUsers.get(0).getUserId());
        filter.setUsernameEqual(getUserNameFromId(Integer.valueOf(householdUsers.get(1).getUserId())));

        householdUserListResponse = list(administratorKs, filter);

        assertThat(householdUserListResponse.results).isNull();
        assertThat(householdUserListResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500038).getCode());
    }
}
