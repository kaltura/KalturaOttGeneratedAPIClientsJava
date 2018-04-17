package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.EngagementAdapterService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.EngagementAdapter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EngagementAdapterService.*;
import static org.awaitility.Awaitility.await;

public class EngagementAdapterServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<EngagementAdapter> engagementAdapterResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<EngagementAdapter>> engagementAdapterListResponse;

    // add
    public static Response<EngagementAdapter> add(Client client, EngagementAdapter engagementAdapter) {
        AddEngagementAdapterBuilder addEngagementAdapterBuilder = EngagementAdapterService.add(engagementAdapter)
                .setCompletion((ApiCompletion<EngagementAdapter>) result -> {
                    engagementAdapterResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementAdapterResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int id) {
        DeleteEngagementAdapterBuilder deleteEngagementAdapterBuilder = EngagementAdapterService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<EngagementAdapter> get(Client client, int id) {
        GetEngagementAdapterBuilder getEngagementAdapterBuilder = EngagementAdapterService.get(id)
                .setCompletion((ApiCompletion<EngagementAdapter>) result -> {
                    engagementAdapterResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementAdapterResponse;
    }

    // list
    public static Response<ListResponse<EngagementAdapter>> list(Client client) {
        ListEngagementAdapterBuilder listEngagementAdapterBuilder = EngagementAdapterService.list()
                .setCompletion((ApiCompletion<ListResponse<EngagementAdapter>>) result -> {
                    engagementAdapterListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementAdapterListResponse;
    }

    // update
    public static Response<EngagementAdapter> update(Client client, int id, EngagementAdapter engagementAdapter) {
        UpdateEngagementAdapterBuilder updateEngagementAdapterBuilder = EngagementAdapterService.update(id, engagementAdapter)
                .setCompletion((ApiCompletion<EngagementAdapter>) result -> {
                    engagementAdapterResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementAdapterResponse;
    }

    // generateSharedSecret
    public static Response<EngagementAdapter> generateSharedSecret(Client client, int id) {
        GenerateSharedSecretEngagementAdapterBuilder generateSharedSecretEngagementAdapterBuilder = EngagementAdapterService.generateSharedSecret(id)
                .setCompletion((ApiCompletion<EngagementAdapter>) result -> {
                    engagementAdapterResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretEngagementAdapterBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementAdapterResponse;
    }
}
