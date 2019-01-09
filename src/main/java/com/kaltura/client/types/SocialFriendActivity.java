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
import com.kaltura.client.types.SocialAction;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SocialFriendActivity.Tokenizer.class)
public class SocialFriendActivity extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String userFullName();
		String userPictureUrl();
		SocialAction.Tokenizer socialAction();
	}

	/**
	 * The full name of the user who did the social action
	 */
	private String userFullName;
	/**
	 * The URL of the profile picture of the user who did the social action
	 */
	private String userPictureUrl;
	/**
	 * The social action
	 */
	private SocialAction socialAction;

	// userFullName:
	public String getUserFullName(){
		return this.userFullName;
	}
	public void setUserFullName(String userFullName){
		this.userFullName = userFullName;
	}

	public void userFullName(String multirequestToken){
		setToken("userFullName", multirequestToken);
	}

	// userPictureUrl:
	public String getUserPictureUrl(){
		return this.userPictureUrl;
	}
	public void setUserPictureUrl(String userPictureUrl){
		this.userPictureUrl = userPictureUrl;
	}

	public void userPictureUrl(String multirequestToken){
		setToken("userPictureUrl", multirequestToken);
	}

	// socialAction:
	public SocialAction getSocialAction(){
		return this.socialAction;
	}
	public void setSocialAction(SocialAction socialAction){
		this.socialAction = socialAction;
	}


	public SocialFriendActivity() {
		super();
	}

	public SocialFriendActivity(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		userFullName = GsonParser.parseString(jsonObject.get("userFullName"));
		userPictureUrl = GsonParser.parseString(jsonObject.get("userPictureUrl"));
		socialAction = GsonParser.parseObject(jsonObject.getAsJsonObject("socialAction"), SocialAction.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSocialFriendActivity");
		kparams.add("userFullName", this.userFullName);
		kparams.add("userPictureUrl", this.userPictureUrl);
		kparams.add("socialAction", this.socialAction);
		return kparams;
	}

}

