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
    public static final String IS_OPC_GROUP = "is_opc_group";
    public static final String OPC_PARTNER_ID = "opc_partner_id";
    public static final String DEFAULT_TIMEOUT_IN_SEC = "default_timeout_in_sec";

    // global users
    public static final String DEFAULT_USER_PASSWORD = "default_user_password";

    // file types
    public static final String WEB_FILE_TYPE = "web_file_type";
    public static final String MOBILE_FILE_TYPE = "mobile_file_type";

    // channels
    public static final String DEFAULT_CHANNEL = "default_channel";
    // automatic channel with "Cut Tags Type"="Or", Tags "Series name"="Shay_Series;" and "Free"="Shay_Series;"

    // collections
    public static final String DEFAULT_COLLECTION = "default_collection";

    // discount code id
    public static final String DISCOUNT_CODE_ID = "discount_code_id";

    // price codes values
    public static final String PRICE_CODE_AMOUNT = "price_code_amount"; // 4.99
    public static final String COMMON_PRICE_CODE_AMOUNT = "5"; // TODO: should we document it as requirement?

    // maximum objects at list response for validating JSON schema
    public static final int MAX_OBJECTS_AT_LIST_RESPONSE = 20;

    // paymentGateway adapter url
    public static final String PAYMENT_GATEWAY_ADAPTER_URL = "http://172.31.6.89:90/PGAdapter/Service.svc";

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

    // payment gateway external id
    public static final String PAYMENT_GATEWAY_EXTERNAL_ID = "payment_gateway_external_id";

    // processing apps logs from remote connection
    public static final String PHOENIX_SERVER_DOMAIN_NAME = "phoenix_server_domain_name";
    public static final String PHOENIX_SERVER_USER_NAME = "phoenix_server_user_name";
    public static final String PHOENIX_SERVER_PASSWORD = "phoenix_server_password";
    public static final String PHOENIX_SERVER_LOGS_DIRECTORY = "phoenix_server_logs_directory";
    public static final String PHOENIX_SERVER_LOGS_DIRECTORY_URL = "phoenix_server_logs_directory_url";
    public static final String PHOENIX_SERVER_LOG_FILE_NAME_PREFIX = "phoenix_server_logs_file_name_prefix";
    public static final String PHOENIX_SERVER_LOG_FILE_EXTENSION = "phoenix_server_logs_file_extension";
    public static final String PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH = "phoenix_server_logs_local_folder_path";
    public static final String REGRESSION_LOGS_LOCAL_FILE = "regression_logs_local_file";
    public static final String SHOULD_REGRESSION_LOGS_BE_SAVED = "should_regression_logs_be_saved";
    public static final String MAX_ALLOWED_PERCENTAGE = "max_allowed_percentage";
    public static final String CODE_PERFORMANCE_REPORT_FILE = "code_performance_report_file";
    public static final String MAX_ALLOWED_EXECUTION_TIME_IN_SEC = "max_allowed_execution_time_in_sec";

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
