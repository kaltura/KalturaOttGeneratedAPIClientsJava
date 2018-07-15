package com.kaltura.client.test.tests.servicesTests.assetHistoryTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.AssetHistoryService.list;
import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.BookmarkService.add;
import static com.kaltura.client.test.tests.enums.MediaType.EPISODE;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.BaseUtils.getConcatenatedString;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInEpoch;
import static com.kaltura.client.test.utils.BookmarkUtils.addBookmark;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getAssets;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {
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
    private void list_tests_before_class() {
        List<MediaAsset> movies = getAssets(2, Optional.of(MOVIE));

        // get first movie asset
        movie = movies.get(0);
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movie.getId())).get(0);

        // get second movie asset
        movie2 = movies.get(1);
        movie2FileId = AssetUtils.getAssetFileIds(String.valueOf(movie2.getId())).get(0);

        // Ingest episode asset
        episode = getAssets(1, Optional.of(EPISODE)).get(0);
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episode.getId())).get(0);
    }

    @Description("assetHistory/action/list - with no filter and one device and two media")
    @Test
    private void assetHistory_vod_with_one_device_and_two_media() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
//        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(movie.getId())), Optional.of(movieFileId), Optional.empty());
//
//        // getPlaybackContext
//        PlaybackContextOptions options = new PlaybackContextOptions();
//        options.setStreamerType("applehttp");
//        options.setMediaProtocol("http");
//        options.setContext(PlaybackContextType.PLAYBACK);
//
//        GetPlaybackContextAssetBuilder getPlaybackContextAssetBuilder =
//                getPlaybackContext(String.valueOf(movie.getId()), AssetType.MEDIA, options)
//                .setKs(masterUserKs);
//        executor.executeSync(getPlaybackContextAssetBuilder);

        // Bookmark first asset - first play
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKs));

        // Bookmark first asset - stop
        bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.STOP);
        executor.executeSync(add(bookmark).setKs(masterUserKs));

        // Bookmark second asset - first play
//        PurchaseUtils.purchasePpv(masterUserKs, Optional.of(Math.toIntExact(movie2.getId())), Optional.of(movie2FileId), Optional.empty());
//
//        getPlaybackContextAssetBuilder = getPlaybackContext(String.valueOf(movie2.getId()), AssetType.MEDIA, options)
//                .setKs(masterUserKs);
//        executor.executeSync(getPlaybackContextAssetBuilder);

        bookmark = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKs));

        // assetHistory/action/list - both assets should returned
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

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

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/list - with no filter and two devices and one media")
    @Test
    private void assetHistory_vod_with_two_devices_and_one_media() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first device - first play
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKs));

        // Bookmark second device - first play
        bookmark = addBookmark(position2, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        executor.executeSync(add(bookmark).setKs(masterUserKs));

        // assetHistory/action/list - both assets should returned
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);

        AssetHistory assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position2);
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(movie.getId());
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(getTimeInEpoch(0));

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/list - filtered by movie asset id")
    @Test
    private void vodAssetHistoryFilteredByAssetId() {
        // create household
        int numOfDevices = 3;
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String udid3 = getDevicesList(household).get(2).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark third asset
        bookmark = addBookmark(position1, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid3);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        //assetHistory/action/list - filter by asset 2 id
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setAssetIdIn(String.valueOf(movie2.getId()));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());

        //assetHistory/action/list - filter by asset 2 and asset 3 ids
        String concatenatedString = getConcatenatedString(String.valueOf(movie2.getId()), String.valueOf(episode.getId()));
        assetHistoryFilter.setAssetIdIn(concatenatedString);

        List<AssetHistory> assetHistoryList = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs)).results.getObjects();

        assertThat(assetHistoryList).extracting("assetId")
                .containsExactlyInAnyOrder(movie2.getId(), episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/list - filtered by movie type id")
    @Test
    private void vodAssetHistoryFilteredByAssetType() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        //assetHistory/action/list - filter by in progress assets only
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);
        assetHistoryFilter.setTypeIn(String.valueOf(getMediaTypeId(MediaType.MOVIE)));

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }

    @Description("assetHistory/action/list - filtered by assets progress")
    @Test
    private void vodAssetHistoryFilteredByAssetProgress() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        masterUserKs = getHouseholdMasterUserKs(household, udid2);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKs);
        executor.executeSync(addBookmarkBuilder);

        //assetHistory/action/list - filter by in progress assets only
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.PROGRESS);

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        //assetHistory/action/list - filter by finished assets only
        assetHistoryFilter.setStatusEqual(WatchStatus.DONE);

        assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKs));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKs));
    }
    //todo - Currently EPG program not returned in response (Ticket was opened to Omer - BEO-4594]
}