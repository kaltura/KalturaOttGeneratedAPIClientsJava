package com.kaltura.client.test.utils;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.response.base.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.kaltura.client.services.OttUserService.GetOttUserBuilder;
import static com.kaltura.client.services.OttUserService.LoginOttUserBuilder;
import static com.kaltura.client.test.tests.BaseTest.*;

public class OttUserUtils extends BaseUtils {

    // generate ottUser
    public static OTTUser generateOttUser() {
        long millis = System.currentTimeMillis();
        String randomString = getRandomString();
        String emailPrefix = "qaott2018" + "+";
        String emailDomain = "@gmail.com";
        String stamp = randomString + millis;

        OTTUser user = new OTTUser();
        user.setUsername(stamp);
        user.setFirstName(randomString);
        user.setLastName(String.valueOf(millis));
        user.setEmail(emailPrefix + stamp + emailDomain);
        user.setAddress(randomString + " fake address");
        user.setCity(randomString + " fake city");
        user.setExternalId(stamp);
        Random r = new Random();
        user.setCountryId(r.nextInt(30 - 1) + 1);

        return user;
    }

    public static OTTUser getOttUserById(int userId) {

        // OttUser/action/get
        GetOttUserBuilder getOttUserBuilder = OttUserService.get();
        getOttUserBuilder.setKs(getAdministratorKs());
        getOttUserBuilder.setUserId(userId);
        Response<OTTUser> userResponse = executor.executeSync(getOttUserBuilder);

        return userResponse.results;
    }

    public static String getKs(int userId) {
        OTTUser ottUser = getOttUserById(userId);

        // ottUser/action/login
        LoginOttUserBuilder loginOttUserBuilder = OttUserService.login(partnerId, ottUser.getUsername(), defaultUserPassword, null, null);
        Response<LoginResponse> loginResponse = executor.executeSync(loginOttUserBuilder);

        return loginResponse.results.getLoginSession().getKs();
    }

    public static String getKs(int userId, String udid) {
        OTTUser ottUser = getOttUserById(userId);

        // ottUser/action/login
        LoginOttUserBuilder loginOttUserBuilder = OttUserService.login(partnerId, ottUser.getUsername(), defaultUserPassword, null, udid);
        Response<LoginResponse> loginResponse = executor.executeSync(loginOttUserBuilder);

        return loginResponse.results.getLoginSession().getKs();
    }

    public static Map<String, StringValue> dynamicDataMapBuilder(String key, String value) {
        Map<String, StringValue> dynamicData = new HashMap<>();

        String sk = key;
        StringValue sv = new StringValue();
        sv.setValue(value);
        dynamicData.put(sk, sv);

        return dynamicData;
    }



}
