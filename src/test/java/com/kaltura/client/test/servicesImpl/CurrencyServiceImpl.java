package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CurrencyService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Currency;
import com.kaltura.client.types.CurrencyFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CurrencyService.*;
import static org.awaitility.Awaitility.await;

public class CurrencyServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Currency>> currencyListResponse;

    // list
    public static Response<ListResponse<Currency>> list(Client client, CurrencyFilter currencyFilter) {
        ListCurrencyBuilder listCurrencyBuilder = CurrencyService.list(currencyFilter)
                .setCompletion((ApiCompletion<ListResponse<Currency>>) result -> {
                    currencyListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listCurrencyBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return currencyListResponse;
    }
}
