package com.kaltura.client.test.tests.servicesTests.assetHistoryTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.AssetHistoryService.*;
import static com.kaltura.client.services.BookmarkService.AddBookmarkBuilder;
import static com.kaltura.client.services.BookmarkService.add;
import static com.kaltura.client.test.tests.enums.MediaType.EPISODE;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.AssetUtils.getAssets;
import static com.kaltura.client.test.utils.BookmarkUtils.addBookmark;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.PurchaseUtils.purchasePpv;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

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

    String masterUserKsCleanHistory;
    String masterUserKsCleanSpecifcAssetHistory;
    String masterUserKsCleanSpecifcAssetTypeHistory;
    String masterUserKsCleanAssetsAccordingToWatchStatusDone;
    String masterUserKsCleanAssetsAccordingToWatchStatusProgress;

    @BeforeClass(alwaysRun = true)
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
    @Test(groups = {"slowBefore"}, priority = -1)
    private void cleanHistory_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsCleanHistory = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsCleanHistory, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanHistory, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark1 = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark1).setKs(masterUserKsCleanHistory);
        executor.executeSync(addBookmarkBuilder);

        // purchase media2 and prepare media file for playback
        masterUserKsCleanHistory = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsCleanHistory, Optional.of(movie2.getId().intValue()), Optional.of(movie2FileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanHistory, String.valueOf(movie2.getId()),
                String.valueOf(movie2FileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset
        Bookmark bookmark2 = addBookmark(position1, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        addBookmarkBuilder = add(bookmark2).setKs(masterUserKsCleanHistory);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/clean - no filtering")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void cleanHistory_after_wait() {
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
                    // wait for 2 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsCleanHistory)).results.getTotalCount() == 2);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsCleanHistory));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);

        // assetHistory/action/clean
        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKsCleanHistory);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - no object returned
        assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
            .setKs(masterUserKsCleanHistory));

        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(0);

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsCleanHistory));
    }

    @Description("assetHistory/action/clean - filtered by asset id")
    @Test(groups = {"slowBefore"})
    private void cleanSpecifcAssetHistory_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsCleanSpecifcAssetHistory = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsCleanSpecifcAssetHistory, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanSpecifcAssetHistory, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark1 = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark1)
                .setKs(masterUserKsCleanSpecifcAssetHistory));

        // purchase media2 and prepare media file for playback
        masterUserKsCleanSpecifcAssetHistory = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsCleanSpecifcAssetHistory, Optional.of(movie2.getId().intValue()), Optional.of(movie2FileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanSpecifcAssetHistory, String.valueOf(movie2.getId()),
                String.valueOf(movie2FileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset
        Bookmark bookmark2 = addBookmark(position2, String.valueOf(movie2.getId()), movie2FileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark2)
                .setKs(masterUserKsCleanSpecifcAssetHistory));
    }

    @Description("assetHistory/action/clean - filtered by asset id")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void cleanSpecifcAssetHistory_after_wait() {
        // assetHistory/action/clean
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setAssetIdIn(String.valueOf(movie.getId()));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKsCleanSpecifcAssetHistory);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = "")
        assetHistoryFilter.setAssetIdIn("");

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsCleanSpecifcAssetHistory)).results.getTotalCount() == 1);
                });

        // assetHistory/action/list - after clean - only asset id 2 returned (was not cleaned)
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsCleanSpecifcAssetHistory));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie2.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsCleanSpecifcAssetHistory));
    }

    @Description("assetHistory/action/clean - filtered by asset type")
    @Test(groups = {"slowBefore"})
    private void cleanSpecifcAssetTypeHistory_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsCleanSpecifcAssetTypeHistory = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsCleanSpecifcAssetTypeHistory, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanSpecifcAssetTypeHistory, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark)
                .setKs(masterUserKsCleanSpecifcAssetTypeHistory));

        // purchase media episode and prepare media file for playback
        masterUserKsCleanSpecifcAssetTypeHistory = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsCleanSpecifcAssetTypeHistory, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanSpecifcAssetTypeHistory, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        executor.executeSync(add(bookmark)
                .setKs(masterUserKsCleanSpecifcAssetTypeHistory));
    }

    @Description("assetHistory/action/clean - filtered by asset type")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void cleanSpecifcAssetTypeHistory_after_wait() {
        //assetHistory/action/clean - only episode type (episode)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setTypeIn(String.valueOf(getMediaTypeId(EPISODE)));
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKsCleanSpecifcAssetTypeHistory);
        executor.executeSync(cleanAssetHistoryBuilder);

        // Update assetHistoryFilter object (assetIdIn = "")
        assetHistoryFilter.setTypeIn("");

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsCleanSpecifcAssetTypeHistory)).results.getTotalCount() == 1);
                });

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsCleanSpecifcAssetTypeHistory));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsCleanSpecifcAssetTypeHistory));
    }

    @Description("assetHistory/action/clean - filtered by asset finished")
    @Test(groups = {"slowBefore"})
    private void cleanAssetsAccordingToWatchStatusDone_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsCleanAssetsAccordingToWatchStatusDone = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsCleanAssetsAccordingToWatchStatusDone, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanAssetsAccordingToWatchStatusDone, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone);
        executor.executeSync(addBookmarkBuilder);

        // purchase media episode and prepare media file for playback
        masterUserKsCleanAssetsAccordingToWatchStatusDone = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsCleanAssetsAccordingToWatchStatusDone, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanAssetsAccordingToWatchStatusDone, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/clean - filtered by asset finished")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void cleanAssetsAccordingToWatchStatusDone_after_wait() {
        //assetHistory/action/clean - only asset that were finished (episode)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.DONE);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - only movie returned (was not cleaned)
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(movie.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsCleanAssetsAccordingToWatchStatusDone));
    }

    @Description("assetHistory/action/clean - filtered by asset in progress")
    @Test(groups = {"slowBefore"})
    private void cleanAssetsAccordingToWatchStatusProgress_before_wait() {
        // create household
        Household household = createHousehold(numOfUsers, numOfDevices, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        String udid2 = getDevicesList(household).get(1).getUdid();
        masterUserKsCleanAssetsAccordingToWatchStatusProgress = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKsCleanAssetsAccordingToWatchStatusProgress, Optional.of(movie.getId().intValue()), Optional.of(movieFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanAssetsAccordingToWatchStatusProgress, String.valueOf(movie.getId()),
                String.valueOf(movieFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark first asset
        Bookmark bookmark = addBookmark(position1, String.valueOf(movie.getId()), movieFileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress);
        executor.executeSync(addBookmarkBuilder);

        // purchase media2 and prepare media file for playback
        masterUserKsCleanAssetsAccordingToWatchStatusProgress = getHouseholdMasterUserKs(household, udid2);
        purchasePpv(masterUserKsCleanAssetsAccordingToWatchStatusProgress, Optional.of(episode.getId().intValue()), Optional.of(episodeFileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKsCleanAssetsAccordingToWatchStatusProgress, String.valueOf(episode.getId()),
                String.valueOf(episodeFileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark second asset
        bookmark = addBookmark(position2, String.valueOf(episode.getId()), episodeFileId, AssetType.MEDIA, BookmarkActionType.FINISH);
        addBookmarkBuilder = add(bookmark).setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress);
        executor.executeSync(addBookmarkBuilder);
    }

    @Description("assetHistory/action/clean - filtered by asset in progress")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void cleanAssetsAccordingToWatchStatusProgress_after_wait() {
        // assetHistory/action/clean - only asset that in progress (movie)
        AssetHistoryFilter assetHistoryFilter = new AssetHistoryFilter();
        assetHistoryFilter.setStatusEqual(WatchStatus.PROGRESS);

        CleanAssetHistoryBuilder cleanAssetHistoryBuilder = clean(assetHistoryFilter);
        cleanAssetHistoryBuilder.setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress);
        executor.executeSync(cleanAssetHistoryBuilder);

        // assetHistory/action/list - after clean - only episode returned (was not cleaned)
        assetHistoryFilter.setStatusEqual(WatchStatus.ALL);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() -> {
                    // wait for 1 assets at history response
                    return (executor.executeSync(list(assetHistoryFilter).setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress)).results.getTotalCount() == 1);
                });

        Response<ListResponse<AssetHistory>> assetHistoryListResponse = executor.executeSync(list(assetHistoryFilter)
                .setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress));

        assertThat(assetHistoryListResponse.error).isNull();
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetHistoryListResponse.results.getObjects().get(0).getAssetId()).isEqualTo(episode.getId());

        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(masterUserKsCleanAssetsAccordingToWatchStatusProgress));
    }
}
