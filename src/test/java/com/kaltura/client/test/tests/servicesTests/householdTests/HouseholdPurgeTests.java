package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.HouseholdUserService;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.HouseholdService.purge;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdPurgeTests extends BaseTest {

    private final int numberOfUsersInHousehold = 1;
    private final int numberOfDevicesInHousehold = 1;


    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - active household")
    @Test()
    private void purge_active_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(1);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(1);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(1);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);

        // delete household after purge
        booleanResponse = executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1006).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - deleted household")
    @Test()
    private void purge_deleted_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // delete household before purge
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(1);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);

        // delete household after purge
        booleanResponse = executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1006).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - after deleting user")
    @Test()
    private void purge_after_delete_user() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);

        // delete user before purge
        executor.executeSync(OttUserService.delete()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(user.getUserId())));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(1);

        JSONObject masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(0);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(1);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(1);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(2);

        userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - after removing user from household")
    @Test()
    private void purge_after_remove_user_from_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);

        // remove user from household before purge
        executor.executeSync(HouseholdUserService.delete(user.getUserId())
                .setKs(getOperatorKs()));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(1);

        JSONObject masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(0);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(1);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(1);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(1);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(2);

        userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(1);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - on suspended household")
    @Test()
    private void purge_on_suspended_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // suspend household before purge
        executor.executeSync(HouseholdService.suspend()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(1);
        assertThat(householdJO.getInt("is_suspended")).as("household suspend status").isEqualTo(1);

        JSONObject masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(0);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(1);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);
        assertThat(householdJO.getInt("is_suspended")).as("household suspend status").isEqualTo(1);

        masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(1);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - after deleting user and household")
    @Test()
    private void purge_after_delete_user_and_household() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);

        // delete user before purge
        executor.executeSync(OttUserService.delete()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(user.getUserId())));

        // delete household before purge
        executor.executeSync(HouseholdService.delete(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(0);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        JSONObject masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(0);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(2);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("purge")).as("household purge status").isEqualTo(1);
        assertThat(householdJO.getInt("status")).as("household delete status").isEqualTo(2);

        masterUserJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(masterUserJO.getInt("purge")).as("master user purge status").isEqualTo(1);
        assertThat(masterUserJO.getInt("status")).as("master user delete status").isEqualTo(2);

        userJO = DBUtils.getUserById(Integer.parseInt(user.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(1);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/purge - with not exists household")
    @Test()
    private void purge_with_not_exists_household() {
        // purge
        int invalidHouseholdId = -1;
        Response<Boolean> booleanResponse = executor.executeSync(purge(invalidHouseholdId)
                .setKs(getOperatorKs()));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1006).getCode());
    }
}
