package com.kaltura.client.test.tests.servicesTests.appTokenTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.test.servicesImpl.AppTokenServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AppTokenUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.AppToken;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddTests extends BaseTest {

    private AppTokenHashType hashType;
    private String sessionUserId;
    private AppToken appToken = new AppToken();


    @BeforeClass
    private void add_tests_before_class() {
        hashType = AppTokenHashType.SHA1;
        sessionUserId = OttUserUtils.generateOttUser().getId();
        appToken = AppTokenUtils.addAppToken(sessionUserId,hashType );


    }

    @Description("appToken/action/add")
    @Test

    private void addAppToken() {
        Response<AppToken> appTokenResponse = AppTokenServiceImpl.add()
    }
}
