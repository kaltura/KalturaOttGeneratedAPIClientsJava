package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserPassword;

public class IngestPpUtils extends BaseIngestUtils {

    // INGEST PP PARAMS
    static final boolean PP_DEFAULT_IS_ACTIVE_VALUE = true;
    static final boolean PP_DEFAULT_IS_RENEWABLE_VALUE = false;
    static final int PP_DEFAULT_MAX_VIEWS_VALUE = 0;
    static final int PP_DEFAULT_RECURRING_PERIODS_VALUE = 1;

    public static String buildIngestPpXml(String action, String ppCode, boolean isActive, String fullLifeCycle, String viewLifeCycle,
                                          int maxViews, String price, String currency, String discount, boolean isRenewable, int recurringPeriods) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestPP.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
