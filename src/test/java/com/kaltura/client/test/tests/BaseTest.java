package com.kaltura.client.test.tests;

import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.Logger;
import com.kaltura.client.services.ChannelService.AddChannelBuilder;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.test.utils.PerformanceUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.*;
import com.kaltura.client.types.Collection;
import com.kaltura.client.utils.response.base.Response;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.*;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Verify.verify;
import static com.kaltura.client.services.ChannelService.add;
import static com.kaltura.client.services.ChannelService.get;
import static com.kaltura.client.services.OttUserService.anonymousLogin;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.services.SubscriptionService.list;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static com.kaltura.client.test.utils.BaseUtils.setTranslationToken;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.OttUserUtils.getOttUserById;
import static com.kaltura.client.test.utils.SubscriptionUtils.getAssetsListBySubscription;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.FIVE_MINUTES_PERIOD;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.EpgData;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.insertEpg;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.MppData;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.insertMpp;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpUtils.PpData;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpUtils.insertPp;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils.PpvData;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils.insertPpv;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static org.awaitility.Awaitility.setDefaultTimeout;

public class BaseTest {

    public static final boolean LOG_HEADERS = true;

    public static Client client;
    public static Configuration config;
    public static TestAPIOkRequestsExecutor executor = TestAPIOkRequestsExecutor.getExecutor();
    private static Response<LoginResponse> loginResponse;
    private static int numOfSuites = getNumOfSuites();

    /*================================================================================
    Shared Test Params - used as a helper common params across tests
    ================================================================================*/

    // shared common params
    public static int partnerId;
//    public static int opcPartnerId;
    public static boolean isOpcGroup;
    public static String defaultUserPassword;

    // shared ks's
    private static String administratorKs, operatorKs, managerKs, anonymousKs;

    // shared ingest users data
    private static String ingestAssetUserUsername, ingestAssetUserPassword, ingestBusinessModuleUserUsername,
            ingestBusinessModuleUserPassword, ingestVirtualAssetUserUsername, ingestVirtualAssetUserPassword;

    // shared VOD
    private static MediaAsset mediaAsset;

    // shared channel name
    private static String epgChannelName;

    // shared EPG program
    private static Asset epgProgram;

    // shared files
    private static MediaFile webMediaFile;
    private static MediaFile mobileMediaFile;

    // shared MPP
    private static Subscription fiveMinRenewableSubscription;

    // shared ingested PP
    private static PricePlan sharedCommonPricePlan;

    // shared discount module for shared PP
    private static DiscountModule sharedCommonDiscountModule;

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

    @Parameters({"accountType"})
    @BeforeSuite(alwaysRun = true)
    public void baseTest_beforeSuite(@org.testng.annotations.Optional("opc") String accountType, ITestContext testContext) {
        XmlSuite suite = testContext.getCurrentXmlTest().getSuite();
        Logger.getLogger(BaseTest.class).debug("Start suite >>> Suite: " + suite.getName());

        // set configuration
        config  = new Configuration();
        config.setEndpoint(getProperty(API_BASE_URL) + "/" + getProperty(API_VERSION));
        config.setAcceptGzipEncoding(false);
        config.setReadTimeout(Integer.valueOf(getProperty(DEFAULT_TIMEOUT_IN_SEC))*1000);

        // set client
        client = new Client(config);

        // set default awaitility timeout
        setDefaultTimeout(Long.parseLong(getProperty(DEFAULT_TIMEOUT_IN_SEC)), TimeUnit.SECONDS);

        // set shared common params
        if ("opc".equals(accountType)) {
            isOpcGroup = true;
            partnerId = Integer.parseInt(getProperty(OPC_PARTNER_ID));
        } else {
            isOpcGroup = false;
            partnerId = Integer.parseInt(getProperty(PARTNER_ID));
        }

        defaultUserPassword = getProperty(DEFAULT_USER_PASSWORD);

        // set performance report
        if ("true".equals(getProperty(WRITE_REGRESSION_LOGS)) && numOfSuites == getNumOfSuites()) {
            File logsDir = new File(getProperty(LOGS_DIR));
            if (Files.exists(logsDir.toPath())) {
                try {
                    FileUtils.cleanDirectory(new File(getProperty(LOGS_DIR)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // decrement numOfSuites in order to generate the performance report after all suites finish
        numOfSuites--;
    }

    @BeforeMethod
    public void baseTest_beforeMethod(Method method) {
        Logger.getLogger(BaseTest.class).debug("Start test >>> Class: " + getClass().getSimpleName() + ", Test: " + method.getName());
    }

    @AfterMethod
    public void baseTest_afterMethod(Method method) {
        Logger.getLogger(BaseTest.class).debug("End test >>> Class: " + getClass().getSimpleName() + ", Test: " + method.getName());
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
        if (sharedCommonPricePlan == null) {
            sharedCommonPricePlan = IngestFixtureData.loadPricePlan(Double.valueOf(COMMON_PRICE_CODE_AMOUNT), EUR.getValue(), getSharedCommonDiscount());
            if (sharedCommonPricePlan == null) {
                PpData ppData = new PpData()
                        .fullLifeCycle(cycles.get(CYCLE_1_DAY))
                        .viewLifeCycle(cycles.get(CYCLE_1_DAY))
                        .maxViews(0)
                        .price(COMMON_PRICE_CODE_AMOUNT)
                        .currency(EUR.getValue())
                        .discount(IngestFixtureData.getDiscountByPercentAndCurrency(EUR.getValue(), getSharedCommonDiscount().getPercent().intValue()))
                        .isRenewable(true)
                        .recurringPeriods(0);

                sharedCommonPricePlan = insertPp(ppData);
            }
        }
        return sharedCommonPricePlan;
    }

    /**
    * Regression requires existing of Price Plan with specific parameters.
     * One of them:
     * Discount percent should be equal 100%
     * Can't work in case proper Discount and PriceCode aren't found in DB
     *
     * @return common shared Discount Module with mentioned parameters
    */
    public static DiscountModule getSharedCommonDiscount() {
        double defaultDiscountPrice = 0.0;
        double defaultDiscountPercentValue = 100.0;
        if (sharedCommonDiscountModule == null) {
            sharedCommonDiscountModule = IngestFixtureData.loadDiscount(defaultDiscountPrice, defaultDiscountPercentValue);
        }
        return sharedCommonDiscountModule;
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
                MppData mppData = new MppData()
                        .pricePlanCode1(getSharedCommonPricePlan().getName())
                        .internalDiscount(IngestFixtureData.getDiscountByPercentAndCurrency(EUR.getValue(), (int) defaultDiscountPercentValue));
                sharedCommonSubscription = insertMpp(mppData);
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
                PpvData ppvData = new PpvData()
                        .discountCode(IngestFixtureData.getDiscountByPercentAndCurrency(EUR.getValue(), (int) discountPercentValue))
                        .usageModule(getSharedCommonPricePlan().getName());
                sharedCommonPpv = insertPpv(ppvData);
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
            String userInfo = isOpcGroup
                    ? IngestFixtureData.getIngestItemUserData(partnerId)
                    : IngestFixtureData.getIngestItemUserData(partnerId + 1);
            ingestAssetUserUsername = userInfo.split(":")[0];
            ingestAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestAssetUserUsername;
    }

    public static String getIngestAssetUserPassword() {
        if (ingestAssetUserPassword == null) {
            String userInfo = isOpcGroup
                    ? IngestFixtureData.getIngestItemUserData(partnerId)
                    : IngestFixtureData.getIngestItemUserData(partnerId + 1);
            ingestAssetUserUsername = userInfo.split(":")[0];
            ingestAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestAssetUserPassword;
    }

    public static String getIngestVirtualAssetUserName() {
        if (ingestVirtualAssetUserUsername == null) {
            String userInfo = isOpcGroup
                    ? IngestFixtureData.getIngestItemUserData(partnerId)
                    : IngestFixtureData.getIngestItemUserData(partnerId + 2);
            ingestVirtualAssetUserUsername = userInfo.split(":")[0];
            ingestVirtualAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestVirtualAssetUserUsername;
    }

    public static String getIngestVirualAssetUserPassword() {
        if (ingestVirtualAssetUserPassword == null) {
            String userInfo = isOpcGroup
                    ? IngestFixtureData.getIngestItemUserData(partnerId)
                    : IngestFixtureData.getIngestItemUserData(partnerId + 2);
            ingestVirtualAssetUserUsername = userInfo.split(":")[0];
            ingestVirtualAssetUserPassword = userInfo.split(":")[1];
        }
        return ingestVirtualAssetUserPassword;
    }

    // getters for shared params
    public static String getAdministratorKs() {
        if (administratorKs == null) {
            String[] userInfo = DBUtils.getUserData("Administrator").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1]));
            administratorKs = loginResponse.results.getLoginSession().getKs();
        }
        return administratorKs;
    }

    public static String getOperatorKs() {
        if (operatorKs == null) {
            String[] userInfo = DBUtils.getUserData("Operator").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1]));
            operatorKs = loginResponse.results.getLoginSession().getKs();
        }
        return operatorKs;
    }

    public static String getManagerKs() {
        if (managerKs == null) {
            String[] userInfo = DBUtils.getUserData("Manager").split(":");
            loginResponse = executor.executeSync(login(partnerId, userInfo[0], userInfo[1]));
            managerKs = loginResponse.results.getLoginSession().getKs();
        }
        return managerKs;
    }

    public static String getAnonymousKs() {
        if (anonymousKs == null) {
            Response<LoginSession> loginSession = executor.executeSync(anonymousLogin(partnerId));
            anonymousKs = loginSession.results.getKs();
        }
        return anonymousKs;
    }

    public static MediaAsset getSharedMediaAsset() {
        if (mediaAsset == null) {
            VodData vodData = new VodData();
            mediaAsset = insertVod(vodData, true);
        }
        return mediaAsset;
    }

    public static String getSharedEpgChannelName() {
        if (epgChannelName == null) {
            epgChannelName = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray().getJSONObject(0).getString("name");
        }
        return epgChannelName;
    }

    public static ProgramAsset getSharedEpgProgram() {
        if (epgProgram == null) {
            epgProgram = insertEpg(new EpgData(getSharedEpgChannelName())).get(0);
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

    public static MediaFile getMediaFileByType(MediaAsset asset, String fileType) {
        MediaFile mediaFile;
        if (fileType.equals(asset.getMediaFiles().get(0).getType())) {
            mediaFile = mediaAsset.getMediaFiles().get(0);
        } else {
            mediaFile = mediaAsset.getMediaFiles().get(1);
        }
        return mediaFile;
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
                PpData ppData = new PpData()
                        .fullLifeCycle(FIVE_MINUTES_PERIOD)
                        .viewLifeCycle(FIVE_MINUTES_PERIOD)
                        .price(PRICE_CODE_AMOUNT)
                        .currency(EUR.getValue())
                        .isRenewable(true)
                        .recurringPeriods(3);

                PricePlan pricePlan = insertPp(ppData);

                // it should have at least 1 VOD
                DynamicChannel channel = loadDefaultChannel();
                channel.setKSql("name='" + getSharedMediaAsset().getName() + "'");
                AddChannelBuilder addChannelBuilder = add(channel);
                Response<Channel> channelResponse = executor.executeSync(addChannelBuilder.setKs(getManagerKs()));
                if (channelResponse.results != null && channelResponse.results.getName() != null) {
                    MppData mppData = new MppData()
                            .pricePlanCode1(pricePlan.getName())
                            .channel2(channelResponse.results.getName());
                    fiveMinRenewableSubscription = insertMpp(mppData);
                }
            }
        }
        return fiveMinRenewableSubscription;
    }

    private static void ingestVODIntoSubscription(Subscription subscription) {
        // getting channel
        SubscriptionFilter filter = new SubscriptionFilter();
        filter.setSubscriptionIdIn(subscription.getId());
        Response<ListResponse<Subscription>> listResponse = executor.executeSync(list(filter)
                .setKs(getOperatorKs()));

        verify(listResponse.results.getObjects().get(0).getChannels().size() > 0);
        int channelId = listResponse.results.getObjects().get(0).getChannels().get(0).getId().intValue();

        // get channel
        DynamicChannel channel = (DynamicChannel) executor.executeSync(get(channelId)
                .setKs(getOperatorKs()))
                .results;

        String[] parameters;
        String tag = null;
        String name = null;

        if (channel.getKSql() == null || channel.getKSql().equals("")) {
            // automatic channel
            String automaticChannelExpression = IngestFixtureData.getAutomaticChannelExpression(channelId);
            parameters = automaticChannelExpression.split(":");
            verify(parameters.length == 2);
            tag = parameters[0];
        } else {
            // KSQL channel
            parameters = channel.getKSql().split("=");
            verify(parameters.length == 2);
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
            VodData vodData = new VodData();
            MediaAsset mediaAsset = insertVod(vodData, true);

            vodData.name(name);
            updateVod(mediaAsset.getName(), vodData);
        }
        if (tag != null) {
            // ingest VOD by tag
            Map<String, List<String>> tags = new HashMap<>();
            List<String> values = new ArrayList<>();
            values.add(parameters[1].replaceAll("'", ""));
            tags.put(tag, values);

            VodData vodData = new VodData().tags(tags);
            insertVod(vodData, true);
        }
    }

    private static DynamicChannel loadDefaultChannel() {
        DynamicChannel channel = new DynamicChannel();
        channel.setMultilingualName(setTranslationToken(getRandomValue("Channel_")));
        channel.setMultilingualDescription(setTranslationToken("Description of " + channel.getName()));
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

    @AfterSuite(alwaysRun = true)
    public void baseTest_afterSuite(ITestContext testContext) {
        XmlSuite suite = testContext.getCurrentXmlTest().getSuite();
        Logger.getLogger(BaseTest.class).debug("End suite >>> Suite: " + suite.getName());

        // generate performance report
        if ("true".equals(getProperty(WRITE_REGRESSION_LOGS)) && numOfSuites == 0) {
            PerformanceUtils.generatePerformanceReport();
        }

        // TODO: 8/14/2018 cleanup: delete generated shared resources and data!
    }

    private static int getNumOfSuites() {
        Document doc = getDocument("src/test/resources/testng.xml");
        NodeList suites = doc.getElementsByTagName("suite-file");
        return suites.getLength();
    }

}
