package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.HouseholdServiceImpl;
import com.kaltura.client.test.servicesImpl.HouseholdUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.utils.Utils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.helper.Helper.generateOttUser;
import static com.kaltura.client.test.helper.Helper.getAPIExceptionFromList;
import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTests extends BaseTest {

    private Household household;
    private List<HouseholdUser> users;

    @BeforeClass
    public void ottUser_delete_tests_setup() {
        household = Utils.createHouseHold(2, 0);
        HouseholdUserFilter filter = new HouseholdUserFilter();
        filter.setHouseholdIdEqual(Math.toIntExact(household.getId()));
        Response<ListResponse<HouseholdUser>> usersResponse = HouseholdUserServiceImpl.listImpl(administratorKS, filter);
        users = usersResponse.results.getObjects();
    }

    @Description("ottUser/action/delete - delete")
    @Test
    private void delete() {
        Response<OTTUser> ottUserResponse = registerImpl(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        OTTUser user = ottUserResponse.results;

        Response<Boolean> booleanResponse = deleteImpl(administratorKS, Integer.valueOf(user.getId()));
        boolean result = booleanResponse.results;
        assertThat(result).isTrue();

        ottUserResponse = getImpl(administratorKS, Optional.of(Integer.valueOf(user.getId())));
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500004).getCode());
        assertThat(ottUserResponse.results).isNull();
    }

    @Description("ottUser/action/delete - delete master user")
    @Test(enabled = true)
    private void delete_master_user() {
        HouseholdUser householdUser = new HouseholdUser();

        for (HouseholdUser user : users) {
            if (user.getIsMaster() != null && user.getIsMaster()) {
                householdUser = user;
                break;
            }
        }

        Response<Boolean> booleanResponse = deleteImpl(administratorKS, Integer.valueOf(householdUser.getUserId()));
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2031).getCode());
    }
    
    @Description("ottUser/action/delete - delete default user")
    @Test(enabled = true)
    private void delete_default_user() {
        HouseholdUser householdUser = new HouseholdUser();

        for (HouseholdUser user : users) {
            if (user.getIsDefault() != null && user.getIsDefault()) {
                householdUser = user;
                break;
            }
        }

        Response<Boolean> booleanResponse = deleteImpl(administratorKS, Integer.valueOf(householdUser.getUserId()));
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2030).getCode());
    }

    @AfterClass
    private void ottUser_delete_tests_tearDown() {
        HouseholdServiceImpl.deleteImpl(administratorKS, Math.toIntExact(household.getId()));
    }

}
