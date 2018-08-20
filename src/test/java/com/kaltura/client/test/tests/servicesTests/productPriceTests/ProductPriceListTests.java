package com.kaltura.client.test.tests.servicesTests.productPriceTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.*;
import com.kaltura.client.services.AssetService.ListAssetBuilder;
import com.kaltura.client.services.ChannelService.DeleteChannelBuilder;
import com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import com.kaltura.client.services.TransactionHistoryService.ListTransactionHistoryBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.services.ChannelService.add;
import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.enums.Currency.*;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPriceListTests extends BaseTest {

    private EntitlementFilter entitlementPpvsFilter;
    private EntitlementFilter entitlementSubsFilter;
    private TransactionHistoryFilter transactionHistoryFilter;
    private Household household;
    private String classMasterUserKs;
    private String classMasterUserId;
    private DynamicChannel sharedChannel;
    private Subscription subscriptionWithMultiCurrenciesAndDiscountPercentage;

    private Response<ListResponse<ProductPrice>> productPriceResponse;
    private Response<ListResponse<Entitlement>> entitlementResponse;
    private Response<ListResponse<BillingTransaction>> listBillingTransactionResponse;

    @BeforeClass
    public void beforeClass() {
        entitlementPpvsFilter = new EntitlementFilter();
        entitlementPpvsFilter.setOrderBy(EntitlementOrderBy.PURCHASE_DATE_ASC.getValue());
        entitlementPpvsFilter.setProductTypeEqual(TransactionType.PPV);
        entitlementPpvsFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        entitlementPpvsFilter.setIsExpiredEqual(false);

        entitlementSubsFilter = new EntitlementFilter();
        entitlementSubsFilter.setOrderBy(EntitlementOrderBy.PURCHASE_DATE_ASC.getValue());
        entitlementSubsFilter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        entitlementSubsFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        entitlementSubsFilter.setIsExpiredEqual(false);

        transactionHistoryFilter = new TransactionHistoryFilter();
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        transactionHistoryFilter.setStartDateGreaterThanOrEqual(0);

        int numberOfUsers = 2;
        int numberOfDevices = 1;
        household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        classMasterUserKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesList(household).get(0).getUdid());
        classMasterUserId = HouseholdUtils.getMasterUser(household).getUserId();

        sharedChannel = new DynamicChannel();
        sharedChannel.setMultilingualName(setTranslationToken(getRandomValue("Channel_")));
        sharedChannel.setMultilingualDescription(setTranslationToken("Description of " + sharedChannel.getName()));
        sharedChannel.setIsActive(true);
        sharedChannel.setAssetTypes(null);
        sharedChannel.setSystemName(sharedChannel.getMultilingualName().get(0).getValue() + "_" + getEpoch());

        MppData mppData = new MppData()
                .isRenewable(true)
                .pricePlanCode1(PRICE_PLAN_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS);
        subscriptionWithMultiCurrenciesAndDiscountPercentage = insertMpp(mppData);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription test by Operator without currency")
    @Test(enabled = false) // as used in feature tests
    public void listSubscriptionTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setSubscriptionIdIn(get5MinRenewableSubscription().getId());

        ListProductPriceBuilder productPriceList = ProductPriceService.list(filter);
        productPriceResponse = executor.executeSync(productPriceList.setKs(getOperatorKs()));
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(get5MinRenewableSubscription().getId().trim());
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isGreaterThan(0);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription test with currency by Operator")
    @Test()
    public void listSubscriptionWithCurrencyTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setSubscriptionIdIn(get5MinRenewableSubscription().getId());
        productPriceResponse = executor.executeSync(ProductPriceService.list(filter).setCurrency(EUR.getValue()).setKs(getOperatorKs()));
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(get5MinRenewableSubscription().getId().trim());
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isGreaterThan(0);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(EUR.getValue());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("productPrice/action/list - without required fields (subscriptionIdIn, collectionIdIn and fileIdIn are empty)")
    @Test()
    public void listWithoutRequiredFieldsTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        ListProductPriceBuilder productPriceList = ProductPriceService.list(filter);
        productPriceResponse = executor.executeSync(productPriceList.setKs(getOperatorKs()));

        int errorCode = 500056;
        assertThat(productPriceResponse.results).isNull();
        assertThat(productPriceResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(errorCode).getCode());
        assertThat(productPriceResponse.error.getMessage()).isEqualToIgnoringCase(
                "One of the arguments [KalturaProductPriceFilter.subscriptionIdIn, KalturaProductPriceFilter.fileIdIn, KalturaProductPriceFilter.collectionIdIn] must have a value");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("productPrice/action/list - ppv test")
    @Test
    public void ppvTest() {
        // TODO: after fix of BEO-4967 change HouseholdDevice.json to have only 1 enum value in objectType
        ListEntitlementBuilder entitlementListBeforePurchase = EntitlementService.list(entitlementPpvsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        int webMediaFileId = getSharedMediaAsset().getMediaFiles().get(0).getId();
        int mobileMediaFileId = getSharedMediaAsset().getMediaFiles().get(1).getId();
        ppFilter.setFileIdIn(String.valueOf(webMediaFileId));
        ppFilter.setIsLowest(false);
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(classMasterUserKs));
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(((PpvPrice)productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(webMediaFileId);

        PurchaseUtils.purchasePpv(classMasterUserKs, Optional.empty(), Optional.of(webMediaFileId), Optional.empty());

        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementPpvsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(webMediaFileId);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaId()).isEqualTo(Math.toIntExact(getSharedMediaAsset().getId()));
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate())
                .isGreaterThan(entitlementResponse.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod()).isIn(PaymentMethodType.OFFLINE, PaymentMethodType.UNKNOWN);

        ListProductPriceBuilder productPriceListAfterPurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListAfterPurchase.setKs(classMasterUserKs));
        // only 1 item mention in filter
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(webMediaFileId);

        ppFilter.setFileIdIn(String.valueOf(mobileMediaFileId));
        ListProductPriceBuilder productPriceListAfterPurchaseForAnotherFileFromTheSameMedia = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListAfterPurchaseForAnotherFileFromTheSameMedia.setKs(classMasterUserKs));
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(mobileMediaFileId);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - common test for PPV and subscription to check before purchase")
    @Test()
    public void productPriceSubscriptionAndPpvBeforePurchaseTest() {
        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setSubscriptionIdIn(get5MinRenewableSubscription().getId());
        filter.setFileIdIn(String.valueOf(getSharedWebMediaFile().getId()));
        filter.setIsLowest(false);
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(filter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase
                .setKs(OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()))));
        // should be 2 ss one item is subscription an another is media file
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(get5MinRenewableSubscription().getId().trim());
        assertThat(productPriceResponse.results.getObjects().get(1).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(1).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(1)).getFileId()).isEqualTo(getSharedWebMediaFile().getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("productPrice/action/list - subscription test")
    @Test
    public void subscriptionTest() {
        // TODO: 3/7/2018 add remarks when possible such as below - show to Shmulik / Michael and see if test is clear
        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        String classMasterUserKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesList(household).get(0).getUdid());

        // create mpp with supporting of 1 type only and having at least 1 media on its channel
        sharedChannel.setKSql("name='" + getSharedMediaAsset().getName() + "'");

        Response<Channel> channelResponse = executor.executeSync(add(sharedChannel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results).isNotNull();

        Channel channel = channelResponse.results;
        assertThat(channel.getName()).isNotNull();

        MppData mppData = new MppData()
                .isRenewable(true)
                .channel1(channel.getName())
                .fileType1(getProperty(WEB_FILE_TYPE));
        Subscription subscription = insertMpp(mppData);

        ListEntitlementBuilder entitlementListBeforePurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(subscription.getId());
        ppFilter.setIsLowest(false);
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(classMasterUserKs));
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(subscription.getId());

        ListProductPriceBuilder productPriceListBeforePurchase4Anonymous = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase4Anonymous.setKs(getAnonymousKs()));
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(subscription.getId());

        PurchaseUtils.purchaseSubscription(classMasterUserKs, Integer.valueOf(subscription.getId()), Optional.empty());

        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(subscription.getId());
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate()).isGreaterThan(
                entitlementResponse.results.getObjects().get(0).getCurrentDate());
        MatcherAssert.assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod(),
                Matchers.anyOf(Matchers.is(PaymentMethodType.OFFLINE), Matchers.is(PaymentMethodType.UNKNOWN)));

        // get data about assets inside subscription to get file
        BundleFilter bundleFilter = new BundleFilter();
        bundleFilter.setBundleTypeEqual(BundleType.SUBSCRIPTION);
        bundleFilter.setIdEqual(Integer.valueOf(subscription.getId()));
        ListAssetBuilder listAssetBuilder = AssetService.list(bundleFilter);
        Response<ListResponse<Asset>> listResponseAssets = executor.executeSync(listAssetBuilder.setKs(classMasterUserKs));
        assertThat(listResponseAssets.results.getTotalCount()).isGreaterThan(0);
        String sharedWebMediaFileId = String.valueOf(listResponseAssets.results.getObjects().get(0).getMediaFiles().get(0).getId()).trim();

        ppFilter.setFileIdIn(sharedWebMediaFileId);
        ListProductPriceBuilder productPriceListAfterPurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListAfterPurchase.setKs(classMasterUserKs));

        // as we have file and subscription in filter
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(1).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        // TODO: should we use ENUM containing subs of KalturaProductPrice class such as: KalturaCollectionPrice, KalturaPpvPrice, KalturaSubscriptionPrice???
        // that logic can't be checked by schema as schema can't check that exactly 1st item is Subscription and 2nd one is PPV
        assertThat(productPriceResponse.results.getObjects().get(0).getClass().getSimpleName()).isEqualToIgnoringCase("SubscriptionPrice");
        assertThat(productPriceResponse.results.getObjects().get(1).getClass().getSimpleName()).isEqualToIgnoringCase("PpvPrice");
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(1).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(subscription.getId());
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(1)).getFileId()).isEqualTo(Integer.valueOf(sharedWebMediaFileId));

        //delete HH for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        //delete subscription
        deleteMpp(subscription.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("/productprice/action/list - subscription - Web_HD_File_only")
    @Test()
    public void productPriceSubscriptionWebHDFileTypeOnlyTest() {
        // create mpp with supporting of 1 type only and having at least 1 media on its channel
        sharedChannel.setKSql("name='" + getSharedMediaAsset().getName() + "'");

        Response<Channel> channelResponse = executor.executeSync(add(sharedChannel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results).isNotNull();

        Channel channel = channelResponse.results;
        assertThat(channel.getName()).isNotNull();

        MppData mppData = new MppData()
                .isRenewable(true)
                .channel1(channel.getName())
                .fileType1(getProperty(WEB_FILE_TYPE));
        Subscription subscription = insertMpp(mppData);

        int webHDFileOnlySubId = Integer.valueOf(subscription.getId());

        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(String.valueOf(webHDFileOnlySubId));
        ppFilter.setFileIdIn(String.valueOf(getSharedWebMediaFile().getId()) + "," + String.valueOf(getSharedMobileMediaFile().getId()));
        ppFilter.setIsLowest(false);
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 3 as we have 2 files and 1 subscriptions
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(webHDFileOnlySubId));
        assertThat(productPriceResponse.results.getObjects().get(1).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(1).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(1)).getFileId()).isEqualTo(getSharedWebMediaFile().getId());
        assertThat(productPriceResponse.results.getObjects().get(2).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(2).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(2)).getFileId()).isEqualTo(getSharedMobileMediaFile().getId());

        PurchaseUtils.purchaseSubscription(masterKs, webHDFileOnlySubId, Optional.empty());

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 3 as we have 2 files and 1 subscriptions
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(webHDFileOnlySubId));
        assertThat(productPriceResponse.results.getObjects().get(1).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(1).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(1).getPrice().getAmount()).isEqualTo(0);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(1)).getFileId()).isEqualTo(getSharedWebMediaFile().getId());
        assertThat(productPriceResponse.results.getObjects().get(2).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(2).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(2).getPrice().getAmount()).isGreaterThan(0);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(2)).getFileId()).isEqualTo(getSharedMobileMediaFile().getId());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        // delete channel
        DeleteChannelBuilder deleteChannelBuilder = ChannelService.delete(Math.toIntExact(channel.getId()));
        executor.executeSync(deleteChannelBuilder.setKs(getManagerKs()));

        //delete subscription
        deleteMpp(subscription.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription - no specifed currency")
    @Test()
    public void productPriceSubscriptionNoSpecifiedCurrencyTest() {
        MppData mppData = new MppData()
                .isRenewable(true)
                .pricePlanCode1(PRICE_PLAN_WITH_MULTI_CURRENCIES);
        Subscription subscription = insertMpp(mppData);

        int subWithMultiCurrencyId = Integer.valueOf(subscription.getId());

        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(String.valueOf(subWithMultiCurrencyId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithMultiCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isGreaterThan(0);
        // as default group currency is EURO
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(EUR.getValue());

        PurchaseUtils.purchaseSubscription(masterKs, subWithMultiCurrencyId, Optional.empty());
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithMultiCurrencyId));
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(subWithMultiCurrencyId));

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithMultiCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        // as default group currency is EURO
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(EUR.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        //delete subscription
        deleteMpp(subscription.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription with discount (percentage) - specified currency")
    @Test()
    public void productPriceSubscriptionWithPercentageDiscountAndSpecifiedCurrencyTest() {
        // TODO: should we save it in Properties?
        double subPriceAfterDiscount = 7.5; // as price 15 and discount is 50%
        int subWithDiscountAndCurrencyId = Integer.valueOf(subscriptionWithMultiCurrenciesAndDiscountPercentage.getId());

        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(String.valueOf(subWithDiscountAndCurrencyId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter).setCurrency(USD.getValue());
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(USD.getValue());

        PurchaseUtils.purchaseSubscription(masterKs, subWithDiscountAndCurrencyId, Optional.of(USD.getValue()));
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(USD.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription with discount (fixed amount) - specified currency - not in locale")
    @Test()
    public void productPriceSubscriptionWithFixedDiscountAndSpecifiedCurrencyNotInLocaleTest() {
        // TODO: should we save it in Properties?
        double subPriceAfterDiscount = 4; // as price 5 and discount is 1

        MppData mppData = new MppData()
                .isRenewable(true)
                .pricePlanCode1(PRICE_PLAN_WITH_MULTI_CURRENCIES_AND_DISCOUNT_FIXED);
        Subscription subscription = insertMpp(mppData);

        int subWithDiscountAndCurrencyId = Integer.valueOf(subscription.getId());

        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(String.valueOf(subWithDiscountAndCurrencyId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter).setCurrency(CLP.getValue());
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(CLP.getValue());

        PurchaseUtils.purchaseSubscription(masterKs, subWithDiscountAndCurrencyId, Optional.of(CLP.getValue()));
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(CLP.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        //delete subscription
        deleteMpp(subscription.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - subscription with discount (percentage) - no specified currency")
    @Test()
    public void productPriceSubscriptionWithPercentageDiscountAndNoSpecifiedCurrencyTest() {
        // TODO: should we save it in Properties?
        double subPriceAfterDiscount = 9.6; // as price 12 in default locale and discount is 20% in default locale
        int subWithDiscountAndCurrencyId = Integer.valueOf(subscriptionWithMultiCurrenciesAndDiscountPercentage.getId());

        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setSubscriptionIdIn(String.valueOf(subWithDiscountAndCurrencyId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(EUR.getValue());

        PurchaseUtils.purchaseSubscription(masterKs, subWithDiscountAndCurrencyId, Optional.empty());
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementSubsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(subPriceAfterDiscount);

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 subscription
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.SUBSCRIPTION);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductId()).isEqualTo(String.valueOf(subWithDiscountAndCurrencyId));
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(EUR.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - PPV - with discount (percentage) - specified currency")
    @Test()
    public void productPricePpvWithPercentageDiscountAndSpecifiedCurrencyTest() {
        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        // TODO: should we save it in Properties?
        double ppvPriceAfterDiscount = 33.3; // as price 37 ILS and discount is 10%

        VodData vodData = new VodData()
                .ppvWebName(PPV_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS)
                .ppvMobileName(PPV_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS);
        MediaAsset mediaAsset = insertVod(vodData, true);

        int mediaFileId = mediaAsset.getMediaFiles().get(0).getId();
        int assetWithMultiCurrencyId = Math.toIntExact(mediaAsset.getId());

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setFileIdIn(String.valueOf(mediaFileId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs).setCurrency(ILS.getValue()));
        // should be 1 file
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(ppvPriceAfterDiscount);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(ILS.getValue());

        PurchaseUtils.purchasePpv(masterKs, Optional.of(assetWithMultiCurrencyId), Optional.of(mediaFileId), Optional.of(ILS.getValue()));
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementPpvsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaId()).isEqualTo(assetWithMultiCurrencyId);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(mediaFileId);
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(assetWithMultiCurrencyId));
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(ppvPriceAfterDiscount);

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 ppv
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(((PpvPrice)productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(mediaFileId);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(ILS.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        // delete media
        deleteVod(mediaAsset.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("productPrice/action/list - PPV - with discount (fixed amount) - specified currency")
    @Test()
    public void productPricePpvWithFixedDiscountAndSpecifiedCurrencyTest() {
        int numberOfUsers = 1;
        int numberOfDevices = 1;
        Household household = HouseholdUtils.createHousehold(numberOfUsers, numberOfDevices, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        String masterKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));

        VodData vodData = new VodData()
                .ppvWebName(PPV_WITH_MULTI_CURRENCIES_AND_FIXED_DISCOUNT)
                .ppvMobileName(PPV_WITH_MULTI_CURRENCIES_AND_FIXED_DISCOUNT);
        MediaAsset mediaAsset = insertVod(vodData, true);

        int mediaFileId = mediaAsset.getMediaFiles().get(0).getId();
        int assetWithMultiCurrencyId = Math.toIntExact(mediaAsset.getId());
        double ppvPriceAfterDiscount = 2; // as price 5 ILS and discount is 3

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setFileIdIn(String.valueOf(mediaFileId));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs).setCurrency(ILS.getValue()));
        // should be 1 file
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(ppvPriceAfterDiscount);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(ILS.getValue());

        PurchaseUtils.purchasePpv(masterKs, Optional.of(assetWithMultiCurrencyId), Optional.of(mediaFileId), Optional.of(ILS.getValue()));
        // to check purchase
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(entitlementPpvsFilter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaId()).isEqualTo(assetWithMultiCurrencyId);
        assertThat(((PpvEntitlement) entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(mediaFileId);
        // to check purchase
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder.setKs(masterKs));
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(String.valueOf(assetWithMultiCurrencyId));
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(ppvPriceAfterDiscount);

        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(masterKs));
        // should be 1 ppv
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(((PpvPrice)productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(mediaFileId);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency()).isEqualTo(ILS.getValue());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        // delete media
        deleteVod(mediaAsset.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("/productPrice/action/list - with passed PPV")
    @Test()
    public void productPriceWithPassedPpvTest() {
        String ppvWithExpiredDate = ";;01/01/2017 00:00:00";
        String ppvMobileModule = getSharedCommonPpv().getName() + ppvWithExpiredDate + PPV_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS + ";;";

        VodData vodData = new VodData()
                .ppvMobileName(ppvMobileModule);
        MediaAsset mediaAssetWith2Ppv1Expired = insertVod(vodData, true);

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        ppFilter.setFileIdIn(String.valueOf(mediaAssetWith2Ppv1Expired.getMediaFiles().get(1).getId()));
        ListProductPriceBuilder productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(classMasterUserKs));
        // should be 1 file with non passed PPV price only
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isGreaterThan(0);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(
                mediaAssetWith2Ppv1Expired.getMediaFiles().get(1).getId());

        ppvMobileModule = getSharedCommonPpv().getName() + ppvWithExpiredDate;
        VodData vodData1 = new VodData()
                .ppvMobileName(ppvMobileModule);
        mediaAssetWith2Ppv1Expired = insertVod(vodData1, true);

        ppFilter = new ProductPriceFilter();
        ppFilter.setFileIdIn(String.valueOf(mediaAssetWith2Ppv1Expired.getMediaFiles().get(1).getId()));
        productPriceListBeforePurchase = ProductPriceService.list(ppFilter);
        productPriceResponse = executor.executeSync(productPriceListBeforePurchase.setKs(classMasterUserKs));
        // should be 1 file and free
        assertThat(productPriceResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FREE);
        assertThat(productPriceResponse.results.getObjects().get(0).getProductType()).isEqualTo(TransactionType.PPV);
        assertThat(productPriceResponse.results.getObjects().get(0).getPrice().getAmount()).isEqualTo(0);
        assertThat(((PpvPrice) productPriceResponse.results.getObjects().get(0)).getFileId()).isEqualTo(
                mediaAssetWith2Ppv1Expired.getMediaFiles().get(1).getId());

        // delete media
        deleteVod(mediaAssetWith2Ppv1Expired.getName());
    }

    @AfterClass
    public void afterClass() {
        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));

        // delete subscription
        deleteMpp(subscriptionWithMultiCurrenciesAndDiscountPercentage.getName());
    }
}