package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.helper.Properties.*;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    public static Client client;
    private Response<LoginResponse> loginResponse;

    public static String administratorKS, operatorKS, managerKS;

    @BeforeSuite
    public void setup() {

        // Set client
        Configuration config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);
        client = new Client(config);
        client.setApiVersion(API_REQUEST_VERSION);

        // Set default awaitility timeout
        setDefaultTimeout(15, TimeUnit.SECONDS);

        // Login with shared users
        loginResponse = login(PARTNER_ID, getProperty(ADMINISTRATOR_USERNAME), getProperty(ADMINISTRATOR_PASSWORD), null, null);
        administratorKS = loginResponse.results.getLoginSession().getKs();

        loginResponse = login(PARTNER_ID, getProperty(OPERATOR_USERNAME), getProperty(OPERATOR_PASSWORD), null, null);
        operatorKS = loginResponse.results.getLoginSession().getKs();
//
//        loginResponse = login(PARTNER_ID, getProperty(MANAGER_USERNAME), getProperty(MANAGER_PASSWORD), null, null);
//        managerKS = loginResponse.results.getLoginSession().getKs();
    }
}
