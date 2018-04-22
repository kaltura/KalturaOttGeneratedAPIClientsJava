package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.InboxMessageStatus;
import com.kaltura.client.services.InboxMessageService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.InboxMessage;
import com.kaltura.client.types.InboxMessageFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.InboxMessageService.*;
import static org.awaitility.Awaitility.await;

public class InboxMessageServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<InboxMessage> inboxMessageResponse;
    private static Response<ListResponse<InboxMessage>> inboxMessageListResponse;
    private static Response<Boolean> booleanResponse;

    // get
    public static Response<InboxMessage> get(Client client, String id) {
        GetInboxMessageBuilder getInboxMessageBuilder = InboxMessageService.get(id)
                .setCompletion((ApiCompletion<InboxMessage>) result -> {
                    inboxMessageResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getInboxMessageBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return inboxMessageResponse;
    }

    // list
    public static Response<ListResponse<InboxMessage>> list(Client client, @Nullable InboxMessageFilter inboxMessageFilter, @Nullable FilterPager filterPager) {
        ListInboxMessageBuilder listInboxMessageBuilder = InboxMessageService.list(inboxMessageFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<InboxMessage>>) result -> {
                    inboxMessageListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listInboxMessageBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return inboxMessageListResponse;
    }

    // updateStatus
    public static Response<Boolean> updateStatus(Client client, String id, InboxMessageStatus inboxMessageStatus) {
        UpdateStatusInboxMessageBuilder updateStatusInboxMessageBuilder = InboxMessageService.updateStatus(id, inboxMessageStatus)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusInboxMessageBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
