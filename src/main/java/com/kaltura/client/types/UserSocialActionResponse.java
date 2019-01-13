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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UserSocialActionResponse.Tokenizer.class)
public class UserSocialActionResponse extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		SocialAction.Tokenizer socialAction();
		RequestBuilder.ListTokenizer<NetworkActionStatus.Tokenizer> failStatus();
	}

	/**
	 * socialAction
	 */
	private SocialAction socialAction;
	/**
	 * List of action permission items
	 */
	private List<NetworkActionStatus> failStatus;

	// socialAction:
	public SocialAction getSocialAction(){
		return this.socialAction;
	}
	public void setSocialAction(SocialAction socialAction){
		this.socialAction = socialAction;
	}

	// failStatus:
	public List<NetworkActionStatus> getFailStatus(){
		return this.failStatus;
	}
	public void setFailStatus(List<NetworkActionStatus> failStatus){
		this.failStatus = failStatus;
	}


	public UserSocialActionResponse() {
		super();
	}

	public UserSocialActionResponse(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		socialAction = GsonParser.parseObject(jsonObject.getAsJsonObject("socialAction"), SocialAction.class);
		failStatus = GsonParser.parseArray(jsonObject.getAsJsonArray("failStatus"), NetworkActionStatus.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserSocialActionResponse");
		kparams.add("socialAction", this.socialAction);
		kparams.add("failStatus", this.failStatus);
		return kparams;
	}

}

