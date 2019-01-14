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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SocialNetworkComment.Tokenizer.class)
public class SocialNetworkComment extends SocialComment {
	
	public interface Tokenizer extends SocialComment.Tokenizer {
		String likeCounter();
		String authorImageUrl();
	}

	/**
	 * Number of likes
	 */
	private String likeCounter;
	/**
	 * The URL of the profile picture of the author of the comment
	 */
	private String authorImageUrl;

	// likeCounter:
	public String getLikeCounter(){
		return this.likeCounter;
	}
	public void setLikeCounter(String likeCounter){
		this.likeCounter = likeCounter;
	}

	public void likeCounter(String multirequestToken){
		setToken("likeCounter", multirequestToken);
	}

	// authorImageUrl:
	public String getAuthorImageUrl(){
		return this.authorImageUrl;
	}
	public void setAuthorImageUrl(String authorImageUrl){
		this.authorImageUrl = authorImageUrl;
	}

	public void authorImageUrl(String multirequestToken){
		setToken("authorImageUrl", multirequestToken);
	}


	public SocialNetworkComment() {
		super();
	}

	public SocialNetworkComment(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		likeCounter = GsonParser.parseString(jsonObject.get("likeCounter"));
		authorImageUrl = GsonParser.parseString(jsonObject.get("authorImageUrl"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSocialNetworkComment");
		kparams.add("likeCounter", this.likeCounter);
		kparams.add("authorImageUrl", this.authorImageUrl);
		return kparams;
	}

}

