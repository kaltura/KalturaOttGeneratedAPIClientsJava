package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import org.testng.annotations.Test;

public class GrantTests extends BaseTest {

    @Test(description = "entitlement/action/grant - grant")
    private void grant() {
        String subscriptionId = "369426";
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        // new hh
        // predefined subscription
        // grant
//        Client client = getClient(operatorKs);
//        EntitlementServiceImpl.grant(client, )
    }
}
