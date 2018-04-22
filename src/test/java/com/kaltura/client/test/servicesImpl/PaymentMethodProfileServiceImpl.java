package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PaymentMethodProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PaymentMethodProfileService.*;
import static org.awaitility.Awaitility.await;

public class PaymentMethodProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<PaymentMethodProfile> paymentMethodProfileResponse;
    private static Response<PaymentGatewayConfiguration> paymentGatewayConfigurationResponse;
    private static Response<ListResponse<PaymentMethodProfile>> paymentMethodProfileListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<PaymentMethodProfile> add(Client client, PaymentMethodProfile paymentMethodProfile) {
        AddPaymentMethodProfileBuilder addPaymentMethodProfileBuilder = PaymentMethodProfileService.add(paymentMethodProfile)
                .setCompletion((ApiCompletion<PaymentMethodProfile>) result -> {
                    paymentMethodProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addPaymentMethodProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentMethodProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int paymentMethodId) {
        DeletePaymentMethodProfileBuilder deletePaymentMethodProfileBuilder = PaymentMethodProfileService.delete(paymentMethodId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deletePaymentMethodProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<PaymentMethodProfile>> list(Client client, PaymentMethodProfileFilter paymentMethodProfileFilter) {
        ListPaymentMethodProfileBuilder listPaymentMethodProfileBuilder = PaymentMethodProfileService.list(paymentMethodProfileFilter)
                .setCompletion((ApiCompletion<ListResponse<PaymentMethodProfile>>) result -> {
                    paymentMethodProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listPaymentMethodProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentMethodProfileListResponse;
    }

    // update
    public static Response<PaymentMethodProfile> update(Client client, int paymentMethodId, PaymentMethodProfile paymentMethodProfile) {
        UpdatePaymentMethodProfileBuilder updatePaymentMethodProfileBuilder = PaymentMethodProfileService.update(paymentMethodId, paymentMethodProfile)
                .setCompletion((ApiCompletion<PaymentMethodProfile>) result -> {
                    paymentMethodProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePaymentMethodProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentMethodProfileResponse;
    }
}
