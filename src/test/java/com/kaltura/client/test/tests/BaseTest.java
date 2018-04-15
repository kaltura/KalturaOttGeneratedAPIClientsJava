package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.Logger;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.anonymousLogin;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.utils.HouseholdUtils.createHouseHold;
import static com.kaltura.client.test.utils.HouseholdUtils.getUsersListFromHouseHold;
import static com.kaltura.client.test.utils.OttUserUtils.getUserById;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    private static Client client;
    private static Configuration config;

    private Response<LoginResponse> loginResponse;
    private Response<LoginSession> loginSession;

    public static String administratorKs, operatorKs, managerKs, anonymousKs;

    // shared household
    public static Household sharedHousehold;
    public static HouseholdUser sharedMasterUser, sharedUser;
    public static String sharedMasterUserKs, sharedUserKs;

    // shared VOD
    public static MediaAsset mediaAsset;

    @BeforeSuite
    public void base_test_before_suite() {
        Logger.getLogger(BaseTest.class).debug("Start Setup!");

        // set configuration
        config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);

        // set client
        client = getClient(null);

        // Set default awaitility timeout
        setDefaultTimeout(20, TimeUnit.SECONDS);

        // Login with shared users
        loginResponse = login(client, PARTNER_ID, getProperty(ADMINISTRATOR_USERNAME), getProperty(ADMINISTRATOR_PASSWORD), null, null);
        administratorKs = loginResponse.results.getLoginSession().getKs();

        loginResponse = login(client, PARTNER_ID, getProperty(OPERATOR_USERNAME), getProperty(OPERATOR_PASSWORD), null, null);
        operatorKs = loginResponse.results.getLoginSession().getKs();

//        loginResponse = login(PARTNER_ID, getProperty(MANAGER_USERNAME), getProperty(MANAGER_PASSWORD), null, null);
//        managerKs = loginResponse.results.getLoginSession().getKs();

        loginSession = anonymousLogin(client, PARTNER_ID, null);
        anonymousKs = loginSession.results.getKs();

        // Set project shared HH and users
//        initSharedHousehold();
//
//        mediaAsset = IngestVODUtils.ingestVOD(Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
//                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
//        System.out.println("INGESTED VOD: " + mediaAsset.getId());

        Logger.getLogger(BaseTest.class).debug("Finish Setup!");
    }

    private void initSharedHousehold() {
        sharedHousehold = createHouseHold(2, 2, false);
        List<HouseholdUser> sharedHouseholdUsers = getUsersListFromHouseHold(sharedHousehold);
        for (HouseholdUser user : sharedHouseholdUsers) {
            if (user.getIsMaster() != null && user.getIsMaster()) {
                sharedMasterUser = user;
            }
            if (user.getIsMaster() == null && user.getIsDefault() == null) {
                sharedUser = user;
            }
        }

        loginResponse = login(client, PARTNER_ID, getUserById(Integer.parseInt(sharedMasterUser.getUserId())).getUsername(), GLOBAL_USER_PASSWORD, null, null);
        sharedMasterUserKs = loginResponse.results.getLoginSession().getKs();

        loginResponse = login(client, PARTNER_ID, getUserById(Integer.parseInt(sharedUser.getUserId())).getUsername(), GLOBAL_USER_PASSWORD, null, null);
        sharedUserKs = loginResponse.results.getLoginSession().getKs();
    }

    public static Client getClient(String ks) {
        client = new Client(config);
        client.setApiVersion(API_REQUEST_VERSION);
        client.setKs(ks);
        return client;
    }
}
