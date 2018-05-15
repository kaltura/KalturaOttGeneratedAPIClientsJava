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
import static com.kaltura.client.services.OttUserService.ResetPasswordOttUserBuilder;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserResetPasswordTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/resetPassword - resetPassword")
    @Test(enabled = false)
    private void resetPassword() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // reset user password
        ResetPasswordOttUserBuilder resetPasswordOttUserBuilder = OttUserService.resetPassword(partnerId, user.getUsername())
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(resetPasswordOttUserBuilder);

        // assert success
        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // login after reset password
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        assertThat(loginResponse.results).isNull();

        // TODO: 4/1/2018 finsih the test after bug BEO-4884 will be fixed
    }
}
