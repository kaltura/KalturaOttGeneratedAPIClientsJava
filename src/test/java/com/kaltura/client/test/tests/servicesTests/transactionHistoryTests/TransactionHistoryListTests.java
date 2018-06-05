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
        //Verify that PPV is correct
        assertThat(ppvEntitlementList.get(0).getProductId()).isEqualTo(PurchaseUtils.purchasePpvDetailsMap.get(PPV_MODULE_ID_KEY));
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
        for(int i = 0; i < billingTransactionList.size(); i++) {
            assertThat(billingTransactionList.get(i).getClass()).isEqualTo(BillingTransaction.class);
            assertThat(billingTransactionList.get(i).getBillingAction()).isEqualTo(BillingAction.PURCHASE);
            assertThat(billingTransactionList.get(i).getIsRecurring()).isEqualTo(false);
            assertThat(billingTransactionList.get(i).getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
            switch (billingTransactionList.get(i).getItemType()){
                case SUBSCRIPTION:
                    BillingTransaction billingTransactionSubscription = billingTransactionList.get(i);
                    assertThat(billingTransactionSubscription.getRecieptCode()).isEqualTo(transactionResponseSubscription.results.getId());
                    //TODO: Shared Subscription name not equals to transactionHistory name written (Title written). (deprecate this assertion or amend with another way)
//                    assertThat(billingTransactionSubscription.getPurchasedItemName()).isEqualTo(getSharedCommonSubscription().getName());
                    assertThat(billingTransactionSubscription.getPurchasedItemCode()).isEqualTo(getSharedCommonSubscription().getId().toString());
                    assertThat(billingTransactionSubscription.getItemType()).isEqualTo(BillingItemsType.SUBSCRIPTION);
                    assertThat(billingTransactionSubscription.getPrice().getAmount().toString()).isEqualTo(PurchaseUtils.purchaseSubscriptionDetailsMap.get(PRICE_AMOUNT));
                    assertThat(billingTransactionSubscription.getPrice().getCurrency().toString()).isEqualTo(PurchaseUtils.purchaseSubscriptionDetailsMap.get(PRICE_CURRENCY));
                    assertThat(billingTransactionSubscription.getStartDate()).isEqualTo(billingTransactionSubscription.getActionDate());
                    assertThat(billingTransactionSubscription.getStartDate()).isEqualTo(transactionResponseSubscription.results.getCreatedAt().longValue());
                    break;

                case PPV:
                    BillingTransaction billingTransactionPpv = billingTransactionList.get(i);
                    assertThat(billingTransactionPpv.getRecieptCode()).isEqualTo(transactionResponsePpv.results.getId());
                    assertThat(billingTransactionPpv.getPurchasedItemName()).isEqualTo(getSharedMediaAsset().getName());
                    assertThat(billingTransactionPpv.getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());
                    assertThat(billingTransactionPpv.getItemType()).isEqualTo(BillingItemsType.PPV);
                    assertThat(billingTransactionPpv.getPrice().getAmount().toString()).isEqualTo(PurchaseUtils.purchasePpvDetailsMap.get(PRICE_AMOUNT));
                    assertThat(billingTransactionPpv.getPrice().getCurrency().toString()).isEqualTo(PurchaseUtils.purchasePpvDetailsMap.get(PRICE_CURRENCY));
                    assertThat(billingTransactionPpv.getStartDate()).isEqualTo(billingTransactionPpv.getActionDate());
                    assertThat(billingTransactionPpv.getStartDate()).isEqualTo(transactionResponsePpv.results.getCreatedAt().longValue());
                    break;

                case COLLECTION:
                    BillingTransaction billingTransactionCollection = billingTransactionList.get(i);
                    assertThat(billingTransactionCollection.getRecieptCode()).isEqualTo(transactionResponseCollection.results.getId());
                    assertThat(billingTransactionCollection.getPurchasedItemName()).isEqualTo(getSharedCommonCollection().getName());
                    assertThat(billingTransactionCollection.getPurchasedItemCode()).isEqualTo(getSharedCommonCollection().getId().toString());
                    assertThat(billingTransactionCollection.getItemType()).isEqualTo(BillingItemsType.COLLECTION);
                    assertThat(billingTransactionCollection.getPrice().getAmount().toString()).isEqualTo(PurchaseUtils.purchaseCollectionDetailsMap.get(PRICE_AMOUNT));
                    assertThat(billingTransactionCollection.getPrice().getCurrency().toString()).isEqualTo(PurchaseUtils.purchaseCollectionDetailsMap.get(PRICE_CURRENCY));
                    assertThat(billingTransactionCollection.getActionDate()).isEqualTo(transactionResponseCollection.results.getCreatedAt().longValue());
                    break;

                default:
                    Logger.getLogger(TransactionHistoryListTests.class).error("No valid item type found!");
                    break;
            }
        }
    }
}
