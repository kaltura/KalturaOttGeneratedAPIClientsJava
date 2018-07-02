package com.kaltura.client.test.utils.ingestUtils;

import io.restassured.http.Header;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.Writer;

import static com.kaltura.client.test.Properties.API_VERSION;
import static com.kaltura.client.test.Properties.INGEST_BASE_URL;
import static com.kaltura.client.test.Properties.getProperty;

public class BaseIngestUtils {

    // urls
    static final String ingestUrl = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

    // headers
    static final Header contentTypeXml = new Header("Content-Type", "text/xml;charset=UTF-8");
    static final Header soapActionIngestTvinciData = new Header("SOAPAction", "http://tempuri.org/IService/IngestTvinciData");
    static final Header soapActionIngestBusinessModules = new Header("SOAPAction", "http://tempuri.org/IService/IngestBusinessModules");
    static final Header soapActionIngestKalturaEpg = new Header("SOAPAction", "http://tempuri.org/IService/IngestKalturaEpg");

    // actions
    public static final String INGEST_ACTION_INSERT = "insert";
    public static final String INGEST_ACTION_UPDATE = "update";
    public static final String INGEST_ACTION_DELETE = "delete";

    // wait configuration
    static final int delayBetweenRetriesInSeconds = 5;
    static final int maxTimeExpectingValidResponseInSeconds = 120;

    // life cycles periods
    public static final String FIVE_MINUTES_PERIOD = "5 Minutes";

    // media types // TODO: ask if these types (from TVM edit VOD page) are default for all accounts
    public static final String MOVIE_MEDIA_TYPE = "Movie";
    public static final String SERIES_MEDIA_TYPE = "Series";
    public static final String EPISODE_MEDIA_TYPE = "Episode";
    public static final String LINEAR_MEDIA_TYPE = "Linear";

    // data
    static final Long MAX_RANDOM_VALUE = 9999999999L;
    static final String DEFAULT_THUMB = "http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png";

    /*
    // PG adapter data
    public static final String PG_DEFAULT_ADAPTER_URL = "http://172.31.6.89:90/PGAdapter/Service.svc";
    public static final String PG_DEFAULT_RENEW_URL = PG_DEFAULT_ADAPTER_URL + "?StateCode=0";
    public static final String PG_DEFAULT_SHARED_SECRET = "123456";
    public static final int PG_DEFAULT_PENDING_INTERVAL = 0;
    public static final int PG_DEFAULT_PENDING_RETRIES = 0;
    public static final int PG_DEFAULT_RENEW_INTERVAL_MINUTES = 15;
    public static final int PG_DEFAULT_RENEW_START_MINUTES =-5;
    public static final String PG_DEFAULT_PG_SETTINGS = "{}";
    */

    static Document getDocument(String uri) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    static String docToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

    static void prettyPrint(Document doc) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));
        System.out.println(out.toString());
    }

    static String uncommentCdataSection(String docAsString) {
        docAsString = docAsString
                .replace("<!--<![CDATA[-->", "<![CDATA[")
                .replace("<!--]]>-->", "]]>");

            return docAsString;
    }
}
