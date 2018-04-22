package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.NotificationsPartnerSettingsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.NotificationsPartnerSettings;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.NotificationsPartnerSettingsService.*;
import static org.awaitility.Awaitility.await;

public class NotificationsPartnerSettingsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<NotificationsPartnerSettings> notificationsPartnerSettingsResponse;
    private static Response<Boolean> booleanResponse;

    // get
    public static Response<NotificationsPartnerSettings> get(Client client) {
        GetNotificationsPartnerSettingsBuilder getNotificationsPartnerSettingsBuilder =
                NotificationsPartnerSettingsService.get().setCompletion((ApiCompletion<NotificationsPartnerSettings>) result -> {
                    notificationsPartnerSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getNotificationsPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return notificationsPartnerSettingsResponse;
    }

    // update
    public static Response<Boolean> update(Client client, NotificationsPartnerSettings notificationsPartnerSettings) {
        UpdateNotificationsPartnerSettingsBuilder updateNotificationsPartnerSettingsBuilder = NotificationsPartnerSettingsService
                .update(notificationsPartnerSettings).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateNotificationsPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
