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
import com.kaltura.client.enums.SocialActionPrivacy;
import com.kaltura.client.enums.SocialNetwork;
import com.kaltura.client.enums.SocialPrivacy;
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
@MultiRequestBuilder.Tokenizer(ActionPermissionItem.Tokenizer.class)
public class ActionPermissionItem extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String network();
		String actionPrivacy();
		String privacy();
		String action();
	}

	/**
	 * Social network
	 */
	private SocialNetwork network;
	/**
	 * Action privacy
	 */
	private SocialActionPrivacy actionPrivacy;
	/**
	 * Social privacy
	 */
	private SocialPrivacy privacy;
	/**
	 * Action - separated with comma
	 */
	private String action;

	// network:
	public SocialNetwork getNetwork(){
		return this.network;
	}
	public void setNetwork(SocialNetwork network){
		this.network = network;
	}

	public void network(String multirequestToken){
		setToken("network", multirequestToken);
	}

	// actionPrivacy:
	public SocialActionPrivacy getActionPrivacy(){
		return this.actionPrivacy;
	}
	public void setActionPrivacy(SocialActionPrivacy actionPrivacy){
		this.actionPrivacy = actionPrivacy;
	}

	public void actionPrivacy(String multirequestToken){
		setToken("actionPrivacy", multirequestToken);
	}

	// privacy:
	public SocialPrivacy getPrivacy(){
		return this.privacy;
	}
	public void setPrivacy(SocialPrivacy privacy){
		this.privacy = privacy;
	}

	public void privacy(String multirequestToken){
		setToken("privacy", multirequestToken);
	}

	// action:
	public String getAction(){
		return this.action;
	}
	public void setAction(String action){
		this.action = action;
	}

	public void action(String multirequestToken){
		setToken("action", multirequestToken);
	}


	public ActionPermissionItem() {
		super();
	}

	public ActionPermissionItem(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		network = SocialNetwork.get(GsonParser.parseString(jsonObject.get("network")));
		actionPrivacy = SocialActionPrivacy.get(GsonParser.parseString(jsonObject.get("actionPrivacy")));
		privacy = SocialPrivacy.get(GsonParser.parseString(jsonObject.get("privacy")));
		action = GsonParser.parseString(jsonObject.get("action"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaActionPermissionItem");
		kparams.add("network", this.network);
		kparams.add("actionPrivacy", this.actionPrivacy);
		kparams.add("privacy", this.privacy);
		kparams.add("action", this.action);
		return kparams;
	}

}

