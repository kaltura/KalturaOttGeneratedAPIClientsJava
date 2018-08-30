package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.types.CouponsGroup;
import com.kaltura.client.types.Ppv;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static com.kaltura.client.services.PpvService.get;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static org.assertj.core.api.Assertions.assertThat;

public class IngestPpvUtils extends BaseIngestUtils {

    private static final String ingestDataResultPath = "Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.";
    private static final String ingestStatusMessagePath = ingestDataResultPath + "Status.Message";
    private static final String ingestReportIdPath = ingestDataResultPath + "ReportId";

    @Accessors(fluent = true)
    @Data
    public static class PpvData {
        @Setter(AccessLevel.NONE) private boolean isActive = true;
        @Setter(AccessLevel.NONE) private String ppvCode;

        private boolean isSubscriptionOnly = false;
        private boolean isFirstDeviceLimitation = false;

        private String description;
        private String discountCode;
        private String currency;
        private String usageModule;
        private String productCode;
        private String firstFileType;
        private String secondFileType;

        private Double price;

        private CouponsGroup couponGroup;
    }

    /** IMPORTANT: In order to update or delete existed ppv use ppv.getName() as "ppvCode" */

    public static Ppv insertPpv(PpvData ppvData) {
        ppvData.ppvCode = getRandomValue("PPV_");

        if (ppvData.description == null) { ppvData.description = ppvData.ppvCode; }
        if (ppvData.price == null) { ppvData.price = Double.valueOf(COMMON_PRICE_CODE_AMOUNT); }
        if (ppvData.currency == null) { ppvData.currency = EUR.getValue(); }
        if (ppvData.usageModule == null) { ppvData.usageModule = getProperty(DEFAULT_USAGE_MODULE_4_INGEST_PPV); }
        if (ppvData.productCode == null) { ppvData.productCode = getProperty(DEFAULT_PRODUCT_CODE); }
        if (ppvData.firstFileType == null) { ppvData.firstFileType = getProperty(WEB_FILE_TYPE); }
        if (ppvData.secondFileType == null) { ppvData.secondFileType = getProperty(MOBILE_FILE_TYPE); }

        String reqBody = buildIngestPpvXml(ppvData, INGEST_ACTION_INSERT);
        Response resp = executeIngestPpvRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

        // TODO: 7/1/2018 add wait until in case needed
        return executor.executeSync(get(Long.valueOf(id))
                .setKs(getOperatorKs()))
                .results;
    }

    public static Ppv updatePpv(String ppvCode, PpvData ppvData) {
        ppvData.ppvCode = ppvCode;

        String reqBody = buildIngestPpvXml(ppvData, INGEST_ACTION_UPDATE);
        Response resp = executeIngestPpvRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

        // TODO: 7/1/2018 add wait until in case needed
        return executor.executeSync(get(Long.valueOf(id))
                .setKs(getOperatorKs()))
                .results;
    }

    public static void deletePpv(String ppvCode) {
        PpvData ppvData = new PpvData();
        ppvData.ppvCode = ppvCode;
        String reqBody = buildIngestPpvXml(ppvData, INGEST_ACTION_DELETE);

        Response resp = executeIngestPpvRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        assertThat(resp.asString()).contains("delete succeeded");
    }

    // private methods
    private static Response executeIngestPpvRequest(String reqBody) {
        Response resp =
                given()
                        .header(contentTypeXml)
                        .header(soapActionIngestBusinessModules)
                        .body(reqBody)
                        .when()
                        .post(ingestUrl);

        Logger.getLogger(IngestPpvUtils.class).debug(reqBody);
        Logger.getLogger(IngestPpvUtils.class).debug(resp.asString());

        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");

        return resp;
    }

    private static String buildIngestPpvXml(PpvData ppvData, String action) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestPPV.xml");

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", ppvData.ppvCode);

        // ppv
        Element ppv = (Element) ingest.getElementsByTagName("ppv").item(0);
        ppv.setAttribute("code", ppvData.ppvCode);
        ppv.setAttribute("action", action);
        ppv.setAttribute("is_active", Boolean.toString(ppvData.isActive));

        if (action.equals(INGEST_ACTION_DELETE)) {
            return uncommentCdataSection(docToString(doc));
        }

        // description
        ppv.getElementsByTagName("description").item(0).setTextContent(ppvData.description);

        // price code
        ppv.getElementsByTagName("price").item(0).setTextContent(String.valueOf(ppvData.price));
        ppv.getElementsByTagName("currency").item(0).setTextContent(ppvData.currency);

        // usage module
        ppv.getElementsByTagName("usage_module").item(0).setTextContent(ppvData.usageModule);

        // discount code
        if (ppvData.discountCode != null) {
            ppv.getElementsByTagName("discountCode").item(0).setTextContent(ppvData.discountCode);
        }

        // subscription only
        ppv.getElementsByTagName("subscription_only").item(0).setTextContent(Boolean.toString(ppvData.isSubscriptionOnly));

        // first device limitation
        ppv.getElementsByTagName("first_device_limitation").item(0).setTextContent(Boolean.toString(ppvData.isFirstDeviceLimitation));

        // product_code
        ppv.getElementsByTagName("product_code").item(0).setTextContent(ppvData.productCode);

        // file types
        ppv.getElementsByTagName("file_type").item(0).setTextContent(ppvData.firstFileType);
        ppv.getElementsByTagName("file_type").item(1).setTextContent(ppvData.secondFileType);


        // coupon_group
        if (ppvData.couponGroup != null) {
            Element couponGroup = (Element) ppv.getElementsByTagName("coupon_group").item(0);
            couponGroup.getElementsByTagName("code").item(0).setTextContent(ppvData.couponGroup.getName());
        }

        // uncomment cdata
        return uncommentCdataSection(docToString(doc));
    }
}
