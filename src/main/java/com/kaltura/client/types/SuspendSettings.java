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
// Copyright (C) 2006-2022  Kaltura Inc.
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
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Suspend Settings
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SuspendSettings.Tokenizer.class)
public class SuspendSettings extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String revokeEntitlements();
		String stopRenew();
	}

	/**
	 * revoke entitlements
	 */
	private Boolean revokeEntitlements;
	/**
	 * stop renew
	 */
	private Boolean stopRenew;

	// revokeEntitlements:
	public Boolean getRevokeEntitlements(){
		return this.revokeEntitlements;
	}
	public void setRevokeEntitlements(Boolean revokeEntitlements){
		this.revokeEntitlements = revokeEntitlements;
	}

	public void revokeEntitlements(String multirequestToken){
		setToken("revokeEntitlements", multirequestToken);
	}

	// stopRenew:
	public Boolean getStopRenew(){
		return this.stopRenew;
	}
	public void setStopRenew(Boolean stopRenew){
		this.stopRenew = stopRenew;
	}

	public void stopRenew(String multirequestToken){
		setToken("stopRenew", multirequestToken);
	}


	public SuspendSettings() {
		super();
	}

	public SuspendSettings(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		revokeEntitlements = GsonParser.parseBoolean(jsonObject.get("revokeEntitlements"));
		stopRenew = GsonParser.parseBoolean(jsonObject.get("stopRenew"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSuspendSettings");
		kparams.add("revokeEntitlements", this.revokeEntitlements);
		kparams.add("stopRenew", this.stopRenew);
		return kparams;
	}

}

