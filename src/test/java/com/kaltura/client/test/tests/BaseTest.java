package com.kaltura.client.test.tests;

import com.google.common.base.Verify;
import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.Logger;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.services.ChannelService.AddChannelBuilder;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.services.SubscriptionService;
import com.kaltura.client.services.SubscriptionService.ListSubscriptionBuilder;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.test.utils.ingestUtils.*;
import com.kaltura.client.types.*;
import com.kaltura.client.types.Collection;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.OttUserUtils.getOttUserById;
import static com.kaltura.client.test.utils.SubscriptionUtils.getAssetsListBySubscription;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.FIVE_MINUTES_PERIOD;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.INGEST_ACTION_INSERT;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    public static final boolean LOG_HEADERS = false;

    public static Client client;
    public static Configuration config;
    public static TestAPIOkRequestsExecutor executor = TestAPIOkRequestsExecutor.getExecutor();
    private static Response<LoginResponse> loginResponse;


    /*================================================================================
    Shared Test Params - used as a helper common params across tests
    ================================================================================*/

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

    // shared collection
    private static Collection sharedCommonCollection;

    // shared ingested PPV
    private static Ppv sharedCommonPpv;

    // cycles map with values related view/full life cycles of price plans
    public static Map<Integer, String> cycles = new HashMap<>();

    {
        // TODO: complete other values
        cycles.put(1440, "1 Day");
    }

    /*================================================================================
    Shared Test Params - end
    ================================================================================*/


    @BeforeSuite
    public void baseTest_beforeSuite() {
        // set configuration
        config  = new Configuration();
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

    @BeforeMethod
    public void baseTest_beforeMethod(Method method) {
        Logger.getLogger(BaseTest.class).debug("Class: " + getClass().getSimpleName() + ", Test: " + method.getName());
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
            sharedCommonPricePlan = IngestFixtureData.loadPricePlan(Double.valueOf(COMMON_PRICE_CODE_AMOUNT), EUR.getValue(), defaultDiscountPrice, defaultDiscountPercentValue);
            if (sharedCommonPricePlan == null) {
                sharedCommonPricePlan = IngestPpUtils.ingestPP(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.of(cycles.get(CYCLE_1_DAY)), Optional.of(cycles.get(CYCLE_1_DAY)), Optional.of(0), Optional.of(COMMON_PRICE_CODE_AMOUNT),
                        Optional.of(EUR.getValue()), Optional.of(IngestFixtureData.getDiscount(EUR.getValue(), (int) defaultDiscountPercentValue)),
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
                sharedCommonSubscription = IngestMppUtils.ingestMPP(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of(IngestFixtureData.getDiscount(EUR.getValue(), (int) defaultDiscountPercentValue)), Optional.empty(),
                        Optional.of(false), Optional.empty(), Optional.of(getSharedCommonPricePlan().getName()), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
            }

            // it should have at least 1 VOD
            if (getAssetsListBySubscription(Integer.valueOf(sharedCommonSubscription.getId()), Optional.empty(), true) == null ||
                    getAssetsListBySubscription(Integer.valueOf(sharedCommonSubscription.getId()), Optional.empty(), true).size() == 0) {
                ingestVODIntoSubscription(sharedCommonSubscription);
            }
        }
        return sharedCommonSubscription;
    }

    /**
     * Regression requires existence of Collection with specific parameters.
     * Price should be as for method public static PricePlan getSharedCommonPricePlan()
     * Usage Module should be as for method public static PricePlan getSharedCommonPricePlan()
     * <p>
     * Collection should be with discount (internal items) 100%
     *
     * @return Collection with mentioned parameters
     */
    public static Collection getSharedCommonCollection() {
        if (sharedCommonCollection == null) {
            sharedCommonCollection = IngestFixtureData.loadSharedCommonCollection(getSharedCommonPricePlan());
            if (sharedCommonCollection == null) {
                Logger.getLogger(BaseTest.class).error("Collection with defined parameters should exist in DB!");
            }
        }
        return sharedCommonCollection;
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
                sharedCommonPpv = IngestPpvUtils.ingestPPV(Optional.of(INGEST_ACTION_INSERT), Optional.empty(), Optional.of(true),
                        Optional.empty(), Optional.of(IngestFixtureData.getDiscount(EUR.getValue(), (int) discountPercentValue)),
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
            mediaAsset = IngestVodUtils.ingestVOD (Optional.empty(), Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty());
        }
        return mediaAsset;
    }

    public static ProgramAsset getSharedEpgProgram() {
        if (epgProgram == null) {
            epgProgram = IngestEpgUtils.ingestEPG(epgChannelName).get(0);
        }
        return (ProgramAsset) epgProgram;
    }


    public static MediaFile getSharedWebMediaFile() {
        if (webMediaFile == null) {
            webMediaFile = getMediaFileByType(getSharedMediaAsset(), getProperty(WEB_FILE_TYPE));
        }
        return webMediaFile;
    }

    public static MediaFile getSharedMobileMediaFile() {
        if (mobileMediaFile == null) {
            mobileMediaFile = getMediaFileByType(getSharedMediaAsset(), getProperty(MOBILE_FILE_TYPE));
        }
        return mobileMediaFile;
    }

    public static MediaFile getMediaFileByType(MediaAsset asset, String type) {
        MediaFile result;
        if (type.equals(asset.getMediaFiles().get(0).getType())) {
            result = mediaAsset.getMediaFiles().get(0);
        } else {
            result = mediaAsset.getMediaFiles().get(1);
        }
        return result;
    }

    public static Subscription get5MinRenewableSubscription() {
        if (fiveMinRenewableSubscription == null) {
            fiveMinRenewableSubscription = IngestFixtureData.loadShared5MinutesRenewableSubscription();
            // it should have at least 1 VOD
            if (getAssetsListBySubscription(Integer.valueOf(fiveMinRenewableSubscription.getId()), Optional.empty(), true) == null ||
                    getAssetsListBySubscription(Integer.valueOf(fiveMinRenewableSubscription.getId()), Optional.empty(), true).size() == 0) {
                ingestVODIntoSubscription(fiveMinRenewableSubscription);
            }
            if (fiveMinRenewableSubscription == null) {
                PricePlan pricePlan = IngestPpUtils.ingestPP(Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.of(FIVE_MINUTES_PERIOD), Optional.of(FIVE_MINUTES_PERIOD), Optional.empty(),
                        Optional.of(getProperty(PRICE_CODE_AMOUNT)), Optional.of(EUR.getValue()), Optional.of(""),
                        Optional.of(true), Optional.of(3));

                // it should have at least 1 VOD
                Channel channel = loadDefaultChannel();
                channel.setFilterExpression("name='" + getSharedMediaAsset().getName() + "'");
                AddChannelBuilder addChannelBuilder = ChannelService.add(channel);
                Response<Channel> channelResponse = executor.executeSync(addChannelBuilder.setKs(getManagerKs()));
                if (channelResponse.results != null && channelResponse.results.getName() != null) {
                    fiveMinRenewableSubscription = IngestMppUtils.ingestMPP(Optional.empty(), Optional.empty(), Optional.empty(),
                            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                            Optional.of(true), Optional.empty(), Optional.of(pricePlan.getName()), Optional.empty(), Optional.empty(),
                            Optional.of(channelResponse.results.getName()), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
                }
            }
        }
        return fiveMinRenewableSubscription;
    }

    private static void ingestVODIntoSubscription(Subscription subscription) {
        // getting channel
        SubscriptionFilter filter = new SubscriptionFilter();
        filter.setSubscriptionIdIn(subscription.getId());
        ListSubscriptionBuilder listSubscriptionBuilder = SubscriptionService.list(filter);
        Response<ListResponse<Subscription>> listResponse = executor.executeSync(listSubscriptionBuilder.setKs(getOperatorKs()));
        Verify.verify(listResponse.results.getObjects().get(0).getChannels().size() > 0);
        int channelId = listResponse.results.getObjects().get(0).getChannels().get(0).getId().intValue();

        Channel channel = IngestFixtureData.getChannel(channelId);
        String[] parameters;
        String tag = null, name = null;
        if (null == channel.getFilterExpression()) {
            // automatic channel
            String automaticChannelExpression = IngestFixtureData.getAutomaticChannelExpression(channelId);
            parameters = automaticChannelExpression.split(":");
            Verify.verify(parameters.length == 2);
            tag = parameters[0];
        } else {
            // KSQL channel
            parameters = channel.getFilterExpression().split("=");
            Verify.verify(parameters.length == 2);
            if ("name".equals(parameters[0].toLowerCase())) {
                // ingest VOD with mentioned name
                name = parameters[0];
            } else {
                // ingest VOD with Tag/Meta (currently supports only tags!!!)
                tag = parameters[0];
            }
        }
        if (name != null) {
            // ingest VOD by name
            MediaAsset mediaAsset = IngestVodUtils.ingestVOD(Optional.empty(), Optional.empty(), true, Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty());
            IngestVodUtils.updateVODName(mediaAsset, name);
        }
        if (tag != null) {
            // ingest VOD by tag
            Map<String, List<String>> tags = new HashMap<>();
            List<String> values = new ArrayList<>();
            values.add(parameters[1].replaceAll("'", ""));
            tags.put(tag, values);
            IngestVodUtils.ingestVOD(Optional.empty(), Optional.empty(), true, Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(tags), Optional.empty(),
                    Optional.empty(), Optional.empty());
        }
    }

    private static Channel loadDefaultChannel() {
        Channel channel = new Channel();
        channel.setName(BaseUtils.getRandomValue("Channel_", 999999));
        channel.setDescription("Description of " + channel.getName());
        channel.setIsActive(true);
        channel.setAssetTypes(null);
        return channel;
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
                List<HouseholdUser> sharedHouseholdUsers = getUsersList(sharedHousehold);
                for (HouseholdUser user : sharedHouseholdUsers) {
                    if (user.getIsMaster() != null && user.getIsMaster()) {
                        sharedMasterUser = user;
                    }
                    if (user.getIsMaster() == null && user.getIsDefault() == null) {
                        sharedUser = user;
                    }
                }

                List<HouseholdDevice> sharedHouseholdDevices = getDevicesList(sharedHousehold);
                String sharedMasterUserName = getOttUserById(Integer.parseInt(sharedMasterUser.getUserId())).getUsername();
                loginResponse = executor.executeSync(login(partnerId, sharedMasterUserName, defaultUserPassword,null,sharedHouseholdDevices.get(0).getUdid()));
                sharedMasterUserKs = loginResponse.results.getLoginSession().getKs();

                String sharedUserName = getOttUserById(Integer.parseInt(sharedUser.getUserId())).getUsername();
                loginResponse = executor.executeSync(login(partnerId, sharedUserName, defaultUserPassword,null,sharedHouseholdDevices.get(1).getUdid()));

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
