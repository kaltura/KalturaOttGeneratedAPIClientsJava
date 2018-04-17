package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.DeviceFamilyService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.DeviceFamily;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.DeviceFamilyService.*;
import static org.awaitility.Awaitility.await;

public class DeviceFamilyServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<DeviceFamily>> deviceFamilyListResponse;

    // list
    public static Response<ListResponse<DeviceFamily>> list(Client client) {
        ListDeviceFamilyBuilder listDeviceFamilyBuilder = DeviceFamilyService.list()
                .setCompletion((ApiCompletion<ListResponse<DeviceFamily>>) result -> {
                    deviceFamilyListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listDeviceFamilyBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return deviceFamilyListResponse;
    }
}
