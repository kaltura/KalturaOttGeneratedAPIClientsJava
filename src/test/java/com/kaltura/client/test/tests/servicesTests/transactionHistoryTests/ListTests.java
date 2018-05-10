package com.kaltura.client.test.tests.servicesTests.transactionHistoryTests;

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.TransactionType;
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

public class ListTests extends BaseTest{

    private EntitlementFilter entitlementPpvFilter;
    private TransactionHistoryFilter transactionHistoryFilter;
    private Response<ListResponse<Entitlement>> listEntitlementServiceResponse;
    private Response<ListResponse<BillingTransaction>> listBillingTransactionResponse;
    private int numberOfDevicesInHousehold = 2;
    private int numberOfUsersInHousehold = 2;

    @BeforeClass
    public void beforeClass(){
        entitlementPpvFilter = new EntitlementFilter();
        entitlementPpvFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);  //Show entitlements per household
        entitlementPpvFilter.setIsExpiredEqual(false);  //Don't show expired assets
        transactionHistoryFilter = new TransactionHistoryFilter();
        transactionHistoryFilter.setStartDateGreaterThanOrEqual((int)((System.currentTimeMillis()/1000)-86400));    //Start date from yesterday (86400 sec = 24 hours)
        transactionHistoryFilter.setEndDateLessThanOrEqual((int)((System.currentTimeMillis()/1000)+86400)); //End date before tomorrow (86400 sec = 24 hours)
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "/transactionhistory/action/list - test ppv purchases is written correctly at transactionHistory")
    public void purchase_ppv(){

        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold,true);
        String masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());   //Login with master to first device
        String userKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(1).getUdid());   //Login with user to second device

        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);  //All transactions per household
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);   //transactionHistory/action/list for household
        assertThat(listBillingTransactionResponse.error).isNull();
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(0);    //No purchases were performed

        int assetFileId = getSharedWebMediaFile().getId();
        PurchaseUtils.purchasePpv(userKs, Optional.of(getSharedMediaAsset().getId().intValue()), Optional.of(assetFileId), null);   //Purchase asset
        String ppvModuleId = PurchaseUtils.purchasePpvDetailsMap.get("ppvModuleId");
        String pricePpv = PurchaseUtils.purchasePpvDetailsMap.get("price");
        String currencyPpv = PurchaseUtils.purchasePpvDetailsMap.get("currency");

        entitlementPpvFilter.setProductTypeEqual(TransactionType.PPV);  //Show only PPV purchases
        ListEntitlementBuilder listEntitlementBuilder = EntitlementService.list(entitlementPpvFilter).setKs(userKs);
        listEntitlementServiceResponse = executor.executeSync(listEntitlementBuilder);  //entitlement/action/list for household
        List<PpvEntitlement> ppvEntitlementList = new ArrayList<>();    //Conversion from Entitlement to PpvEntitlement
        for(Entitlement entitlement:listEntitlementServiceResponse.results.getObjects()){
            if(entitlement.getClass() == PpvEntitlement.class) {
                ppvEntitlementList.add((PpvEntitlement) entitlement);
            }
        }
        assertThat(listEntitlementServiceResponse.results.getTotalCount()).isEqualTo(1);    //Verify that count of entitlements is 1
        assertThat(ppvEntitlementList.get(0).getProductId()).isEqualTo(ppvModuleId);    //Verify that PPV is correct
        assertThat(ppvEntitlementList.get(0).getMediaFileId()).isEqualTo(assetFileId);  //Verify that asset file is correct

        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);   //All transactions per user
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs); //Use non-master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);   //transactionHistory/action/list for non-master user
        assertThat(listBillingTransactionResponse.error).isNull();  //No errors appeared at response
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);    //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());  //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType().getValue()).isEqualTo(TransactionType.PPV.getValue()); //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);   //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);  //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType().getValue()).isEqualTo("FullPeriod");    //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction().getValue()).isEqualTo("purchase"); //Verify that asset was purchased

        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);  //All transactions per household
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(userKs); //Use non-master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);   //transactionHistory/action/list for household
        assertThat(listBillingTransactionResponse.error).isNull();  //No errors appeared at response
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);    //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());  //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType().getValue()).isEqualTo(TransactionType.PPV.getValue()); //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);   //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);  //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType().getValue()).isEqualTo("FullPeriod");    //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction().getValue()).isEqualTo("purchase"); //Verify that asset was purchased

        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);  //All transactions per household
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);   //Use master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);   //transactionHistory/action/list for household
        assertThat(listBillingTransactionResponse.error).isNull();  //No errors appeared at response
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(1);    //Verify that one transaction was performed
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(getSharedMediaAsset().getId().toString());  //Verify asset id is correct
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getItemType().getValue()).isEqualTo(TransactionType.PPV.getValue()); //Verify transaction is for PPV
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getAmount().toString()).isEqualTo(pricePpv);   //Verify price amount
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getPrice().getCurrency().toString()).isEqualTo(currencyPpv);  //Verify price currency
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingPriceType().getValue()).isEqualTo("FullPeriod");    //Verify entitlement period
        assertThat(listBillingTransactionResponse.results.getObjects().get(0).getBillingAction().getValue()).isEqualTo("purchase"); //Verify that asset was purchased

        transactionHistoryFilter.setEntityReferenceEqual(EntityReferenceBy.USER);   //All transactions per user
        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryFilter).setKs(masterUserKs);   //Use master user ks
        listBillingTransactionResponse = executor.executeSync(listTransactionHistoryBuilder);   //transactionHistory/action/list for master user
        assertThat(listBillingTransactionResponse.error).isNull();  //No errors appeared at response
        assertThat(listBillingTransactionResponse.results.getTotalCount()).isEqualTo(0);    //No transactions were performed with master user
    }
}
