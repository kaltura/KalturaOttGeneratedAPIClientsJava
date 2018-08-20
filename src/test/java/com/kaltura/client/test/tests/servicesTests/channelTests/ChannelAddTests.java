package com.kaltura.client.test.tests.servicesTests.channelTests;

import com.kaltura.client.enums.ChannelOrderBy;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.AssetUtils;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.ChannelUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.ChannelService.add;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.BaseUtils.getEpoch;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMediaTypeId;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.VodData;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

public class ChannelAddTests extends BaseTest {

    private DynamicChannel channel;
//    private String channelName;
//    private String description;
    private Boolean isActive = true;
    private IntegerValue integerValue = new IntegerValue();


    @BeforeClass
    private void channel_addTests_before_class() {
        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("channel/action/add - with all asset types")
    @Test
    private void addChannel() {
        String ksqlExpression = "name ~ 'movie'";
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results.getName()).isEqualTo(channelName);

        // cleanup - delete channel
        executor.executeSync(ChannelService.delete(Math.toIntExact(channelResponse.results.getId()))
                .setKs(getManagerKs()));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("channel/action/add - with specific asset type")
    @Test
    private void addChannelWithAssetType() {
        String ksqlExpression = "name ~ 'movie'";

        int mediaTypeId = getMediaTypeId(MediaType.MOVIE);
        integerValue.setValue(mediaTypeId);
        List<IntegerValue> assetTypes = new ArrayList<>();
        assetTypes.add(integerValue);

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, assetTypes);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results.getName()).isEqualTo(channelName);

        // cleanup - delete channel
        executor.executeSync(ChannelService.delete(Math.toIntExact(channelResponse.results.getId()))
                .setKs(getManagerKs()));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("channel/action/add - with not supported opc partner id")
    @Test(enabled = false) // no validation in channel/action/add for account 203
    private void addDynamicChannelWithNotSupportedOpcPartnerId() {
        String ksqlExpression = "name ~ 'movie'";

        int mediaTypeId = getMediaTypeId(MediaType.MOVIE);
        integerValue.setValue(mediaTypeId);
        List<IntegerValue> assetTypes = new ArrayList<>();
        assetTypes.add(integerValue);

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, assetTypes);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4074).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("channel/action/add - order by NAME_DESC")
    @Test
    private void checkOrderOfAssetsInChannel() {
        String asset1Name = "Movie_" + BaseUtils.getCurrentDateInFormat("yyMMddHHmmss");
        String asset2Name = "Episode_" + BaseUtils.getCurrentDateInFormat("yyMMddHHmmss");

        // Ingest first asset
        VodData vodData = new VodData()
                .name(asset1Name)
                .mediaType(MediaType.MOVIE);
        MediaAsset movieAsset = insertVod(vodData);

        // Ingest second asset
        VodData vodData1 = new VodData()
                .name(asset2Name)
                .mediaType(MediaType.EPISODE);
        MediaAsset episodeAsset = insertVod(vodData1);

        String ksqlExpression = "(or name = '" + movieAsset.getName() + "' name = '" + episodeAsset.getName() + "')";
        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.NAME_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        assertThat(channelResponse.results.getMultilingualName().get(0).getValue()).isEqualTo(channelName);
        int channelId = Math.toIntExact(channelResponse.results.getId());


        // asset/action/list
        ChannelFilter channelFilter = AssetUtils.getChannelFilter(channelId, Optional.empty(), Optional.empty(), Optional.empty());

        Response<ListResponse<Asset>> listResponse = executor.executeSync(AssetService.list(channelFilter)
                .setKs(getManagerKs()));

        assertThat(listResponse.results.getTotalCount()).isEqualTo(2);
        // Verify movie asset id returned first (because order is by name_desc)
        assertThat(listResponse.results.getObjects().get(0).getId()).isEqualTo(movieAsset.getId());

        // cleanup - delete channel
        executor.executeSync(ChannelService.delete(channelId)
                .setKs(getManagerKs()));
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("channel/action/add - with invalid asset type - error 4028")
    @Test(enabled = false) // no validation in channel/action/add for account 203
    private void addChannelWithInvalidAssetType() {
        String ksqlExpression = "name ~ 'movie'";

        int invalidAssetType = 1;
        integerValue.setValue(invalidAssetType);
        List<IntegerValue> assetTypes = new ArrayList<>();
        assetTypes.add(integerValue);

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, assetTypes);

        // channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"4028"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4028).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("channel/action/add - mandatory channel multilingualName not provided")
    @Test
    private void addChannelWithoutMultilingualName() {
        String ksqlExpression = "name ~ 'movie'";

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        DynamicChannel channel = new DynamicChannel();
        channel.setIsActive(true);
        channel.setOrderBy(channelOrder);
        channel.setSystemName("systemName " + getEpoch());

        //channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"50027","message":"Argument [name] cannot be empty"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(50027).getCode());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("channel/action/add - syntax error in filter expression")
    @Test
    private void addChannelWithSyntaxErrorInFilterExpression() {
        String ksqlExpression = "name = 'syntax error";

        ChannelOrder channelOrder = new ChannelOrder();
        channelOrder.setOrderBy(ChannelOrderBy.LIKES_DESC);

        String channelName = "Channel_" + getEpoch();
        String description = "description of " + channelName;
        channel = ChannelUtils.addDynamicChannel(channelName, description, isActive, ksqlExpression, channelOrder, null);

        //channel/action/add
        Response<Channel> channelResponse = executor.executeSync(add(channel)
                .setKs(getManagerKs())
                .setLanguage("*"));

        // KalturaAPIException","code":"4004","message":"Invalid expression structure"
        assertThat(channelResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4004).getCode());
    }

    @AfterClass
    private void channel_addTests_after_class() {

    }

}
