package com.kaltura.client.test.utils;

import com.kaltura.client.Client;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;

import java.util.Random;

import static com.kaltura.client.test.tests.BaseTest.administratorKs;

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

    public static String getUserNameFromId(int userId) {
        Client client = BaseTest.getClient(administratorKs);
        client.setUserId(userId);
        Response<OTTUser> userResponse = OttUserServiceImpl.get(client);

        return userResponse.results.getUsername();
    }
}
