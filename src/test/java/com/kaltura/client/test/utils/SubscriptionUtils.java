package com.kaltura.client.test.utils;

import com.kaltura.client.enums.BundleType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.BundleFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.tests.BaseTest.getOperatorKs;

public class SubscriptionUtils extends BaseUtils {


    public static List<Asset> getAssetsListBySubscription(int subscriptionId, Optional<Integer> numOfPages) {
        Response<ListResponse<Asset>> assetListResponse;

        // set filter
        BundleFilter filter = new BundleFilter();
        filter.setBundleTypeEqual(BundleType.SUBSCRIPTION);
        filter.setIdEqual(subscriptionId);

        if (numOfPages.isPresent()) {
            FilterPager pager = new FilterPager();
            pager.setPageSize(numOfPages.get());
            pager.setPageIndex(1);

            assetListResponse = BaseTest.executor.executeSync(AssetService.list(filter, pager).setKs(getOperatorKs()));
        } else {
            assetListResponse = BaseTest.executor.executeSync(AssetService.list(filter).setKs(getOperatorKs()));
        }

        // remove assets without media files from list
        List<Asset> assets = assetListResponse.results.getObjects();
        List<Asset> assetsToRemove = new ArrayList<>();

        for (Asset asset : assets) {
            if (asset.getMediaFiles().size() < 1) {
                assetsToRemove.add(asset);
            }
        }

        assets.removeAll(assetsToRemove);

        return assets;
    }
}
