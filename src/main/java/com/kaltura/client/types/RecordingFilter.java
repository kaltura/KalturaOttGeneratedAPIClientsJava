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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Filtering recordings
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(RecordingFilter.Tokenizer.class)
public class RecordingFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String statusIn();
		String externalRecordingIdIn();
		String kSql();
	}

	/**
	 * Recording Statuses
	 */
	private String statusIn;
	/**
	 * Comma separated external identifiers
	 */
	private String externalRecordingIdIn;
	/**
	 * KSQL expression
	 */
	private String kSql;

	// statusIn:
	public String getStatusIn(){
		return this.statusIn;
	}
	public void setStatusIn(String statusIn){
		this.statusIn = statusIn;
	}

	public void statusIn(String multirequestToken){
		setToken("statusIn", multirequestToken);
	}

	// externalRecordingIdIn:
	public String getExternalRecordingIdIn(){
		return this.externalRecordingIdIn;
	}
	public void setExternalRecordingIdIn(String externalRecordingIdIn){
		this.externalRecordingIdIn = externalRecordingIdIn;
	}

	public void externalRecordingIdIn(String multirequestToken){
		setToken("externalRecordingIdIn", multirequestToken);
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


	public RecordingFilter() {
		super();
	}

	public RecordingFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		statusIn = GsonParser.parseString(jsonObject.get("statusIn"));
		externalRecordingIdIn = GsonParser.parseString(jsonObject.get("externalRecordingIdIn"));
		kSql = GsonParser.parseString(jsonObject.get("kSql"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaRecordingFilter");
		kparams.add("statusIn", this.statusIn);
		kparams.add("externalRecordingIdIn", this.externalRecordingIdIn);
		kparams.add("kSql", this.kSql);
		return kparams;
	}

}

