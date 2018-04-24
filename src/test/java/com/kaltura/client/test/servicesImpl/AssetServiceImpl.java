package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.AssetService.*;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.awaitility.Awaitility.await;

public class AssetServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Asset> assetResponse;
    private static Response<ListResponse<Asset>> assetListResponse;
    private static Response<AssetCount> assetCountResponse;
    private static Response<AdsContext> adsContextResponse;
    private static Response<PlaybackContext> playbackContextResponse;

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

    // count
    public static Response<AssetCount> get(Client client, @Nullable SearchAssetFilter searchAssetFilter) {
        CountAssetBuilder countAssetBuilder = AssetService.count(searchAssetFilter)
                .setCompletion((ApiCompletion<AssetCount>) result -> {
                    assetCountResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(countAssetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetCountResponse;
    }

    // getAdsContext
    public static Response<AdsContext> getAdsContext(Client client, String assetId, AssetType assetType, PlaybackContextOptions playbackContextOptions) {
        GetAdsContextAssetBuilder getAdsContextAssetBuilder = AssetService.getAdsContext(assetId, assetType, playbackContextOptions)
                .setCompletion((ApiCompletion<AdsContext>) result -> {
                    adsContextResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getAdsContextAssetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return adsContextResponse;
    }

    // getPlaybackContext
    public static Response<PlaybackContext> getPlaybackContext(Client client, String assetId, AssetType assetType, PlaybackContextOptions playbackContextOptions) {
        GetPlaybackContextAssetBuilder getPlaybackContextAssetBuilder = AssetService.getPlaybackContext(assetId, assetType, playbackContextOptions)
                .setCompletion((ApiCompletion<PlaybackContext>) result -> {
                    playbackContextResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getPlaybackContextAssetBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return playbackContextResponse;
    }
}


