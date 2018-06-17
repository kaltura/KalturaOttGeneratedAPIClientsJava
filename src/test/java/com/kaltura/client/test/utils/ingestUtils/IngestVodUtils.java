package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;
import java.util.Map;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.getIngestAssetUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestAssetUserPassword;
import static com.kaltura.client.test.utils.XmlUtils.asList;

public class IngestVodUtils extends BaseIngestUtils {

    public static String buildIngestVodXml(String action, String coguid, boolean isActive, String name, String thumbUrl, String description, String catalogStartDate, String catalogEndDate,
                                           String startDate, String endDate, String mediaType, String ppvWebName, String ppvMobileName, Map<String, List<String>> tags, Map<String, String> strings,
                                           Map<String, Integer> numbers, Map<String, String> dates)  {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestVOD.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("userName").item(0).setTextContent(getIngestAssetUserName());
        doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestAssetUserPassword());

//        // add CDATA section
//        CDATASection cdata = doc.createCDATASection("");
//        doc.getElementsByTagName("tem:request").item(0).appendChild(cdata);

        // media
        Element media = (Element) doc.getElementsByTagName("media").item(0);
        media.setAttribute("co_guid", coguid);
        media.setAttribute("entry_id", "entry_" + coguid);
        media.setAttribute("action", action);
        media.setAttribute("is_active", Boolean.toString(isActive));

        // name
        Element nameElement = (Element) media.getElementsByTagName("name").item(0);
        nameElement.getElementsByTagName("value").item(0).setTextContent(name);

        // thumb
        Element thumb = (Element) media.getElementsByTagName("thumb").item(0);
        thumb.setAttribute("url", thumbUrl);

        // description
        Element descriptionElement = (Element) media.getElementsByTagName("description").item(0);
        descriptionElement.getElementsByTagName("value").item(0).setTextContent(description);

        // dates
        Element datesElement = (Element) media.getElementsByTagName("dates").item(0);
        datesElement.getElementsByTagName("catalog_start").item(0).setTextContent(catalogStartDate);
        datesElement.getElementsByTagName("start").item(0).setTextContent(startDate);
        datesElement.getElementsByTagName("catalog_end").item(0).setTextContent(catalogEndDate);
        datesElement.getElementsByTagName("end").item(0).setTextContent(endDate);

        // pic_ratios
        Element picRatios = (Element) media.getElementsByTagName("pic_ratios").item(0);
        for (Node n : asList(picRatios.getElementsByTagName("ratio"))) {
            Element e = (Element) n;
            e.setAttribute("thumb", thumbUrl);
        }

        // media type
        media.getElementsByTagName("media_type").item(0).setTextContent(mediaType);

        // strings
        Element stringsElement = (Element) media.getElementsByTagName("strings").item(0);
        for (Map.Entry<String, String> entry : strings.entrySet()) {
            // meta node
            Element meta = generateAndAppendMetaNode(doc, stringsElement, entry.getKey());

            // value node
            Element value = doc.createElement("value");
            value.setAttribute("lang", "eng");
            value.setTextContent(entry.getValue());
            meta.appendChild(value);
        }

        // doubles
        Element doublesElement = (Element) media.getElementsByTagName("doubles").item(0);
        for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
            // meta node
            Element meta = generateAndAppendMetaNode(doc, doublesElement, entry.getKey());
            meta.setTextContent(String.valueOf(entry.getValue()));
        }

        // dates
        Element datesMetaElement = (Element) media.getElementsByTagName("dates").item(1);
        for (Map.Entry<String, String> entry : dates.entrySet()) {
            // meta node
            Element metaElement = generateAndAppendMetaNode(doc, datesMetaElement, entry.getKey());
            metaElement.setTextContent(entry.getValue());
        }

        // metas
        Element metasElement = (Element) media.getElementsByTagName("metas").item(0);
        for (Map.Entry<String, List<String>> entry : tags.entrySet()) {
            // meta node
            generateAndAppendMetaNode(doc, metasElement, entry.getKey());
            if (entry.getValue() != null) {
                // container node
                for (String s : entry.getValue()) {
                    Element container = doc.createElement("container");
                    metasElement.appendChild(container);

                    // value node
                    Element value = doc.createElement("value");
                    value.setTextContent(s);
                    value.setAttribute("lang", "eng");
                    container.appendChild(value);
                }
            }
        }

        // file types
        Element file1 = (Element) media.getElementsByTagName("file").item(0);
        file1.setAttribute("type", getProperty(WEB_FILE_TYPE));
        file1.setAttribute("co_guid", "web_" + coguid);
        file1.setAttribute("PPV_MODULE", ppvWebName);

        Element file2 = (Element) media.getElementsByTagName("file").item(1);
        file2.setAttribute("type", getProperty(MOBILE_FILE_TYPE));
        file2.setAttribute("co_guid", "ipad_" + coguid);
        file2.setAttribute("PPV_MODULE", ppvMobileName);

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }

    private static Element generateAndAppendMetaNode(Document doc, Element rootElement, String name) {
        // meta node
        Element meta = doc.createElement("meta");
        meta.setAttribute("name", name);
        meta.setAttribute("ml_handling", "unique");
        rootElement.appendChild(meta);

        return meta;
    }
}
