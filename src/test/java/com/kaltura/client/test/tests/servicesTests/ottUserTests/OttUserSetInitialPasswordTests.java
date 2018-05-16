package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.LoginSession;
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

public class OttUserSetInitialPasswordTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/setInitialPassword")
    @Test()
    private void setInitialPassword() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // reset user password
        executor.executeSync(resetPassword(partnerId, user.getUsername()));

        // get resetPassword token
        String resetPasswordToken = DBUtils.getResetPasswordToken(user.getUsername());

        // set initial password
        String newPassword = "newPassword";
        SetInitialPasswordOttUserBuilder setInitialPasswordOttUserBuilder = OttUserService.setInitialPassword(partnerId, resetPasswordToken, newPassword);
        Response<OTTUser> ottUserResponse = executor.executeSync(setInitialPasswordOttUserBuilder);

        assertThat(ottUserResponse.results).isEqualToComparingOnlyGivenFields(user, "username", "email",
                "address", "firstName", "lastName", "city", "externalId", "countryId");

        // login with new password
        LoginSession loginSession = executor.executeSync(login(partnerId, user.getUsername(), newPassword)).results.getLoginSession();
        assertThat(loginSession.getKs()).isNotNull();

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("ottUser/action/setInitialPassword - invalid resetPassword token")
    @Test()
    private void setInitialPassword_with_invalid_resetPassword_token() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // reset user password
        executor.executeSync(resetPassword(partnerId, user.getUsername()));

        // set initial password with invalid token
        String newPassword = "newPassword";
        String invalidToken = "invalidToken";

        SetInitialPasswordOttUserBuilder setInitialPasswordOttUserBuilder = OttUserService.setInitialPassword(partnerId, invalidToken, newPassword);
        Response<OTTUser> ottUserResponse = executor.executeSync(setInitialPasswordOttUserBuilder);

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/setInitialPassword - expired resetPassword token")
    @Test()
    private void setInitialPassword_with_expired_resetPassword_token() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // reset user password
        executor.executeSync(resetPassword(partnerId, user.getUsername()));

        // get resetPassword token
        String resetPasswordToken = DBUtils.getResetPasswordToken(user.getUsername());

        // set initial password - first time
        String newPassword = "newPassword";

        SetInitialPasswordOttUserBuilder setInitialPasswordOttUserBuilder = OttUserService.setInitialPassword(partnerId, resetPasswordToken, newPassword);
        executor.executeSync(setInitialPasswordOttUserBuilder);

        // set initial password - second time
        Response<OTTUser> ottUserResponse = executor.executeSync(setInitialPasswordOttUserBuilder);

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2000).getCode());

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

}
