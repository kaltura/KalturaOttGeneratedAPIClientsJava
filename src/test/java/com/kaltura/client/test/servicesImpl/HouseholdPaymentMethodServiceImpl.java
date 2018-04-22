package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdPaymentMethodService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdPaymentMethod;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PaymentGatewayConfiguration;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdPaymentMethodService.*;
import static org.awaitility.Awaitility.await;

public class HouseholdPaymentMethodServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;
    private static Response<HouseholdPaymentMethod> householdPaymentMethodResponse;
    private static Response<PaymentGatewayConfiguration> paymentGatewayConfigurationResponse;
    private static Response<ListResponse<HouseholdPaymentMethod>> householdPaymentMethodListResponse;

    // add
    public static Response<HouseholdPaymentMethod> add(Client client, HouseholdPaymentMethod householdPaymentMethod) {
        AddHouseholdPaymentMethodBuilder addHouseholdPaymentMethodBuilder = HouseholdPaymentMethodService.add(householdPaymentMethod)
                .setCompletion((ApiCompletion<HouseholdPaymentMethod>) result -> {
                    householdPaymentMethodResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHouseholdPaymentMethodBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdPaymentMethodResponse;
    }

    // forceRemove
    public static Response<Boolean> forceRemove(Client client, int paymentGatewayId, int paymentMethodId) {
        ForceRemoveHouseholdPaymentMethodBuilder forceRemoveHouseholdPaymentMethodBuilder = HouseholdPaymentMethodService
                .forceRemove(paymentGatewayId, paymentMethodId).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(forceRemoveHouseholdPaymentMethodBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<HouseholdPaymentMethod>> list(Client client) {
        ListHouseholdPaymentMethodBuilder listHouseholdPaymentMethodBuilder = HouseholdPaymentMethodService.list()
                .setCompletion((ApiCompletion<ListResponse<HouseholdPaymentMethod>>) result -> {
                    householdPaymentMethodListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdPaymentMethodBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdPaymentMethodListResponse;
    }

    // remove
    public static Response<Boolean> remove(Client client, int paymentGatewayId, int paymentMethodId) {
        RemoveHouseholdPaymentMethodBuilder removeHouseholdPaymentMethodBuilder = HouseholdPaymentMethodService
                .remove(paymentGatewayId, paymentMethodId).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(removeHouseholdPaymentMethodBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // setAsDefault
    public static Response<Boolean> setAsDefault(Client client, int paymentGatewayId, int paymentMethodId) {
        SetAsDefaultHouseholdPaymentMethodBuilder setAsDefaultHouseholdPaymentMethodBuilder = HouseholdPaymentMethodService
                .setAsDefault(paymentGatewayId, paymentMethodId).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(setAsDefaultHouseholdPaymentMethodBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
