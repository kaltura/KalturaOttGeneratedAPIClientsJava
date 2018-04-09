package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.AssetService.GetAssetBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class AssetServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Asset> assetResponse;

    // list
    public static Response<Asset> get(String ks, String id, AssetReferenceType assetReferenceType) {
        GetAssetBuilder assetBuilder = AssetService.get(id, assetReferenceType)
                .setCompletion((ApiCompletion<Asset>) result -> {
                    assetResponse = result;
                    done.set(true);
                });

        assetBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(assetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetResponse;
    }
}


