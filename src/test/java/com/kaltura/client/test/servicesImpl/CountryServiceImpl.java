package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CountryService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Country;
import com.kaltura.client.types.CountryFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CountryService.*;
import static org.awaitility.Awaitility.await;

public class CountryServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Country>> countryListResponse;

    // list
    public static Response<ListResponse<Country>> list(Client client, CountryFilter countryFilter) {
        ListCountryBuilder listCountryBuilder = CountryService.list(countryFilter)
                .setCompletion((ApiCompletion<ListResponse<Country>>) result -> {
                    countryListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listCountryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return countryListResponse;
    }
}
