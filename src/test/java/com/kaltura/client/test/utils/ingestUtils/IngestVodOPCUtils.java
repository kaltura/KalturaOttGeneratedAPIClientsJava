package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.enums.AssetOrderBy;
import com.kaltura.client.test.tests.enums.MediaType;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.kaltura.client.services.AssetService.list;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.BaseUtils.*;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.*;

public class IngestVodOPCUtils extends BaseIngestUtils {

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
    public static final String EMPTY_IMAGE_TAG = "<ratio ratio=\"\" thumb=\"\"/>\n";
    public static final String EMPTY_THUMB_TAG = "<thumb ingestUrl=\"\"/>\n";

    // TODO: remove it using util
    public static final String DELETE_VOD_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
            "<soapenv:Header/>\n" +
            "<soapenv:Body>\n" +
            "<tem:IngestTvinciData>\n" +
            "<tem:request>\n" +
            "<userName>Test_API_27_03</userName>\n" +
            "<passWord>Test_API_27_03</passWord>\n" +
            "<data>\n" +
            "<![CDATA[\n" +
            "<feed>\n" +
            "<export>\n" +
            "<media action=\"delete\" co_guid=\"180822092522774\" entry_id=\"entry_180822092522774\" erase=\"false\" is_active=\"true\">\n" +
            "</media>\n" +
            "</export>\n" +
            "</feed>\n" +
            "]]>\n" +
            "</data>\n" +
            "</tem:request>\n" +
            "</tem:IngestTvinciData>\n" +
            "</soapenv:Body>\n" +
            "</soapenv:Envelope>";

    // media types
    public static final String EPISODE = "Episode";
    public static final String MOVIE = "Movie";
    public static final String SERIES = "Series";

    // TODO: how to get these data from DB or request?
    //Movie fields
    public static final String mediaTextFieldName = "BoxOffice";
    public static final String mediaDateFieldName = "ReleaseDate";
    public static final String mediaNumberFieldName = "Runtime2";
    public static final String mediaBooleanFieldName = "IsAgeLimited";
    public static final String mediaTagFieldName = "Actors";

    //Episode fields
    public static final String episodeTextFieldName = "TwitterHashtag";
    public static final String episodeDateFieldName = "Date";
    public static final String episodeNumberFieldName = "CommonIpAddress";
    public static final String episodeBooleanFieldName = "CyyNCAh";
    public static final String episodeTagFieldName = "Studio";

    //Series fields
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

    public static IngestVodUtils.VodData getVodData(String mediaType, List<VodFile> mediaAssetFiles) {
        switch (mediaType) {
            case "Movie":
                return new IngestVodUtils.VodData()
                        .name(name)
                        .description(description)
                        .mediaType(MediaType.MOVIE)
                        .thumbUrl(DEFAULT_THUMB)
                        .strings(stringMetaMap)
                        .booleans(booleanHashMap)
                        .numbers(numberMetaMap)
                        .dates(datesMetaMap)
                        .tags(tagsMetaMap)
                        .isVirtual(false)
                        .assetFiles(mediaAssetFiles);
            case "Episode":
                return new IngestVodUtils.VodData()
                        .name(name)
                        .description(description)
                        .mediaType(MediaType.EPISODE)
                        .thumbUrl(DEFAULT_THUMB)
                        .strings(stringMetaMap)
                        .booleans(booleanHashMap)
                        .numbers(numberMetaMap)
                        .dates(datesMetaMap)
                        .tags(tagsMetaMap)
                        .isVirtual(false)
                        .assetFiles(mediaAssetFiles);
            case "Series":
                return new IngestVodUtils.VodData()
                        .name(name)
                        .description(description)
                        .mediaType(MediaType.SERIES)
                        .thumbUrl(DEFAULT_THUMB)
                        .strings(stringMetaMap)
                        .booleans(booleanHashMap)
                        .numbers(numberMetaMap)
                        .dates(datesMetaMap)
                        .tags(tagsMetaMap)
                        .isVirtual(true)
                        .assetFiles(mediaAssetFiles);
            default:
                return null;
        }
    }

    static void fillMediaMapsWithData() {
        stringMetaMap.put(mediaTextFieldName, textValue);
        numberMetaMap.put(mediaNumberFieldName, doubleValue);
        datesMetaMap.put(mediaDateFieldName, dateValue);
        booleanHashMap.put(mediaBooleanFieldName, booleanValue);
        tagsMetaMap.put(mediaTagFieldName, tagValues);
    }

    static void fillEpisodeMapsWithData() {
        stringMetaMap.put(episodeTextFieldName, textValue);
        numberMetaMap.put(episodeNumberFieldName, doubleValue);
        datesMetaMap.put(episodeDateFieldName, dateValue);
        booleanHashMap.put(episodeBooleanFieldName, booleanValue);
        tagsMetaMap.put(episodeTagFieldName, tagValues);
    }

    static void fillSeriesMapsWithData() {
        stringMetaMap.put(seriesTextFieldName, textValue);
        numberMetaMap.put(seriesNumberFieldName, doubleValue);
        datesMetaMap.put(seriesDateFieldName, dateValue);
        booleanHashMap.put(seriesBooleanFieldName, booleanValue);
        tagsMetaMap.put(seriesTagFieldName, tagValues);
    }

    public static void generateDefaultValues4Update(boolean previousValue, String mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy") + "_updated";
        dateValue = getOffsetDateInFormat(1,"MM/dd/yyyy");
        // to round up 2 decimal places
        doubleValue = Math.round(getRandomDoubleValue() * 100.0) / 100.0;
        booleanValue = !previousValue;
        tagValues = new ArrayList<>();
        tagValues.add(tagValue1 + "Upd");
        tagValues.add(tagValue2 + "Upd");
        tagValues.add(textValue);

        fillMapsWithData(mediaType);
    }

    public static void generateDefaultValues4Insert(String mediaType) {
        textValue = "textValue" + getCurrentDateInFormat("MM/dd/yyyy");
        dateValue = getCurrentDateInFormat("MM/dd/yyyy");
        // to round up 2 decimal places
        doubleValue = Math.round(getRandomDoubleValue() * 100.0) / 100.0;
        booleanValue = getRandomBooleanValue();
        tagValues = new ArrayList<>();
        tagValues.add(tagValue1);
        tagValues.add(tagValue2);
        tagValues.add(textValue);

        fillMapsWithData(mediaType);
    }

    private static void fillMapsWithData(String mediaType) {
        clearMapsBeforeFilling();
        switch (mediaType) {
            case MOVIE:
                fillMediaMapsWithData();
                break;
            case EPISODE:
                fillEpisodeMapsWithData();
                break;
            case SERIES:
                fillSeriesMapsWithData();
                break;
            default:
                break;
        }
    }

    private static void clearMapsBeforeFilling() {
        stringMetaMap = new HashMap<>();
        numberMetaMap = new HashMap<>();
        booleanHashMap = new HashMap<>();
        datesMetaMap = new HashMap<>();
        tagsMetaMap = new HashMap<>();
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
        //assertThat(assetListResponse.results.getObjects().size()).isEqualTo(1);

        return assetListResponse.results.getObjects().get(0).getExternalId();
    }

    public static List<VodFile> get2AssetFiles(String fileType1, String fileType2, String ppvs1, String ppvs2) {
        List<VodFile> result = new ArrayList<>();

        long e = getEpoch();
        String r = getRandomValue();

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

    public static String getIngestXmlWithoutFiles(String ingestXml) {
        return getUpdatedIngestXml(ingestXml, "<files>", "</files>", "");
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