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
import com.kaltura.client.enums.AssetType;
import com.kaltura.client.enums.SocialActionType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SocialAction.Tokenizer.class)
public class SocialAction extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String actionType();
		String time();
		String assetId();
		String assetType();
		String url();
	}

	/**
	 * social action document id
	 */
	private String id;
	/**
	 * Action type
	 */
	private SocialActionType actionType;
	/**
	 * EPOC based timestamp for when the action occurred
	 */
	private Long time;
	/**
	 * ID of the asset that was acted upon
	 */
	private Long assetId;
	/**
	 * Type of the asset that was acted upon, currently only VOD (media)
	 */
	private AssetType assetType;
	/**
	 * The value of the url
	 */
	private String url;

	// id:
	public String getId(){
		return this.id;
	}
	// actionType:
	public SocialActionType getActionType(){
		return this.actionType;
	}
	public void setActionType(SocialActionType actionType){
		this.actionType = actionType;
	}

	public void actionType(String multirequestToken){
		setToken("actionType", multirequestToken);
	}

	// time:
	public Long getTime(){
		return this.time;
	}
	public void setTime(Long time){
		this.time = time;
	}

	public void time(String multirequestToken){
		setToken("time", multirequestToken);
	}

	// assetId:
	public Long getAssetId(){
		return this.assetId;
	}
	public void setAssetId(Long assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// assetType:
	public AssetType getAssetType(){
		return this.assetType;
	}
	public void setAssetType(AssetType assetType){
		this.assetType = assetType;
	}

	public void assetType(String multirequestToken){
		setToken("assetType", multirequestToken);
	}

	// url:
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}

	public void url(String multirequestToken){
		setToken("url", multirequestToken);
	}


	public SocialAction() {
		super();
	}

	public SocialAction(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		actionType = SocialActionType.get(GsonParser.parseString(jsonObject.get("actionType")));
		time = GsonParser.parseLong(jsonObject.get("time"));
		assetId = GsonParser.parseLong(jsonObject.get("assetId"));
		assetType = AssetType.get(GsonParser.parseString(jsonObject.get("assetType")));
		url = GsonParser.parseString(jsonObject.get("url"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSocialAction");
		kparams.add("actionType", this.actionType);
		kparams.add("time", this.time);
		kparams.add("assetId", this.assetId);
		kparams.add("assetType", this.assetType);
		kparams.add("url", this.url);
		return kparams;
	}

}

