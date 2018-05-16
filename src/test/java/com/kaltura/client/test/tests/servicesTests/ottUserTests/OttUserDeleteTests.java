package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.services.HouseholdService.DeleteHouseholdBuilder;
import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.HouseholdUtils.getDefaultUserFromHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getMasterUserFromHousehold;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserDeleteTests extends BaseTest {

    private Household household;
    private Response<Boolean> booleanResponse;
    private DeleteOttUserBuilder deleteOttUserBuilder;

    private final int numberOfDevicesInHousehold = 0;
    private final int numberOfUsersInHousehold = 2;

    @BeforeClass
    private void ottUser_delete_tests_setup() {
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/delete - delete")
    @Test
    private void delete() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // delete user and assert success
        deleteOttUserBuilder = OttUserService.delete()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        boolean result = executor.executeSync(deleteOttUserBuilder).results;

        assertThat(result).isTrue();

        // try to get user and assert error
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<OTTUser> ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500004).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/delete - delete master user: error 2031")
    @Test()
    private void delete_master_user() {
        // get household master user
        HouseholdUser masterUser = getMasterUserFromHousehold(household);

        // try to delete master user and assert error
        deleteOttUserBuilder = OttUserService.delete()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        booleanResponse = executor.executeSync(deleteOttUserBuilder);

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2031).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/delete - delete default user: error 2030")
    @Test()
    private void delete_default_user() {
        // get household default user
        HouseholdUser defaultUser = getDefaultUserFromHousehold(household);

        // try to delete default user and assert error
        deleteOttUserBuilder = OttUserService.delete()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(defaultUser.getUserId()));
        booleanResponse = executor.executeSync(deleteOttUserBuilder);

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2030).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/delete - delete not exist user: error 500004")
    @Test()
    private void delete_not_exist_user() {
        // delete not exist user and assert error
        int invalidUserId = 1;
        deleteOttUserBuilder = OttUserService.delete()
                .setKs(getAdministratorKs())
                .setUserId(invalidUserId);
        booleanResponse = executor.executeSync(deleteOttUserBuilder);

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500004).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/delete - delete user with suspended household: error 1009")
    @Test()
    private void delete_user_with_suspended_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);

        // get regular user
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);

        // suspend household
        SuspendHouseholdBuilder suspendHouseholdBuilder = suspend()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getUserId()));
        booleanResponse = executor.executeSync(suspendHouseholdBuilder);

        // delete suspended user
        deleteOttUserBuilder = OttUserService.delete()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getUserId()));
        booleanResponse = executor.executeSync(deleteOttUserBuilder);

        // assert error
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1009).getCode());
    }

    @AfterClass
    private void ottUser_delete_tests_tearDown() {
        DeleteHouseholdBuilder deleteHouseholdBuilder = HouseholdService.delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

}
