package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.test.Properties;
import com.kaltura.client.test.servicesImpl.AppTokenServiceImpl;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AppTokenUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.SessionInfo;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartSessionTests extends BaseTest {

    private AppTokenHashType hashType;
    private String sessionUserId = "1577578";
    private String udid1 = "1234567890";
    private String udid2 = "9876543210";
    private AppToken appToken = new AppToken();
    public static Client client;
    private String sessionPrivileges = "key1:value1,key2:value2";
    private Long expiryDate;
    private String anonymousKs;


    @BeforeClass
    private void add_tests_before_class() {
        client = getClient(null);
        // Invoke ottUser/action/anonymousLogin to receive LoginSession object (and anonymous KS)
        Response<LoginSession> loginSessionResponse = OttUserServiceImpl.anonymousLogin(client, Properties.PARTNER_ID, udid1);
        anonymousKs = loginSessionResponse.results.getKs();
        client.setKs(operatorKs);
        expiryDate = BaseUtils.getTimeInEpoch(1);
    }

    @Description("appToken/action/startSession - SHA1")
    @Test
    private void startSessionSha1() {
        hashType = AppTokenHashType.SHA1;
        // Build appToken object
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, sessionPrivileges, Math.toIntExact(expiryDate));
        // Invoke AppToken/action/add
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);
        client.setKs(anonymousKs);

        // Generate new token hash
        String tokenHash = AppTokenUtils.getTokenHash(hashType, anonymousKs, appTokenResponse.results.getToken());
        // Invoke AppToken/action/startSession - with udid1
        Response<SessionInfo> sessionInfoResponse = AppTokenServiceImpl.startSession(client, appTokenResponse.results.getId()
                , tokenHash, null, Math.toIntExact(expiryDate), udid1);

        assertThat(sessionInfoResponse.results.getKs()).isNotEmpty();
        assertThat(sessionInfoResponse.results.getPartnerId()).isEqualTo(Properties.PARTNER_ID);
        assertThat(sessionInfoResponse.results.getUserId()).isEqualTo(sessionUserId);
        assertThat(sessionInfoResponse.results.getExpiry()).isEqualTo(Math.toIntExact(expiryDate));
        assertThat(sessionInfoResponse.results.getPrivileges()).contains(sessionPrivileges);
        assertThat(sessionInfoResponse.results.getUdid()).isEqualTo(udid1);
        assertThat(sessionInfoResponse.results.getCreateDate()).isNotZero();

        // Invoke AppToken/action/startSession - with udid2
        sessionInfoResponse = AppTokenServiceImpl.startSession(client, appTokenResponse.results.getId()
                , tokenHash, null, Math.toIntExact(expiryDate), udid2);

        assertThat(sessionInfoResponse.results.getKs()).isNotEmpty();

        // TODO - Add session/action/get request with ks received from startSession API

    }

    @Description("appToken/action/startSession - SHA256")
    @Test
    private void startSessionSha256() {
        hashType = AppTokenHashType.SHA256;
        // Build appToken object
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, sessionPrivileges, Math.toIntExact(expiryDate));
        // Invoke AppToken/action/add
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);
        client.setKs(anonymousKs);
        // Generate new token hash
        String tokenHash = AppTokenUtils.getTokenHash(hashType, anonymousKs, appTokenResponse.results.getToken());
        // // Invoke AppToken/action/startSession - with udid1
        Response<SessionInfo> sessionInfoResponse = AppTokenServiceImpl.startSession(client, appTokenResponse.results.getId()
                , tokenHash, null, Math.toIntExact(expiryDate), udid1);

        assertThat(sessionInfoResponse.results.getKs()).isNotEmpty();
        assertThat(sessionInfoResponse.results.getPartnerId()).isEqualTo(Properties.PARTNER_ID);
        assertThat(sessionInfoResponse.results.getUserId()).isEqualTo(sessionUserId);
        assertThat(sessionInfoResponse.results.getExpiry()).isEqualTo(expiryDate);
        assertThat(sessionInfoResponse.results.getPrivileges()).isEqualTo(sessionPrivileges);
        assertThat(sessionInfoResponse.results.getUdid()).isEqualTo(udid1);
        assertThat(sessionInfoResponse.results.getCreateDate()).isNotZero();

        // Invoke AppToken/action/startSession - with udid2
        sessionInfoResponse = AppTokenServiceImpl.startSession(client, appTokenResponse.results.getId()
                , tokenHash, null, Math.toIntExact(expiryDate), udid2);

        assertThat(sessionInfoResponse.results.getKs()).isNotEmpty();

        // TODO - Add session/action/get request with ks received from startSession API
    }
}
