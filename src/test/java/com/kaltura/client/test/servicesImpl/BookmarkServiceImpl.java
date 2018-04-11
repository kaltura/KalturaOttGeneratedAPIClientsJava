package com.kaltura.client.test.servicesImpl;


import com.kaltura.client.Client;
import com.kaltura.client.services.BookmarkService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Bookmark;
import com.kaltura.client.types.BookmarkFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.BookmarkService.ListBookmarkBuilder;
import static org.awaitility.Awaitility.await;

public class BookmarkServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<Bookmark>> bookmarkListResponse;


    // Bookmark/action/add
    public static Response<Boolean> add(Client client, Bookmark bookmark) {
        AddBookmarkBuilder addBookmarkBuilder = BookmarkService.add(bookmark)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addBookmarkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // Bookmark/action/list
    public static Response<ListResponse<Bookmark>> list(Client client, BookmarkFilter bookmarkFilter) {
        ListBookmarkBuilder listBookmarkBuilder = BookmarkService.list(bookmarkFilter)
                .setCompletion((ApiCompletion<ListResponse<Bookmark>>) result -> {
                    bookmarkListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listBookmarkBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return bookmarkListResponse;
    }
}
