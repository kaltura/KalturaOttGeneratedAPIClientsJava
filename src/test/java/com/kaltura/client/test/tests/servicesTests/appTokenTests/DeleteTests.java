package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.test.servicesImpl.AppTokenServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AppTokenUtils;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTests extends BaseTest {

    private AppTokenHashType hashType;
    private String sessionUserId;
    private AppToken appToken = new AppToken();
    public static Client client;

    @BeforeClass
    private void add_tests_before_class() {
        sessionUserId = "1577578";
        client = getClient(operatorKs);
        hashType = AppTokenHashType.SHA1;
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, null, null);
    }

    @Description("appToken/action/delete")
    @Test
    private void addAppToken() {
        // Add token
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);
        assertThat(appTokenResponse.error).isNull();
        assertThat(appTokenResponse.results.getExpiry()).isNull();

        // Delete token
        Response<Boolean> deleteTokenResponse = AppTokenServiceImpl.delete(client, appTokenResponse.results.getId());
        assertThat(deleteTokenResponse.results).isTrue();

        // Try to delete token using invalid token id
        deleteTokenResponse = AppTokenServiceImpl.delete(client, "1234");
        assertThat(deleteTokenResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());

        // Try to delete token again - exception returned
        deleteTokenResponse = AppTokenServiceImpl.delete(client, appTokenResponse.results.getId());
        assertThat(deleteTokenResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }

}