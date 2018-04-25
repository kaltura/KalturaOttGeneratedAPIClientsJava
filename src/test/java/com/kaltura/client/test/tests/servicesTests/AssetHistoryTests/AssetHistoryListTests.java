package com.kaltura.client.test.tests.servicesTests.AssetHistoryTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.WatchStatus;
import com.kaltura.client.test.servicesImpl.AssetHistoryServiceImpl;
import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.*;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.Properties.EPISODE_MEDIA_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetHistoryListTests extends BaseTest {
    private Client client;
    private long movieAssetId;
    private int movieFileId;
    private long episodeAssetId;
    private int episodeFileId;
    private AssetType assetType;
    private BookmarkActionType actionType;

    // instantiate Bookmark object
    private Bookmark bookmark = new Bookmark();

    @BeforeClass
    private void add_tests_before_class() {
        client = getClient(getsharedMasterUserKs());
    }

    @Description("/AssetHistory/action/list - with no filter")
    @Test
    private void vodAssetHistory() {

        // Ingest movie vod asset
        movieAssetId = BaseTest.getSharedMediaAsset().getId();
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movieAssetId)).get(0);

        // Ingest episode vod asset
        MediaAsset mediaAsset = IngestVODUtils.ingestVOD(Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(String.valueOf(EPISODE_MEDIA_TYPE)), Optional.empty(), Optional.empty());
        episodeAssetId = mediaAsset.getId();
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episodeAssetId)).get(0);

        // Movie asset bookmark
        int position1 = 10;
        actionType = BookmarkActionType.FIRST_PLAY;
        assetType = AssetType.MEDIA;
        bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movieAssetId), movieFileId, assetType, actionType);
        //bookmark/action/add - Movie asset
        BookmarkServiceImpl.add(client, bookmark);

        // Episode asset bookmark
        int position2 = 20;
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episodeAssetId), episodeFileId, assetType, actionType);
        //bookmark/action/add - Episode asset
        BookmarkServiceImpl.add(client, bookmark);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(null, null, WatchStatus.ALL, null);

        //assetHistory/action/list - both assets (episode and movie) should returned
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);
        // First object
        AssetHistory assetHistoryObject1 = assetHistoryListResponse.results.getObjects().get(0);
        // Second object
        AssetHistory assetHistoryObject2 = assetHistoryListResponse.results.getObjects().get(1);

        // Assertions for first object returned (movie asset)
        assertThat(assetHistoryObject1.getAssetId()).isEqualTo(movieAssetId);
        assertThat(assetHistoryObject1.getAssetType()).isEqualTo(assetType);
        assertThat(assetHistoryObject1.getPosition()).isEqualTo(position1);
        assertThat(assetHistoryObject1.getDuration()).isGreaterThan(0);
        // Verify that flag is set to false (user hasn't finish watching the asset)
        assertThat(assetHistoryObject1.getFinishedWatching()).isFalse();
        assertThat(assetHistoryObject1.getWatchedDate()).isLessThanOrEqualTo(BaseUtils.getTimeInEpoch(0));

        // Assertions for second object returned (episode asset)
        assertThat(assetHistoryObject2.getAssetId()).isEqualTo(episodeAssetId);
        assertThat(assetHistoryObject2.getAssetType()).isEqualTo(assetType);
        assertThat(assetHistoryObject2.getPosition()).isEqualTo(position2);

        // Assert total count = 2 (two bookmarks)
        assertThat(assetHistoryListResponse.results.getTotalCount()).isEqualTo(2);


    }

    @Description("/AssetHistory/action/list -filtered by movie type id")
    @Test
    private void vodAssetHistoryFiltered() {

        // Ingest movie vod asset
        movieAssetId = BaseTest.getSharedMediaAsset().getId();
        movieFileId = AssetUtils.getAssetFileIds(String.valueOf(movieAssetId)).get(0);

        // Ingest episode vod asset
        MediaAsset mediaAsset = IngestVODUtils.ingestVOD(Optional.empty(), true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(String.valueOf(EPISODE_MEDIA_TYPE)), Optional.empty(), Optional.empty());
        episodeAssetId = mediaAsset.getId();
        episodeFileId = AssetUtils.getAssetFileIds(String.valueOf(episodeAssetId)).get(0);

        // Movie asset bookmark
        int position1 = 10;
        actionType = BookmarkActionType.FIRST_PLAY;
        assetType = AssetType.MEDIA;
        bookmark = BookmarkUtils.addBookmark(position1, String.valueOf(movieAssetId), movieFileId, assetType, actionType);
        //bookmark/action/add - Movie asset
        BookmarkServiceImpl.add(client, bookmark);

        // Episode asset bookmark
        int position2 = 20;
        bookmark = BookmarkUtils.addBookmark(position2, String.valueOf(episodeAssetId), episodeFileId, assetType, actionType);
        //bookmark/action/add - Episode asset
        BookmarkServiceImpl.add(client, bookmark);

        AssetHistoryFilter assetHistoryFilter = AssetHistoryUtils.getAssetHistoryFilter(String.valueOf(movieAssetId), null, WatchStatus.ALL, null);

        //assetHistory/action/list - 1111
        Response<ListResponse<AssetHistory>> assetHistoryListResponse = AssetHistoryServiceImpl.list(client, assetHistoryFilter, null);

    }

}
