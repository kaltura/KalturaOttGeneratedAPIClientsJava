package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionHistoryOrderBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.services.EntitlementService.GrantEntitlementBuilder;
import com.kaltura.client.services.HouseholdService.*;
import com.kaltura.client.services.TransactionHistoryService;
import com.kaltura.client.services.TransactionHistoryService.ListTransactionHistoryBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.services.OttUserService.RegisterOttUserBuilder;
import static com.kaltura.client.services.OttUserService.register;
import static com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import static com.kaltura.client.services.ProductPriceService.list;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class EntitlementGrantTests extends BaseTest {

    private int ppvId;
    private int assetId;
    private int subscriptionId;
    private int contentId;

    private final int numberOfUsersInHousehold = 2;
    private final int numberOfDevicesInHousehold = 1;

    private Household testSharedHousehold;
    private Response<ListResponse<BillingTransaction>> billingTransactionListResponse;


    @BeforeClass
    private void grant_test_before_class() {
        assetId = Math.toIntExact(getSharedMediaAsset().getId());
        contentId = getSharedWebMediaFile().getId();
        testSharedHousehold = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        subscriptionId = Integer.valueOf(getSharedCommonSubscription().getId());
        ppvId = Integer.valueOf(getSharedCommonPpv().getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("entitlement/action/grant - grant subscription with history = true")
    @Test
    private void grant_subscription_with_history() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);


        // grant subscription - history = true
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(subscriptionId, TransactionType.SUBSCRIPTION, true, 0)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);
        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);

        // verify other user from the household entitled to granted subscription
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.subscriptionIdIn(String.valueOf(subscriptionId));

        ListProductPriceBuilder listProductPriceBuilder = list(productPriceFilter)
                .setUserId(Integer.valueOf(masterUser.getUserId()))
                .setKs(getAdministratorKs());
        Response<ListResponse<ProductPrice>> productPriceListResponse = executor.executeSync(listProductPriceBuilder);
        ProductPrice productPrice = productPriceListResponse.results.getObjects().get(0);

        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(0);
        assertThat(productPrice.getPurchaseStatus().getValue()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED.getValue());


        // check transaction return in transactionHistory by user
        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter =  new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        TransactionHistoryService.ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(subscriptionId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(subscriptionId));


        //delete household for cleanup
        //HouseholdService.delete(getClient(getAdministratorKs()), Math.toIntExact(household.getId()));
        DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("entitlement/action/grant - grant subscription with history = false")
    @Test
    private void grant_subscription_without_history() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);


        // grant subscription - history = true
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(subscriptionId, TransactionType.SUBSCRIPTION, false, 0)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);

        // check transaction not return in transactionHistory by user
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        TransactionHistoryService.ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(0);

        // check transaction not return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(0);


        //delete household for cleanup
        DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("entitlement/action/grant - grant ppv with history = true")
    @Test
    private void grant_ppv_with_history() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);

        // grant subscription - history = true
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, true, contentId)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);
        assertThat(booleanResponse).isNotNull();
        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // verify other user from the household entitled to granted subscription
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.fileIdIn(String.valueOf(contentId));

        ListProductPriceBuilder listProductPriceBuilder = list(productPriceFilter)
                .setUserId(Integer.valueOf(masterUser.getUserId()))
                .setKs(getAdministratorKs());
        Response<ListResponse<ProductPrice>> productPriceListResponse = executor.executeSync(listProductPriceBuilder);
        ProductPrice productPrice = productPriceListResponse.results.getObjects().get(0);

        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(0);
        assertThat(productPrice.getPurchaseStatus().getValue()).isEqualTo(PurchaseStatus.PPV_PURCHASED.getValue());


        // check transaction return in transactionHistory by user
        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        //delete household for cleanup
        DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("entitlement/action/grant - grant ppv with history = false")
    @Test
    private void grant_ppv_without_history() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);
        HouseholdUser user = HouseholdUtils.getRegularUsersList(household).get(0);

        // grant subscription - history = true
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, true, contentId)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);
        assertThat(booleanResponse).isNotNull();
        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);


        // check transaction return in transactionHistory by user
        BillingTransaction billingTransaction;
        TransactionHistoryFilter transactionHistoryfilter = new TransactionHistoryFilter();
        transactionHistoryfilter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.USER.getValue());

        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        // check transaction return in transactionHistory by household
        transactionHistoryfilter.entityReferenceEqual(EntityReferenceBy.HOUSEHOLD.getValue());

        listTransactionHistoryBuilder = TransactionHistoryService.list(transactionHistoryfilter, null)
                .setKs(OttUserUtils.getKs(Integer.parseInt(user.getUserId())));
        billingTransactionListResponse = executor.executeSync(listTransactionHistoryBuilder);
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);

        billingTransaction = billingTransactionListResponse.results.getObjects().get(0);
        assertThat(billingTransaction.getPurchasedItemCode()).isEqualTo(String.valueOf(assetId));


        //delete household for cleanup
        DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()))
                .setKs(getAdministratorKs());
        executor.executeSync(deleteHouseholdBuilder);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/grant - grant ppv with wrong id - error 6001")
    private void grant_ppv_with_wrong_id() {
        int productId = 1;

        // get user form test shared household
        HouseholdUser user = HouseholdUtils.getRegularUsersList(testSharedHousehold).get(0);

        // grant ppv with wrong id
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(productId, TransactionType.PPV, true, contentId)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);


        // assert error 6001 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(6001).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/grant - grant purchased ppv - error 3021")
    @Test
    private void grant_purchased_ppv() {
        // get user form test shared household
        HouseholdUser user = HouseholdUtils.getRegularUsersList(testSharedHousehold).get(0);

        // grant ppv - first time
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, true, contentId)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);
        assertThat(booleanResponse.error).isNull();

        // grant ppv - second time
        grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, true, contentId)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        booleanResponse = executor.executeSync(grantEntitlementBuilder);

        // assert error 3021 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3021).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/grant - grant purchased subscription - error 3024")
    @Test
    private void grant_purchased_subscription() {
        // get user form test shared household
        HouseholdUser user = HouseholdUtils.getRegularUsersList(testSharedHousehold).get(0);

        // grant subscription - first time
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(subscriptionId, TransactionType.SUBSCRIPTION, false, 0)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        executor.executeSync(grantEntitlementBuilder);

        // grant subscription - second time
        grantEntitlementBuilder = EntitlementService.grant(subscriptionId, TransactionType.SUBSCRIPTION, false, 0)
                .setUserId(Integer.valueOf(user.getUserId()))
                .setKs(getAdministratorKs());
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);

        // assert error 3024 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3024).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Issue("BEO-5022")
    @Description("entitlement/action/grant - error 3023")
    @Test(enabled = false)
    private void grant_3023() {
        // TODO: 4/30/2018 implement test
    }

    @Severity(SeverityLevel.NORMAL)
    @Issue("BEO-5022")
    @Description("entitlement/action/grant - grant ppv with invalid content id - error 3018")
    @Test(enabled = false)
    private void grant_ppv_with_invalid_contentId() {
        // get user form test shared household
        HouseholdUser user = HouseholdUtils.getRegularUsersList(testSharedHousehold).get(0);

        // grant ppv with invalid content id
        int invalidContentId = 1;
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, true, invalidContentId)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);
        assertThat(booleanResponse.error).isNull();

        // assert error 3018 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3018).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/grant - user not in domain - error 1005")
    @Test
    private void grant_ppv_user_not_in_domain() {
        // get user form test shared household
        RegisterOttUserBuilder registerOttUserBuilder = register(partnerId, OttUserUtils.generateOttUser(), defaultUserPassword);
        Response<OTTUser> ottUserResponse = executor.executeSync(registerOttUserBuilder);
        OTTUser user = ottUserResponse.results;

        // set user not from household
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(ppvId, TransactionType.PPV, false, contentId)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        // grant subscription
        Response<Boolean> booleanResponse = executor.executeSync(grantEntitlementBuilder);

        // assert error 1005 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1005).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("entitlement/action/grant - user suspend - error 2001")
    @Test
    private void grant_ppv_user_suspend() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, false);
        HouseholdUser masterUser = HouseholdUtils.getMasterUser(household);

        // suspend household
        SuspendHouseholdBuilder suspendHouseholdBuilder = suspend(0)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // grant subscription to suspend user
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(subscriptionId, TransactionType.SUBSCRIPTION, false, 0)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        booleanResponse = executor.executeSync(grantEntitlementBuilder);

        // assert error 2001 is return
        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2001).getCode());
    }

    // TODO: 4/16/2018 finish negative scenarios
//    @Test(description = "entitlement/action/grant - UnableToPurchaseFree - error 3022")
}
