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
import com.kaltura.client.enums.AdsPolicy;
import java.util.List;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class PlaybackSource extends MediaFile {

	/**  Source format according to delivery profile streamer type (applehttp, mpegdash
	  etc.)  */
    private String format;
	/**  Comma separated string according to deliveryProfile media protocols
	  (&amp;#39;http,https&amp;#39; etc.)  */
    private String protocols;
	/**  DRM data object containing relevant license URL ,scheme name and certificate  */
    private List<DrmPlaybackPluginData> drm;
	/**  Ads policy  */
    private AdsPolicy adsPolicy;
	/**  The parameters to pass to the ads server  */
    private String adsParam;

    // format:
    public String getFormat(){
        return this.format;
    }
    public void setFormat(String format){
        this.format = format;
    }

    // protocols:
    public String getProtocols(){
        return this.protocols;
    }
    public void setProtocols(String protocols){
        this.protocols = protocols;
    }

    // drm:
    public List<DrmPlaybackPluginData> getDrm(){
        return this.drm;
    }
    public void setDrm(List<DrmPlaybackPluginData> drm){
        this.drm = drm;
    }

    // adsPolicy:
    public AdsPolicy getAdsPolicy(){
        return this.adsPolicy;
    }
    public void setAdsPolicy(AdsPolicy adsPolicy){
        this.adsPolicy = adsPolicy;
    }

    // adsParam:
    public String getAdsParam(){
        return this.adsParam;
    }
    public void setAdsParam(String adsParam){
        this.adsParam = adsParam;
    }


    public PlaybackSource() {
       super();
    }

    public PlaybackSource(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        format = GsonParser.parseString(jsonObject.get("format"));
        protocols = GsonParser.parseString(jsonObject.get("protocols"));
        drm = GsonParser.parseArray(jsonObject.getAsJsonArray("drm"), DrmPlaybackPluginData.class);
        adsPolicy = AdsPolicy.get(GsonParser.parseString(jsonObject.get("adsPolicy")));
        adsParam = GsonParser.parseString(jsonObject.get("adsParam"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaPlaybackSource");
        kparams.add("format", this.format);
        kparams.add("protocols", this.protocols);
        kparams.add("drm", this.drm);
        kparams.add("adsPolicy", this.adsPolicy);
        kparams.add("adsParam", this.adsParam);
        return kparams;
    }

}

