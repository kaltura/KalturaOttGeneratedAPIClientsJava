package com.kaltura.client.test.tests.servicesTests.transactionTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionAdapterStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import com.kaltura.client.services.TransactionService;
import com.kaltura.client.services.TransactionService.PurchaseTransactionBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.utils.BaseUtils.getEpochInLocalTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class TransactionPurchaseTests extends BaseTest {

    private int numberOfUsersInDomain = 2;
    private int numberOfDevicesInDomain = 2;
    private Response<ListResponse<ProductPrice>> listResponse;
    private Household household;
    private String masterUserKs;
    private String userKs;



    @BeforeClass (enabled = false)
    public void beforeClass(){
        // Prepare household with users and devices
        household =  HouseholdUtils.createHousehold(numberOfUsersInDomain, numberOfDevicesInDomain, false);
//        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
//        masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());
//        userKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(1).getUdid());


        PaymentGatewayProfile paymentGatewayProfile = new PaymentGatewayProfile();
        

    }

    @Test(enabled = false)
    private void purchasePpvWithDefaultPG() {
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setFileIdIn(getSharedWebMediaFile().getId().toString());
        ListProductPriceBuilder listProductPriceBuilder = ProductPriceService.list(productPriceFilter).setKs(masterUserKs);
        listResponse = executor.executeSync(listProductPriceBuilder);
        assertThat(listResponse.results).isNotNull();
        PpvPrice productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());

        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.PPV);
        purchase.setProductId(Integer.valueOf(productPricePpv.getPpvModuleId()));
        purchase.setContentId(getSharedWebMediaFile().getId());
        purchase.setCurrency(productPricePpv.getPrice().getCurrency());
        purchase.setPrice(productPricePpv.getPrice().getAmount());

        PurchaseTransactionBuilder purchaseTransactionBuilder = TransactionService.purchase(purchase).setKs(masterUserKs);
        Response<Transaction> purchaseTransaction = executor.executeSync(purchaseTransactionBuilder);
        Transaction purchasePpv = purchaseTransaction.results;
        assertThat(purchasePpv).isNotNull();
        assertThat(purchasePpv.getCreatedAt()).isCloseTo((int)BaseUtils.getEpochInLocalTime(0), within(15));
        assertThat(purchasePpv.getPaymentGatewayResponseId()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpv.getState()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpv.getFailReasonCode()).isEqualTo(0);
        assertThat(purchasePpv.getRelatedObjects()).isNull();

        listResponse = executor.executeSync(listProductPriceBuilder);
        assertThat(listResponse.results).isNotNull();
        productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());
    }

    @Test (enabled = false)
    private void purchaseSubscriptionWithDefaultPG() {
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(getSharedCommonSubscription().getId().toString());
        ListProductPriceBuilder listProductPriceBuilder = ProductPriceService.list(productPriceFilter).setKs(masterUserKs);
        listResponse = executor.executeSync(listProductPriceBuilder);
        assertThat(listResponse.results).isNotNull();
        PpvPrice productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());

        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.PPV);
        purchase.setProductId(Integer.valueOf(productPricePpv.getPpvModuleId()));
        purchase.setContentId(getSharedWebMediaFile().getId());
        purchase.setCurrency(productPricePpv.getPrice().getCurrency());
        purchase.setPrice(productPricePpv.getPrice().getAmount());

        PurchaseTransactionBuilder purchaseTransactionBuilder = TransactionService.purchase(purchase).setKs(masterUserKs);
        Response<Transaction> purchaseTransaction = executor.executeSync(purchaseTransactionBuilder);
        Transaction purchasePpv = purchaseTransaction.results;
        assertThat(purchasePpv).isNotNull();
        assertThat(purchasePpv.getCreatedAt()).isCloseTo((int)BaseUtils.getEpochInLocalTime(0), within(15));
        assertThat(purchasePpv.getPaymentGatewayResponseId()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpv.getState()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpv.getFailReasonCode()).isEqualTo(0);
        assertThat(purchasePpv.getRelatedObjects()).isNull();

        listResponse = executor.executeSync(listProductPriceBuilder);
        assertThat(listResponse.results).isNotNull();
        productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());
    }
}
