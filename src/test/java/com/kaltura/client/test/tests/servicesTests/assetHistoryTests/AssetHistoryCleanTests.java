package com.kaltura.client.test.tests.servicesTests.assetHistoryTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.services.AssetHistoryService.*;
import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.BookmarkService.add;
import static com.kaltura.client.test.tests.enums.MediaType.EPISODE;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.AssetUtils.getAssets;
import static com.kaltura.client.test.utils.BookmarkUtils.addBookmark;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryCleanTests extends BaseTest {
    private final int position1 = 10;
    private final int position2 = 20;
    private final int numOfUsers = 1;
    private final int numOfDevices = 2;

    private MediaAsset movie;
    private int movieFileId;

    private MediaAsset movie2;
    private int movie2FileId;

    private MediaAsset episode;
    private int episodeFileId;


    @BeforeClass
    private void assetHistory_clean_tests_before_class() {
        List<MediaAsset> movies = getAssets(2, MOVIE);

        // get first movie asset
        movie = movies.get(0);
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movie.getId())).get(0);

        // get second movie asset
        movie2 = movies.get(1);
        movie2FileId = AssetUtils.getAssetFileIds(String.valueOf(movie2.getId())).get(0);

        // Ingest episode asset
        episode = getAssets(1, EPISODE).get(0);
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episode.getId())).get(0);
    }

    @Description("assetHistory/action/clean - no filtering")
    @Test
    private void cleanHistory() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark1 = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark1).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        Bookmark bookmark2 = addBookmark(position1, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark2).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // assetHistory/action/list - both assets should returned
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        // assetHistory/action/clean
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - no object returned
        assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
            .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(0);

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/clean - filtered by asset id")
    @Test
    private void cleanSpecifcAssetHistory() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark1 = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark1)
                .setKs(masterUserKs));

        // Bookmark second asset
        Bookmark bookmark2 = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        executor.executeSync(add(bookmark2)
                .setKs(masterUserKs));

        // assetHistory/action/clean
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setAssetIdIn(String.valueOf(movie.getId()));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = "")
        assetHistoryFilter.setAssetIdIn("");

        // assetHistory/action/list - after clean - only asset id 2 returned (was not cleaned)
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/clean - filtered by asset type")
    @Test
    private void cleanSpecifcAssetTypeHistory() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark)
                .setKs(masterUserKs));

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        executor.executeSync(add(bookmark)
                .setKs(masterUserKs));

        //assetHistory/action/clean - only episode type (episode)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setTypeIn(String.valueOf(getMediaTypeId(EPISODE)));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = "")
        assetHistoryFilter.setTypeIn("");

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/clean - filtered by asset finished")
    @Test
    private void cleanAssetsAccordingToWatchStatusDone() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        //assetHistory/action/clean - only asset that were finished (episode)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.DONE);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/clean - filtered by asset in progress")
    @Test
    private void cleanAssetsAccordingToWatchStatusProgress() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // assetHistory/action/clean - only asset that in progress (movie)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.PROGRESS);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKs);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - only episode returned (was not cleaned)
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }
}
