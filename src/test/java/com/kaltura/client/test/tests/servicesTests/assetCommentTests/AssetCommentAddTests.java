package com.kaltura.client.test.tests.servicesTests.assetCommentTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.test.servicesImpl.AssetCommentServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.AssetCommentUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.IngestEPGUtils;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.AssetComment;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssetCommentAddTests extends BaseTest {
    private Client client;
    String writer = "Shmulik";
    Long createDate = 0L;
    String header = "header";
    String subHeader = "subHeader";
    String text = "A lot of text";

    @BeforeClass
    private void add_tests_before_class() {
        BaseUtils.getSharedHousehold();
        client = getClient(sharedMasterUserKs);
    }

    @Description
    @Test
    private void addCommentForVod() {

        Long assetId = 608772L;
        AssetComment assetComment = AssetCommentUtils.addAssetComment(Math.toIntExact(assetId), AssetType.MEDIA, writer, text, createDate, subHeader, header);
        Response<AssetComment> assetCommentResponse = AssetCommentServiceImpl.add(client, assetComment);
        assertThat(assetCommentResponse.results.getId()).isGreaterThan(0);
        assertThat(assetCommentResponse.results.getAssetId()).isEqualTo(Math.toIntExact(assetId));
        assertThat(assetCommentResponse.results.getAssetType()).isEqualTo(AssetType.MEDIA);
        assertThat(assetCommentResponse.results.getWriter()).isEqualTo(writer);
        assertThat(assetCommentResponse.results.getText()).isEqualTo(text);
        assertThat(assetCommentResponse.results.getSubHeader()).isEqualTo(subHeader);
        assertThat(assetCommentResponse.results.getHeader()).isEqualTo(header);
        assertThat(assetCommentResponse.results.getCreateDate()).isGreaterThanOrEqualTo(BaseUtils.getTimeInEpoch(0));
    }

    @Description
    @Test
    private void addCommentForEPGProgram() {
        Response<ListResponse<Asset>> epgProgram = IngestEPGUtils.ingestEPG("Shmulik_Series_1", Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        //Long epgProgramId = epgProgram.results.getObjects().get(0).getId();
//        AssetComment assetComment = AssetCommentUtils.addAssetComment(Math.toIntExact(epgProgramId), AssetType.EPG, writer, text, createDate, subHeader, header);
//        Response<AssetComment> assetCommentResponse = AssetCommentServiceImpl.add(client, assetComment);
    }
}
