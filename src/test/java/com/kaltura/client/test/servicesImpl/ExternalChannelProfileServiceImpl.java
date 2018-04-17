package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ExternalChannelProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ExternalChannelProfile;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ExternalChannelProfileService.*;
import static org.awaitility.Awaitility.await;

public class ExternalChannelProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ExternalChannelProfile> externalChannelProfileResponse;
    private static Response<ListResponse<ExternalChannelProfile>> externalChannelProfileListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<ExternalChannelProfile> add(Client client, ExternalChannelProfile externalChannelProfile) {
        AddExternalChannelProfileBuilder addExternalChannelProfileBuilder = ExternalChannelProfileService.add(externalChannelProfile)
                .setCompletion((ApiCompletion<ExternalChannelProfile>) result -> {
                    externalChannelProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addExternalChannelProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return externalChannelProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int externalChannelId) {
        DeleteExternalChannelProfileBuilder deleteExternalChannelProfileBuilder = ExternalChannelProfileService.delete(externalChannelId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteExternalChannelProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<ExternalChannelProfile>> list(Client client) {
        ListExternalChannelProfileBuilder listExternalChannelProfileBuilder = ExternalChannelProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<ExternalChannelProfile>>) result -> {
                    externalChannelProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listExternalChannelProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return externalChannelProfileListResponse;
    }

    // update
    public static Response<ExternalChannelProfile> update(Client client, int externalChannelId, ExternalChannelProfile externalChannelProfile) {
        UpdateExternalChannelProfileBuilder updateExternalChannelProfileBuilder = ExternalChannelProfileService.update(externalChannelId, externalChannelProfile)
                .setCompletion((ApiCompletion<ExternalChannelProfile>) result -> {
                    externalChannelProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateExternalChannelProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return externalChannelProfileResponse;
    }
}
