package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.AssetService.*;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.awaitility.Awaitility.await;

public class AssetServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Asset> assetResponse;
    private static Response<ListResponse<Asset>> assetListResponse;

    // get
    public static Response<Asset> get(Client client, String id, AssetReferenceType assetReferenceType) {
        GetAssetBuilder assetBuilder = AssetService.get(id, assetReferenceType)
                .setCompletion((ApiCompletion<Asset>) result -> {
                    assetResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(assetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetResponse;
    }

    // list
    public static Response<ListResponse<Asset>> list(Client client, AssetFilter assetFilter, @Nullable FilterPager filterPager) {
        ListAssetBuilder listAssetBuilder = AssetService.list(assetFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Asset>>) result -> {
                    assetListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listAssetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetListResponse;
    }
}


