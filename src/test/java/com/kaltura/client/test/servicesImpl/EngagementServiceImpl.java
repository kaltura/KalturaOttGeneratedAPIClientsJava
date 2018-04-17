package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.EngagementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Engagement;
import com.kaltura.client.types.EngagementFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EngagementService.*;
import static org.awaitility.Awaitility.await;

public class EngagementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Engagement> engagementResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Engagement>> engagementListResponse;

    // add
    public static Response<Engagement> add(Client client, Engagement engagement) {
        AddEngagementBuilder addEngagementBuilder = EngagementService.add(engagement)
                .setCompletion((ApiCompletion<Engagement>) result -> {
                    engagementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addEngagementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int id) {
        DeleteEngagementBuilder deleteEngagementBuilder = EngagementService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteEngagementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<Engagement> get(Client client, int id) {
        GetEngagementBuilder getEngagementBuilder = EngagementService.get(id)
                .setCompletion((ApiCompletion<Engagement>) result -> {
                    engagementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getEngagementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementResponse;
    }

    // list
    public static Response<ListResponse<Engagement>> list(Client client, EngagementFilter engagementFilter) {
        ListEngagementBuilder listEngagementBuilder = EngagementService.list(engagementFilter)
                .setCompletion((ApiCompletion<ListResponse<Engagement>>) result -> {
                    engagementListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listEngagementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return engagementListResponse;
    }
}
