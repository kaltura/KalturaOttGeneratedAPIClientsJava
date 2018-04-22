package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.SearchHistoryService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SearchHistoryService.*;
import static org.awaitility.Awaitility.await;

public class SearchHistoryServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<SearchHistory>> searchHistoryListResponse;

    // clean
    public static Response<Boolean> clean(Client client, @Nullable SearchHistoryFilter searchHistoryFilter) {
        CleanSearchHistoryBuilder cleanSearchHistoryBuilder = SearchHistoryService.clean(searchHistoryFilter)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(cleanSearchHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String id) {
        DeleteSearchHistoryBuilder deleteSearchHistoryBuilder = SearchHistoryService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteSearchHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<SearchHistory>> list(Client client, @Nullable SearchHistoryFilter searchHistoryFilter, @Nullable FilterPager filterPager) {
        ListSearchHistoryBuilder listSearchHistoryBuilder = SearchHistoryService.list(searchHistoryFilter, filterPager)
                .setCompletion((ApiCompletion<ListResponse<SearchHistory>>) result -> {
                    searchHistoryListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listSearchHistoryBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return searchHistoryListResponse;
    }
}
