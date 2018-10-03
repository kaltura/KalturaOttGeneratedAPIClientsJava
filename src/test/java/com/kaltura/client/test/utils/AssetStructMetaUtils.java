package com.kaltura.client.test.utils;

import com.kaltura.client.services.AssetStructMetaService;
import com.kaltura.client.services.AssetStructService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.test.tests.enums.AssetStructMetaType;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.kaltura.client.test.tests.BaseTest.getOperatorKs;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getMetaNameById;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetStructMetaUtils extends BaseUtils {

    public static TestAPIOkRequestsExecutor executor = TestAPIOkRequestsExecutor.getExecutor();

    public static AssetStructMeta loadAssetStructMeta(String metaName) {
        Long metaId = DBUtils.getMetaIdByName(metaName, false);
        AssetStructMetaFilter assetStructMetaFilter = new AssetStructMetaFilter();
        //assetStructMetaFilter.setAssetStructIdEqual(assetStructId);
        assetStructMetaFilter.setMetaIdEqual(metaId);
        AssetStructMetaService.ListAssetStructMetaBuilder listAssetStructMetaBuilder = AssetStructMetaService.list(assetStructMetaFilter);
        Response<ListResponse<AssetStructMeta>> listAssetMetaStructResponse = executor.executeSync(listAssetStructMetaBuilder
                .setKs(getOperatorKs()));

        return listAssetMetaStructResponse.results.getObjects().get(0);
    }

    // TODO: probably can be useless as we do not have assetStructMeta/Add (only list and update)
    public static AssetStructMeta getAssetStructMeta(String defaultIngestValue, String ingestReferencePath,
                                               boolean isProtectFromIngest, boolean isInherited) {
        AssetStructMeta assetStructMeta = new AssetStructMeta();
        assetStructMeta.setDefaultIngestValue(defaultIngestValue);
        assetStructMeta.setIngestReferencePath(ingestReferencePath);
        assetStructMeta.setProtectFromIngest(isProtectFromIngest);
        // TODO: update library to have options update it
        //assetStructMeta.setIsInherited(isInherited);

        return assetStructMeta;
    }

// TODO: COMPLETE THE METHOD
//    public static List<String> getAssetStructMetas(Long assetStructId, AssetStructMetaType type, boolean processBasicFields) {
//        List<String> result = new ArrayList<>();
//
//        AssetStructMetaFilter assetStructMetaFilter = new AssetStructMetaFilter();
//        assetStructMetaFilter.setAssetStructIdEqual(assetStructId);
//        AssetStructMetaService.ListAssetStructMetaBuilder listAssetStructMetaBuilder = AssetStructMetaService.list(assetStructMetaFilter);
//        Response<ListResponse<AssetStructMeta>> listAssetMetaStructResponse = executor.executeSync(listAssetStructMetaBuilder
//                .setKs(getOperatorKs()));
//
//        String name;
//        for (AssetStructMeta assetStructMeta: listAssetMetaStructResponse.results.getObjects()) {
//            name = getMetaNameById(assetStructMeta.getMetaId(), processBasicFields);
//            if (StringUtils.isNotEmpty(name)) {
//                if (!AssetStructMetaType.ALL.equals(type)) {
//
//                }
//            }
//        }
//
//        //assertThat(assetStructFromResponse.getParentId()).isEqualTo(0L);
//
//        return result;
//    }
}
