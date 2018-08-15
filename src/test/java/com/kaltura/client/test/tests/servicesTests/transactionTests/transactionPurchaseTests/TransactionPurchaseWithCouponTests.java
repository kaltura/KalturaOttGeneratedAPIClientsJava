package com.kaltura.client.test.tests.servicesTests.transactionTests.transactionPurchaseTests;

import com.kaltura.client.enums.CouponGroupType;
import com.kaltura.client.services.CouponsGroupService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.CouponsGroup;
import com.kaltura.client.types.DiscountDetails;
import com.kaltura.client.types.RandomCouponGenerationOptions;
import com.kaltura.client.types.StringValue;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.CouponsGroupService.add;
import static com.kaltura.client.test.Properties.DISCOUNT_CODE_ID;
import static com.kaltura.client.test.Properties.getProperty;

public class TransactionPurchaseWithCouponTests extends BaseTest {

    public DiscountDetails discount;

    @BeforeClass
    public void TransactionPurchaseWithCouponTests_beforeClass() {
//        // get discount
//        DiscountDetails discount = executor.executeSync(list()
//                .setKs(getOperatorKs()))
//                .results
//                .getObjects()
//                .get(0);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("transaction/action/purchase - subscription with coupon")
    @Test()
    public void purchaseSubscriptionWithCoupon() {
        // add coupon group
        CouponsGroup cg = new CouponsGroup();
        cg.setCouponGroupType(CouponGroupType.COUPON);
        cg.setName("Coupon_" + BaseUtils.getEpochInLocalTime());
        cg.setDiscountId(Long.parseLong(getProperty(DISCOUNT_CODE_ID)));
        cg.setMaxUsesNumber(1);

        cg = executor.executeSync(add(cg)
                .setKs(getOperatorKs()))
                .results;

        RandomCouponGenerationOptions options = new RandomCouponGenerationOptions();
        options.setNumberOfCoupons(1);
        options.setUseLetters(true);
        options.setUseNumbers(true);
        options.setUseSpecialCharacters(true);

        StringValue stringValue = executor.executeSync(CouponsGroupService.generate(Long.parseLong(cg.getId()), options)
                .setKs(getOperatorKs()))
                .results
                .getObjects()
                .get(0);

        // Prepare household with users and devices
//        Household household = createHousehold(2, 2, true);
//        List<HouseholdDevice> devices = getDevicesList(household);
//        String masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());
//        String userKs = getHouseholdUserKs(household, devices.get(1).getUdid());
//
//        // set product price filter
//        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
//        productPriceFilter.setSubscriptionIdIn(getSharedCommonSubscription().getId());
//
//        // productPrice list
//        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(list(productPriceFilter)
//                .setKs(masterUserKs));
//        assertThat(productPriceResponse.results).isNotNull();
//        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
//
//        // purchase subscription
//        Purchase purchase = new Purchase();
//        purchase.setProductType(TransactionType.SUBSCRIPTION);
//        purchase.setContentId(0);
//        purchase.setCurrency(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency());
//        purchase.setProductId(Integer.valueOf(getSharedCommonSubscription().getId()));
//        purchase.setPrice(productPriceResponse.results.getObjects().get(0).getPrice().getAmount());
//
//        // purchase subscription - first time
//        executor.executeSync(purchase(purchase)
//                .setKs(masterUserKs));
//
//        // purchase subscription - second time - because of beo-5249
//        executor.executeSync(purchase(purchase)
//                .setKs(masterUserKs));
//
//        // purchase subscription - third time
//        Response<Transaction> transactionResponse = executor.executeSync(purchase(purchase)
//                .setKs(userKs));
//
//        // assert transaction
//        assertThat(transactionResponse.results).isNull();
//        assertThat(transactionResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(3024).getCode());
//
        // cleanup
        executor.executeSync(CouponsGroupService.delete(Long.parseLong(cg.getId())).setKs(getOperatorKs()));
//        executor.executeSync(delete().setKs(masterUserKs));
    }

    // TODO: 8/14/2018 purchase with coupon (also not valid coupon)
}
