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
import com.kaltura.client.enums.DeviceFamilyType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(DeviceFamilyFilter.Tokenizer.class)
public class DeviceFamilyFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idEqual();
		String nameEqual();
		String typeEqual();
	}

	/**
	 * Filter the device family with this identifier.
	 */
	private Long idEqual;
	/**
	 * Filter the device family with this name.
	 */
	private String nameEqual;
	/**
	 * Filter device families of this type
	 */
	private DeviceFamilyType typeEqual;

	// idEqual:
	public Long getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(Long idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// nameEqual:
	public String getNameEqual(){
		return this.nameEqual;
	}
	public void setNameEqual(String nameEqual){
		this.nameEqual = nameEqual;
	}

	public void nameEqual(String multirequestToken){
		setToken("nameEqual", multirequestToken);
	}

	// typeEqual:
	public DeviceFamilyType getTypeEqual(){
		return this.typeEqual;
	}
	public void setTypeEqual(DeviceFamilyType typeEqual){
		this.typeEqual = typeEqual;
	}

	public void typeEqual(String multirequestToken){
		setToken("typeEqual", multirequestToken);
	}


	public DeviceFamilyFilter() {
		super();
	}

	public DeviceFamilyFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseLong(jsonObject.get("idEqual"));
		nameEqual = GsonParser.parseString(jsonObject.get("nameEqual"));
		typeEqual = DeviceFamilyType.get(GsonParser.parseString(jsonObject.get("typeEqual")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaDeviceFamilyFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("nameEqual", this.nameEqual);
		kparams.add("typeEqual", this.typeEqual);
		return kparams;
	}

}

