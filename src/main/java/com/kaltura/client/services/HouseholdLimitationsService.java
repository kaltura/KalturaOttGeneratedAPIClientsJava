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
// Copyright (C) 2006-2021  Kaltura Inc.
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

import com.kaltura.client.types.HouseholdLimitations;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdLimitationsService {
	
	public static class AddHouseholdLimitationsBuilder extends RequestBuilder<HouseholdLimitations, HouseholdLimitations.Tokenizer, AddHouseholdLimitationsBuilder> {
		
		public AddHouseholdLimitationsBuilder(HouseholdLimitations householdLimitations) {
			super(HouseholdLimitations.class, "householdlimitations", "add");
			params.add("householdLimitations", householdLimitations);
		}
	}

	/**
	 * Add household limitation
	 * 
	 * @param householdLimitations Household limitations
	 */
    public static AddHouseholdLimitationsBuilder add(HouseholdLimitations householdLimitations)  {
		return new AddHouseholdLimitationsBuilder(householdLimitations);
	}
	
	public static class DeleteHouseholdLimitationsBuilder extends RequestBuilder<Boolean, String, DeleteHouseholdLimitationsBuilder> {
		
		public DeleteHouseholdLimitationsBuilder(int householdLimitationsId) {
			super(Boolean.class, "householdlimitations", "delete");
			params.add("householdLimitationsId", householdLimitationsId);
		}
		
		public void householdLimitationsId(String multirequestToken) {
			params.add("householdLimitationsId", multirequestToken);
		}
	}

	/**
	 * Delete household limitation
	 * 
	 * @param householdLimitationsId Id of household limitation
	 */
    public static DeleteHouseholdLimitationsBuilder delete(int householdLimitationsId)  {
		return new DeleteHouseholdLimitationsBuilder(householdLimitationsId);
	}
	
	public static class GetHouseholdLimitationsBuilder extends RequestBuilder<HouseholdLimitations, HouseholdLimitations.Tokenizer, GetHouseholdLimitationsBuilder> {
		
		public GetHouseholdLimitationsBuilder(int id) {
			super(HouseholdLimitations.class, "householdlimitations", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get the limitation module by id
	 * 
	 * @param id Household limitations module identifier
	 */
    public static GetHouseholdLimitationsBuilder get(int id)  {
		return new GetHouseholdLimitationsBuilder(id);
	}
	
	public static class ListHouseholdLimitationsBuilder extends ListResponseRequestBuilder<HouseholdLimitations, HouseholdLimitations.Tokenizer, ListHouseholdLimitationsBuilder> {
		
		public ListHouseholdLimitationsBuilder() {
			super(HouseholdLimitations.class, "householdlimitations", "list");
		}
	}

	/**
	 * Get the list of PartnerConfiguration
	 */
    public static ListHouseholdLimitationsBuilder list()  {
		return new ListHouseholdLimitationsBuilder();
	}
}
