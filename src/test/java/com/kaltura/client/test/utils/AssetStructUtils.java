package com.kaltura.client.test.utils;

import com.kaltura.client.types.AssetStruct;
import com.kaltura.client.types.TranslationToken;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AssetStructUtils extends BaseUtils {

    public static AssetStruct getAssetStruct(String prefix, String language, boolean isProtected, String metaIds,
                                             @Nullable Long parentId, @Nullable Long connectingMetaId,
                                             @Nullable Long connectedParentMetaId) {
        AssetStruct assetStruct = new AssetStruct();
        assetStruct.setSystemName(prefix + "_System_name");
        List<TranslationToken> translationTokens = new ArrayList<>();
        TranslationToken translationToken = new TranslationToken();
        translationToken.setValue(prefix + "_multiLingualName");
        translationToken.setLanguage(language);
        translationTokens.add(translationToken);
        assetStruct.setMultilingualName(translationTokens);
        assetStruct.setIsProtected(isProtected);
        assetStruct.setMetaIds(metaIds);
        assetStruct.setParentId(parentId);
        assetStruct.setConnectingMetaId(connectingMetaId);
        assetStruct.setConnectedParentMetaId(connectedParentMetaId);

        return assetStruct;
    }
}
