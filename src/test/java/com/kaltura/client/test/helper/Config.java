package com.kaltura.client.test.helper;

import java.util.ResourceBundle;

public class Config {

    private static ResourceBundle resourceBundle;

    //url properties
    public static final String API_BASE_URL = "http://34.249.122.223:8080";
    public static final String API_URL_VERSION = "v4_8";

    //db properties
    // TODO: 3/19/2018 encrypt db username and password in test.properties file
    public static final String DB_URL = "db_url";
    public static final String DB_USER = "db_user";
    public static final String DB_PASSWORD = "db_password";

    //request properties
    public static final int PARTNER_ID = 203;
    public static final String API_REQUEST_VERSION = "4.8.1";

    //global user properties
    public static final String GLOBAL_USER_PASSWORD = "password";


    public static String getProperty(String propertyKey) {
        if (resourceBundle == null)
            resourceBundle = ResourceBundle.getBundle("test");
        return resourceBundle.getString(propertyKey);
    }

    //todo global list
    // TODO: 3/8/2018 talk with Elram about DB cleanup
    // TODO: 3/8/2018 add relevant data assertions (include optional params) to impl services
    // TODO: 3/12/2018 open conference page with all the documentation problems
    // TODO: 12/MAR/2018 decide if we need that autoskip logic for tests with known opened bugs:
    // https://dzone.com/articles/how-to-automatically-skip-tests-based-on-defects-s
    // TODO: 3/19/2018 update readme file with project structure and list of services
    // TODO: 3/19/2018 find how to run specific tests according to api version
}
