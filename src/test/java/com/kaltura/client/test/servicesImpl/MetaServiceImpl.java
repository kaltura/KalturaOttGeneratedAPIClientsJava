package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.MetaService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Meta;
import com.kaltura.client.types.MetaFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.MetaService.*;
import static org.awaitility.Awaitility.await;

public class MetaServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Meta>> metaListResponse;
    private static Response<Meta> metaResponse;

    // list
    public static Response<ListResponse<Meta>> list(Client client, @Nullable MetaFilter metaFilter) {
        ListMetaBuilder listMetaBuilder = MetaService.list(metaFilter)
                .setCompletion((ApiCompletion<ListResponse<Meta>>) result -> {
                    metaListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listMetaBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return metaListResponse;
    }

    // update
    public static Response<Meta> update(Client client, String id, Meta meta) {
        UpdateMetaBuilder updateMetaBuilder = MetaService.update(id, meta)
                .setCompletion((ApiCompletion<Meta>) result -> {
                    metaResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateMetaBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return metaResponse;
    }
}
