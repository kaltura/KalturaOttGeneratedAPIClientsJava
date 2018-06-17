package com.kaltura.client.test.utils.ingestUtils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.Optional;

import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserName;
import static com.kaltura.client.test.tests.BaseTest.getIngestBusinessModuleUserPassword;

public class IngestEpgUtils extends BaseIngestUtils {

    public static String buildIngestEpgXml(String epgChannelName, Optional<Integer> programCount, Optional<String> firstProgramStartDate,
                                           Optional<Integer> programDuration, Optional<String> programDurationPeriodName,
                                           Optional<Boolean> isCridUnique4AllPrograms, Optional<Integer> seasonCount,
                                           Optional<String> coguid, Optional<String> crid, Optional<String> seriesId) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse("src/test/resources/ingest_xml_templates/ingestEPG.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // user and password
        doc.getElementsByTagName("userName").item(0).setTextContent(getIngestBusinessModuleUserName());
        doc.getElementsByTagName("passWord").item(0).setTextContent(getIngestBusinessModuleUserPassword());

        // uncomment cdata
        String docAsString = docToString(doc);
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

        return docAsString;
    }
}
