package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.Subscription;
import io.restassured.RestAssured;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.Optional;

import static com.kaltura.client.test.IngestConstants.INGEST_ACTION_INSERT;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserPassword;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.path.xml.XmlPath.from;

public class IngestMppUtils extends BaseIngestUtils {

    // INGEST MPP PARAMS
    static final String MPP_DEFAULT_DESCRIPTION_VALUE = "Ingest MPP description";
    static final String MPP_DEFAULT_START_DATE_VALUE = "20/03/2016 00:00:00";
    static final String MPP_DEFAULT_END_DATE_VALUE = "20/03/2099 00:00:00";
    static final boolean MPP_DEFAULT_IS_ACTIVE_VALUE = true;
    static final boolean MPP_DEFAULT_IS_RENEWABLE_VALUE = false;
    static final int MPP_DEFAULT_GRACE_PERIOD_VALUE = 0;

    // TODO: THIS VALUES RELATED TO OUR ENV ONLY discuss with Alon
    static final String MPP_DEFAULT_COUPON_GROUP_VALUE =
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

    static final String MPP_DEFAULT_PRODUCT_CODES_VALUE =
            "<product_code>\n" +
                    "<code>ProductCode1</code>\n" +
                    "<verification_payment_gateway>Google</verification_payment_gateway>\n" +
                    "</product_code>\n" +
                    "<product_code>\n" +
                    "<code>ProductCode2</code>\n" +
                    "<verification_payment_gateway>Apple</verification_payment_gateway>\n" +
                    "</product_code>";

    /**
     * IMPORTANT: please delete inserted by that method items
     *
     * @param action            - can be "insert" or "delete" ("update" looks like broken)
     * @param mppCode           - should have value in case "action" is "delete"
     * @param isActive
     * @param title
     * @param description
     * @param startDate
     * @param endDate
     * @param internalDiscount
     * @param productCode
     * @param isRenewable
     * @param gracePeriodMinute
     * @param pricePlanCode1
     * @param pricePlanCode2
     * @param channel1
     * @param channel2
     * @param fileType1
     * @param fileType2
     * @param couponGroup
     * @param productCodes
     * @return MPP data
     * <p>
     * !!!Only created by that method MPP can be deleted!!!
     * <p>
     * to delete existed MPP use corresponded action and value mpp.getName() as "mppCode"
     * (where mpp is a variable that contains mpp data).
     * <p>
     * <p>
     * don't forget after deletion of mpp delete also price plan using by deleted mpp (if it was created by ingestPP method)
     */
    // ingest new MPP
    public static Subscription ingestMPP(Optional<String> action, Optional<String> mppCode, Optional<Boolean> isActive, Optional<String> title,
                                         Optional<String> description, Optional<String> startDate, Optional<String> endDate, Optional<String> internalDiscount,
                                         Optional<String> productCode, Optional<Boolean> isRenewable, Optional<Integer> gracePeriodMinute,
                                         Optional<String> pricePlanCode1, Optional<String> pricePlanCode2, Optional<String> channel1,
                                         Optional<String> channel2, Optional<String> fileType1, Optional<String> fileType2, Optional<String> couponGroup,
                                         Optional<String> productCodes) {
        String mppCodeValue = mppCode.orElse(getRandomValue("MPP_", 9999999999L));
        String actionValue = action.orElse(INGEST_ACTION_INSERT);
        boolean isActiveValue = isActive.orElse(MPP_DEFAULT_IS_ACTIVE_VALUE);
        String titleValue = INGEST_ACTION_INSERT.equals(actionValue) ? mppCodeValue : title.orElse(mppCodeValue);
        String descriptionValue = description.orElse(MPP_DEFAULT_DESCRIPTION_VALUE);
        String startDateValue = startDate.orElse(MPP_DEFAULT_START_DATE_VALUE);
        String endDateValue = endDate.orElse(MPP_DEFAULT_END_DATE_VALUE);
        String defaultCurrencyOfDiscount4IngestMpp = "GBP";
        int defaultPercentageOfDiscount4IngestMpp = 100;
        String internalDiscountValue = internalDiscount.orElse(IngestFixtureData.getDiscount(defaultCurrencyOfDiscount4IngestMpp, defaultPercentageOfDiscount4IngestMpp));
        String productCodeValue = productCode.orElse("");
        boolean isRenewableValue = isRenewable.orElse(MPP_DEFAULT_IS_RENEWABLE_VALUE);
        int gracePeriodMinuteValue = gracePeriodMinute.orElse(MPP_DEFAULT_GRACE_PERIOD_VALUE);

        String pricePlanCode1Value = pricePlanCode1.orElse(getProperty(DEFAULT_USAGE_MODULE_4_INGEST_MPP));
        String pricePlanCode2Value = pricePlanCode2.orElse("");
        String channel1Value = channel1.orElse(getProperty(DEFAULT_CHANNEL));
        String channel2Value = channel2.orElse("");
        String fileType1Value = fileType1.orElse("");
        String fileType2Value = fileType2.orElse("");
        String couponGroupValue = couponGroup.orElse(MPP_DEFAULT_COUPON_GROUP_VALUE);
        String productCodesValue = productCodes.orElse(MPP_DEFAULT_PRODUCT_CODES_VALUE);


        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody = IngestMppUtils.buildIngestMppXml(actionValue, mppCodeValue, isActiveValue, titleValue,
                descriptionValue, startDateValue, endDateValue, internalDiscountValue, productCodeValue,
                isRenewableValue, gracePeriodMinuteValue, pricePlanCode1Value, pricePlanCode2Value,
                channel1Value, channel2Value, fileType1Value, fileType2Value, couponGroupValue, productCodesValue);

        io.restassured.response.Response resp = RestAssured
                .given()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .header("SOAPAction", "http://tempuri.org/IService/IngestBusinessModules")
                .body(reqBody)
                .when()
                .post(url);

        Logger.getLogger(IngestUtils.class).debug(reqBody);
        Logger.getLogger(IngestUtils.class).debug("\n Response:!!! " + resp.asString());

        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();
        //Logger.getLogger(IngestUtils.class).debug("ReportId = " + reportId);

        url = getProperty(INGEST_REPORT_URL) + "/" + getProperty(PARTNER_ID) + "/" + reportId;
        resp = RestAssured.given()
                .log().all()
                .get(url);

        Logger.getLogger(IngestUtils.class).debug(resp.asString());
        //System.out.println(resp.asString().split(" = ")[1].replaceAll("\\.", ""));

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

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

    private static String buildIngestMppXml(String action, String mppCode, boolean isActive, String title, String description,
                                           String startDate, String endDate, String internalDiscount, String productCode,
                                           boolean isRenewable, int gracePeriodMinute, String pricePlanCode1,
                                           String pricePlanCode2, String channel1, String channel2, String fileType1,
                                           String fileType2, String couponGroup, String productCodes) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestMPP.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", mppCode);

        // multi price plan
        Element mpp = (Element) ingest.getElementsByTagName("multi_price_plan").item(0);
        mpp.setAttribute("code", mppCode);
        mpp.setAttribute("action", action);
        mpp.setAttribute("is_active", Boolean.toString(isActive));

        // title
        mpp.getElementsByTagName("title").item(0).setTextContent(title);

        // description
        mpp.getElementsByTagName("description").item(0).setTextContent(description);

        // start date
        mpp.getElementsByTagName("start_date").item(0).setTextContent(startDate);

        // end date
        mpp.getElementsByTagName("end_date").item(0).setTextContent(endDate);

        // internal discount
        mpp.getElementsByTagName("internal_discount").item(0).setTextContent(internalDiscount);

        // product code
        mpp.getElementsByTagName("product_code").item(0).setTextContent(productCode);

        // is renewable
        mpp.getElementsByTagName("is_renewable").item(0).setTextContent(Boolean.toString(isRenewable));

        // grace period minutes
        mpp.getElementsByTagName("grace_period_minutes").item(0).setTextContent(String.valueOf(gracePeriodMinute));

        // price plan codes
        mpp.getElementsByTagName("price_plan_code").item(0).setTextContent(pricePlanCode1);
        mpp.getElementsByTagName("price_plan_code").item(1).setTextContent(pricePlanCode2);

        // channels
        mpp.getElementsByTagName("channel").item(0).setTextContent(channel1);
        mpp.getElementsByTagName("channel").item(1).setTextContent(channel2);

        // file types
        mpp.getElementsByTagName("file_type").item(0).setTextContent(fileType1);
        mpp.getElementsByTagName("file_type").item(1).setTextContent(fileType2);

        // subscription coupon group
        mpp.getElementsByTagName("subscription_coupon_group").item(0).setTextContent(couponGroup);

        // product codes
        mpp.getElementsByTagName("product_codes").item(0).setTextContent(productCodes);

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }
}
