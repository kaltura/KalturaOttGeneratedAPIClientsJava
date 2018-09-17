package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.delete;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.dynamicDataMapBuilder;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserRegisterTests extends BaseTest {

    private Response<OTTUser> ottUserResponse;


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/register - register")
    @Test
    private void register() {
        // generate user
        OTTUser user = generateOttUser();

        // set dynamic date
        user.setDynamicData(dynamicDataMapBuilder("key", "value"));

        // register
        ottUserResponse = executor.executeSync(OttUserService.register(partnerId, user, defaultUserPassword));
        user = ottUserResponse.results;

        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType",
                "householdId", "dynamicData", "isHouseholdMaster", "suspensionState", "id", "params");

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/register - user exists - error 2014")
    @Test
    private void register_exists_user() {
        // generate user
        OTTUser user = generateOttUser();

        // register - first time
        ottUserResponse = executor.executeSync(OttUserService.register(partnerId, user, defaultUserPassword));
        String userId = ottUserResponse.results.getId();

        // register -  second time
        ottUserResponse = executor.executeSync(OttUserService.register(partnerId, user, defaultUserPassword));

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2014).getCode());

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(userId)));
    }

    //<throws name="UserExists"/>
    //<throws name="ExternalIdAlreadyExists"/>
}
