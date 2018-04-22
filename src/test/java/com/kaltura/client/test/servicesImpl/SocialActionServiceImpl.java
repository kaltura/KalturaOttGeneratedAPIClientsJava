package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SocialActionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SocialActionService.*;
import static org.awaitility.Awaitility.await;

public class SocialActionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<UserSocialActionResponse> userSocialActionResponseResponse;
    private static Response<List<NetworkActionStatus>> networkActionStatusList;
    private static Response<ListResponse<SocialAction>> socialActionListResponse;

    // add
    public static Response<UserSocialActionResponse> add(Client client, SocialAction socialAction) {
        AddSocialActionBuilder addSocialActionBuilder = SocialActionService.add(socialAction)
                .setCompletion((ApiCompletion<UserSocialActionResponse>) result -> {
                    userSocialActionResponseResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addSocialActionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userSocialActionResponseResponse;
    }

    // delete
    public static Response<List<NetworkActionStatus>> delete(Client client, String id) {
        DeleteSocialActionBuilder deleteSocialActionBuilder = SocialActionService.delete(id)
                .setCompletion((ApiCompletion<List<NetworkActionStatus>>) result -> {
                    networkActionStatusList = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteSocialActionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return networkActionStatusList;
    }

    // list
    public static Response<ListResponse<SocialAction>> list(Client client, SocialActionFilter socialActionFilter, @Nullable FilterPager filterPager) {
        ListSocialActionBuilder listSocialActionBuilder = SocialActionService.list(socialActionFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<SocialAction>>) result -> {
                    socialActionListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSocialActionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialActionListResponse;
    }
}
