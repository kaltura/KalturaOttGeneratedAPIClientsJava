package com.kaltura.client.test.tests.servicesTests.assetStatisticsTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.services.AssetStatisticsService.QueryAssetStatisticsBuilder;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.types.AssetStatistics;
import com.kaltura.client.types.AssetStatisticsQuery;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.MediaAsset;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.services.AssetStatisticsService.query;

public class AssetStatisticsQueryTests extends BaseTest{

    private MediaAsset media1;
    private MediaAsset media2;
    private MediaAsset media3;

    @BeforeClass(enabled = false)
    private void assetStatistics_before_class(){
        List<MediaAsset> medias = AssetUtils.getAssets(3, MediaType.MOVIE);
        media1 = medias.get(0);
        media2 = medias.get(1);
        media3 = medias.get(2);
    }

    @Test(enabled = false)
    public void asset(){

        AssetStatisticsQuery assetStatisticsQuery = new AssetStatisticsQuery();
        assetStatisticsQuery.setAssetIdIn(String.valueOf(media1.getId()));
        assetStatisticsQuery.setAssetTypeEqual(AssetType.MEDIA);

        QueryAssetStatisticsBuilder queryAssetStatisticsBuilder = query(assetStatisticsQuery);
        Response<ListResponse<AssetStatistics>> assetStatistics = executor.executeSync(queryAssetStatisticsBuilder);


    }


}
