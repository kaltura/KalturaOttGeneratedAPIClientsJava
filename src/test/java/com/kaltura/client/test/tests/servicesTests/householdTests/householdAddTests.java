package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.add;
import static com.kaltura.client.services.HouseholdService.delete;

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

    @Description("household/action/add - master user exists in other household")
    @Test
    private void add_with_exists_in_other_household_masterUser() {
        // create household
        Household household = new Household();
        household.setName(masterUser.getUserId() + " Domain");
        household.setDescription(masterUser.getUserId() + " Description");

        // add household
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);
        executor.executeSync(add(household).setKs(masterUserKs));

        // TODO: 5/23/2018 finsih test

    }

    @AfterClass
    private void household_getTests_afterClass() {
        // delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getAdministratorKs()));
    }
}
