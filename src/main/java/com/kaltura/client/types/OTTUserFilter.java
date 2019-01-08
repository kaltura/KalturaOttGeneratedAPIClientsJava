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

/**
 * OTT User filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(OTTUserFilter.Tokenizer.class)
public class OTTUserFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String usernameEqual();
		String externalIdEqual();
		String idIn();
		String roleIdsIn();
	}

	/**
	 * Username
	 */
	private String usernameEqual;
	/**
	 * User external identifier
	 */
	private String externalIdEqual;
	/**
	 * List of user identifiers separated by &amp;#39;,&amp;#39;
	 */
	private String idIn;
	/**
	 * Comma separated list of role Ids.
	 */
	private String roleIdsIn;

	// usernameEqual:
	public String getUsernameEqual(){
		return this.usernameEqual;
	}
	public void setUsernameEqual(String usernameEqual){
		this.usernameEqual = usernameEqual;
	}

	public void usernameEqual(String multirequestToken){
		setToken("usernameEqual", multirequestToken);
	}

	// externalIdEqual:
	public String getExternalIdEqual(){
		return this.externalIdEqual;
	}
	public void setExternalIdEqual(String externalIdEqual){
		this.externalIdEqual = externalIdEqual;
	}

	public void externalIdEqual(String multirequestToken){
		setToken("externalIdEqual", multirequestToken);
	}

	// idIn:
	public String getIdIn(){
		return this.idIn;
	}
	public void setIdIn(String idIn){
		this.idIn = idIn;
	}

	public void idIn(String multirequestToken){
		setToken("idIn", multirequestToken);
	}

	// roleIdsIn:
	public String getRoleIdsIn(){
		return this.roleIdsIn;
	}
	public void setRoleIdsIn(String roleIdsIn){
		this.roleIdsIn = roleIdsIn;
	}

	public void roleIdsIn(String multirequestToken){
		setToken("roleIdsIn", multirequestToken);
	}


	public OTTUserFilter() {
		super();
	}

	public OTTUserFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		usernameEqual = GsonParser.parseString(jsonObject.get("usernameEqual"));
		externalIdEqual = GsonParser.parseString(jsonObject.get("externalIdEqual"));
		idIn = GsonParser.parseString(jsonObject.get("idIn"));
		roleIdsIn = GsonParser.parseString(jsonObject.get("roleIdsIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaOTTUserFilter");
		kparams.add("usernameEqual", this.usernameEqual);
		kparams.add("externalIdEqual", this.externalIdEqual);
		kparams.add("idIn", this.idIn);
		kparams.add("roleIdsIn", this.roleIdsIn);
		return kparams;
	}

}

