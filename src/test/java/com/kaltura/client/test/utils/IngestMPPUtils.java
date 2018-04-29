package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.types.Subscription;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Optional;

import static com.kaltura.client.test.Properties.*;
import static io.restassured.path.xml.XmlPath.from;

public class IngestMPPUtils extends BaseUtils {

    private static String DEFAULT_ACTION_VALUE = "insert";
    private static boolean DEFAULT_IS_ACTIVE_VALUE = true;
    private static String DEFAULT_TITLE_VALUE = "Ingest MPP title";
    private static String DEFAULT_DESCRIPTION_VALUE = "Ingest MPP description";
    private static String DEFAULT_START_DATE_VALUE = "20/03/2016 00:00:00";
    private static String DEFAULT_END_DATE_VALUE = "20/03/2099 00:00:00";
    private static boolean DEFAULT_IS_RENEWABLE_VALUE = false;
    private static int DEFAULT_GRACE_PERIOD_VALUE = 0;

    // TODO: THIS VALUES RELATED TO OUR ENV ONLY discuss with Alon
    private static String DEFAULT_COUPON_GROUP_VALUE =
            "<coupon_group_id>\n" +
                    "<start_date>01/05/2017 00:00:00</start_date>\n" +
                    "<end_date>31/12/2099 23:59:59</end_date>\n" +
                    "<code>100% unlimited</code>\n" +
                    "</coupon_group_id>\n" +
                    "<coupon_group_id>\n" +
                    "<start_date>01/05/2017 00:00:00</start_date>\n" +
                    "<end_date>31/05/2017 23:59:59</end_date>\n" +
                    "<code>Expired coupon group 1</code>\n" +
                    "</coupon_group_id>";

    private static String DEFAULT_PRODUCT_CODES_VALUE =
            "<product_code>\n" +
                    "<code>ProductCode1</code>\n" +
                    "<verification_payment_gateway>Google</verification_payment_gateway>\n" +
                    "</product_code>\n" +
                    "<product_code>\n" +
                    "<code>ProductCode2</code>\n" +
                    "<verification_payment_gateway>Apple</verification_payment_gateway>\n" +
                    "</product_code>";

    // ingest new MPP
    public static Subscription ingestMPP(Optional<String> action, Optional<String> mppCode, Optional<Boolean> isActive,
                                         Optional<String> title, Optional<String> description, Optional<String> startDate,
                                         Optional<String> endDate, Optional<String> internalDiscount,
                                         Optional<String> productCode, Optional<Boolean> isRenewable,
                                         Optional<Integer> gracePeriodMinute, Optional<String> pricePlanCode1,
                                         Optional<String> pricePlanCode2, Optional<String> channel1,
                                         Optional<String> channel2, Optional<String> fileType1,
                                         Optional<String> fileType2, Optional<String> couponGroup, Optional<String> productCodes) {
        String mppCodeValue = mppCode.orElse(getRandomValue("MPP_", 9999999999L));
        String actionValue = action.orElse(DEFAULT_ACTION_VALUE);
        boolean isActiveValue = isActive.orElse(DEFAULT_IS_ACTIVE_VALUE);
        String titleValue = title.orElse(DEFAULT_TITLE_VALUE);
        String descriptionValue = description.orElse(DEFAULT_DESCRIPTION_VALUE);
        String startDateValue = startDate.orElse(DEFAULT_START_DATE_VALUE);
        String endDateValue = endDate.orElse(DEFAULT_END_DATE_VALUE);
        String internalDiscountValue = internalDiscount.orElse(getProperty(HUNDRED_PERCENTS_UKP_DISCOUNT_NAME));
        String productCodeValue = productCode.orElse("");
        boolean isRenewableValue = isRenewable.orElse(DEFAULT_IS_RENEWABLE_VALUE);
        int gracePeriodMinuteValue = gracePeriodMinute.orElse(DEFAULT_GRACE_PERIOD_VALUE);
        String pricePlanCode1Value = pricePlanCode1.orElse(getProperty(DEFAULT_USAGE_MODULE_4_INGEST_MPP));
        String pricePlanCode2Value = pricePlanCode2.orElse("");
        String channel1Value = channel1.orElse(getProperty(DEFAULT_CHANNEL));
        String channel2Value = channel2.orElse("");
        String fileType1Value = fileType1.orElse("");
        String fileType2Value = fileType2.orElse("");
        String couponGroupValue = couponGroup.orElse(DEFAULT_COUPON_GROUP_VALUE);
        String productCodesValue = productCodes.orElse(DEFAULT_PRODUCT_CODES_VALUE);


        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";
        HashMap headermap = new HashMap<>();
        headermap.put("Content-Type", "text/xml;charset=UTF-8");
        headermap.put("SOAPAction", "http://tempuri.org/IService/IngestBusinessModules");

        String reqBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:IngestBusinessModules><tem:username>" + getProperty(INGEST_BUSINESS_MODULE_USER_USERNAME) + "</tem:username><tem:password>" +
                        getProperty(INGEST_BUSINESS_MODULE_USER_PASSWORD) + "</tem:password><tem:xml>" +
                "         <![CDATA[" + buildIngestMppXML(actionValue, mppCodeValue, isActiveValue, titleValue,
                        descriptionValue, startDateValue, endDateValue, internalDiscountValue, productCodeValue,
                        isRenewableValue, gracePeriodMinuteValue, pricePlanCode1Value, pricePlanCode2Value,
                        channel1Value, channel2Value, fileType1Value, fileType2Value, couponGroupValue, productCodesValue) +
                "                 ]]></tem:xml></tem:IngestBusinessModules>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        Response resp = RestAssured.given()
                .log().all()
                .headers(headermap)
                .body(reqBody)
                .post(url);

        //Logger.getLogger(IngestMPPUtils.class).debug(reqBody);
        Logger.getLogger(IngestMPPUtils.class).debug(resp.asString());

        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();
        //Logger.getLogger(IngestMPPUtils.class).debug("ReportId = " + reportId);

        url = getProperty(INGEST_REPORT_URL) + "/" + getProperty(PARTNER_ID) + "/" + reportId;
        resp = RestAssured.given()
                .log().all()
                .get(url);

        Logger.getLogger(IngestMPPUtils.class).debug(resp.asString());
        //System.out.println(resp.asString().split(" = ")[1].replaceAll("\\.", ""));

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "");

        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setName(titleValue);
        subscription.setDescription(descriptionValue);
        // TODO: complete COMMENTED IF NEEDED
        //subscription.setStartDate();
        //subscription.setEndDate();
        //subscription.setDiscountModule();
        //subscription.setProductCodes();
        subscription.isRenewable(String.valueOf(isRenewableValue));
        subscription.setGracePeriodMinutes(gracePeriodMinuteValue);
        //subscription.setPricePlanIds();
        //subscription.setChannels();
        //subscription.setFileTypes();
        //subscription.setCouponsGroups();
        return subscription;
    }

    private static String buildIngestMppXML(String action, String mppCode, boolean isActive, String title, String description,
                                            String startDate, String endDate, String internalDiscount, String productCode,
                                            boolean isRenewable, int gracePeriodMinute, String pricePlanCode1,
                                            String pricePlanCode2, String channel1, String channel2, String fileType1,
                                            String fileType2, String couponGroup, String productCodes) {
        return "<ingest id=\"" + mppCode + "\">\n" +
                "<multi_price_plans>\n" +
                "<multi_price_plan code=\"" + mppCode + "\" action=\"" + action + "\" is_active=\"" + isActive + "\">\n" +
                "<titles>\n" +
                "<title lang=\"eng\">" + title + "</title>\n" +
                "</titles>\n" +
                "<descriptions>\n" +
                "<description lang=\"eng\">" + description + "</description>" +
                "</descriptions>\n" +
                "<start_date>" + startDate + "</start_date>\n" +
                "<end_date>" + endDate + "</end_date>\n" +
                "<internal_discount>" + internalDiscount + "</internal_discount>\n" +
                "<coupon_group/>\n" +
                "<product_code>" + productCode + "</product_code>\n" +
                "<is_renewable>" + isRenewable + "</is_renewable>\n" +
                "<priview_module/>\n" +
                "<grace_period_minutes>" + gracePeriodMinute + "</grace_period_minutes>\n" +
                "<price_plan_codes>\n" +
                "<price_plan_code>" + pricePlanCode1 + "</price_plan_code>\n" +
                "<price_plan_code>" + pricePlanCode2 + "</price_plan_code>\n" +
                "</price_plan_codes>\n" +
                "<channels>\n" +
                "<channel>" + channel1 + "</channel>\n" +
                "<channel>" + channel2 + "</channel>\n" +
                "</channels>\n" +
                "<file_types>\n" +
                "<file_type>" + fileType1 + "</file_type>\n" +
                "<file_type>" + fileType2 + "</file_type>\n" +
                "</file_types>\n" +
                "<order_number/>\n" +
                "<subscription_coupon_group>" + couponGroup + "</subscription_coupon_group>\n" +
                "<product_codes>" + productCodes + "</product_codes>\n" +
                "</multi_price_plan>\n" +
                "</multi_price_plans>\n" +
                "</ingest>\n";
    }
}
