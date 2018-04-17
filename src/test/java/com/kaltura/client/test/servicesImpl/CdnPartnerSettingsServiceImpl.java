package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CdnPartnerSettingsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.CDNPartnerSettings;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CdnPartnerSettingsService.*;
import static org.awaitility.Awaitility.await;

public class CdnPartnerSettingsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<CDNPartnerSettings> cdnPartnerSettingsResponse;

    // get
    public static Response<CDNPartnerSettings> get(Client client) {
        GetCdnPartnerSettingsBuilder getCdnPartnerSettingsBuilder = CdnPartnerSettingsService.get()
                .setCompletion((ApiCompletion<CDNPartnerSettings>) result -> {
                    cdnPartnerSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getCdnPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnPartnerSettingsResponse;
    }

    // update
    public static Response<CDNPartnerSettings> update(Client client, CDNPartnerSettings cdnPartnerSettings) {
        UpdateCdnPartnerSettingsBuilder updateCdnPartnerSettingsBuilder = CdnPartnerSettingsService.update(cdnPartnerSettings)
                .setCompletion((ApiCompletion<CDNPartnerSettings>) result -> {
                    cdnPartnerSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateCdnPartnerSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnPartnerSettingsResponse;
    }
}
