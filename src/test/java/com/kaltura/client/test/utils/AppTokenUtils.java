package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AppTokenHashType;
import com.kaltura.client.types.AppToken;

public class AppTokenUtils extends BaseUtils {

    public static AppToken addAppToken(String userId, AppTokenHashType appTokenHashType, String sessionPrivileges) {
        AppToken appToken = new AppToken();
        appToken.setHashType(appTokenHashType);
        appToken.sessionUserId(userId);
        appToken.setSessionPrivileges(sessionPrivileges);

        return appToken;
    }


}
