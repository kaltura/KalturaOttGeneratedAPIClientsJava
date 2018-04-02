package com.kaltura.client.test.tests.servicesTests.productPriceTests;

import com.kaltura.client.enums.PurchaseStatus;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.IngestPPVUtils;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Ppv;
import com.kaltura.client.types.ProductPrice;
import com.kaltura.client.types.ProductPriceFilter;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;
import java.util.Optional;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl.list;
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
}
