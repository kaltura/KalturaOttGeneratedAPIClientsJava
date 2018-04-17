package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.EmailService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.EmailMessage;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EmailService.*;
import static org.awaitility.Awaitility.await;

public class EmailServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;

    // send
    public static Response<Boolean> send(Client client, EmailMessage emailMessage) {
        SendEmailBuilder sendEmailBuilder = EmailService.send(emailMessage)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(sendEmailBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
