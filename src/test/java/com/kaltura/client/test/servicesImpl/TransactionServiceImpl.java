package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.TransactionService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.TransactionService.*;
import static com.kaltura.client.services.TransactionService.PurchaseTransactionBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class TransactionServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Transaction> transactionResponse;
    private static Response<Long> longResponse;
    private static Response<Boolean> booleanResponse;

    // purchase
    public static Response<Transaction> purchase(String ks, Purchase purchase) {
        PurchaseTransactionBuilder purchaseBuilder = TransactionService.purchase(purchase)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        purchaseBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(purchaseBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // getPurchaseSessionId
    public static Response<Long> getPurchaseSessionId(String ks, PurchaseSession purchaseSession) {
        GetPurchaseSessionIdTransactionBuilder getPurchaseSessionIdTransactionBuilder =
                TransactionService.getPurchaseSessionId(purchaseSession)
                .setCompletion((ApiCompletion<Long>) result -> {
                    longResponse = result;
                    done.set(true);
                });

        getPurchaseSessionIdTransactionBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(getPurchaseSessionIdTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return longResponse;
    }

    // setWaiver
    public static Response<Boolean> setWaiver(String ks, int assetId, TransactionType transactionType) {
        SetWaiverTransactionBuilder setWaiverTransactionBuilder = TransactionService.setWaiver(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        setWaiverTransactionBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(setWaiverTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // updateStatus
    // TODO: 4/10/2018 check why updateStatus returns Response<Void>
    public static void updateStatus(String ks, String paymentGatewayId, String externalTransactionId, String signature, TransactionStatus transactionStatus) {
        UpdateStatusTransactionBuilder updateStatusTransactionBuilder =
                TransactionService.updateStatus(paymentGatewayId, externalTransactionId, signature, transactionStatus);

        updateStatusTransactionBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusTransactionBuilder.build(client));
    }

    // upgrade
    public static Response<Transaction> upgrade(String ks, Purchase purchase) {
        UpgradeTransactionBuilder upgradeTransactionBuilder = TransactionService.upgrade(purchase)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        upgradeTransactionBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(upgradeTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // validateReceipt
    public static Response<Transaction> validateReceipt(String ks, ExternalReceipt externalReceipt) {
        ValidateReceiptTransactionBuilder validateReceiptTransactionBuilder = TransactionService.validateReceipt(externalReceipt)
                .setCompletion((ApiCompletion<Transaction>) result -> {
                    transactionResponse = result;
                    done.set(true);
                });

        validateReceiptTransactionBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(validateReceiptTransactionBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return transactionResponse;
    }

    // downgrade
    // TODO: 4/10/2018 implement downgrade function and check why it returns Response<Void>
}


