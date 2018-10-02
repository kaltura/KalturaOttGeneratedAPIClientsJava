package com.kaltura.client.test.utils;

import com.kaltura.client.types.AssetStruct;
import com.kaltura.client.types.TranslationToken;
import javax.annotation.Nullable;
import java.util.List;

public class AssetStructUtils extends BaseUtils {

    public static AssetStruct getAssetStruct(String prefix, String language, boolean isProtected, String metaIds,
                                             @Nullable Long parentId, @Nullable Long connectingMetaId,
                                             @Nullable Long connectedParentMetaId) {
        AssetStruct assetStruct = new AssetStruct();
        assetStruct.setSystemName(prefix + "_System_name");
        assetStruct.setMultilingualName(setTranslationToken(prefix + "_multiLingualName"));
        assetStruct.setIsProtected(isProtected);
        assetStruct.setMetaIds(metaIds);
        assetStruct.setParentId(parentId);
        assetStruct.setConnectingMetaId(connectingMetaId);
        assetStruct.setConnectedParentMetaId(connectedParentMetaId);

        return assetStruct;
    }

    public static AssetStruct copyAssetStructObject(AssetStruct assetStruct2Copy) {
        AssetStruct result = new AssetStruct();
        result.setConnectedParentMetaId(assetStruct2Copy.getConnectedParentMetaId());
        result.setConnectingMetaId(assetStruct2Copy.getConnectingMetaId());
        result.setParentId(assetStruct2Copy.getParentId());
        result.setMetaIds(assetStruct2Copy.getMetaIds());
        result.setSystemName(assetStruct2Copy.getSystemName());
        result.setIsProtected(assetStruct2Copy.getIsProtected());
        result.setPluralName(assetStruct2Copy.getPluralName());
        result.setMultilingualName(assetStruct2Copy.getMultilingualName());
        result.setFeatures(assetStruct2Copy.getFeatures());

        return result;
    }

    public static void setInheritanceFieldsInAssetStruct(AssetStruct assetStruct, Long parentAssetStructId,
                                           Long connectedParentMetaId, Long connectingMetaId) {
        assetStruct.setParentId(parentAssetStructId);
        assetStruct.setConnectedParentMetaId(connectedParentMetaId);
        assetStruct.setConnectingMetaId(connectingMetaId);
    }
}
