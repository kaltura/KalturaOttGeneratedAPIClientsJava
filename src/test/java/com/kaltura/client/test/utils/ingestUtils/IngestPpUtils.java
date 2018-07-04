package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.services.PricePlanService;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.PricePlan;
import com.kaltura.client.types.PricePlanFilter;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;

public class IngestPpUtils extends BaseIngestUtils {

    private static final String ingestDataResultPath = "Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.";
    private static final String ingestStatusMessagePath = ingestDataResultPath + "Status.Message";
    private static final String ingestReportIdPath = ingestDataResultPath + "ReportId";

    @Accessors(fluent = true)
    @Data
    public static class PpData {
        @Setter(AccessLevel.NONE) private boolean isActive = true;
        @Setter(AccessLevel.NONE) private String ppCode;

        private boolean isRenewable = false;

        private String fullLifeCycle;
        private String viewLifeCycle;
        private String price;
        private String currency;
        private String discount;

        private Integer maxViews;
        private Integer recurringPeriods;
    }

    /**
     * IMPORTANT: In order to update or delete existed price plan use pricePlan.getName() as "ppCode"
     */
    public static PricePlan insertPp(PpData ppData) {
        final int DEFAULT_MAX_VIEWS = 0;
        final int DEFAULT_RECURRING_PERIODS = 1;

        final int defaultPercentageOfDiscount = 100;
        DiscountModule discountModule = IngestFixtureData.getDiscount(defaultPercentageOfDiscount);

        ppData.ppCode = getRandomValue("AUTOPricePlan_", MAX_RANDOM_VALUE);

        if (ppData.fullLifeCycle == null) {
            ppData.fullLifeCycle = FIVE_MINUTES_PERIOD;
        }
        if (ppData.viewLifeCycle == null) {
            ppData.viewLifeCycle = FIVE_MINUTES_PERIOD;
        }
        if (ppData.maxViews == null) {
            ppData.maxViews = DEFAULT_MAX_VIEWS;
        }
        if (ppData.price == null) {
            ppData.price = getProperty(PRICE_CODE_AMOUNT);
        }
        if (ppData.currency == null) {
            ppData.currency = EUR.getValue();
        }
        if (ppData.discount == null) {
            ppData.discount = discountModule.toParams().get("code").toString();
        }
        if (ppData.recurringPeriods == null) {
            ppData.recurringPeriods = DEFAULT_RECURRING_PERIODS;
        }

        String reqBody = buildIngestPpXml(ppData, INGEST_ACTION_INSERT);
        Response resp = executeIngestPpRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].trim().replaceAll("\\.", "");

        PricePlanFilter filter = new PricePlanFilter();
        filter.setIdIn(id);

        return executor.executeSync(PricePlanService.list(filter)
                .setKs(getOperatorKs()))
                .results.getObjects().get(0);
    }

    public static PricePlan updatePp(String ppCode, PpData ppData) {
        ppData.ppCode = ppCode;

        String reqBody = buildIngestPpXml(ppData, INGEST_ACTION_UPDATE);
        Response resp = executeIngestPpRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].trim().replaceAll("\\.", "");

        PricePlanFilter filter = new PricePlanFilter();
        filter.setIdIn(id);

        return executor.executeSync(PricePlanService.list(filter)
                .setKs(getOperatorKs()))
                .results.getObjects().get(0);

        // TODO: 7/1/2018 add wait until PricePlanService.list(filter) is updated in case needed
    }

    public static void deletePp(String ppCode) {
        PpData ppData = new PpData();
        ppData.ppCode = ppCode;
        String reqBody = buildIngestPpXml(ppData, INGEST_ACTION_DELETE);

        Response resp = executeIngestPpRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        assertThat(resp.asString()).contains("delete succeeded");

        // TODO: 7/1/2018 add wait until SubscriptionService.list(filter) is empty in case needed
    }

    //private methods
    private static Response executeIngestPpRequest(String reqBody) {
        Response resp =
                given()
                        .header(contentTypeXml)
                        .header(soapActionIngestBusinessModules)
                        .body(reqBody)
                        .when()
                        .post(ingestUrl);

        Logger.getLogger(IngestPpUtils.class).debug(reqBody);
        Logger.getLogger(IngestPpUtils.class).debug(resp.asString());

        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");

        return resp;
    }

    private static String buildIngestPpXml(PpData ppData, String action) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestPP.xml");

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", "reportIngestPricePlan");

        // price plan
        Element pp = (Element) ingest.getElementsByTagName("price_plan").item(0);
        pp.setAttribute("code", ppData.ppCode);
        pp.setAttribute("action", action);
        pp.setAttribute("is_active", Boolean.toString(ppData.isActive));

        if (action.equals(INGEST_ACTION_DELETE)) {
            return uncommentCdataSection(docToString(doc));
        }

        // full life cycles
        pp.getElementsByTagName("full_life_cycle").item(0).setTextContent(ppData.fullLifeCycle);

        // view life cycle
        pp.getElementsByTagName("view_life_cycle").item(0).setTextContent(ppData.viewLifeCycle);

        // max views
        pp.getElementsByTagName("max_views").item(0).setTextContent(String.valueOf(ppData.maxViews));

        // price code
        pp.getElementsByTagName("price").item(0).setTextContent(ppData.price);
        pp.getElementsByTagName("currency").item(0).setTextContent(ppData.currency);

        // discount
        pp.getElementsByTagName("discount").item(0).setTextContent(ppData.discount);

        // is renewable
        pp.getElementsByTagName("is_renewable").item(0).setTextContent(Boolean.toString(ppData.isRenewable));

        // recurring periods
        pp.getElementsByTagName("recurring_periods").item(0).setTextContent(String.valueOf(ppData.recurringPeriods));

        // uncomment cdata
        return uncommentCdataSection(docToString(doc));
    }
}
