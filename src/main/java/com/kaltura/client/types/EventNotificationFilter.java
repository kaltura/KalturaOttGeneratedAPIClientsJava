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
// Copyright (C) 2006-2019  Kaltura Inc.
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EventNotificationFilter.Tokenizer.class)
public class EventNotificationFilter extends CrudFilter {
	
	public interface Tokenizer extends CrudFilter.Tokenizer {
		String idEqual();
		String objectIdEqual();
		String objectTypeEqual();
	}

	/**
	 * Indicates which event notification to return by their event notifications Id.
	 */
	private String idEqual;
	/**
	 * Indicates which objectId to return by their event notifications.
	 */
	private Long objectIdEqual;
	/**
	 * Indicates which objectType to return by their event notifications.
	 */
	private String objectTypeEqual;

	// idEqual:
	public String getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(String idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// objectIdEqual:
	public Long getObjectIdEqual(){
		return this.objectIdEqual;
	}
	public void setObjectIdEqual(Long objectIdEqual){
		this.objectIdEqual = objectIdEqual;
	}

	public void objectIdEqual(String multirequestToken){
		setToken("objectIdEqual", multirequestToken);
	}

	// objectTypeEqual:
	public String getObjectTypeEqual(){
		return this.objectTypeEqual;
	}
	public void setObjectTypeEqual(String objectTypeEqual){
		this.objectTypeEqual = objectTypeEqual;
	}

	public void objectTypeEqual(String multirequestToken){
		setToken("objectTypeEqual", multirequestToken);
	}


	public EventNotificationFilter() {
		super();
	}

	public EventNotificationFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseString(jsonObject.get("idEqual"));
		objectIdEqual = GsonParser.parseLong(jsonObject.get("objectIdEqual"));
		objectTypeEqual = GsonParser.parseString(jsonObject.get("objectTypeEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEventNotificationFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("objectIdEqual", this.objectIdEqual);
		kparams.add("objectTypeEqual", this.objectTypeEqual);
		return kparams;
	}

}

