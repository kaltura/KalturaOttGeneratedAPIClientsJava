package com.kaltura.client.test.tests.servicesTests.AssetTests;


import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;

import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.types.Asset;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.services.AssetService.*;

public class AssetGetTests extends BaseTest {

    private Long assetId;
    private int fileId1;
    private int fileId2;


    @BeforeClass
    private void bookmark_addTests_before_class() {
        assetId = BaseTest.getSharedMediaAsset().getId();
        fileId1 = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0);
        fileId2 = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Asset/action/get - VOD")
    @Test

    private void get() {
        GetAssetBuilder getAssetBuilder = AssetService.get(String.valueOf(assetId), AssetReferenceType.MEDIA)
                .setKs(BaseTest.SharedHousehold.getSharedMasterUserKs());
        Response<Asset> assetGetResponse = executor.executeSync(getAssetBuilder);

        assertThat(assetGetResponse.results.getId()).isEqualTo(assetId);
        assertThat(assetGetResponse.results.getMediaFiles().get(0).getId()).isEqualTo(fileId1);
        assertThat(assetGetResponse.results.getMediaFiles().get(1).getId()).isEqualTo(fileId2);
    }
}
