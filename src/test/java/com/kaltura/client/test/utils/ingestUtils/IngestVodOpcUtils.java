package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.tests.enums.IngestAction;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.*;

import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.tests.BaseTest.executor;
import static com.kaltura.client.test.tests.BaseTest.getAnonymousKs;
import static com.kaltura.client.test.tests.enums.IngestAction.INSERT;
import static com.kaltura.client.test.tests.enums.IngestAction.UPDATE;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.VodFile;

public class IngestVodOpcUtils extends BaseIngestUtils {

    private static String stringMetaValue;
    private static String dateMetaValue;
    private static double numberMetaValue;
    private static boolean booleanMetaValue;
    private static List<String> tagMetaValue;

    // TODO: how to get these data from DB or request?
    // Movie fields
    public static final String mediaTextFieldName = "BoxOffice";
    public static final String mediaDateFieldName = "ReleaseDate";
    public static final String mediaNumberFieldName = "Runtime2";
    public static final String mediaBooleanFieldName = "IsAgeLimited";
    public static final String mediaTagFieldName = "Actors";

    // Episode fields
    public static final String episodeTextFieldName = "TwitterHashtag";
    public static final String episodeDateFieldName = "Date";
    public static final String episodeNumberFieldName = "CommonIpAddress";
    public static final String episodeBooleanFieldName = "CyyNCAh";
    public static final String episodeTagFieldName = "Studio";

    // Series fields
    public static final String seriesTextFieldName = "SeriesID";
    public static final String seriesDateFieldName = "DateField";
    public static final String seriesNumberFieldName = "ReleaseYear";
    public static final String seriesBooleanFieldName = "IsWestern";
    public static final String seriesTagFieldName = "Studio";

    // fields & values
    private static HashMap<String, String> stringMetaMap = new HashMap<>();
    private static HashMap<String, Double> numberMetaMap = new HashMap<>();
    private static HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    private static HashMap<String, String> datesMetaMap = new HashMap<>();
    private static HashMap<String, List<String>> tagsMetaMap = new HashMap<>();

    private static String tagValue1 = "Jack Nicholson";
    private static String tagValue2 = "Natalie Portman";

    public static VodData getVodData(MediaType mediaType, IngestAction action) {
        if (action == INSERT) {
            initDefaultValues4Insert(mediaType);
        } else if (action == UPDATE) {
            initDefaultValues4Update(booleanMetaValue, mediaType);
        }

        VodData data = new VodData()
                .setDefaultValues()
                .stringsMeta(stringMetaMap)
                .booleansMeta(booleanHashMap)
                .numbersMeta(numberMetaMap)
                .datesMeta(datesMetaMap)
                .tags(tagsMetaMap);

        switch (mediaType) {
            case MOVIE:
                data.mediaType(MediaType.MOVIE).isVirtual(false);
                return data;
            case EPISODE:
                data.mediaType(MediaType.EPISODE).isVirtual(false);
                return data;
            case SERIES:
                data.mediaType(MediaType.SERIES).isVirtual(true);
                return data;
            default:
                return null;
        }
    }

    private static void initDefaultValues4Update(boolean previousValue, MediaType mediaType) {
        stringMetaValue = "stringMetaValue_" + getCurrentDateInFormat("MM/dd/yyyy") + "_updated";
        dateMetaValue = getOffsetDateInFormat(1, "MM/dd/yyyy");
        numberMetaValue = Math.round(getRandomDouble() * 100.0) / 100.0;
        booleanMetaValue = !previousValue;
        tagMetaValue = Arrays.asList(tagValue1 + "_updated", tagValue2 + "_updated", stringMetaValue + "_updated");

        fillMapsWithData(mediaType);
    }

    private static void initDefaultValues4Insert(MediaType mediaType) {
        stringMetaValue = "stringMetaValue_" + getCurrentDateInFormat("MM/dd/yyyy");
        dateMetaValue = getCurrentDateInFormat("MM/dd/yyyy");
        numberMetaValue = Math.round(getRandomDouble() * 100.0) / 100.0;
        booleanMetaValue = getRandomBoolean();
        tagMetaValue = Arrays.asList(tagValue1, tagValue2, stringMetaValue);

        fillMapsWithData(mediaType);
    }

    private static void fillMapsWithData(MediaType mediaType) {
        stringMetaMap = new HashMap<>();
        numberMetaMap = new HashMap<>();
        booleanHashMap = new HashMap<>();
        datesMetaMap = new HashMap<>();
        tagsMetaMap = new HashMap<>();

        switch (mediaType) {
            case MOVIE:
                stringMetaMap.put(mediaTextFieldName, stringMetaValue);
                numberMetaMap.put(mediaNumberFieldName, numberMetaValue);
                datesMetaMap.put(mediaDateFieldName, dateMetaValue);
                booleanHashMap.put(mediaBooleanFieldName, booleanMetaValue);
                tagsMetaMap.put(mediaTagFieldName, tagMetaValue);
                break;

            case EPISODE:
                stringMetaMap.put(episodeTextFieldName, stringMetaValue);
                numberMetaMap.put(episodeNumberFieldName, numberMetaValue);
                datesMetaMap.put(episodeDateFieldName, dateMetaValue);
                booleanHashMap.put(episodeBooleanFieldName, booleanMetaValue);
                tagsMetaMap.put(episodeTagFieldName, tagMetaValue);
                break;

            case SERIES:
                stringMetaMap.put(seriesTextFieldName, stringMetaValue);
                numberMetaMap.put(seriesNumberFieldName, numberMetaValue);
                datesMetaMap.put(seriesDateFieldName, dateMetaValue);
                booleanHashMap.put(seriesBooleanFieldName, booleanMetaValue);
                tagsMetaMap.put(seriesTagFieldName, tagMetaValue);
                break;

            default:
                break;
        }
    }

    public static String getCoguidOfActiveMediaAsset(int assetType) {
        SearchAssetFilter assetFilter = new SearchAssetFilter();
        assetFilter.setOrderBy(AssetOrderBy.CREATE_DATE_DESC.getValue());
        assetFilter.setTypeIn(String.valueOf(assetType));
        FilterPager pager = new FilterPager();
        pager.setPageSize(1);
        pager.setPageIndex(1);

        Response<ListResponse<Asset>> assetListResponse = executor.executeSync(list(assetFilter, pager)
                        .setKs(getAnonymousKs()));

        return assetListResponse.results.getObjects().get(0).getExternalId();
    }

    public static List<VodFile> get2AssetFiles(String fileType1, String fileType2, String ppvs1, String ppvs2) {
        List<VodFile> result = new ArrayList<>();

        long e = getEpoch();
        String r = String.valueOf(BaseUtils.getRandomLong());

        String coguid1 = "file_1_" + e + "_" + r;
        String coguid2 = "file_2_" + e + "_" + r;

        VodFile file1 = new VodFile(fileType1, ppvs1).coguid(coguid1);
        VodFile file2 = new VodFile(fileType2, ppvs2).coguid(coguid2);

        result.add(file1);
        result.add(file2);

        return result;
    }

    public static boolean isTagValueFound(String value2Found, Asset asset) {
        Map<String, MultilingualStringValueArray> tags = asset.getTags();
        Map.Entry<String, MultilingualStringValueArray> entry = tags.entrySet().iterator().next();
        List<MultilingualStringValue> tagsValues = entry.getValue().getObjects();
        for (MultilingualStringValue tagValue: tagsValues) {
            if (value2Found.equals(tagValue.getValue())) {
                return true;
            }
        }
        return false;
    }
}
