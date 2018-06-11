package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.services.AppTokenService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AppTokenUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.AppTokenService.AddAppTokenBuilder;
import static com.kaltura.client.services.AppTokenService.GetAppTokenBuilder;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.*;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTokenAddTests extends BaseTest {

    private String sessionUserId;
    private AppToken appToken;
    private String sessionPrivileges;
    Response<AppToken> addAppTokenResponseSlowTest;

    // TODO: 5/3/2018 Add comments!
    @BeforeClass
    private void add_tests_before_class() {
        sessionUserId = getSharedUser().getUserId();
        appToken = AppTokenUtils.addAppToken(sessionUserId, AppTokenHashType.SHA1, null, null);
    }

    @Description("appToken/action/add")
    @Test
    private void addAppToken() {

        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getOperatorKs());
        Response<AppToken> appTokenResponse = executor.executeSync(addAppTokenBuilder);

        // Verify no error returned
        assertThat(appTokenResponse.error).isNull();
        assertThat(appTokenResponse.results.getExpiry()).isNull();
        assertThat(appTokenResponse.results.getId()).isNotEmpty();
        assertThat(appTokenResponse.results.getSessionDuration()).isGreaterThan(0);
        assertThat(appTokenResponse.results.getHashType()).isEqualTo(AppTokenHashType.SHA1);
        assertThat(appTokenResponse.results.getToken()).isNotEmpty();
        assertThat(appTokenResponse.results.getSessionUserId()).isEqualTo(sessionUserId);
        assertThat(appTokenResponse.results.getPartnerId()).isEqualTo(partnerId);
        assertThat(appTokenResponse.results.getSessionUserId()).isEqualTo(String.valueOf(sessionUserId));
    }

    @Description("appToken/action/add - without hash type")
    @Test
    private void addAppTokenWithDefaultHashType() {
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, null, null);
        
        // Invoke AppToken/action/add - with no hash type (will return the default hash type)
        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getOperatorKs());
        Response<AppToken> appTokenResponse = executor.executeSync(addAppTokenBuilder);

        // Verify that hashType = SHA256
        assertThat(appTokenResponse.results.getHashType()).isEqualTo(AppTokenHashType.SHA256);
    }

    @Description("appToken/action/add - with privileges")
    @Test
    private void addAppTokenWithPrivileges() {
        sessionPrivileges = "key1:value1,key2:value2";
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, sessionPrivileges, null);

        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getOperatorKs());
        Response<AppToken> appTokenResponse = executor.executeSync(addAppTokenBuilder);

        assertThat(appTokenResponse.results.getSessionPrivileges()).isEqualTo(sessionPrivileges);
    }

    @Description("appToken/action/add - with expiry date")
    @Test(groups = "slow_before")
    private void addAppTokenWithExpiryDate_before() {
        // setup for test
        System.out.println("before started");
        add_tests_before_class();
        Long expiryDate = BaseUtils.getTimeInEpoch(1);
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, sessionPrivileges, Math.toIntExact(expiryDate));

        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getOperatorKs());
        addAppTokenResponseSlowTest = executor.executeSync(addAppTokenBuilder);

        assertThat(addAppTokenResponseSlowTest.results.getExpiry()).isEqualTo(Math.toIntExact(expiryDate));

        // Wait until token is expired (according to expiry date)
        System.out.println("Waiting until token expiry date reached");
        System.out.println("before finished");
    }

    @Test(groups = "slow_after", dependsOnMethods = "addAppTokenWithExpiryDate_before")
    private void addAppTokenWithExpiryDate_after() {
        System.out.println("after started");
        GetAppTokenBuilder getAppTokenBuilder = AppTokenService.get(addAppTokenResponseSlowTest.results.getId())
                .setKs(getOperatorKs());
        Response<AppToken> getAppTokenResponse = executor.executeSync(getAppTokenBuilder);

        assertThat(getAppTokenResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
        System.out.println("after finished");
    }

    @Description("appToken/action/add - with no expiry date (return default expiry date -" +
            "According to app_token_max_expiry_seconds key value in group_203 CB document")
    @Test
    //todo - Add specific mark indicating the version of the feature
    private void addAppTokenWithNoExpiryDate() {
        getSharedHousehold();
        int expiryDate = 0;
        //int cbExpiryDateValue = 2592000;
        appToken = AppTokenUtils.addAppToken(null, null, sessionPrivileges, expiryDate);

        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getSharedMasterUserKs());
        Response<AppToken> addAppTokenResponse = executor.executeSync(addAppTokenBuilder);

        assertThat(addAppTokenResponse.results.getExpiry()).isGreaterThan(Math.toIntExact(BaseUtils.getTimeInEpoch(0)));
    }

    @Description("appToken/action/add - with no specific user id")
    @Test
    private void addAppTokenWithoutSpecificUserId() {
        appToken = AppTokenUtils.addAppToken(null, null, sessionPrivileges, null);

        AddAppTokenBuilder addAppTokenBuilder = AppTokenService.add(appToken)
                .setKs(getOperatorKs());
        Response<AppToken> addAppTokenResponse = executor.executeSync(addAppTokenBuilder);

        assertThat(addAppTokenResponse.error).isNull();
        assertThat(addAppTokenResponse.results.getExpiry()).isNull();
        assertThat(addAppTokenResponse.results.getId()).isNotEmpty();
        assertThat(addAppTokenResponse.results.getToken()).isNotEmpty();
        assertThat(addAppTokenResponse.results.getSessionUserId()).isNotEqualTo(sessionUserId);
        assertThat(addAppTokenResponse.results.getPartnerId()).isEqualTo(partnerId);
    }
}
