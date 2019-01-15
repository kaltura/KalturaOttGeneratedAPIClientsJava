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
import com.kaltura.client.enums.SubscriptionSetType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Subscription details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SubscriptionSet.Tokenizer.class)
public abstract class SubscriptionSet extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String type();
		String subscriptionIds();
	}

	/**
	 * SubscriptionSet identifier
	 */
	private Long id;
	/**
	 * SubscriptionSet name
	 */
	private String name;
	/**
	 * Type of the Subscription Set
	 */
	private SubscriptionSetType type;
	/**
	 * A list of comma separated subscription ids associated with this set ordered by
	  priority ascending
	 */
	private String subscriptionIds;

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

	// type:
	public SubscriptionSetType getType(){
		return this.type;
	}
	// subscriptionIds:
	public String getSubscriptionIds(){
		return this.subscriptionIds;
	}
	public void setSubscriptionIds(String subscriptionIds){
		this.subscriptionIds = subscriptionIds;
	}

	public void subscriptionIds(String multirequestToken){
		setToken("subscriptionIds", multirequestToken);
	}


	public SubscriptionSet() {
		super();
	}

	public SubscriptionSet(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		type = SubscriptionSetType.get(GsonParser.parseString(jsonObject.get("type")));
		subscriptionIds = GsonParser.parseString(jsonObject.get("subscriptionIds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscriptionSet");
		kparams.add("name", this.name);
		kparams.add("subscriptionIds", this.subscriptionIds);
		return kparams;
	}

}

