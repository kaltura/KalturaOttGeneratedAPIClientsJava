package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.UserAssetsListItemType;
import com.kaltura.client.enums.UserAssetsListType;
import com.kaltura.client.services.UserAssetsListItemService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.UserAssetsListItem;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserAssetsListItemService.*;
import static org.awaitility.Awaitility.await;

public class UserAssetsListItemServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<UserAssetsListItem> userAssetsListItemResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<UserAssetsListItem> add(Client client, UserAssetsListItem userAssetsListItem) {
        AddUserAssetsListItemBuilder addUserAssetsListItemBuilder = UserAssetsListItemService.add(userAssetsListItem)
                .setCompletion((ApiCompletion<UserAssetsListItem>) result -> {
                    userAssetsListItemResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addUserAssetsListItemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userAssetsListItemResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String assetId, UserAssetsListType userAssetsListType) {
        DeleteUserAssetsListItemBuilder deleteUserAssetsListItemBuilder = UserAssetsListItemService.delete(assetId, userAssetsListType)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteUserAssetsListItemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<UserAssetsListItem> get(Client client, String assetId, UserAssetsListType userAssetsListType,
                                                   UserAssetsListItemType userAssetsListItemType) {
        GetUserAssetsListItemBuilder getUserAssetsListItemBuilder = UserAssetsListItemService.get(assetId, userAssetsListType, userAssetsListItemType)
                .setCompletion((ApiCompletion<UserAssetsListItem>) result -> {
                    userAssetsListItemResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getUserAssetsListItemBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userAssetsListItemResponse;
    }
}
