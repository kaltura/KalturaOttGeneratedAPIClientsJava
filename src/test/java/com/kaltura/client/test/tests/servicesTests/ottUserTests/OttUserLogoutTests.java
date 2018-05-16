package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserLogoutTests extends BaseTest {

    private Response<LoginResponse> loginResponse;
    private Response<Boolean> booleanResponse;
    private Response<OTTUser> ottUserResponse;


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/logout - logout")
    @Test
    private void logout() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user
        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        String userKs = loginResponse.results.getLoginSession().getKs();

        // logout user
        LogoutOttUserBuilder logoutOttUserBuilder = OttUserService.logout().setKs(userKs);
        booleanResponse = executor.executeSync(logoutOttUserBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert can't get user after logout
        GetOttUserBuilder getOttUserBuilder = OttUserService.get().setKs(userKs);
        ottUserResponse = executor.executeSync(getOttUserBuilder);
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500016).getCode());

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/logout - logout")
    @Test
    private void logout_from_specific_platform() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // login user without device
        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword));
        String userWithoutDeviceKs = loginResponse.results.getLoginSession().getKs();

        // login user with device
        String udid = "123456";
        loginResponse = executor.executeSync(login(partnerId, user.getUsername(), defaultUserPassword, null, udid));
        String userWithDeviceKs = loginResponse.results.getLoginSession().getKs();

        user = loginResponse.results.getUser();

        // logout user using device ks
        LogoutOttUserBuilder logoutOttUserBuilder = OttUserService.logout().setKs(userWithDeviceKs);
        booleanResponse = executor.executeSync(logoutOttUserBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert can't 'get' user using device ks
        GetOttUserBuilder getOttUserBuilder = OttUserService.get().setKs(userWithDeviceKs);
        ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500016).getCode());

        // assert can 'get' user using no-device ks
        getOttUserBuilder = OttUserService.get().setKs(userWithoutDeviceKs);
        ottUserResponse = executor.executeSync(getOttUserBuilder);

        assertThat(ottUserResponse.error).isNull();
        assertThat(ottUserResponse.results).isEqualToIgnoringGivenFields(user, "userState", "userType");

        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }


}
