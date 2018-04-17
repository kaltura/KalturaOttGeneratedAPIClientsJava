package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.AnnouncementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Announcement;
import com.kaltura.client.types.AnnouncementFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.AnnouncementService.*;
import static org.awaitility.Awaitility.await;

public class AnnouncementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Announcement> announcementResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Announcement>> announcementListResponse;

    // activate
    public static Response<Announcement> add(Client client, Announcement announcement) {
        AddAnnouncementBuilder addAnnouncementBuilder = AnnouncementService.add(announcement)
                .setCompletion((ApiCompletion<Announcement>) result -> {
                    announcementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return announcementResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, long id) {
        DeleteAnnouncementBuilder deleteAnnouncementBuilder = AnnouncementService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // enableSystemAnnouncements
    public static Response<Boolean> enableSystemAnnouncements(Client client) {
        EnableSystemAnnouncementsAnnouncementBuilder enableSystemAnnouncementsAnnouncementBuilder =
                AnnouncementService.enableSystemAnnouncements().setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(enableSystemAnnouncementsAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<Announcement>> list(Client client, AnnouncementFilter announcementFilter, @Nullable FilterPager filterPager) {
        ListAnnouncementBuilder listAnnouncementBuilder = AnnouncementService.list(announcementFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Announcement>>) result -> {
                    announcementListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return announcementListResponse;
    }

    // update
    public static Response<Announcement> update(Client client, int announcementId, Announcement announcement) {
        UpdateAnnouncementBuilder updateAnnouncementBuilder = AnnouncementService.update(announcementId, announcement)
                .setCompletion((ApiCompletion<Announcement>) result -> {
                    announcementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return announcementResponse;
    }

    // updateStatus
    public static Response<Boolean> updateStatus(Client client, long id, boolean status) {
        UpdateStatusAnnouncementBuilder updateStatusAnnouncementBuilder = AnnouncementService.updateStatus(id, status)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusAnnouncementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
