package com.kaltura.client.test.tests.servicesTests.AssetTests.AssetListTests;

import com.kaltura.client.enums.RecordingStatus;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.PremiumService;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static com.kaltura.client.services.HouseholdService.delete;
import static com.kaltura.client.services.RecordingService.add;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.HouseholdUtils.createHousehold;
import static com.kaltura.client.test.utils.PurchaseUtils.purchasePpv;
import static com.kaltura.client.test.utils.PurchaseUtils.purchaseSubscription;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getSubscriptionWithPremiumService;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.EpgData;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.insertEpg;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduledRecordingProgramFilterTests extends BaseTest {

    private int linearAssetId1, linearAssetId2;
    private JSONObject linearAssetJsonObject1, linearAssetJsonObject2;
    private String masterUserKs;
    private List<ProgramAsset> programAssets1, programAssets2;


    @BeforeClass
    private void asset_list_scheduledRecordingProgramFilter_before_class() {
        // create household
        Household household = createHousehold();
        String udid = HouseholdUtils.getDevicesList(household).get(0).getUdid();
        masterUserKs = HouseholdUtils.getHouseholdMasterUserKs(household, udid);

        // purchase subscription with npvr premium service
        Subscription subscription = getSubscriptionWithPremiumService(PremiumService.NPVR);
        purchaseSubscription(masterUserKs, Integer.parseInt(subscription.getId()), Optional.empty());

        // get linearAsset and epg channelId
        JSONArray jsonArray = DBUtils.getLinearAssetIdAndEpgChannelNameJsonArray();
        linearAssetJsonObject1 = jsonArray.getJSONObject(0);
        linearAssetJsonObject2 = jsonArray.getJSONObject(1);

        linearAssetId1 = linearAssetJsonObject1.getInt("media_id");
        linearAssetId2 = linearAssetJsonObject2.getInt("media_id");

        // purchase linearAsset
        purchasePpv(masterUserKs, Optional.of(linearAssetId1), Optional.empty(), Optional.empty());
        purchasePpv(masterUserKs, Optional.of(linearAssetId2), Optional.empty(), Optional.empty());

        // ingest epg's
        EpgData epgData1 = new EpgData(linearAssetJsonObject1.getString("name"))
                .startDate(getEpochInLocalTime(5));
        programAssets1 = insertEpg(epgData1);

//        String seriesId = String.valueOf(getEpochInLocalTime());
        EpgData epgData2 = new EpgData(linearAssetJsonObject2.getString("name"))
                .startDate(getEpochInLocalTime(360));
//                .seriesId(seriesId)
//                .seasonsNum(3);
        programAssets2 = insertEpg(epgData2);

        // add recordings 1
        Recording recording1 = new Recording();
        recording1.setAssetId(programAssets1.get(0).getId());

        Response<Recording> recordingResponse = executor.executeSync(add(recording1).setKs(masterUserKs));
        assertThat(recordingResponse.results.getStatus()).isEqualTo(RecordingStatus.SCHEDULED);

        // add recordings 2
        Recording recording2 = new Recording();
        recording2.setAssetId(programAssets2.get(0).getId());

        recordingResponse = executor.executeSync(add(recording2).setKs(masterUserKs));
        assertThat(recordingResponse.results.getStatus()).isEqualTo(RecordingStatus.SCHEDULED);

//        // add series recording
//        SeriesRecording seriesRecording = new SeriesRecording();
//        seriesRecording.setChannelId(linearAssetJsonObject2.getLong("id"));
//        seriesRecording.setSeasonNumber(1);
//        seriesRecording.setType(RecordingType.SERIES);
//        seriesRecording.setEpgId();
//
//        Response<SeriesRecording> seriesRecordingResponse = executor.executeSync(SeriesRecordingService.add(seriesRecording)
//                .setKs(masterUserKs));
////        assertThat(seriesRecordingResponse.results.getStatus()).isEqualTo(RecordingStatus.SCHEDULED);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - scheduledRecordingProgramFilter - channelsIn")
    @Test
    private void list_assets_with_channelFilter_by_channelId() {
        // set scheduledRecordingProgramFilter
        ScheduledRecordingProgramFilter filter = new ScheduledRecordingProgramFilter();
        String channelsIn = getConcatenatedString(String.valueOf(linearAssetJsonObject1.getInt("id")),
                String.valueOf(linearAssetJsonObject2.getInt("id")));
        filter.setChannelsIn(channelsIn);

        // get list
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(AssetService.list(filter)
                .setKs(masterUserKs));

        // assert response
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(2);

        assertThat(assetListResponse.results.getObjects()).extracting("id")
                .containsExactlyInAnyOrder(programAssets1.get(0).getId(), programAssets2.get(0).getId());

        assertThat(assetListResponse.results.getObjects()).extracting("name")
                .containsExactlyInAnyOrder(programAssets1.get(0).getName(), programAssets2.get(0).getName());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - scheduledRecordingProgramFilter - startDateGreaterThanOrNull")
    @Test
    private void list_assets_with_channelFilter_by_startDateGreaterThanOrNull() {
        // set scheduledRecordingProgramFilter
        String channelsIn = getConcatenatedString(String.valueOf(linearAssetJsonObject1.getInt("id")),
                String.valueOf(linearAssetJsonObject2.getInt("id")));

        ScheduledRecordingProgramFilter filter = new ScheduledRecordingProgramFilter();
        filter.setChannelsIn(channelsIn);
        filter.setStartDateGreaterThanOrNull(getEpochInUtcTime(180));

        // get list
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(AssetService.list(filter)
                .setKs(masterUserKs));

        // assert response
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(programAssets2.get(0).getId());
        assertThat(assetListResponse.results.getObjects().get(0).getName()).isEqualTo(programAssets2.get(0).getName());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - scheduledRecordingProgramFilter - setEndDateLessThanOrNull")
    @Test(enabled = true)
    private void list_assets_with_channelFilter_by_setEndDateLessThanOrNull() {
        // set scheduledRecordingProgramFilter
        String channelsIn = getConcatenatedString(String.valueOf(linearAssetJsonObject1.getInt("id")),
                String.valueOf(linearAssetJsonObject2.getInt("id")));

        ScheduledRecordingProgramFilter filter = new ScheduledRecordingProgramFilter();
        filter.setChannelsIn(channelsIn);
        filter.setEndDateLessThanOrNull(getEpochInUtcTime(60));

        // get list
        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(AssetService.list(filter)
                .setKs(masterUserKs));

        // assert response
        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(programAssets1.get(0).getId());
        assertThat(assetListResponse.results.getObjects().get(0).getName()).isEqualTo(programAssets1.get(0).getName());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("asset/action/list - scheduledRecordingProgramFilter - setRecordingTypeEqual")
    @Test(enabled = false)
    private void list_assets_with_channelFilter_by_setRecordingTypeEqual() {
//        // set scheduledRecordingProgramFilter
//        String channelsIn = getConcatenatedString(String.valueOf(linearAssetJsonObject1.getInt("id")),
//                String.valueOf(linearAssetJsonObject2.getInt("id")));
//
//        ScheduledRecordingProgramFilter filter = new ScheduledRecordingProgramFilter();
//        filter.setChannelsIn(channelsIn);
//        filter.setRecordingTypeEqual(ScheduledRecordingAssetType.SERIES);
//
//        // get list
//        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(AssetService.list(filter)
//                .setKs(masterUserKs));
//
//        // assert response
//        assertThat(assetListResponse.results.getTotalCount()).isEqualTo(1);
//        assertThat(assetListResponse.results.getObjects().get(0).getId()).isEqualTo(programAssets1.get(0).getId());
//        assertThat(assetListResponse.results.getObjects().get(0).getName()).isEqualTo(programAssets1.get(0).getName());
    }
    
    @AfterClass
    private void asset_list_scheduledRecordingProgramFilter_after_class() {
        // cleanup
        executor.executeSync(delete().setKs(masterUserKs));
    }
}
