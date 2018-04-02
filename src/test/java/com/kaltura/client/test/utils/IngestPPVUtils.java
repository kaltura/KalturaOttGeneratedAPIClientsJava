package com.kaltura.client.test.utils;

import com.kaltura.client.types.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import static com.kaltura.client.test.Properties.*;
import static io.restassured.path.xml.XmlPath.from;

public class IngestPPVUtils extends BaseUtils {

    // ingest new PPV
    public static Ppv ingestPPV(String action, boolean isActive, String description, String discount,
                                double price, String currency, String usageModule, boolean isSubscriptionOnly,
                                boolean isFirstDeviceLimitation, String productCode, String firstFileType,
                                String secondFileType) {
        String ppvCode = getRandomValue("PPV_", 9999999999L);

        String url = SOAP_BASE_URL + "/Ingest_" + API_URL_VERSION + "/Service.svc?wsdl";
        HashMap headermap = new HashMap<>();
        headermap.put("Content-Type", "text/xml;charset=UTF-8");
        headermap.put("SOAPAction", "\"http://tempuri.org/IService/IngestBusinessModules\"");
        String reqBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:IngestBusinessModules><tem:username>" + getProperty(INGEST_USER_NAME) + "</tem:username><tem:password>" + getProperty(INGEST_USER_PASSWORD) + "</tem:password><tem:xml>" +
                "         <![CDATA[" + buildIngestPpvXML(action, ppvCode, isActive, description, discount, price, currency,
                                        usageModule, isSubscriptionOnly, isFirstDeviceLimitation, productCode, firstFileType, secondFileType) +
                "                 ]]></tem:xml></tem:IngestBusinessModules>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        Response resp = RestAssured.given()
                .log().all()
                .headers(headermap)
                .body(reqBody)
                .post(url);
        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();
        System.out.println("ReportId = " + reportId);

        url = INGEST_REPORT_URL + "/" + PARTNER_ID + "/" + reportId;
        resp = RestAssured.given()
                .log().all()
                .get(url);
        System.out.println(resp.asString());
        System.out.println(resp.asString().split(" = ")[1].replaceAll("\\.", ""));

        // TODO: complete it
        Ppv ppv = new Ppv();
        //ppv.setDescriptions();
        /*PriceDetails priceDetails = new PriceDetails();
        Price price1 = new Price();
        price1.setAmount(price);
        price1.setCurrency(currency);
        priceDetails.setPrice(price1);
        ppv.setPrice(priceDetails);*/
        /*UsageModule usageModule1 = new UsageModule();
        usageModule1.setName(usageModule);
        ppv.setUsageModule(usageModule1);*/
        //ppv.setIsSubscriptionOnly(isSubscriptionOnly);
        //ppv.setFirstDeviceLimitation(isFirstDeviceLimitation);
        //ppv.setProductCode(productCode);

        return null;
    }

    private static String buildIngestPpvXML(String action, String ppvCode, boolean isActive, String description, String discount,
                                     double price, String currency, String usageModule, boolean isSubscriptionOnly,
                                     boolean isFirstDeviceLimitation, String productCode, String firstFileType,
                                     String secondFileType) {
        return "<ingest id=\"" + ppvCode + "\">\n" +
                "  <ppvs>\n" +
                "    <ppv code=\"" + ppvCode + "\" action=\"" + action + "\" is_active=\"" + isActive + "\">\n" +
                "      <descriptions>\n" +
                "        <description lang=\"eng\">" + description + "</description>\n" +
                "      </descriptions>\n" +
                "      <price_code>\n" +
                "        <price>" + price + "</price>\n" +
                "        <currency>" + currency + "</currency>\n" +
                "      </price_code>\n" +
                "      <usage_module>" + usageModule + "</usage_module>\n" +
                "      <discount>" + discount + "</discount>\n" +
                "      <coupon_group/>\n" +
                "      <subscription_only>" + isSubscriptionOnly + "</subscription_only>\n" +
                "      <first_device_limitation>" + isFirstDeviceLimitation + "</first_device_limitation>\n" +
                "      <product_code>" + productCode + "</product_code>\n" +
                "      <file_types>\n" +
                "        <file_type>" + firstFileType + "</file_type>\n" +
                "        <file_type>" + secondFileType + "</file_type>\n" +
                "      </file_types>\n" +
                "    </ppv>\n" +
                "  </ppvs>\n" +
                "</ingest>\n";
    }
}
