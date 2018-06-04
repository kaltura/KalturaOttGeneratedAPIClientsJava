package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.*;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.services.AssetService.GetPlaybackContextAssetBuilder;
import static com.kaltura.client.services.AssetService.getPlaybackContext;
import static com.kaltura.client.services.EntitlementService.CancelEntitlementBuilder;
import static com.kaltura.client.services.EntitlementService.cancel;
import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.services.OttUserService.login;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdSuspendTests extends BaseTest {

    private final int numberOfUsersInHousehold = 2;
    private final int numberOfDevicesInHousehold = 1;

    private Subscription subscription;
    private Asset asset;

    private enum Permissions {
        PLAYBACK_SUBSCRIPTION,
        PLAYBACK_PPV,
        PURCHASE_SUBSCRIPTION,
        PURCHASE_PPV,
        RENEW_SUBSCRIPTION,
        PURCHASE_SERVICE, // purchase premium services
        LOGIN,
        CANCEL_SUBSCRIPTION,
        DELETE_ALL_APP_TOKENS
    }

    @BeforeClass
    private void household_suspendTests_beforeClass() {
        // set subscription
        subscription = BaseTest.getSharedCommonSubscription();

        // set asset
        asset = SubscriptionUtils.getAssetsListBySubscription(Integer.parseInt(subscription.getId()), Optional.empty()).get(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/suspend")
    @Test
    private void suspend() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // suspend household
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend()
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // get household
        Response<Household> householdResponse = executor.executeSync(get(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
        assertThat(householdResponse.results.getState().getValue()).isEqualTo(HouseholdState.SUSPENDED.getValue());

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("household/action/suspend - with master user ks - error 500004")
    @Test
    private void suspend_with_masterUser_ks() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // suspend household
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.suspend().setKs(masterUserKs));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(500004).getCode());

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with login role - error 7013")
    @Test
    private void suspend_with_login_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.LOGIN.name());
        role.setName(Permissions.LOGIN.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend with login role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // login
        String username = OttUserUtils.getOttUserById(Integer.parseInt(masterUser.getUserId())).getUsername();
        Response<LoginResponse> loginResponse = executor.executeSync(login(partnerId, username, defaultUserPassword));

        assertThat(loginResponse.results).isNull();
        assertThat(loginResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(7013).getCode());

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with purchase_subscription role - error 7013")
    @Test
    private void suspend_with_purchase_subscription_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PURCHASE_SUBSCRIPTION.name());
        role.setName(Permissions.PURCHASE_SUBSCRIPTION.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend with purchase_subscription role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // purchase subscription
        Response<Transaction> transactionResponse = PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());

        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(7013).getCode());

        // purchase ppv in order to verify suspend is specific to role
        Integer mediaFileId = asset.getMediaFiles().get(0).getId();
        transactionResponse = PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(asset.getId())),
                Optional.of(mediaFileId), null);

        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // add device to household to verify suspend is specific to role
        HouseholdDevice householdDevice = HouseholdUtils.generateHouseholdDevice();
        Response<HouseholdDevice> householdDeviceResponse = executor.executeSync(HouseholdDeviceService.add(householdDevice)
                .setKs(masterUserKs));
        assertThat(householdDeviceResponse.error).isNull();

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with cancel_subscription role - error 1009")
    @Test
    private void suspend_with_cancel_subscription_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.CANCEL_SUBSCRIPTION.name());
        role.setName(Permissions.CANCEL_SUBSCRIPTION.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend with cancel_subscription role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // purchase subscription
        PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());

        // cancel subscription
        CancelEntitlementBuilder cancelEntitlementBuilder = cancel(Integer.parseInt(subscription.getId()), TransactionType.SUBSCRIPTION)
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        booleanResponse = executor.executeSync(cancelEntitlementBuilder);

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1009).getCode());

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with playback_subscription role")
    @Test
    private void suspend_with_playback_subscription_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PLAYBACK_SUBSCRIPTION.name());
        role.setName(Permissions.PLAYBACK_SUBSCRIPTION.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend with playback_subscription role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // purchase subscription
        PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());

        // get platbackContext
        PlaybackContextOptions playbackContextOptions = new PlaybackContextOptions();
        playbackContextOptions.setContext(PlaybackContextType.PLAYBACK);
        playbackContextOptions.setStreamerType("applehttp");
        playbackContextOptions.setMediaProtocol("http");

        GetPlaybackContextAssetBuilder getPlaybackContextAssetBuilder = getPlaybackContext(String.valueOf(asset.getId()), AssetType.MEDIA, playbackContextOptions)
                .setKs(masterUserKs);
        Response<PlaybackContext> playbackContextResponse = executor.executeSync(getPlaybackContextAssetBuilder);

        assertThat(playbackContextResponse.results.getActions().get(0).getType()).isEqualTo(RuleActionType.BLOCK);
        assertThat(playbackContextResponse.results.getMessages().get(0).getMessage()).isEqualTo("Not entitled");
        assertThat(playbackContextResponse.results.getMessages().get(0).getCode()).isEqualTo("NotEntitled");

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with purchase_ppv role")
    @Test
    private void suspend_with_purchase_ppv_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PURCHASE_PPV.name());
        role.setName(Permissions.PURCHASE_PPV.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // suspend with purchase_ppv role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // purchase ppv
        Integer mediaFileId = asset.getMediaFiles().get(0).getId();
        Response<Transaction> transactionResponse = PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(asset.getId())),
                Optional.of(mediaFileId), null);

        assertThat(transactionResponse.results).isNull();
        assertThat(transactionResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(7013).getCode());

        // purchase subscription in order to verify suspend is specific to role
        transactionResponse = PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());

        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Issue("BEO-5166")
    @Description("household/action/suspend - with playback_ppv role")
    @Test(enabled = true)
    private void suspend_with_playback_ppv_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PLAYBACK_PPV.name());
        role.setName(Permissions.PLAYBACK_PPV.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // purchase ppv
        Integer mediaFileId = asset.getMediaFiles().get(0).getId();
        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(asset.getId())), Optional.of(mediaFileId), null);

        // suspend with playback_ppv role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // get platbackContext
        PlaybackContextOptions playbackContextOptions = new PlaybackContextOptions();
        playbackContextOptions.setContext(PlaybackContextType.PLAYBACK);
        playbackContextOptions.setStreamerType("applehttp");
        playbackContextOptions.setMediaProtocol("http");

        GetPlaybackContextAssetBuilder getPlaybackContextAssetBuilder = getPlaybackContext(String.valueOf(asset.getId()), AssetType.MEDIA, playbackContextOptions)
                .setKs(masterUserKs);
        Response<PlaybackContext> playbackContextResponse = executor.executeSync(getPlaybackContextAssetBuilder);

        assertThat(playbackContextResponse.results.getActions().get(0).getType()).isEqualTo(RuleActionType.BLOCK);
        assertThat(playbackContextResponse.results.getMessages().get(0).getMessage()).isEqualTo("Not entitled");
        assertThat(playbackContextResponse.results.getMessages().get(0).getCode()).isEqualTo("NotEntitled");

        // purchase subscription in order to verify suspend is specific to role
        Response<Transaction> transactionResponse = PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());
        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Issue("BEO-5173")
    @Description("household/action/suspend - with renew_subscription role")
    @Test(enabled = true, groups = "slow")
    private void suspend_with_renew_subscription_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.RENEW_SUBSCRIPTION.name());
        role.setName(Permissions.RENEW_SUBSCRIPTION.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // purchase subscription
        Subscription fiveMinRenewSubscription = get5MinRenewableSubscription();
        int fiveMinRenewSubscriptionId = Integer.parseInt(fiveMinRenewSubscription.getId().trim());
        PurchaseUtils.purchaseSubscription(masterUserKs, fiveMinRenewSubscriptionId, Optional.empty());

        //***
        System.out.println("here!!!");
        EntitlementFilter filter1 = new EntitlementFilter();
        filter1.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        executor.executeSync(EntitlementService.list(filter1).setKs(masterUserKs));

        // set productPrice filter
        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setSubscriptionIdIn(fiveMinRenewSubscription.getId());

        //get productprice list - before renew
        Response<ListResponse<ProductPrice>> productPriceListResponse = executor.executeSync(ProductPriceService.list(filter).setKs(masterUserKs));
        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceListResponse.results.getObjects().get(0).getProductId()).isEqualTo(fiveMinRenewSubscription.getId().trim());
        assertThat(productPriceListResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);

        // suspend with renew_subscription role
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId()));
        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
        assertThat(booleanResponse.results).isTrue();

        // sleep for 5 min
        try { Thread.sleep(300000); } catch (InterruptedException e) { e.printStackTrace(); }

        //get productprice list - after renew
        productPriceListResponse = executor.executeSync(ProductPriceService.list(filter).setKs(masterUserKs));
        assertThat(productPriceListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(productPriceListResponse.results.getObjects().get(0).getProductId()).isEqualTo(fiveMinRenewSubscription.getId().trim());
        assertThat(productPriceListResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.USER_SUSPENDED);

        //transactionhistory/list - after sleep
        Response<ListResponse<BillingTransaction>> billingTransactionListResponse = executor.executeSync(TransactionHistoryService.list().setKs(masterUserKs));
        assertThat(billingTransactionListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(billingTransactionListResponse.results.getObjects().get(0).getPurchasedItemCode()).isEqualTo(fiveMinRenewSubscription.getId().trim());
        assertThat(billingTransactionListResponse.results.getObjects().get(0).getBillingAction()).isEqualTo(BillingAction.PURCHASE);

        // cleanup - delete role
        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));

        // cleanup - delete household
        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("household/action/suspend - with purchase_service role")
    @Test(enabled = false)
    private void suspend_with_purchase_service_role() {
        // set household
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        HouseholdUser masterUser = HouseholdUtils.getMasterUserFromHousehold(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid();
        String masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);

        // create role
        UserRole role = new UserRole();
        role.setExcludedPermissionNames(Permissions.PURCHASE_SERVICE.name());
        role.setName(Permissions.PURCHASE_SERVICE.name());

        // add role
        Response<UserRole> userRoleResponse = executor.executeSync(UserRoleService.add(role).setKs(getOperatorKs()));
        role = userRoleResponse.results;

        // TODO: 6/4/2018 finsih test

//        // purchase ppv
//        Integer mediaFileId = asset.getMediaFiles().get(0).getId();
//        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(asset.getId())), Optional.of(mediaFileId), null);

//        // suspend with playback_ppv role
//        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(Math.toIntExact(role.getId()))
//                .setKs(getOperatorKs())
//                .setUserId(Integer.valueOf(masterUser.getUserId()));
//        Response<Boolean> booleanResponse = executor.executeSync(suspendHouseholdBuilder);
//        assertThat(booleanResponse.results).isTrue();
//
//        // get platbackContext
//        PlaybackContextOptions playbackContextOptions = new PlaybackContextOptions();
//        playbackContextOptions.setContext(PlaybackContextType.PLAYBACK);
//        playbackContextOptions.setStreamerType("applehttp");
//        playbackContextOptions.setMediaProtocol("http");
//
//        GetPlaybackContextAssetBuilder getPlaybackContextAssetBuilder = getPlaybackContext(String.valueOf(asset.getId()), AssetType.MEDIA, playbackContextOptions)
//                .setKs(masterUserKs);
//        Response<PlaybackContext> playbackContextResponse = executor.executeSync(getPlaybackContextAssetBuilder);
//
//        assertThat(playbackContextResponse.results.getActions().get(0).getType()).isEqualTo(RuleActionType.BLOCK);
//        assertThat(playbackContextResponse.results.getMessages().get(0).getMessage()).isEqualTo("Not entitled");
//        assertThat(playbackContextResponse.results.getMessages().get(0).getCode()).isEqualTo("NotEntitled");
//
//        // purchase subscription in order to verify suspend is specific to role
//        Response<Transaction> transactionResponse = PurchaseUtils.purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()));
//        assertThat(transactionResponse.error).isNull();
//        assertThat(transactionResponse.results.getState()).isEqualTo("OK");
//
//        // cleanup - delete role
//        executor.executeSync(UserRoleService.delete(role.getId()).setKs(getOperatorKs()));
//
//        // cleanup - delete household
//        executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }
}
