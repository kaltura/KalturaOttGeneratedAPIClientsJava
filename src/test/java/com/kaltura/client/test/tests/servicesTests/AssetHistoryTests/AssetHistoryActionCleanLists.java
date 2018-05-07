package com.kaltura.client.test.tests.servicesTests.AssetHistoryTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.services.AssetHistoryService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetHistoryUtils;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BookmarkUtils;
import com.kaltura.client.test.utils.IngestUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.services.BookmarkService.*;
import static com.kaltura.client.services.AssetHistoryService.CleanAssetHistoryBuilder;
import static com.kaltura.client.services.AssetHistoryService.ListAssetHistoryBuilder;
import static com.kaltura.client.services.BookmarkService.add;
import static com.kaltura.client.test.IngestConstants.EPISODE_MEDIA_TYPE;
import static com.kaltura.client.test.IngestConstants.MOVIE_MEDIA_TYPE;
import static com.kaltura.client.test.Properties.EPISODE_MEDIA_TYPE_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getHouseholdMasterUserKs;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryActionCleanLists extends BaseTest {

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
    // TODO: 5/3/2018 change before method name
    private void clean_tests_before_class() {
        // Ingest first movie asset
        movie = IngestUtils.ingestBasicVOD(Optional.of("movieForBookmark"), MOVIE_MEDIA_TYPE);
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movie.getId())).get(0);
        // Ingest second movie asset
        movie2 = IngestUtils.ingestBasicVOD(Optional.of("movie2ForBookmark"), MOVIE_MEDIA_TYPE);
        movie2FileId = AssetUtils.getAssetFileIds(String.valueOf(movie2.getId())).get(0);
        // Ingest episode asset
        episode = IngestUtils.ingestBasicVOD(Optional.of("EpisodeForBookmark"), EPISODE_MEDIA_TYPE);
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episode.getId())).get(0);
    }

    @Description("/assetHistory/action/clean - no filtering")
    @Test
    private void cleanHistory() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String masterUserKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        //assetHistory/action/list - both assets should returned
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null)
                .setKs(masterUserKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        //assetHistory/action/clean
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - no object returned
        listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null)
                .setKs(masterUserKs);
        assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(0);
    }

    @Description("/assetHistory/action/clean - filtered by asset id")
    @Test
    private void cleanSpecifcAssetHistory() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String masterUserKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(movie.getId()), null, WatchStatus.ALL, null);

        //assetHistory/action/clean
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = null)
        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        // assetHistory/action/list - after clean - only asset id 2 returned (was not cleaned)
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null);
        listAssetHistoryBuilder.setKs(masterUserKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());
    }

    @Description("/assetHistory/action/clean - filtered by asset type")
    @Test
    private void cleanSpecifcAssetTypeHistory() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String masterUserKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, getProperty(EPISODE_MEDIA_TYPE_ID));

        //assetHistory/action/clean - only episode type (episode)

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = null)
        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)

        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null);
        listAssetHistoryBuilder.setKs(masterUserKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());
    }

    @Description("/assetHistory/action/clean - filtered by asset finished")
    @Test
    private void cleanAssetsAccordingToWatchStatusDone() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String masterUserKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.DONE, null);

        //assetHistory/action/clean - only asset that were finished (episode)

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)

        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null);
        listAssetHistoryBuilder.setKs(masterUserKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());
    }

    @Description("/assetHistory/action/clean - filtered by asset in progress")
    @Test
    private void cleanAssetsAccordingToWatchStatusProgress() {

        Household household = createHousehold(numOfUsers, numbOfDevices, false);
        String masterUserKs = getHouseholdMasterUserKs(household, null);

        // Bookmark first asset
        Bookmark bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.PROGRESS, null);

        //assetHistory/action/clean - only asset that in progress (movie)
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = AssetHistoryService.clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        // assetHistory/action/list - after clean - only episode returned (was not cleaned)
        ListAssetHistoryBuilder listAssetHistoryBuilder = AssetHistoryService.list(assetHistoryFilter, null);
        listAssetHistoryBuilder.setKs(masterUserKs);
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(listAssetHistoryBuilder);

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());
    }
}
