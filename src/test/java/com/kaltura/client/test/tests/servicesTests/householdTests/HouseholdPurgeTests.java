package com.kaltura.client.test.tests.servicesTests.householdTests;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.HouseholdService.purge;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdPurgeTests extends BaseTest {

//    private Household household;
//    private HouseholdUser masterUser;
//    private String masterUserKs;

    @BeforeClass
    private void household_purgeTests_beforeClass() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge - active household")
    @Test()
    private void purge_active_household() {
        // set household
        int numberOfUsersInHousehold = 1;
        int numberOfDevicesInHousehold = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("status")).as("household purge status").isEqualTo(1);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(1);

        // purge
        Response<Boolean> booleanResponse = executor.executeSync(purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert relevant statuses in db after purge
        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("status")).as("purge status").isEqualTo(2);

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
        int numberOfUsersInHousehold = 1;
        int numberOfDevicesInHousehold = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // delete household before purge
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));

        // assert relevant statuses in db before purge
        JSONObject householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
        assertThat(householdJO.getInt("status")).as("household purge status").isEqualTo(2);

        JSONObject userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(0);
        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);
//
//        // purge
//        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.purge(Math.toIntExact(household.getId()))
//                .setKs(getOperatorKs()));
//        assertThat(booleanResponse.results.booleanValue()).isTrue();
//
//        // assert relevant statuses in db after purge
//        householdJO = DBUtils.getHouseholdById(Math.toIntExact(household.getId()));
//        assertThat(householdJO.getInt("status")).as("purge status").isEqualTo(2);
//
//        userJO = DBUtils.getUserById(Integer.parseInt(masterUser.getUserId()));
//        assertThat(userJO.getInt("purge")).as("user purge status").isEqualTo(1);
//        assertThat(userJO.getInt("status")).as("user delete status").isEqualTo(2);
//
//        // delete household after purge
//        booleanResponse = executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
//        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1006).getCode());
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

    @AfterClass
    private void household_purgeTests_afterClass() {

    }
}
