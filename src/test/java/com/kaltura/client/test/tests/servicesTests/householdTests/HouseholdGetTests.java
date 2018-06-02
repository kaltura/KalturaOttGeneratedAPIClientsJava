package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdGetTests extends BaseTest {

    private Household household;

    @BeforeClass
    private void household_getTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 2;
        int numberOfDevicesInHousehold = 2;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/get - with master user ks")
    @Test
    private void get_with_masterUser_ks() {
        // get master user ks
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);

        // get household
        GetHouseholdBuilder getHouseholdBuilder = get()
                .setKs(masterUserKs);
        Response<Household> householdResponse = executor.executeSync(getHouseholdBuilder);

        assertThat(householdResponse.error).isNull();
        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/get - with regular user ks")
    @Test
    private void get_with_regularUser_ks() {
        // get regular user ks
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);
        String userKs = OttUserUtils.getKs(Integer.parseInt(user.getUserId()), null);

        // get household
        GetHouseholdBuilder getHouseholdBuilder = get()
                .setKs(userKs);
        Response<Household> householdResponse = executor.executeSync(getHouseholdBuilder);

        assertThat(householdResponse.error).isNull();
        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/get - with operator user ks")
    @Test
    private void get_with_operatorUser_ks() {
        // get household
        GetHouseholdBuilder getHouseholdBuilder = get(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs());
        Response<Household> householdResponse = executor.executeSync(getHouseholdBuilder);

        assertThat(householdResponse.error).isNull();
        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/get - with invalid household id - error 1006")
    @Test
    private void get_with_invalid_householdId() {
        int invalidHouseholdId = 1;

        // get household
        GetHouseholdBuilder getHouseholdBuilder = get(invalidHouseholdId)
                .setKs(getOperatorKs());
        Response<Household> householdResponse = executor.executeSync(getHouseholdBuilder);

        assertThat(householdResponse.results).isNull();
        assertThat(householdResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1006).getCode());
    }

    @AfterClass
    private void household_getTests_afterClass() {
        // delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getAdministratorKs()));
    }
}
