package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.HouseholdPaymentGatewayService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.HouseholdPaymentGateway;
import com.kaltura.client.types.KeyValue;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PaymentGatewayConfiguration;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdPaymentGatewayService.*;
import static org.awaitility.Awaitility.await;

public class HouseholdPaymentGatewayServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;
    private static Response<String> stringResponse;
    private static Response<PaymentGatewayConfiguration> paymentGatewayConfigurationResponse;
    private static Response<ListResponse<HouseholdPaymentGateway>> householdPaymentGatewayListResponse;

    // setChargeId
    public static Response<Boolean> setChargeId(Client client, String paymentGatewayExternalId, String chargeId) {
        SetChargeIDHouseholdPaymentGatewayBuilder setChargeIDHouseholdPaymentGatewayBuilder =
                setChargeID(paymentGatewayExternalId, chargeId)
                        .setCompletion((ApiCompletion<Boolean>) result -> {
                            booleanResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(setChargeIDHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // disable
    public static Response<Boolean> disable(Client client, int paymentGateway) {
        DisableHouseholdPaymentGatewayBuilder disableHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.disable(paymentGateway)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(disableHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // enable
    public static Response<Boolean> enable(Client client, int paymentGateway) {
        EnableHouseholdPaymentGatewayBuilder enableHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.enable(paymentGateway)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(enableHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // getChargeID
    public static Response<String> getChargeID(Client client, String paymentGatewayExternalId) {
        GetChargeIDHouseholdPaymentGatewayBuilder getChargeIDHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService
                .getChargeID(paymentGatewayExternalId).setCompletion((ApiCompletion<String>) result -> {
                    stringResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getChargeIDHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return stringResponse;
    }

    // invoke
    public static Response<PaymentGatewayConfiguration> invoke(Client client, int paymentGateway, String intent, List<KeyValue> extraParams) {
        InvokeHouseholdPaymentGatewayBuilder invokeHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService
                .invoke(paymentGateway, intent, extraParams).setCompletion((ApiCompletion<PaymentGatewayConfiguration>) result -> {
                    paymentGatewayConfigurationResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(invokeHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return paymentGatewayConfigurationResponse;
    }

    // list
    public static Response<ListResponse<HouseholdPaymentGateway>> list(Client client) {
        ListHouseholdPaymentGatewayBuilder listHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.list()
                .setCompletion((ApiCompletion<ListResponse<HouseholdPaymentGateway>>) result -> {
                    householdPaymentGatewayListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdPaymentGatewayListResponse;
    }

    // resume
    public static void resume(Client client, int paymentGateway) {
        ResumeHouseholdPaymentGatewayBuilder resumeHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.resume(paymentGateway);
        resumeHouseholdPaymentGatewayBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(resumeHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }

    // suspend
    public static void suspend(Client client, int paymentGateway) {
        SuspendHouseholdPaymentGatewayBuilder suspendHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.suspend(paymentGateway);
        suspendHouseholdPaymentGatewayBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(suspendHouseholdPaymentGatewayBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }
}
