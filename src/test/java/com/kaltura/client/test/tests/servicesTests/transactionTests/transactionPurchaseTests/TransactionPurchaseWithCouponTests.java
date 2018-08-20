package com.kaltura.client.test.tests.servicesTests.transactionTests.transactionPurchaseTests;

import com.kaltura.client.enums.CouponGroupType;
import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.CouponsGroupService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.kaltura.client.services.CouponsGroupService.add;
import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.ProductPriceService.list;
import static com.kaltura.client.services.TransactionService.purchase;
import static com.kaltura.client.test.Properties.DISCOUNT_CODE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransactionPurchaseWithCouponTests extends BaseTest {

    @BeforeClass
    public void TransactionPurchaseWithCouponTests_beforeClass() {

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - subscription with coupon")
    @Test()
    public void purchaseSubscriptionWithCoupon() {
        // add coupon group
        CouponsGroup cg = new CouponsGroup();
        cg.setCouponGroupType(CouponGroupType.COUPON);
        cg.setName("Coupon_" + BaseUtils.getEpoch());
        cg.setDiscountId(Long.parseLong(getProperty(DISCOUNT_CODE_ID)));
        cg.setMaxUsesNumber(1);
        cg.setStartDate(getEpoch(Calendar.MONTH, -1));
        cg.setEndDate(getEpoch(Calendar.YEAR, 10));

        cg = executor.executeSync(add(cg)
                .setKs(getOperatorKs()))
                .results;

        RandomCouponGenerationOptions options = new RandomCouponGenerationOptions();
        options.setNumberOfCoupons(1);
        options.setUseLetters(true);
        options.setUseNumbers(true);
        options.setUseSpecialCharacters(true);

        // ingest mpp with coupon group
        List<CouponsGroup> couponsGroups = Arrays.asList(cg);

        MppData mppData = new MppData()
                .pricePlanCode1(getSharedCommonPricePlan().getName())
                .couponGroups(couponsGroups);
        Subscription subscription = insertMpp(mppData);

        // Prepare household
        Household household = createHousehold();
        List<HouseholdDevice> devices = getDevicesList(household);
        String masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());

        // get coupon value
        String couponValue = executor.executeSync(CouponsGroupService.generate(Long.parseLong(cg.getId()), options)
                .setKs(getOperatorKs()))
                .results
                .getObjects()
                .get(0)
                .getValue();

        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(subscription.getId());
        productPriceFilter.setCouponCodeEqual(couponValue);

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
        purchase.setProductId(Integer.valueOf(subscription.getId()));
        purchase.setPrice(productPriceResponse.results.getObjects().get(0).getPrice().getAmount());
        purchase.setCoupon(couponValue);

        // purchase subscription - first time
        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
                .setKs(masterUserKs));

        // assert transaction
        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // assert purchase status
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);

        // cleanup
        executor.executeSync(CouponsGroupService.delete(Long.parseLong(cg.getId())).setKs(getOperatorKs()));
        executor.executeSync(delete().setKs(masterUserKs));
        deleteMpp(subscription.getName());
    }

    // TODO: 8/14/2018 purchase with coupon (also not valid coupon)
}
