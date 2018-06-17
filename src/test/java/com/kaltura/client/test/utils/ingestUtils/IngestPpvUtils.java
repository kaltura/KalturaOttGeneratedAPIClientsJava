package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserPassword;

public class IngestPpvUtils extends BaseIngestUtils {

    public static String buildIngestPpvXml(String action, String ppvCode, boolean isActive, String description, String discount,
                                           double price, String currency, String usageModule, boolean isSubscriptionOnly,
                                           boolean isFirstDeviceLimitation, String productCode, String firstFileType, String secondFileType) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestPPV.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("tem:username").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("tem:password").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // ingest
        Element ingest = (Element) doc.getElementsByTagName("ingest").item(0);
        ingest.setAttribute("id", ppvCode);

        // ppv
        Element ppv = (Element) ingest.getElementsByTagName("ppv").item(0);
        ppv.setAttribute("code", ppvCode);
        ppv.setAttribute("action", action);
        ppv.setAttribute("is_active", Boolean.toString(isActive));

        // description
        ppv.getElementsByTagName("description").item(0).setTextContent(description);

        // price code
        ppv.getElementsByTagName("price").item(0).setTextContent(String.valueOf(price));
        ppv.getElementsByTagName("currency").item(0).setTextContent(currency);

        // usage module
        ppv.getElementsByTagName("usage_module").item(0).setTextContent(usageModule);

        // discount
        ppv.getElementsByTagName("discount").item(0).setTextContent(discount);

        // subscription only
        ppv.getElementsByTagName("subscription_only").item(0).setTextContent(Boolean.toString(isSubscriptionOnly));

        // first device limitation
        ppv.getElementsByTagName("first_device_limitation").item(0).setTextContent(Boolean.toString(isFirstDeviceLimitation));

        // product_code
        ppv.getElementsByTagName("product_code").item(0).setTextContent(productCode);

        // file types
        ppv.getElementsByTagName("file_type").item(0).setTextContent(firstFileType);
        ppv.getElementsByTagName("file_type").item(1).setTextContent(secondFileType);

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }
}
