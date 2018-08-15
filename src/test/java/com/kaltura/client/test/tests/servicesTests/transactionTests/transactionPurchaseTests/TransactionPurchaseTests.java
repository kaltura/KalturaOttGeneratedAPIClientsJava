package com.kaltura.client.test.tests.servicesTests.transactionTests.transactionPurchaseTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionAdapterStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.HouseholdPaymentGatewayService;
import com.kaltura.client.services.PaymentGatewayProfileService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.ProductPriceService.list;
import static com.kaltura.client.services.TransactionService.purchase;
import static com.kaltura.client.test.Properties.PAYMENT_GATEWAY_ADAPTER_URL;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInLocalTime;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class TransactionPurchaseTests extends BaseTest {

    private String masterUserKs;
    private String userKs;

    @BeforeClass()
    public void transaction_purchase_before_class(){
        // Prepare household with users and devices
        int numOfUsers = 2;
        int numOfDevices = 2;

        Household household = createHousehold(numOfUsers, numOfDevices, true);

        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

        String userUdid = getDevicesList(household).get(1).getUdid();
        userKs = getHouseholdUserKs(household, userUdid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("transaction/action/purchase - ppv with default PG")
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("transaction/action/purchase - subscription with default PG")
    @Test()
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("transaction/action/purchase - collection with default PG")
    @Test()
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

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - subscription with household without PG - error 6007")
    @Test()
    public void purchaseSubscriptionWithoutPG() {
        // Prepare household with users and devices
        Household household = createHousehold(1, 1, false);
        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(getSharedCommonSubscription().getId());

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
        purchase.setProductId(Integer.valueOf(getSharedCommonSubscription().getId()));
        purchase.setProductType(TransactionType.SUBSCRIPTION);

        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(6007).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - collection with household with PG without charge id - error 6009")
    @Test()
    public void purchaseCollectionWithPGWithoutChargeId() {
        // Prepare household with users and devices
        Household household = createHousehold(1, 1, false);
        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

        // create paymentGateway
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssSS");
        String externalIdentifier = df.format(new Date());

        PaymentGatewayProfile pg = new PaymentGatewayProfile();
        pg.setName("paymentGateway_" + getEpochInLocalTime());
        pg.setIsActive(1);
        pg.setIsDefault(false);
        pg.setRenewStartMinutes(-5);
        pg.setRenewIntervalMinutes(15);
        pg.setPendingRetries(0);
        pg.setPendingInterval(0);
        pg.setSharedSecret("123456");
        pg.setExternalIdentifier(externalIdentifier);
        pg.setRenewUrl(PAYMENT_GATEWAY_ADAPTER_URL + "?StateCode=0");
        pg.setAdapterUrl(PAYMENT_GATEWAY_ADAPTER_URL);

        // add paymentGateway
        pg = executor.executeSync(PaymentGatewayProfileService.add(pg)
                .setKs(getOperatorKs()))
                .results;

        // enable pg for hh
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdPaymentGatewayService.enable(pg.getId())
                .setKs(masterUserKs));
        assertThat(booleanResponse.results).isTrue();

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

        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(6009).getCode());

        //cleanup - delete hh and pg
        executor.executeSync(delete().setKs(masterUserKs));
        executor.executeSync(PaymentGatewayProfileService.delete(pg.getId()).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - ppv configured to 'Subscription Only' - error 3023")
    @Test()
    public void purchaseSubscriptionOnlyPpv() {
        // ingest ppv configured to 'Subscription Only'
        PpvData ppvData = new PpvData().isSubscriptionOnly(true);
        Ppv ppv = insertPpv(ppvData);

        VodData vodData = new VodData()
                .ppvWebName(ppv.getName())
                .ppvMobileName(ppv.getName());
        MediaAsset mediaAsset = insertVod(vodData);

        // purchase ppv
        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.PPV);
        purchase.setProductId(Integer.valueOf(ppv.getId()));
        purchase.setContentId(mediaAsset.getMediaFiles().get(0).getId());
        purchase.setCurrency(ppv.getPrice().getPrice().getCurrency());
        purchase.setPrice(ppv.getPrice().getPrice().getAmount());

        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3023).getCode());

        //cleanup - delete ppv
        deletePpv(ppv.getName());
        deleteVod(mediaAsset.getName());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - subscription with invalid price - error 6000")
    @Test()
    public void purchaseSubscriptionWithInvalidPrice() {
        // Prepare household with users and devices
        Household household = createHousehold(1, 1, true);
        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

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
        purchase.setPrice(productPriceResponse.results.getObjects().get(0).getPrice().getAmount() + 1);

        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(6000).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - ppv with invalid ppv moduleId - error 6001")
    @Test()
    public void purchasePpvWithInvalidPpvModuleId() {
        // Prepare household with users and devices
        Household household = createHousehold(1, 1, true);
        String masterUserUdid = getDevicesList(household).get(0).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, masterUserUdid);

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
        purchase.setProductId(1);
        purchase.setContentId(getSharedWebMediaFile().getId());
        purchase.setCurrency(productPricePpv.getPrice().getCurrency());
        purchase.setPrice(productPricePpv.getPrice().getAmount());

        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(6001).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - same collection twice - error 3021")
    @Test()
    public void purchaseCollectionTwice() {
        // Prepare household with users and devices
        Household household = createHousehold(2, 2, true);
        List<HouseholdDevice> devices = getDevicesList(household);
        String masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());
        String userKs = getHouseholdUserKs(household, devices.get(1).getUdid());

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

        // purchase collection - first time
        executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // purchase collection - second time
        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(userKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3027).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - same ppv twice - error 3021")
    @Test()
    public void purchasePpvTwice() {
        // Prepare household with users and devices
        Household household = createHousehold(2, 2, true);
        List<HouseholdDevice> devices = getDevicesList(household);
        String masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());
        String userKs = getHouseholdUserKs(household, devices.get(1).getUdid());

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

        // purchase ppv - first time
        executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // purchase ppv - second time
        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(userKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3021).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - same subscription twice - error 3024")
    @Test()
    public void purchaseSubscriptionTwice() {
        // Prepare household with users and devices
        Household household = createHousehold(2, 2, true);
        List<HouseholdDevice> devices = getDevicesList(household);
        String masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());
        String userKs = getHouseholdUserKs(household, devices.get(1).getUdid());

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

        // purchase subscription - first time
        executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // purchase subscription - second time - because of beo-5249
        executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // purchase subscription - third time
        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(userKs));

        // assert transaction
        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3024).getCode());

        //cleanup - delete hh
        executor.executeSync(delete().setKs(masterUserKs));
    }

    // TODO: 8/15/2018 complete below scenarios:
    // ppv with expired file
    // ppv with file off
    // asset with future start date
    // asset with off ppv
    // asset with expired / off file
    // <error name="NotForPurchase" code="3025" description="The Content ID entered is not available for purchase."/>
    // <error name="FileToMediaMismatch" code="3028" description="The file and media don't match"/>

    @AfterClass
    public void transaction_purchase_after_class() {
        // cleanup
        executor.executeSync(delete().setKs(masterUserKs));
    }
}
