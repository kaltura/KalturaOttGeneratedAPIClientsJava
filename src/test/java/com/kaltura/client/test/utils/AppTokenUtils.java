package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.types.AppToken;

import javax.annotation.Nullable;

public class AppTokenUtils extends BaseUtils {

    public static AppToken addAppToken(String userId, @Nullable AppTokenHashType appTokenHashType, @Nullable String sessionPrivileges, @Nullable Integer expiryDate) {
        AppToken appToken = new AppToken();
        appToken.setHashType(appTokenHashType);
        appToken.setSessionUserId(userId);
        appToken.setSessionPrivileges(sessionPrivileges);
        appToken.setExpiry(expiryDate);

        return appToken;
    }


}
