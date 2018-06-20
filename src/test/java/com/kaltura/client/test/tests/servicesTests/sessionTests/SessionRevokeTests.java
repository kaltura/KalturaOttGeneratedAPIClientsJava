package com.kaltura.client.test.tests.servicesTests.sessionTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.services.SessionService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.GetOttUserBuilder;
import static com.kaltura.client.services.SessionService.RevokeSessionBuilder;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionRevokeTests extends BaseTest {

    @Description("/session/action/revoke - 2 different kss")
    @Test
    private void RevokeKs() {
        Household household = HouseholdUtils.createHousehold(2, 2, false);
        String udid = HouseholdUtils.getDevicesList(household).get(0).getUdid();
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household);
        String masterUserKs2 = HouseholdUtils.getHouseholdMasterUserKs(household, udid);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Revoke all sessions for specific user
        RevokeSessionBuilder revokeSessionBuilder = SessionService.revoke().setKs(masterUserKs);
        Response<Boolean> booleanResponse = executor.executeSync(revokeSessionBuilder);

        assertThat(booleanResponse.results).isTrue();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify ks is expired
        GetOttUserBuilder getOttUserBuilder = OttUserService.get().setKs(masterUserKs);
        Response<OTTUser> ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());

        // Verify ks2 is expired
        GetOttUserBuilder getOttUserBuilder2 = OttUserService.get().setKs(masterUserKs2);
        Response<OTTUser> ottUserResponse2 = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse2.error.getCode()).isEqualTo(getAPIExceptionFromList(500016).getCode());
    }

}