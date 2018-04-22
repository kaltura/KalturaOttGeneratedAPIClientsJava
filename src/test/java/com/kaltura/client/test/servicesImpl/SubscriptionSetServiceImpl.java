package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SubscriptionSetService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SubscriptionSetService.*;
import static org.awaitility.Awaitility.await;

public class SubscriptionSetServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<SubscriptionSet>> subscriptionSetListResponse;
    private static Response<SubscriptionSet> subscriptionSetResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<SubscriptionSet> add(Client client, SubscriptionSet subscriptionSet) {
        AddSubscriptionSetBuilder addSubscriptionSetBuilder = SubscriptionSetService.add(subscriptionSet)
                .setCompletion((ApiCompletion<SubscriptionSet>) result -> {
                    subscriptionSetResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addSubscriptionSetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return subscriptionSetResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, long id) {
        DeleteSubscriptionSetBuilder deleteSubscriptionSetBuilder = SubscriptionSetService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteSubscriptionSetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<SubscriptionSet> get(Client client, long id) {
        GetSubscriptionSetBuilder getSubscriptionSetBuilder = SubscriptionSetService.get(id)
                .setCompletion((ApiCompletion<SubscriptionSet>) result -> {
                    subscriptionSetResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getSubscriptionSetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return subscriptionSetResponse;
    }

    // list
    public static Response<ListResponse<SubscriptionSet>> delete(Client client, @Nullable SubscriptionSetFilter subscriptionSetFilter) {
        ListSubscriptionSetBuilder listSubscriptionSetBuilder = SubscriptionSetService.list(subscriptionSetFilter)
                .setCompletion((ApiCompletion<ListResponse<SubscriptionSet>>) result -> {
                    subscriptionSetListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSubscriptionSetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return subscriptionSetListResponse;
    }

    // update
    public static Response<SubscriptionSet> delete(Client client, long id, SubscriptionSet subscriptionSet) {
        UpdateSubscriptionSetBuilder updateSubscriptionSetBuilder = SubscriptionSetService.update(id, subscriptionSet)
                .setCompletion((ApiCompletion<SubscriptionSet>) result -> {
                    subscriptionSetResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateSubscriptionSetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return subscriptionSetResponse;
    }
}
