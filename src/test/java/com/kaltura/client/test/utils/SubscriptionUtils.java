package com.kaltura.client.test.utils;

import com.google.common.base.Verify;
import com.kaltura.client.enums.BundleType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.SubscriptionService;
import com.kaltura.client.services.SubscriptionService.ListSubscriptionBuilder;
import com.kaltura.client.test.tests.enums.ChannelType;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.tests.BaseTest.executor;
import static com.kaltura.client.test.tests.BaseTest.getOperatorKs;

public class SubscriptionUtils extends BaseUtils {


    public static List<Asset> getAssetsListBySubscription(int subscriptionId, Optional<Integer> numOfPages, boolean isListCanBeEmpty) {
        Response<ListResponse<Asset>> assetListResponse;

        // set filter
        BundleFilter filter = new BundleFilter();
        filter.setBundleTypeEqual(BundleType.SUBSCRIPTION);
        filter.setIdEqual(subscriptionId);

        if (numOfPages.isPresent()) {
            FilterPager pager = new FilterPager();
            pager.setPageSize(numOfPages.get());
            pager.setPageIndex(1);

            assetListResponse = executor.executeSync(AssetService.list(filter, pager).setKs(getOperatorKs()));
        } else {
            assetListResponse = executor.executeSync(AssetService.list(filter).setKs(getOperatorKs()));
        }

        List<Asset> assets = assetListResponse.results.getObjects();
        // Asset list can be empty in case creation of new MPP
        if (!isListCanBeEmpty) {
            Verify.verify(assetListResponse.results.getTotalCount() > 0, "Asset list can't be empty");
            // remove assets without media files from list
            List<Asset> assetsToRemove = new ArrayList<>();

            for (Asset asset : assets) {
                if (asset.getMediaFiles().size() < 1) {
                    assetsToRemove.add(asset);
                }
            }

            assets.removeAll(assetsToRemove);
        }
        return assets;
    }

    public static Channel loadAutomaticOrKsqlChannel(String subscriptionId) {
        List<BaseChannel> channels = getChannelsListBySubscription(subscriptionId);
        String channelType;
        Channel result;
        for (BaseChannel channel: channels) {
            result = IngestFixtureData.getChannel(channel.getId().intValue());
            channelType = result.toParams().get("channel_type").toString();
            if (ChannelType.AUTOMATIC_CHANNEL_TYPE.getValue().equals(channelType) ||
                    ChannelType.KSQL_CHANNEL_TYPE.getValue().equals(channelType)) {
                return result;
            }
        }
        return null;
    }

    public static List<BaseChannel> getChannelsListBySubscription(String subscriptionId) {
        SubscriptionFilter filter = new SubscriptionFilter();
        filter.setSubscriptionIdIn(subscriptionId);
        ListSubscriptionBuilder listSubscriptionBuilder = SubscriptionService.list(filter);
        Response<ListResponse<Subscription>> listResponse = executor.executeSync(listSubscriptionBuilder.setKs(getOperatorKs()));
        Verify.verify(listResponse.results.getObjects().get(0).getChannels().size() > 0);
        return listResponse.results.getObjects().get(0).getChannels();
    }
}
