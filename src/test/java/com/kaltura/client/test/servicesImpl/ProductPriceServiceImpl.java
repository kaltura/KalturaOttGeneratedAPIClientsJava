package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.ProductPrice;
import com.kaltura.client.types.ProductPriceFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class ProductPriceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<ProductPrice>> productPriceResponse;

    // list
    public static Response<ListResponse<ProductPrice>> list(String ks, ProductPriceFilter filter, Optional<String> currency) {
        client.setKs(ks);
        if (currency.isPresent()) {
            client.setCurrency(currency.get());
        }
        ListProductPriceBuilder productPriceBuilder = ProductPriceService.list(filter)
                .setCompletion((ApiCompletion<ListResponse<ProductPrice>>) result -> {
                    productPriceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return productPriceResponse;
    }
}


