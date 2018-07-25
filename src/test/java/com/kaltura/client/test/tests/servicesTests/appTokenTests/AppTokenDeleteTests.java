package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.AppTokenService.add;
import static com.kaltura.client.services.AppTokenService.delete;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.getSharedUser;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTokenDeleteTests extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("appToken/action/delete")
    @Test
    private void deleteAppToken() {
        // Add token
        String sessionUserId = getSharedUser().getUserId();

        AppToken appToken = new AppToken();
        appToken.setSessionUserId(sessionUserId);
        appToken.setHashType(AppTokenHashType.SHA1);

        Response<AppToken> appTokenResponse = executor.executeSync(add(appToken)
                .setKs(getOperatorKs()));

        assertThat(appTokenResponse.error).isNull();
        assertThat(appTokenResponse.results.getExpiry()).isNull();

        // Delete token
        Response<Boolean> booleanResponse = executor.executeSync(delete(appTokenResponse.results.getId())
                .setKs(getOperatorKs()));

        assertThat(booleanResponse.results).isTrue();
    }

    @Severity(SeverityLevel.MINOR)
    @Description("appToken/action/delete - invalid token")
    @Test
    private void deleteInvalidAppToken() {
        // Try to delete token using invalid token id
        String invalidTokenId = "1234";
        Response<Boolean> booleanResponse = executor.executeSync(delete(invalidTokenId)
                .setKs(getOperatorKs()));

        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("appToken/action/delete - same token twice")
    @Test
    private void deleteSameAppTokenTwice() {
        // Add token
        String sessionUserId = getSharedUser().getUserId();

        AppToken appToken = new AppToken();
        appToken.setSessionUserId(sessionUserId);
        appToken.setHashType(AppTokenHashType.SHA1);

        Response<AppToken> appTokenResponse = executor.executeSync(add(appToken)
                .setKs(getOperatorKs()));

        assertThat(appTokenResponse.error).isNull();
        assertThat(appTokenResponse.results.getExpiry()).isNull();

        // Delete token
        Response<Boolean> booleanResponse = executor.executeSync(delete(appTokenResponse.results.getId())
                .setKs(getOperatorKs()));

        assertThat(booleanResponse.results).isTrue();

        // Try to delete token again - exception returned
        booleanResponse = executor.executeSync(delete(appTokenResponse.results.getId())
                .setKs(getOperatorKs()));

        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }

}