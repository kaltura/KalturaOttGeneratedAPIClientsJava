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

public class GetTests extends BaseTest {

    final private String sessionUserId = "1577578";
    private String sessionPrivileges = "key1:value1,key2:value2";
    private AppTokenHashType hashType;
    public static Client client;
    Long expiryDate;
    int offSetInMinutes = 1;
    int sessionDuration = 86400;
    AppToken appToken;

    @BeforeClass
    private void get_tests_before_class() {
        client = getClient(operatorKs);
        hashType = AppTokenHashType.SHA1;

        expiryDate = BaseUtils.getTimeInEpoch(offSetInMinutes);
    }

    @Description("AppToken/action/get")
    @Test

    private void getAppToken() {
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, sessionPrivileges, Math.toIntExact(expiryDate));
        Response<AppToken> addAppTokenResponse = AppTokenServiceImpl.add(client, appToken);
        Response<AppToken> getAppTokenResponse = AppTokenServiceImpl.get(client, addAppTokenResponse.results.getId());

        assertThat(getAppTokenResponse.results.getId()).isEqualTo(addAppTokenResponse.results.getId());
        assertThat(getAppTokenResponse.results.getExpiry()).isEqualTo(Math.toIntExact(expiryDate));
        assertThat(getAppTokenResponse.results.getPartnerId()).isEqualTo(Properties.PARTNER_ID);
        assertThat(getAppTokenResponse.results.getSessionDuration()).isEqualTo(sessionDuration);
        assertThat(getAppTokenResponse.results.getHashType()).isEqualTo(hashType);
        assertThat(getAppTokenResponse.results.getSessionPrivileges()).isEqualTo(sessionPrivileges);
        assertThat(getAppTokenResponse.results.getToken()).isEqualTo(addAppTokenResponse.results.getToken());
        assertThat(getAppTokenResponse.results.getSessionUserId()).isEqualTo(sessionUserId);
    }

    @Description("AppToken/action/get")
    @Test
    private void getAppTokenWithInvalidId() {
        Response<AppToken> getAppTokenResponse = AppTokenServiceImpl.get(client, "1234");
        assertThat(getAppTokenResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500055).getCode());
    }


}
