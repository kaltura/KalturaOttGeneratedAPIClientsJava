package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.BookmarkOrderBy;
import com.kaltura.client.enums.PositionOwner;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BookmarkUtils;
import com.kaltura.client.types.Bookmark;
import com.kaltura.client.types.BookmarkFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.kaltura.client.services.BookmarkService.*;
import static com.kaltura.client.test.tests.BaseTest.SharedHousehold.getSharedMasterUserKs;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;


public class BookmarkAddTests extends BaseTest {

    private long assetId;
    private int fileId;
    private List<String> assetList;
    private Bookmark bookmark;
    private BookmarkFilter bookmarkFilter;

    @BeforeClass
    private void bookmark_addTests_before_class() {
        assetList = new ArrayList<>();
        assetId = BaseTest.getSharedMediaAsset().getId();
        fileId = AssetUtils.getAssetFileIds(String.valueOf(assetId)).get(0);

        assetList.add(String.valueOf(assetId));

        // Initialize bookmarkFilter object parameters
        bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_ASC, AssetType.MEDIA, assetList);
    }

    @Description("bookmark/action/add - first play")
    @Test
    private void firstPlayback() {
        int position = 0;
        bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);

        // Invoke bookmark/action/add request
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(getSharedMasterUserKs());
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);

        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        ListBookmarkBuilder listBookmarkBuilder = list(bookmarkFilter).setKs(getSharedMasterUserKs());
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);

        Bookmark bookmark1 = bookmarkListResponse.results.getObjects().get(0);

        // Match content of asset id
        assertThat(bookmark1.getId()).isEqualTo(String.valueOf(assetId));

        // Match content of asset position
        assertThat(bookmark1.getPosition()).isEqualTo(position);

        // verify finishedWatching = false
        assertThat(bookmark1.getFinishedWatching()).isFalse();

        // Verify positionOwner = user
        assertThat(bookmark1.getPositionOwner()).isEqualTo(PositionOwner.USER);

        // Verify asset type = media
        assertThat(bookmark1.getType()).isEqualTo(AssetType.MEDIA);

        // Verify total count = 1
        assertThat(bookmarkListResponse.results.getTotalCount()).isEqualTo(1);
    }

    @Description("bookmark/action/add - pause")
    @Test
    private void pausePlayback() {
        int position = 30;
        bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.PAUSE);

        // Invoke bookmark/action/add request
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(getSharedMasterUserKs());
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);

        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(list(bookmarkFilter)
                .setKs(getSharedMasterUserKs()));
        Bookmark bookmark = bookmarkListResponse.results.getObjects().get(0);

        // Match content of asset position
        assertThat(bookmark.getPosition()).isEqualTo(position);
    }

    @Description("bookmark/action/add - 95% watching == finish watching")
    @Test
    private void watchingNinetyFive() {
        int position = 999;
        bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.PLAY);

        // Invoke bookmark/action/add request
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(getSharedMasterUserKs());
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);

        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        ListBookmarkBuilder listBookmarkBuilder = list(bookmarkFilter).setKs(getSharedMasterUserKs());
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);
        Bookmark bookmark3 = bookmarkListResponse.results.getObjects().get(0);

        // Verify finishedWatching = true
        assertThat(bookmark3.getFinishedWatching()).isTrue();
    }

    @Description("bookmark/action/add - back to start - position:0")
    @Test
    private void backToStart() {
        int position = 0;
        bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.STOP);

        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(getSharedMasterUserKs());
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        ListBookmarkBuilder listBookmarkBuilder = list(bookmarkFilter).setKs(getSharedMasterUserKs());
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);
        Bookmark bookmark = bookmarkListResponse.results.getObjects().get(0);

        // Verify finishedWatching = false
        assertThat(bookmark.getFinishedWatching()).isFalse();
    }

    @Description("bookmark/action/add - finish watching")
    @Test
    private void finishWatching() {
        int position = 60;
        bookmark = BookmarkUtils.addBookmark(position, String.valueOf(assetId), fileId, AssetType.MEDIA, BookmarkActionType.FINISH);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = executor.executeSync(add(bookmark)
                .setKs(getSharedMasterUserKs()));

        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        ListBookmarkBuilder listBookmarkBuilder = list(bookmarkFilter).setKs(getSharedMasterUserKs());
        Response<ListResponse<Bookmark>> bookmarkListResponse = executor.executeSync(listBookmarkBuilder);
        Bookmark bookmark = bookmarkListResponse.results.getObjects().get(0);

        // Verify finishedWatching = true
        assertThat(bookmark.getFinishedWatching()).isTrue();
    }

    @Description("bookmark/action/add - empty asset id")
    @Test
    private void emptyAssetId() {
        bookmark = BookmarkUtils.addBookmark(0, null, fileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        AddBookmarkBuilder addBookmarkBuilder = add(bookmark).setKs(getSharedMasterUserKs());
        Response<Boolean> booleanResponse = executor.executeSync(addBookmarkBuilder);

        assertThat(booleanResponse.results).isNull();
        // Verify exception returned - code: 500003 ("Invalid Asset id")
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500003).getCode());
    }

    // TODO - Add test for EPG bookmark
    // TODO - Add test for recording bookmark
}
