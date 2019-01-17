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
import com.kaltura.client.enums.PurchaseSettingsType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Purchase settings and PIN
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(PurchaseSettings.Tokenizer.class)
public class PurchaseSettings extends Pin {
	
	public interface Tokenizer extends Pin.Tokenizer {
		String permission();
	}

	/**
	 * Purchase permission - block, ask or allow
	 */
	private PurchaseSettingsType permission;

	// permission:
	public PurchaseSettingsType getPermission(){
		return this.permission;
	}
	public void setPermission(PurchaseSettingsType permission){
		this.permission = permission;
	}

	public void permission(String multirequestToken){
		setToken("permission", multirequestToken);
	}


	public PurchaseSettings() {
		super();
	}

	public PurchaseSettings(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		permission = PurchaseSettingsType.get(GsonParser.parseString(jsonObject.get("permission")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaPurchaseSettings");
		kparams.add("permission", this.permission);
		return kparams;
	}

}

