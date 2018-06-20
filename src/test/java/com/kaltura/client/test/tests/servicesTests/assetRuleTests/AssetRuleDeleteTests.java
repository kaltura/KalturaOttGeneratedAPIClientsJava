package com.kaltura.client.test.tests.servicesTests.assetRuleTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetRuleUtils;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class AssetRuleDeleteTests extends BaseTest {


    @Description("/assetrule/action/delete - currently used to run util only")
    @Test(enabled = false) // as test is not implemented yet
    private void deleteAllGeoAssetRules() {
        AssetRuleUtils.deleteAllGeoAssetRules();
    }

}
