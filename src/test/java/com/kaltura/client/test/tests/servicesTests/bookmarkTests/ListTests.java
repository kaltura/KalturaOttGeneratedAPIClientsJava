package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.BookmarkOrderBy;
import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;

public class ListTests extends BaseTest {

    private String assetId;
    private int fileId;


    private String assetId2;
    private int fileId2;

    private List <String> assetList = new ArrayList<>();

    @BeforeClass
    private void list_tests_before_class() {
        assetId = "606283";
        List<Integer> assetFileIds = AssetUtils.getAssetFileIds(assetId);
        fileId = assetFileIds.get(0);
        assetList.add(assetId);

        assetId2 = "606282";
        List<Integer> asset2FileIds = AssetUtils.getAssetFileIds(assetId2);
        fileId2 = asset2FileIds.get(0);
        assetList.add(assetId2);
    }

    @Description("bookmark/action/list - order by")
    @Test

    private void BookmarkOrderBy() {

        // Bookmark asset1
        Bookmark bookmark = BookmarkUtils.addBookmark(0, assetId, fileId, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark);
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // Bookmark asset2
        Bookmark bookmark2 = BookmarkUtils.addBookmark(10, assetId2, fileId2, AssetType.MEDIA, BookmarkActionType.FIRST_PLAY);
        Response<Boolean> booleanResponse2 = BookmarkServiceImpl.add(sharedMasterUserKs, bookmark2);
        assertThat(booleanResponse2.results.booleanValue()).isTrue();


        BookmarkFilter bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_DESC,AssetType.MEDIA, assetList);
        Response<ListResponse<Bookmark>> bookmarkListResponse = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);

        Bookmark bookmarkObject = bookmarkListResponse.results.getObjects().get(0);
        Bookmark bookmarkObject2 = bookmarkListResponse.results.getObjects().get(1);

        // Assertions
        // *************************************

        // Verify that asset2 returned first (bookmark/action/list is response is ordered by POSITION DESC)
        assertThat( bookmarkObject.getId()).isEqualTo(String.valueOf(assetId2));
        assertThat( bookmarkObject2.getId()).isEqualTo(String.valueOf(assetId));

        bookmarkFilter = BookmarkUtils.listBookmark(BookmarkOrderBy.POSITION_ASC,AssetType.MEDIA, assetList);
        bookmarkListResponse = BookmarkServiceImpl.list(sharedMasterUserKs,bookmarkFilter);

        bookmarkObject = bookmarkListResponse.results.getObjects().get(0);
        bookmarkObject2 = bookmarkListResponse.results.getObjects().get(1);

        // Assertions
        // *************************************

        // Verify that asset1 returned first (bookmark/action/list is response is ordered by POSITION DESC)
        assertThat( bookmarkObject.getId()).isEqualTo(String.valueOf(assetId));
        assertThat( bookmarkObject2.getId()).isEqualTo(String.valueOf(assetId2));
    }
}
