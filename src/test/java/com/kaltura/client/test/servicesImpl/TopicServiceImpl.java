package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.TopicAutomaticIssueNotification;
import com.kaltura.client.services.TopicService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.TopicService.*;
import static org.awaitility.Awaitility.await;

public class TopicServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Topic> topicResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Topic>> topicListResponse;

    // get
    public static Response<Topic> get(Client client, int id) {
        GetTopicBuilder getTopicBuilder = TopicService.get(id)
                .setCompletion((ApiCompletion<Topic>) result -> {
                    topicResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getTopicBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return topicResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int id) {
        DeleteTopicBuilder deleteTopicBuilder = TopicService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteTopicBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<Topic>> list(Client client, @Nullable TopicFilter topicFilter, @Nullable FilterPager filterPager) {
        ListTopicBuilder listTopicBuilder = TopicService.list(topicFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Topic>>) result -> {
                    topicListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listTopicBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return topicListResponse;
    }

    // updateStatus
    public static Response<Boolean> updateStatus(Client client, int id, TopicAutomaticIssueNotification topicAutomaticIssueNotification) {
        UpdateStatusTopicBuilder updateStatusTopicBuilder = TopicService.updateStatus(id, topicAutomaticIssueNotification)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusTopicBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

}
