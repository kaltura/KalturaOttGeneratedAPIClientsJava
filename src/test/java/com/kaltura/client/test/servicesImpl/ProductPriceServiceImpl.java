package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.ProductPriceService.*;
import static com.kaltura.client.test.tests.BaseTest.client;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductPriceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static final String LIST_RESPONSE_SCHEMA = "schemas/KalturaProductPriceListResponse_Schema.json";

    private static Response<ListResponse<ProductPrice>> productPriceResponse;

    //list
    public static Response<ListResponse<ProductPrice>> list(String ks, ProductPriceFilter filter, Optional<String> currency) {
        ListProductPriceBuilder productPriceBuilder = ProductPriceService.list(filter)
                .setCompletion((ApiCompletion<ListResponse<ProductPrice>>) result -> {
                    productPriceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        if (productPriceResponse.isSuccess()) {
            assertThat(TestAPIOkRequestsExecutor.fullResponseAsString, matchesJsonSchemaInClasspath(LIST_RESPONSE_SCHEMA));
        }

        return productPriceResponse;
    }
}


