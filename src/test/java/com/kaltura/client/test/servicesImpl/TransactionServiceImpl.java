package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.TransactionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.TransactionService.*;
import static org.awaitility.Awaitility.await;

public class TransactionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Transaction> transactionResponse;
    private static Response<Long> longResponse;
    private static Response<Boolean> booleanResponse;

    // purchase
    public static Response<Transaction> purchase(Client client, Purchase purchase) {
        PurchaseTransactionBuilder purchaseBuilder = TransactionService.purchase(purchase)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(purchaseBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // getPurchaseSessionId
    public static Response<Long> getPurchaseSessionId(Client client, PurchaseSession purchaseSession) {
        GetPurchaseSessionIdTransactionBuilder getPurchaseSessionIdTransactionBuilder =
                TransactionService.getPurchaseSessionId(purchaseSession)
                .setCompletion((ApiCompletion<Long>) result -> {
                    longResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getPurchaseSessionIdTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return longResponse;
    }

    // setWaiver
    public static Response<Boolean> setWaiver(Client client, int assetId, TransactionType transactionType) {
        SetWaiverTransactionBuilder setWaiverTransactionBuilder = TransactionService.setWaiver(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(setWaiverTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // updateStatus
    public static void updateStatus(Client client, String paymentGatewayId, String externalTransactionId, String signature, TransactionStatus transactionStatus) {
        UpdateStatusTransactionBuilder updateStatusTransactionBuilder = TransactionService.updateStatus(paymentGatewayId, externalTransactionId, signature, transactionStatus);
        updateStatusTransactionBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }

    // upgrade
    public static Response<Transaction> upgrade(Client client, Purchase purchase) {
        UpgradeTransactionBuilder upgradeTransactionBuilder = TransactionService.upgrade(purchase)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(upgradeTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // validateReceipt
    public static Response<Transaction> validateReceipt(Client client, ExternalReceipt externalReceipt) {
        ValidateReceiptTransactionBuilder validateReceiptTransactionBuilder = TransactionService.validateReceipt(externalReceipt)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(validateReceiptTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // downgrade
    public static void downgrade(Client client, Purchase purchase) {
        DowngradeTransactionBuilder downgradeTransactionBuilder = TransactionService.downgrade(purchase);
        downgradeTransactionBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(downgradeTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }
}


