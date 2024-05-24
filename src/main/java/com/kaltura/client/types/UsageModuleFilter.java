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
import com.kaltura.client.types.AssociatedShopEntities;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(UsageModuleFilter.Tokenizer.class)
public class UsageModuleFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String idEqual();
		AssociatedShopEntities.Tokenizer associatedShopEntities();
	}

	/**
	 * usageModule id
	 */
	private Integer idEqual;
	/**
	 * filter all usageModules by associate shop entities
	 */
	private AssociatedShopEntities associatedShopEntities;

	// idEqual:
	public Integer getIdEqual(){
		return this.idEqual;
	}
	public void setIdEqual(Integer idEqual){
		this.idEqual = idEqual;
	}

	public void idEqual(String multirequestToken){
		setToken("idEqual", multirequestToken);
	}

	// associatedShopEntities:
	public AssociatedShopEntities getAssociatedShopEntities(){
		return this.associatedShopEntities;
	}
	public void setAssociatedShopEntities(AssociatedShopEntities associatedShopEntities){
		this.associatedShopEntities = associatedShopEntities;
	}


	public UsageModuleFilter() {
		super();
	}

	public UsageModuleFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		idEqual = GsonParser.parseInt(jsonObject.get("idEqual"));
		associatedShopEntities = GsonParser.parseObject(jsonObject.getAsJsonObject("associatedShopEntities"), AssociatedShopEntities.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaUsageModuleFilter");
		kparams.add("idEqual", this.idEqual);
		kparams.add("associatedShopEntities", this.associatedShopEntities);
		return kparams;
	}

}

