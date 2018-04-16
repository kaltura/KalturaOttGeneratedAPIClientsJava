package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.TransactionHistoryService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.BillingTransaction;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.TransactionHistoryFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.TransactionHistoryService.ListTransactionHistoryBuilder;
import static org.awaitility.Awaitility.await;

public class TransactionHistoryServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<BillingTransaction>> billingTransactionListResponse;

    // list
    public static Response<ListResponse<BillingTransaction>> list(Client client, TransactionHistoryFilter filter, FilterPager pager) {
        ListTransactionHistoryBuilder listTransactionHistoryBuilder = TransactionHistoryService.list(filter, pager)
                .setCompletion((ApiCompletion<ListResponse<BillingTransaction>>) result -> {
                    billingTransactionListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listTransactionHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return billingTransactionListResponse;
    }
}
