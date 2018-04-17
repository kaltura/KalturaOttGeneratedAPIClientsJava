package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CollectionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Collection;
import com.kaltura.client.types.CollectionFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CollectionService.ListCollectionBuilder;
import static org.awaitility.Awaitility.await;

public class CollectionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Collection>> collectionListResponse;

    // list
    public static Response<ListResponse<Collection>> list(Client client, CollectionFilter collectionFilter) {
        ListCollectionBuilder listCollectionBuilder = CollectionService.list(collectionFilter)
                .setCompletion((ApiCompletion<ListResponse<Collection>>) result -> {
                    collectionListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listCollectionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return collectionListResponse;
    }
}
