package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.LicensedUrlService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.LicensedUrlService.*;
import static org.awaitility.Awaitility.await;

public class LicensedUrlServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<LicensedUrl> licensedUrlResponse;

    // get
    public static Response<LicensedUrl> get(Client client, LicensedUrlBaseRequest licensedUrlBaseRequest) {
        GetLicensedUrlBuilder getLicensedUrlBuilder = LicensedUrlService.get(licensedUrlBaseRequest)
                .setCompletion((ApiCompletion<LicensedUrl>) result -> {
                    licensedUrlResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getLicensedUrlBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return licensedUrlResponse;
    }
}
