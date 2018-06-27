package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserUpdateDynamicDataTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/updateDynamicData - updateDynamicData")
    @Test
    private void updateDynamicData() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // set dynamic data
        String keyString = BaseUtils.getRandomString();
        String valueString = BaseUtils.getRandomString();

        StringValue value = new StringValue();
        value.setValue(valueString);

        // update user dynamic data
        UpdateDynamicDataOttUserBuilder updateDynamicDataOttUserBuilder = OttUserService.updateDynamicData(keyString, value)
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<OTTUserDynamicData> ottUserDynamicDataResponse = executor.executeSync(updateDynamicDataOttUserBuilder);

        // assert new dynamic data
        assertThat(ottUserDynamicDataResponse.error).isNull();
        assertThat(ottUserDynamicDataResponse.results.getKey()).isEqualTo(keyString);
        assertThat(ottUserDynamicDataResponse.results.getValue().getValue()).isEqualTo(valueString);

        // get user
        Response<OTTUser> ottUserResponse = executor.executeSync(OttUserService.get()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(user.getId())));

        // assert get user data
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getDynamicData()).isNotNull();
        assertThat(ottUserResponse.results.getDynamicData().get(keyString).getValue()).isEqualTo(valueString);

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/updateDynamicData - with masterUser ks")
    @Test
    private void updateDynamicData_with_masterUser_ks() {
        // set household
        int numOfUsers = 1;
        int numOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numOfUsers, numOfDevices, false);
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household);

        // set dynamic data
        String keyString = BaseUtils.getRandomString();
        String valueString = BaseUtils.getRandomString();

        StringValue value = new StringValue();
        value.setValue(valueString);

        // update user dynamic data
        Response<OTTUserDynamicData> ottUserDynamicDataResponse =
                executor.executeSync(OttUserService.updateDynamicData(keyString, value)
                        .setKs(masterUserKs));

        // assert new dynamic data
        assertThat(ottUserDynamicDataResponse.error).isNull();
        assertThat(ottUserDynamicDataResponse.results.getKey()).isEqualTo(keyString);
        assertThat(ottUserDynamicDataResponse.results.getValue().getValue()).isEqualTo(valueString);

        // get user
        Response<OTTUser> ottUserResponse = executor.executeSync(OttUserService.get()
                .setKs(masterUserKs));

        // assert get user data
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getDynamicData()).isNotNull();
        assertThat(ottUserResponse.results.getDynamicData().get(keyString).getValue()).isEqualTo(valueString);

        // cleanup
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/updateDynamicData - with user ks")
    @Test
    private void updateDynamicData_with_user_ks() {
        // set household
        int numOfUsers = 1;
        int numOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numOfUsers, numOfDevices, false);
        String userKs = HouseholdUtils.getHouseholdUserKs(household);

        // set dynamic data
        String keyString = BaseUtils.getRandomString();
        String valueString = BaseUtils.getRandomString();

        StringValue value = new StringValue();
        value.setValue(valueString);

        // update user dynamic data
        Response<OTTUserDynamicData> ottUserDynamicDataResponse =
                executor.executeSync(OttUserService.updateDynamicData(keyString, value)
                        .setKs(userKs));

        // assert new dynamic data
        assertThat(ottUserDynamicDataResponse.error).isNull();
        assertThat(ottUserDynamicDataResponse.results.getKey()).isEqualTo(keyString);
        assertThat(ottUserDynamicDataResponse.results.getValue().getValue()).isEqualTo(valueString);

        // get user
        Response<OTTUser> ottUserResponse = executor.executeSync(OttUserService.get()
                .setKs(userKs));

        // assert get user data
        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results.getDynamicData()).isNotNull();
        assertThat(ottUserResponse.results.getDynamicData().get(keyString).getValue()).isEqualTo(valueString);

        // cleanup
        executor.executeSync(HouseholdService.delete().setKs(userKs));
    }
}
