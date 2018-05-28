package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.enums.HouseholdState;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.UserRoleService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
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

import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.services.OttUserService.login;
import static org.assertj.core.api.Assertions.assertThat;

public class householdSuspendTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;

    private enum Permissions {
        PLAYBACK_SUBSCRIPTION,
        PLAYBACK_PPV,
        PURCHASE_SUBSCRIPTION,
        PURCHASE_PPV,
        RENEW_SUBSCRIPTION,
        PURCHASE_SERVICE,
        LOGIN,
        CANCEL_SUBSCRIPTION,
        DELETE_ALL_APP_TOKENS
    }

    @BeforeClass
    private void household_suspendTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 2;
        int numberOfDevicesInHousehold = 1;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/suspend")
    @Test
    private void suspend() {
        // suspend household
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // get household
        Response<Household> householdResponse = executor.executeSync(get(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState().getValue()).isEqualTo(HouseholdState.SUSPENDED.getValue());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/suspend - with master user ks - error 500004")
    @Test
    private void suspend_with_masterUser_ks() {
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);

        // suspend household
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.suspend().setKs(masterUserKs));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500004).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/suspend - login role - error 7013")
    @Test
    private void suspend_with_login_role() {
        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.LOGIN.name());
        role.setName(Permissions.LOGIN.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend login
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // login
        String username = OttUserUtils.getOttUserById(Integer.parseInt(masterUser.getUserId())).getUsername();
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, username, defaultUserPassword));

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(7013).getCode());

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/suspend - purchase_subscription role - error 7013")
    @Test
    private void suspend_with_purchase_subscription_role() {
        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PURCHASE_SUBSCRIPTION.name());
        role.setName(Permissions.PURCHASE_SUBSCRIPTION.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend purchase_subscription
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // purchase_subscription
        String userKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);
        Response<Transaction> transactionResponse = PurchaseUtils.purchaseSubscription(userKs, Integer.parseInt(BaseTest.getSharedCommonSubscription().getId()));

        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(7013).getCode());

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));
    }

    @AfterClass
    private void household_suspendTests_afterClass() {
        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }
}
