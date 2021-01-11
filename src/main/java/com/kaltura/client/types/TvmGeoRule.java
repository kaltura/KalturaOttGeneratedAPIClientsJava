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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * TVM geo rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TvmGeoRule.Tokenizer.class)
public class TvmGeoRule extends TvmRule {
	
	public interface Tokenizer extends TvmRule.Tokenizer {
		String onlyOrBut();
		String countryIds();
		String proxyRuleId();
		String proxyRuleName();
		String proxyLevelId();
		String proxyLevelName();
	}

	/**
	 * Indicates if the rule is relevent ONLY for the country ids or except country ids
	  here. - is that true?
	 */
	private Boolean onlyOrBut;
	/**
	 * Comma separated list of country Ids.
	 */
	private String countryIds;
	/**
	 * proxyRuleId - what is that?
	 */
	private Integer proxyRuleId;
	/**
	 * proxyRuleName - what is that?
	 */
	private String proxyRuleName;
	/**
	 * proxyLevelId - what is that?
	 */
	private Integer proxyLevelId;
	/**
	 * proxyLevelName - what is that?
	 */
	private String proxyLevelName;

	// onlyOrBut:
	public Boolean getOnlyOrBut(){
		return this.onlyOrBut;
	}
	public void setOnlyOrBut(Boolean onlyOrBut){
		this.onlyOrBut = onlyOrBut;
	}

	public void onlyOrBut(String multirequestToken){
		setToken("onlyOrBut", multirequestToken);
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

	// proxyRuleId:
	public Integer getProxyRuleId(){
		return this.proxyRuleId;
	}
	public void setProxyRuleId(Integer proxyRuleId){
		this.proxyRuleId = proxyRuleId;
	}

	public void proxyRuleId(String multirequestToken){
		setToken("proxyRuleId", multirequestToken);
	}

	// proxyRuleName:
	public String getProxyRuleName(){
		return this.proxyRuleName;
	}
	public void setProxyRuleName(String proxyRuleName){
		this.proxyRuleName = proxyRuleName;
	}

	public void proxyRuleName(String multirequestToken){
		setToken("proxyRuleName", multirequestToken);
	}

	// proxyLevelId:
	public Integer getProxyLevelId(){
		return this.proxyLevelId;
	}
	public void setProxyLevelId(Integer proxyLevelId){
		this.proxyLevelId = proxyLevelId;
	}

	public void proxyLevelId(String multirequestToken){
		setToken("proxyLevelId", multirequestToken);
	}

	// proxyLevelName:
	public String getProxyLevelName(){
		return this.proxyLevelName;
	}
	public void setProxyLevelName(String proxyLevelName){
		this.proxyLevelName = proxyLevelName;
	}

	public void proxyLevelName(String multirequestToken){
		setToken("proxyLevelName", multirequestToken);
	}


	public TvmGeoRule() {
		super();
	}

	public TvmGeoRule(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		onlyOrBut = GsonParser.parseBoolean(jsonObject.get("onlyOrBut"));
		countryIds = GsonParser.parseString(jsonObject.get("countryIds"));
		proxyRuleId = GsonParser.parseInt(jsonObject.get("proxyRuleId"));
		proxyRuleName = GsonParser.parseString(jsonObject.get("proxyRuleName"));
		proxyLevelId = GsonParser.parseInt(jsonObject.get("proxyLevelId"));
		proxyLevelName = GsonParser.parseString(jsonObject.get("proxyLevelName"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTvmGeoRule");
		kparams.add("onlyOrBut", this.onlyOrBut);
		kparams.add("countryIds", this.countryIds);
		kparams.add("proxyRuleId", this.proxyRuleId);
		kparams.add("proxyRuleName", this.proxyRuleName);
		kparams.add("proxyLevelId", this.proxyLevelId);
		kparams.add("proxyLevelName", this.proxyLevelName);
		return kparams;
	}

}

