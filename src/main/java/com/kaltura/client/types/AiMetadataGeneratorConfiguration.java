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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * The configuration object for the metadata enrichment feature.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(AiMetadataGeneratorConfiguration.Tokenizer.class)
public class AiMetadataGeneratorConfiguration extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String isEnabled();
		RequestBuilder.MapTokenizer<MetaFieldNameMap.Tokenizer> assetStructMetaNameMap();
		RequestBuilder.ListTokenizer<StringValue.Tokenizer> supportedLanguages();
	}

	/**
	 * Specifies if the feature is enabled or disabled.
	 */
	private Boolean isEnabled;
	/**
	 * A type of dictionary defined as [long,KalturaMetaFieldNameMap].              
	  This property is used to correlate the newly generated metadata to             
	  existing metadata IDs which are available in the asset’s struct.
	 */
	private Map<String, MetaFieldNameMap> assetStructMetaNameMap;
	/**
	 * A read only array to list the set of languages which can be used with the
	  service.              In practice it is populated with the values set in
	  KalturaMetadataGeneratorLanguages ENUM.
	 */
	private List<StringValue> supportedLanguages;

	// isEnabled:
	public Boolean getIsEnabled(){
		return this.isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public void isEnabled(String multirequestToken){
		setToken("isEnabled", multirequestToken);
	}

	// assetStructMetaNameMap:
	public Map<String, MetaFieldNameMap> getAssetStructMetaNameMap(){
		return this.assetStructMetaNameMap;
	}
	public void setAssetStructMetaNameMap(Map<String, MetaFieldNameMap> assetStructMetaNameMap){
		this.assetStructMetaNameMap = assetStructMetaNameMap;
	}

	// supportedLanguages:
	public List<StringValue> getSupportedLanguages(){
		return this.supportedLanguages;
	}

	public AiMetadataGeneratorConfiguration() {
		super();
	}

	public AiMetadataGeneratorConfiguration(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		isEnabled = GsonParser.parseBoolean(jsonObject.get("isEnabled"));
		assetStructMetaNameMap = GsonParser.parseMap(jsonObject.getAsJsonObject("assetStructMetaNameMap"), MetaFieldNameMap.class);
		supportedLanguages = GsonParser.parseArray(jsonObject.getAsJsonArray("supportedLanguages"), StringValue.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaAiMetadataGeneratorConfiguration");
		kparams.add("isEnabled", this.isEnabled);
		kparams.add("assetStructMetaNameMap", this.assetStructMetaNameMap);
		return kparams;
	}

}

