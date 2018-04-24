package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.test.utils.IngestVODUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.Optional;
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
    private static Response<LoginResponse> loginResponse;
    private static Configuration config;

    // shared ks's
    private static String administratorKs, operatorKs, managerKs, anonymousKs;

    // shared household
    private static Household sharedHousehold;
    private static HouseholdUser sharedMasterUser, sharedUser;
    private static String sharedMasterUserKs, sharedUserKs;

    // shared VOD
    private static MediaAsset mediaAsset;


    @BeforeSuite
    public void base_test_before_suite() {
        // set configuration
        config = new Configuration();
        config.setEndpoint(API_BASE_URL + "/" + API_URL_VERSION);
        config.setAcceptGzipEncoding(false);
        client = getClient(null);

        // Set default awaitility timeout
        setDefaultTimeout(30, TimeUnit.SECONDS);
    }


    public static Client getClient(String ks) {
        Client client = new Client(config);
        client.setApiVersion(API_REQUEST_VERSION);
        client.setKs(ks);
        return client;
    }

    // getters for shared params
    public static String getAdministratorKs() {
        if (administratorKs == null) {
            loginResponse = login(client, PARTNER_ID, getProperty(ADMINISTRATOR_USERNAME), getProperty(ADMINISTRATOR_PASSWORD), null, null);
            administratorKs = loginResponse.results.getLoginSession().getKs();
        }
        return administratorKs;
    }

    public static String getOperatorKs() {
        if (operatorKs == null) {
            loginResponse = login(client, PARTNER_ID, getProperty(OPERATOR_USERNAME), getProperty(OPERATOR_PASSWORD), null, null);
            operatorKs = loginResponse.results.getLoginSession().getKs();
        }
        return operatorKs;
    }

    public static String getManagerKs() {
        if (managerKs == null) {
            loginResponse = login(client, PARTNER_ID, getProperty(MANAGER_USERNAME), getProperty(MANAGER_PASSWORD), null, null);
            managerKs = loginResponse.results.getLoginSession().getKs();
        }
        return managerKs;
    }

    public static String getAnonymousKs() {
        if (anonymousKs == null) {
            Response<LoginSession> loginSession = anonymousLogin(client, PARTNER_ID, null);
            anonymousKs = loginSession.results.getKs();
        }
        return anonymousKs;
    }

    public static MediaAsset getSharedMediaAsset() {
        if (mediaAsset == null) {
            mediaAsset = IngestVODUtils.ingestVOD(Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
            System.out.println("INGESTED VOD: " + mediaAsset.getId());
        }
        return mediaAsset;
    }

    public static Household getSharedHousehold() {
        Client client = getClient(null);

        if (sharedHousehold == null) {
            sharedHousehold = createHouseHold(2, 2, true);
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
        return sharedHousehold;
    }

    public static String getsharedMasterUserKs() {
        if (sharedHousehold == null) getSharedHousehold();
        return sharedMasterUserKs;
    }

    public static String getsharedUserKs() {
        if (sharedHousehold == null) getSharedHousehold();
        return sharedUserKs;
    }
}
