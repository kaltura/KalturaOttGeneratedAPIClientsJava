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
package com.kaltura.client.services;

import com.kaltura.client.enums.HouseholdFrequencyType;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdFilter;
import com.kaltura.client.types.HouseholdPartnerConfiguration;
import com.kaltura.client.types.RetryDeleteRequest;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HouseholdService {
	
	public static class AddHouseholdBuilder extends RequestBuilder<Household, Household.Tokenizer, AddHouseholdBuilder> {
		
		public AddHouseholdBuilder(Household household) {
			super(Household.class, "household", "add");
			params.add("household", household);
		}
	}

	/**
	 * Creates a household for the user
	 * 
	 * @param household Household object
	 */
    public static AddHouseholdBuilder add(Household household)  {
		return new AddHouseholdBuilder(household);
	}
	
	public static class DeleteHouseholdBuilder extends RequestBuilder<Boolean, String, DeleteHouseholdBuilder> {
		
		public DeleteHouseholdBuilder(int id) {
			super(Boolean.class, "household", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	public static DeleteHouseholdBuilder delete()  {
		return delete(Integer.MIN_VALUE);
	}

	/**
	 * Fully delete a household. Delete all of the household information, including
	  users, devices, entitlements, payment methods and notification date.
	 * 
	 * @param id Household identifier
	 */
    public static DeleteHouseholdBuilder delete(int id)  {
		return new DeleteHouseholdBuilder(id);
	}
	
	public static class GetHouseholdBuilder extends RequestBuilder<Household, Household.Tokenizer, GetHouseholdBuilder> {
		
		public GetHouseholdBuilder(int id) {
			super(Household.class, "household", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	public static GetHouseholdBuilder get()  {
		return get(Integer.MIN_VALUE);
	}

	/**
	 * Returns the household model
	 * 
	 * @param id Household identifier
	 */
    public static GetHouseholdBuilder get(int id)  {
		return new GetHouseholdBuilder(id);
	}
	
	public static class GetPartnerConfigurationHouseholdBuilder extends RequestBuilder<HouseholdPartnerConfiguration, HouseholdPartnerConfiguration.Tokenizer, GetPartnerConfigurationHouseholdBuilder> {
		
		public GetPartnerConfigurationHouseholdBuilder() {
			super(HouseholdPartnerConfiguration.class, "household", "getPartnerConfiguration");
		}
	}

	/**
	 * Get household partner configuration
	 */
    public static GetPartnerConfigurationHouseholdBuilder getPartnerConfiguration()  {
		return new GetPartnerConfigurationHouseholdBuilder();
	}
	
	public static class ListHouseholdBuilder extends ListResponseRequestBuilder<Household, Household.Tokenizer, ListHouseholdBuilder> {
		
		public ListHouseholdBuilder(HouseholdFilter filter, FilterPager pager) {
			super(Household.class, "household", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListHouseholdBuilder list(HouseholdFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Retrive household for the partner filter by external identifier
	 * 
	 * @param filter Filter parameters for filtering out the result
	 * @param pager Page size and index. Number of assets to return per page. Possible range 5 ≤
	 * size ≥ 50. If omitted - will be set to 25. If a value &gt; 50 provided –
	 * will set to 50
	 */
    public static ListHouseholdBuilder list(HouseholdFilter filter, FilterPager pager)  {
		return new ListHouseholdBuilder(filter, pager);
	}
	
	public static class PurgeHouseholdBuilder extends RequestBuilder<Boolean, String, PurgeHouseholdBuilder> {
		
		public PurgeHouseholdBuilder(int id) {
			super(Boolean.class, "household", "purge");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Purge a household. Delete all of the household information, including users,
	  devices, entitlements, payment methods and notification date.
	 * 
	 * @param id Household identifier
	 */
    public static PurgeHouseholdBuilder purge(int id)  {
		return new PurgeHouseholdBuilder(id);
	}
	
	public static class ResetFrequencyHouseholdBuilder extends RequestBuilder<Household, Household.Tokenizer, ResetFrequencyHouseholdBuilder> {
		
		public ResetFrequencyHouseholdBuilder(HouseholdFrequencyType frequencyType) {
			super(Household.class, "household", "resetFrequency");
			params.add("frequencyType", frequencyType);
		}
		
		public void frequencyType(String multirequestToken) {
			params.add("frequencyType", multirequestToken);
		}
	}

	/**
	 * Reset a household’s time limitation for removing user or device
	 * 
	 * @param frequencyType Possible values: devices – reset the device change frequency.
	 *             users – reset the user add/remove frequency
	 */
    public static ResetFrequencyHouseholdBuilder resetFrequency(HouseholdFrequencyType frequencyType)  {
		return new ResetFrequencyHouseholdBuilder(frequencyType);
	}
	
	public static class ResumeHouseholdBuilder extends RequestBuilder<Boolean, String, ResumeHouseholdBuilder> {
		
		public ResumeHouseholdBuilder() {
			super(Boolean.class, "household", "resume");
		}
	}

	/**
	 * Resumed a given household service to its previous service settings
	 */
    public static ResumeHouseholdBuilder resume()  {
		return new ResumeHouseholdBuilder();
	}
	
	public static class RetryDeleteHouseholdBuilder extends NullRequestBuilder {
		
		public RetryDeleteHouseholdBuilder(RetryDeleteRequest request) {
			super("household", "retryDelete");
			params.add("request", request);
		}
	}

	/**
	 * Retry delete household entities by retention.
	 * 
	 * @param request Request data
	 */
    public static RetryDeleteHouseholdBuilder retryDelete(RetryDeleteRequest request)  {
		return new RetryDeleteHouseholdBuilder(request);
	}
	
	public static class SuspendHouseholdBuilder extends RequestBuilder<Boolean, String, SuspendHouseholdBuilder> {
		
		public SuspendHouseholdBuilder(int roleId) {
			super(Boolean.class, "household", "suspend");
			params.add("roleId", roleId);
		}
		
		public void roleId(String multirequestToken) {
			params.add("roleId", multirequestToken);
		}
	}

	public static SuspendHouseholdBuilder suspend()  {
		return suspend(Integer.MIN_VALUE);
	}

	/**
	 * Suspend a given household service. Sets the household status to
	  “suspended&amp;quot;.The household service settings are maintained for later
	  resume
	 * 
	 * @param roleId roleId
	 */
    public static SuspendHouseholdBuilder suspend(int roleId)  {
		return new SuspendHouseholdBuilder(roleId);
	}
	
	public static class UpdateHouseholdBuilder extends RequestBuilder<Household, Household.Tokenizer, UpdateHouseholdBuilder> {
		
		public UpdateHouseholdBuilder(Household household) {
			super(Household.class, "household", "update");
			params.add("household", household);
		}
	}

	/**
	 * Update the household name and description
	 * 
	 * @param household Household object
	 */
    public static UpdateHouseholdBuilder update(Household household)  {
		return new UpdateHouseholdBuilder(household);
	}
	
	public static class UpdatePartnerConfigurationHouseholdBuilder extends NullRequestBuilder {
		
		public UpdatePartnerConfigurationHouseholdBuilder(HouseholdPartnerConfiguration configuration) {
			super("household", "updatePartnerConfiguration");
			params.add("configuration", configuration);
		}
	}

	/**
	 * Update household partner configuration
	 * 
	 * @param configuration Household partner configuration details
	 */
    public static UpdatePartnerConfigurationHouseholdBuilder updatePartnerConfiguration(HouseholdPartnerConfiguration configuration)  {
		return new UpdatePartnerConfigurationHouseholdBuilder(configuration);
	}
}
