package com.kaltura.client.test.utils.ingestUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.services.PpvService;
import com.kaltura.client.services.PpvService.GetPpvBuilder;
import com.kaltura.client.test.utils.dbUtils.IngestFixtureData;
import com.kaltura.client.types.*;
import io.restassured.response.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Optional;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.tests.enums.Currency.EUR;
import static com.kaltura.client.test.utils.BaseUtils.getRandomValue;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.from;

public class IngestPpvUtils extends BaseIngestUtils {

    /**
     * IMPORTANT: please delete inserted by that method items
     *
     * @param action                  - can be "insert", "update" and "delete"
     * @param ppvCode                 - should have value in case "action" one of {"update" and "delete"}
     * @param isActive
     * @param description
     * @param discount
     * @param price
     * @param currency
     * @param usageModule
     * @param isSubscriptionOnly
     * @param isFirstDeviceLimitation
     * @param productCode
     * @param firstFileType
     * @param secondFileType
     * @return PPV data
     * <p>
     * to update or delete existed ppv use corresponded action and value ppv.getName() as "ppvCode"
     * (where ppv is a variable that contains ppv data)
     * <p>
     * !!!Only created by that method PPV can be deleted/update!!!
     */
    // ingest new PPV
    public static Ppv ingestPPV(Optional<String> action, Optional<String> ppvCode, Optional<Boolean> isActive, Optional<String> description,
                                Optional<String> discount, Optional<Double> price, Optional<String> currency, Optional<String> usageModule,
                                Optional<Boolean> isSubscriptionOnly, Optional<Boolean> isFirstDeviceLimitation, Optional<String> productCode,
                                Optional<String> firstFileType, Optional<String> secondFileType) {
        String actionValue = action.orElse(INGEST_ACTION_INSERT);
        String ppvCodeValue = ppvCode.orElse(getRandomValue("PPV_", MAX_RANDOM_VALUE));
        boolean isActiveValue = isActive.isPresent() ? isActive.get() : true;
        String descriptionValue = description.orElse("My ingest PPV");
        String defaultCurrencyOfDiscount4IngestPpv = "ILS";
        int defaultPercentageOfDiscount4IngestPpv = 50;
        String discountValue = discount.orElse(IngestFixtureData.getDiscount(defaultCurrencyOfDiscount4IngestPpv, defaultPercentageOfDiscount4IngestPpv));
        double priceValue = price.orElse(Double.valueOf(getProperty(PRICE_CODE_AMOUNT)));
        String currencyValue = currency.orElse(EUR.getValue());
        String usageModuleValue = usageModule.orElse(getProperty(DEFAULT_USAGE_MODULE_4_INGEST_PPV));
        boolean isSubscriptionOnlyValue = isSubscriptionOnly.isPresent() ? isSubscriptionOnly.get() : false;
        boolean isFirstDeviceLimitationValue = isFirstDeviceLimitation.isPresent() ? isFirstDeviceLimitation.get() : false;
        String productCodeValue = productCode.orElse(getProperty(DEFAULT_PRODUCT_CODE));
        String firstFileTypeValue = firstFileType.orElse(getProperty(WEB_FILE_TYPE));
        String secondFileTypeValue = secondFileType.orElse(getProperty(MOBILE_FILE_TYPE));

        String url = getProperty(INGEST_BASE_URL) + "/Ingest_" + getProperty(API_VERSION) + "/Service.svc?wsdl";

        String reqBody = IngestPpvUtils.buildIngestPpvXml(actionValue, ppvCodeValue, isActiveValue, descriptionValue,
                discountValue, priceValue, currencyValue, usageModuleValue, isSubscriptionOnlyValue,
                isFirstDeviceLimitationValue, productCodeValue, firstFileTypeValue, secondFileTypeValue);

        Response resp =
                given()
                    .header(contentTypeXml)
                    .header(soapActionIngestBusinessModules)
                    .body(reqBody)
                .when()
                    .post(url);

        Logger.getLogger(IngestPpvUtils.class).debug(reqBody);
        Logger.getLogger(IngestPpvUtils.class).debug("\n Response: " + resp.asString());

        // TODO: 6/20/2018 add response assertion

        String reportId = from(resp.asString()).get("Envelope.Body.IngestBusinessModulesResponse.IngestBusinessModulesResult.ReportId").toString();
        //System.out.println("ReportId = " + reportId);

        url = getProperty(INGEST_REPORT_URL) + "/" + getProperty(PARTNER_ID) + "/" + reportId;
        resp = given().get(url);

        Logger.getLogger(IngestPpvUtils.class).debug(resp.asString());
        Logger.getLogger(IngestPpvUtils.class).debug(resp.asString().split(" = ")[1].replaceAll("\\.", ""));

        String id = resp.asString().split(" = ")[1].replaceAll("\\.", "").trim();

        GetPpvBuilder getPpvBuilder = PpvService.get(Long.valueOf(id));
        com.kaltura.client.utils.response.base.Response<Ppv> ppvResponse = executor.executeSync(getPpvBuilder.setKs(getOperatorKs()));

        return ppvResponse.results;
    }

    private static String buildIngestPpvXml(String action, String ppvCode, boolean isActive, String description, String discount,
                                           double price, String currency, String usageModule, boolean isSubscriptionOnly,
                                           boolean isFirstDeviceLimitation, String productCode, String firstFileType, String secondFileType) {
        Document doc = getDocument("src/test/resources/ingest_xml_templates/ingestPPV.xml");

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
