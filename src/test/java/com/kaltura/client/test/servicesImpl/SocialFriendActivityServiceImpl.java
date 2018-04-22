package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SocialFriendActivityService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SocialFriendActivityService.*;
import static org.awaitility.Awaitility.await;

public class SocialFriendActivityServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<SocialFriendActivity>> socialFriendActivityListResponse;

    // list
    public static Response<ListResponse<SocialFriendActivity>> list(Client client, @Nullable SocialFriendActivityFilter socialFriendActivityFilter,
                                                                    @Nullable FilterPager filterPager) {
        ListSocialFriendActivityBuilder listSocialFriendActivityBuilder = SocialFriendActivityService.list(socialFriendActivityFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<SocialFriendActivity>>) result -> {
                    socialFriendActivityListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSocialFriendActivityBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialFriendActivityListResponse;
    }
}
