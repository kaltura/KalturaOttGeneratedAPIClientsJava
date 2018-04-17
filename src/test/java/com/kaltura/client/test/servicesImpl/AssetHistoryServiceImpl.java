package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AssetHistoryService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AssetHistoryService.*;
import static org.awaitility.Awaitility.await;

public class AssetHistoryServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<AssetHistory>> assetHistoryListResponse;

    // clean
    public static void clean(Client client, AssetHistoryFilter assetHistoryFilter) {
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(cleanAssetHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }

    // list
    public static Response<ListResponse<AssetHistory>> list(Client client, AssetHistoryFilter assetHistoryFilter, @Nullable FilterPager filterPager) {
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<AssetHistory>>) result -> {
                    assetHistoryListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listAssetHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetHistoryListResponse;
    }
}
