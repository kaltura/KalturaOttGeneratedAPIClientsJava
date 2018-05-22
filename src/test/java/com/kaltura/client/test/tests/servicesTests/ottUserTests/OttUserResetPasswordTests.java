package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import static com.kaltura.client.services.OttUserService.ResetPasswordOttUserBuilder;
import static com.kaltura.client.services.OttUserService.delete;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserResetPasswordTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/resetPassword - resetPassword")
    @Test()
    private void resetPassword() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // assert resetPassword token is null
        String resetPasswordToken = DBUtils.getResetPasswordToken(user.getUsername());
        assertThat(resetPasswordToken).isNull();

        // reset user password
        ResetPasswordOttUserBuilder resetPasswordOttUserBuilder = OttUserService.resetPassword(partnerId, user.getUsername());
        Response<Boolean> booleanResponse = executor.executeSync(resetPasswordOttUserBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert resetPassword token is not null or empty
        resetPasswordToken = DBUtils.getResetPasswordToken(user.getUsername());
        assertThat(Strings.isNotNullAndNotEmpty(resetPasswordToken)).isTrue();

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/resetPassword - resetPassword with invalid user - error 2000")
    @Test()
    private void resetPassword_with_invalid_user() {
        // register user
        String invalidUsername = "invalidUsername";

        // reset user password with invalid user
        ResetPasswordOttUserBuilder resetPasswordOttUserBuilder = OttUserService.resetPassword(partnerId, invalidUsername);
        Response<Boolean> booleanResponse = executor.executeSync(resetPasswordOttUserBuilder);

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());

    }
}
