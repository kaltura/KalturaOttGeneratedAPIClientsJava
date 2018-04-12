package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.Properties;
import com.kaltura.client.test.servicesImpl.EntitlementServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.OTTUser;
import org.testng.annotations.Test;

public class GrantTests extends BaseTest {

    @Test(description = "entitlement/action/grant - grant")
    private void grant() {
        Client client = getClient(null);
        // TODO: 4/12/2018 remove hardcoded subscription Id
        int subscriptionId = 369426;

//        Household household = HouseholdUtils.createHouseHold(2, 1, false);
//        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
//        Response<LoginResponse> loginResponse = OttUserServiceImpl.login(client, PARTNER_ID, OttUserUtils.getUserNameFromId(Integer.parseInt(masterUser.getUserId())),
//                GLOBAL_USER_PASSWORD, null, null);
        OTTUser user = OttUserUtils.generateOttUser();
        
        client.setKs(administratorKs);
        client.setUserId(user.getus);
        EntitlementServiceImpl.grant(client, subscriptionId, TransactionType.SUBSCRIPTION, true, null);

//        Client client = getClient(operatorKs);
//        EntitlementServiceImpl.grant(client, )
    }
}
