package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.*;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.BookmarkService;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.services.SocialActionService;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kaltura.client.services.AssetService.*;
import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.SocialActionService.AddSocialActionBuilder;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.getSharedMasterUserKs;
import static com.kaltura.client.test.tests.BaseTest.executor;
import static com.kaltura.client.test.tests.BaseTest.getOperatorKs;
import static com.kaltura.client.test.tests.enums.KsqlKey.ASSET_TYPE;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class AssetUtils extends BaseUtils {

    // TODO: 6/21/2018 Use Optional instead of nullable
    public static SearchAssetFilter getSearchAssetFilter(@Nullable String ksql, @Nullable String typeIn,
                                                         @Nullable DynamicOrderBy dynamicOrderBy, List<AssetGroupBy> groupBy, String name, String orderBy) {
        SearchAssetFilter searchAssetFilter = new SearchAssetFilter();
        searchAssetFilter.setKSql(ksql);
        searchAssetFilter.setTypeIn(typeIn);
        searchAssetFilter.setDynamicOrderBy(dynamicOrderBy);
        searchAssetFilter.setGroupBy(groupBy);
        searchAssetFilter.setName(name);
        searchAssetFilter.setOrderBy(orderBy);

        return searchAssetFilter;
    }

    public static SearchAssetFilter getSearchAssetFilter(String ksql) {
        SearchAssetFilter searchAssetFilter = new SearchAssetFilter();
        searchAssetFilter.setKSql(ksql);

        return searchAssetFilter;
    }

    public static ChannelFilter getChannelFilter(int idEqual, Optional<String> ksql, Optional<DynamicOrderBy> dynamicOrderBy, Optional<String> orderBy) {
        ChannelFilter channelFilter = new ChannelFilter();
        channelFilter.setIdEqual(idEqual);

        ksql.ifPresent(channelFilter::setKSql);
        dynamicOrderBy.ifPresent(channelFilter::setDynamicOrderBy);
        orderBy.ifPresent(channelFilter::setOrderBy);

        return channelFilter;
    }

    public static List<Integer> getAssetFileIds(String assetId) {
        GetAssetBuilder getAssetBuilder = get(assetId, AssetReferenceType.MEDIA)
                .setKs(getSharedMasterUserKs());
        Response<Asset> assetResponse = executor.executeSync(getAssetBuilder);

        List<MediaFile> mediaFiles = assetResponse.results.getMediaFiles();
        assertThat(mediaFiles.size()).as("media files list").isGreaterThan(0);

        return mediaFiles.stream().map(MediaFile::getId).collect(Collectors.toList());
    }

    public static List<Asset> getAssetsByType(String typeIn) {
        AssetFilter assetFilter = getSearchAssetFilter(null, typeIn, null, null, null, null);

        FilterPager filterPager = new FilterPager();
        filterPager.setPageSize(20);
        filterPager.setPageIndex(1);

        ListAssetBuilder listAssetBuilder = list(assetFilter, filterPager)
                .setKs(getSharedMasterUserKs());
        Response<ListResponse<Asset>> assetResponse = executor.executeSync(listAssetBuilder);

        return assetResponse.results.getObjects();
    }

    // TODO - need to make util more efficient (creating too many HH)
    public static void addViewsToAsset(Long assetId, int numOfActions, AssetType assetType) {
        if (numOfActions <= 0) {
            Logger.getLogger("Value must be equal or greater than 0");
        } else {
            for (int i = 0; i < numOfActions; i++) {
                int j = 1;
                Household household = HouseholdUtils.createHousehold(j, j, false);
                HouseholdUser householdUser = HouseholdUtils.getMasterUser(household);

                Bookmark bookmark = BookmarkUtils.addBookmark(
                        0,
                        String.valueOf(assetId),
                        AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0),
                        assetType,
                        BookmarkActionType.FIRST_PLAY
                );

                AddBookmarkBuilder bookmarkBuilder = BookmarkService.add(bookmark)
                        .setKs(getOperatorKs())
                        .setUserId(Integer.valueOf(householdUser.getUserId()));
                executor.executeSync(bookmarkBuilder);

                // cleanup - delete household
                executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
            }
        }
    }

    public static void addLikesToAsset(Long assetId, int numOfActions, AssetType assetType) {
        if (numOfActions <= 0) {
            Logger.getLogger("Value must be equal or greater than 0");
        } else {
            for (int i = 0; i < numOfActions; i++) {
                int j = 1;
                Household household = HouseholdUtils.createHousehold(j, j, false);
                HouseholdUser householdUser = HouseholdUtils.getMasterUser(household);

                SocialAction socialAction = SocialUtils.getSocialAction(SocialActionType.LIKE, null, assetId, assetType, null);

                AddSocialActionBuilder addSocialActionBuilder = SocialActionService.add(socialAction)
                        .setKs(getOperatorKs())
                        .setUserId(Integer.valueOf(householdUser.getUserId()));
                executor.executeSync(addSocialActionBuilder);

                // cleanup - delete household
                executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
            }
        }
    }

    public static void addVotesToAsset(Long assetId, int numOfActions, AssetType assetType, int rate) {
        if (numOfActions <= 0) {
            Logger.getLogger("Value must be equal or greater than 0");
        } else {
            for (int i = 0; i < numOfActions; i++) {
                int j = 1;
                Household household = HouseholdUtils.createHousehold(j, j, false);
                HouseholdUser householdUser = HouseholdUtils.getMasterUser(household);

                SocialActionRate socialActionRate = SocialUtils.getSocialActionRate(SocialActionType.RATE, null, assetId, assetType, null, rate);

                AddSocialActionBuilder addSocialActionBuilder = SocialActionService.add(socialActionRate)
                        .setKs(getOperatorKs())
                        .setUserId(Integer.valueOf(householdUser.getUserId()));
                executor.executeSync(addSocialActionBuilder);

                // cleanup - delete household
                executor.executeSync(delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
            }
        }
    }

    public static List<MediaAsset> getAssets(int numOfAssets, MediaType mediaType) {
        SearchAssetFilter filter = new SearchAssetFilter();
        String query = new KsqlBuilder().equal(ASSET_TYPE.getValue(), getMediaTypeId(mediaType)).toString();
        filter.setKSql(query);

        FilterPager pager = new FilterPager();
        pager.setPageIndex(1);
        pager.setPageSize(numOfAssets);

        return (List<MediaAsset>) (List<?>) executor.executeSync(AssetService.list(filter, pager)
                .setKs(getOperatorKs())).results.getObjects();
    }

    public static List<ProgramAsset> getPrograms(int numOfAssets) {
        SearchAssetFilter filter = new SearchAssetFilter();
        filter.setTypeIn("0");

        FilterPager pager = new FilterPager();
        pager.setPageIndex(1);
        pager.setPageSize(numOfAssets);

        return (List<ProgramAsset>) (List<?>) executor.executeSync(AssetService.list(filter, pager)
                .setKs(getOperatorKs())).results.getObjects();
    }

    public static Asset getAssetByPurchaeStatus(List<Asset> assets, PurchaseStatus purchaseStatus) {
        Asset asset = null;
        ProductPriceFilter filter = new ProductPriceFilter();

        for (Asset a : assets) {
            filter.setFileIdIn(String.valueOf(a.getMediaFiles().get(0).getId()));
            ProductPrice productPrice = executor.executeSync(ProductPriceService.list(filter)
                    .setKs(getOperatorKs()))
                    .results.getObjects().get(0);

            if (productPrice.getPurchaseStatus().equals(purchaseStatus)) {
                asset = a;
                break;
            }
        }

        if (asset == null) {
            fail("No asset in the provided status in assets list");
        }

        return asset;
    }

    public static String getCoguid(Asset asset) {
        // TODO: 7/1/2018 finsih util 
        return null;
    }
}





