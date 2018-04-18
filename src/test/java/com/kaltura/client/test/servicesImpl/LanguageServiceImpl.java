package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.types.InboxMessage;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

public class LanguageServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<InboxMessage> inboxMessageResponse;
    private static Response<ListResponse<InboxMessage>> inboxMessageListResponse;
    private static Response<Boolean> booleanResponse;

//    // list
//    public static void list(Client client) {
//
//        InboxMessageService.GetInboxMessageBuilder getInboxMessageBuilder = InboxMessageService.get(id)
//                .setCompletion((ApiCompletion<InboxMessage>) result -> {
//                    inboxMessageResponse = result;
//                    done.set(true);
//                });
//
//        TestAPIOkRequestsExecutor.getExecutor().queue(getInboxMessageBuilder.build(client));
//        await().untilTrue(done);
//        done.set(false);
//
//        return inboxMessageResponse;
//    }
}
