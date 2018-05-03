package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.Client;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateLoginDataTests extends BaseTest {

    private Response<Boolean> booleanResponse;
    private Response<OTTUser> ottUserResponse;
    private OTTUser user;


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/updateLoginData - updateLoginData")
    @Test
    private void updateLoginData() {

        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
        OTTUser user = ottUserResponse.results;
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword, null, null));
        String userKs = loginResponse.results.getLoginSession().getKs();

        UpdateLoginDataOttUserBuilder updateLoginDataOttUserBuilder = OttUserService.updateLoginData(user.getUsername(), defaultUserPassword, defaultUserPassword + 1);
        updateLoginDataOttUserBuilder.setKs(userKs);
        Response<Boolean> booleanResponse = executor.executeSync(updateLoginDataOttUserBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // try login with old password


        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword, null, null));
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());

        // try login with new password

        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword + 1, null, null));
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession().getKs()).isNotNull();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/updateLoginData - updateLoginData with administratorKs")
    @Test
    private void updateLoginData_with_administratorKs() {


        ottUserResponse = executor.executeSync(register(partnerId, user, defaultUserPassword));
        OTTUser user = ottUserResponse.results;

        UpdateLoginDataOttUserBuilder updateLoginDataOttUserBuilder = OttUserService.updateLoginData(user.getUsername(), defaultUserPassword, defaultUserPassword + 1);
        updateLoginDataOttUserBuilder.setKs(getAdministratorKs());
        booleanResponse = executor.executeSync(updateLoginDataOttUserBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // try login with old password

        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword, null, null));
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());

        // try login with new password

        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword + 1, null, null));
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession().getKs()).isNotNull();
    }
}
