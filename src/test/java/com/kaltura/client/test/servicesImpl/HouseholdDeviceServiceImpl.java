package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.services.HouseholdDeviceService;
import com.kaltura.client.types.HouseholdDevice;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdDeviceService.AddHouseholdDeviceBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class HouseholdDeviceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdDevice> householdDeviceResponse;


    //add
    public static Response<HouseholdDevice> add(String ks, HouseholdDevice householdDevice) {
        AddHouseholdDeviceBuilder addHouseholdDeviceBuilder = HouseholdDeviceService.add(householdDevice)
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    householdDeviceResponse = result;
                    done.set(true);
                });

        addHouseholdDeviceBuilder.setKs(ks);
        APIOkRequestsExecutor.getExecutor().queue(addHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }
}
