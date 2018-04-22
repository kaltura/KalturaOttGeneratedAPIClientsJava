package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.TimeShiftedTvPartnerSettingsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.TimeShiftedTvPartnerSettings;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.TimeShiftedTvPartnerSettingsService.*;
import static org.awaitility.Awaitility.await;

public class TimeShiftedTvPartnerSettingsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<TimeShiftedTvPartnerSettings> timeShiftedTvPartnerSettingsResponse;
    private static Response<Boolean> booleanResponse;

    // get
    public static Response<TimeShiftedTvPartnerSettings> get(Client client) {
        GetTimeShiftedTvPartnerSettingsBuilder getTimeShiftedTvPartnerSettingsBuilder =
                TimeShiftedTvPartnerSettingsService.get().setCompletion((ApiCompletion<TimeShiftedTvPartnerSettings>) result -> {
                    timeShiftedTvPartnerSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getTimeShiftedTvPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return timeShiftedTvPartnerSettingsResponse;
    }

    // update
    public static Response<Boolean> update(Client client, TimeShiftedTvPartnerSettings timeShiftedTvPartnerSettings) {
        UpdateTimeShiftedTvPartnerSettingsBuilder updateTimeShiftedTvPartnerSettingsBuilder = TimeShiftedTvPartnerSettingsService
                .update(timeShiftedTvPartnerSettings).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateTimeShiftedTvPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
