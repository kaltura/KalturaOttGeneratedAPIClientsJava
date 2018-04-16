package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionHistoryOrderBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.test.servicesImpl.EntitlementServiceImpl;
import com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl;
import com.kaltura.client.test.servicesImpl.TransactionHistoryServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GrantTests extends BaseTest {

    // TODO: 4/12/2018 remove hardcoded subscription Id
    private final int subscriptionId = 369426;
    private final int ppvId = 30297;
    private final int assetId = 607368;

    private int contentId;

    private Response<ListResponse<BillingTransaction>> billingTransactionListResponse;


    @Test(description = "entitlement/action/grant - grant subscription with history = true")
    private void grant_subscription_with_history() {
        Client client = getClient(administratorKs);

        // set household
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);


        // grant subscription - history = true
        client.setUserId(Integer.valueOf(user.getUserId()));
        Response<Boolean> booleanResponse = EntitlementServiceImpl.grant(client, subscriptionId, TransactionType.SUBSCRIPTION, true, null);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // verify other user from the household entitled to granted subscription
        client.setUserId(Integer.valueOf(masterUser.getUserId()));

        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.subscriptionIdIn(String.valueOf(subscriptionId));

        Response<ListResponse<ProductPrice>> productPriceListResponse = ProductPriceServiceImpl.list(client, productPriceFilter);
        ProductPrice productPrice = productPriceListResponse.results.getObjects().get(0);

        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(0);
        assertThat(productPrice.getPurchaseStatus().getValue()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED.getValue());


        // check transaction return in transactionHistory by user
        client.setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        client.setUserId(null);

        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(subscriptionId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(subscriptionId));


        // force cancel subscription
        client.setKs(administratorKs);
        client.setUserId(Integer.valueOf(user.getUserId()));
        EntitlementServiceImpl.forceCancel(client, subscriptionId, TransactionType.SUBSCRIPTION);
    }

    @Test(description = "entitlement/action/grant - grant subscription with history = false")
    private void grant_subscription_without_history() {
        Client client = getClient(administratorKs);

        // set household
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);


        // grant subscription - history = true
        client.setUserId(Integer.valueOf(user.getUserId()));
        Response<Boolean> booleanResponse = EntitlementServiceImpl.grant(client, subscriptionId, TransactionType.SUBSCRIPTION, false, null);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // check transaction not return in transactionHistory by user
        client.setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        client.setUserId(null);

        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(0);


        // check transaction not return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(0);


        // force cancel subscription
        client.setKs(administratorKs);
        client.setUserId(Integer.valueOf(user.getUserId()));
        EntitlementServiceImpl.forceCancel(client, subscriptionId, TransactionType.SUBSCRIPTION);
    }

    @Test(description = "entitlement/action/grant - grant ppv with history = true")
    private void grant_ppv_with_history() {
        Client client = getClient(administratorKs);
        contentId = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0);

        // set household
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);


        // grant subscription - history = true
        client.setUserId(Integer.valueOf(user.getUserId()));
        Response<Boolean> booleanResponse = EntitlementServiceImpl.grant(client, ppvId, TransactionType.PPV, true, contentId);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // verify other user from the household entitled to granted subscription
        client.setUserId(Integer.valueOf(masterUser.getUserId()));

        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.fileIdIn(String.valueOf(contentId));

        Response<ListResponse<ProductPrice>> productPriceListResponse = ProductPriceServiceImpl.list(client, productPriceFilter);
        ProductPrice productPrice = productPriceListResponse.results.getObjects().get(0);

        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(0);
        assertThat(productPrice.getPurchaseStatus().getValue()).isEqualTo(PurchaseStatus.PPV_PURCHASED.getValue());


        // check transaction return in transactionHistory by user
        client.setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        client.setUserId(null);

        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // force cancel subscription
        client.setKs(administratorKs);
        client.setUserId(Integer.valueOf(user.getUserId()));
        EntitlementServiceImpl.forceCancel(client, ppvId, TransactionType.PPV);
    }

    @Test(description = "entitlement/action/grant - grant ppv with history = false")
    private void grant_ppv_without_history() {
        Client client = getClient(administratorKs);
        contentId = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0);

        // set household
        Household household = HouseholdUtils.createHouseHold(2, 1, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersListFromHouseHold(household).get(0);


        // grant subscription - history = true
        client.setUserId(Integer.valueOf(user.getUserId()));
        Response<Boolean> booleanResponse = EntitlementServiceImpl.grant(client, ppvId, TransactionType.PPV, true, contentId);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // check transaction return in transactionHistory by user
        client.setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        client.setUserId(null);

        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, transactionHistoryfilter, null);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // force cancel subscription
        client.setKs(administratorKs);
        client.setUserId(Integer.valueOf(user.getUserId()));
        EntitlementServiceImpl.forceCancel(client, ppvId, TransactionType.PPV);
    }

    // TODO: 4/16/2018 finish negative scenarios 
//    @Test(description = "entitlement/action/grant - ppv - error 6001")
//    @Test(description = "entitlement/action/grant - ppv - error 3021")
//    @Test(description = "entitlement/action/grant - subscription - error 3024")
//    @Test(description = "entitlement/action/grant - subscription - error 3023")

}
