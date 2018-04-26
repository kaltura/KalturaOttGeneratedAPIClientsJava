package com.kaltura.client.test;

import java.util.ResourceBundle;

public class Properties {

    private static ResourceBundle resourceBundle;

    // Url properties
    public static final String API_BASE_URL = "http://api-preprod.ott.kaltura.com"; // "http://34.249.122.223:8080"
    public static final String SOAP_BASE_URL = "http://api-preprod.ott.kaltura.com:8030"; // "http://34.249.122.223:8030";
    public static final String INGEST_REPORT_URL = "http://api-preprod.ott.kaltura.com:5823"; // "http://34.249.122.223:5823";
    public static final String API_URL_VERSION = "v4_8_1";

    // DB properties
    // TODO: 3/19/2018 encrypt db username and password in test.properties file
    public static final String DB_URL = "db_url";
    public static final String DB_USER = "db_user";
    public static final String DB_PASSWORD = "db_password";

    // Request properties
    public static final int PARTNER_ID = 203;
    public static final String API_REQUEST_VERSION = "4.8.1";

    // Global user properties
    public static final String ADMINISTRATOR_USERNAME = "administrator_user_username";
    public static final String ADMINISTRATOR_PASSWORD = "administrator_user_password";

    public static final String OPERATOR_USERNAME = "operator_user_username";
    public static final String OPERATOR_PASSWORD = "operator_user_password";

    public static final String MANAGER_USERNAME = "manager_user_username";
    public static final String MANAGER_PASSWORD = "manager_user_password";

    public static final String GLOBAL_USER_PASSWORD = "password";

    // media types // TODO: ask if these types (from TVM edit VOD page) are default for all accounts
    public static final String MOVIE_MEDIA_TYPE = "Movie";
    public static final String EPISODE_MEDIA_TYPE = "Episode";
    public static final String LINEAR_MEDIA_TYPE = "Linear";

    // currencies
    public static final String CURRENCY_EUR = "EUR";

    // PG adapter data
    public static final String PG_DEFAULT_ADAPTER_URL = "http://172.31.6.89:90/PGAdapter/Service.svc";
    public static final String PG_DEFAULT_RENEW_URL = PG_DEFAULT_ADAPTER_URL + "?StateCode=0";
    public static final String PG_DEFAULT_SHARED_SECRET = "123456";
    public static final int PG_DEFAULT_PENDING_INTERVAL = 0;
    public static final int PG_DEFAULT_PENDING_RETRIES = 0;
    public static final int PG_DEFAULT_RENEW_INTERVAL_MINUTES = 15;
    public static final int PG_DEFAULT_RENEW_START_MINUTES =-5;
    public static final String PG_DEFAULT_PG_SETTINGS = "{}";

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
    // TODO: 3/22/2018 ask Elram where to open the apiException meesage not equal to description issue
}
