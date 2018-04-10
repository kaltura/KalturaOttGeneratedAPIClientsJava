package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EntitlementService.*;
import static com.kaltura.client.services.EntitlementService.ListEntitlementBuilder;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class EntitlementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Entitlement>> entitlementListResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<EntitlementRenewal> entitlementRenewalResponse;
    private static Response<Entitlement> entitlementResponse;

    // list
    public static Response<ListResponse<Entitlement>> list(String ks, EntitlementFilter filter, @Nullable FilterPager pager) {
        ListEntitlementBuilder productPriceBuilder = EntitlementService.list(filter, pager)
                .setCompletion((ApiCompletion<ListResponse<Entitlement>>) result -> {
                    entitlementListResponse = result;
                    done.set(true);
                });

        productPriceBuilder.setKs(ks);

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementListResponse;
    }

    // cancel
    public static Response<Boolean> cancel(String ks, int assetId, TransactionType transactionType) {
        CancelEntitlementBuilder cancelEntitlementBuilder = EntitlementService.cancel(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        cancelEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(cancelEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // cancelScheduledSubscription
    public static Response<Boolean> cancelScheduledSubscription(String ks, long scheduledSubscriptionId) {
        CancelScheduledSubscriptionEntitlementBuilder cancelScheduledSubscriptionEntitlementBuilder =
                EntitlementService.cancelScheduledSubscription(scheduledSubscriptionId)
                        .setCompletion((ApiCompletion<Boolean>) result -> {
                            booleanResponse = result;
                            done.set(true);
                        });

        cancelScheduledSubscriptionEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(cancelScheduledSubscriptionEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // externalReconcile
    public static Response<Boolean> externalReconcile(String ks) {
        ExternalReconcileEntitlementBuilder externalReconcileEntitlementBuilder =
                EntitlementService.externalReconcile()
                        .setCompletion((ApiCompletion<Boolean>) result -> {
                            booleanResponse = result;
                            done.set(true);
                        });

        externalReconcileEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(externalReconcileEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // forceCancel
    public static Response<Boolean> forceCancel(String ks, int assetId, TransactionType transactionType) {
        ForceCancelEntitlementBuilder forceCancelEntitlementBuilder = EntitlementService.forceCancel(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        forceCancelEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(forceCancelEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // getNextRenewal
    public static Response<EntitlementRenewal> getNextRenewal(String ks, int id) {
        GetNextRenewalEntitlementBuilder getNextRenewalEntitlementBuilder = EntitlementService.getNextRenewal(id)
                .setCompletion((ApiCompletion<EntitlementRenewal>) result -> {
                    entitlementRenewalResponse = result;
                    done.set(true);
                });

        getNextRenewalEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(getNextRenewalEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementRenewalResponse;
    }

    // grant
    public static Response<Boolean> grant(String ks, int productId, TransactionType transactionType, boolean history, @Nullable int contentId) {
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(productId, transactionType, history, contentId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        grantEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(grantEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // swap
    public static Response<Boolean> swap(String ks, int currentProductId, int newProductId, boolean history) {
        SwapEntitlementBuilder swapEntitlementBuilder = EntitlementService.swap(currentProductId, newProductId, history)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        swapEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(swapEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // update
    public static Response<Entitlement> update(String ks, int id, Entitlement entitlement) {
        UpdateEntitlementBuilder updateEntitlementBuilder = EntitlementService.update(id, entitlement)
                .setCompletion((ApiCompletion<Entitlement>) result -> {
                    entitlementResponse = result;
                    done.set(true);
                });

        updateEntitlementBuilder.setKs(ks);
        TestAPIOkRequestsExecutor.getExecutor().queue(updateEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementResponse;
    }

    // cancelRenewal
    // TODO: 4/10/2018 implement cancelRenewal function and check why it returns Response<Void>
}


