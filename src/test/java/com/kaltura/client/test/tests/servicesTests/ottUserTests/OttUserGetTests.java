package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserGetTests extends BaseTest {

    private OTTUser user;
    private Response<LoginResponse> loginResponse;

    @BeforeClass
    private void ottUser_get_tests_setup() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        user = loginResponse.results.getUser();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/get - get")
    @Test
    private void get() {
        // get user
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setKs(loginResponse.results.getLoginSession().getKs());
        Response<OTTUser> ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(loginResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/get - get with super user ks")
    @Test
    private void get_with_superUserKs() {
        // get user
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<OTTUser> ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(loginResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType");
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/get - get with invalid userId - error 500004")
    @Test
    private void get_with_invalid_userId() {
        // get user
        int invalidUserId = 1;
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setKs(getAdministratorKs())
                .setUserId(invalidUserId);
        Response<OTTUser> ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500004).getCode());
    }

    @AfterClass
    private void get_afterClass() {
        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

}
