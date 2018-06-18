package com.kaltura.client.test.tests.servicesTests.assetHistoryTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetHistoryUtils;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BookmarkUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.kaltura.client.services.AssetHistoryService.*;
import static com.kaltura.client.services.BookmarkService.*;
import static com.kaltura.client.test.IngestConstants.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.Properties.MOVIE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.BaseUtils.getConcatenatedString;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInEpoch;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getHouseholdMasterUserKs;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {

    private final int position1 = 10;
    private final int position2 = 20;
    private final int numbOfDevices = 1;
    private final int numOfUsers = 1;
    private MediaAsset movie;
    private int movieFileId;
    private MediaAsset movie2;
    private int movie2FileId;
    private MediaAsset episode;
    private int episodeFileId;

    @BeforeClass
    private void list_tests_before_class() {
        // Ingest first movie asset
        movie = IngestUtils.ingestVOD( MOVIE_MEDIA_TYPE);
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movie.getId())).get(0);
        // Ingest second movie asset
        movie2 = IngestUtils.ingestVOD(MOVIE_MEDIA_TYPE);
        movie2FileId = AssetUtils.getAssetFileIds(String.valueOf(movie2.getId())).get(0);
        // Ingest episode asset
        episode = IngestUtils.ingestVOD(EPISODE_MEDIA_TYPE);
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episode.getId())).get(0);
    }

    @Description("/AssetHistory/action/list - with no filter")
    @Test
    private void vodAssetHistory() {

        // Create HH with one user and one device
        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Build AssetHistoryFilter object
        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        //assetHistory/action/list - both assets should returned
        ListAssetHistoryBuilder listAssetHistoryBuilder = list(assetHistoryFilter, null)
                .setKs(userKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        // objects can be returned in any order
        AssetHistory assetHistoryObject1, assetHistoryObject2;
        if (assetHistoryListResponse.results.getObjects().get(0).getAssetId().equals(movie2.getId())) {
            assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
            assetHistoryObject2 = assetHistoryListResponse.results.getObjects().get(1);
        } else {
            assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(1);
            assetHistoryObject2 = assetHistoryListResponse.results.getObjects().get(0);
        }

        // Assertions for first object returned
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position2);
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(movie2.getId());

        // Verify that flag is set to false (user hasn't finish watching the asset)
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(getTimeInEpoch(0));

        // Assertions for second object returned
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(movie.getId());
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(position1);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);
    }

    @Description("/AssetHistory/action/list -filtered by movie asset id")
    @Test
    private void vodAssetHistoryFilteredByAssetId() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark third asset
        bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(movie2.getId()),
                null, WatchStatus.ALL, null);


        //assetHistory/action/list - filter by asset 2 id
        ListAssetHistoryBuilder listAssetHistoryBuilder = list(assetHistoryFilter, null)
                .setKs(userKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());

        String concatenatedString = getConcatenatedString(String.valueOf(movie2.getId()), String.valueOf(episode.getId()));


        //assetHistory/action/list - filter by asset 2 and asset 3 ids
        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(concatenatedString, null, WatchStatus.ALL, null);

        listAssetHistoryBuilder = list(assetHistoryFilter, null)
                .setKs(userKs);
        assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        List<AssetHistory> assetHistoryList = assetHistoryListResponse.results.getObjects();

        List<Long> assetHistoryIdsList = new ArrayList<>();
        for (AssetHistory assetHistory : assetHistoryList) {
            assetHistoryIdsList.add(assetHistory.getAssetId());
        }
        assertThat(assetHistoryIdsList).containsOnly(movie2.getId(), episode.getId());
    }

    @Description("/AssetHistory/action/list -filtered by movie type id")
    @Test
    private void vodAssetHistoryFilteredByAssetType() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL,
                getProperty(MOVIE_MEDIA_TYPE_ID));

        //assetHistory/action/list - filter by in progress assets only

        ListAssetHistoryBuilder listAssetHistoryBuilder = list(assetHistoryFilter, null);
        listAssetHistoryBuilder.setKs(userKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());
    }

    @Description("/AssetHistory/action/list -filtered by assets progress")
    @Test
    private void vodAssetHistoryFilteredByAssetProgress() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String userKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(userKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.PROGRESS, null);

        //assetHistory/action/list - filter by in progress assets only

        ListAssetHistoryBuilder listAssetHistoryBuilder = list(assetHistoryFilter, null).setKs(userKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.DONE, null);

        //assetHistory/action/list - filter by finished assets only

        listAssetHistoryBuilder = list(assetHistoryFilter, null).setKs(userKs);
        assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());

    }

    //todo - Currently EPG program not returned in response (Ticket was opened to Omer - BEO-4594]
}
