package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ConfigurationsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Configurations;
import com.kaltura.client.types.ConfigurationsFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ConfigurationsService.*;
import static org.awaitility.Awaitility.await;

public class ConfigurationsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Configurations> configurationsResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Configurations>> configurationsListResponse;
    private static Response<String> stringResponse;

    // add
    public static Response<Configurations> add(Client client, Configurations configurations) {
        AddConfigurationsBuilder addConfigurationsBuilder = ConfigurationsService.add(configurations)
                .setCompletion((ApiCompletion<Configurations>) result -> {
                    configurationsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationsResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String id) {
        DeleteConfigurationsBuilder deleteConfigurationsBuilder = ConfigurationsService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<Configurations> get(Client client, String id) {
        GetConfigurationsBuilder getConfigurationsBuilder = ConfigurationsService.get(id)
                .setCompletion((ApiCompletion<Configurations>) result -> {
                    configurationsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationsResponse;
    }

    // list
    public static Response<ListResponse<Configurations>> list(Client client, ConfigurationsFilter configurationsFilter) {
        ListConfigurationsBuilder listConfigurationsBuilder = ConfigurationsService.list(configurationsFilter)
                .setCompletion((ApiCompletion<ListResponse<Configurations>>) result -> {
                    configurationsListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationsListResponse;
    }

    // update
    public static Response<Configurations> update(Client client, String id, Configurations configurations) {
        UpdateConfigurationsBuilder updateConfigurationsBuilder = ConfigurationsService.update(id, configurations)
                .setCompletion((ApiCompletion<Configurations>) result -> {
                    configurationsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationsResponse;
    }

    // serveByDevice
    public static Response<String> serveByDevice(Client client, String applicationName, String clientVersion, String platform, String udid, String tag, @Nullable int partnerId) {
        ServeByDeviceConfigurationsBuilder serveByDeviceConfigurationsBuilder = ConfigurationsService.serveByDevice(applicationName, clientVersion, platform, udid, tag, partnerId);
        serveByDeviceConfigurationsBuilder.setCompletion((ApiCompletion<String>) result -> {
            stringResponse = result;
            done.set(true);
        });

        TestAPIOkRequestsExecutor.getExecutor().queue(serveByDeviceConfigurationsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return stringResponse;
    }
}
