package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.types.APIException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kaltura.client.test.Properties.API_URL_VERSION;

public class BaseUtils {

    private static final String API_SCHEMA_URL = "https://api-preprod.ott.kaltura.com/" + API_URL_VERSION + "/clientlibs/KalturaClient.xml";
    private static List<APIException> exceptions;


    // generate random string
    public static String getRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        while (sb.length() < 12) {
            int index = (int) (r.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString().toLowerCase();
    }

    // get api exception
    public static APIException getAPIExceptionFromList(int code) {
        getApiExceptionList();

        for (APIException exception : exceptions) {
            if (exception.getCode().equals(String.valueOf(code))) {
                return exception;
            }
        }

        Logger.getLogger(BaseUtils.class).error("No such error code in the API schema");
        return null;
    }

    // generate apiException list base on the api schema
    private static List<APIException> getApiExceptionList() {
        if (exceptions == null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;
            try {
                doc = db.parse(new URL(API_SCHEMA_URL).openStream());
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NodeList nodeList = doc.getElementsByTagName("error");

            exceptions = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                APIException exception = new APIException(null, element.getAttribute("description"), element.getAttribute("code"));
                exceptions.add(exception);
            }
        }
        return exceptions;
    }
}
