package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ConfigurationGroupDeviceService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ConfigurationGroupDeviceService.*;
import static org.awaitility.Awaitility.await;

public class ConfigurationGroupDeviceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ConfigurationGroupDevice> configurationGroupDeviceResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<ConfigurationGroupDevice>> configurationGroupDeviceListResponse;

    // add
    public static Response<Boolean> add(Client client, ConfigurationGroupDevice configurationGroupDevice) {
        AddConfigurationGroupDeviceBuilder addConfigurationGroupDeviceBuilder = ConfigurationGroupDeviceService.add(configurationGroupDevice)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addConfigurationGroupDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String udid) {
        DeleteConfigurationGroupDeviceBuilder deleteConfigurationGroupDeviceBuilder = ConfigurationGroupDeviceService.delete(udid)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteConfigurationGroupDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<ConfigurationGroupDevice> get(Client client, String udid) {
        GetConfigurationGroupDeviceBuilder getConfigurationGroupDeviceBuilder = ConfigurationGroupDeviceService.get(udid)
                .setCompletion((ApiCompletion<ConfigurationGroupDevice>) result -> {
                    configurationGroupDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationGroupDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupDeviceResponse;
    }

    // list
    public static Response<ListResponse<ConfigurationGroupDevice>> list(Client client, ConfigurationGroupDeviceFilter configurationGroupDeviceFilter, @Nullable FilterPager filterPager) {
        ListConfigurationGroupDeviceBuilder listConfigurationGroupDeviceBuilder = ConfigurationGroupDeviceService.list(configurationGroupDeviceFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<ConfigurationGroupDevice>>) result -> {
                    configurationGroupDeviceListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listConfigurationGroupDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupDeviceListResponse;
    }
}
