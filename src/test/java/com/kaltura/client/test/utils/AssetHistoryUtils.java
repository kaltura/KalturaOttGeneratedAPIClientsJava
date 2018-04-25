package com.kaltura.client.test.utils;

import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.types.AssetHistoryFilter;

import javax.annotation.Nullable;

public class AssetHistoryUtils extends BaseUtils {

    public static AssetHistoryFilter getAssetHistoryFilter(@Nullable String assetIdIn, @Nullable Integer days, WatchStatus statusEqual, @Nullable String typeIn) {
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setAssetIdIn(assetIdIn);
        assetHistoryFilter.setDaysLessThanOrEqual(days);
        assetHistoryFilter.setStatusEqual(statusEqual);
        assetHistoryFilter.setTypeIn(typeIn);

        return assetHistoryFilter;

    }
}
