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
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * If filter properties are empty will return all API action type permission items
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PermissionItemByApiActionFilter.Tokenizer.class)
public class PermissionItemByApiActionFilter extends PermissionItemFilter {
	
	public interface Tokenizer extends PermissionItemFilter.Tokenizer {
		String serviceEqual();
		String actionEqual();
	}

	/**
	 * API service name
	 */
	private String serviceEqual;
	/**
	 * API action name
	 */
	private String actionEqual;

	// serviceEqual:
	public String getServiceEqual(){
		return this.serviceEqual;
	}
	public void setServiceEqual(String serviceEqual){
		this.serviceEqual = serviceEqual;
	}

	public void serviceEqual(String multirequestToken){
		setToken("serviceEqual", multirequestToken);
	}

	// actionEqual:
	public String getActionEqual(){
		return this.actionEqual;
	}
	public void setActionEqual(String actionEqual){
		this.actionEqual = actionEqual;
	}

	public void actionEqual(String multirequestToken){
		setToken("actionEqual", multirequestToken);
	}


	public PermissionItemByApiActionFilter() {
		super();
	}

	public PermissionItemByApiActionFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		serviceEqual = GsonParser.parseString(jsonObject.get("serviceEqual"));
		actionEqual = GsonParser.parseString(jsonObject.get("actionEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPermissionItemByApiActionFilter");
		kparams.add("serviceEqual", this.serviceEqual);
		kparams.add("actionEqual", this.actionEqual);
		return kparams;
	}

}

