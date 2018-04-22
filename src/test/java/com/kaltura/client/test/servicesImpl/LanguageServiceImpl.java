package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.LanguageService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Language;
import com.kaltura.client.types.LanguageFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.LanguageService.*;
import static org.awaitility.Awaitility.await;

public class LanguageServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<Language>> languageListResponse;

    // list
    public static Response<ListResponse<Language>> list(Client client, LanguageFilter languageFilter) {
        ListLanguageBuilder listLanguageBuilder = LanguageService.list(languageFilter)
                .setCompletion((ApiCompletion<ListResponse<Language>>) result -> {
                    languageListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listLanguageBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return languageListResponse;
    }
}
