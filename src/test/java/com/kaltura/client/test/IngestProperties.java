package com.kaltura.client.test;

public class IngestProperties extends Properties {

    public static final String INGEST_ACTION_INSERT = "insert";
    public static Long MAX_RANDOM_GENERATED_VALUE_4_INGEST = 9999999999L;
    public static final String INGEST_VOD_DEFAULT_THUMB = "http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png";

    // file types
    public static final String WEB_FILE_TYPE = "web_file_type";
    public static final String MOBILE_FILE_TYPE = "mobile_file_type";

    // ingest
    public static final String INGEST_USER_NAME = "ingest_user_username";
    public static final String INGEST_USER_PASSWORD = "ingest_user_password";
    public static final String INGEST_BUSINESS_MODULE_USER_NAME = "ingest_business_module_user_username";
    public static final String INGEST_BUSINESS_MODULE_USER_PASSWORD = "ingest_business_module_user_password";

    // channels
    public static final String DEFAULT_CHANNEL = "default_channel"; // automatic channel with "Cut Tags Type"="Or", Tags "Series name"="Shay_Series;" and "Free"="Shay_Series;"

    // price codes
    public static final String PRICE_CODE_AMOUNT_4_99 = "price_code_amount_4_99"; // 4.99

    // usage modules
    public static final String DEFAULT_USAGE_MODULE_4_INGEST_PPV = "default_usage_module_4_ingest_ppv"; // module has 10 Minutes life cycles, 0 maximum views
    public static final String DEFAULT_USAGE_MODULE_4_INGEST_MPP = "default_usage_module_4_ingest_mpp"; // module has 1 Day life cycles, 0 maximum views

    // product codes
    public static final String DEFAULT_PRODUCT_CODE = "default_product_code";

    // discount modules
    public static final String FIFTY_PERCENTS_ILS_DISCOUNT_NAME = "fifty_percents_ils_discount_name";
    public static final String HUNDRED_PERCENTS_UKP_DISCOUNT_NAME = "hundred_percents_ukp_discount_name";

    // life cycles periods
    public static final String FIVE_MINUTES_PERIOD = "5 Minutes";
}
