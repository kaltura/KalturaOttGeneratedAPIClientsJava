package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.enums.*;
import com.kaltura.client.services.BookmarkService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BookmarkUtils;
import com.kaltura.client.types.Bookmark;
import com.kaltura.client.types.BookmarkFilter;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.BookmarkService.*;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.getSharedMasterUserKs;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.HouseholdUtils.*;
import static com.kaltura.client.test.utils.PurchaseUtils.purchasePpv;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class BookmarkListTests extends BaseTest {

    private long assetId, assetId2;
    private int fileId, fileId2;
    private List<String> assetList;
    private String masterUserKs;

    @BeforeClass(alwaysRun = true)
    private void bookmark_listTests_before_class() {
        assetList = new ArrayList<>();

        assetId = BaseTest.getSharedMediaAsset().getId();
        List<Integer> assetFileIds = AssetUtils.getAssetFileIds(String.valueOf(assetId));
        fileId = assetFileIds.get(0);
        assetList.add(String.valueOf(assetId));

        assetId2 = BaseTest.getSharedMediaAsset().getId();
        List<Integer> asset2FileIds = AssetUtils.getAssetFileIds(String.valueOf(assetId2));
        fileId2 = asset2FileIds.get(0);
        assetList.add(String.valueOf(assetId2));
    }

    @Description("bookmark/action/list - order by")
    @Test(groups = {"slowBefore"})
    private void BookmarkOrderBy_before_wait() {
        // create household
        int numberOfUsersInHousehold = 1;
        int numberOfDevicesInHousehold = 1;
        Household household = createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        String udid1 = getDevicesList(household).get(0).getUdid();
        masterUserKs = getHouseholdMasterUserKs(household, udid1);

        // purchase media and prepare media file for playback
        purchasePpv(masterUserKs, Optional.of((int)assetId), Optional.of(fileId), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKs, String.valueOf(assetId),
                String.valueOf(fileId), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark asset1
        Bookmark bookmark = BookmarkUtils.addBookmark(0, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        BookmarkService.AddBookmarkBuilder addBookmarkBuilder = BookmarkService.add(bookmark).setKs(masterUserKs);
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // purchase media2 and prepare media file for playback
        purchasePpv(masterUserKs, Optional.of((int)assetId2), Optional.of(fileId2), Optional.empty());
        AssetUtils.playbackAssetFilePreparation(masterUserKs, String.valueOf(assetId2),
                String.valueOf(fileId2), AssetType.MEDIA, PlaybackContextType.PLAYBACK, UrlType.PLAYMANIFEST);

        // Bookmark asset2
        Bookmark bookmark2 = BookmarkUtils.addBookmark(10, String.valueOf(assetId2), fileId2, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);

        AddBookmarkBuilder addBookmarkBuilder2 = BookmarkService.add(bookmark2).setKs(masterUserKs);
        Response<Boolean> booleanResponse2 = executor.executeSync(addBookmarkBuilder2);

        assertThat(booleanResponse.error).isNull();
        assertThat(booleanResponse2.results.booleanValue()).isTrue();
    }

    @Description("bookmark/action/list - order by")
    @Test(groups = {"slowAfter"}, dependsOnGroups = {"slowBefore"})
    private void BookmarkOrderBy_after_wait() {
        // set filter
        BookmarkFilter bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_DESC,AssetType.MEDIA, assetList);

        // prepare variables for await() functionality
        int delayBetweenRetriesInSeconds = 15;
        int maxTimeExpectingValidResponseInSeconds = 80;

        // Invoke bookmark/action/list to verify insertion of assets bookmarks
        ListBookmarkBuilder listBookmarkBuilderAwait = list(bookmarkFilter).setKs(masterUserKs);
        await()
                .pollInterval(delayBetweenRetriesInSeconds, TimeUnit.SECONDS)
                .atMost(maxTimeExpectingValidResponseInSeconds, TimeUnit.SECONDS)
                .until(() ->{
                    return (executor.executeSync(listBookmarkBuilderAwait).results.getTotalCount() == 2);
                });

        ListBookmarkBuilder listBookmarkBuilder = listBookmarkBuilderAwait;
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);

        assertThat(bookmarkListResponse.error).isNull();
        Bookmark bookmarkObject = bookmarkListResponse.results.getObjects().get(0);
        Bookmark bookmarkObject2 = bookmarkListResponse.results.getObjects().get(1);

        // Verify that asset2 returned first (bookmark/action/list is response is ordered by POSITION DESC)
        assertThat( bookmarkObject.getId()).isEqualTo(String.valueOf(assetId2));
        assertThat( bookmarkObject2.getId()).isEqualTo(String.valueOf(assetId));

        bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_ASC,AssetType.MEDIA, assetList);
        listBookmarkBuilder = BookmarkService.list(bookmarkFilter).setKs(masterUserKs);
        bookmarkListResponse = executor.executeSync(listBookmarkBuilder);

        assertThat(bookmarkListResponse.error).isNull();
        bookmarkObject = bookmarkListResponse.results.getObjects().get(0);
        bookmarkObject2 = bookmarkListResponse.results.getObjects().get(1);

        // Verify that asset1 returned first (bookmark/action/list is response is ordered by POSITION DESC)
        assertThat( bookmarkObject.getId()).isEqualTo(String.valueOf(assetId));
        assertThat( bookmarkObject2.getId()).isEqualTo(String.valueOf(assetId2));
    }

    @Description("bookmark/action/list - empty asset id")
    @Test
    private void emptyAssetId() {
        // creating bookmark filter with empty asset id
        BookmarkFilter bookmarkFilter = new BookmarkFilter();
        bookmarkFilter.setAssetIdIn("");
        bookmarkFilter.setAssetTypeEqual(AssetType.MEDIA);

        ListBookmarkBuilder listBookmarkBuilder = list(bookmarkFilter).setKs(getSharedMasterUserKs());
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);

        assertThat(bookmarkListResponse.results).isNull();
        assertThat(bookmarkListResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(50027).getCode());
    }
}
