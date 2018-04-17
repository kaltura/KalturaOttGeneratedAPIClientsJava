package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ExportTaskService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ExportTask;
import com.kaltura.client.types.ExportTaskFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ExportTaskService.*;
import static org.awaitility.Awaitility.await;

public class ExportTaskServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ExportTask> exportTaskResponse;
    private static Response<ListResponse<ExportTask>> exportTaskListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<ExportTask> add(Client client, ExportTask exportTask) {
        AddExportTaskBuilder addExportTaskBuilder = ExportTaskService.add(exportTask)
                .setCompletion((ApiCompletion<ExportTask>) result -> {
                    exportTaskResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addExportTaskBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return exportTaskResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, long id) {
        DeleteExportTaskBuilder deleteExportTaskBuilder = ExportTaskService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteExportTaskBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<ExportTask> get(Client client, long id) {
        GetExportTaskBuilder getExportTaskBuilder = ExportTaskService.get(id)
                .setCompletion((ApiCompletion<ExportTask>) result -> {
                    exportTaskResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getExportTaskBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return exportTaskResponse;
    }

    // list
    public static Response<ListResponse<ExportTask>> list(Client client, @Nullable ExportTaskFilter exportTaskFilter) {
        ListExportTaskBuilder listExportTaskBuilder = ExportTaskService.list(exportTaskFilter)
                .setCompletion((ApiCompletion<ListResponse<ExportTask>>) result -> {
                    exportTaskListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listExportTaskBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return exportTaskListResponse;
    }

    // update
    public static Response<ExportTask> update(Client client, long id, ExportTask exportTask) {
        UpdateExportTaskBuilder updateExportTaskBuilder = ExportTaskService.update(id, exportTask)
                .setCompletion((ApiCompletion<ExportTask>) result -> {
                    exportTaskResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateExportTaskBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return exportTaskResponse;
    }
}
