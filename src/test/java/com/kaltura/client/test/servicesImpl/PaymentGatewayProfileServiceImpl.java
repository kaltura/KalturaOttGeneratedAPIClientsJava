package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PaymentGatewayProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.KeyValue;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PaymentGatewayConfiguration;
import com.kaltura.client.types.PaymentGatewayProfile;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PaymentGatewayProfileService.*;
import static org.awaitility.Awaitility.await;

public class PaymentGatewayProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<PaymentGatewayProfile> paymentGatewayProfileResponse;
    private static Response<PaymentGatewayConfiguration> paymentGatewayConfigurationResponse;
    private static Response<ListResponse<PaymentGatewayProfile>> paymentGatewayProfileListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<PaymentGatewayProfile> add(Client client, PaymentGatewayProfile paymentGatewayProfile) {
        AddPaymentGatewayProfileBuilder addPaymentGatewayProfileBuilder = PaymentGatewayProfileService
                .add(paymentGatewayProfile).setCompletion((ApiCompletion<PaymentGatewayProfile>) result -> {
                    paymentGatewayProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addPaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int paymentGatewayId) {
        DeletePaymentGatewayProfileBuilder deletePaymentGatewayProfileBuilder = PaymentGatewayProfileService.delete(paymentGatewayId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deletePaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generateSharedSecret
    public static Response<PaymentGatewayProfile> generateSharedSecret(Client client, int paymentGatewayId) {
        GenerateSharedSecretPaymentGatewayProfileBuilder generateSharedSecretPaymentGatewayProfileBuilder =
                PaymentGatewayProfileService.generateSharedSecret(paymentGatewayId)
                        .setCompletion((ApiCompletion<PaymentGatewayProfile>) result -> {
                            paymentGatewayProfileResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretPaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayProfileResponse;
    }

    // getConfiguration
    public static Response<PaymentGatewayConfiguration> getConfiguration(Client client, String alias, String intent, List<KeyValue> extraParams) {
        GetConfigurationPaymentGatewayProfileBuilder getConfigurationPaymentGatewayProfileBuilder =
                PaymentGatewayProfileService.getConfiguration(alias, intent, extraParams)
                        .setCompletion((ApiCompletion<PaymentGatewayConfiguration>) result -> {
                            paymentGatewayConfigurationResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationPaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayConfigurationResponse;
    }

    // list
    public static Response<ListResponse<PaymentGatewayProfile>> list(Client client) {
        ListPaymentGatewayProfileBuilder listPaymentGatewayProfileBuilder = PaymentGatewayProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<PaymentGatewayProfile>>) result -> {
                    paymentGatewayProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listPaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayProfileListResponse;
    }

    // update
    public static Response<PaymentGatewayProfile> update(Client client, int paymentGatewayId, PaymentGatewayProfile paymentGatewayProfile) {
        UpdatePaymentGatewayProfileBuilder updatePaymentGatewayProfileBuilder = PaymentGatewayProfileService
                .update(paymentGatewayId, paymentGatewayProfile).setCompletion((ApiCompletion<PaymentGatewayProfile>) result -> {
                    paymentGatewayProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePaymentGatewayProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayProfileResponse;
    }
}
