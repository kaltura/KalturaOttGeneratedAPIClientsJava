package com.kaltura.client.test.tests.servicesTests.AssetHistoryTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.services.AssetHistoryService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetHistoryUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.AssetHistory;
import com.kaltura.client.types.AssetHistoryFilter;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.AssetHistoryService.CleanAssetHistoryBuilder;
import static com.kaltura.client.services.AssetHistoryService.ListAssetHistoryBuilder;
import static com.kaltura.client.test.IngestConstants.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.Properties.MOVIE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.BaseUtils.getConcatenatedString;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInEpoch;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {

    private int position1 = 10;
    private int position2 = 20;
    int numbOfDevices = 1;
    int numOfUsers = 1;

    @BeforeClass
    private void add_tests_before_class() {

    }

    @Description("/AssetHistory/action/list - with no filter")
    @Test
    private void vodAssetHistory() {

        Household household = HouseholdUtils.createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = HouseholdUtils.getHouseholdMasterUserKs(household, null);

        // Ingest and bookmark first asset
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, position2, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        //assetHistory/action/list - both assets should returned
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        listAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household,null));
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);


        // First object
        AssetHistory assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
        // Second object
        AssetHistory assetHistoryObject2 = assetHistoryListResponse.results.getObjects().get(1);

        // Assertions for first object returned
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(assetId2);
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position2);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);

        // Verify that flag is set to false (user hasn't finish watching the asset)
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(getTimeInEpoch(0));

        // Assertions for second object returned
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(assetId1);
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(position1);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household, null));
        executor.executeSync(cleanAssetHistoryBuilder);
    }

    @Description("/AssetHistory/action/list -filtered by movie asset id")
    @Test
    private void vodAssetHistoryFilteredByAssetId() {

        Household household = HouseholdUtils.createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = HouseholdUtils.getHouseholdMasterUserKs(household, null);

        // Ingest and bookmark first asset
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);
        Long assetId3 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, position1, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(assetId2), null, WatchStatus.ALL, null);

        //assetHistory/action/list - filter by asset 2 id
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        listAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household,null));
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId2);

        String concatenatedString = getConcatenatedString(String.valueOf(assetId2), String.valueOf(assetId3));

        //assetHistory/action/list - filter by asset 2 and asset 3 ids
        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(concatenatedString,
                null, WatchStatus.ALL, null);

        listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId3);
        assertThat(assetHistoryListResponse.results.getObjects().get(1).getAssetId()).isEqualTo(assetId2);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household, null));
        executor.executeSync(cleanAssetHistoryBuilder);
    }

    @Description("/AssetHistory/action/list -filtered by movie type id")
    @Test
    private void vodAssetHistoryFilteredByAssetType() {

        Household household = HouseholdUtils.createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = HouseholdUtils.getHouseholdMasterUserKs(household, null);


        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, EPISODE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL,
                getProperty(MOVIE_MEDIA_TYPE_ID));

        //assetHistory/action/list - filter by in progress assets only

        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        listAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household,null));
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId1);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household, null));
        executor.executeSync(cleanAssetHistoryBuilder);
    }


    @Description("/AssetHistory/action/list -filtered by assets progress")
    @Test
    private void vodAssetHistoryFilteredByAssetProgress() {

        Household household = HouseholdUtils.createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = HouseholdUtils.getHouseholdMasterUserKs(household, null);


        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, MOVIE_MEDIA_TYPE, 10, BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = AssetHistoryUtils.ingestAssetAndPerformBookmark(userKs, EPISODE_MEDIA_TYPE, 100, BookmarkActionType.FINISH);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.PROGRESS, null);

        //assetHistory/action/list - filter by in progress assets only

        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        listAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household,null));
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId1);

        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.DONE, null);

        //assetHistory/action/list - filter by finished assets only

        listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter,null);
        assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId2);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(HouseholdUtils.getHouseholdMasterUserKs(household, null));
        executor.executeSync(cleanAssetHistoryBuilder);
    }

    //todo - Currently EPG program not returned in response (Ticket was opened to Omer - BEO-4594]

}
