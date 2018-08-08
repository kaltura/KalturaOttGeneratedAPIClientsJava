package com.kaltura.client.test.tests.servicesTests.AssetTests;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.KsqlBuilder;

import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static com.kaltura.client.services.AssetService.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestEpgUtils.*;
import static com.kaltura.client.test.tests.enums.KsqlKey.EPG_ID;
import static com.kaltura.client.test.tests.enums.KsqlKey.MEDIA_ID;
import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetCountTests extends BaseTest {

    private ProgramAsset program, program2;
    private MediaAsset asset, asset2;

    private final String metaName = "synopsis";
    private final String metaValue1 = metaName + getRandomValue("_");
    private final String tagName = "Studio";
    private final String tagValue = tagName +  getRandomValue("_");
    private final String epgMetaName = "Country";
    private final String epgMetaValue = epgMetaName + getRandomValue("_");

    @BeforeClass
    private void asset_count_before_class() {

        HashMap<String, String> stringMetaMap1 = new HashMap<>();
        stringMetaMap1.put(metaName, metaValue1);
        stringMetaMap1.put(tagName,tagValue);

        // ingest asset 1
        IngestVodUtils.VodData vodData1 = new IngestVodUtils.VodData()
                .mediaType(MOVIE)
                .strings(stringMetaMap1);
        asset = insertVod(vodData1);

        // ingest asset 2
        IngestVodUtils.VodData vodData2 = new IngestVodUtils.VodData()
                .mediaType(MOVIE)
                .strings(stringMetaMap1);

        asset2 = insertVod(vodData2);

        HashMap<String, String> metas = new HashMap<>();
        metas.put(epgMetaName, epgMetaValue);
        
        // ingest epg programs
        EpgData epgData = new EpgData(getSharedEpgChannelName());
        epgData.seasonsNum(1);
        epgData.episodesNum(2);
        epgData.metas(metas);

        List<ProgramAsset> programsList = IngestEpgUtils.insertEpg(epgData);
        program = programsList.get(0);
        program2 = programsList.get(1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Ingest 2 VOD assets with the same meta (synopsis) value and pass the meta name in the count request")
    @Test
    private void groupByVodMeta() {
        String query = new KsqlBuilder()
                .openOr()
                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset.getId()))
                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset2.getId()))
                .closeOr()
                .toString();

        SearchAssetFilter searchAssetFilter = new SearchAssetFilter();
        searchAssetFilter.setKSql(query);
        searchAssetFilter.setTypeIn(String.valueOf(DBUtils.getMediaTypeId(MediaType.MOVIE)));

        ArrayList<AssetGroupBy> arrayList = new ArrayList<>();
        AssetMetaOrTagGroupBy assetMetaOrTagGroupBy = new AssetMetaOrTagGroupBy();
        assetMetaOrTagGroupBy.setValue(metaName);
        arrayList.add(assetMetaOrTagGroupBy);

        searchAssetFilter.setGroupBy(arrayList);
        AssetService.CountAssetBuilder countAssetBuilder = AssetService.count(searchAssetFilter)
                .setKs(BaseTest.getAnonymousKs());

        // asset/action/count
        Response<AssetCount> assetCountResponse = executor.executeSync(countAssetBuilder);
        assertThat(assetCountResponse.results.getCount()).isEqualTo(2);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Ingest 2 VOD assets with the same tag (Genre) value and pass the tag name in the count request")
    @Test
    private void groupByVodTag() {
        String query = new KsqlBuilder()
                .openOr()
                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset.getId()))
                .equal(MEDIA_ID.getValue(), Math.toIntExact(asset2.getId()))
                .closeOr()
                .toString();

        SearchAssetFilter searchAssetFilter = new SearchAssetFilter();
        searchAssetFilter.setKSql(query);
        searchAssetFilter.setTypeIn(String.valueOf(DBUtils.getMediaTypeId(MediaType.MOVIE)));

        ArrayList<AssetGroupBy> arrayList = new ArrayList<>();
        AssetMetaOrTagGroupBy assetMetaOrTagGroupBy = new AssetMetaOrTagGroupBy();
        assetMetaOrTagGroupBy.setValue(tagName);
        arrayList.add(assetMetaOrTagGroupBy);

        searchAssetFilter.setGroupBy(arrayList);
        AssetService.CountAssetBuilder countAssetBuilder = AssetService.count(searchAssetFilter)
                .setKs(BaseTest.getAnonymousKs());

        // asset/action/count
        Response<AssetCount> assetCountResponse = executor.executeSync(countAssetBuilder);
        assertThat(assetCountResponse.results.getCount()).isEqualTo(2);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("")
    @Test
    private void groupByEPGMeta() {
        String query = new KsqlBuilder()
                .openOr()
                .equal(EPG_ID.getValue(), String.valueOf(program.getId()))
                .equal(EPG_ID.getValue(),String.valueOf(program2.getId()))
                .closeOr()
                .toString();

        SearchAssetFilter searchAssetFilter = new SearchAssetFilter();
        searchAssetFilter.setKSql(query);
        searchAssetFilter.setTypeIn("0");

        ArrayList<AssetGroupBy> arrayList = new ArrayList<>();
        AssetMetaOrTagGroupBy assetMetaOrTagGroupBy = new AssetMetaOrTagGroupBy();
        assetMetaOrTagGroupBy.setValue(epgMetaName);
        arrayList.add(assetMetaOrTagGroupBy);

        searchAssetFilter.setGroupBy(arrayList);
        CountAssetBuilder countAssetBuilder = AssetService.count(searchAssetFilter)
                .setKs(BaseTest.getAnonymousKs());

        // asset/action/count
        Response<AssetCount> assetCountResponse = executor.executeSync(countAssetBuilder);
        assertThat(assetCountResponse.results.getCount()).isEqualTo(2);
    }
}
