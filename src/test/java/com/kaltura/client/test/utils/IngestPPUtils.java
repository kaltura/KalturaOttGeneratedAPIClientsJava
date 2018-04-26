package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.types.PricePlan;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Optional;

import static com.kaltura.client.test.IngestConstants.CURRENCY_EUR;
import static com.kaltura.client.test.Properties.*;
import static io.restassured.path.xml.XmlPath.from;

public class IngestPPUtils extends BaseUtils {

    private static String DEFAULT_ACTION_VALUE = "insert";
    private static boolean DEFAULT_IS_ACTIVE_VALUE = true;
    private static String DEFAULT_FULL_LIFE_CYCLE_VALUE = "5 Minutes";
    private static String DEFAULT_VIEW_LIFE_CYCLE_VALUE = "5 Minutes";
    private static int DEFAULT_MAX_VIEWS_VALUE = 0;
    private static boolean DEFAULT_IS_RENEWABLE_VALUE = false;
    private static int DEFAULT_RECURRING_PERIODS_VALUE = 1;

    // ingest new PP
    public static PricePlan ingestPP(Optional<String> action, Optional<String> ppCode, Optional<Boolean> isActive,
                                      Optional<String> fullLifeCycle, Optional<String> viewLifeCycle, Optional<Integer> maxViews,
                                      Optional<String> price, Optional<String> currency,
                                      Optional<String> discount, Optional<Boolean> isRenewable,
                                      Optional<Integer> recurringPeriods) {
        String ppCodeValue = ppCode.orElse(getRandomValue("AUTOPricePlan_", 9999999999L));
        String actionValue = action.orElse(DEFAULT_ACTION_VALUE);
        boolean isActiveValue = isActive.orElse(DEFAULT_IS_ACTIVE_VALUE);
        String fullLifeCycleValue = fullLifeCycle.orElse(DEFAULT_FULL_LIFE_CYCLE_VALUE);
        String viewLifeCycleValue = viewLifeCycle.orElse(DEFAULT_VIEW_LIFE_CYCLE_VALUE);
        int maxViewsValue = maxViews.orElse(DEFAULT_MAX_VIEWS_VALUE);
        String priceValue = price.orElse(getProperty(PRICE_CODE_AMOUNT));
        String currencyValue = currency.orElse(CURRENCY_EUR);
        String discountValue = discount.orElse(getProperty(HUNDRED_PERCENTS_UKP_DISCOUNT_NAME));
        boolean isRenewableValue = isRenewable.orElse(DEFAULT_IS_RENEWABLE_VALUE);
        int recurringPeriodsValue = recurringPeriods.orElse(DEFAULT_RECURRING_PERIODS_VALUE);

        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";
        HashMap headermap = new HashMap<>();
        headermap.put("Content-Type", "text/xml;charset=UTF-8");
        headermap.put("SOAPAction", "http://tempuri.org/IService/IngestBusinessModules");

        String reqBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:IngestBusinessModules><tem:username>" + getProperty(INGEST_BUSINESS_MODULE_USER_USERNAME) + "</tem:username><tem:password>" +
                        getProperty(INGEST_BUSINESS_MODULE_USER_PASSWORD) + "</tem:password><tem:xml>" +
                "         <![CDATA[" + buildIngestPpXML(actionValue, ppCodeValue, isActiveValue, fullLifeCycleValue,
                            viewLifeCycleValue, maxViewsValue, priceValue, currencyValue, discountValue,
                            isRenewableValue, recurringPeriodsValue) +
                "                 ]]></tem:xml></tem:IngestBusinessModules>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        Response resp = RestAssured.given()
                .log().all()
                .headers(headermap)
                .body(reqBody)
                .post(url);

        Logger.getLogger(IngestPPUtils.class).debug(reqBody);
        Logger.getLogger(IngestPPUtils.class).debug(resp.asString());

        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();
        //Logger.getLogger(IngestMPPUtils.class).debug("ReportId = " + reportId);

        url = getProperty(INGEST_REPORT_URL) + "/" + getProperty(PARTNER_ID) + "/" + reportId;
        resp = RestAssured.given()
                .log().all()
                .get(url);

        Logger.getLogger(IngestPPUtils.class).debug(resp.asString());
        //System.out.println(resp.asString().split(" = ")[1].replaceAll("\\.", ""));

        String id = resp.asString().split(" = ")[1].trim().replaceAll("\\.", "");
        //Logger.getLogger(IngestPPUtils.class).debug("ID: " + id);

        PricePlan pricePlan = new PricePlan();
        pricePlan.setId(Long.valueOf(id));
        pricePlan.setMaxViewsNumber(maxViewsValue);
        pricePlan.setIsRenewable(isRenewableValue);
        pricePlan.setRenewalsNumber(recurringPeriodsValue);
        // TODO: complete COMMENTED IF NEEDED
        //pricePlan.setFullLifeCycle();
        //pricePlan.setViewLifeCycle();
        //pricePlan.setPriceDetailsId();
        //pricePlan.setDiscountId();
        return pricePlan;
    }

    private static String buildIngestPpXML(String action, String ppCode, boolean isActive, String fullLifeCycle,
                                           String viewLifeCycle, int maxViews, String price, String currency,
                                           String discount, boolean isRenewable, int recurringPeriods) {
        String id = "reportIngestPricePlan" + action.substring(0, 1).toUpperCase() + action.substring(1);
        return "<ingest id=\"" + id + "\">\n" +
                    "<price_plans>\n" +
                        "<price_plan code=\"" + ppCode + "\"  action=\"" + action + "\" is_active=\"" + isActive + "\">\n" +
                            "<full_life_cycle>" + fullLifeCycle + "</full_life_cycle>\n" +
                            "<view_life_cycle>" + viewLifeCycle + "</view_life_cycle>\n" +
                            "<max_views>" + maxViews + "</max_views>\n" +
                            "<price_code>\n" +
                                "<price>" + price + "</price>\n" +
                                "<currency>" + currency + "</currency>\n" +
                            "</price_code>\n" +
                            "<discount>" + discount + "</discount>\n" +
                            "<is_renewable>" + isRenewable + "</is_renewable>\n" +
                            "<recurring_periods>" + recurringPeriods + "</recurring_periods>\n" +
                        "</price_plan>\n" +
                    "</price_plans>\n" +
                "</ingest>\n";
    }
}
