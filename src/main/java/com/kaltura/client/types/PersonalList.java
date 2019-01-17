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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PersonalList.Tokenizer.class)
public class PersonalList extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String createDate();
		String ksql();
		String partnerListType();
	}

	/**
	 * Id
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Create Date
	 */
	private Long createDate;
	/**
	 * Search assets using dynamic criteria. Provided collection of nested expressions
	  with key, comparison operators, value, and logical conjunction.             
	  Possible keys: any Tag or Meta defined in the system and the following reserved
	  keys: start_date, end_date.               epg_id, media_id - for specific asset
	  IDs.              geo_block - only valid value is &amp;quot;true&amp;quot;: When
	  enabled, only assets that are not restricted to the user by geo-block rules will
	  return.              parental_rules - only valid value is
	  &amp;quot;true&amp;quot;: When enabled, only assets that the user
	  doesn&amp;#39;t need to provide PIN code will return.             
	  user_interests - only valid value is &amp;quot;true&amp;quot;. When enabled,
	  only assets that the user defined as his interests (by tags and metas) will
	  return.              epg_channel_id – the channel identifier of the EPG
	  program.              entitled_assets - valid values: &amp;quot;free&amp;quot;,
	  &amp;quot;entitled&amp;quot;, &amp;quot;not_entitled&amp;quot;,
	  &amp;quot;both&amp;quot;. free - gets only free to watch assets. entitled - only
	  those that the user is implicitly entitled to watch.              asset_type -
	  valid values: &amp;quot;media&amp;quot;, &amp;quot;epg&amp;quot;,
	  &amp;quot;recording&amp;quot; or any number that represents media type in group.
	               Comparison operators: for numerical fields =, &amp;gt;, &amp;gt;=,
	  &amp;lt;, &amp;lt;=, : (in).               For alpha-numerical fields =, !=
	  (not), ~ (like), !~, ^ (any word starts with), ^= (phrase starts with), +
	  (exists), !+ (not exists).              Logical conjunction: and, or.           
	     Search values are limited to 20 characters each for the next operators: ~,
	  !~, ^, ^=              (maximum length of entire filter is 4096 characters)
	 */
	private String ksql;
	/**
	 * Partner List Type (optional)
	 */
	private Integer partnerListType;

	// id:
	public Long getId(){
		return this.id;
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

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// ksql:
	public String getKsql(){
		return this.ksql;
	}
	public void setKsql(String ksql){
		this.ksql = ksql;
	}

	public void ksql(String multirequestToken){
		setToken("ksql", multirequestToken);
	}

	// partnerListType:
	public Integer getPartnerListType(){
		return this.partnerListType;
	}
	public void setPartnerListType(Integer partnerListType){
		this.partnerListType = partnerListType;
	}

	public void partnerListType(String multirequestToken){
		setToken("partnerListType", multirequestToken);
	}


	public PersonalList() {
		super();
	}

	public PersonalList(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		ksql = GsonParser.parseString(jsonObject.get("ksql"));
		partnerListType = GsonParser.parseInt(jsonObject.get("partnerListType"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPersonalList");
		kparams.add("name", this.name);
		kparams.add("ksql", this.ksql);
		kparams.add("partnerListType", this.partnerListType);
		return kparams;
	}

}

