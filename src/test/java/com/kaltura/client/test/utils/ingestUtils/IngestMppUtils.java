package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserPassword;

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

    public static String buildIngestMppXml(String action, String mppCode, boolean isActive, String title, String description,
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
