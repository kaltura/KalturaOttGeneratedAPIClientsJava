package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.enums.HouseholdState;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.UserRoleService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.HouseholdService.get;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdResumeTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;
    private String masterUserKs;
    private Subscription subscription;

    @BeforeClass
    private void household_resumeTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 2;
        int numberOfDevicesInHousehold = 1;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // set subscription
        subscription = BaseTest.getSharedCommonSubscription();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/resume - suspended household")
    @Test
    private void resume_suspended_household() {
        // suspend household
        executor.executeSync(HouseholdService.suspend()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));

        // get household - verify status is suspended
        Response<Household> householdResponse = executor.executeSync(get(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState()).isEqualTo(HouseholdState.SUSPENDED);

        // resume household
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.resume()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // get household - verify status is ok
        householdResponse = executor.executeSync(get(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState()).isEqualTo(HouseholdState.OK);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/resume - suspended household")
    @Test
    private void resume_suspended_with_purchase_subscription_role_household() {
        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames("PURCHASE_SUBSCRIPTION");
        role.setName("PURCHASE_SUBSCRIPTION");

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend household
        executor.executeSync(HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));

        // get household - verify status is suspended
        Response<Household> householdResponse = executor.executeSync(get(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState()).isEqualTo(HouseholdState.SUSPENDED);

        // resume household
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.resume()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // get household - verify status is ok
        householdResponse = executor.executeSync(get(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState()).isEqualTo(HouseholdState.OK);

        // purchase subscription
        Response<Transaction> transactionResponse = PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()));
        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));
    }

    @AfterClass
    private void household_resumeTests_afterClass() {
        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }


}
