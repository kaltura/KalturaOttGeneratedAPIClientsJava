package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.ContextType;
import com.kaltura.client.enums.PlaybackContextType;
import com.kaltura.client.services.AssetFileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.AssetFileContext;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AssetFileService.*;
import static org.awaitility.Awaitility.await;

public class AssetFileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<AssetFileContext> assetFileContextResponse;

    // getContext
    public static Response<AssetFileContext> getContext(Client client, String id, ContextType contextType) {
        GetContextAssetFileBuilder getContextAssetFileBuilder = AssetFileService.getContext(id, contextType)
                .setCompletion((ApiCompletion<AssetFileContext>) result -> {
                    assetFileContextResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getContextAssetFileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetFileContextResponse;
    }

    // playManifest
    public static void playManifest(Client client, int partnerId, String assetId, AssetType assetType, long assetFileId,
                                    PlaybackContextType playbackContextType, @Nullable String ks) {
        PlayManifestAssetFileBuilder playManifestAssetFileBuilder = AssetFileService.playManifest(partnerId, assetId, assetType,
                assetFileId, playbackContextType, ks);
        playManifestAssetFileBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(playManifestAssetFileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }
}
