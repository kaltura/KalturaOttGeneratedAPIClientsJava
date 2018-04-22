package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.MessageTemplateType;
import com.kaltura.client.services.MessageTemplateService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.MessageTemplate;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.MessageTemplateService.*;
import static org.awaitility.Awaitility.await;

public class MessageTemplateServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<MessageTemplate> messageTemplateResponse;

    // get
    public static Response<MessageTemplate> get(Client client, MessageTemplateType messageTemplateType) {
        GetMessageTemplateBuilder getMessageTemplateBuilder = MessageTemplateService.get(messageTemplateType)
                .setCompletion((ApiCompletion<MessageTemplate>) result -> {
                    messageTemplateResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getMessageTemplateBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return messageTemplateResponse;
    }

    // update
    public static Response<MessageTemplate> update(Client client, MessageTemplateType messageTemplateType, MessageTemplate messageTemplate) {
        UpdateMessageTemplateBuilder updateMessageTemplateBuilder = MessageTemplateService.update(messageTemplateType, messageTemplate)
                .setCompletion((ApiCompletion<MessageTemplate>) result -> {
                    messageTemplateResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateMessageTemplateBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return messageTemplateResponse;
    }
}
