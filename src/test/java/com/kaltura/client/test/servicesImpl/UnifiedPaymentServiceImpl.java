package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.UnifiedPaymentService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.UnifiedPaymentRenewal;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UnifiedPaymentService.GetNextRenewalUnifiedPaymentBuilder;
import static org.awaitility.Awaitility.await;

public class UnifiedPaymentServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<UnifiedPaymentRenewal> unifiedPaymentRenewalResponse;

    // getNextRenewal
    public static Response<UnifiedPaymentRenewal> getNextRenewal(Client client, int id) {
        GetNextRenewalUnifiedPaymentBuilder getNextRenewalUnifiedPaymentBuilder = UnifiedPaymentService.getNextRenewal(id)
                .setCompletion((ApiCompletion<UnifiedPaymentRenewal>) result -> {
                    unifiedPaymentRenewalResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getNextRenewalUnifiedPaymentBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return unifiedPaymentRenewalResponse;
    }
}
