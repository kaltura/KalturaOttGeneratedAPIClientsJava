package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ReportService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ReportService.*;
import static org.awaitility.Awaitility.await;

public class ReportServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Report> reportResponse;
    private static Response<ListResponse<Report>> reportListResponse;

    // get
    public static Response<Report> get(Client client, String udid) {
        GetReportBuilder getReportBuilder = ReportService.get(udid)
                .setCompletion((ApiCompletion<Report>) result -> {
                    reportResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getReportBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return reportResponse;
    }

    // list
    public static Response<ListResponse<Report>> list(Client client, ReportFilter reportFilter, @Nullable FilterPager filterPager) {
        ListReportBuilder listReportBuilder = ReportService.list(reportFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<Report>>) result -> {
                    reportListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listReportBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return reportListResponse;
    }
}
