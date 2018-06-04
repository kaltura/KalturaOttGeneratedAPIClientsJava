package com.kaltura.client.test.tests.servicesTests.transactionHistoryTests;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.*;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import com.kaltura.client.services.TransactionHistoryService;
import com.kaltura.client.services.TransactionHistoryService.ListTransactionHistoryBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionHistoryListTests extends BaseTest{

    private ListTransactionHistoryBuilder listTransactionHistoryBuilder;
    private EntitlementFilter entitlementFilter;
    private TransactionHistoryFilter transactionHistoryFilter;
    private Response<ListResponse<Entitlement>> listEntitlementServiceResponsePpv;
    private Response<ListResponse<Entitlement>> listEntitlementServiceResponseSubscription;
    private Response<ListResponse<BillingTransaction>> listBillingTransactionResponse;
    private Response<Transaction> transactionResponseSubscription;
    private Response<Transaction> transactionResponseCollection;
    private Response<Transaction> transactionResponsePpv;
    private int numberOfDevicesInHousehold = 2;
    private int numberOfUsersInHousehold = 2;
    private String masterUserKs;
    private String userKs;
    private String methodName;
    public static final String PPV_MODULE_ID_KEY = "ppvModuleId";
    public static final String PRICE_AMOUNT = "price";
    public static final String PRICE_CURRENCY = "currency";
    //Epoch for transactionHistory filter (86400 sec = 24 hours)
    public static final int yesterdayInEpoch =  (int)((System.currentTimeMillis()/1000)-86400);
    public static final int tomorrowInEpoch =  (int)((System.currentTimeMillis()/1000)+86400);


    @BeforeClass
    public void transactionHistortTestSetup(){
        entitlementFilter = new EntitlementFilter();
        //Show entitlements per household
        entitlementFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        //Don't show expired assets
        entitlementFilter.setIsExpiredEqual(false);
        transactionHistoryFilter = new TransactionHistoryFilter();
        //Start date from yesterday
        transactionHistoryFilter.setStartDateGreaterThanOrEqual(yesterdayInEpoch);
        //End date before tomorrow
        transactionHistoryFilter.setEndDateLessThanOrEqual(tomorrowInEpoch);

        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold,true);
        //Login with master to first device
        masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());
        //Login with user to second device
        userKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(1).getUdid());

        //All transactions per household - empty
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for household with master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //No purchases were performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(0);

        //Purchase asset with non-master user
        transactionResponsePpv = PurchaseUtils.purchasePpv(userKs, Optional.of(getSharedMediaAsset().getId().intValue()), Optional.of(getSharedWebMediaFile().getId()), null);
        //Purchase subscription with non-master user
        transactionResponseSubscription = PurchaseUtils.purchaseSubscription(userKs, Integer.parseInt(getSharedCommonSubscription().getId()), Optional.empty());
        //Purchase collection with master user
        transactionResponseCollection = PurchaseUtils.purchaseCollection(masterUserKs, Integer.parseInt(getSharedCommonCollection().getId()));

        //Show PPV entitlements
        entitlementFilter.setProductTypeEqual(TransactionType.PPV);
        ListEntitlementBuilder listEntitlementBuilder = EntitlementService.list(entitlementFilter).setKs(userKs);
        //entitlement/action/list
        listEntitlementServiceResponsePpv = executor.executeSync(listEntitlementBuilder);
        //Verify response is correct
        assertThat(listEntitlementServiceResponsePpv.error).isNull();
        assertThat(listEntitlementServiceResponsePpv.results.getObjects().get(0).getClass()).isEqualTo(PpvEntitlement.class);
        assertThat(listEntitlementServiceResponsePpv.results.getTotalCount()).isEqualTo(1);
        //Conversion from Entitlement to PpvEntitlement object
        List<PpvEntitlement> ppvEntitlementList = new ArrayList<>();
        for(Entitlement entitlement:listEntitlementServiceResponsePpv.results.getObjects()){
            if(entitlement.getClass() == PpvEntitlement.class) {
                ppvEntitlementList.add((PpvEntitlement) entitlement);
            }
        }
        //TODO: change ppvModuleId to getSharedWebMediaFile().getPpvModules().getObjects().get(0).getValue() - right now it is null
        String ppvModuleId = PurchaseUtils.purchasePpvDetailsMap.get(PPV_MODULE_ID_KEY);
        //Verify that PPV is correct
        assertThat(ppvEntitlementList.get(0).getProductId()).isEqualTo(ppvModuleId);
        //Verify that asset file is correct
        assertThat(ppvEntitlementList.get(0).getMediaFileId()).isEqualTo(getSharedWebMediaFile().getId());

        //Show subscription entitlements
        entitlementFilter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        listEntitlementBuilder = EntitlementService.list(entitlementFilter).setKs(userKs);
        //entitlement/action/list
        listEntitlementServiceResponseSubscription = executor.executeSync(listEntitlementBuilder);
        //Verify that subscription is correct
        assertThat(listEntitlementServiceResponseSubscription.results.getTotalCount()).isEqualTo(1);
        assertThat(listEntitlementServiceResponseSubscription.results.getObjects().get(0).getProductId()).isEqualTo(getSharedCommonSubscription().getId());
        assertThat(listEntitlementServiceResponseSubscription.results.getObjects().get(0).getClass()).isEqualTo(SubscriptionEntitlement.class);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "/transactionHistory/action/list - test purchases per household are written correctly at transactionHistory for non-master user")
    public void testTransactionHistoryPerHouseholdWithUserKs() {
        //All transactions per non-master user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs);
        //transactionHistory/action/list for non-master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that two transactions were performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(listBillingTransactionResponse.results.getObjects().size()).isEqualTo(3);
        List<BillingTransaction> billingTransactionList = listBillingTransactionResponse.results.getObjects();
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        assertMethod(methodName, billingTransactionList);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "/transactionHistory/action/list - test purchases per user are written correctly at transactionHistory for non-master user")
    public void testTransactionHistoryPerUserWithUserKs() {
        //All transactions per non-master user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs);
        //transactionHistory/action/list for non-master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that two transactions were performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(2);
        assertThat(listBillingTransactionResponse.results.getObjects().size()).isEqualTo(2);
        List<BillingTransaction> billingTransactionList = listBillingTransactionResponse.results.getObjects();
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        assertMethod(methodName, billingTransactionList);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "/transactionHistory/action/list - test purchases per household are written correctly at transactionHistory for master user")
    public void testTransactionHistoryPerHouseholdWithMasterKs() {
        //All transactions per master user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that two transactions were performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(3);
        assertThat(listBillingTransactionResponse.results.getObjects().size()).isEqualTo(3);
        List<BillingTransaction> billingTransactionList = listBillingTransactionResponse.results.getObjects();
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        assertMethod(methodName, billingTransactionList);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "/transactionHistory/action/list - test no purchases per user were written at transactionHistory for master user")
    public void testTransactionHistoryPerUserWithMasterKs() {
        //All transactions per master user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(listBillingTransactionResponse.results.getObjects().size()).isEqualTo(1);
        List<BillingTransaction> billingTransactionList = listBillingTransactionResponse.results.getObjects();
        methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        assertMethod(methodName, billingTransactionList);
    }

    private void assertMethod(String methodName, List<BillingTransaction> billingTransactionList) {
        Logger.getLogger(TransactionHistoryListTests.class).debug(methodName);
        String price, currency;
        for(int i = 0; i < billingTransactionList.size(); i++) {
            assertThat(listBillingTransactionResponse.results.getObjects().get(i).getClass()).isEqualTo(BillingTransaction.class);
            switch (billingTransactionList.get(i).getItemType()){
                case SUBSCRIPTION:
                    BillingTransaction billingTransactionSubscription = billingTransactionList.get(i);
                    //Get subscription details (Price, Currency)
                    price = PurchaseUtils.purchaseSubscriptionDetailsMap.get(PRICE_AMOUNT);
                    currency = PurchaseUtils.purchaseSubscriptionDetailsMap.get(PRICE_CURRENCY);
                    assertThat(billingTransactionSubscription.getRecieptCode()).isEqualTo(transactionResponseSubscription.results.getId());
                    assertThat(billingTransactionSubscription.getPurchasedItemName().length()).isGreaterThan(1);
                    assertThat(billingTransactionSubscription.getPurchasedItemCode()).isEqualTo(getSharedCommonSubscription().getId().toString());
                    assertThat(billingTransactionSubscription.getItemType()).isEqualTo(BillingItemsType.SUBSCRIPTION);
                    assertThat(billingTransactionSubscription.getBillingAction()).isEqualTo(BillingAction.PURCHASE);
                    assertThat(billingTransactionSubscription.getPrice().getAmount().toString()).isEqualTo(price);
                    assertThat(billingTransactionSubscription.getPrice().getCurrency().toString()).isEqualTo(currency);
                    assertThat(billingTransactionSubscription.getIsRecurring()).isEqualTo(false);
                    assertThat(billingTransactionSubscription.getBillingProviderRef().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionSubscription.getPurchaseId().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionSubscription.getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
                    assertThat(billingTransactionSubscription.getStartDate()).isEqualTo(billingTransactionSubscription.getActionDate());
                    assertThat(billingTransactionSubscription.getStartDate()).isEqualTo(transactionResponseSubscription.results.getCreatedAt().longValue());
                    break;

                case PPV:
                    BillingTransaction billingTransactionPpv = billingTransactionList.get(i);
                    //Get PPV details (Price, Currency)
                    price = PurchaseUtils.purchasePpvDetailsMap.get(PRICE_AMOUNT);
                    currency = PurchaseUtils.purchasePpvDetailsMap.get(PRICE_CURRENCY);
                    assertThat(billingTransactionPpv.getRecieptCode()).isEqualTo(transactionResponsePpv.results.getId());
                    assertThat(billingTransactionPpv.getPurchasedItemName().length()).isGreaterThan(1);
                    assertThat(billingTransactionPpv.getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());
                    assertThat(billingTransactionPpv.getItemType()).isEqualTo(BillingItemsType.PPV);
                    assertThat(billingTransactionPpv.getBillingAction()).isEqualTo(BillingAction.PURCHASE);
                    assertThat(billingTransactionPpv.getPrice().getAmount().toString()).isEqualTo(price);
                    assertThat(billingTransactionPpv.getPrice().getCurrency().toString()).isEqualTo(currency);
                    assertThat(billingTransactionPpv.getIsRecurring()).isEqualTo(false);
                    assertThat(billingTransactionPpv.getBillingProviderRef().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionPpv.getPurchaseId().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionPpv.getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
                    assertThat(billingTransactionPpv.getStartDate()).isEqualTo(billingTransactionPpv.getActionDate());
                    assertThat(billingTransactionPpv.getStartDate()).isEqualTo(transactionResponsePpv.results.getCreatedAt().longValue());
                    break;

                case COLLECTION:
                    BillingTransaction billingTransactionCollection = billingTransactionList.get(i);
                    //Get collection details (Price, Currency)
                    price = PurchaseUtils.purchaseCollectionDetailsMap.get(PRICE_AMOUNT);
                    currency = PurchaseUtils.purchaseCollectionDetailsMap.get(PRICE_CURRENCY);
                    assertThat(billingTransactionCollection.getRecieptCode()).isEqualTo(transactionResponseCollection.results.getId());
                    assertThat(billingTransactionCollection.getPurchasedItemName().length()).isGreaterThan(1);
                    assertThat(billingTransactionCollection.getPurchasedItemCode()).isEqualTo(getSharedCommonCollection().getId().toString());
                    assertThat(billingTransactionCollection.getItemType()).isEqualTo(BillingItemsType.COLLECTION);
                    assertThat(billingTransactionCollection.getBillingAction()).isEqualTo(BillingAction.PURCHASE);
                    assertThat(billingTransactionCollection.getPrice().getAmount().toString()).isEqualTo(price);
                    assertThat(billingTransactionCollection.getPrice().getCurrency().toString()).isEqualTo(currency);
                    assertThat(billingTransactionCollection.getIsRecurring()).isEqualTo(false);
                    assertThat(billingTransactionCollection.getBillingProviderRef().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionCollection.getPurchaseId().toString().length()).isGreaterThan(1);
                    assertThat(billingTransactionCollection.getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
                    assertThat(billingTransactionCollection.getActionDate()).isEqualTo(transactionResponseCollection.results.getCreatedAt().longValue());
                    break;

                default:
                    Logger.getLogger(TransactionHistoryListTests.class).error("No valid item type found!");
                    break;
            }
        }
    }
}
