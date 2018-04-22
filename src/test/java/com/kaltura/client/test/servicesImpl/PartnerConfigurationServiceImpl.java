package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PartnerConfigurationService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.PartnerConfiguration;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PartnerConfigurationService.UpdatePartnerConfigurationBuilder;
import static org.awaitility.Awaitility.await;

public class PartnerConfigurationServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;

    // update
    public static Response<Boolean> update(Client client, PartnerConfiguration partnerConfiguration) {
        UpdatePartnerConfigurationBuilder updatePartnerConfigurationBuilder = PartnerConfigurationService.update(partnerConfiguration)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePartnerConfigurationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
