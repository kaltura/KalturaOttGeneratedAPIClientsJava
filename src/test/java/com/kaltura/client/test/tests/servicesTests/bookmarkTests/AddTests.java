package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
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

    // TODO: 09/04/2018  - once implemented use asset from ingest and not hardcoded
    private long assetId;
    private AssetType type;
    private int fileId;
    private BookmarkActionType actionType;

    @BeforeClass
    private void add_tests_before_class() {
        assetId = mediaAsset.getId();
        type = AssetType.get("MEDIA");
        AssetReferenceType assetReferenceType = AssetReferenceType.get("MEDIA");
        Response<Asset> assetResponse = AssetServiceImpl.get(sharedMasterUserKs,String.valueOf(assetId),assetReferenceType);
        fileId = assetResponse.results.getMediaFiles().get(0).getId();
        actionType = BookmarkActionType.get("FIRST_PLAY");
    }

    @Description("bookmark/action/add - add")
    @Test
    private void add() {

        Bookmark bookmark = new Bookmark();
        bookmark.setType(type);
        bookmark.setId(String.valueOf(assetId));
        bookmark.setPosition(0);

        BookmarkPlayerData playerData = new BookmarkPlayerData();
        playerData.setAction(actionType);
        playerData.setAverageBitrate(0);
        playerData.setTotalBitrate(0);
        playerData.setCurrentBitrate(0);

        playerData.setFileId((long) fileId);

        bookmark.setPlayerData(playerData);

        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark);
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

        BookmarkFilter bookmarkFilter = new BookmarkFilter();
        bookmarkFilter.setAssetIdIn(String.valueOf(assetId));
        bookmarkFilter.setOrderBy("POSITION_ASC");
        bookmarkFilter.setAssetTypeEqual(type);

        Response<ListResponse<Bookmark>> listResponse = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);
        
        String test123 = listResponse.results.getObjects().get(0).getId();
        assertThat (test123.equals((String.valueOf(fileId))));

    }

}
