package com.kaltura.client.test.tests.servicesTests.productPriceTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.IngestPPVUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;
import java.util.Optional;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl.list;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;

public class ListTests extends BaseTest {

    @Description("productPrice/action/list - subscription test by Operator without currency")
    @Test(enabled = false) // as used in feature tests
    public void listSubscriptionTest() {
        ProductPriceFilter filter = new ProductPriceFilter();
        Ppv ppv = IngestPPVUtils.ingestPPV(INGEST_ACTION_INSERT, true, "My ingest PPV", getProperty(FIFTY_PERCENTS_ILS_DISCOUNT_NAME),
                Double.valueOf(getProperty(AMOUNT_4_99_EUR)), CURRENCY_EUR, getProperty(ONE_DAY_USAGE_MODULE), false, false,
                getProperty(DEFAULT_PRODUCT_CODE), getProperty(WEB_FILE_TYPE), getProperty(MOBILE_FILE_TYPE));
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
}
