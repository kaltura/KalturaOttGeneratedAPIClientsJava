package com.kaltura.client.test.tests.servicesTests.AssetHistoryTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.test.servicesImpl.AssetHistoryServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.*;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.test.IngestConstants.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.Properties.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {
    private Client client;
    private AssetType assetType = AssetType.MEDIA;
    private int position1 = 10;
    private int position2 = 20;

    @BeforeClass
    private void add_tests_before_class() {

    }

    @Description("/AssetHistory/action/list - with no filter")
    @Test
    private void vodAssetHistory() {

        Household household = HouseholdUtils.createHouseHold(1, 1, false);
        client = getClient(HouseholdUtils.getHouseholdMasterUserKs(household,null));

        // Ingest and bookmark first asset
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, position2, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);
        //assetHistory/action/list - both assets should returned
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);

        // First object
        AssetHistory assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
        // Second object
        AssetHistory assetHistoryObject2 = assetHistoryListResponse.results.getObjects().get(1);

        // Assertions for first object returned
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(assetId2);
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(assetType);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position2);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        // Verify that flag is set to false (user hasn't finish watching the asset)
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(BaseUtils.getTimeInEpoch(0));

        // Assertions for second object returned
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(assetId1);
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(assetType);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(position1);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        AssetHistoryServiceImpl.clean(client, assetHistoryFilter);
    }

    @Description("/AssetHistory/action/list -filtered by movie asset id")
    @Test
    private void vodAssetHistoryFilteredByAssetId() {

        Household household = HouseholdUtils.createHouseHold(1, 1, false);
        client = getClient(HouseholdUtils.getHouseholdMasterUserKs(household,null));

        // Ingest and bookmark first asset
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        Long assetId3 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(assetId2), null, WatchStatus.ALL, null);
        //assetHistory/action/list - filter by asset 2 id
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId2);

        String concatenatedString = BaseUtils.getConcatenatedString(String.valueOf(assetId2), String.valueOf(assetId3));

        //assetHistory/action/list - filter by asset 2 and asset 3 ids
        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(concatenatedString,
                null, WatchStatus.ALL, null);
        assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId3);
        assertThat(assetHistoryListResponse.results.getObjects().get(1).getAssetId()).isEqualTo(assetId2);

        AssetHistoryServiceImpl.clean(client, assetHistoryFilter);
    }

    @Description("/AssetHistory/action/list -filtered by movie type id")
    @Test
    private void vodAssetHistoryFilteredByAssetType() {

        Household household = HouseholdUtils.createHouseHold(1, 1, false);
        client = getClient(HouseholdUtils.getHouseholdMasterUserKs(household,null));

        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, EPISODE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, getProperty(MOVIE_MEDIA_TYPE_ID));
        //assetHistory/action/list - filter by in progress assets only
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId1);

        AssetHistoryServiceImpl.clean(client, assetHistoryFilter);
    }


    @Description("/AssetHistory/action/list -filtered by assets progress")
    @Test
    private void vodAssetHistoryFilteredByAssetProgress() {

        Household household = HouseholdUtils.createHouseHold(1, 1, false);
        client = getClient(HouseholdUtils.getHouseholdMasterUserKs(household,null));

        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, MOVIE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(client, EPISODE_MEDIA_TYPE, 100, BookmarkActionType.FINISH);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.PROGRESS, null);

        //assetHistory/action/list - filter by in progress assets only
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId1);

        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.DONE, null);
        //assetHistory/action/list - filter by finished assets only
        assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId2);

        AssetHistoryServiceImpl.clean(client, assetHistoryFilter);
    }

}
