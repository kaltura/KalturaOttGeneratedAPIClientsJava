package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.NotificationsSettingsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.NotificationsSettings;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.NotificationsSettingsService.GetNotificationsSettingsBuilder;
import static com.kaltura.client.services.NotificationsSettingsService.UpdateNotificationsSettingsBuilder;
import static org.awaitility.Awaitility.await;

public class NotificationsSettingsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<NotificationsSettings> notificationsSettingsResponse;
    private static Response<Boolean> booleanResponse;

    // get
    public static Response<NotificationsSettings> get(Client client) {
        GetNotificationsSettingsBuilder getNotificationsSettingsBuilder = NotificationsSettingsService.get()
                .setCompletion((ApiCompletion<NotificationsSettings>) result -> {
                    notificationsSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getNotificationsSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return notificationsSettingsResponse;
    }

    // update
    public static Response<Boolean> update(Client client, NotificationsSettings notificationsSettings) {
        UpdateNotificationsSettingsBuilder updateNotificationsSettingsBuilder = NotificationsSettingsService.update(notificationsSettings)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateNotificationsSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
