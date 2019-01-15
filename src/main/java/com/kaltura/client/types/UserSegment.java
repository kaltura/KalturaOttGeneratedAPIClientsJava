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
// Copyright (C) 2006-2018  Kaltura Inc.
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
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Indicates a segment of a user
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserSegment.Tokenizer.class)
public class UserSegment extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String segmentId();
		String userId();
	}

	/**
	 * Segment Id
	 */
	private Long segmentId;
	/**
	 * User Id of segment
	 */
	private String userId;

	// segmentId:
	public Long getSegmentId(){
		return this.segmentId;
	}
	public void setSegmentId(Long segmentId){
		this.segmentId = segmentId;
	}

	public void segmentId(String multirequestToken){
		setToken("segmentId", multirequestToken);
	}

	// userId:
	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
	}


	public UserSegment() {
		super();
	}

	public UserSegment(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		segmentId = GsonParser.parseLong(jsonObject.get("segmentId"));
		userId = GsonParser.parseString(jsonObject.get("userId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserSegment");
		kparams.add("segmentId", this.segmentId);
		kparams.add("userId", this.userId);
		return kparams;
	}

}

