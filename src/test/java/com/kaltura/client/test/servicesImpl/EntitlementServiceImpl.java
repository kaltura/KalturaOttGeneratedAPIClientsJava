package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.EntitlementService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.EntitlementService.*;
import static org.awaitility.Awaitility.await;

public class EntitlementServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Entitlement>> entitlementListResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<EntitlementRenewal> entitlementRenewalResponse;
    private static Response<Entitlement> entitlementResponse;

    // list
    public static Response<ListResponse<Entitlement>> list(Client client, EntitlementFilter filter, @Nullable FilterPager pager) {
        ListEntitlementBuilder productPriceBuilder = EntitlementService.list(filter, pager)
                .setCompletion((ApiCompletion<ListResponse<Entitlement>>) result -> {
                    entitlementListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(productPriceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementListResponse;
    }

    // cancel
    public static Response<Boolean> cancel(Client client, int assetId, TransactionType transactionType) {
        CancelEntitlementBuilder cancelEntitlementBuilder = EntitlementService.cancel(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // cancelScheduledSubscription
    public static Response<Boolean> cancelScheduledSubscription(Client client, long scheduledSubscriptionId) {
        CancelScheduledSubscriptionEntitlementBuilder cancelScheduledSubscriptionEntitlementBuilder =
                EntitlementService.cancelScheduledSubscription(scheduledSubscriptionId)
                        .setCompletion((ApiCompletion<Boolean>) result -> {
                            booleanResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(cancelScheduledSubscriptionEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // externalReconcile
    public static Response<Boolean> externalReconcile(Client client) {
        ExternalReconcileEntitlementBuilder externalReconcileEntitlementBuilder =
                EntitlementService.externalReconcile()
                        .setCompletion((ApiCompletion<Boolean>) result -> {
                            booleanResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(externalReconcileEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // forceCancel
    public static Response<Boolean> forceCancel(Client client, int assetId, TransactionType transactionType) {
        ForceCancelEntitlementBuilder forceCancelEntitlementBuilder = EntitlementService.forceCancel(assetId, transactionType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(forceCancelEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // getNextRenewal
    public static Response<EntitlementRenewal> getNextRenewal(Client client, int id) {
        GetNextRenewalEntitlementBuilder getNextRenewalEntitlementBuilder = EntitlementService.getNextRenewal(id)
                .setCompletion((ApiCompletion<EntitlementRenewal>) result -> {
                    entitlementRenewalResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getNextRenewalEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementRenewalResponse;
    }

    // grant
    public static Response<Boolean> grant(Client client, int productId, TransactionType transactionType, boolean history, @Nullable int contentId) {
        GrantEntitlementBuilder grantEntitlementBuilder = EntitlementService.grant(productId, transactionType, history, contentId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(grantEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // swap
    public static Response<Boolean> swap(Client client, int currentProductId, int newProductId, boolean history) {
        SwapEntitlementBuilder swapEntitlementBuilder = EntitlementService.swap(currentProductId, newProductId, history)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(swapEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // update
    public static Response<Entitlement> update(Client client, int id, Entitlement entitlement) {
        UpdateEntitlementBuilder updateEntitlementBuilder = EntitlementService.update(id, entitlement)
                .setCompletion((ApiCompletion<Entitlement>) result -> {
                    entitlementResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateEntitlementBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return entitlementResponse;
    }

    // cancelRenewal
    // TODO: 4/10/2018 implement cancelRenewal function and check why it returns Response<Void>
}


