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
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Base Promotion
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BasePromotion.Tokenizer.class)
public abstract class BasePromotion extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		RequestBuilder.ListTokenizer<Condition.Tokenizer> conditions();
	}

	/**
	 * These conditions define the Promotion that applies on
	 */
	private List<Condition> conditions;

	// conditions:
	public List<Condition> getConditions(){
		return this.conditions;
	}
	public void setConditions(List<Condition> conditions){
		this.conditions = conditions;
	}


	public BasePromotion() {
		super();
	}

	public BasePromotion(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		conditions = GsonParser.parseArray(jsonObject.getAsJsonArray("conditions"), Condition.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBasePromotion");
		kparams.add("conditions", this.conditions);
		return kparams;
	}

}

