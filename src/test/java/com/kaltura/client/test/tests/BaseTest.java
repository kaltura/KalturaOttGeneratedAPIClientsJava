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

    // shared common params
    public static int partnerId;
    public static String defaultUserPassword;

    // shared ks's
    private static String administratorKs, operatorKs, managerKs, anonymousKs;

    // shared VOD
    private static MediaAsset mediaAsset;

    /*
    // shared household params
    Household sharedHousehold;
    HouseholdUser sharedMasterUser, sharedUser;
    String sharedMasterUserKs, sharedUserKs;
    */

    @BeforeSuite
    public void base_test_before_suite() {
        partnerId = Integer.parseInt(getProperty(PARTNER_ID));
        defaultUserPassword = getProperty(DEFAULT_USER_PASSWORD);

        // set configuration
        config = new Configuration();
        config.setEndpoint(getProperty(API_BASE_URL) + "/" + getProperty(API_VERSION));
        config.setAcceptGzipEncoding(false);
        client = getClient(null);

        // Set default awaitility timeout
        setDefaultTimeout(30, TimeUnit.SECONDS);
    }


    public static Client getClient(String ks) {
        Client client = new Client(config);
        client.setKs(ks);
        return client;
    }

    // getters for shared params
    public static String getAdministratorKs() {
        if (administratorKs == null) {
            loginResponse = login(client, partnerId, getProperty(ADMINISTRATOR_USER_USERNAME), getProperty(ADMINISTRATOR_USER_PASSWORD), null, null);
            administratorKs = loginResponse.results.getLoginSession().getKs();
        }
        return administratorKs;
    }

    public static String getOperatorKs() {
        if (operatorKs == null) {
            loginResponse = login(client, partnerId, getProperty(OPERATOR_USER_USERNAME), getProperty(OPERATOR_USER_PASSWORD), null, null);
            operatorKs = loginResponse.results.getLoginSession().getKs();
        }
        return operatorKs;
    }

    public static String getManagerKs() {
        if (managerKs == null) {
            loginResponse = login(client, partnerId, getProperty(MANAGER_USER_USERNAME), getProperty(MANAGER_USER_PASSWORD), null, null);
            managerKs = loginResponse.results.getLoginSession().getKs();
        }
        return managerKs;
    }

    public static String getAnonymousKs() {
        if (anonymousKs == null) {
            Response<LoginSession> loginSession = anonymousLogin(client, partnerId, null);
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

    // shared household
    public static class SharedHousehold {

        private static Household sharedHousehold;
        private static HouseholdUser sharedMasterUser, sharedUser;
        private static String sharedMasterUserKs, sharedUserKs;


        public static Household getSharedHousehold() {
            Client client = getClient(null);
            int numOfUsers = 2;
            int numOfDevices = 2;

            if (sharedHousehold == null) {
                sharedHousehold = createHouseHold(numOfUsers, numOfDevices, true);
                List<HouseholdUser> sharedHouseholdUsers = getUsersListFromHouseHold(sharedHousehold);
                for (HouseholdUser user : sharedHouseholdUsers) {
                    if (user.getIsMaster() != null && user.getIsMaster()) {
                        sharedMasterUser = user;
                    }
                    if (user.getIsMaster() == null && user.getIsDefault() == null) {
                        sharedUser = user;
                    }
                }

                loginResponse = login(client, partnerId, getUserById(Integer.parseInt(sharedMasterUser.getUserId())).getUsername(), defaultUserPassword, null, null);
                sharedMasterUserKs = loginResponse.results.getLoginSession().getKs();

                loginResponse = login(client, partnerId, getUserById(Integer.parseInt(sharedUser.getUserId())).getUsername(), defaultUserPassword, null, null);
                sharedUserKs = loginResponse.results.getLoginSession().getKs();
            }
            return sharedHousehold;
        }

        public static String getSharedMasterUserKs() {
            if (sharedHousehold == null) getSharedHousehold();
            return sharedMasterUserKs;
        }

        public static String getSharedUserKs() {
            if (sharedHousehold == null) getSharedHousehold();
            return sharedUserKs;
        }

        public static HouseholdUser getSharedMasterUser() {
            if (sharedHousehold == null) getSharedHousehold();
            return sharedMasterUser;
        }

        public static HouseholdUser getSharedUser() {
            if (sharedHousehold == null) getSharedHousehold();
            return sharedUser;
        }
    }
}
