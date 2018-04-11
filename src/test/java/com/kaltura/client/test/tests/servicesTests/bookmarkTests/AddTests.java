package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.Client;
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

    private Client client;
    private long assetId;
    private AssetType type;
    private int fileId;
    private BookmarkActionType actionType;
    private int position = 0;

    @BeforeClass
    private void add_tests_before_class() {
        client = getClient(sharedMasterUserKs);
        assetId = mediaAsset.getId();

        type = AssetType.get("MEDIA");
        AssetReferenceType assetReferenceType = AssetReferenceType.get("MEDIA");
        Response<Asset> assetResponse = AssetServiceImpl.get(client,String.valueOf(assetId),assetReferenceType);
        fileId = assetResponse.results.getMediaFiles().get(0).getId();
        actionType = BookmarkActionType.get("FIRST_PLAY");
    }

    @Description("bookmark/action/add - basic functionality")
    @Test
    private void add() {

        Bookmark bookmark = new Bookmark();
        bookmark.setType(type);
        bookmark.setId(String.valueOf(assetId));
        bookmark.setPosition(position);

        BookmarkPlayerData playerData = new BookmarkPlayerData();
        playerData.setAction(actionType);
        playerData.setAverageBitrate(0);
        playerData.setTotalBitrate(0);
        playerData.setCurrentBitrate(0);

        playerData.setFileId((long) fileId);

        bookmark.setPlayerData(playerData);

        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(client, bookmark);
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        BookmarkFilter bookmarkFilter = new BookmarkFilter();
        bookmarkFilter.setAssetIdIn(String.valueOf(assetId));
        bookmarkFilter.setOrderBy("POSITION_ASC");
        bookmarkFilter.setAssetTypeEqual(type);

        Response<ListResponse<Bookmark>> bookmarkListResponse = BookmarkServiceImpl.list(client, bookmarkFilter);
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
}
