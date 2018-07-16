package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.types.APIException;
import com.kaltura.client.types.TranslationToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.kaltura.client.test.Properties.API_VERSION;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.tests.BaseTest.config;
import static io.restassured.RestAssured.given;

public class BaseUtils {

    private static final String API_SCHEMA_URL = "https://api-preprod.ott.kaltura.com/" + getProperty(API_VERSION) + "/clientlibs/KalturaClient.xml";
    private static List<APIException> exceptions;

    // to get e.g. yesterday date in specific date format need call the method so: getOffsetDateInFormat(-1, pattern);
    public static String getOffsetDateInFormat(int offsetDay, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        if (offsetDay != 0) {
            cal.add(Calendar.DATE, offsetDay);
        }
        return dateFormat.format(cal.getTime());
    }

    // Get Date time according to offset parameter provided (with the pattern: dd/MM/yyyy HH:mm:ss)
    public static String getTimeInDate(int offSetInMinutes, String timeZone) {
        TimeZone theTimeZone = TimeZone.getTimeZone(timeZone);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(theTimeZone);
        Date dNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);
        calendar.add(Calendar.MINUTE, offSetInMinutes);
        dNow = calendar.getTime();

        return dateFormat.format(dNow);
    }

    // Get Date time according to offset parameter provided (with the pattern: dd/MM/yyyy HH:mm:ss)
    public static String getTimeInDate(int offSetInMinutes) {
        return getTimeInDate(offSetInMinutes,"israel");
    }

    // Get epoch time in seconds according to off set parameter provided (in minutes)
    public static long getTimeInEpoch(int offSetInMinutes) {
        Date dNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);
        calendar.add(Calendar.MINUTE, offSetInMinutes);

        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimeInEpoch() {
        return Instant.now().toEpochMilli();
    }

    // generate current data String in specified format
    public static String getCurrentDateInFormat(String pattern) {
        return getOffsetDateInFormat(0, pattern);
    }

    // generate string containing prefix and random long suffix
    public static String getRandomValue(String prefix, long maxValue) {
        long randomLongValue = ThreadLocalRandom.current().nextLong(maxValue);
        return prefix + randomLongValue;
    }

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

    // Get concatenated string
    public static String getConcatenatedString(String... strings) {
        List<String> list = new ArrayList<>();
        for (String arg : strings) {
            list.add(arg);
        }
        return String.join(",", list);
    }

    public static String getConcatenatedString(List<String> strings) {
        return String.join(",", strings);
    }

    public static String getFileContent(String filePath) {
        String result = "";
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = new FileInputStream(filePath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.delete()) {
            Logger.getLogger(BaseUtils.class).debug("File deleted successfully: " + filePath);
        } else {
            Logger.getLogger(BaseUtils.class).error("Failed to delete the file: " + filePath);
        }
    }

    public static void clearCache() {
        String url = config.getEndpoint() + "/clear_cache.aspx";
        given().queryParam("action", "clear_all").get(url);
    }

    public static List<TranslationToken> setTranslationToken(String value) {
        TranslationToken translationToken = new TranslationToken();
        translationToken.setLanguage("eng");
        translationToken.setValue(value);

        List<TranslationToken> translationTokens = new ArrayList<>();
        translationTokens.add(translationToken);

        return translationTokens;
    }
}
