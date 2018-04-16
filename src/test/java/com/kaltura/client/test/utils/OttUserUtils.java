package com.kaltura.client.test.utils;

import com.kaltura.client.Client;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;

import java.util.Random;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.tests.BaseTest.administratorKs;
import static com.kaltura.client.test.tests.BaseTest.getClient;

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
        Random r = new Random();
        user.setCountryId(r.nextInt(30 - 1) + 1);

        return user;
    }

    public static OTTUser getUserById(int userId) {
        Client client = getClient(administratorKs);
        client.setUserId(userId);
        Response<OTTUser> userResponse = OttUserServiceImpl.get(client);

        return userResponse.results;
    }

    public static String getKs(int userId) {
        Client client = getClient(null);
        OTTUser ottUser = getUserById(userId);
        Response<LoginResponse > loginResponse = OttUserServiceImpl.login(client, PARTNER_ID, ottUser.getUsername(), GLOBAL_USER_PASSWORD, null, null);
        return loginResponse.results.getLoginSession().getKs();
    }
}
