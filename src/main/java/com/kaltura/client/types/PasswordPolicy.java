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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Password policy settings
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PasswordPolicy.Tokenizer.class)
public class PasswordPolicy extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String id();
		String name();
		String userRoleIds();
		String historyCount();
		String expiration();
		RequestBuilder.ListTokenizer<RegexExpression.Tokenizer> complexities();
		String lockoutFailuresCount();
	}

	/**
	 * id
	 */
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Comma separated UserRole Ids list which the policy is applied on
	 */
	private String userRoleIds;
	/**
	 * The number of passwords that should be remembered for each user so that they
	  cannot be reused.
	 */
	private Integer historyCount;
	/**
	 * When should the password expire (will represent time as days).
	 */
	private Integer expiration;
	/**
	 * array of  KalturaRegex
	 */
	private List<RegexExpression> complexities;
	/**
	 * the number of passwords failures before the account is locked.
	 */
	private Integer lockoutFailuresCount;

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

	// userRoleIds:
	public String getUserRoleIds(){
		return this.userRoleIds;
	}
	public void setUserRoleIds(String userRoleIds){
		this.userRoleIds = userRoleIds;
	}

	public void userRoleIds(String multirequestToken){
		setToken("userRoleIds", multirequestToken);
	}

	// historyCount:
	public Integer getHistoryCount(){
		return this.historyCount;
	}
	public void setHistoryCount(Integer historyCount){
		this.historyCount = historyCount;
	}

	public void historyCount(String multirequestToken){
		setToken("historyCount", multirequestToken);
	}

	// expiration:
	public Integer getExpiration(){
		return this.expiration;
	}
	public void setExpiration(Integer expiration){
		this.expiration = expiration;
	}

	public void expiration(String multirequestToken){
		setToken("expiration", multirequestToken);
	}

	// complexities:
	public List<RegexExpression> getComplexities(){
		return this.complexities;
	}
	public void setComplexities(List<RegexExpression> complexities){
		this.complexities = complexities;
	}

	// lockoutFailuresCount:
	public Integer getLockoutFailuresCount(){
		return this.lockoutFailuresCount;
	}
	public void setLockoutFailuresCount(Integer lockoutFailuresCount){
		this.lockoutFailuresCount = lockoutFailuresCount;
	}

	public void lockoutFailuresCount(String multirequestToken){
		setToken("lockoutFailuresCount", multirequestToken);
	}


	public PasswordPolicy() {
		super();
	}

	public PasswordPolicy(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		userRoleIds = GsonParser.parseString(jsonObject.get("userRoleIds"));
		historyCount = GsonParser.parseInt(jsonObject.get("historyCount"));
		expiration = GsonParser.parseInt(jsonObject.get("expiration"));
		complexities = GsonParser.parseArray(jsonObject.getAsJsonArray("complexities"), RegexExpression.class);
		lockoutFailuresCount = GsonParser.parseInt(jsonObject.get("lockoutFailuresCount"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPasswordPolicy");
		kparams.add("name", this.name);
		kparams.add("userRoleIds", this.userRoleIds);
		kparams.add("historyCount", this.historyCount);
		kparams.add("expiration", this.expiration);
		kparams.add("complexities", this.complexities);
		kparams.add("lockoutFailuresCount", this.lockoutFailuresCount);
		return kparams;
	}

}

