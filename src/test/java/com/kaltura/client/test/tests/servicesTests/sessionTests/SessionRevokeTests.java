package com.kaltura.client.test.tests.servicesTests.sessionTests;

import com.kaltura.client.Client;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.servicesImpl.SessionServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;

import com.kaltura.client.types.Household;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionRevokeTests extends BaseTest {

    public static Client client;
    private String udid;

    @BeforeClass
    private void revoke_tests_before_class() {
        udid = "123456789";
    }


    @Description("/session/action/revoke - 2 different kss")
    @Test
    private void RevokeKs() {
        Household household = HouseholdUtils.createHouseHold(2, 2, false);
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, null);
        String masterUserKs2 = HouseholdUtils.getHouseholdMasterUserKs(household, udid);
        client = getClient(masterUserKs);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Revoke all sessions for specific user
        Response<Boolean> booleanResponse = SessionServiceImpl.revoke(client);
        assertThat(booleanResponse.results).isTrue();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify ks is expired
        Response<OTTUser> ottUserResponse2 = OttUserServiceImpl.get(client);
        assertThat(ottUserResponse2.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());

        client = getClient(masterUserKs2);

        // Verify ks2 is expired
        Response<OTTUser> ottUserResponse3 = OttUserServiceImpl.get(client);
        assertThat(ottUserResponse3.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());
    }

}