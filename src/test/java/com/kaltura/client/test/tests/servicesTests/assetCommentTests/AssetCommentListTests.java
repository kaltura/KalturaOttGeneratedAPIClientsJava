package com.kaltura.client.test.tests.servicesTests.assetCommentTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetCommentOrderBy;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.test.servicesImpl.AssetCommentServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetCommentUtils;
import com.kaltura.client.types.AssetComment;
import com.kaltura.client.types.AssetCommentFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssetCommentListTests extends BaseTest {

    private Client client;
    private String writer = "Shmulik";
    private Long createDate = 0L;
    private String header = "header";
    private String subHeader = "subHeader";
    private String text = "A lot of text";

    @BeforeClass
    private void add_tests_before_class() {
        BaseTest.getSharedHousehold();
        client = getClient(getsharedUserKs());
    }

    @Description("AssetComment/action/list - check order by functionality")
    @Test

    private void checkCommentsOrder() {

        Long assetId = BaseTest.getSharedMediaAsset().getId();

        // Initialize assetComment object
        AssetComment assetComment = AssetCommentUtils.assetComment(Math.toIntExact(assetId), AssetType.MEDIA, writer, text, createDate, subHeader, header);

        // AssetComment/action/add - first comment
        Response<AssetComment> assetComment1Response = AssetCommentServiceImpl.add(client, assetComment);

        // AssetComment/action/add - second comment comment
        Response<AssetComment> assetComment2Response = AssetCommentServiceImpl.add(client, assetComment);

        //Initialize assetCommentFilter object
        AssetCommentFilter assetCommentFilter = AssetCommentUtils.assetCommentFilter(Math.toIntExact(assetId), AssetType.MEDIA,
                AssetCommentOrderBy.CREATE_DATE_DESC);

        //AssetComment/action/list - return both comments
        Response<ListResponse<AssetComment>> assetCommentListResponse = AssetCommentServiceImpl.list(client, assetCommentFilter, null);

        AssetComment assetCommentObjectResponse = assetCommentListResponse.results.getObjects().get(0);
        AssetComment assetComment2ObjectResponse = assetCommentListResponse.results.getObjects().get(1);

        // Assert that total count = 2 (two comments added)
        assertThat(assetCommentListResponse.results.getTotalCount()).isEqualTo(2);

        // Assert that second comment return first because order by = CREATE_DATE_DESC (newest comment return first)
        assertThat(assetCommentObjectResponse.getAssetId()).isEqualTo(assetComment2Response.results.getAssetId());
        assertThat(assetComment2ObjectResponse.getAssetId()).isEqualTo(assetComment1Response.results.getAssetId());
    }
}
