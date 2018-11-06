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
import com.kaltura.client.types.ChannelOrder;
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

/**
 * Channel details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Channel.Tokenizer.class)
public class Channel extends BaseChannel {
	
	public interface Tokenizer extends BaseChannel.Tokenizer {
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String oldName();
		String systemName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		String oldDescription();
		String isActive();
		ChannelOrder.Tokenizer orderBy();
		String createDate();
		String updateDate();
		String supportSegmentBasedOrdering();
	}

	/**
	 * Channel name
	 */
	private String name;
	/**
	 * Channel name
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * Channel name
	 */
	private String oldName;
	/**
	 * Channel system name
	 */
	private String systemName;
	/**
	 * Cannel description
	 */
	private String description;
	/**
	 * Cannel description
	 */
	private List<TranslationToken> multilingualDescription;
	/**
	 * Cannel description
	 */
	private String oldDescription;
	/**
	 * active status
	 */
	private Boolean isActive;
	/**
	 * Channel order by
	 */
	private ChannelOrder orderBy;
	/**
	 * Specifies when was the Channel was created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Channel last updated. Date and time represented as epoch.
	 */
	private Long updateDate;
	/**
	 * Specifies whether the assets in this channel will be ordered based on their
	  match to the user&amp;#39;s segments (see BEO-5524)
	 */
	private Boolean supportSegmentBasedOrdering;

	// name:
	public String getName(){
		return this.name;
	}
	// multilingualName:
	public List<TranslationToken> getMultilingualName(){
		return this.multilingualName;
	}
	public void setMultilingualName(List<TranslationToken> multilingualName){
		this.multilingualName = multilingualName;
	}

	// oldName:
	public String getOldName(){
		return this.oldName;
	}
	public void setOldName(String oldName){
		this.oldName = oldName;
	}

	public void oldName(String multirequestToken){
		setToken("oldName", multirequestToken);
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
	// multilingualDescription:
	public List<TranslationToken> getMultilingualDescription(){
		return this.multilingualDescription;
	}
	public void setMultilingualDescription(List<TranslationToken> multilingualDescription){
		this.multilingualDescription = multilingualDescription;
	}

	// oldDescription:
	public String getOldDescription(){
		return this.oldDescription;
	}
	public void setOldDescription(String oldDescription){
		this.oldDescription = oldDescription;
	}

	public void oldDescription(String multirequestToken){
		setToken("oldDescription", multirequestToken);
	}

	// isActive:
	public Boolean getIsActive(){
		return this.isActive;
	}
	public void setIsActive(Boolean isActive){
		this.isActive = isActive;
	}

	public void isActive(String multirequestToken){
		setToken("isActive", multirequestToken);
	}

	// orderBy:
	public ChannelOrder getOrderBy(){
		return this.orderBy;
	}
	public void setOrderBy(ChannelOrder orderBy){
		this.orderBy = orderBy;
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// supportSegmentBasedOrdering:
	public Boolean getSupportSegmentBasedOrdering(){
		return this.supportSegmentBasedOrdering;
	}
	public void setSupportSegmentBasedOrdering(Boolean supportSegmentBasedOrdering){
		this.supportSegmentBasedOrdering = supportSegmentBasedOrdering;
	}

	public void supportSegmentBasedOrdering(String multirequestToken){
		setToken("supportSegmentBasedOrdering", multirequestToken);
	}


	public Channel() {
		super();
	}

	public Channel(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		oldName = GsonParser.parseString(jsonObject.get("oldName"));
		systemName = GsonParser.parseString(jsonObject.get("systemName"));
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		oldDescription = GsonParser.parseString(jsonObject.get("oldDescription"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		orderBy = GsonParser.parseObject(jsonObject.getAsJsonObject("orderBy"), ChannelOrder.class);
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		supportSegmentBasedOrdering = GsonParser.parseBoolean(jsonObject.get("supportSegmentBasedOrdering"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaChannel");
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("oldName", this.oldName);
		kparams.add("systemName", this.systemName);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("oldDescription", this.oldDescription);
		kparams.add("isActive", this.isActive);
		kparams.add("orderBy", this.orderBy);
		kparams.add("supportSegmentBasedOrdering", this.supportSegmentBasedOrdering);
		return kparams;
	}

}

