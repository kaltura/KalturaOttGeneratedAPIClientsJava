package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.FollowTvSeriesService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.FollowTvSeries;
import com.kaltura.client.types.FollowTvSeriesFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.FollowTvSeriesService.*;
import static org.awaitility.Awaitility.await;

public class FollowTvSeriesServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<FollowTvSeries> followTvSeriesResponse;
    private static Response<ListResponse<FollowTvSeries>> followTvSeriesListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<FollowTvSeries> add(Client client, FollowTvSeries followTvSeries) {

        AddFollowTvSeriesBuilder addFollowTvSeriesBuilder = FollowTvSeriesService.add(followTvSeries)
                .setCompletion((ApiCompletion<FollowTvSeries>) result -> {
                    followTvSeriesResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addFollowTvSeriesBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return followTvSeriesResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int assetId) {
        DeleteFollowTvSeriesBuilder deleteFollowTvSeriesBuilder = FollowTvSeriesService.delete(assetId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteFollowTvSeriesBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<FollowTvSeries>> list(Client client, FollowTvSeriesFilter followTvSeriesFilter, @Nullable FilterPager filterPager) {
        ListFollowTvSeriesBuilder listFollowTvSeriesBuilder = FollowTvSeriesService.list(followTvSeriesFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<FollowTvSeries>>) result -> {
                    followTvSeriesListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listFollowTvSeriesBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return followTvSeriesListResponse;
    }
}
