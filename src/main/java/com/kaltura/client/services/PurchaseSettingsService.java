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
package com.kaltura.client.services;

import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.types.PurchaseSettings;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PurchaseSettingsService {
	
	public static class GetPurchaseSettingsBuilder extends RequestBuilder<PurchaseSettings, PurchaseSettings.Tokenizer, GetPurchaseSettingsBuilder> {
		
		public GetPurchaseSettingsBuilder(EntityReferenceBy by) {
			super(PurchaseSettings.class, "purchasesettings", "get");
			params.add("by", by);
		}
		
		public void by(String multirequestToken) {
			params.add("by", multirequestToken);
		}
	}

	/**
	 * Retrieve the purchase settings.              Includes specification of where
	  these settings were defined – account, household or user
	 * 
	 * @param by Reference type to filter by
	 */
    public static GetPurchaseSettingsBuilder get(EntityReferenceBy by)  {
		return new GetPurchaseSettingsBuilder(by);
	}
	
	public static class UpdatePurchaseSettingsBuilder extends RequestBuilder<PurchaseSettings, PurchaseSettings.Tokenizer, UpdatePurchaseSettingsBuilder> {
		
		public UpdatePurchaseSettingsBuilder(EntityReferenceBy entityReference, PurchaseSettings settings) {
			super(PurchaseSettings.class, "purchasesettings", "update");
			params.add("entityReference", entityReference);
			params.add("settings", settings);
		}
		
		public void entityReference(String multirequestToken) {
			params.add("entityReference", multirequestToken);
		}
	}

	/**
	 * Set a purchase PIN for the household or user
	 * 
	 * @param entityReference Reference type to filter by
	 * @param settings New settings to apply
	 */
    public static UpdatePurchaseSettingsBuilder update(EntityReferenceBy entityReference, PurchaseSettings settings)  {
		return new UpdatePurchaseSettingsBuilder(entityReference, settings);
	}
}
