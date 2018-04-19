package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.services.PurchaseSettingsService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.PurchaseSettings;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PurchaseSettingsService.*;
import static org.awaitility.Awaitility.await;

public class PurchaseSettingsServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<PurchaseSettings> purchaseSettingsResponse;

    // get
    public static Response<PurchaseSettings> get(Client client, EntityReferenceBy entityReferenceBy) {
        GetPurchaseSettingsBuilder getPurchaseSettingsBuilder = PurchaseSettingsService.get(entityReferenceBy)
                .setCompletion((ApiCompletion<PurchaseSettings>) result -> {
                    purchaseSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getPurchaseSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return purchaseSettingsResponse;
    }

    // update
    public static Response<PurchaseSettings> update(Client client, EntityReferenceBy entityReferenceBy, PurchaseSettings purchaseSettings) {
        UpdatePurchaseSettingsBuilder updatePurchaseSettingsBuilder = PurchaseSettingsService.update(entityReferenceBy, purchaseSettings)
                .setCompletion((ApiCompletion<PurchaseSettings>) result -> {
                    purchaseSettingsResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updatePurchaseSettingsBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return purchaseSettingsResponse;
    }
}
