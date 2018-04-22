package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.PinType;
import com.kaltura.client.services.PinService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Pin;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PinService.*;
import static org.awaitility.Awaitility.await;

public class PinServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Pin> pinResponse;
    private static Response<Boolean> booleanResponse;

    // get
    public static Response<Pin> get(Client client, EntityReferenceBy entityReferenceBy, PinType pinType) {
        GetPinBuilder getPinBuilder = PinService.get(entityReferenceBy, pinType)
                .setCompletion((ApiCompletion<Pin>) result -> {
                    pinResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getPinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return pinResponse;
    }

    // update
    public static Response<Pin> update(Client client, EntityReferenceBy entityReferenceBy, PinType pinType, Pin pin, @Nullable int ruleId) {
        UpdatePinBuilder updatePinBuilder = PinService.update(entityReferenceBy, pinType, pin, ruleId)
                .setCompletion((ApiCompletion<Pin>) result -> {
                    pinResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return pinResponse;
    }

    // validate
    public static Response<Boolean> validate(Client client, String pin, PinType pinType, int ruleId) {
        ValidatePinBuilder validatePinBuilder = PinService.validate(pin, pinType, ruleId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(validatePinBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
