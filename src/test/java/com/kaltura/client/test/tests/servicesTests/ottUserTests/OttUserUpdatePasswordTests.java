package com.kaltura.client.test.tests.servicesTests.ottUserTests;

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
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserUpdatePasswordTests extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/updateLoginData - updateLoginData")
    @Test
    private void updatePassword() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // update password
        String newPassword = "newPassword";
        UpdatePasswordOttUserBuilder updatePasswordOttUserBuilder = OttUserService.updatePassword(Integer.parseInt(user.getId()), newPassword);
        updatePasswordOttUserBuilder.setKs(getAdministratorKs());
        executor.executeSync(updatePasswordOttUserBuilder);

        // login with old password
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());

        // login with new password
        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), newPassword));
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession().getKs()).isNotNull();

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }
}
