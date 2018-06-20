package com.kaltura.client.test;

import java.util.ResourceBundle;

public class Properties {

    private static ResourceBundle resourceBundle;

    // url properties
    public static final String API_BASE_URL = "api_base_url";
    public static final String API_VERSION = "api_version";
    public static final String INGEST_BASE_URL = "ingest_base_url";
    public static final String INGEST_REPORT_URL = "ingest_report_url";

    // DB properties
    public static final String DB_URL = "db_url";
    public static final String DB_USER = "db_user";
    public static final String DB_PASSWORD = "db_password";

    // Request properties
    public static final String PARTNER_ID = "partner_id";
    public static final String DEFAULT_TIMEOUT_IN_SEC = "default_timeout_in_sec";

    // global users
    public static final String DEFAULT_USER_PASSWORD = "default_user_password";

    // file types
    public static final String WEB_FILE_TYPE = "web_file_type";
    public static final String MOBILE_FILE_TYPE = "mobile_file_type";

    // media types ids
    public static final String MOVIE_MEDIA_TYPE_ID = "movie_media_type_id";
    public static final String EPISODE_MEDIA_TYPE_ID = "episode_media_type_id";

    // channels
    public static final String DEFAULT_CHANNEL = "default_channel"; // automatic channel with "Cut Tags Type"="Or", Tags "Series name"="Shay_Series;" and "Free"="Shay_Series;"

    // price codes values
    public static final String PRICE_CODE_AMOUNT = "price_code_amount"; // 4.99
    public static final String COMMON_PRICE_CODE_AMOUNT = "5"; // TODO: should we document it as requirement?

    // maximum objects at list response for validating JSON schema
    public static final int MAX_OBJECTS_AT_LIST_RESPONSE = 20;

    // price plans
    // INGEST doesn't allow create PP with multi-currencies
    // TODO: should we document it as requirement? price plan having few locales (EUR + few others) with different prices
    // should we leave there name or id if we do use name in ingest
    public static final String PRICE_PLAN_WITH_MULTI_CURRENCIES = "lior's PriceCode price plan";
    // TODO: should we document it as requirement? price plan having few locales (USD + few others) with different prices and discount for subscriptions 50%
    // should we leave there name or id if we do use name in ingest
    public static final String PRICE_PLAN_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS = "shmulik_multi_currency_with_discount";
    // TODO: should we document it as requirement? price plan having few locales (USD + few others) with different prices and fixed discount for subscriptions 1
    // should we leave there name or id if we do use name in ingest
    public static final String PRICE_PLAN_WITH_MULTI_CURRENCIES_AND_DISCOUNT_FIXED = "shmulik_multi_currency_with_discount (fixed amount)";

    // PPVs
    // Ingest doesn't allow create PP with multi currencies
    // TODO: should we document it as requirement?
    public static final String PPV_WITH_MULTI_CURRENCIES_AND_DISCOUNT_PERCENTS = "multi price code with 50% discount";
    // TODO: should we document it as requirement?
    public static final String PPV_WITH_MULTI_CURRENCIES_AND_FIXED_DISCOUNT = "multi price code with fixed amount discount";

    //cycles
    public static final int CYCLE_1_DAY = 1440; // in minutes

    // usage modules
    public static final String DEFAULT_USAGE_MODULE_4_INGEST_PPV = "default_usage_module_4_ingest_ppv"; // module has 10 Minutes life cycles, 0 maximum views
    public static final String DEFAULT_USAGE_MODULE_4_INGEST_MPP = "default_usage_module_4_ingest_mpp"; // module has 1 Day life cycles, 0 maximum views

    // product codes
    public static final String DEFAULT_PRODUCT_CODE = "default_product_code";

    public static String getProperty(String propertyKey) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("test");
        }
        return resourceBundle.getString(propertyKey);
    }
}

// todo global list
// TODO: 3/12/2018 open conference page with all the documentation problems
// TODO: 12/MAR/2018 decide if we need that autoskip logic for tests with known opened bugs:
// https://dzone.com/articles/how-to-automatically-skip-tests-based-on-defects-s
// TODO: 3/19/2018 update readme file with project structure and list of services
