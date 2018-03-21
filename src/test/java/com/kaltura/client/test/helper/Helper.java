package com.kaltura.client.test.helper;

import com.kaltura.client.types.OTTUser;

import java.util.Random;

public class Helper {

    //generate ottUser
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

    //generate random string
    public static String getRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        while (sb.length() < 12) {
            int index = (int) (r.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString().toLowerCase();
    }
}
