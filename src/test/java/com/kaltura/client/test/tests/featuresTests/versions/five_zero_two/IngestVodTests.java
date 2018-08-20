package com.kaltura.client.test.tests.featuresTests.versions.five_zero_two;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.MediaAsset;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.kaltura.client.test.tests.enums.MediaType.MOVIE;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5428
 */
public class IngestVodTests extends BaseTest {

    private static final int assetStructId = 1883; // TODO: update on property (or if possible on another type)
    private static final String textFieldName = "BoxOffice";
    private static final String dateFieldName = "ReleaseDate";
    private static final String numberFieldName = "Runtime2";
    private static final String booleanFieldName = "IsAgeLimited";
    private static final String tagFieldName = "Actors";

    private String name;
    private String description;
    private HashMap<String, String> stringMetaMap = new HashMap<>();
    private HashMap<String, Integer> numberMetaMap = new HashMap<>();
    private HashMap<String, String> datesMetaMap = new HashMap<>();
    private HashMap<String, List<String>> tagsMetaMap = new HashMap<>();
    private List<IngestVodUtils.VODFile> assetFiles = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        String prefix = "Movie_";
        String localCoguid = getCurrentDateInFormat("yyMMddHHmmssSS");
        name =  prefix + "Name_" + localCoguid;
        description = prefix + "Description_" + localCoguid;

        stringMetaMap.put(textFieldName, textFieldName + "value1");
        numberMetaMap.put(numberFieldName, 1234);
        datesMetaMap.put(dateFieldName, "12/12/2012");
        List<String> actors = new ArrayList<>();
        actors.add("Jack Nicholson");
        actors.add("Natalie Portman");
        tagsMetaMap.put(tagFieldName, actors);

        IngestVodUtils.VODFile file1 = new IngestVodUtils.VODFile()
                .assetDuration("1000")
                .quality("HIGH")
                .handling_type("CLIP")
                .cdn_name("Default CDN")
                .cdn_code("http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .alt_cdn_code("http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .billing_type("Tvinci")
                .product_code("productExampleCode")
                .type("Test130301")
                .coguid("Test130301_" + localCoguid)
                .ppvModule("Shai_Regression_PPV");
        IngestVodUtils.VODFile file2 = new IngestVodUtils.VODFile()
                .assetDuration("1000")
                .quality("HIGH")
                .handling_type("CLIP")
                .cdn_name("Default CDN")
                .cdn_code("http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .alt_cdn_code("http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m")
                .billing_type("Tvinci")
                .product_code("productExampleCode")
                .type("new file type1")
                .coguid("new file type1_" + localCoguid)
                .ppvModule("Subscription_only_PPV");
        assetFiles.add(file1);
        assetFiles.add(file2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "ingest VOD with filled base meta fields")
    public void insertVodBaseFields() {
        String testSuffix = "1";

        IngestVodUtils.VodData vodData1 = new IngestVodUtils.VodData()
                .name(name + testSuffix)
                .description(description + testSuffix)
                .mediaType(MOVIE)
                .strings(stringMetaMap)
                .numbers(numberMetaMap)
                .dates(datesMetaMap)
                .tags(tagsMetaMap)
                .assetFiles(assetFiles);

        MediaAsset asset = insertVod(vodData1, false);

    }

}
