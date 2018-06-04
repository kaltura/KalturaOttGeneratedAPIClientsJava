package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.IngestConstants;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeSuite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.test.IngestConstants.FIVE_MINUTES_PERIOD;
import static com.kaltura.client.test.IngestConstants.INGEST_ACTION_INSERT;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getUsersListFromHouseHold;
import static com.kaltura.client.test.utils.OttUserUtils.getOttUserById;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    public static final boolean LOG_HEADERS = false;

    public static Client client;
    public static Configuration config;
    public static TestAPIOkRequestsExecutor executor = TestAPIOkRequestsExecutor.getExecutor();
    private static Response<LoginResponse> loginResponse;


    // shared common params
    public static int partnerId;
    public static String defaultUserPassword;

    // shared ks's
    private static String administratorKs, operatorKs, managerKs, anonymousKs;

    // shared ingest users data
    private static String ingestAssetUserUsername, ingestAssetUserPassword, ingestBusinessModuleUserUsername,
            ingestBusinessModuleUserPassword;

    // shared VOD
    private static MediaAsset mediaAsset;

    // TODO - dynamic
    private static String epgChannelName = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(0).getString("name");

    //Shared EPG program
    private static Asset epgProgram;

    // shared files
    private static MediaFile webMediaFile;
    private static MediaFile mobileMediaFile;

    // shared MPP
    private static Subscription fiveMinRenewableSubscription;

    // shared ingested PP
    private static PricePlan sharedCommonPricePlan;

    // shared ingested subscription
    private static Subscription sharedCommonSubscription;

    // shared ingested PPV
    private static Ppv sharedCommonPpv;

    // cycles map with values related view/full life cycles of price plans
    private static Map<Integer, String> cycles = new HashMap<>();

    {
        // TODO: complete other values
        cycles.put(1440, "1 Day");
    }


    /*================================================================================
    testing shared params list - used as a helper common params across tests

    int partnerId
    String defaultUserPassword

    String administratorKs
    String operatorKs
    String managerKs
    String anonymousKs

    MediaAsset mediaAsset

    MediaFile webMediaFile
    MediaFile mobileMediaFile

    Subscription fiveMinRenewableSubscription

    Household sharedHousehold
    HouseholdUser sharedMasterUser
    HouseholdUser sharedUser
    String sharedMasterUserKs
    String sharedUserKs
    ================================================================================*/


    @BeforeSuite
    public void base_test_before_suite() {
        // set configuration
        config = new Configuration();
        config.setEndpoint(getProperty(API_BASE_URL) + "/" + getProperty(API_VERSION));
        config.setAcceptGzipEncoding(false);

        // set client
        client = new Client(config);

        // set default awaitility timeout
        setDefaultTimeout(Long.parseLong(getProperty(DEFAULT_TIMEOUT_IN_SEC)), TimeUnit.SECONDS);

        // set shared common params
        partnerId = Integer.parseInt(getProperty(PARTNER_ID));
        defaultUserPassword = getProperty(DEFAULT_USER_PASSWORD);
    }

    /**
     * Regression requires existing of Price Plan with specific parameters.
     * Price should be 5 Euros
     * Discount percent should be equal 100%
     * <p>
     * In case item is not found in DB it will be ingested.
     * Can't work in case proper Discount and PriceCode aren't found in DB
     *
     * @return common shared Price Plan with mentioned parameters
     */
    public static PricePlan getSharedCommonPricePlan() {
        double defaultDiscountPrice = 0.0;
        double defaultDiscountPercentValue = 100.0;
        if (sharedCommonPricePlan == null) {
            sharedCommonPricePlan = IngestFixtureData.loadPricePlan(Double.valueOf(COMMON_PRICE_CODE_AMOUNT), IngestConstants.CURRENCY_EUR, defaultDiscountPrice, defaultDiscountPercentValue);
            if (sharedCommonPricePlan == null) {
                sharedCommonPricePlan = IngestUtils.ingestPP(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.of(cycles.get(CYCLE_1_DAY)), Optional.of(cycles.get(CYCLE_1_DAY)), Optional.of(0), Optional.of(COMMON_PRICE_CODE_AMOUNT),
                        Optional.of(IngestConstants.CURRENCY_EUR), Optional.of(IngestFixtureData.getDiscount(IngestConstants.CURRENCY_EUR, (int) defaultDiscountPercentValue)),
                        Optional.of(true), Optional.of(0));
            }
        }
        return sharedCommonPricePlan;
    }

    /**
     * Regression requires existing of MPP with specific parameters.
     * Price Plan should be as for method public static PricePlan getSharedCommonPricePlan()
     * <p>
     * MPP shouldn't be renewed and with discount (internal items) 100%
     *
     * @return MPP with mentioned parameters
     */
    public static Subscription getSharedCommonSubscription() {
        double defaultDiscountPercentValue = 100.0;
        if (sharedCommonSubscription == null) {
            sharedCommonSubscription = IngestFixtureData.loadSharedCommonSubscription(getSharedCommonPricePlan());
            if (sharedCommonSubscription == null) {
                sharedCommonSubscription = IngestUtils.ingestMPP(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of(IngestFixtureData.getDiscount(IngestConstants.CURRENCY_EUR, (int) defaultDiscountPercentValue)), Optional.empty(),
                        Optional.of(false), Optional.empty(), Optional.of(getSharedCommonPricePlan().getName()), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
            }
        }
        return sharedCommonSubscription;
    }

    /**
     * Regression requires existing of PPV with specific parameters.
     * Price Plan should be as for method public static PricePlan getSharedCommonPricePlan()
     *
     * PPV should be with discount (internal items) 50%
     *
     * @return PPV with mentioned parameters
     */
    public static Ppv getSharedCommonPpv(){
        double discountPercentValue = 50.0;
        if (sharedCommonPpv == null) {
            sharedCommonPpv = IngestFixtureData.loadSharedCommonPpv(getSharedCommonPricePlan());
            if (sharedCommonPpv == null) {
                sharedCommonPpv = IngestUtils.ingestPPV(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.empty(), Optional.of(IngestFixtureData.getDiscount(IngestConstants.CURRENCY_EUR, (int) discountPercentValue)),
                        Optional.empty(), Optional.empty(), Optional.of(getSharedCommonPricePlan().getName()),
                        Optional.of(false), Optional.of(false), Optional.empty(), Optional.empty(), Optional.empty());
            }
        }
        return sharedCommonPpv;
    }

    public static String getIngestBusinessModuleUserName() {
        if (ingestBusinessModuleUserUsername == null) {
            String userInfo = IngestFixtureData.getIngestItemUserData(partnerId);
            ingestBusinessModuleUserUsername = userInfo.split(":")[0];
            ingestBusinessModuleUserPassword = userInfo.split(":")[1];
        }
        return ingestBusinessModuleUserUsername;
    }

    public static String getIngestBusinessModuleUserPassword() {
        if (ingestBusinessModuleUserPassword == null) {
            String userInfo = IngestFixtureData.getIngestItemUserData(partnerId);
            ingestBusinessModuleUserUsername = userInfo.split(":")[0];
            ingestBusinessModuleUserPassword = userInfo.split(":")[1];
        }
        return ingestBusinessModuleUserPassword;
    }

    public static String getIngestAssetUserName() {
        if (ingestAssetUserUsername == null) {
            String userInfo = IngestFixtureData.getIngestItemUserData(partnerId + 1);
            ingestAssetUserUsername = userInfo.split(":")[0];
            ingestAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestAssetUserUsername;
    }

    public static String getIngestAssetUserPassword() {
        if (ingestAssetUserPassword == null) {
            String userInfo = IngestFixtureData.getIngestItemUserData(partnerId + 1);
            ingestAssetUserUsername = userInfo.split(":")[0];
            ingestAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestAssetUserPassword;
    }

    // getters for shared params
    public static String getAdministratorKs() {
        if (administratorKs == null) {
            String[] userInfo = DBUtils.getUserData("Administrator").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1],
                    null, null));
            administratorKs = loginResponse.results.getLoginSession().getKs();
        }
        return administratorKs;
    }

    public static String getOperatorKs() {
        if (operatorKs == null) {
            String[] userInfo = DBUtils.getUserData("Operator").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1],
                    null, null));
            operatorKs = loginResponse.results.getLoginSession().getKs();
        }
        return operatorKs;
    }

    public static String getManagerKs() {
        if (managerKs == null) {
            String[] userInfo = DBUtils.getUserData("Manager").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1],
                    null, null));
            managerKs = loginResponse.results.getLoginSession().getKs();
        }
        return managerKs;
    }

    public static String getAnonymousKs() {
        if (anonymousKs == null) {
            Response<LoginSession> loginSession = executor.executeSync(OttUserService.anonymousLogin(partnerId));
            anonymousKs = loginSession.results.getKs();
        }
        return anonymousKs;
    }

    public static MediaAsset getSharedMediaAsset() {
        if (mediaAsset == null) {
            mediaAsset = IngestUtils.ingestVOD(Optional.empty(), Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty());
        }
        return mediaAsset;
    }

    public static ProgramAsset getSharedEpgProgram() {
        if (epgProgram == null) {
            epgProgram = IngestUtils.ingestEPG(epgChannelName, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty()).get(0);
        }
        return (ProgramAsset) epgProgram;
    }


    public static MediaFile getSharedWebMediaFile() {
        if (webMediaFile == null) {
            if (getProperty(WEB_FILE_TYPE).equals(getSharedMediaAsset().getMediaFiles().get(0).getType())) {
                webMediaFile = mediaAsset.getMediaFiles().get(0);
            } else {
                webMediaFile = mediaAsset.getMediaFiles().get(1);
            }
        }
        return webMediaFile;
    }

    public static MediaFile getSharedMobileMediaFile() {
        if (mobileMediaFile == null) {
            if (getProperty(MOBILE_FILE_TYPE).equals(getSharedMediaAsset().getMediaFiles().get(0).getType())) {
                mobileMediaFile = mediaAsset.getMediaFiles().get(0);
            } else {
                mobileMediaFile = mediaAsset.getMediaFiles().get(1);
            }
        }
        return mobileMediaFile;
    }

    public static Subscription get5MinRenewableSubscription() {
        // TODO: add logic checking data from DB
        if (fiveMinRenewableSubscription == null) {
            PricePlan pricePlan = IngestUtils.ingestPP(Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.of(FIVE_MINUTES_PERIOD), Optional.of(FIVE_MINUTES_PERIOD), Optional.empty(),
                    Optional.of(getProperty(PRICE_CODE_AMOUNT)), Optional.of(IngestConstants.CURRENCY_EUR), Optional.of(""),
                    Optional.of(true), Optional.of(3));
            fiveMinRenewableSubscription = IngestUtils.ingestMPP(Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.of(true), Optional.empty(), Optional.of(pricePlan.getName()), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        return fiveMinRenewableSubscription;
    }

    // shared household
    public static class SharedHousehold {

        private static Household sharedHousehold;
        private static HouseholdUser sharedMasterUser, sharedUser;
        private static String sharedMasterUserKs, sharedUserKs;


        public static Household getSharedHousehold() {
            int numOfUsers = 2;
            int numOfDevices = 2;

            if (sharedHousehold == null) {
                sharedHousehold = createHousehold(numOfUsers, numOfDevices, true);
                List<HouseholdUser> sharedHouseholdUsers = getUsersListFromHouseHold(sharedHousehold);
                for (HouseholdUser user : sharedHouseholdUsers) {
                    if (user.getIsMaster() != null && user.getIsMaster()) {
                        sharedMasterUser = user;
                    }
                    if (user.getIsMaster() == null && user.getIsDefault() == null) {
                        sharedUser = user;
                    }
                }


                String sharedMasterUserName = getOttUserById(Integer.parseInt(sharedMasterUser.getUserId())).getUsername();
                loginResponse = executor.executeSync(login(partnerId, sharedMasterUserName, defaultUserPassword,null,null));
                sharedMasterUserKs = loginResponse.results.getLoginSession().getKs();

                String sharedUserName = getOttUserById(Integer.parseInt(sharedUser.getUserId())).getUsername();
                loginResponse = executor.executeSync(login(partnerId, sharedUserName, defaultUserPassword,null,null));

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
