package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.services.SubscriptionService;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.CouponsGroup;
import com.kaltura.client.types.ProductCode;
import com.kaltura.client.types.Subscription;
import com.kaltura.client.types.SubscriptionFilter;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.BaseUtils.getFormattedTime;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;
import static java.util.TimeZone.getTimeZone;
import static org.assertj.core.api.Assertions.assertThat;

public class IngestMppUtils extends BaseIngestUtils {

    private static final String ingestDataResultPath = "Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.";
    private static final String ingestStatusMessagePath = ingestDataResultPath + "Status.Message";
    private static final String ingestReportIdPath = ingestDataResultPath + "ReportId";

    // TODO: THIS VALUES RELATED TO OUR ENV ONLY discuss with Alon
    private static final String DEFAULT_COUPON_GROUP =
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

    private static final String DEFAULT_PRODUCT_CODES =
            "<product_code>\n" +
                    "<code>ProductCode1</code>\n" +
                    "<verification_payment_gateway>Google</verification_payment_gateway>\n" +
                    "</product_code>\n" +
                    "<product_code>\n" +
                    "<code>ProductCode2</code>\n" +
                    "<verification_payment_gateway>Apple</verification_payment_gateway>\n" +
                    "</product_code>";

    @Accessors(fluent = true)
    @Data
    public static class MppData {
        @Setter(AccessLevel.NONE) private boolean isActive = true;
        @Setter(AccessLevel.NONE) private String mppCode;
        @Setter(AccessLevel.NONE) private String title;

        private boolean isRenewable = false;

        private String description;
        private String startDate;
        private String endDate;
        private String internalDiscount;
        private String productCode;
        private String pricePlanCode1;
        private String pricePlanCode2;
        private String channel1;
        private String channel2;
        private String fileType1;
        private String fileType2;

        private List<CouponsGroup> couponGroups;
        private List<ProductCode> productCodes;

        private Integer gracePeriodMinute;
    }

    /**
     * IMPORTANT: In order to update or delete existing MPP use subscription.getName() as "mppCode"
     * Don't forget after deletion of mpp delete also price plan using by deleted mpp (if it was created by ingestPP method)
     **/

    public static Subscription insertMpp(MppData mppData) {
        final String DEFAULT_START_DATE = "20/03/2016 00:00:00";
        final String DEFAULT_END_DATE = "20/03/2099 00:00:00";
        final int DEFAULT_GRACE_PERIOD = 0;

        final String currencyOfDiscount = "GBP";
        final int percentageOfDiscount = 100;

        mppData.mppCode = getRandomValue("MPP_");
        mppData.title = mppData.mppCode;

        if (mppData.description == null) { mppData.description = "Description of " + mppData.mppCode; }
        if (mppData.startDate == null) { mppData.startDate = DEFAULT_START_DATE; }
        if (mppData.endDate == null) { mppData.endDate = DEFAULT_END_DATE; }
        if (mppData.internalDiscount == null) { mppData.internalDiscount = IngestFixtureData
                .getDiscountByPercentAndCurrency(currencyOfDiscount, percentageOfDiscount); }
        if (mppData.gracePeriodMinute == null) { mppData.gracePeriodMinute = DEFAULT_GRACE_PERIOD; }
        if (mppData.pricePlanCode1 == null) { mppData.pricePlanCode1 = getProperty(DEFAULT_USAGE_MODULE_4_INGEST_MPP); }
        if (mppData.channel1 == null) { mppData.channel1 = getProperty(DEFAULT_CHANNEL); }
//        if (mppData.couponGroup == null) { mppData.couponGroup = DEFAULT_COUPON_GROUP; }
//        if (mppData.productCodes == null) { mppData.productCodes = DEFAULT_PRODUCT_CODES; }

        String reqBody = buildIngestMppXml(mppData, INGEST_ACTION_INSERT);
        Response resp = executeIngestMppRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

        // TODO: 7/1/2018 add wait until in case needed
        SubscriptionFilter filter = new SubscriptionFilter();
        filter.setSubscriptionIdIn(id);

        return executor.executeSync(SubscriptionService.list(filter)
                .setKs(getAnonymousKs()))
                .results.getObjects().get(0);
    }

    /** Mpp update seems to be broken */
/*    public static Subscription updateMpp(String mppCode, MppData mppData) {
        mppData.mppCode = mppCode;
        String reqBody = buildIngestMppXml(mppData, INGEST_ACTION_UPDATE);
        Response resp = executeIngestMppRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

        SubscriptionFilter filter = new SubscriptionFilter();
        filter.setSubscriptionIdIn(id);

        return executor.executeSync(SubscriptionService.list(filter)
                .setKs(getAnonymousKs()))
                .results.getObjects().get(0);

       // TODO: 7/1/2018 add wait until SubscriptionService.list(filter) is updated in case needed
    }*/

    public static void deleteMpp(String mppCode) {
        MppData mppData = new MppData();
        mppData.mppCode = mppCode;
        String reqBody = buildIngestMppXml(mppData, INGEST_ACTION_DELETE);

        Response resp = executeIngestMppRequest(reqBody);
        String reportId = from(resp.asString()).getString(ingestReportIdPath);

        resp = executeIngestReportRequest(reportId);

        assertThat(resp.asString()).contains("delete succeeded");

        // TODO: 7/1/2018 add wait until SubscriptionService.list(filter) is empty in case needed
    }

    // private methods
    private static Response executeIngestMppRequest(String reqBody) {
        Response resp = given()
                .header(contentTypeXml)
                .header(soapActionIngestBusinessModules)
                .body(reqBody)
                .when()
                .post(ingestUrl);

        Logger.getLogger(IngestVodUtils.class).debug(reqBody + "\n");
        Logger.getLogger(IngestVodUtils.class).debug(resp.asString());

        assertThat(resp).isNotNull();
        assertThat(from(resp.asString()).getString(ingestStatusMessagePath)).isEqualTo("OK");

        return resp;
    }

    private static String buildIngestMppXml(MppData mppData, String action) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestMPP.xml");

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", mppData.mppCode);

        // multi price plan
        Element mpp = (Element) ingest.getElementsByTagName("multi_price_plan").item(0);
        mpp.setAttribute("code", mppData.mppCode);
        mpp.setAttribute("action", action);
        mpp.setAttribute("is_active", Boolean.toString(mppData.isActive));

        if (action.equals(INGEST_ACTION_DELETE)) {
            return uncommentCdataSection(docToString(doc));
        }

        // title
        mpp.getElementsByTagName("title").item(0).setTextContent(mppData.title);

        // description
        mpp.getElementsByTagName("description").item(0).setTextContent(mppData.description);

        // start date
        mpp.getElementsByTagName("start_date").item(0).setTextContent(mppData.startDate);

        // end date
        mpp.getElementsByTagName("end_date").item(0).setTextContent(mppData.endDate);

        // internal discount
        mpp.getElementsByTagName("internal_discount").item(0).setTextContent(mppData.internalDiscount);

        // product code
        mpp.getElementsByTagName("product_code").item(0).setTextContent(mppData.productCode);

        // is renewable
        mpp.getElementsByTagName("is_renewable").item(0).setTextContent(Boolean.toString(mppData.isRenewable));

        // grace period minutes
        mpp.getElementsByTagName("grace_period_minutes").item(0).setTextContent(String.valueOf(mppData.gracePeriodMinute));

        // price plan codes
        mpp.getElementsByTagName("price_plan_code").item(0).setTextContent(mppData.pricePlanCode1);
        mpp.getElementsByTagName("price_plan_code").item(1).setTextContent(mppData.pricePlanCode2);

        // channels
        mpp.getElementsByTagName("channel").item(0).setTextContent(mppData.channel1);
        mpp.getElementsByTagName("channel").item(1).setTextContent(mppData.channel2);

        // file types
        mpp.getElementsByTagName("file_type").item(0).setTextContent(mppData.fileType1);
        mpp.getElementsByTagName("file_type").item(1).setTextContent(mppData.fileType2);

        // subscription coupon group
        if (mppData.couponGroups != null && mppData.couponGroups.size() > 0) {
            Element subscriptionCouponGroup = (Element) mpp.getElementsByTagName("subscription_coupon_group").item(0);

            for (CouponsGroup cg : mppData.couponGroups) {
                subscriptionCouponGroup.appendChild(addCouponsGroup(doc, cg));
            }
        }

        // product codes
        if (mppData.productCodes != null && mppData.productCodes.size() > 0) {
            Element productCodes = (Element) mpp.getElementsByTagName("product_codes").item(0);

            for (ProductCode pc : mppData.productCodes) {
                productCodes.appendChild(addProductCode(doc, pc));
            }
        }

        // uncomment cdata
        return uncommentCdataSection(docToString(doc));
    }

    private static Element addCouponsGroup(Document doc, CouponsGroup cg) {
        // coupon_group_id node
        Element couponsGroup = doc.createElement("coupon_group_id");

        // start_date node
        if (cg.getStartDate() != null) {
            Element startDate = doc.createElement("start_date");
            startDate.setTextContent(getFormattedTime(cg.getStartDate(), getTimeZone("UTC")));
            couponsGroup.appendChild(startDate);
        }

        // end_date node
        if (cg.getEndDate() != null) {
            Element endDate = doc.createElement("end_date");
            endDate.setTextContent(getFormattedTime(cg.getEndDate(), getTimeZone("UTC")));
            couponsGroup.appendChild(endDate);
        }

        // code node
        if (cg.getName() != null) {
            Element code = doc.createElement("code");
            code.setTextContent(cg.getName());
            couponsGroup.appendChild(code);
        }

        // TODO: 8/16/2018 add relevant CouponsGroup fields in case needed
        return couponsGroup;
    }

    private static Element addProductCode(Document doc, ProductCode pc) {
        // product_code node
        Element productCode = doc.createElement("product_code");

        // code node
        if (pc.getCode() != null) {
            Element code = doc.createElement("code");
            code.setTextContent(pc.getCode());
            productCode.appendChild(code);
        }

        // verification_payment_gateway node
        if (pc.getInappProvider() != null) {
            Element verificationPaymentGateway = doc.createElement("verification_payment_gateway");
            verificationPaymentGateway.setTextContent(pc.getInappProvider());
            productCode.appendChild(verificationPaymentGateway);
        }

        return productCode;
    }
}
