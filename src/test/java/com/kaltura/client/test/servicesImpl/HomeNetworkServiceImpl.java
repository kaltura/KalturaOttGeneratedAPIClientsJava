package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HomeNetworkService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HomeNetworkService.*;
import static org.awaitility.Awaitility.await;

public class HomeNetworkServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HomeNetwork> homeNetworkResponse;
    private static Response<ListResponse<HomeNetwork>> homeNetworkListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<HomeNetwork> add(Client client, HomeNetwork homeNetwork) {
        AddHomeNetworkBuilder addHomeNetworkBuilder = HomeNetworkService.add(homeNetwork)
                .setCompletion((ApiCompletion<HomeNetwork>) result -> {
                    homeNetworkResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHomeNetworkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return homeNetworkResponse;
    }

    // delete
    public static Response<HomeNetwork> delete(Client client, String externalId) {
        DeleteHomeNetworkBuilder deleteHomeNetworkBuilder = HomeNetworkService.delete(externalId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteHomeNetworkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return homeNetworkResponse;
    }

    // list
    public static Response<ListResponse<HomeNetwork>> list(Client client) {
        ListHomeNetworkBuilder listHomeNetworkBuilder = HomeNetworkService.list()
                .setCompletion((ApiCompletion<ListResponse<HomeNetwork>>) result -> {
                    homeNetworkListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHomeNetworkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return homeNetworkListResponse;
    }

    // update
    public static Response<HomeNetwork> list(Client client, String externalId, HomeNetwork homeNetwork) {
        UpdateHomeNetworkBuilder updateHomeNetworkBuilder = HomeNetworkService.update(externalId, homeNetwork)
                .setCompletion((ApiCompletion<HomeNetwork>) result -> {
                    homeNetworkResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateHomeNetworkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return homeNetworkResponse;
    }
}
