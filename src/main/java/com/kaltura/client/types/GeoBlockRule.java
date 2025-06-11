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
import com.kaltura.client.enums.GeoBlockMode;
import com.kaltura.client.enums.ProxyRuleLevel;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Geo Block Rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GeoBlockRule.Tokenizer.class)
public class GeoBlockRule extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String createDate();
		String updateDate();
		String countryIds();
		String mode();
		String isProxyRuleEnabled();
		String proxyRuleLevel();
	}

	/**
	 * Geo Block Rule id
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Create Date Epoch time in seconds
	 */
	private Long createDate;
	/**
	 * Update Date Epoch time in seconds
	 */
	private Long updateDate;
	/**
	 * comma separated string representing list of countries that the rule shall apply
	  to
	 */
	private String countryIds;
	/**
	 * mode - Defines the geo-blocking strategy based on user location.             
	  AllowOnlySelected - Implements a restrictive whitelist approach where content is
	  only accessible from explicitly selected countries. All other countries are
	  blocked by default.              BlockOnlySelected - Implements a permissive
	  blacklist approach where content is accessible from all countries except those
	  explicitly selected for blocking.
	 */
	private GeoBlockMode mode;
	/**
	 * Should geo block rule check proxy as well
	 */
	private Boolean isProxyRuleEnabled;
	/**
	 * Level of proxy rule check - medium or high
	 */
	private ProxyRuleLevel proxyRuleLevel;

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
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// countryIds:
	public String getCountryIds(){
		return this.countryIds;
	}
	public void setCountryIds(String countryIds){
		this.countryIds = countryIds;
	}

	public void countryIds(String multirequestToken){
		setToken("countryIds", multirequestToken);
	}

	// mode:
	public GeoBlockMode getMode(){
		return this.mode;
	}
	public void setMode(GeoBlockMode mode){
		this.mode = mode;
	}

	public void mode(String multirequestToken){
		setToken("mode", multirequestToken);
	}

	// isProxyRuleEnabled:
	public Boolean getIsProxyRuleEnabled(){
		return this.isProxyRuleEnabled;
	}
	public void setIsProxyRuleEnabled(Boolean isProxyRuleEnabled){
		this.isProxyRuleEnabled = isProxyRuleEnabled;
	}

	public void isProxyRuleEnabled(String multirequestToken){
		setToken("isProxyRuleEnabled", multirequestToken);
	}

	// proxyRuleLevel:
	public ProxyRuleLevel getProxyRuleLevel(){
		return this.proxyRuleLevel;
	}
	public void setProxyRuleLevel(ProxyRuleLevel proxyRuleLevel){
		this.proxyRuleLevel = proxyRuleLevel;
	}

	public void proxyRuleLevel(String multirequestToken){
		setToken("proxyRuleLevel", multirequestToken);
	}


	public GeoBlockRule() {
		super();
	}

	public GeoBlockRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		countryIds = GsonParser.parseString(jsonObject.get("countryIds"));
		mode = GeoBlockMode.get(GsonParser.parseString(jsonObject.get("mode")));
		isProxyRuleEnabled = GsonParser.parseBoolean(jsonObject.get("isProxyRuleEnabled"));
		proxyRuleLevel = ProxyRuleLevel.get(GsonParser.parseString(jsonObject.get("proxyRuleLevel")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGeoBlockRule");
		kparams.add("name", this.name);
		kparams.add("countryIds", this.countryIds);
		kparams.add("mode", this.mode);
		kparams.add("isProxyRuleEnabled", this.isProxyRuleEnabled);
		kparams.add("proxyRuleLevel", this.proxyRuleLevel);
		return kparams;
	}

}

