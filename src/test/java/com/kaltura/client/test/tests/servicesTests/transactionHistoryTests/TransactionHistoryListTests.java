package com.kaltura.client.test.tests.servicesTests.transactionHistoryTests;

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
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionHistoryListTests extends BaseTest{

    private EntitlementFilter entitlementPpvFilter;
    private TransactionHistoryFilter transactionHistoryFilter;
    private Response<ListResponse<Entitlement>> listEntitlementServiceResponse;
    private Response<ListResponse<BillingTransaction>> listBillingTransactionResponse;
    private int numberOfDevicesInHousehold = 2;
    private int numberOfUsersInHousehold = 2;
    public static final String PPV_MODULE_ID_KEY = "ppvModuleId";
    public static final String PPV_PRICE_AMOUNT = "price";
    public static final String PPV_PRICE_CURRENCY = "currency";
    //Epoch for transactionHistory filter (86400 sec = 24 hours)
    public static final int yesterdayInEpoch =  (int)((System.currentTimeMillis()/1000)-86400);
    public static final int tomorrowInEpoch =  (int)((System.currentTimeMillis()/1000)+86400);


    @BeforeClass
    public void beforeClass(){
        entitlementPpvFilter = new EntitlementFilter();
        //Show entitlements per household
        entitlementPpvFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        //Don't show expired assets
        entitlementPpvFilter.setIsExpiredEqual(false);
        transactionHistoryFilter = new TransactionHistoryFilter();
        //Start date from yesterday
        transactionHistoryFilter.setStartDateGreaterThanOrEqual(yesterdayInEpoch);
        //End date before tomorrow
        transactionHistoryFilter.setEndDateLessThanOrEqual(tomorrowInEpoch);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("/transactionhistory/action/list - test ppv purchases is written correctly at transactionHistory")
    @Test
    public void purchase_ppv(){

        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold,true);
        //Login with master to first device
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());
        //Login with user to second device
        String userKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(1).getUdid());

        //All transactions per household
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for household with master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //No purchases were performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(0);

        int assetFileId = getSharedWebMediaFile().getId();
        //Purchase asset
        PurchaseUtils.purchasePpv(userKs, Optional.of(getSharedMediaAsset().getId().intValue()), Optional.of(assetFileId), null);
        //Get PPV details (ID, Price, Currency)
        String ppvModuleId = PurchaseUtils.purchasePpvDetailsMap.get(PPV_MODULE_ID_KEY);
        String pricePpv = PurchaseUtils.purchasePpvDetailsMap.get(PPV_PRICE_AMOUNT);
        String currencyPpv = PurchaseUtils.purchasePpvDetailsMap.get(PPV_PRICE_CURRENCY);

        //Show only PPV purchases
        entitlementPpvFilter.setProductTypeEqual(TransactionType.PPV);
        ListEntitlementBuilder listEntitlementBuilder = EntitlementService.list(entitlementPpvFilter).setKs(userKs);
        //entitlement/action/list
        listEntitlementServiceResponse = executor.executeSync(listEntitlementBuilder);
        //Conversion from Entitlement to PpvEntitlement object
        List<PpvEntitlement> ppvEntitlementList = new ArrayList<>();
        for(Entitlement entitlement:listEntitlementServiceResponse.results.getObjects()){
            if(entitlement.getClass() == PpvEntitlement.class) {
                ppvEntitlementList.add((PpvEntitlement) entitlement);
            }
        }
        //Verify that count of entitlements is 1
        assertThat(listEntitlementServiceResponse.results.getTotalCount()).isEqualTo(1);
        //Verify that PPV is correct
        assertThat(ppvEntitlementList.get(0).getProductId()).isEqualTo(ppvModuleId);
        //Verify that asset file is correct
        assertThat(ppvEntitlementList.get(0).getMediaFileId()).isEqualTo(assetFileId);

        //All transactions per user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);
        //Use non-master user ks
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs);
        //transactionHistory/action/list for non-master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());
        //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType()).isEqualTo(BillingItemsType.PPV);
        //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);
        //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);
        //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
        //Verify that asset was purchased
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction()).isEqualTo(BillingAction.PURCHASE);

        //All transactions per household
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        //Use non-master user ks
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs);
        //transactionHistory/action/list for non-master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());
        //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType()).isEqualTo(BillingItemsType.PPV);
        //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);
        //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);
        //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
        //Verify that asset was purchased
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction()).isEqualTo(BillingAction.PURCHASE);

        //All transactions per household
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        //Use master user ks
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);
        //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());
        //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType()).isEqualTo(BillingItemsType.PPV);
        //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);
        //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);
        //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType()).isEqualTo(BillingPriceType.FULLPERIOD);
        //Verify that asset was purchased
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction()).isEqualTo(BillingAction.PURCHASE);

        //All transactions per user
        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);
        //Use master user ks
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        //transactionHistory/action/list for master user
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);
        //No errors appeared at response
        assertThat(listBillingTransactionResponse.error).isNull();
        //No transactions were performed with master user
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(0);
    }
}
