package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.SocialActionType;
import com.kaltura.client.types.SocialAction;
import com.kaltura.client.types.SocialActionRate;

import javax.annotation.Nullable;

public class SocialUtils {
    public static SocialAction getSocialAction(@Nullable SocialActionType socialActionType, @Nullable Long time, Long assetId, @Nullable AssetType assetType,
                                               @Nullable String url) {
        SocialAction socialAction = new SocialAction();
        socialAction.setActionType(socialActionType);
        socialAction.setTime(time);
        socialAction.setAssetId(assetId);
        socialAction.setAssetType(assetType);
        socialAction.setUrl(url);

        return socialAction;
    }

    public static SocialActionRate getSocialActionRate(@Nullable SocialActionType socialActionType, @Nullable Long time, Long assetId, @Nullable AssetType assetType,
                                                       @Nullable String url, int rate) {
        SocialActionRate socialActionRate = new SocialActionRate();
        socialActionRate.setActionType(socialActionType);
        socialActionRate.setTime(time);
        socialActionRate.setAssetId(assetId);
        socialActionRate.setAssetType(assetType);
        socialActionRate.setUrl(url);
        socialActionRate.setRate(rate);

        return socialActionRate;
    }


}
