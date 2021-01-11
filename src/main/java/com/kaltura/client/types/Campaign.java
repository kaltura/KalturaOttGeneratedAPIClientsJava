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
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.enums.ObjectState;
import com.kaltura.client.types.Promotion;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Campaign
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Campaign.Tokenizer.class)
public class Campaign extends CrudObject {
	
	public interface Tokenizer extends CrudObject.Tokenizer {
		String id();
		String createDate();
		String updateDate();
		String startDate();
		String endDate();
		String name();
		String systemName();
		String description();
		String state();
		Promotion.Tokenizer promotion();
		String message();
		String collectionIdIn();
	}

	/**
	 * ID
	 */
	private Long id;
	/**
	 * Create date of the rule
	 */
	private Long createDate;
	/**
	 * Update date of the rule
	 */
	private Long updateDate;
	/**
	 * Start date of the rule
	 */
	private Long startDate;
	/**
	 * End date of the rule
	 */
	private Long endDate;
	/**
	 * Name
	 */
	private String name;
	/**
	 * systemName
	 */
	private String systemName;
	/**
	 * Description
	 */
	private String description;
	/**
	 * state
	 */
	private ObjectState state;
	/**
	 * The Promotion that is promoted to the user
	 */
	private Promotion promotion;
	/**
	 * Free text message to the user that gives information about the campaign.
	 */
	private String message;
	/**
	 * Comma separated collection IDs list
	 */
	private String collectionIdIn;

	// id:
	public Long getId(){
		return this.id;
	}
	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// startDate:
	public Long getStartDate(){
		return this.startDate;
	}
	public void setStartDate(Long startDate){
		this.startDate = startDate;
	}

	public void startDate(String multirequestToken){
		setToken("startDate", multirequestToken);
	}

	// endDate:
	public Long getEndDate(){
		return this.endDate;
	}
	public void setEndDate(Long endDate){
		this.endDate = endDate;
	}

	public void endDate(String multirequestToken){
		setToken("endDate", multirequestToken);
	}

	// name:
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// systemName:
	public String getSystemName(){
		return this.systemName;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}

	public void systemName(String multirequestToken){
		setToken("systemName", multirequestToken);
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public void description(String multirequestToken){
		setToken("description", multirequestToken);
	}

	// state:
	public ObjectState getState(){
		return this.state;
	}
	// promotion:
	public Promotion getPromotion(){
		return this.promotion;
	}
	public void setPromotion(Promotion promotion){
		this.promotion = promotion;
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

	// collectionIdIn:
	public String getCollectionIdIn(){
		return this.collectionIdIn;
	}
	public void setCollectionIdIn(String collectionIdIn){
		this.collectionIdIn = collectionIdIn;
	}

	public void collectionIdIn(String multirequestToken){
		setToken("collectionIdIn", multirequestToken);
	}


	public Campaign() {
		super();
	}

	public Campaign(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		name = GsonParser.parseString(jsonObject.get("name"));
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		description = GsonParser.parseString(jsonObject.get("description"));
		state = ObjectState.get(GsonParser.parseString(jsonObject.get("state")));
		promotion = GsonParser.parseObject(jsonObject.getAsJsonObject("promotion"), Promotion.class);
		message = GsonParser.parseString(jsonObject.get("message"));
		collectionIdIn = GsonParser.parseString(jsonObject.get("collectionIdIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCampaign");
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("name", this.name);
		kparams.add("systemName", this.systemName);
		kparams.add("description", this.description);
		kparams.add("promotion", this.promotion);
		kparams.add("message", this.message);
		kparams.add("collectionIdIn", this.collectionIdIn);
		return kparams;
	}

}

