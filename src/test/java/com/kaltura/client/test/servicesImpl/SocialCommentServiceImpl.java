package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SocialCommentService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SocialCommentService.*;
import static org.awaitility.Awaitility.await;

public class SocialCommentServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<SocialComment>> socialCommentListResponse;

    // list
    public static Response<ListResponse<SocialComment>> list(Client client, SocialCommentFilter socialCommentFilter, @Nullable FilterPager filterPager) {
        ListSocialCommentBuilder listSocialCommentBuilder = SocialCommentService.list(socialCommentFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<SocialComment>>) result -> {
                    socialCommentListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSocialCommentBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialCommentListResponse;
    }
}
