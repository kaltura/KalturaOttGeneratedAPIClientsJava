package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ConfigurationGroupService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ConfigurationGroup;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ConfigurationGroupService.*;
import static org.awaitility.Awaitility.await;

public class ConfigurationGroupServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ConfigurationGroup> configurationGroupResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<ConfigurationGroup>> configurationGroupListResponse;

    // add
    public static Response<ConfigurationGroup> add(Client client, ConfigurationGroup configurationGroup) {
        AddConfigurationGroupBuilder addConfigurationGroupBuilder = ConfigurationGroupService.add(configurationGroup)
                .setCompletion((ApiCompletion<ConfigurationGroup>) result -> {
                    configurationGroupResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addConfigurationGroupBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String id) {
        DeleteConfigurationGroupBuilder deleteConfigurationGroupBuilder = ConfigurationGroupService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteConfigurationGroupBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<ConfigurationGroup> get(Client client, String id) {
        GetConfigurationGroupBuilder getConfigurationGroupBuilder = ConfigurationGroupService.get(id)
                .setCompletion((ApiCompletion<ConfigurationGroup>) result -> {
                    configurationGroupResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationGroupBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupResponse;
    }

    // list
    public static Response<ListResponse<ConfigurationGroup>> list(Client client) {
        ListConfigurationGroupBuilder listConfigurationGroupBuilder = ConfigurationGroupService.list()
                .setCompletion((ApiCompletion<ListResponse<ConfigurationGroup>>) result -> {
                    configurationGroupListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listConfigurationGroupBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupListResponse;
    }

    // update
    public static Response<ConfigurationGroup> update(Client client, String id, ConfigurationGroup configurationGroup) {
        UpdateConfigurationGroupBuilder updateConfigurationGroupBuilder = ConfigurationGroupService.update(id, configurationGroup)
                .setCompletion((ApiCompletion<ConfigurationGroup>) result -> {
                    configurationGroupResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateConfigurationGroupBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupResponse;
    }
}
