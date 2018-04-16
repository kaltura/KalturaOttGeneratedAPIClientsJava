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

public class StartSessionTests extends BaseTest {

    private AppTokenHashType hashType;
    private String sessionUserId = "1577578";
    private String udid = "1234567890";
    private AppToken appToken = new AppToken();
    public static Client client;
    //private String sessionPrivileges = "key1:value1,key2:value2";
    private Long expiryDate;
    private Response<LoginSession> loginSessionResponse;
    private String anonymousKs;


    @BeforeClass
    private void add_tests_before_class() {
        client = getClient(null);
        // Invoke ottUser/action/anonymousLogin to receive LoginSession object (and anonymous KS)
        loginSessionResponse = OttUserServiceImpl.anonymousLogin(client, Properties.PARTNER_ID, udid);
        anonymousKs = loginSessionResponse.results.getKs();

        client.setKs(operatorKs);
        hashType = AppTokenHashType.SHA1;
        expiryDate = BaseUtils.getTimeInEpoch(1);
        appToken = AppTokenUtils.addAppToken(sessionUserId, hashType, null, Math.toIntExact(expiryDate));
    }

    @Description("appToken/action/startSession")
    @Test

    private void startSession() {
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add(client, appToken);
        client.setKs(anonymousKs);
        String tokenHash = AppTokenUtils.getTokenHash(hashType,anonymousKs,appTokenResponse.results.getToken());
        Response<SessionInfo> sessionInfoResponse = AppTokenServiceImpl.startSession(client, appTokenResponse.results.getId()
                ,tokenHash, null, Math.toIntExact(expiryDate), udid);
    }
}
