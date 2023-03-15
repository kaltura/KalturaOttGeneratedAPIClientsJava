// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
// text.
//
// Copyright (C) 2006-2023  Kaltura Inc.
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

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.StringValueArray;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AssetShopCondition.Tokenizer.class)
public class AssetShopCondition extends AssetConditionBase {
	
	public interface Tokenizer extends AssetConditionBase.Tokenizer {
		String value();
		StringValueArray.Tokenizer values();
	}

	/**
	 * Shop marker&amp;#39;s value
	 */
	private String value;
	/**
	 * Shop marker&amp;#39;s values
	 */
	private StringValueArray values;

	// value:
	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}

	public void value(String multirequestToken){
		setToken("value", multirequestToken);
	}

	// values:
	public StringValueArray getValues(){
		return this.values;
	}
	public void setValues(StringValueArray values){
		this.values = values;
	}


	public AssetShopCondition() {
		super();
	}

	public AssetShopCondition(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		value = GsonParser.parseString(jsonObject.get("value"));
		values = GsonParser.parseObject(jsonObject.getAsJsonObject("values"), StringValueArray.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAssetShopCondition");
		kparams.add("value", this.value);
		kparams.add("values", this.values);
		return kparams;
	}

}

