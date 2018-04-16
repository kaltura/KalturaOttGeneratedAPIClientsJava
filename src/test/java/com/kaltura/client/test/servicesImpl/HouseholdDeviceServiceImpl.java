package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdDeviceService;
import com.kaltura.client.services.HouseholdDeviceService.*;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdDevice;
import com.kaltura.client.types.HouseholdDeviceFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.awaitility.Awaitility.await;

public class HouseholdDeviceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdDevice> householdDeviceResponse;
    private static Response<ListResponse<HouseholdDevice>> householdDevicesListResponse;


    // add
    public static Response<HouseholdDevice> add(Client client, HouseholdDevice householdDevice) {
        AddHouseholdDeviceBuilder addHouseholdDeviceBuilder = HouseholdDeviceService.add(householdDevice)
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    householdDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }

    // list
    public static Response<ListResponse<HouseholdDevice>> list(Client client, HouseholdDeviceFilter filter) {
        ListHouseholdDeviceBuilder listHouseholdDeviceBuilder = HouseholdDeviceService.list(filter)
                .setCompletion((ApiCompletion<ListResponse<HouseholdDevice>>) result -> {
                    householdDevicesListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDevicesListResponse;
    }
}
