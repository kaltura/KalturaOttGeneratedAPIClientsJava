package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.UserAssetRuleService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.UserAssetRule;
import com.kaltura.client.types.UserAssetRuleFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.UserAssetRuleService.*;
import static org.awaitility.Awaitility.await;

public class UserAssetRuleServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<UserAssetRule>> userAssetRuleListResponse;

    // list
    public static Response<ListResponse<UserAssetRule>> list(Client client, UserAssetRuleFilter userAssetRuleFilter) {
        ListUserAssetRuleBuilder listUserAssetRuleBuilder = UserAssetRuleService.list(userAssetRuleFilter)
                .setCompletion((ApiCompletion<ListResponse<UserAssetRule>>) result -> {
                    userAssetRuleListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listUserAssetRuleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return userAssetRuleListResponse;
    }
}
