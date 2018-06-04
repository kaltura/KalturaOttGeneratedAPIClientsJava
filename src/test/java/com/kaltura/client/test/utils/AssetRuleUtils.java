package com.kaltura.client.test.utils;

import com.kaltura.client.services.AssetRuleService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.AssetRule;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;

import static com.kaltura.client.services.AssetRuleService.*;
import static com.kaltura.client.test.tests.BaseTest.executor;

public class AssetRuleUtils extends BaseUtils {


    // Delete all geo asset rules from DB (changed to status 2)
    public static void deleteAllGeoAssetRules() {
        ListAssetRuleBuilder listAssetRuleBuilder = AssetRuleService.list().setKs(BaseTest.getOperatorKs());
        Response<ListResponse<AssetRule>> listAssetRule = executor.executeSync(listAssetRuleBuilder);
        if (listAssetRule.results.getTotalCount() > 0) {
            for (AssetRule rule : listAssetRule.results.getObjects()) {
                DeleteAssetRuleBuilder deleteAssetRuleBuilder = AssetRuleService.delete(rule.getId()).setKs(BaseTest.getOperatorKs());
                executor.executeSync(deleteAssetRuleBuilder);
            }
        }

    }
}
