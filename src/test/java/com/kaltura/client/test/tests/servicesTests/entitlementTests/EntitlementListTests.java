package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.PaymentMethodType;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.services.EntitlementService.ForceCancelEntitlementBuilder;
import com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Optional;
import static com.kaltura.client.enums.EntitlementOrderBy.PURCHASE_DATE_ASC;
import static com.kaltura.client.services.HouseholdService.delete;
import static org.assertj.core.api.Assertions.assertThat;

public class EntitlementListTests extends BaseTest {

    private Household sharedHousehold;
    private EntitlementFilter filter;

    private Response<ListResponse<Entitlement>> entitlementResponse;

    private final int numberOfUsersInHousehold = 1;
    private final int numberOfDevicesInHousehold = 1;
    private String classMasterUserKs;

    @BeforeClass
    public void setUp() {
        sharedHousehold = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        classMasterUserKs = HouseholdUtils.getHouseholdUserKs(sharedHousehold, HouseholdUtils.getDevicesListFromHouseHold(sharedHousehold).get(0).getUdid());

        filter = new EntitlementFilter();
        filter.setOrderBy(PURCHASE_DATE_ASC.getValue());
        filter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("/entitlement/action/list before purchase")
    @Test
    public void entitlementListBeforePurchase() {
        // subscription
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        filter.setIsExpiredEqual(false);
        ListEntitlementBuilder entitlementListBeforePurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        // ppv
        filter.setProductTypeEqual(TransactionType.PPV);
        entitlementListBeforePurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        //with is expiredEqual: true
        filter.setIsExpiredEqual(true);
        // ppv
        filter.setProductTypeEqual(TransactionType.PPV);
        entitlementListBeforePurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        // subscription
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListBeforePurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("/entitlement/action/list after purchase")
    @Test
    public void entitlementListAfterPurchase() {
        PurchaseUtils.purchasePpv(classMasterUserKs, Optional.of(Math.toIntExact(getSharedMediaAsset().getId())),
                Optional.of(getSharedWebMediaFile().getId()), Optional.empty());
        PurchaseUtils.purchaseSubscription(classMasterUserKs, Integer.valueOf(getSharedCommonSubscription().getId()),
                Optional.empty());

        // subscription
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        filter.setIsExpiredEqual(false);
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1); // only sub
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(getSharedCommonSubscription().getId());
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate()).isGreaterThan(
                entitlementResponse.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod()).isIn(
                PaymentMethodType.OFFLINE, PaymentMethodType.UNKNOWN);

        // ppv
        filter.setProductTypeEqual(TransactionType.PPV);
        entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(classMasterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1); // only Ppv
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(getSharedWebMediaFile().getId());
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaId()).isEqualTo(Math.toIntExact(getSharedMediaAsset().getId()));
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate()).isGreaterThan(
                entitlementResponse.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod()).isIn(
                PaymentMethodType.OFFLINE, PaymentMethodType.UNKNOWN);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("/entitlement/action/list after forceCancel")
    @Test
    public void entitlementListAfterForceCancel() {
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        String masterUserId = HouseholdUtils.getMasterUserFromHousehold(household).getUserId();
        String masterUserKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());

        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(getSharedMediaAsset().getId())),
                Optional.of(getSharedWebMediaFile().getId()), Optional.empty());
        PurchaseUtils.purchaseSubscription(masterUserKs, Integer.valueOf(getSharedCommonSubscription().getId()),
                Optional.empty());

        // after purchase
        // ppv
        filter.setProductTypeEqual(TransactionType.PPV);
        filter.setIsExpiredEqual(true);
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        // subscription
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(0);

        // cancel purchases
        ForceCancelEntitlementBuilder forceCancelEntitlementBuilder = EntitlementService.forceCancel(
                Integer.valueOf(getSharedCommonSubscription().getId()), TransactionType.SUBSCRIPTION);
        executor.executeSync(forceCancelEntitlementBuilder.setKs(getOperatorKs()).setUserId(Integer.valueOf(masterUserId)));
        forceCancelEntitlementBuilder = EntitlementService.forceCancel(getSharedWebMediaFile().getId(), TransactionType.PPV);
        executor.executeSync(forceCancelEntitlementBuilder.setKs(getOperatorKs()).setUserId(Integer.valueOf(masterUserId)));

        // after cancel purchase
        // ppv
        filter.setProductTypeEqual(TransactionType.PPV);
        filter.setIsExpiredEqual(true);
        entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1); // only Ppv
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(getSharedWebMediaFile().getId());
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaId()).isEqualTo(Math.toIntExact(getSharedMediaAsset().getId()));
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate()).isLessThanOrEqualTo(
                entitlementResponse.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod()).isIn(
                PaymentMethodType.OFFLINE, PaymentMethodType.UNKNOWN);

        // subscription
        filter.setProductTypeEqual(TransactionType.SUBSCRIPTION);
        entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(1); // only sub
        assertThat(entitlementResponse.results.getObjects().get(0).getProductId()).isEqualTo(getSharedCommonSubscription().getId());
        assertThat(entitlementResponse.results.getObjects().get(0).getEndDate()).isLessThanOrEqualTo(
                entitlementResponse.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementResponse.results.getObjects().get(0).getPaymentMethod()).isIn(
                PaymentMethodType.OFFLINE, PaymentMethodType.UNKNOWN);

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("/entitlement/action/list paging")
    @Test
    public void entitlementListWithPaging() {
        Household household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        //String masterUserId = HouseholdUtils.getMasterUserFromHousehold(household).getUserId();
        String masterUserKs = HouseholdUtils.getHouseholdUserKs(household, HouseholdUtils.getDevicesListFromHouseHold(household).get(0).getUdid());

        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(getSharedMediaAsset().getId())),
                Optional.of(getSharedWebMediaFile().getId()), Optional.empty());
        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(getSharedMediaAsset().getId())),
                Optional.of(getSharedMobileMediaFile().getId()), Optional.empty());

        // after purchase
        // without paging
        filter.setProductTypeEqual(TransactionType.PPV);
        filter.setIsExpiredEqual(false);
        ListEntitlementBuilder entitlementListAfterPurchase = EntitlementService.list(filter, null);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(2); // 2 files
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(getSharedWebMediaFile().getId());
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(1)).getMediaFileId()).isEqualTo(getSharedMobileMediaFile().getId());

        // with paging on 1st page
        FilterPager pager = new FilterPager();
        pager.setPageSize(1);
        pager.setPageIndex(1);
        entitlementListAfterPurchase = EntitlementService.list(filter, pager);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(2); // purchased 2 files
        assertThat(entitlementResponse.results.getObjects().size()).isEqualTo(1); // only 1st file
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(getSharedWebMediaFile().getId());

        // with paging on 2nd page
        pager.setPageIndex(2);
        entitlementListAfterPurchase = EntitlementService.list(filter, pager);
        entitlementResponse = executor.executeSync(entitlementListAfterPurchase.setKs(masterUserKs));
        assertThat(entitlementResponse.results.getTotalCount()).isEqualTo(2); // purchased 2 files
        assertThat(entitlementResponse.results.getObjects().size()).isEqualTo(1); // only 2nd file
        assertThat(((PpvEntitlement)entitlementResponse.results.getObjects().get(0)).getMediaFileId()).isEqualTo(getSharedMobileMediaFile().getId());

        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(household.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));
    }

    @AfterClass
    public void tearDown() {
        //delete household for cleanup
        HouseholdService.DeleteHouseholdBuilder deleteHouseholdBuilder = delete(Math.toIntExact(sharedHousehold.getId()));
        executor.executeSync(deleteHouseholdBuilder.setKs(getAdministratorKs()));
    }
}
