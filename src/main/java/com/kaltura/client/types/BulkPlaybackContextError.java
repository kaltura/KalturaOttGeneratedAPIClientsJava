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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Wrapper for KalturaAPIException to make it compatible with KalturaOTTObject
  inheritance requirement
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BulkPlaybackContextError.Tokenizer.class)
public class BulkPlaybackContextError extends BulkResponseObject {
	
	public interface Tokenizer extends BulkResponseObject.Tokenizer {
		String code();
		String message();
		RequestBuilder.ListTokenizer<ApiExceptionArg.Tokenizer> args();
	}

	/**
	 * The error code from the API exception
	 */
	private String code;
	/**
	 * The error message from the API exception
	 */
	private String message;
	/**
	 * Additional error arguments from the API exception
	 */
	private List<ApiExceptionArg> args;

	// code:
	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}

	public void code(String multirequestToken){
		setToken("code", multirequestToken);
	}

	// message:
	public String getMessage(){
		return this.message;
	}
	public void setMessage(String message){
		this.message = message;
	}

	public void message(String multirequestToken){
		setToken("message", multirequestToken);
	}

	// args:
	public List<ApiExceptionArg> getArgs(){
		return this.args;
	}
	public void setArgs(List<ApiExceptionArg> args){
		this.args = args;
	}


	public BulkPlaybackContextError() {
		super();
	}

	public BulkPlaybackContextError(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		code = GsonParser.parseString(jsonObject.get("code"));
		message = GsonParser.parseString(jsonObject.get("message"));
		args = GsonParser.parseArray(jsonObject.getAsJsonArray("args"), ApiExceptionArg.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBulkPlaybackContextError");
		kparams.add("code", this.code);
		kparams.add("message", this.message);
		kparams.add("args", this.args);
		return kparams;
	}

}

