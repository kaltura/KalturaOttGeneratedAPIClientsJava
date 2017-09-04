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
import java.util.List;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Asset count - represents a specific value of the field, its count and its sub
  groups.  */
@SuppressWarnings("serial")
public class AssetCount extends ObjectBase {

	/**  Value  */
    private String value;
	/**  Count  */
    private Integer count;
	/**  Sub groups  */
    private List<AssetsCount> subs;

    // value:
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }

    // count:
    public Integer getCount(){
        return this.count;
    }
    public void setCount(Integer count){
        this.count = count;
    }

    // subs:
    public List<AssetsCount> getSubs(){
        return this.subs;
    }
    public void setSubs(List<AssetsCount> subs){
        this.subs = subs;
    }


    public AssetCount() {
       super();
    }

    public AssetCount(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        value = GsonParser.parseString(jsonObject.get("value"));
        count = GsonParser.parseInt(jsonObject.get("count"));
        subs = GsonParser.parseArray(jsonObject.getAsJsonArray("subs"), AssetsCount.class);

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaAssetCount");
        kparams.add("value", this.value);
        kparams.add("count", this.count);
        kparams.add("subs", this.subs);
        return kparams;
    }

}

