package com.kaltura.client.test.tests.servicesTests.transactionTests.transactionPurchaseTests;

import com.kaltura.client.enums.CouponGroupType;
import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.kaltura.client.services.CouponsGroupService.add;
import static com.kaltura.client.services.ProductPriceService.list;
import static com.kaltura.client.test.Properties.DISCOUNT_CODE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils.deletePpv;
import static com.kaltura.client.test.utils.ingestUtils.IngestPpvUtils.insertPpv;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.deleteVod;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransactionPurchaseWithCouponTests extends BaseTest {

    private static CouponsGroup cg;
    private static List<StringValue> coupons;
    private static String masterUserKs;
    private static Integer discountPercentage;

    @BeforeClass
    public void TransactionPurchaseWithCouponTests_beforeClass() {
        // create household
        Household household = createHousehold();
        List<HouseholdDevice> devices = getDevicesList(household);
        masterUserKs = getHouseholdMasterUserKs(household, devices.get(0).getUdid());

        // add coupon group
        cg = new CouponsGroup();
        cg.setCouponGroupType(CouponGroupType.COUPON);
        cg.setName("Coupon_" + BaseUtils.getEpoch());
        cg.setDiscountId(Long.parseLong(getProperty(DISCOUNT_CODE_ID)));
        cg.setMaxUsesNumber(100);
        cg.setStartDate(getEpoch(Calendar.MONTH, -1));
        cg.setEndDate(getEpoch(Calendar.YEAR, 100));

        cg = executor.executeSync(add(cg)
                .setKs(getOperatorKs()))
                .results;

        // get discount details
        DiscountDetailsFilter filter = new DiscountDetailsFilter();
        filter.setIdIn(getProperty(DISCOUNT_CODE_ID));
        DiscountDetails discountDetails = executor.executeSync(DiscountDetailsService.list(filter)
                .setKs(getOperatorKs())
                .setCurrency("*"))
                .results.getObjects().get(0);
        discountPercentage = discountDetails.getMultiCurrencyDiscount().get(0).getPercentage();

        // generate coupons
        RandomCouponGenerationOptions options = new RandomCouponGenerationOptions();
        options.setNumberOfCoupons(10);
        options.setUseLetters(true);
        options.setUseNumbers(true);
        options.setUseSpecialCharacters(true);

        coupons = executor.executeSync(CouponsGroupService.generate(Long.parseLong(cg.getId()), options)
                .setKs(getOperatorKs()))
                .results
                .getObjects();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("transaction/action/purchase - subscription with coupon")
    @Test()
    public void purchaseSubscriptionWithCoupon() {
        // ingest mpp with coupon group
        List<CouponsGroup> couponsGroups = Arrays.asList(cg);

        MppData mppData = new MppData()
                .pricePlanCode1(getSharedCommonPricePlan().getName())
                .couponGroups(couponsGroups);
        Subscription subscription = insertMpp(mppData);

        // generate coupon
        String couponValue = coupons.get(0).getValue();

        // set product price filter
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setSubscriptionIdIn(subscription.getId());
        productPriceFilter.setCouponCodeEqual(couponValue);

        // productPrice list
        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        ProductPrice productPrice = productPriceResponse.results.getObjects().get(0);
        assertThat(productPrice.getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(subscription.getPrice().getPrice().getAmount() * discountPercentage / 100);
        assertThat(productPrice.getPrice().getCurrency()).isEqualTo(subscription.getPrice().getPrice().getCurrency());

        // purchase subscription
        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.SUBSCRIPTION);
        purchase.setProductId(Integer.valueOf(subscription.getId()));
        purchase.setContentId(0);
        purchase.setCurrency(productPrice.getPrice().getCurrency());
        purchase.setPrice(productPrice.getPrice().getAmount());
        purchase.setCoupon(couponValue);

        // purchase subscription
        Response<Transaction> transactionResponse = executor.executeSync(TransactionService.purchase(purchase)
                .setKs(masterUserKs));

        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // productPrice list - assert purchase status
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));

        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.SUBSCRIPTION_PURCHASED);

        // cleanup
        deleteMpp(subscription.getName());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("transaction/action/purchase - ppv with coupon")
    @Test()
    public void purchasePpvWithCoupon() {
        IngestPpvUtils.PpvData ppvData = new IngestPpvUtils.PpvData().couponGroup(cg);
        Ppv ppv = insertPpv(ppvData);

        IngestVodUtils.VodData vodData = new IngestVodUtils.VodData()
                .ppvWebName(ppv.getName())
                .ppvMobileName(ppv.getName());
        MediaAsset mediaAsset = insertVod(vodData);

        // generate coupon
        String couponValue = coupons.get(1).getValue();

        // set product price filter - get full price
        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setFileIdIn(String.valueOf(mediaAsset.getMediaFiles().get(0).getId()));
        productPriceFilter.setCouponCodeEqual(couponValue);

        // productPrice list
        Response<ListResponse<ProductPrice>> productPriceResponse = executor.executeSync(ProductPriceService
                .list(productPriceFilter)
                .setKs(getAnonymousKs()));
        assertThat(productPriceResponse.results).isNotNull();
        ProductPrice productPrice = productPriceResponse.results.getObjects().get(0);
        assertThat(productPrice.getPurchaseStatus()).isEqualTo(PurchaseStatus.FOR_PURCHASE);
        assertThat(productPrice.getPrice().getAmount()).isEqualTo(ppv.getPrice().getPrice().getAmount() * discountPercentage / 100);
        assertThat(productPrice.getPrice().getCurrency()).isEqualTo(ppv.getPrice().getPrice().getCurrency());

        // purchase ppv
        Purchase purchase = new Purchase();
        purchase.setProductType(TransactionType.PPV);
        purchase.setProductId(Integer.valueOf(ppv.getId()));
        purchase.setContentId(mediaAsset.getMediaFiles().get(0).getId());
        purchase.setCurrency(productPrice.getPrice().getCurrency());
        purchase.setPrice(productPrice.getPrice().getAmount());
        purchase.setCoupon(couponValue);

        // purchase subscription
        Response<Transaction> transactionResponse = executor.executeSync(TransactionService.purchase(purchase)
                .setKs(masterUserKs));
        assertThat(transactionResponse.error).isNull();
        assertThat(transactionResponse.results.getState()).isEqualTo("OK");

        // productPrice list - assert purchase status
        productPriceResponse = executor.executeSync(list(productPriceFilter)
                .setKs(masterUserKs));
        assertThat(productPriceResponse.results).isNotNull();
        assertThat(productPriceResponse.results.getObjects().get(0).getPurchaseStatus()).isEqualTo(PurchaseStatus.PPV_PURCHASED);

        // cleanup
        deletePpv(ppv.getName());
        deleteVod(mediaAsset.getName());
    }

    @AfterClass
    public void TransactionPurchaseWithCouponTests_afterClass() {
        // delete couponGroup
        executor.executeSync(CouponsGroupService.delete(Long.parseLong(cg.getId()))
                .setKs(getOperatorKs()));

        // delete hh
        executor.executeSync(HouseholdService.delete()
                .setKs(masterUserKs));
    }

    // TODO: 8/20/2018 purchase ppv / collection with coupon
    // TODO: 8/14/2018 purchase with not valid coupon
    // TODO: 8/20/2018 purchase with not valid coupon - diffrent coupon group
    // TODO: 8/20/2018 coupon end date expired
    // TODO: 8/20/2018 coupon start date expired  
    // TODO: 8/20/2018 deleted coupon group
    // TODO: 8/20/2018 cg.setCouponGroupType(CouponGroupType.COUPON); giftcard
}
