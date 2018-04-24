package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.*;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.BookmarkUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;


public class BookmarkAddTests extends BaseTest {

    private Client client;
    private long assetId;
    private int fileId;
    private BookmarkActionType actionType;
    private int position = 0;
    private List<String> assetList = new ArrayList<>();
    // instantiate Bookmark object
    private Bookmark bookmark = new Bookmark();
    // instantiate BookmarkFilter object
    private BookmarkFilter bookmarkFilter = new BookmarkFilter();

    @BeforeClass
    private void add_tests_before_class() {
        BaseUtils.getSharedHousehold();
        client = getClient(sharedMasterUserKs);
        assetId = 608775;
        AssetReferenceType assetReferenceType = AssetReferenceType.get(AssetReferenceType.MEDIA.getValue());
        Response<Asset> assetResponse = AssetServiceImpl.get(client, String.valueOf(assetId), assetReferenceType);
        fileId = assetResponse.results.getMediaFiles().get(0).getId();
        actionType = BookmarkActionType.get(BookmarkActionType.FIRST_PLAY.getValue());

        assetList.add(String.valueOf(assetId));
        // Initialize bookmark object parameters

        // Initialize bookmarkFilter object parameters
        bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_ASC, AssetType.MEDIA, assetList);
    }

    @Description("bookmark/action/add - first play")
    @Test
    private void firstPlayback() {
        actionType = BookmarkActionType.FIRST_PLAY;
        position = 0;
        bookmark = BookmarkUtils.addBookmark(position,String.valueOf(assetId),fileId,AssetType.MEDIA, actionType);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();


        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse = BookmarkServiceImpl.list(client, bookmarkFilter);
        Bookmark bookmark1 = bookmarkListResponse.results.getObjects().get(0);

        // Match content of asset id
        assertThat(bookmark1.getId()).isEqualTo(String.valueOf(assetId));

        // Match content of asset position
        assertThat(bookmark1.getPosition()).isEqualTo(this.position);

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
        // Set action type to "PAUSE"
        actionType = BookmarkActionType.PAUSE;
        position = 30;
        bookmark = BookmarkUtils.addBookmark(position,String.valueOf(assetId),fileId,AssetType.MEDIA, actionType);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse2 = BookmarkServiceImpl.list(client, bookmarkFilter);
        Bookmark bookmark2 = bookmarkListResponse2.results.getObjects().get(0);

        // Match content of asset position
        assertThat(bookmark2.getPosition()).isEqualTo(this.position);
    }

    @Description("bookmark/action/add - 95% watching == finish watching")
    @Test
    private void watchingNinetyFive() {
        actionType = BookmarkActionType.PLAY;
        position = 999;
        bookmark = BookmarkUtils.addBookmark(position,String.valueOf(assetId),fileId,AssetType.MEDIA, actionType);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse3 = BookmarkServiceImpl.list(client, bookmarkFilter);
        Bookmark bookmark3 = bookmarkListResponse3.results.getObjects().get(0);

        // Verify finishedWatching = true
        assertThat(bookmark3.getFinishedWatching()).isTrue();

    }

    @Description("bookmark/action/add - back to start - position:0")
    @Test
    private void backToStart() {
        actionType = BookmarkActionType.STOP;
        position = 0;
        bookmark = BookmarkUtils.addBookmark(position,String.valueOf(assetId),fileId,AssetType.MEDIA, actionType);

        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        Response<ListResponse<Bookmark>> bookmarkListResponse4 = BookmarkServiceImpl.list(client, bookmarkFilter);
        Bookmark bookmark4 = bookmarkListResponse4.results.getObjects().get(0);
        // Verify finishedWatching = false
        assertThat(bookmark4.getFinishedWatching()).isFalse();
    }

    @Description("bookmark/action/add - finish watching")
    @Test
    private void finishWatching() {
        // Set action type to "FINISH"
        actionType = BookmarkActionType.FINISH;
        position = 60;
        bookmark = BookmarkUtils.addBookmark(position,String.valueOf(assetId),fileId,AssetType.MEDIA, actionType);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse = BookmarkServiceImpl.list(client, bookmarkFilter);
        Bookmark bookmark = bookmarkListResponse.results.getObjects().get(0);

        // Verify finishedWatching = true
        assertThat(bookmark.getFinishedWatching()).isTrue();

    }

    // Error validations

    @Description("bookmark/action/add - empty asset id")
    @Test
    private void emptyAssetId() {
        bookmark = BookmarkUtils.addBookmark(0, null, fileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        assertThat(booleanResponse.results).isNull();
        // Verify exception returned - code: 500003 ("Invalid Asset id")
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500003).getCode());
    }


    // TODO - Add test for EPG bookmark
    // TODO - Add test for recording bookmark
}
