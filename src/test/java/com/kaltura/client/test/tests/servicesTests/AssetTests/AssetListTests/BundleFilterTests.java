package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.enums.BundleType;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.KsqlBuilder;
import com.kaltura.client.test.utils.ingestUtils.IngestMppUtils;
import com.kaltura.client.types.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.services.ChannelService.add;
import static com.kaltura.client.test.Properties.DEFAULT_COLLECTION;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.tests.enums.KsqlKey.MEDIA_ID;
import static com.kaltura.client.test.tests.enums.MediaType.*;
import static com.kaltura.client.test.utils.AssetUtils.getAssets;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInLocalTime;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.MppData;
import static com.kaltura.client.test.utils.ingestUtils.IngestMppUtils.insertMpp;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BundleFilterTests extends BaseTest {
    private MediaAsset movie1, movie2;
    private MediaAsset series1, series2;
    private MediaAsset episode1, episode2;
    private DynamicChannel channel1, channel2;
    private Subscription subscription;


    @BeforeClass
    private void asset_list_bundleFilter_before_class() {
        // get movie
        List<MediaAsset> medias = getAssets(2, MOVIE);
        movie1 = medias.get(0);
        movie2 = medias.get(1);

        // get series
        List<MediaAsset> series = getAssets(2, SERIES);
        series1 = series.get(0);
        series2 = series.get(1);

        // get episode
        List<MediaAsset> episodes = getAssets(2, EPISODE);
        episode1 = episodes.get(0);
        episode2 = episodes.get(1);

        // add assets to channel query 1
        String channel1Query = new KsqlBuilder()
                .openOr()
                .equal(MEDIA_ID.getValue(), String.valueOf(movie1.getId()))
                .equal(MEDIA_ID.getValue(), String.valueOf(series1.getId()))
                .equal(MEDIA_ID.getValue(), String.valueOf(episode1.getId()))
                .closeOr()
                .toString();

        // add assets to channel query 2
        String channel2Query = new KsqlBuilder()
                .openOr()
                .equal(MEDIA_ID.getValue(), String.valueOf(movie2.getId()))
                .equal(MEDIA_ID.getValue(), String.valueOf(series2.getId()))
                .equal(MEDIA_ID.getValue(), String.valueOf(episode2.getId()))
                .closeOr()
                .toString();

        // add channel1
        channel1 = new DynamicChannel();
        channel1.setMultilingualName(setTranslationToken("channel_" + getEpochInLocalTime()));
        channel1.setMultilingualDescription(setTranslationToken("Description of " + channel1.getName()));
        channel1.setSystemName(channel1.getMultilingualName().get(0).getValue() + getRandomValue(""));
        channel1.setIsActive(true);
        channel1.setKSql(channel1Query);

        channel1 = (DynamicChannel) executor.executeSync(add(channel1)
                .setKs(getOperatorKs())
                .setLanguage("*"))
                .results;

        // add channel2
        channel2 = new DynamicChannel();
        channel2.setMultilingualName(setTranslationToken("channel_" + getEpochInLocalTime()));
        channel2.setMultilingualDescription(setTranslationToken("Description of " + channel2.getName()));
        channel2.setSystemName(channel2.getMultilingualName().get(0).getValue() + getRandomValue(""));
        channel2.setIsActive(true);
        channel2.setKSql(channel2Query);

        channel2 = (DynamicChannel) executor.executeSync(add(channel2)
                .setKs(getOperatorKs())
                .setLanguage("*"))
                .results;

        // ingest subscription with 2 new channels
        MppData mppData = new MppData()
                .channel1(channel1.getMultilingualName().get(0).getValue())
                .channel2(channel2.getMultilingualName().get(0).getValue());
        subscription = insertMpp(mppData);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - bundleFilter - idEqual - subscription")
    @Test
    private void list_assets_with_bundleFilter_subscription_by_idEqual() {
        // set bundleFilter
        BundleFilter filter = new BundleFilter();
        filter.setIdEqual(Integer.valueOf(subscription.getId()));
        filter.setOrderBy(AssetOrderBy.NAME_ASC.getValue());

        // get list
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(6);

        assertThat(assetListResponse.getObjects()).extracting("id")
                .containsExactlyInAnyOrder(movie1.getId(), episode1.getId(), series1.getId(), movie2.getId(),
                        episode2.getId(), series2.getId());

        assertThat(assetListResponse.getObjects()).extracting("name")
                .isSorted();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - bundleFilter - idEqual and typeIn - subscription")
    @Test
    private void list_assets_with_bundleFilter_subscription_by_idEqual_and_typeIn() {
        // adding "MOVIE", "SERIES" and "LINEAR" to string types (excluding "EPISODE")
        String types = getConcatenatedString(String.valueOf(getMediaTypeId(MOVIE)),
                String.valueOf(getMediaTypeId(SERIES)),
                String.valueOf(getMediaTypeId(LINEAR)));

        // set bundleFilter
        BundleFilter filter = new BundleFilter();
        filter.setBundleTypeEqual(BundleType.SUBSCRIPTION);
        filter.setIdEqual(Integer.valueOf(subscription.getId()));
        filter.setTypeIn(types);

        // get list
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(4);

        assertThat(assetListResponse.getObjects()).extracting("id")
                .containsExactlyInAnyOrder(movie1.getId(), movie2.getId(), series1.getId(), series2.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - bundleFilter - idEqual and typeIn - collection")
    @Test
    private void list_assets_with_bundleFilter_collection_by_idEqual_and_typeIn() {
        // set bundleFilter
        BundleFilter filter = new BundleFilter();
        filter.setBundleTypeEqual(BundleType.COLLECTION);
        filter.setIdEqual(Integer.valueOf(getProperty(DEFAULT_COLLECTION)));

        // get full list
        List<Asset> assets = executor.executeSync(list(filter)
                .setKs(getAnonymousKs()))
                .results
                .getObjects();

        Asset movie = null;
        for (Asset asset : assets) {
           if (asset.getType() == getMediaTypeId(MOVIE)) {
               movie = asset;
               break;
           }
        }

        if (movie == null){
            fail("Please add movie asset into the channel related to the provided collection");
        }

        // get movies list
        filter.setTypeIn(String.valueOf(getMediaTypeId(MOVIE)));
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(1);

        assertThat(assetListResponse.getObjects()).extracting("id")
                .containsExactly(movie.getId());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - bundleFilter - subscription - idEqual and collection bundleType")
    @Test
    private void list_assets_with_bundleFilter_by_subscription_idEqual_and_collection_bundleType() {
        // set bundleFilter
        BundleFilter filter = new BundleFilter();
        filter.setBundleTypeEqual(BundleType.COLLECTION);
        filter.setIdEqual(Integer.valueOf(subscription.getId()));

        // get list
        ListResponse<Asset> assetListResponse = executor.executeSync(list(filter)
                .setKs(getAnonymousKs())).results;

        // assert response
        assertThat(assetListResponse.getTotalCount()).isEqualTo(0);
    }

    @AfterClass
    private void asset_list_bundleFilter_after_class() {
        // delete channels
        executor.executeSync(ChannelService.delete(Math.toIntExact(channel1.getId()))
                .setKs(getOperatorKs()));

        executor.executeSync(ChannelService.delete(Math.toIntExact(channel2.getId()))
                .setKs(getOperatorKs()));

        // delete subscription
        IngestMppUtils.deleteMpp(subscription.getName());
    }
}
