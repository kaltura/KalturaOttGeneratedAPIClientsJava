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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Filter for user segments
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserSegmentFilter.Tokenizer.class)
public class UserSegmentFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String userIdEqual();
		String kSql();
	}

	/**
	 * User ID
	 */
	private String userIdEqual;
	/**
	 * KSQL expression
	 */
	private String kSql;

	// userIdEqual:
	public String getUserIdEqual(){
		return this.userIdEqual;
	}
	public void setUserIdEqual(String userIdEqual){
		this.userIdEqual = userIdEqual;
	}

	public void userIdEqual(String multirequestToken){
		setToken("userIdEqual", multirequestToken);
	}

	// kSql:
	public String getKSql(){
		return this.kSql;
	}
	public void setKSql(String kSql){
		this.kSql = kSql;
	}

	public void kSql(String multirequestToken){
		setToken("kSql", multirequestToken);
	}


	public UserSegmentFilter() {
		super();
	}

	public UserSegmentFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		userIdEqual = GsonParser.parseString(jsonObject.get("userIdEqual"));
		kSql = GsonParser.parseString(jsonObject.get("kSql"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserSegmentFilter");
		kparams.add("userIdEqual", this.userIdEqual);
		kparams.add("kSql", this.kSql);
		return kparams;
	}

}

