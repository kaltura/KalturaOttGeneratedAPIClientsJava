package com.kaltura.client.test;

import java.util.ResourceBundle;

public class Properties {

    private static ResourceBundle resourceBundle;

    // Url properties
    public static final String API_BASE_URL = "http://34.249.122.223:8080";
    public static final String API_URL_VERSION = "v4_8";
    public static final String SOAP_BASE_URL = "http://34.249.122.223:8030";
    public static final String INGEST_REPORT_URL = "http://34.249.122.223:5823";

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

    // file types
    public static final String WEB_FILE_TYPE = "web_file_type";
    public static final String MOBILE_FILE_TYPE = "mobile_file_type";

    // ingest
    public static final String INGEST_USER_NAME = "ingest_user_username";
    public static final String INGEST_USER_PASSWORD = "ingest_user_password";

    public static final String INGEST_ACTION_INSERT = "insert";

    // price codes
    public static final String AMOUNT_4_99_EUR = "amount_4_99_eur"; // 4.99
    public static final String CURRENCY_EUR = "EUR";

    // usage modules
    public static final String ONE_DAY_USAGE_MODULE = "one_day_usage_module"; // module has 1 Day life cycles, 0 maximum views and 5 minute waiver

    // product codes
    public static final String DEFAULT_PRODUCT_CODE = "default_product_code";

    // discount modules
    public static final String FIFTY_PERCENTS_ILS_DISCOUNT_NAME = "fifty_percents_ils_discount_name";


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
