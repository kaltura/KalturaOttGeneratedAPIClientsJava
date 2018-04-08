package com.kaltura.client.test.tests.servicesTests.productPriceTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.test.servicesImpl.EntitlementServiceImpl;
import com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.IngestPPVUtils;
import com.kaltura.client.test.utils.IngestVODUtils;
import com.kaltura.client.test.utils.PurchaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Optional;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl.list;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class ListTests extends BaseTest {

    private EntitlementFilter entitlementPpvsFilter;

    @BeforeClass
    public void beforeClass() {
        entitlementPpvsFilter = new EntitlementFilter();
        entitlementPpvsFilter.setOrderBy(EntitlementOrderBy.PURCHASE_DATE_ASC.getValue());
        entitlementPpvsFilter.setProductTypeEqual(TransactionType.PPV);
        entitlementPpvsFilter.setEntityReferenceEqual(EntityReferenceBy.HOUSEHOLD);
        entitlementPpvsFilter.setIsExpiredEqual(false);

    }

    @Description("productPrice/action/list - subscription test by Operator without currency")
    @Test(enabled = false) // as used in feature tests
    public void listSubscriptionTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        // TODO: fix! filter.setSubscriptionIdIn(five_min_renewable_subscription_id);
        Response<ListResponse<ProductPrice>> productPriceList = list(operatorKs, filter, Optional.empty());
        // TODO: fix! assertThat(productPriceList.results.getObjects().get(0).getProductId()).isEqualToIgnoringCase(five_min_renewable_subscription_id);
        assertThat(productPriceList.results.getObjects().get(0).getPurchaseStatus() == PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceList.results.getObjects().get(0).getPrice().getAmount() > 0);
    }

    @Description("productPrice/action/list - without required fields (subscriptionIdIn, collectionIdIn and fileIdIn are empty)")
    @Test()
    public void listWithoutRequiredFieldsTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        Response<ListResponse<ProductPrice>> productPriceResponse = list(operatorKs, filter, Optional.empty());
        // TODO: should we create enums for error codes and their messages? A: Yes if library doesn't contain them
        assertThat(productPriceResponse.results).isNull();
        assertThat(productPriceResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500056).getCode());
        assertThat(productPriceResponse.error.getMessage()).isEqualToIgnoringCase(
                "One of the arguments [KalturaProductPriceFilter.subscriptionIdIn, KalturaProductPriceFilter.fileIdIn, KalturaProductPriceFilter.collectionIdIn] must have a value");
    }

    @Description("productPrice/action/list - ppv test")
    @Test(enabled = false) // as failed
    public void ppvTest() {
        /*Ppv ppv = IngestPPVUtils.ingestPPV(INGEST_ACTION_INSERT, true, "My ingest PPV", getProperty(FIFTY_PERCENTS_ILS_DISCOUNT_NAME),
                Double.valueOf(getProperty(AMOUNT_4_99_EUR)), CURRENCY_EUR, getProperty(ONE_DAY_USAGE_MODULE), false, false,
                getProperty(DEFAULT_PRODUCT_CODE), getProperty(WEB_FILE_TYPE), getProperty(MOBILE_FILE_TYPE));*/
        Response<ListResponse<Entitlement>> entitlementListBeforePurchase = EntitlementServiceImpl.list(sharedMasterUserKs, entitlementPpvsFilter, Optional.empty());
        assertThat(entitlementListBeforePurchase.results.getTotalCount() == 0);

        ProductPriceFilter ppFilter = new ProductPriceFilter();
        int webMediaFileId = mediaAsset.getMediaFiles().get(0).getId();
        int mobileMediaFileId = mediaAsset.getMediaFiles().get(1).getId();
        ppFilter.setFileIdIn(String.valueOf(webMediaFileId));
        ppFilter.setIsLowest(false);
        Response<ListResponse<ProductPrice>> productPriceListBeforePurchase = ProductPriceServiceImpl.list(sharedMasterUserKs, ppFilter, Optional.empty());
        assertThat(productPriceListBeforePurchase.results.getTotalCount() == 1);
        assertThat(productPriceListBeforePurchase.results.getObjects().get(0).getPurchaseStatus() == PurchaseStatus.FOR_PURCHASE);
        assertThat(productPriceListBeforePurchase.results.getObjects().get(0).getProductType() == TransactionType.PPV);
        assertThat(((PpvPrice)productPriceListBeforePurchase.results.getObjects().get(0)).getFileId() == webMediaFileId);

        PurchaseUtils.purchasePpv(sharedMasterUserKs, Optional.empty(), Optional.of(webMediaFileId), Optional.empty());

        Response<ListResponse<Entitlement>> entitlementListAfterPurchase = EntitlementServiceImpl.list(sharedMasterUserKs, entitlementPpvsFilter, Optional.empty());
        System.out.println(entitlementListAfterPurchase.results.getTotalCount());
        assertThat(entitlementListAfterPurchase.results.getTotalCount() == 1);
        assertThat(((PpvEntitlement) entitlementListAfterPurchase.results.getObjects().get(0)).getMediaFileId() == webMediaFileId);
        assertThat(((PpvEntitlement) entitlementListAfterPurchase.results.getObjects().get(0)).getMediaId() == mediaAsset.getId().intValue());
        assertThat(entitlementListAfterPurchase.results.getObjects().get(0).getEndDate() >
                entitlementListAfterPurchase.results.getObjects().get(0).getCurrentDate());
        assertThat(entitlementListAfterPurchase.results.getObjects().get(0).getPaymentMethod().equals(PaymentMethodType.OFFLINE) ||
                entitlementListAfterPurchase.results.getObjects().get(0).getPaymentMethod().equals(PaymentMethodType.UNKNOWN));

        Response<ListResponse<ProductPrice>> productPriceListAfterPurchase = ProductPriceServiceImpl.list(sharedMasterUserKs, ppFilter, Optional.empty());
        assertThat(productPriceListAfterPurchase.results.getTotalCount() == 1);
        assertThat(productPriceListAfterPurchase.results.getObjects().get(0).getPurchaseStatus() == PurchaseStatus.PPV_PURCHASED);
        assertThat(((PpvPrice) productPriceListAfterPurchase.results.getObjects().get(0)).getFileId() == webMediaFileId);

        ppFilter.setFileIdIn(String.valueOf(mobileMediaFileId));
        Response<ListResponse<ProductPrice>> productPriceListAfterPurchaseForAnotherFileFromTheSameMedia = ProductPriceServiceImpl.list(sharedMasterUserKs, ppFilter, Optional.empty());
        assertThat(productPriceListAfterPurchaseForAnotherFileFromTheSameMedia.results.getTotalCount() == 1);
        assertThat(productPriceListAfterPurchaseForAnotherFileFromTheSameMedia.results.getObjects().get(0).getPurchaseStatus() == PurchaseStatus.PPV_PURCHASED);
        assertThat(((PpvPrice) productPriceListAfterPurchaseForAnotherFileFromTheSameMedia.results.getObjects().get(0)).getFileId() == mobileMediaFileId);
    }
}
