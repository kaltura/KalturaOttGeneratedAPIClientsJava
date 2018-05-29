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
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.HouseholdService.get;
import static org.assertj.core.api.Assertions.assertThat;

public class householdDeleteTests extends BaseTest {

    private final int numberOfUsersInHousehold = 1;
    private final int numberOfDevicesInHousehold = 1;


    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/delete - master user household delete")
    @Test
    private void delete_with_household_masterUser() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // delete household
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);
        Response<Boolean> booleanResponse = executor.executeSync(delete().setKs(masterUserKs));

        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // get household
        Response<Household> householdResponse = executor.executeSync(get(Math.toIntExact(household.getId())).setKs(getOperatorKs()));

        assertThat(householdResponse.results).isNull();
        assertThat(householdResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1006).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/delete - regular user household delete - error 500004")
    @Test
    private void delete_with_household_regularUser() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);

        // delete household
        String userKs = OttUserUtils.getKs(Integer.parseInt(user.getUserId()), null);
        Response<Boolean> booleanResponse = executor.executeSync(delete().setKs(userKs));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500004).getCode());
    }


}
