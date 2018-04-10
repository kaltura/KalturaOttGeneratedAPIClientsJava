package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.PositionOwner;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AddTests extends BaseTest {

    private long assetId;
    private AssetType type;
    private int fileId;
    private BookmarkActionType actionType;
    private int position = 0;
    // instantiate Bookmark object
    private Bookmark bookmark = new Bookmark();
    // instantiate BookmarkPlayerData object
    private BookmarkPlayerData playerData = new BookmarkPlayerData();
    // instantiate BookmarkFilter object
    private BookmarkFilter bookmarkFilter = new BookmarkFilter();

    @BeforeClass
    private void add_tests_before_class() {
        // Get asset id from ingest
        assetId = mediaAsset.getId();
        type = AssetType.get("MEDIA");
        AssetReferenceType assetReferenceType = AssetReferenceType.get("MEDIA");
        Response<Asset> assetResponse = AssetServiceImpl.get(sharedMasterUserKs,String.valueOf(assetId),assetReferenceType);
        fileId = assetResponse.results.getMediaFiles().get(0).getId();
        actionType = BookmarkActionType.get("FIRST_PLAY");

        // Initialize bookmark object parameters
        bookmark.setType(type);
        bookmark.setId(String.valueOf(assetId));
        bookmark.setPosition(position);

        // Initialize playerData object parameters
        playerData.setAction(actionType);
        playerData.setAverageBitrate(0);
        playerData.setTotalBitrate(0);
        playerData.setCurrentBitrate(0);
        playerData.setFileId((long) fileId);
        bookmark.setPlayerData(playerData);

        // Initialize asset id
        bookmarkFilter.setAssetIdIn(String.valueOf(assetId));
        // Initialize orderBy
        bookmarkFilter.setOrderBy("POSITION_ASC");
        // Initialize asset type
        bookmarkFilter.setAssetTypeEqual(type);
    }

    @Description("bookmark/action/add - first play")
    @Test
    private void firstPlayback() {

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();


        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);
        Bookmark bookmark1 = bookmarkListResponse.results.getObjects().get(0);

        // Assertions
        // ***********************************************

        // Match content of asset id
        assertThat( bookmark1.getId()).isEqualTo(String.valueOf(assetId));

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

    @Description("bookmark/action/add - stop")
    @Test
    private void stopPlayback() {
        // Set action type to "STOP"
        actionType = BookmarkActionType.get("STOP");
        playerData.setAction(actionType);
        position = 30;
        bookmark.setPosition(position);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse2 = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);
        Bookmark bookmark2 = bookmarkListResponse2.results.getObjects().get(0);

        // Match content of asset position
        assertThat(bookmark2.getPosition()).isEqualTo(this.position);
    }


    @Description("bookmark/action/add - finish watching")
    @Test
    private void FinishWatching() {
        // Set action type to "FINISH"
        actionType = BookmarkActionType.get("FINISH");
        playerData.setAction(actionType);
        position = 60;
        bookmark.setPosition(position);

        // Invoke bookmark/action/add request
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark);
        // Verify response return true
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        // Verify no error returned
        assertThat(booleanResponse.error).isNull();

        // Invoke bookmark/action/list to verify insertion of bookmark position
        Response<ListResponse<Bookmark>> bookmarkListResponse3 = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);
        Bookmark bookmark3 = bookmarkListResponse3.results.getObjects().get(0);

        // Assertions
        // ***********************************************

        // Verify finishedWatching = true
        assertThat(bookmark3.getFinishedWatching()).isTrue();

    }
}
