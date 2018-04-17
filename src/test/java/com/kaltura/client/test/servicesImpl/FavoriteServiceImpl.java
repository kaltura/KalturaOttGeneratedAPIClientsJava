package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.FavoriteService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Favorite;
import com.kaltura.client.types.FavoriteFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.FavoriteService.*;
import static org.awaitility.Awaitility.await;

public class FavoriteServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Favorite> favoriteResponse;
    private static Response<ListResponse<Favorite>> favoriteListResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<Favorite> add(Client client, Favorite favorite) {
        AddFavoriteBuilder addFavoriteBuilder = FavoriteService.add(favorite)
                .setCompletion((ApiCompletion<Favorite>) result -> {
                    favoriteResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addFavoriteBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return favoriteResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int id) {
        DeleteFavoriteBuilder deleteFavoriteBuilder = FavoriteService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteFavoriteBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<Favorite>> list(Client client, @Nullable FavoriteFilter favoriteFilter) {
        ListFavoriteBuilder listFavoriteBuilder = FavoriteService.list(favoriteFilter)
                .setCompletion((ApiCompletion<ListResponse<Favorite>>) result -> {
                    favoriteListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listFavoriteBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return favoriteListResponse;
    }
}
