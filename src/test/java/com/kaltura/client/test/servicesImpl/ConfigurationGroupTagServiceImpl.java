package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ConfigurationGroupTagService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ConfigurationGroupTag;
import com.kaltura.client.types.ConfigurationGroupTagFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ConfigurationGroupTagService.*;
import static org.awaitility.Awaitility.await;

public class ConfigurationGroupTagServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ConfigurationGroupTag> configurationGroupTagResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<ConfigurationGroupTag>> configurationGroupTagListResponse;

    // add
    public static Response<ConfigurationGroupTag> add(Client client, ConfigurationGroupTag configurationGroupTag) {
        AddConfigurationGroupTagBuilder addConfigurationGroupTagBuilder = ConfigurationGroupTagService.add(configurationGroupTag)
                .setCompletion((ApiCompletion<ConfigurationGroupTag>) result -> {
                    configurationGroupTagResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addConfigurationGroupTagBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupTagResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String tag) {
        DeleteConfigurationGroupTagBuilder deleteConfigurationGroupTagBuilder = ConfigurationGroupTagService.delete(tag)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteConfigurationGroupTagBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<ConfigurationGroupTag> get(Client client, String tag) {
        GetConfigurationGroupTagBuilder getConfigurationGroupTagBuilder = ConfigurationGroupTagService.get(tag)
                .setCompletion((ApiCompletion<ConfigurationGroupTag>) result -> {
                    configurationGroupTagResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationGroupTagBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupTagResponse;
    }

    // list
    public static Response<ListResponse<ConfigurationGroupTag>> list(Client client, ConfigurationGroupTagFilter configurationGroupTagFilter) {
        ListConfigurationGroupTagBuilder listConfigurationGroupTagBuilder = ConfigurationGroupTagService.list(configurationGroupTagFilter)
                .setCompletion((ApiCompletion<ListResponse<ConfigurationGroupTag>>) result -> {
                    configurationGroupTagListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listConfigurationGroupTagBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return configurationGroupTagListResponse;
    }
}
