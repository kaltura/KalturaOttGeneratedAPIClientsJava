package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.delete;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserRegisterTests extends BaseTest {

    private OTTUser user;
    private Response<OTTUser> ottUserResponse;

    @BeforeClass
    private void ottUser_login_tests_setup() {
        user = generateOttUser();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/register - register")
    @Test
    private void register() {
        ottUserResponse = executor.executeSync(OttUserService.register(partnerId, user, defaultUserPassword));

        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType",
                "householdId", "dynamicData", "isHouseholdMaster", "suspensionState", "id", "params");

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    //<throws name="UserExists"/>
    //<throws name="ExternalIdAlreadyExists"/>
}
