package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.services.PricePlanService;
import com.kaltura.client.services.PricePlanService.ListPricePlanBuilder;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.PricePlan;
import com.kaltura.client.types.PricePlanFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Optional;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;

public class IngestPpUtils extends BaseIngestUtils {

    // INGEST PP PARAMS
    static final boolean PP_DEFAULT_IS_ACTIVE_VALUE = true;
    static final boolean PP_DEFAULT_IS_RENEWABLE_VALUE = false;
    static final int PP_DEFAULT_MAX_VIEWS_VALUE = 0;
    static final int PP_DEFAULT_RECURRING_PERIODS_VALUE = 1;

    /**
     * IMPORTANT: please delete inserted by that method items
     *
     * @param action           - can be "insert", "update" and "delete"
     * @param ppCode           - should have value in case "action" one of {"update" and "delete"}
     * @param isActive
     * @param fullLifeCycle
     * @param viewLifeCycle
     * @param maxViews
     * @param price
     * @param currency
     * @param discount
     * @param isRenewable
     * @param recurringPeriods
     * @return PricePlan data
     * <p>
     * to update or delete existed price plan use corresponded action and value pricePlan.getName() as "ppCode"
     * (where pricePlan is a variable that contains price plan data)
     * <p>
     * !!!Only created by that method PP can be deleted/updated!!!
     */
    public static PricePlan ingestPP(Optional<String> action, Optional<String> ppCode, Optional<Boolean> isActive, Optional<String> fullLifeCycle, 
                                     Optional<String> viewLifeCycle, Optional<Integer> maxViews, Optional<String> price, Optional<String> currency, 
                                     Optional<String> discount, Optional<Boolean> isRenewable, Optional<Integer> recurringPeriods) {
        String ppCodeValue = ppCode.orElse(getRandomValue("AUTOPricePlan_", MAX_RANDOM_VALUE));
        String actionValue = action.orElse(INGEST_ACTION_INSERT);
        boolean isActiveValue = isActive.orElse(PP_DEFAULT_IS_ACTIVE_VALUE);
        String fullLifeCycleValue = fullLifeCycle.orElse(FIVE_MINUTES_PERIOD);
        String viewLifeCycleValue = viewLifeCycle.orElse(FIVE_MINUTES_PERIOD);
        int maxViewsValue = maxViews.orElse(PP_DEFAULT_MAX_VIEWS_VALUE);
        String priceValue = price.orElse(getProperty(PRICE_CODE_AMOUNT));
        String currencyValue = currency.orElse(EUR.getValue());
        int defaultPercentageOfDiscount4IngestMpp = 100;
        DiscountModule discountModule = IngestFixtureData.getDiscount(defaultPercentageOfDiscount4IngestMpp);
        String discountValue = discount.orElse(discountModule.toParams().get("code").toString());
        boolean isRenewableValue = isRenewable.orElse(PP_DEFAULT_IS_RENEWABLE_VALUE);
        int recurringPeriodsValue = recurringPeriods.orElse(PP_DEFAULT_RECURRING_PERIODS_VALUE);

        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody = IngestPpUtils.buildIngestPpXml(actionValue, ppCodeValue, isActiveValue, fullLifeCycleValue,
                viewLifeCycleValue, maxViewsValue, priceValue, currencyValue, discountValue, isRenewableValue, recurringPeriodsValue);

        io.restassured.response.Response resp =
                given()
                    .header(contentTypeXml)
                    .header(soapActionIngestBusinessModules)
                    .body(reqBody)
                .when()
                    .post(url);

        Logger.getLogger(IngestPpUtils.class).debug(reqBody);
        Logger.getLogger(IngestPpUtils.class).debug(resp.asString());

        // TODO: 6/20/2018 add response assertion 
        
        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();

        url = getProperty(INGEST_REPORT_URL) + "/" + getProperty(PARTNER_ID) + "/" + reportId;
        resp = given().get(url);

        Logger.getLogger(IngestPpUtils.class).debug(resp.asString());

        String id = resp.asString().split(" = ")[1].trim().replaceAll("\\.", "");

        PricePlanFilter filter = new PricePlanFilter();
        filter.setIdIn(id);
        ListPricePlanBuilder pricePlanBuilder = PricePlanService.list(filter);
        com.kaltura.client.utils.response.base.Response<ListResponse<PricePlan>> pricePlanListResponse =
                executor.executeSync(pricePlanBuilder.setKs(getOperatorKs()));
        return pricePlanListResponse.results.getObjects().get(0);
    }

    private static String buildIngestPpXml(String action, String ppCode, boolean isActive, String fullLifeCycle, String viewLifeCycle,
                                          int maxViews, String price, String currency, String discount, boolean isRenewable, int recurringPeriods) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestPP.xml");

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", "reportIngestPricePlan");

        // price plan
        Element pp = (Element) ingest.getElementsByTagName("price_plan").item(0);
        pp.setAttribute("code", ppCode);
        pp.setAttribute("action", action);
        pp.setAttribute("is_active", Boolean.toString(isActive));

        // full life cycles
        pp.getElementsByTagName("full_life_cycle").item(0).setTextContent(fullLifeCycle);

        // view life cycle
        pp.getElementsByTagName("view_life_cycle").item(0).setTextContent(viewLifeCycle);

        // max views
        pp.getElementsByTagName("max_views").item(0).setTextContent(String.valueOf(maxViews));

        // price code
        pp.getElementsByTagName("price").item(0).setTextContent(price);
        pp.getElementsByTagName("currency").item(0).setTextContent(currency);

        // discount
        pp.getElementsByTagName("discount").item(0).setTextContent(discount);

        // is renewable
        pp.getElementsByTagName("is_renewable").item(0).setTextContent(Boolean.toString(isRenewable));

        // recurring periods
        pp.getElementsByTagName("recurring_periods").item(0).setTextContent(String.valueOf(recurringPeriods));

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }
}
