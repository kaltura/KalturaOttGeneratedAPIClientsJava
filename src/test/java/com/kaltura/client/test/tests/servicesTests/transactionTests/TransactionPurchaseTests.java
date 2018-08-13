package com.kaltura.client.test.tests.servicesTests.transactionTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionAdapterStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import com.kaltura.client.services.TransactionService.PurchaseTransactionBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.ProductPriceService.list;
import static com.kaltura.client.services.TransactionService.purchase;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInLocalTime;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class TransactionPurchaseTests extends BaseTest {

    private final int numOfUsers = 2;
    private final int numOfDevices = 2;

    private Household household;
    private String masterUserKs;
    private String userKs;


    @BeforeClass (enabled = true)
    public void transaction_purchas_before_class(){
        // Prepare household with users and devices
        household =  createHousehold(numOfUsers, numOfDevices, true);

        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

        String userUdid = getDevicesList(household).get(1).getUdid();
        userKs = getHouseholdUserKs(household, userUdid);
    }

    @Test(enabled = true)
    public void purchasePpvWithDefaultPG() {
        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setFileIdIn(getSharedWebMediaFile().getId().toString());

        // product price list
        Response<ListResponse<ProductPrice>> listResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(listResponse.results).isNotNull();

        // get ppv
        PpvPrice productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());

        // purchase ppv
        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.PPV);
        purchase.setProductId(Integer.valueOf(productPricePpv.getPpvModuleId()));
        purchase.setContentId(getSharedWebMediaFile().getId());
        purchase.setCurrency(productPricePpv.getPrice().getCurrency());
        purchase.setPrice(productPricePpv.getPrice().getAmount());

        Transaction purchasePpvTransaction = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs))
                .results;

        // assert transaction
        assertThat(purchasePpvTransaction).isNotNull();
        assertThat(purchasePpvTransaction.getCreatedAt()).isCloseTo((int) getEpochInLocalTime(), within(120));
        assertThat(purchasePpvTransaction.getPaymentGatewayResponseId()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpvTransaction.getState()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(purchasePpvTransaction.getFailReasonCode()).isEqualTo(0);
        assertThat(purchasePpvTransaction.getRelatedObjects()).isNull();

        // assert product price list
        listResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));

        assertThat(listResponse.results).isNotNull();
        productPricePpv = (PpvPrice) listResponse.results.getObjects().get(0);

        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());
    }

    @Test (enabled = false)
    public void purchaseSubscriptionWithDefaultPG() {
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(getSharedCommonSubscription().getId());
        ListProductPriceBuilder listProductPriceBuilder = list(productPriceFilter).setKs(masterUserKs);
        Response<ListResponse<ProductPrice>> listResponse = executor.executeSync(listProductPriceBuilder);
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

        PurchaseTransactionBuilder purchaseTransactionBuilder = purchase(purchase).setKs(masterUserKs);
        Response<Transaction> purchaseTransaction = executor.executeSync(purchaseTransactionBuilder);
        Transaction purchasePpv = purchaseTransaction.results;
        assertThat(purchasePpv).isNotNull();
        assertThat(purchasePpv.getCreatedAt()).isCloseTo((int) getEpochInLocalTime(0), within(15));
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
    public void purchaseCollectionWithDefaultPG() {

    }

    @AfterClass
    public void transaction_purchas_after_class() {
        // cleanup
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }
}
