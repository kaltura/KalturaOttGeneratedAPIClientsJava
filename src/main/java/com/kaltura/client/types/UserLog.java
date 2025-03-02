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

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * This log entry records an event related to a user&amp;#39;s interaction with the
  Kaltura TV Platform (KTP). The event may be initiated directly by the user or by
  the platform itself in response to user activity.
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserLog.Tokenizer.class)
public class UserLog extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String createDate();
		String userId();
		String message();
	}

	/**
	 * UserLog entry unique identifier
	 */
	private Integer id;
	/**
	 * The log created date in epoch
	 */
	private Integer createDate;
	/**
	 * A valid user unique identifier
	 */
	private Integer userId;
	/**
	 * Log message
	 */
	private String message;

	// id:
	public Integer getId(){
		return this.id;
	}
	// createDate:
	public Integer getCreateDate(){
		return this.createDate;
	}
	// userId:
	public Integer getUserId(){
		return this.userId;
	}
	// message:
	public String getMessage(){
		return this.message;
	}

	public UserLog() {
		super();
	}

	public UserLog(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseInt(jsonObject.get("id"));
		createDate = GsonParser.parseInt(jsonObject.get("createDate"));
		userId = GsonParser.parseInt(jsonObject.get("userId"));
		message = GsonParser.parseString(jsonObject.get("message"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserLog");
		return kparams;
	}

}

