package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.test.Properties;
import com.kaltura.client.test.servicesImpl.AppTokenServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AppTokenUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class AddTests extends BaseTest {

    private AppTokenHashType hashType;
    private String sessionUserId;
    private AppToken appToken = new AppToken();
    public static Client client;
    private String sessionPrivileges;


    @BeforeClass
    private void add_tests_before_class() {
        sessionUserId = "1577578";
        client = getClient(operatorKs);
        hashType = AppTokenHashType.SHA1;
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, null, null);
    }

    @Description("appToken/action/add")
    @Test
    private void addAppToken() {
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);

        // Assertions
        // ****************************

        // Verify no error returned
        assertThat(appTokenResponse.error).isNull();
        assertThat(appTokenResponse.results.getExpiry()).isNull();
        assertThat(appTokenResponse.results.getId()).isNotEmpty();
        assertThat(appTokenResponse.results.getSessionDuration()).isGreaterThan(0);
        assertThat(appTokenResponse.results.getHashType()).isEqualTo(this.hashType);
        assertThat(appTokenResponse.results.getToken()).isNotEmpty();
        assertThat(appTokenResponse.results.getSessionUserId()).isEqualTo(this.sessionUserId);
        assertThat(appTokenResponse.results.getPartnerId()).isEqualTo(Properties.PARTNER_ID);
        assertThat(appTokenResponse.results.getSessionUserId()).isEqualTo(String.valueOf(this.sessionUserId));
    }

    @Description("appToken/action/add - without hash type")
    @Test
    private void addAppTokenWithDefaultHashType() {
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, null, null);
        // Invoke AppToken/action/add - with no hash type (will return the default hash type)
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);
        // Verify that hashType = SHA256
        assertThat(appTokenResponse.results.getHashType()).isEqualTo(AppTokenHashType.SHA256);
    }

    @Description("appToken/action/add - with privileges")
    @Test
    private void addAppTokenWithPrivileges() {
        sessionPrivileges = "key1:value1,key2:value2";
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, sessionPrivileges, null);
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);

        assertThat(appTokenResponse.results.getSessionPrivileges()).isEqualTo(sessionPrivileges);
    }

    @Description("appToken/action/add - with expiry date")
    @Test
    private void addAppTokenWithExpiryDate() {
        Long expiryDate = BaseUtils.getTimeInEpoch(1);
        appToken = AppTokenUtils.addAppToken(sessionUserId, null, sessionPrivileges, Math.toIntExact(expiryDate));
        Response<AppToken> addAppTokenResponse = AppTokenServiceImpl.add(client, appToken);
        assertThat(addAppTokenResponse.results.getExpiry()).isEqualTo(Math.toIntExact(expiryDate));

         // Wait until token is expired (according to expiry date)
        System.out.println("Waiting 1 minute until token expiry date reached");

        try {
            Thread.sleep(72000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Response<AppToken> getAppTokenResponse = AppTokenServiceImpl.get(client, addAppTokenResponse.results.getId());
        assertThat(getAppTokenResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }
}
