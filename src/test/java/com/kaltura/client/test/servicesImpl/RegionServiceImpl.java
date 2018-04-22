package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.RegionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Region;
import com.kaltura.client.types.RegionFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.RegionService.*;
import static org.awaitility.Awaitility.await;

public class RegionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Region>> regionListResponse;

    // list
    public static Response<ListResponse<Region>> list(Client client, RegionFilter regionFilter) {
        ListRegionBuilder listRegionBuilder = RegionService.list(regionFilter)
                .setCompletion((ApiCompletion<ListResponse<Region>>) result -> {
                    regionListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listRegionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return regionListResponse;
    }
}
