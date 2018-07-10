package com.kaltura.client.test.utils;

import com.kaltura.client.types.*;

import javax.annotation.Nullable;
import java.util.List;

public class ChannelUtils extends BaseUtils {

    public static DynamicChannel addDynamicChannel(String name, @Nullable String description, @Nullable Boolean isActive, @Nullable String ksqlExpression,
                                            @Nullable ChannelOrder channelOrder, @Nullable List<IntegerValue> assetTypes) {
        DynamicChannel channel = new DynamicChannel();
        channel.setName(name);
        channel.setDescription(description);
        channel.setIsActive(isActive);
        channel.setAssetTypes(assetTypes);
        channel.setKSql(ksqlExpression);
        channel.setOrderBy(channelOrder);

        return channel;
    }
}
