package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PersonalFeedService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PersonalFeedService.*;
import static org.awaitility.Awaitility.await;

public class PersonalFeedServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<PersonalFeed>> personalFeedListResponse;

    // list
    public static Response<ListResponse<PersonalFeed>> list(Client client, PersonalFeedFilter personalFeedFilter, @Nullable FilterPager filterPager) {
        ListPersonalFeedBuilder listPersonalFeedBuilder = PersonalFeedService.list(personalFeedFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<PersonalFeed>>) result -> {
                    personalFeedListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listPersonalFeedBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return personalFeedListResponse;
    }
}