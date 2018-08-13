package com.kaltura.client.test.tests.servicesTests.transactionTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionAdapterStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.HouseholdService;
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

    private String masterUserKs;
    private String userKs;

    @BeforeClass ()
    public void transaction_purchas_before_class(){
        // Prepare household with users and devices
        int numOfUsers = 2;
        int numOfDevices = 2;

        Household household = createHousehold(numOfUsers, numOfDevices, true);

        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

        String userUdid = getDevicesList(household).get(1).getUdid();
        userKs = getHouseholdUserKs(household, userUdid);
    }

    @Test()
    public void purchasePpvWithDefaultPG() {
        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setFileIdIn(getSharedWebMediaFile().getId().toString());

        // productPrice list
        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);

        // get ppv
        PpvPrice productPricePpv = (PpvPrice) productPriceResponse.results.getObjects().get(0);
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

        // assert productPrice list with regular userKs
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(userKs));
        assertThat(productPriceResponse.results).isNotNull();

        productPricePpv = (PpvPrice) productPriceResponse.results.getObjects().get(0);
        assertThat(productPricePpv.getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);
        assertThat(productPricePpv.getFileId()).isEqualTo(getSharedWebMediaFile().getId());
    }

    @Test ()
    public void purchaseSubscriptionWithDefaultPG() {
        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(getSharedCommonSubscription().getId());

        // productPrice list
        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);

        // purchase subscription
        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.SUBSCRIPTION);
        purchase.setContentId(0);
        purchase.setCurrency(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency());
        purchase.setProductId(Integer.valueOf(getSharedCommonSubscription().getId()));
        purchase.setPrice(productPriceResponse.results.getObjects().get(0).getPrice().getAmount());

        Transaction subscriptionTransaction = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs))
                .results;

        // assert transaction
        assertThat(subscriptionTransaction).isNotNull();
        assertThat(subscriptionTransaction.getCreatedAt()).isCloseTo((int) getEpochInLocalTime(0), within(120));
        assertThat(subscriptionTransaction.getPaymentGatewayResponseId()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(subscriptionTransaction.getState()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(subscriptionTransaction.getFailReasonCode()).isEqualTo(0);
        assertThat(subscriptionTransaction.getRelatedObjects()).isNull();

        // assert productPrice list with regular userKs
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(userKs));
        assertThat(productPriceResponse.results).isNotNull();

        SubscriptionPrice subscriptionPrice  = (SubscriptionPrice) productPriceResponse.results.getObjects().get(0);
        assertThat(subscriptionPrice.getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);
    }

    @Test ()
    public void purchaseCollectionWithDefaultPG() {
        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setCollectionIdIn(getSharedCommonCollection().getId());

        // productPrice list
        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);

        // purchase collection
        Purchase purchase = new Purchase();
        purchase.setCurrency(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency());
        purchase.setPrice(productPriceResponse.results.getObjects().get(0).getPrice().getAmount());
        purchase.setContentId(0);
        purchase.setProductId(Integer.valueOf(getSharedCommonCollection().getId()));
        purchase.setProductType(TransactionType.COLLECTION);

        Transaction collectionTransaction = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs))
                .results;

        // assert transaction
        assertThat(collectionTransaction).isNotNull();
        assertThat(collectionTransaction.getCreatedAt()).isCloseTo((int) getEpochInLocalTime(0), within(120));
        assertThat(collectionTransaction.getPaymentGatewayResponseId()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(collectionTransaction.getState()).isEqualTo(TransactionAdapterStatus.OK.getValue());
        assertThat(collectionTransaction.getFailReasonCode()).isEqualTo(0);
        assertThat(collectionTransaction.getRelatedObjects()).isNull();

        // assert productPrice list with regular userKs
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(userKs));
        assertThat(productPriceResponse.results).isNotNull();

        CollectionPrice collectionPrice  = (CollectionPrice) productPriceResponse.results.getObjects().get(0);
        assertThat(collectionPrice.getPurchaseStatus()).isEqualTo(PurchaseStatus.COLLECTION_PURCHASED);
    }

    @AfterClass
    public void transaction_purchas_after_class() {
        // cleanup
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }
}
