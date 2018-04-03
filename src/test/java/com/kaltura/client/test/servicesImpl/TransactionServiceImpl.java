package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.services.TransactionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Purchase;
import com.kaltura.client.types.Transaction;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.TransactionService.PurchaseTransactionBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class TransactionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Transaction> purchaseResponse;

    // list
    public static Response<Transaction> purchase(String ks, Purchase purchase) {
        client.setKs(ks);
        PurchaseTransactionBuilder purchaseBuilder = TransactionService.purchase(purchase)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    purchaseResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(purchaseBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return purchaseResponse;
    }
}


