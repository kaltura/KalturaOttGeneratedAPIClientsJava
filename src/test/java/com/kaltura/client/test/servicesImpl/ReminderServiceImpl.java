package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.ReminderType;
import com.kaltura.client.services.ReminderService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Reminder;
import com.kaltura.client.types.ReminderFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ReminderService.*;
import static org.awaitility.Awaitility.await;

public class ReminderServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Reminder> reminderResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Reminder>> reminderListResponse;

    // add
    public static Response<Reminder> add(Client client, Reminder reminder) {
        AddReminderBuilder addReminderBuilder = ReminderService.add(reminder)
                .setCompletion((ApiCompletion<Reminder>) result -> {
                    reminderResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addReminderBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return reminderResponse;
    }

    // delete
    public static Response<Boolean> add(Client client, long id, ReminderType reminderType) {
        DeleteReminderBuilder deleteReminderBuilder = ReminderService.delete(id, reminderType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteReminderBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<Reminder>> list(Client client, ReminderFilter reminderFilter, @Nullable FilterPager filterPager) {
        ListReminderBuilder listReminderBuilder = ReminderService.list(reminderFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Reminder>>) result -> {
                    reminderListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listReminderBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return reminderListResponse;
    }
}
