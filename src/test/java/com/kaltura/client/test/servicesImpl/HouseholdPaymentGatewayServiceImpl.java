package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdPaymentGatewayService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.HouseholdPaymentGatewayService.SetChargeIDHouseholdPaymentGatewayBuilder;
import static org.awaitility.Awaitility.await;

public class HouseholdPaymentGatewayServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<Boolean> setChargeId(Client client, String paymentGatewayExternalId, String chargeId) {
        SetChargeIDHouseholdPaymentGatewayBuilder setChargeIDHouseholdPaymentGatewayBuilder =
                new HouseholdPaymentGatewayService.SetChargeIDHouseholdPaymentGatewayBuilder(paymentGatewayExternalId, chargeId)
                    .setCompletion((ApiCompletion<Boolean>) result -> {
                        booleanResponse = result;
                        done.set(true);
                    });

        TestAPIOkRequestsExecutor.getExecutor().queue(setChargeIDHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
