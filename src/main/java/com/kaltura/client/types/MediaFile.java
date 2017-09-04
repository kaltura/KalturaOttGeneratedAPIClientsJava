// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2017  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client.types;

import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.StringValueArray;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Media file details  */
@SuppressWarnings("serial")
public class MediaFile extends ObjectBase {

	/**  Unique identifier for the asset  */
    private Integer assetId;
	/**  File unique identifier  */
    private Integer id;
	/**  Device types as defined in the system  */
    private String type;
	/**  URL of the media file to be played  */
    private String url;
	/**  Duration of the media file  */
    private Long duration;
	/**  External identifier for the media file  */
    private String externalId;
	/**  Billing type  */
    private String billingType;
	/**  Quality  */
    private String quality;
	/**  Handling type  */
    private String handlingType;
	/**  CDN name  */
    private String cdnName;
	/**  CDN code  */
    private String cdnCode;
	/**  Alt CDN code  */
    private String altCdnCode;
	/**  PPV Module  */
    private StringValueArray ppvModules;
	/**  Product code  */
    private String productCode;

    // assetId:
    public Integer getAssetId(){
        return this.assetId;
    }
    public void setAssetId(Integer assetId){
        this.assetId = assetId;
    }

    // id:
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    // type:
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    // url:
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }

    // duration:
    public Long getDuration(){
        return this.duration;
    }
    public void setDuration(Long duration){
        this.duration = duration;
    }

    // externalId:
    public String getExternalId(){
        return this.externalId;
    }
    public void setExternalId(String externalId){
        this.externalId = externalId;
    }

    // billingType:
    public String getBillingType(){
        return this.billingType;
    }
    public void setBillingType(String billingType){
        this.billingType = billingType;
    }

    // quality:
    public String getQuality(){
        return this.quality;
    }
    public void setQuality(String quality){
        this.quality = quality;
    }

    // handlingType:
    public String getHandlingType(){
        return this.handlingType;
    }
    public void setHandlingType(String handlingType){
        this.handlingType = handlingType;
    }

    // cdnName:
    public String getCdnName(){
        return this.cdnName;
    }
    public void setCdnName(String cdnName){
        this.cdnName = cdnName;
    }

    // cdnCode:
    public String getCdnCode(){
        return this.cdnCode;
    }
    public void setCdnCode(String cdnCode){
        this.cdnCode = cdnCode;
    }

    // altCdnCode:
    public String getAltCdnCode(){
        return this.altCdnCode;
    }
    public void setAltCdnCode(String altCdnCode){
        this.altCdnCode = altCdnCode;
    }

    // ppvModules:
    public StringValueArray getPpvModules(){
        return this.ppvModules;
    }
    public void setPpvModules(StringValueArray ppvModules){
        this.ppvModules = ppvModules;
    }

    // productCode:
    public String getProductCode(){
        return this.productCode;
    }
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }


    public MediaFile() {
       super();
    }

    public MediaFile(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        assetId = GsonParser.parseInt(jsonObject.get("assetId"));
        id = GsonParser.parseInt(jsonObject.get("id"));
        type = GsonParser.parseString(jsonObject.get("type"));
        url = GsonParser.parseString(jsonObject.get("url"));
        duration = GsonParser.parseLong(jsonObject.get("duration"));
        externalId = GsonParser.parseString(jsonObject.get("externalId"));
        billingType = GsonParser.parseString(jsonObject.get("billingType"));
        quality = GsonParser.parseString(jsonObject.get("quality"));
        handlingType = GsonParser.parseString(jsonObject.get("handlingType"));
        cdnName = GsonParser.parseString(jsonObject.get("cdnName"));
        cdnCode = GsonParser.parseString(jsonObject.get("cdnCode"));
        altCdnCode = GsonParser.parseString(jsonObject.get("altCdnCode"));
        ppvModules = GsonParser.parseObject(jsonObject.getAsJsonObject("ppvModules"), StringValueArray.class);
        productCode = GsonParser.parseString(jsonObject.get("productCode"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaMediaFile");
        kparams.add("assetId", this.assetId);
        kparams.add("type", this.type);
        kparams.add("url", this.url);
        kparams.add("duration", this.duration);
        kparams.add("externalId", this.externalId);
        kparams.add("billingType", this.billingType);
        kparams.add("quality", this.quality);
        kparams.add("handlingType", this.handlingType);
        kparams.add("cdnName", this.cdnName);
        kparams.add("cdnCode", this.cdnCode);
        kparams.add("altCdnCode", this.altCdnCode);
        kparams.add("ppvModules", this.ppvModules);
        kparams.add("productCode", this.productCode);
        return kparams;
    }

}

