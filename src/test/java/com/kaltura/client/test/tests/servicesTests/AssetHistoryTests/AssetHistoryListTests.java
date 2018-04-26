package com.kaltura.client.test.tests.servicesTests.AssetHistoryTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.test.servicesImpl.AssetHistoryServiceImpl;
import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.*;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.Properties.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {
    private Client client;
    private AssetType assetType = AssetType.MEDIA;

    @BeforeClass
    private void add_tests_before_class() {
        client = getClient(getsharedMasterUserKs());
    }

    @Description("/AssetHistory/action/list - with no filter")
    @Test
    private void vodAssetHistory() {

        int asset1Position = 10;
        int asset2Position = 20;

        // Ingest and bookmark first asset
        Long assetId1 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,asset1Position,BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,asset2Position,BookmarkActionType.FIRST_PLAY);

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
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(asset2Position);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        // Verify that flag is set to false (user hasn't finish watching the asset)
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(BaseUtils.getTimeInEpoch(0));

        // Assertions for second object returned
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(assetId1);
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(assetType);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(asset1Position);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);
    }

    @Description("/AssetHistory/action/list -filtered by movie asset id")
    @Test
    private void vodAssetHistoryFilteredByAssetId() {

        // Ingest and bookmark first asset
        Long assetId1 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,10,BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset
        Long assetId2 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,10,BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(assetId2), null, WatchStatus.ALL, null);
        //assetHistory/action/list - filter by asset 2 id
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId2);
    }

    @Description("/AssetHistory/action/list -filtered by movie type id")
    @Test
    private void vodAssetHistoryFilteredByAssetType() {

        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,10,BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = ingestAssetAndPerformBookmark(EPISODE_MEDIA_TYPE,10,BookmarkActionType.FIRST_PLAY);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, MOVIE_MEDIA_TYPE_ID);
        //assetHistory/action/list - filter by in progress assets only
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(assetId1);
    }


    @Description("/AssetHistory/action/list -filtered by assets in progress")
    @Test
    private void vodAssetHistoryFilteredByAssetProgress() {
        // Ingest and bookmark first asset (movie in first play)
        Long assetId1 = ingestAssetAndPerformBookmark(MOVIE_MEDIA_TYPE,10,BookmarkActionType.FIRST_PLAY);
        // Ingest and bookmark second asset (movie in finish action)
        Long assetId2 = ingestAssetAndPerformBookmark(EPISODE_MEDIA_TYPE,100,BookmarkActionType.FINISH);

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
    }



    // Ingest asset, bookmark it and return the asset id
    private Long ingestAssetAndPerformBookmark(String mediaType, int position, BookmarkActionType bookmarkActionType) {
        // Ingest asset
        MediaAsset mediaAsset = IngestVODUtils.ingestVOD(Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(String.valueOf(mediaType)), Optional.empty(), Optional.empty());
        Long assetId = mediaAsset.getId();
        int fileId = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0);
        // Movie asset bookmark
        assetType = AssetType.MEDIA;
        Bookmark bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, assetType, bookmarkActionType);
        //bookmark/action/add - Movie asset
        BookmarkServiceImpl.add(client, bookmark);

        return assetId;
    }
}
