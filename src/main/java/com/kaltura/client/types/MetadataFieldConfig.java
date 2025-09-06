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
import com.kaltura.client.enums.MetadataUpdateOperation;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Configuration for a specific metadata field including system name and update
  operation
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(MetadataFieldConfig.Tokenizer.class)
public class MetadataFieldConfig extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String systemName();
		String operation();
	}

	/**
	 * The system name of the metadata field in the asset struct
	 */
	private String systemName;
	/**
	 * The update operation to be performed on this metadata field
	 */
	private MetadataUpdateOperation operation;

	// systemName:
	public String getSystemName(){
		return this.systemName;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}

	public void systemName(String multirequestToken){
		setToken("systemName", multirequestToken);
	}

	// operation:
	public MetadataUpdateOperation getOperation(){
		return this.operation;
	}
	public void setOperation(MetadataUpdateOperation operation){
		this.operation = operation;
	}

	public void operation(String multirequestToken){
		setToken("operation", multirequestToken);
	}


	public MetadataFieldConfig() {
		super();
	}

	public MetadataFieldConfig(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		operation = MetadataUpdateOperation.get(GsonParser.parseString(jsonObject.get("operation")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaMetadataFieldConfig");
		kparams.add("systemName", this.systemName);
		kparams.add("operation", this.operation);
		return kparams;
	}

}

