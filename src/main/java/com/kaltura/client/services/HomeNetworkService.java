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

import com.kaltura.client.types.HomeNetwork;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class HomeNetworkService {
	
	public static class AddHomeNetworkBuilder extends RequestBuilder<HomeNetwork, HomeNetwork.Tokenizer, AddHomeNetworkBuilder> {
		
		public AddHomeNetworkBuilder(HomeNetwork homeNetwork) {
			super(HomeNetwork.class, "homenetwork", "add");
			params.add("homeNetwork", homeNetwork);
		}
	}

	/**
	 * Add a new home network to a household
	 * 
	 * @param homeNetwork Home network to add
	 */
    public static AddHomeNetworkBuilder add(HomeNetwork homeNetwork)  {
		return new AddHomeNetworkBuilder(homeNetwork);
	}
	
	public static class DeleteHomeNetworkBuilder extends RequestBuilder<Boolean, String, DeleteHomeNetworkBuilder> {
		
		public DeleteHomeNetworkBuilder(String externalId) {
			super(Boolean.class, "homenetwork", "delete");
			params.add("externalId", externalId);
		}
		
		public void externalId(String multirequestToken) {
			params.add("externalId", multirequestToken);
		}
	}

	/**
	 * Delete household’s existing home network
	 * 
	 * @param externalId The network to update
	 */
    public static DeleteHomeNetworkBuilder delete(String externalId)  {
		return new DeleteHomeNetworkBuilder(externalId);
	}
	
	public static class ListHomeNetworkBuilder extends ListResponseRequestBuilder<HomeNetwork, HomeNetwork.Tokenizer, ListHomeNetworkBuilder> {
		
		public ListHomeNetworkBuilder() {
			super(HomeNetwork.class, "homenetwork", "list");
		}
	}

	/**
	 * Retrieve the household’s home networks
	 */
    public static ListHomeNetworkBuilder list()  {
		return new ListHomeNetworkBuilder();
	}
	
	public static class UpdateHomeNetworkBuilder extends RequestBuilder<HomeNetwork, HomeNetwork.Tokenizer, UpdateHomeNetworkBuilder> {
		
		public UpdateHomeNetworkBuilder(String externalId, HomeNetwork homeNetwork) {
			super(HomeNetwork.class, "homenetwork", "update");
			params.add("externalId", externalId);
			params.add("homeNetwork", homeNetwork);
		}
		
		public void externalId(String multirequestToken) {
			params.add("externalId", multirequestToken);
		}
	}

	/**
	 * Update and existing home network for a household
	 * 
	 * @param externalId Home network identifier
	 * @param homeNetwork Home network to update
	 */
    public static UpdateHomeNetworkBuilder update(String externalId, HomeNetwork homeNetwork)  {
		return new UpdateHomeNetworkBuilder(externalId, homeNetwork);
	}
}
