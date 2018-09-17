package com.kaltura.client.test.tests.servicesTests.assetHistoryTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.AssetHistoryService.list;
import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.BookmarkService.add;
import static com.kaltura.client.test.tests.enums.MediaType.EPISODE;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.AssetUtils.getAssets;
import static com.kaltura.client.test.utils.BaseUtils.getConcatenatedString;
import static com.kaltura.client.test.utils.BookmarkUtils.addBookmark;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.PurchaseUtils.purchasePpv;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

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

    private String masterUserKsAssetHistoryTwoMedias;
    private String masterUserKsAssetHistoryOneMedia;
    private String masterUserKsVodAssetHistoryFilteredByAssetId;
    private String masterUserKsVodAssetHistoryFilteredByAssetType;
    private String masterUserKsVodAssetHistoryFilteredByAssetProgress;


    @BeforeClass
    private void list_tests_before_class() {
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

    @Description("assetHistory/action/list - with no filter and one device and two media")
    @Test(groups = {"slowBefore"}, priority = -1)
    private void assetHistory_vod_with_one_device_and_two_media_before_wait() {
        list_tests_before_class();
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        masterUserKsAssetHistoryTwoMedias = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsAssetHistoryTwoMedias, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsAssetHistoryTwoMedias, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset - first play
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKsAssetHistoryTwoMedias));

        // Bookmark first asset - stop
        bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.STOP);
        executor.executeSync(add(bookmark).setKs(masterUserKsAssetHistoryTwoMedias));

        // purchase media2 and prepare media file for playback
        purchasePpv(masterUserKsAssetHistoryTwoMedias, Optional.of(movie2.getId().intValue()), Optional.of(movie2FileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsAssetHistoryTwoMedias, String.valueOf(movie2.getId()),
                String.valueOf(movie2FileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset - first play
        bookmark = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKsAssetHistoryTwoMedias));
    }

    @Description("assetHistory/action/list - with no filter and one device and two media")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void assetHistory_vod_with_one_device_and_two_media_after_wait() {
        // assetHistory/action/list - both assets should returned
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsAssetHistoryTwoMedias)).results.getTotalCount() == 2);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsAssetHistoryTwoMedias));

        assertThat(assetHistoryListResponse.error).isNull();
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
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(BaseUtils.getEpoch());

        // Assertions for second object returned
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(movie.getId());
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(position1);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsAssetHistoryTwoMedias));
    }

    @Description("assetHistory/action/list - with no filter and two devices and one media")
    @Test(groups = {"slowBefore"})
    private void assetHistory_vod_with_two_devices_and_one_media_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsAssetHistoryOneMedia = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback on first device
        purchasePpv(masterUserKsAssetHistoryOneMedia, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsAssetHistoryOneMedia, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first device - first play
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKsAssetHistoryOneMedia));

        // prepare media file for playback on second device
        masterUserKsAssetHistoryOneMedia = getHouseholdMasterUserKs(household, udid2);
        AssetUtils.playbackAssetFilePreparation(masterUserKsAssetHistoryOneMedia, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second device - first play
        bookmark = addBookmark(position2, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark).setKs(masterUserKsAssetHistoryOneMedia));
    }

    @Description("assetHistory/action/list - with no filter and two devices and one media")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void assetHistory_vod_with_two_devices_and_one_media_after_wait() {
        // assetHistory/action/list - both assets should returned
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsAssetHistoryOneMedia)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsAssetHistoryOneMedia));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);

        AssetHistory assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position2);
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(movie.getId());
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(BaseUtils.getEpoch());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsAssetHistoryOneMedia));
    }

    @Description("assetHistory/action/list - filtered by movie asset id")
    @Test(groups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetId_before_wait() {
        // create household
        int numOfDevices = 3;
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        String udid3 = getDevicesList(household).get(2).getUdid();
        masterUserKsVodAssetHistoryFilteredByAssetId = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetId, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetId, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetId);
        executor.executeSync(addBookmarkBuilder);

        // purchase media2 and prepare media file for playback
        masterUserKsVodAssetHistoryFilteredByAssetId = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetId, Optional.of(movie2.getId().intValue()), Optional.of(movie2FileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetId, String.valueOf(movie2.getId()),
                String.valueOf(movie2FileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetId);
        executor.executeSync(addBookmarkBuilder);

        // purchase media2 and prepare media file for playback
        masterUserKsVodAssetHistoryFilteredByAssetId = getHouseholdMasterUserKs(household, udid3);
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetId, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetId, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark third asset
        bookmark = addBookmark(position1, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetId);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/list - filtered by movie asset id")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetId_after_wait() {
        //assetHistory/action/list - filter by asset 2 id
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setAssetIdIn(String.valueOf(movie2.getId()));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsVodAssetHistoryFilteredByAssetId)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsVodAssetHistoryFilteredByAssetId));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());

        //assetHistory/action/list - filter by asset 2 and asset 3 ids
        String concatenatedString = getConcatenatedString(String.valueOf(movie2.getId()), String.valueOf(episode.getId()));
        assetHistoryFilter.setAssetIdIn(concatenatedString);

        List<AssetHistory> assetHistoryList = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsVodAssetHistoryFilteredByAssetId)).results.getObjects();

        assertThat(assetHistoryList).extracting("assetId")
                .containsExactlyInAnyOrder(movie2.getId(), episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsVodAssetHistoryFilteredByAssetId));
    }

    @Description("assetHistory/action/list - filtered by movie type id")
    @Test(groups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetType_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsVodAssetHistoryFilteredByAssetType = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetType, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetType, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetType);
        executor.executeSync(addBookmarkBuilder);

        // purchase media episode and prepare media file for playback
        masterUserKsVodAssetHistoryFilteredByAssetType = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetType, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetType, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetType);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/list - filtered by movie type id")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetType_after_wait() {
        //assetHistory/action/list - filter by in progress assets only
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);
        assetHistoryFilter.setTypeIn(String.valueOf(getMediaTypeId(MediaType.MOVIE)));

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsVodAssetHistoryFilteredByAssetType)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsVodAssetHistoryFilteredByAssetType));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsVodAssetHistoryFilteredByAssetType));
    }

    @Description("assetHistory/action/list - filtered by assets progress")
    @Test(groups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetProgress_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsVodAssetHistoryFilteredByAssetProgress = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetProgress, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetProgress, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress);
        executor.executeSync(addBookmarkBuilder);

        // purchase media episode and prepare media file for playback
        masterUserKsVodAssetHistoryFilteredByAssetProgress = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsVodAssetHistoryFilteredByAssetProgress, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsVodAssetHistoryFilteredByAssetProgress, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark Second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/list - filtered by assets progress")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void vodAssetHistoryFilteredByAssetProgress_after_wait() {
        //assetHistory/action/list - filter by in progress assets only
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.PROGRESS);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        //assetHistory/action/list - filter by finished assets only
        assetHistoryFilter.setStatusEqual(WatchStatus.DONE);

        assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsVodAssetHistoryFilteredByAssetProgress));
    }
    //todo - Currently EPG program not returned in response (Ticket was opened to Omer - BEO-4594]
}