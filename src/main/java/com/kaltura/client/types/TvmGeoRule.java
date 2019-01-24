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
// Copyright (C) 2006-2019  Kaltura Inc.
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

/**
 * TVM geo rule
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(TvmGeoRule.Tokenizer.class)
public class TvmGeoRule extends TvmRule {
	
	public interface Tokenizer extends TvmRule.Tokenizer {
		String onlyOrBut();
		String countryIds();
		String proxyRule();
		String proxyLevel();
	}

	/**
	 * Indicates if the rule is relevent ONLY for the country ids or except country ids
	  here.
	 */
	private Boolean onlyOrBut;
	/**
	 * Comma separated list of country Ids.
	 */
	private String countryIds;
	/**
	 * proxyRule - what is that?
	 */
	private Integer proxyRule;
	/**
	 * proxyLevel - what is that?
	 */
	private Integer proxyLevel;

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

	// proxyRule:
	public Integer getProxyRule(){
		return this.proxyRule;
	}
	public void setProxyRule(Integer proxyRule){
		this.proxyRule = proxyRule;
	}

	public void proxyRule(String multirequestToken){
		setToken("proxyRule", multirequestToken);
	}

	// proxyLevel:
	public Integer getProxyLevel(){
		return this.proxyLevel;
	}
	public void setProxyLevel(Integer proxyLevel){
		this.proxyLevel = proxyLevel;
	}

	public void proxyLevel(String multirequestToken){
		setToken("proxyLevel", multirequestToken);
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
		proxyRule = GsonParser.parseInt(jsonObject.get("proxyRule"));
		proxyLevel = GsonParser.parseInt(jsonObject.get("proxyLevel"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaTvmGeoRule");
		kparams.add("onlyOrBut", this.onlyOrBut);
		kparams.add("countryIds", this.countryIds);
		kparams.add("proxyRule", this.proxyRule);
		kparams.add("proxyLevel", this.proxyLevel);
		return kparams;
	}

}

