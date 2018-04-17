package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AssetStatisticsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AssetStatisticsService.*;
import static org.awaitility.Awaitility.await;

public class AssetStatisticsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<AssetStatistics>> assetStatisticsListResponse;

    // query
    public static Response<ListResponse<AssetStatistics>> query(Client client, AssetStatisticsQuery assetStatisticsQuery) {
        QueryAssetStatisticsBuilder queryAssetStatisticsBuilder = AssetStatisticsService.query(assetStatisticsQuery)
                .setCompletion((ApiCompletion<ListResponse<AssetStatistics>>) result -> {
                    assetStatisticsListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(queryAssetStatisticsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetStatisticsListResponse;
    }
}
