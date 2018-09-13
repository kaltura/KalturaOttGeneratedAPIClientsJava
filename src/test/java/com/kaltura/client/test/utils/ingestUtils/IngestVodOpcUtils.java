package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.tests.enums.IngestAction;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.tests.BaseTest.executor;
import static com.kaltura.client.test.tests.BaseTest.getAnonymousKs;
import static com.kaltura.client.test.tests.enums.IngestAction.INSERT;
import static com.kaltura.client.test.tests.enums.IngestAction.UPDATE;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.VodFile;

public class IngestVodOpcUtils extends BaseIngestUtils {

    public static String name;
    public static String description;
    public static String textValue;
    public static String dateValue;
    public static double doubleValue;
    public static boolean booleanValue;
    public static List<String> tagValues;

    public static final String FILE_TYPE_1 = "Test130301";
    public static final String FILE_TYPE_2 = "new file type1";

    public static final String EMPTY_FILE_1_TAG = "<file PPV_MODULE=\"\" alt_cdn_code=\"\" assetDuration=\"\" billing_type=\"\" cdn_code=\"\" cdn_name=\"\" co_guid=\"\" handling_type=\"\" product_code=\"\" quality=\"\" type=\"" + FILE_TYPE_1 + "\"/>\n";
    public static final String EMPTY_FILE_2_TAG = "<file PPV_MODULE=\"\" alt_cdn_code=\"\" assetDuration=\"\" billing_type=\"\" cdn_code=\"\" cdn_name=\"\" co_guid=\"\" handling_type=\"\" product_code=\"\" quality=\"\" type=\"" + FILE_TYPE_2 + "\"/>\n";

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
    public static HashMap<String, String> stringMetaMap = new HashMap<>();
    public static HashMap<String, Double> numberMetaMap = new HashMap<>();
    public static HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    public static HashMap<String, String> datesMetaMap = new HashMap<>();
    public static HashMap<String, List<String>> tagsMetaMap = new HashMap<>();

    public static List<VodFile> movieAssetFiles = new ArrayList<>();
    public static List<VodFile> episodeAssetFiles = new ArrayList<>();
    public static List<VodFile> seriesAssetFiles = new ArrayList<>();

    public static String tagValue1 = "Jack Nicholson";
    public static String tagValue2 = "Natalie Portman";

    public static VodData getVodData(MediaType mediaType, List<VodFile> mediaAssetFiles, IngestAction action) {
        if (action == INSERT) {
            generateDefaultValues4Insert(mediaType);
        } else if (action == UPDATE) {
            generateDefaultValues4Update(booleanValue, mediaType);
        }

        VodData data = new VodData()
                .name(name)
                .description(description)
                .thumbUrl(DEFAULT_THUMB)
                .strings(stringMetaMap)
                .booleans(booleanHashMap)
                .numbers(numberMetaMap)
                .dates(datesMetaMap)
                .tags(tagsMetaMap)
                .assetFiles(mediaAssetFiles);

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

    private static void generateDefaultValues4Update(boolean previousValue, MediaType mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy") + "_updated";
        dateValue = getOffsetDateInFormat(1, "MM/dd/yyyy");
        doubleValue = Math.round(getRandomDouble() * 100.0) / 100.0;
        booleanValue = !previousValue;
        tagValues = new ArrayList<>();
        tagValues.add(tagValue1 + "Upd");
        tagValues.add(tagValue2 + "Upd");
        tagValues.add(textValue);

        fillMapsWithData(mediaType);
    }

    private static void generateDefaultValues4Insert(MediaType mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy");
        dateValue = getCurrentDateInFormat("MM/dd/yyyy");

        doubleValue = Math.round(getRandomDouble() * 100.0) / 100.0;
        booleanValue = getRandomBoolean();
        tagValues = new ArrayList<>();
        tagValues.add(tagValue1);
        tagValues.add(tagValue2);
        tagValues.add(textValue);

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
                stringMetaMap.put(mediaTextFieldName, textValue);
                numberMetaMap.put(mediaNumberFieldName, doubleValue);
                datesMetaMap.put(mediaDateFieldName, dateValue);
                booleanHashMap.put(mediaBooleanFieldName, booleanValue);
                tagsMetaMap.put(mediaTagFieldName, tagValues);
                break;

            case EPISODE:
                stringMetaMap.put(episodeTextFieldName, textValue);
                numberMetaMap.put(episodeNumberFieldName, doubleValue);
                datesMetaMap.put(episodeDateFieldName, dateValue);
                booleanHashMap.put(episodeBooleanFieldName, booleanValue);
                tagsMetaMap.put(episodeTagFieldName, tagValues);
                break;

            case SERIES:
                stringMetaMap.put(seriesTextFieldName, textValue);
                numberMetaMap.put(seriesNumberFieldName, doubleValue);
                datesMetaMap.put(seriesDateFieldName, dateValue);
                booleanHashMap.put(seriesBooleanFieldName, booleanValue);
                tagsMetaMap.put(seriesTagFieldName, tagValues);
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

    public static String getUpdatedIngestXml(String ingestXml, String openTag2Update, String closeTag2Update, String updateOnString) {
        int positionBeginTag = ingestXml.indexOf(openTag2Update);
        int positionEndTag;
        if ("/>".equals(closeTag2Update)) {
            positionEndTag = ingestXml.indexOf(closeTag2Update, positionBeginTag);
        } else {
            positionEndTag = ingestXml.indexOf(closeTag2Update);
        }

        String string2Delete = ingestXml.substring(positionBeginTag, positionEndTag + closeTag2Update.length());
        return ingestXml.replace(string2Delete, updateOnString);
    }
}
