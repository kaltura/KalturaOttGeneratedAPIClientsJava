package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.RecordingService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.RecordingService.*;
import static org.awaitility.Awaitility.await;

public class RecordingServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Recording> recordingResponse;
    private static Response<ListResponse<Recording>> recordingListResponse;

    // add
    public static Response<Recording> add(Client client, Recording recording) {
        AddRecordingBuilder addRecordingBuilder = RecordingService.add(recording)
                .setCompletion((ApiCompletion<Recording>) result -> {
                    recordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingResponse;
    }

    // cancel
    public static Response<Recording> cancel(Client client, long id) {
        CancelRecordingBuilder cancelRecordingBuilder = RecordingService.cancel(id)
                .setCompletion((ApiCompletion<Recording>) result -> {
                    recordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingResponse;
    }

    // delete
    public static Response<Recording> delete(Client client, long id) {
        DeleteRecordingBuilder deleteRecordingBuilder = RecordingService.delete(id)
                .setCompletion((ApiCompletion<Recording>) result -> {
                    recordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingResponse;
    }

    // get
    public static Response<Recording> get(Client client, long id) {
        GetRecordingBuilder getRecordingBuilder = RecordingService.get(id)
                .setCompletion((ApiCompletion<Recording>) result -> {
                    recordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingResponse;
    }

    // list
    public static Response<ListResponse<Recording>> list(Client client, @Nullable RecordingFilter recordingFilter, @Nullable FilterPager filterPager) {
        ListRecordingBuilder listRecordingBuilder = RecordingService.list(recordingFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Recording>>) result -> {
                    recordingListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingListResponse;
    }

    // protect
    public static Response<Recording> protect(Client client, long id) {
        ProtectRecordingBuilder protectRecordingBuilder = RecordingService.protect(id)
                .setCompletion((ApiCompletion<Recording>) result -> {
                    recordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(protectRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recordingResponse;
    }
}
