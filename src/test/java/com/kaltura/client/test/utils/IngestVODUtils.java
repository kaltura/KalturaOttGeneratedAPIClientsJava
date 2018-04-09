package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.types.Asset;
import com.kaltura.client.types.MediaAsset;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import com.kaltura.client.utils.response.base.Response;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.Properties.INGEST_USER_PASSWORD;
import static com.kaltura.client.test.Properties.getProperty;
import static com.kaltura.client.test.tests.BaseTest.anonymousKs;
import static io.restassured.path.xml.XmlPath.from;
import static org.awaitility.Awaitility.await;

public class IngestVODUtils extends BaseUtils {

    // ingest new VOD (Media) // TODO: complete one-by-one needed fields to cover util ingest_vod from old project
    public static MediaAsset ingestVOD(Optional<String> coguid, boolean isActive, Optional<String> name,
                                       Optional<String> thumbUrl, Optional<String> description, Optional<String> catalogStartDate,
                                       Optional<String> catalogEndDate, Optional<String> startDate, Optional<String> endDate,
                                       Optional<String> mediaType, Optional<String> ppvWebName, Optional<String> ppvMobileName) {
        String coguidValue = coguid.isPresent() ? coguid.get() : getCurrentDataInFormat("yyMMddHHmmssSS");
        String nameValue = name.isPresent() ? name.get() : MOVIE_MEDIA_TYPE + "_" + coguidValue;
        String thumbUrlValue = thumbUrl.isPresent() ? thumbUrl.get() : INGEST_VOD_DEFAULT_THUMB;
        String descriptionValue = description.isPresent() ? description.get() : "description of " + coguidValue;
        String catalogStartDateValue = catalogStartDate.isPresent() ? catalogStartDate.get() : getOffsetDateInFormat( -1, "dd/MM/yyyy hh:mm:ss");
        String catalogEndDateValue = catalogEndDate.isPresent() ? catalogEndDate.get() : "14/10/2099 17:00:00";
        String startDateValue = startDate.isPresent() ? startDate.get() : getOffsetDateInFormat( -1, "dd/MM/yyyy hh:mm:ss");
        String endDateValue = endDate.isPresent() ? endDate.get() : "14/10/2099 17:00:00";
        String mediaTypeValue = mediaType.isPresent() ? mediaType.get() : MOVIE_MEDIA_TYPE;
        String ppvWebNameValue = ppvWebName.isPresent() ? ppvWebName.get() : "Shai_Regression_PPV"; // TODO: update on any generated value
        String ppvMobileNameValue = ppvMobileName.isPresent() ? ppvMobileName.get() : "Shai_Regression_PPV"; // TODO: update on any generated value
        // TODO: check if ingest url is the same for all ingest actions
        String url = SOAP_BASE_URL + "/Ingest_" + API_URL_VERSION + "/Service.svc?wsdl";
        HashMap headermap = new HashMap<>();
        headermap.put("Content-Type", "text/xml;charset=UTF-8");
        headermap.put("SOAPAction", "\"http://tempuri.org/IService/IngestTvinciData\"");
        String reqBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:IngestTvinciData><tem:request><userName>" + getProperty(INGEST_USER_NAME) + "</userName><passWord>" + getProperty(INGEST_USER_PASSWORD) + "</passWord><data>" +
                "         <![CDATA[" + buildIngestVodXml(coguidValue, isActive, nameValue, thumbUrlValue, descriptionValue, catalogStartDateValue,
                                    catalogEndDateValue, startDateValue, endDateValue, mediaTypeValue, ppvWebNameValue, ppvMobileNameValue) +
                "                 ]]></data></tem:request></tem:IngestTvinciData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        io.restassured.response.Response resp = RestAssured.given()
                .log().all()
                .headers(headermap)
                .body(reqBody)
                .post(url);
        //System.out.println("RESPONSE: " + resp.asString());
        String id = from(resp.asString()).get("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.AssetsStatus.IngestAssetStatus.InternalAssetId").toString();

        MediaAsset mediaAsset = new MediaAsset();
        mediaAsset.setName(nameValue);
        mediaAsset.setId(Long.valueOf(id));
        mediaAsset.setDescription(descriptionValue);
        //mediaAsset.setStartDate(startDate);
        //mediaAsset.setEndDate(endDate);

        await().atMost(30, TimeUnit.SECONDS).until(isDataReturned(id));
        Response<Asset> mediaAssetDetails = AssetServiceImpl.get(anonymousKs, id, AssetReferenceType.MEDIA);
        mediaAsset.setMediaFiles(mediaAssetDetails.results.getMediaFiles());
        return mediaAsset;
    }

    private static Callable<Boolean> isDataReturned(String mediaId) {
        return () -> AssetServiceImpl.get(anonymousKs, mediaId, AssetReferenceType.MEDIA).error == null;
    }

    private static String buildIngestVodXml(String coguid, boolean isActive, String name, String thumbUrl,
                                            String description, String catalogStartDate, String catalogEndDate,
                                            String startDate, String endDate, String mediaType, String ppvWebName,
                                            String ppvMobileName) {
        return "<feed>\n" +
                "  <export>\n" +
                "    <media co_guid=\"" + coguid + "\" entry_id=\"entry_" + coguid + "\" action=\"update\" is_active=\"" + isActive + "\" erase=\"false\">\n" +
                "      <basic>\n" +
                "        <name>\n" +
                "          <value lang=\"eng\">" + name + "</value>\n" +
                "        </name>\n" +
                "        <thumb url=\"" + thumbUrl + "\"/>\n" +
                "        <description>\n" +
                "          <value lang=\"eng\">" + description + "</value>\n" +
                "        </description>\n" +
                "        <dates>\n" +
                "          <catalog_start>" + catalogStartDate + "</catalog_start>\n" +
                "          <start>" + startDate + "</start>\n" +
                "          <catalog_end>" + catalogEndDate + "</catalog_end>\n" +
                "          <end>" + endDate + "</end>\n" +
                "        </dates>\n" +
                "        <pic_ratios>\n" +
                "          <ratio thumb=\"" + thumbUrl + "\" ratio=\"4:3\"/>\n" +
                "          <ratio thumb=\"" + thumbUrl + "\" ratio=\"16:9\"/>\n" +
                "        </pic_ratios>\n" +
                "        <media_type>" + mediaType + "</media_type>\n" +
                "        <rules>\n" +
                //"          <geo_block_rule>${#TestCase#i_geo_block_rule}</geo_block_rule>\n" +
                //"          <watch_per_rule>Parent Allowed</watch_per_rule>\n" +
                //"          <device_rule>${#TestCase#i_device_block_rule}</device_rule>\n" +
                "        </rules>\n" +
                "      </basic>\n" +
                "      <structure>\n" +
                //"        <strings>\n" +
                //"          <meta name=\"Synopsis\" ml_handling=\"unique\">\n" +
                //"            <value lang=\"eng\">syno pino sister</value>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"${#TestCase#i_meta_name}\" ml_handling=\"unique\">\n" +
                //"            <value lang=\"eng\">${#TestCase#i_meta_value}</value>\n" +
                //"          </meta>\n" +
                //"        </strings>\n" +
                //"        <booleans/>\n" +
                //"        <doubles>\n" +
                //"          <meta name=\"${#TestCase#i_double_meta_name}\" ml_handling=\"unique\">${#TestCase#i_double_meta_value}</meta>\n" +
                //"        </doubles>\n" +
                //"        <dates>\n" +
                //"          <meta name=\"${#TestCase#i_date_meta_name}\" ml_handling=\"unique\">${#TestCase#i_date_meta_value}</meta>\n" +
                //"        </dates>\n" +
                //"        <metas>\n" +
                //"          <meta name=\"Country\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">Costa Rica;Israel</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"Genre\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">GIH</value>\n" +
                //"            </container>\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">ABC</value>\n" +
                //"            </container>\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">DEF</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"Series name\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">Shay_Series</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"Free\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">${#TestCase#i_tag_free_value}</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"${#TestCase#i_parental_field_name}\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">${#TestCase#i_parental_value}</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"          <meta name=\"${#TestCase#i_tag_name}\" ml_handling=\"unique\">\n" +
                //"            <container>\n" +
                //"              <value lang=\"eng\">${#TestCase#i_tag_value}</value>\n" +
                //"            </container>\n" +
                //"          </meta>\n" +
                //"        </metas>\n" +
                "      </structure>\n" +
                "      <files>\n" +
                "        <file type=\"" + getProperty(WEB_FILE_TYPE) + "\" assetDuration=\"1000\" quality=\"HIGH\" handling_type=\"CLIP\" cdn_name=\"Default CDN\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" co_guid=\"web_" + coguid + "\" billing_type=\"Tvinci\" PPV_MODULE=\"" + ppvWebName + "\" product_code=\"productExampleCode\"/>\n" +
                "        <file type=\"" + getProperty(MOBILE_FILE_TYPE) + "\" assetDuration=\"1000\" quality=\"HIGH\" handling_type=\"CLIP\" cdn_name=\"Default CDN\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/applehttp/tags/ipadnew,ipad/protocol/http/f/a.m3u8\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/applehttp/tags/ipadnew,ipad/protocol/http/f/a.m3u8\" co_guid=\"ipad_" + coguid + "\" billing_type=\"Tvinci\" PPV_MODULE=\"" + ppvMobileName + "\" product_code=\"productExampleCode\"/>\n" +
                "      </files>\n" +
                "    </media>\n" +
                "  </export>\n" +
                "</feed>";
    }
}
