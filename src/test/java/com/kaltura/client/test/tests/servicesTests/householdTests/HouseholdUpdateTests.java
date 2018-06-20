package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.HouseholdService.update;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdUpdateTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;
    private String masterUserKs;

    @BeforeClass
    private void household_updateTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 2;
        int numberOfDevicesInHousehold = 1;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        masterUser = HouseholdUtils.getMasterUser(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesList(household).get(0).getUdid();
        masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/update - with masterUserKs")
    @Test
    private void update_with_masterUserKs() {
        // update household
        String householdUpdatedDetails = "updated details with masterUserKs";
        Household updatedHousehold = new Household();
        updatedHousehold.setName(householdUpdatedDetails);
        updatedHousehold.setDescription(householdUpdatedDetails);

        updatedHousehold = executor.executeSync(update(updatedHousehold)
                .setKs(masterUserKs))
                .results;

        assertThat(updatedHousehold.getName()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold.getDescription()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold).isEqualToIgnoringGivenFields(household, "name", "description",
                "frequencyNextDeviceAction", "frequencyNextUserAction", "restriction");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/update - with operatorKs")
    @Test
    private void update_with_operatorKs() {
        // update household
        String householdUpdatedDetails = "updated details with operatorKs";
        Household updatedHousehold = new Household();
        updatedHousehold.setName(householdUpdatedDetails);
        updatedHousehold.setDescription(householdUpdatedDetails);

        updatedHousehold = executor.executeSync(update(updatedHousehold)
            .setKs(getOperatorKs())
            .setUserId(Integer.valueOf(masterUser.getUserId())))
            .results;

        assertThat(updatedHousehold.getName()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold.getDescription()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold).isEqualToIgnoringGivenFields(household, "name", "description",
                "frequencyNextDeviceAction", "frequencyNextUserAction", "restriction");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/update - with regular userKs")
    @Test
    private void update_with_regular_userKs() {
        // update household
        String householdUpdatedDetails = "updated details with regular userKs";
        Household updatedHousehold = new Household();
        updatedHousehold.setName(householdUpdatedDetails);
        updatedHousehold.setDescription(householdUpdatedDetails);

        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);
        String userKs = OttUserUtils.getKs(Integer.parseInt(user.getUserId()));

        updatedHousehold = executor.executeSync(update(updatedHousehold)
                .setKs(userKs))
                .results;

        assertThat(updatedHousehold.getName()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold.getDescription()).isEqualTo(householdUpdatedDetails);
        assertThat(updatedHousehold).isEqualToIgnoringGivenFields(household, "name", "description",
                "frequencyNextDeviceAction", "frequencyNextUserAction", "restriction");
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/update - with empty household object")
    @Issue("BEO-5169")
    @Test(enabled = false)
    private void update_with_empty_household() {
        // update household
        String householdUpdatedDetails = "updated details with empty household";
        Household updatedHousehold = new Household();

        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);
        String userKs = OttUserUtils.getKs(Integer.parseInt(user.getUserId()));

        updatedHousehold = executor.executeSync(update(updatedHousehold)
                .setKs(userKs))
                .results;

        assertThat(updatedHousehold).isEqualToIgnoringGivenFields(household, "frequencyNextDeviceAction",
                "frequencyNextUserAction", "restriction");
    }

    @AfterClass
    private void household_updateTests_afterClass() {
        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getAdministratorKs()));
    }
}
