package com.kaltura.client.test.utils;

import com.kaltura.client.services.AssetStructMetaService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
import com.kaltura.client.types.AssetStructMeta;
import com.kaltura.client.types.AssetStructMetaFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;

import static com.kaltura.client.test.tests.BaseTest.getOperatorKs;

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
}
