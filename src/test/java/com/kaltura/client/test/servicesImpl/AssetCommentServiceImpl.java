package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AssetCommentService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.AssetComment;
import com.kaltura.client.types.AssetCommentFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AssetCommentService.*;
import static org.awaitility.Awaitility.await;

public class AssetCommentServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<AssetComment> assetCommentResponse;
    private static Response<ListResponse<AssetComment>> assetCommentListResponse;


    // add
    public static Response<AssetComment> add(Client client, AssetComment assetComment) {
        AddAssetCommentBuilder addAssetCommentBuilder = AssetCommentService.add(assetComment)
                .setCompletion((ApiCompletion<AssetComment>) result -> {
                    assetCommentResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addAssetCommentBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetCommentResponse;
    }

    // list
    public static Response<ListResponse<AssetComment>> list(Client client, AssetCommentFilter assetCommentFilter, @Nullable FilterPager filterPager) {
        ListAssetCommentBuilder listAssetCommentBuilder = AssetCommentService.list(assetCommentFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<AssetComment>>) result -> {
                    assetCommentListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listAssetCommentBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return assetCommentListResponse;
    }
}
