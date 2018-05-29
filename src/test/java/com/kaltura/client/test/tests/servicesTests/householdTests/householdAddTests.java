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

import static com.kaltura.client.services.HouseholdService.add;
import static com.kaltura.client.services.HouseholdService.delete;
import static org.assertj.core.api.Assertions.assertThat;

public class householdAddTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;

    @BeforeClass
    private void household_addTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 1;
        int numberOfDevicesInHousehold = 1;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/add - master user exists in other household - error 1018")
    @Test
    private void add_with_exists_in_other_household_masterUser() {
        // create household
        Household household = new Household();
        household.setName(masterUser.getUserId() + " Domain");
        household.setDescription(masterUser.getUserId() + " Description");

        // add household
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);
        Response<Household> householdResponse = executor.executeSync(add(household).setKs(masterUserKs));

        assertThat(householdResponse.results).isNull();
        assertThat(householdResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1018).getCode());
    }

    @AfterClass
    private void household_getTests_afterClass() {
        // delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getAdministratorKs()));
    }
}
