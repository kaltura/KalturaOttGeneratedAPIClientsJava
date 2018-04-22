package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SeriesRecordingService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.SeriesRecording;
import com.kaltura.client.types.SeriesRecordingFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SeriesRecordingService.*;
import static org.awaitility.Awaitility.await;

public class SeriesRecordingServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<SeriesRecording> seriesRecordingResponse;
    private static Response<ListResponse<SeriesRecording>> seriesRecordingListResponse;

    // add
    public static Response<SeriesRecording> add(Client client, SeriesRecording seriesRecording) {
        AddSeriesRecordingBuilder addSeriesRecordingBuilder = SeriesRecordingService.add(seriesRecording)
                .setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // cancel
    public static Response<SeriesRecording> cancel(Client client, long id) {
        CancelSeriesRecordingBuilder cancelSeriesRecordingBuilder = SeriesRecordingService.cancel(id)
                .setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // cancelByEpgId
    public static Response<SeriesRecording> cancelByEpgId(Client client, long id, long epgId) {
        CancelByEpgIdSeriesRecordingBuilder cancelByEpgIdSeriesRecordingBuilder = SeriesRecordingService.cancelByEpgId(id, epgId)
                .setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelByEpgIdSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // cancelBySeasonNumber
    public static Response<SeriesRecording> cancelBySeasonNumber(Client client, long id, long seasonNumber) {
        CancelBySeasonNumberSeriesRecordingBuilder cancelBySeasonNumberSeriesRecordingBuilder = SeriesRecordingService
                .cancelBySeasonNumber(id, seasonNumber).setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelBySeasonNumberSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // delete
    public static Response<SeriesRecording> delete(Client client, long id) {
        DeleteSeriesRecordingBuilder deleteSeriesRecordingBuilder = SeriesRecordingService.delete(id)
                .setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // deleteBySeasonNumber
    public static Response<SeriesRecording> deleteBySeasonNumber(Client client, long id, int seasonNumber) {
        DeleteBySeasonNumberSeriesRecordingBuilder deleteBySeasonNumberSeriesRecordingBuilder =
                SeriesRecordingService.deleteBySeasonNumber(id, seasonNumber).setCompletion((ApiCompletion<SeriesRecording>) result -> {
                    seriesRecordingResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteBySeasonNumberSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingResponse;
    }

    // list
    public static Response<ListResponse<SeriesRecording>> list(Client client, @Nullable SeriesRecordingFilter seriesRecordingFilter) {
        ListSeriesRecordingBuilder listSeriesRecordingBuilder = SeriesRecordingService.list(seriesRecordingFilter)
                .setCompletion((ApiCompletion<ListResponse<SeriesRecording>>) result -> {
                    seriesRecordingListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSeriesRecordingBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return seriesRecordingListResponse;
    }
}
