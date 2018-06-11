package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Entitlement;
import com.kaltura.client.types.EntitlementFilter;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static com.kaltura.client.services.EntitlementService.*;
import static com.kaltura.client.services.HouseholdService.delete;
import static org.assertj.core.api.Assertions.assertThat;

public class EntitlementCancelTests extends BaseTest {

    private int subscriptionId;

    private Household testSharedHousehold;
    private HouseholdUser testSharedMasterUser;

    private final int numberOfUsersInHousehold = 2;
    private final int numberOfDevicesInHousehold = 1;

    @BeforeClass
    private void cancel_test_before_class() {
        subscriptionId = Integer.valueOf(getSharedCommonSubscription().getId());

        // set household
        testSharedHousehold = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        testSharedMasterUser = HouseholdUtils.getMasterUserFromHousehold(testSharedHousehold);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("entitlement/action/cancel - cancel subscription")
    @Test
    private void cancel_subscription() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
        String userKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);

        // grant subscription
        GrantEntitlementBuilder grantEntitlementBuilder = grant(subscriptionId, TransactionType.SUBSCRIPTION, true, 0)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        executor.executeSync(grantEntitlementBuilder);

        // set entitlementFilter
        EntitlementFilter filter = new EntitlementFilter();
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);

        // assert entitlement list size == 1
        ListEntitlementBuilder listEntitlementBuilder = EntitlementService.list(filter)
                .setKs(userKs);
        List<Entitlement> entitlementList = executor.executeSync(listEntitlementBuilder).results.getObjects();
        assertThat(entitlementList.size()).isEqualTo(1);

        // cancel subscription
        CancelEntitlementBuilder cancelEntitlementBuilder = cancel(subscriptionId, TransactionType.SUBSCRIPTION)
                .setKs(userKs);
        Response<Boolean> booleanResponse = executor.executeSync(cancelEntitlementBuilder);
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert entitlement list size == 0
        listEntitlementBuilder = EntitlementService.list(filter)
                .setKs(userKs);
        entitlementList = executor.executeSync(listEntitlementBuilder).results.getObjects();
        assertThat(entitlementList.size()).isEqualTo(0);

        // delete household for cleanup
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getAdministratorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/cancel - cancel invalid subscription - error 3000")
    @Test
    private void cancel_with_invalid_subscription() {
        // cancel subscription
        int invalidSubscriptionId = 1;
        String userKs = OttUserUtils.getKs(Integer.parseInt(testSharedMasterUser.getUserId()), null);

        CancelEntitlementBuilder cancelEntitlementBuilder = cancel(invalidSubscriptionId, TransactionType.SUBSCRIPTION)
                .setKs(userKs);
        Response<Boolean> booleanResponse = executor.executeSync(cancelEntitlementBuilder);
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(3000).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/cancel - cancel played subscription - error 3005")
    @Test
    private void cancel_played_subscription() {
//        // set household
//        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
//        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
//        String userKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), null);
        // TODO: 5/23/2018 complete test  
    }

    @AfterClass
    private void cancel_test_after_class() {
        // delete shared household for cleanup
        executor.executeSync(delete(Math.toIntExact(testSharedHousehold.getId())).setKs(getAdministratorKs()));
    }


    // TODO: 5/22/2018 add cancel ppv test with dynamic data
}
