package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.OttCategoryService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.OTTCategory;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class ParentalRuleServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<OTTCategory> ottCategoryResponse;

    // disable

    // disableDefault

    // enable

    // list

    // get
    public static Response<OTTCategory> get(Client client, int id) {
        OttCategoryService.GetOttCategoryBuilder getOttCategoryBuilder = OttCategoryService.get(id)
                .setCompletion((ApiCompletion<OTTCategory>) result -> {
                    ottCategoryResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getOttCategoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottCategoryResponse;
    }
}
