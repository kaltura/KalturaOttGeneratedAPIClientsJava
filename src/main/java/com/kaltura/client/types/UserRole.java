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
@MultiRequestBuilder.Tokenizer(UserRole.Tokenizer.class)
public class UserRole extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String permissionNames();
		String excludedPermissionNames();
	}

	/**
	 * User role identifier
	 */
	private Long id;
	/**
	 * User role name
	 */
	private String name;
	/**
	 * permissions associated with the user role
	 */
	private String permissionNames;
	/**
	 * permissions associated with the user role in is_exclueded = true
	 */
	private String excludedPermissionNames;

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

	// permissionNames:
	public String getPermissionNames(){
		return this.permissionNames;
	}
	public void setPermissionNames(String permissionNames){
		this.permissionNames = permissionNames;
	}

	public void permissionNames(String multirequestToken){
		setToken("permissionNames", multirequestToken);
	}

	// excludedPermissionNames:
	public String getExcludedPermissionNames(){
		return this.excludedPermissionNames;
	}
	public void setExcludedPermissionNames(String excludedPermissionNames){
		this.excludedPermissionNames = excludedPermissionNames;
	}

	public void excludedPermissionNames(String multirequestToken){
		setToken("excludedPermissionNames", multirequestToken);
	}


	public UserRole() {
		super();
	}

	public UserRole(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		permissionNames = GsonParser.parseString(jsonObject.get("permissionNames"));
		excludedPermissionNames = GsonParser.parseString(jsonObject.get("excludedPermissionNames"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUserRole");
		kparams.add("name", this.name);
		kparams.add("permissionNames", this.permissionNames);
		kparams.add("excludedPermissionNames", this.excludedPermissionNames);
		return kparams;
	}

}

