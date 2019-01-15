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
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Entitlements filter
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(EntitlementFilter.Tokenizer.class)
public class EntitlementFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String productTypeEqual();
		String entityReferenceEqual();
		String isExpiredEqual();
	}

	/**
	 * The type of the entitlements to return
	 */
	private TransactionType productTypeEqual;
	/**
	 * Reference type to filter by
	 */
	private EntityReferenceBy entityReferenceEqual;
	/**
	 * Is expired
	 */
	private Boolean isExpiredEqual;

	// productTypeEqual:
	public TransactionType getProductTypeEqual(){
		return this.productTypeEqual;
	}
	public void setProductTypeEqual(TransactionType productTypeEqual){
		this.productTypeEqual = productTypeEqual;
	}

	public void productTypeEqual(String multirequestToken){
		setToken("productTypeEqual", multirequestToken);
	}

	// entityReferenceEqual:
	public EntityReferenceBy getEntityReferenceEqual(){
		return this.entityReferenceEqual;
	}
	public void setEntityReferenceEqual(EntityReferenceBy entityReferenceEqual){
		this.entityReferenceEqual = entityReferenceEqual;
	}

	public void entityReferenceEqual(String multirequestToken){
		setToken("entityReferenceEqual", multirequestToken);
	}

	// isExpiredEqual:
	public Boolean getIsExpiredEqual(){
		return this.isExpiredEqual;
	}
	public void setIsExpiredEqual(Boolean isExpiredEqual){
		this.isExpiredEqual = isExpiredEqual;
	}

	public void isExpiredEqual(String multirequestToken){
		setToken("isExpiredEqual", multirequestToken);
	}


	public EntitlementFilter() {
		super();
	}

	public EntitlementFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		productTypeEqual = TransactionType.get(GsonParser.parseString(jsonObject.get("productTypeEqual")));
		entityReferenceEqual = EntityReferenceBy.get(GsonParser.parseString(jsonObject.get("entityReferenceEqual")));
		isExpiredEqual = GsonParser.parseBoolean(jsonObject.get("isExpiredEqual"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaEntitlementFilter");
		kparams.add("productTypeEqual", this.productTypeEqual);
		kparams.add("entityReferenceEqual", this.entityReferenceEqual);
		kparams.add("isExpiredEqual", this.isExpiredEqual);
		return kparams;
	}

}

