package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.UserLoginPin;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.services.UserLoginPinService.AddUserLoginPinBuilder;
import static com.kaltura.client.services.UserLoginPinService.add;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class OttUserLoginWithPinTests extends BaseTest {

    private OTTUser user;

    private Response<LoginResponse> loginResponse;
    private Response<UserLoginPin> userLoginPinResponse;

    private final String SECRET = "secret";

    @BeforeClass
    private void ottUser_login_tests_setup() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/loginWithPin - loginWithPin with secret")
    @Test
    private void loginWithPin_with_secret() {
        // add pin
        AddUserLoginPinBuilder addUserLoginPinBuilder = add(SECRET)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        userLoginPinResponse = executor.executeSync(addUserLoginPinBuilder);

        // login with pin
        String pin = userLoginPinResponse.results.getPinCode();
        LoginWithPinOttUserBuilder loginWithPinOttUserBuilder = loginWithPin(partnerId, pin, null, SECRET);
        loginResponse = executor.executeSync(loginWithPinOttUserBuilder);

        // assert
        assertThat(loginResponse.error).isNull();
        assertThat(loginResponse.results.getLoginSession()).isNotNull();
        assertThat(loginResponse.results.getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/loginWithPin - loginWithPin with invalid secret - error 2008")
    @Test
    private void loginWithPin_with_invalid_secret() {
        // add pin
        AddUserLoginPinBuilder addUserLoginPinBuilder = add(SECRET)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        userLoginPinResponse = executor.executeSync(addUserLoginPinBuilder);

        // login with pin and wrong secret
        String invalidSecret = SECRET + 1;
        String pin = userLoginPinResponse.results.getPinCode();
        LoginWithPinOttUserBuilder loginWithPinOttUserBuilder = loginWithPin(partnerId, pin, null, invalidSecret);
        loginResponse = executor.executeSync(loginWithPinOttUserBuilder);

        // assert error 2008 is return
        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2008).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/loginWithPin - loginWithPin with expired pinCode - error 2004")
    @Test(groups = "slow_before")
    private void loginWithPin_with_expired_pinCode_before() {
        // setup for test
        ottUser_login_tests_setup();
        // add pin
        AddUserLoginPinBuilder addUserLoginPinBuilder = add(SECRET)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        userLoginPinResponse = executor.executeSync(addUserLoginPinBuilder);
    }

    @Test(groups = "slow_after", dependsOnGroups = {"slow_before"}, priority = 2)
    private void loginWithPin_with_expired_pinCode_after() {
        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 10;
        int maxTimeExpectingValidResponseInSeconds = 200;
        // wait for pin to expire
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    long expire = userLoginPinResponse.results.getExpirationTime() + 120;
                    long now = BaseUtils.getEpochInLocalTime(0);
                    return now > expire;
                });

        LoginWithPinOttUserBuilder loginWithPinOttUserBuilder = loginWithPin(partnerId, userLoginPinResponse.results.getPinCode(), null, SECRET);
        loginResponse = executor.executeSync(loginWithPinOttUserBuilder);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2004).getCode());

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/loginWithPin - loginWithPin with invalid pin")
    @Test()
    private void loginWithPin_with_invalid_pin() {
        // login with invalid pin
        String invalidPin = "invalidPin";
        LoginWithPinOttUserBuilder loginWithPinOttUserBuilder = loginWithPin(partnerId, invalidPin, null, SECRET);
        loginResponse = executor.executeSync(loginWithPinOttUserBuilder);

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2003).getCode());
    }

//<throws name="LoginViaPinNotAllowed"/>
//<throws name="UserNotInDomain"/>
//<throws name="WrongPasswordOrUserName"/>
//<throws name="PinNotExists"/>
//<throws name="NoValidPin"/>
//<throws name="SecretIsWrong"/>
//<throws name="UserSuspended"/>
//<throws name="InsideLockTime"/>
//<throws name="UserNotActivated"/>
//<throws name="UserAllreadyLoggedIn"/>
//<throws name="UserDoubleLogIn"/>
//<throws name="DeviceNotRegistered"/>
//<throws name="UserNotMasterApproved"/>

    @AfterClass
    private void loginWithPin_afterClass() {
        // cleanup
        executor.executeSync(delete().setKs(getAdministratorKs()).setUserId(Integer.valueOf(user.getId())));
    }
}
