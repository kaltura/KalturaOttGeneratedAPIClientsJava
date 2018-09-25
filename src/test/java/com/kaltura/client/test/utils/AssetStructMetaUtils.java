package com.kaltura.client.test.utils;

import com.kaltura.client.types.AssetStructMeta;

public class AssetStructMetaUtils extends BaseUtils {

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
