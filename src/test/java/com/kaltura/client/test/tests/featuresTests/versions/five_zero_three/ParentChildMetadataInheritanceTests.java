package com.kaltura.client.test.tests.featuresTests.versions.five_zero_three;

import com.kaltura.client.services.*;
import com.kaltura.client.services.AssetStructService.*;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.test.utils.ingestUtils.IngestVodUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Link;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kaltura.client.test.tests.enums.IngestAction.INSERT;
import static com.kaltura.client.test.tests.enums.MediaType.EPISODE;
import static com.kaltura.client.test.tests.enums.MediaType.SERIES;
import static com.kaltura.client.test.utils.AssetStructMetaUtils.loadAssetStructMeta;
import static com.kaltura.client.test.utils.AssetStructUtils.copyAssetStructObject;
import static com.kaltura.client.test.utils.AssetStructUtils.getAssetStruct;
import static com.kaltura.client.test.utils.AssetStructUtils.setInheritanceFieldsInAssetStruct;
import static com.kaltura.client.test.utils.AssetUtils.getMediaAsset;
import static com.kaltura.client.test.utils.BaseUtils.getCurrentDateInFormat;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.DEFAULT_LANGUAGE;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodOpcUtils.getVodData;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.deleteVod;
import static com.kaltura.client.test.utils.ingestUtils.IngestVodUtils.insertVod;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-5423
 */
@Link(name = "Parent Child Metadata Inheritance", url = "BEO-5423")
@Test(groups = { "opc", "Parent Child Metadata Inheritance" })
public class ParentChildMetadataInheritanceTests extends BaseTest {

    private String metaIds;
    private Response<ListResponse<AssetStruct>> sharedAssetStructListResponse;
    private AssetStruct sharedAssetStruct1, sharedAssetStruct2;
    private AssetStructMeta sharedMetaString1, sharedMetaString2, sharedMetaNumber1, sharedMetaNumber2;
    private AssetStructMeta sharedMetaDate1, sharedMetaDate2, sharedMetaBoolean1, sharedMetaBoolean2;
    private String sharedBasicMetaIds;
    private String language1;
    private Map<String, Map<String, String>> metas = new HashMap<>();

    @BeforeClass()
    public void setUp() {
        List<Language> languages = executor.executeSync(LanguageService.list(new LanguageFilter())
                .setKs(getOperatorKs())).results.getObjects();
        language1 = languages.get(0).getCode();
        // TODO: check if assetStructList be useful at the end
        // assetStructList to fill default metaIds
//        AssetStructFilter filter = new AssetStructFilter();
//        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
//        sharedAssetStructListResponse = executor.executeSync(listAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(sharedAssetStructListResponse.results.getTotalCount()).isGreaterThan(0);
//        metaIds = sharedAssetStructListResponse.results.getObjects().get(0).getMetaIds();

        // identify shared assetStructMetas
        List<String> assetStructNames = DBUtils.getAllAssetStructMetas("", 2);
        sharedMetaString1 = loadAssetStructMeta(assetStructNames.get(0));
        sharedMetaString2 = loadAssetStructMeta(assetStructNames.get(1));
        sharedMetaNumber1 = loadAssetStructMeta(assetStructNames.get(2));
        sharedMetaNumber2 = loadAssetStructMeta(assetStructNames.get(3));
        sharedMetaDate1 = loadAssetStructMeta(assetStructNames.get(4));
        sharedMetaDate2 = loadAssetStructMeta(assetStructNames.get(5));
        sharedMetaBoolean1 = loadAssetStructMeta(assetStructNames.get(6));
        sharedMetaBoolean2 = loadAssetStructMeta(assetStructNames.get(7));
        // TODO: ask Lior why ONLY these ids are obvious when DB has more basic metas?
        sharedBasicMetaIds = "58,629,1482,1493,1494,1495,1496,1497,566";//loadBasicAssetStructMetaId();

        // TODO: check if type of metas should be updated????
        metas.put(assetStructNames.get(0), Map.of(language1, "Default string value1"));
        metas.put(assetStructNames.get(1), Map.of(language1, "Default string value2"));
        metas.put(assetStructNames.get(2), Map.of(language1, "1111"));
        metas.put(assetStructNames.get(3), Map.of(language1, "1112"));
        metas.put(assetStructNames.get(4), Map.of(language1, "01/01/2001"));
        metas.put(assetStructNames.get(5), Map.of(language1, "01/01/2002"));
        metas.put(assetStructNames.get(6), Map.of(language1, "true"));
        metas.put(assetStructNames.get(7), Map.of(language1, "false"));

        // create shared assetStruct1
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        sharedAssetStruct1 = createAssetStruct(prefix, metaIds);

        // create shared assetStruct2
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        sharedAssetStruct2 = createAssetStruct(prefix, metaIds);
    }

    @Test
    public void testAssetStructListSecurity() {
        // assetStructList Master
        AssetStructFilter filter = new AssetStructFilter();
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(SharedHousehold.getSharedMasterUserKs()));
        assertThat(assetStructListResponse.error.getMessage()).isEqualToIgnoringCase("Service Forbidden");

        // assetStructList Operator
        filter = new AssetStructFilter();
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(assetStructListResponse.results).isNotNull();
        assertThat(assetStructListResponse.error).isNull();
    }

    @Test
    public void testCreateInheritance() {
        // create assetStruct1
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStruct1 = createAssetStruct(prefix, metaIds);
        // create assetStruct2
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStruct2 = createAssetStruct(prefix, metaIds);

        // set inheritance
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStruct2);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStruct1.getId(),
                sharedMetaString1.getMetaId(), sharedMetaString2.getMetaId());
        updateAssetStruct(assetStruct2.getId(), assetStruct2Update, false);

        // check parent doesn't have changes in related inheritance fields
        AssetStructFilter filter = new AssetStructFilter();
        filter.setIdIn(assetStruct1.getId().toString());
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(0L);
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(0L);
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(0L);

        // check children asset struct has changes
        filter.setIdIn(assetStruct2.getId().toString());
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStruct1.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // remove assetStructs
        deleteAssetStruct(assetStruct2.getId()); // firstly should be deleted children
        deleteAssetStruct(assetStruct1.getId());
    }

    @Test(enabled = false) // TODO: remove after deletion
    public void testInheritance4TextAndNumericFieldsOnly() {
        // create assetStruct1
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStruct1 = createAssetStruct(prefix, metaIds);
        // create assetStruct2
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStruct2 = createAssetStruct(prefix, metaIds);

        // set inheritance // to try set inheritance between different types of meta
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStruct2);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStruct1.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStruct2.getId(), assetStruct2Update, true);

        // TODO: wait response from Anat and depends on it continue the test with other types
        // check children asset struct has changes
//        AssetStructFilter filter = new AssetStructFilter();
//        filter.setIdIn(assetStruct2.getId().toString());
//        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
//        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
//                .setKs(getOperatorKs()));
//        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
//        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStruct1.getId());
//        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
//        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // remove assetStructs
        deleteAssetStruct(assetStruct2.getId()); // firstly should be deleted children
        deleteAssetStruct(assetStruct1.getId());
    }

    @Test
    public void testInheritance3Levels() {
        // create granpa
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStructGrandParent = createAssetStruct(prefix, metaIds);
        // create parent
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStructParent = createAssetStruct(prefix, metaIds);
        // create children
        metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "3";
        AssetStruct assetStructChildren = createAssetStruct(prefix, metaIds);

        // set inheritance between Children and Parent
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStructChildren);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructParent.getId(),
                sharedMetaString2.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructChildren.getId(), assetStruct2Update, false);

        // set inheritance between Parent and GrandParent
        assetStruct2Update = copyAssetStructObject(assetStructParent);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructGrandParent.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructParent.getId(), assetStruct2Update, false);

        // check children asset struct has changes
        AssetStructFilter filter = new AssetStructFilter();
        filter.setIdIn(assetStructChildren.getId().toString());
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructParent.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString2.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // check parent asset struct has changes
        filter = new AssetStructFilter();
        filter.setIdIn(assetStructParent.getId().toString());
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructGrandParent.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // remove assetStructs
        deleteAssetStruct(assetStructChildren.getId()); // firstly should be deleted children
        deleteAssetStruct(assetStructParent.getId()); // secondly should be deleted parent
        deleteAssetStruct(assetStructGrandParent.getId());
    }

    // checking that on BE level it is doesn't supported limitation about 1 children maximum
    @Test
    public void testInheritanceParentWith2Childrens() {
        // create parent
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStructParent = createAssetStruct(prefix, metaIds);
        // create children1
        metaIds = sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStructChildren1 = createAssetStruct(prefix, metaIds);
        // create children2
        metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedMetaString2.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "3";
        AssetStruct assetStructChildren2 = createAssetStruct(prefix, metaIds);

        // set inheritance between Children2 and Parent
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStructChildren2);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructParent.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructChildren2.getId(), assetStruct2Update, false);

        // set inheritance between Children1 and Parent
        assetStruct2Update = copyAssetStructObject(assetStructChildren1);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructParent.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructChildren1.getId(), assetStruct2Update, false);

        // check children asset2 struct has changes
        AssetStructFilter filter = new AssetStructFilter();
        filter.setIdIn(assetStructChildren2.getId().toString());
        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructParent.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // check children asset1 struct has changes
        filter = new AssetStructFilter();
        filter.setIdIn(assetStructChildren1.getId().toString());
        listAssetStructBuilder = AssetStructService.list(filter);
        assetStructListResponse = executor.executeSync(listAssetStructBuilder
                .setKs(getOperatorKs()));
        assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructParent.getId());
        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // remove assetStructs
        deleteAssetStruct(assetStructChildren2.getId()); // firstly should be deleted childrens
        deleteAssetStruct(assetStructChildren1.getId()); // firstly should be deleted childrens
        deleteAssetStruct(assetStructParent.getId());

        // TODO: check if it completed
    }

    @Test
    public void testInheritanceWithIngest() {
        // create parent
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStructParent = createAssetStruct(prefix, metaIds);
        // create children1
        metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStructChildren1 = createAssetStruct(prefix, metaIds);

        // set inheritance between Children1 and Parent
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStructChildren1);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructParent.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructChildren1.getId(), assetStruct2Update, false);

//        // check children asset1 struct has changes
//        AssetStructFilter filter = new AssetStructFilter();
//        filter.setIdIn(assetStructChildren1.getId().toString());
//        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
//        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
//                .setKs(getOperatorKs()));
//        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
//        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructParent.getId());
//        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
//        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // ingest metas of needed type
//        IngestVodUtils.VodData vodData = getVodData(null, INSERT)
//                .multilingualStringMeta(metas);


        // remove assetStructs
        deleteAssetStruct(assetStructChildren1.getId()); // firstly should be deleted childrens
        deleteAssetStruct(assetStructParent.getId());
    }

//    @Test TODO: after ingest
//    public void testIngestRules() {
//        IngestVodUtils.VodData vodData = getVodData(SERIES, INSERT);
//        MediaAsset series = insertVod(vodData, true);
//        assertThat(series.getExternalId()).isNotNull();
//
//        vodData = getVodData(EPISODE, INSERT);
//        MediaAsset episode = insertVod(vodData, true);
//        assertThat(episode.getExternalId()).isNotNull();
//
//        // cleanup
//        deleteVod(series.getExternalId());
//        deleteVod(episode.getExternalId());
//    }

    @Test
    public void testApiCreationOfAssetsWithInheritance() {
        // create parent
        String metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        String prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss");
        AssetStruct assetStructParent = createAssetStruct(prefix, metaIds);
        // create children
        metaIds = sharedMetaString1.getMetaId().toString() + "," + sharedBasicMetaIds;
        prefix = "AssetStruct_" + getCurrentDateInFormat("yyMMddHHmmss") + "2";
        AssetStruct assetStructChildren = createAssetStruct(prefix, metaIds);
        Map<String, String> metas = new HashMap<>();
        metas.put(DBUtils.getMetaNameById(sharedMetaString1.getMetaId(), false), "some default value");
        // set inheritance between Children and Parent
        AssetStruct assetStruct2Update = copyAssetStructObject(assetStructChildren);
        setInheritanceFieldsInAssetStruct(assetStruct2Update, assetStructParent.getId(),
                sharedMetaString1.getMetaId(), sharedMetaDate2.getMetaId());
        updateAssetStruct(assetStructChildren.getId(), assetStruct2Update, false);

        // TODO: complete
        MediaAsset asset = getMediaAsset(assetStructParent.getId(), "testName", "testDescription");
        asset.setMetas(loadMetas(metas));
        AssetService.AddAssetBuilder addAssetBuilder = AssetService.add(asset);
        Response<Asset> response = executor.executeSync(addAssetBuilder
                .setKs(getOperatorKs())
                .setLanguage("*"));
        asset = (MediaAsset) response.results;

        MediaAsset asset2 = getMediaAsset(assetStructChildren.getId(), "testName2", "testDescription2");
        asset2.setMetas(loadMetas(metas));
        addAssetBuilder = AssetService.add(asset2);
        response = executor.executeSync(addAssetBuilder
                .setKs(getOperatorKs())
                .setLanguage("*"));
        asset2 = (MediaAsset) response.results;
//
//        // check children asset1 struct has changes
//        AssetStructFilter filter = new AssetStructFilter();
//        filter.setIdIn(assetStructChildren.getId().toString());
//        ListAssetStructBuilder listAssetStructBuilder = AssetStructService.list(filter);
//        Response<ListResponse<AssetStruct>> assetStructListResponse = executor.executeSync(listAssetStructBuilder
//                .setKs(getOperatorKs()));
//        AssetStruct assetStructFromResponse = assetStructListResponse.results.getObjects().get(0);
//        assertThat(assetStructFromResponse.getParentId()).isEqualTo(assetStructParent.getId());
//        assertThat(assetStructFromResponse.getConnectedParentMetaId()).isEqualTo(sharedMetaString1.getMetaId());
//        assertThat(assetStructFromResponse.getConnectingMetaId()).isEqualTo(sharedMetaString2.getMetaId());

        // 4rent folder has some logic

        // remove assetStructs
        deleteVod(asset.getExternalId());
        deleteVod(asset2.getExternalId());
        deleteAssetStruct(assetStructChildren.getId()); // firstly should be deleted children
        deleteAssetStruct(assetStructParent.getId());
    }

    // TODO: find better name and option to use it in the whole project
    private Map<String, Value> loadMetas(Map<String, String> metas) {
        Map<String, Value> result = new HashMap<>();
        for (Map.Entry<String, String> entry: metas.entrySet()) {
            StringValue value = new StringValue();
            value.setValue(entry.getValue());
            //value.value(entry.getValue());
            result.put(entry.getKey(), value);
        }

        return result;
    }


    // TODO: Check if it used after implementation
    private List<AssetStruct> loadAssetStructsWithoutHierarchy(int countOfAssetStructs, List<AssetStruct> listOfAssetStructs) {
        List<AssetStruct> result = new ArrayList<>();
        for (AssetStruct assetStruct: listOfAssetStructs) {
            if ((result.size() < countOfAssetStructs) && (assetStruct.getParentId() == 0)) {
                result.add(assetStruct);
            }
        }
        return result;
    }

    // added to play with methods that are going to be checked
//    @Test
//    public void sandbox() {
//        String prefix = "MaxTest_assetStruct_";
//
//        Long assetStructId = sharedAssetStructListResponse.results.getObjects().get(0).getId();
//
//        AssetStructMetaFilter assetStructMetaFilter = new AssetStructMetaFilter();
//        assetStructMetaFilter.setAssetStructIdEqual(assetStructId);
//        ListAssetStructMetaBuilder listAssetStructMetaBuilder = AssetStructMetaService.list(assetStructMetaFilter);
//        Response<ListResponse<AssetStructMeta>> listAssetMetaStructResponse = executor.executeSync(listAssetStructMetaBuilder
//                .setKs(getOperatorKs()));
//        assertThat(listAssetMetaStructResponse.results.getTotalCount()).isGreaterThan(0);
//        Long metaId = listAssetMetaStructResponse.results.getObjects().get(0).getMetaId();
//
//        String defaultIngestValue = prefix + "_defaultIngestValue";
//        String ingestReferencePath = prefix + "ingestReferencePath";
//        boolean isProtectFromIngest = false;
//        boolean isInherited = true;
//        AssetStructMeta assetStructMeta = getAssetStructMeta(defaultIngestValue,
//                ingestReferencePath, isProtectFromIngest, isInherited);
//        UpdateAssetStructMetaBuilder updateAssetStructMetaBuilder =
//                new UpdateAssetStructMetaBuilder(assetStructId, metaId, assetStructMeta);
//        Response<AssetStructMeta> assetStructMetaResponse = executor.executeSync(updateAssetStructMetaBuilder
//                .setKs(getOperatorKs()));
//        assertThat(assetStructMetaResponse.results.getAssetStructId()).isEqualTo(assetStructId);
//        assertThat(assetStructMetaResponse.results.getMetaId()).isEqualTo(metaId);
//        assertThat(assetStructMetaResponse.results.getDefaultIngestValue()).isEqualToIgnoringCase(defaultIngestValue);
//        assertThat(assetStructMetaResponse.results.getIngestReferencePath()).isEqualToIgnoringCase(ingestReferencePath);
//        assertThat(assetStructMetaResponse.results.getProtectFromIngest()).isEqualTo(isProtectFromIngest);
//        //TODO: update library to have options compare it
//        //assertThat(assetStructMetaResponse.results.getInherited()).isEqualTo(isInherited);
//
//        // assetStructAdd
//        AssetStruct assetStruct = getAssetStruct(prefix, DEFAULT_LANGUAGE, false, metaIds, null, null, null);
//        AddAssetStructBuilder addAssetStructBuilder = AssetStructService.add(assetStruct);
//        Response<AssetStruct> assetStructResponse = executor.executeSync(addAssetStructBuilder
//                .setKs(getOperatorKs()).setLanguage("*"));
//        AssetStruct assetStructFromResponse = assetStructResponse.results;
//        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name");
//
//        assetStruct.setSystemName(prefix + "_System_name_upd");
//
//        // assetStructUpdate
//        UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(assetStructFromResponse.getId(), assetStruct);
//        assetStructResponse = executor.executeSync(updateAssetStructBuilder
//                .setKs(getOperatorKs())
//                .setLanguage("*"));
//        assertThat(assetStructResponse.results.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name_upd");
//
//        // assetStructDelete
//        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructFromResponse.getId());
//        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
//    }

//    @Test
//    public void sandboxRemoveMetasAndTags() {
//        IngestVodUtils.VodData vodData = getVodData(MOVIE, INSERT);
//        MediaAsset movie = insertVod(vodData, true);
//
//        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
//        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
//        // get all metas
//        Set<String> movieMetasNames = movie.getMetas().keySet();
//        int metasSizeAfterCreation = movieMetasNames.size();
//        int nonBasicMediaMetasCount = 0;
//        assertThat(metasSizeAfterCreation).isGreaterThan(0);
//
//        // get all tags
//        Set<String> movieTagsNames = movie.getTags().keySet();
//        int tagsSizeAfterCreation = movieTagsNames.size();
//        assertThat(tagsSizeAfterCreation).isGreaterThan(0);
//
//        // get all metas and tags info to remove them from media
//        List<String> metaIds = new ArrayList<>();
//        int metaId;
//        for (String metaName: movieMetasNames) {
//            metaId = getMetaIdByName(metaName, false);
//            if (metaId !=-1) {
//                metaIds.add(String.valueOf(metaId));
//                nonBasicMediaMetasCount++;
//            }
//        }
//        for (String metaName: movieTagsNames) {
//            metaId = getMetaIdByName(metaName, true);
//            if (metaId !=-1) {
//                metaIds.add(String.valueOf(metaId));
//            }
//        }
//        String metaIdsIn = String.join(",", metaIds);
//
//        // removeMetasAndTags
//        RemoveMetasAndTagsAssetBuilder removeMetasAndTagsAssetBuilder =
//                AssetService.removeMetasAndTags(movie.getId(), AssetReferenceType.MEDIA, metaIdsIn);
//        Response<Boolean> booleanResponse = executor.executeSync(removeMetasAndTagsAssetBuilder
//                .setKs(getOperatorKs()));
//        assertThat(booleanResponse.results).isEqualTo(true);
//
//        // check results
//        GetAssetBuilder getAssetBuilder = AssetService.get(movie.getId().toString(), AssetReferenceType.MEDIA);
//        Response<Asset> assetResponse = executor.executeSync(getAssetBuilder
//                .setKs(getOperatorKs()));
//        movie = (MediaAsset) assetResponse.results;
//
//        assertThat(movie.getName()).isEqualTo(movie.getExternalId());
//        assertThat(movie.getDescription()).isEqualTo("description of " + movie.getExternalId());
//        assertThat(movie.getMetas().keySet().size()).isEqualTo(metasSizeAfterCreation - nonBasicMediaMetasCount);
//        assertThat(movie.getTags().keySet().size()).isEqualTo(0);
//
//        // cleanup
//        deleteVod(movie.getExternalId());
//    }

//    @Test
//    public void sandboxDelete() {
//        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(2381);
//        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
//                .setKs(getOperatorKs()));
//        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
//    }

    @AfterClass()
    public void tearDown() {
        // cleaning created shared assetStructs
        deleteAssetStruct(sharedAssetStruct1.getId());
        deleteAssetStruct(sharedAssetStruct2.getId());
    }

    void deleteAssetStruct(Long assetStructId) {
        DeleteAssetStructBuilder deleteAssetStructBuilder = AssetStructService.delete(assetStructId);
        Response<Boolean> deleteAssetStructResponse = executor.executeSync(deleteAssetStructBuilder
                .setKs(getOperatorKs()));
        assertThat(deleteAssetStructResponse.results.booleanValue()).isEqualTo(true);
    }

    AssetStruct createAssetStruct(String prefix, String metaIds) {
        AssetStruct result = getAssetStruct(prefix, DEFAULT_LANGUAGE, false, metaIds, null,
                null, null);

        AddAssetStructBuilder addAssetStructBuilder = AssetStructService.add(result);
        Response<AssetStruct> assetStructResponse = executor.executeSync(addAssetStructBuilder
                .setKs(getOperatorKs()).setLanguage("*"));
        result = assetStructResponse.results;

        assertThat(result.getSystemName()).isEqualToIgnoringCase(prefix + "_System_name");

        return result;
    }

    Response<AssetStruct> updateAssetStruct(Long assetStructId, AssetStruct updatedAssetStruct, boolean isErrorExpected) {
        UpdateAssetStructBuilder updateAssetStructBuilder = AssetStructService.update(
                assetStructId, updatedAssetStruct);
        Response<AssetStruct> assetStructResponse = executor.executeSync(updateAssetStructBuilder
                .setKs(getOperatorKs()).setLanguage("*"));
        if (isErrorExpected) {
            assertThat(assetStructResponse.error).isNotNull();
        } else {
            assertThat(assetStructResponse.results).isNotNull();
        }

        return assetStructResponse;
    }
}
