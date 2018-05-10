package com.kaltura.client.test.utils;

import com.kaltura.client.services.AssetRuleService;
import com.kaltura.client.types.AssetRule;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;

import static com.kaltura.client.services.AssetRuleService.*;
import static com.kaltura.client.test.tests.BaseTest.executor;

public class assetRulesUtils extends BaseUtils {





    public void deleteAllGeoAssetRules() {


        ListAssetRuleBuilder listAssetRuleBuilder = AssetRuleService.list();
        Response<ListResponse<AssetRule>> d = executor.executeSync(listAssetRuleBuilder);

        for (int i = 0; i < listAssetRuleBuilder) {

        }
        AddAssetRuleBuilder addAssetRuleBuilder = AssetRuleService.delete();
    }



}
