package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.NotificationType;
import com.kaltura.client.services.NotificationService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.PushMessage;
import com.kaltura.client.types.RegistryResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.NotificationService.*;
import static com.kaltura.client.services.NotificationService.RegisterNotificationBuilder;
import static com.kaltura.client.services.NotificationService.SendPushNotificationBuilder;
import static org.awaitility.Awaitility.await;

public class NotificationServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;
    private static Response<RegistryResponse> registryResponse;

    // register
    public static Response<RegistryResponse> register(Client client, String identifier, NotificationType notificationType) {
        RegisterNotificationBuilder registerNotificationBuilder = NotificationService.register(identifier, notificationType)
                .setCompletion((ApiCompletion<RegistryResponse>) result -> {
                    registryResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(registerNotificationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return registryResponse;
    }

    // sendPush
    public static Response<Boolean> sendPush(Client client, int userId, PushMessage pushMessage) {
        SendPushNotificationBuilder sendPushNotificationBuilder = NotificationService.sendPush(userId, pushMessage)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(sendPushNotificationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // setDevicePushToken
    public static Response<Boolean> setDevicePushToken(Client client, String pushToken) {
        SetDevicePushTokenNotificationBuilder setDevicePushTokenNotificationBuilder = NotificationService.setDevicePushToken(pushToken)
                .setCompletion(new ApiCompletion<Boolean>() {
                    @Override
                    public void onComplete(Response<Boolean> result) {
                        booleanResponse = result;
                        done.set(true);
                    }
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(setDevicePushTokenNotificationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
