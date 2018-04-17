package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.DeviceBrandService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.DeviceBrand;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.DeviceBrandService.*;
import static org.awaitility.Awaitility.await;

public class DeviceBrandServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<DeviceBrand>> deviceBrandListResponse;

    // list
    public static Response<ListResponse<DeviceBrand>> list(Client client) {
        ListDeviceBrandBuilder listDeviceBrandBuilder = DeviceBrandService.list()
                .setCompletion((ApiCompletion<ListResponse<DeviceBrand>>) result -> {
                    deviceBrandListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listDeviceBrandBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return deviceBrandListResponse;
    }
}
