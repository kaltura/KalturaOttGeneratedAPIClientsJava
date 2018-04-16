package com.kaltura.client.test.tests;

import com.kaltura.client.test.utils.HouseholdUtils;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Sandbox extends BaseTest {

    @Test(enabled = false)
    private void test() {
        RestAssured.baseURI = "http://34.249.122.223:8030/Ingest_v4_8/Service.svc?wsdl";

        String myEnvelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\"><soapenv:Header/><soapenv:Body><tem:IngestTvinciData><tem:request><userName>QABE - Regular-main</userName><passWord>QABE - Regular-main</passWord><data><![CDATA[   <feed><export><media co_guid=\"180328093854266\" entry_id=\"entry_180328093854266\" action=\"update\" is_active=\"true\" erase=\"false\"><basic><name><value lang=\"eng\">Movie_180328093854266</value></name><thumb url=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\"/><description><value lang=\"eng\">description of 180328093854266</value></description><dates><catalog_start>27/03/2018 09:38:54</catalog_start><start>27/03/2018 09:38:54</start><catalog_end>14/10/2099 17:00:00</catalog_end><end>14/10/2099 17:00:00</end></dates><pic_ratios><ratio thumb=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\" ratio=\"4:3\" /><ratio thumb=\"http://opengameart.org/sites/default/files/styles/thumbnail/public/pictures/picture-1760-1321510314.png\" ratio=\"16:9\" /></pic_ratios><media_type>Movie</media_type><rules><geo_block_rule>Philippines Only</geo_block_rule><watch_per_rule>Parent Allowed</watch_per_rule><device_rule></device_rule></rules></basic><structure><strings><meta name=\"Synopsis\" ml_handling=\"unique\"><value lang=\"eng\">syno pino sister</value></meta><meta name=\"meta_name\" ml_handling=\"unique\"><value lang=\"eng\">meta_value</value></meta></strings><booleans/><doubles><meta name=\"Release year\" ml_handling=\"unique\">1900</meta></doubles><dates><meta name=\"Life cycle start date\" ml_handling=\"unique\">23/03/2017 12:34:56</meta></dates><metas><meta name=\"Country\" ml_handling=\"unique\"><container><value lang=\"eng\">Costa Rica;Israel</value></container></meta><meta name=\"Genre\" ml_handling=\"unique\"><container><value lang=\"eng\">GIH</value></container><container><value lang=\"eng\">ABC</value></container><container><value lang=\"eng\">DEF</value></container></meta><meta name=\"Series name\" ml_handling=\"unique\"><container><value lang=\"eng\">Shay_Series</value></container></meta><meta name=\"Free\" ml_handling=\"unique\"><container><value lang=\"eng\">KSQL channel_573349</value></container></meta><meta name=\"Parental Rating\" ml_handling=\"unique\"><container><value lang=\"eng\"></value></container></meta><meta name=\"\" ml_handling=\"unique\"><container><value lang=\"eng\"></value></container></meta></metas></structure><files><file type=\"Web HD\" assetDuration=\"1000\" quality=\"HIGH\" handling_type=\"CLIP\" cdn_name=\"Default CDN\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/hdnetworkmanifest/tags/mbr/protocol/http/f/a.a4m\" co_guid=\"web_180328093854266\" billing_type=\"Tvinci\" PPV_MODULE=\"Shai_Regression_PPV\" product_code=\"productExampleCode\" /><file type=\"Mobile_Devices_Main_HD\" assetDuration=\"1000\" quality=\"HIGH\" handling_type=\"CLIP\" cdn_name=\"Default CDN\" cdn_code=\"http://cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/applehttp/tags/ipadnew,ipad/protocol/http/f/a.m3u8\" alt_cdn_code=\"http://alt_cdntesting.qa.mkaltura.com/p/231/sp/23100/playManifest/entryId/0_3ugsts44/format/applehttp/tags/ipadnew,ipad/protocol/http/f/a.m3u8\" co_guid=\"ipad_180328093854266\" billing_type=\"Tvinci\" PPV_MODULE=\"Shai_Regression_PPV\" product_code=\"productExampleCode\" /></files></media></export></feed>]]></data></tem:request></tem:IngestTvinciData></soapenv:Body></soapenv:Envelope>";
        String xml = given()
                .header("SOAPAction","http://tempuri.org/IService/IngestTvinciData")
                .contentType("text/xml;charset=UTF-8")
                .body(myEnvelope)
                .when()
                .post()
                .andReturn()
                .asString();

        int assetId = new XmlPath(xml).getInt("Envelope.Body.IngestTvinciDataResponse.IngestTvinciDataResult.tvmID");
        System.out.println("!!! " + assetId);
    }

    @Test(enabled = true)
    private void test1() {
        HouseholdUtils.createHouseHold(2, 1, false);
    }
}
