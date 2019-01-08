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

import com.kaltura.client.types.EngagementAdapter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EngagementAdapterService {
	
	public static class AddEngagementAdapterBuilder extends RequestBuilder<EngagementAdapter, EngagementAdapter.Tokenizer, AddEngagementAdapterBuilder> {
		
		public AddEngagementAdapterBuilder(EngagementAdapter engagementAdapter) {
			super(EngagementAdapter.class, "engagementadapter", "add");
			params.add("engagementAdapter", engagementAdapter);
		}
	}

	/**
	 * Insert new Engagement adapter for partner
	 * 
	 * @param engagementAdapter Engagement adapter Object
	 */
    public static AddEngagementAdapterBuilder add(EngagementAdapter engagementAdapter)  {
		return new AddEngagementAdapterBuilder(engagementAdapter);
	}
	
	public static class DeleteEngagementAdapterBuilder extends RequestBuilder<Boolean, String, DeleteEngagementAdapterBuilder> {
		
		public DeleteEngagementAdapterBuilder(int id) {
			super(Boolean.class, "engagementadapter", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete Engagement adapter by Engagement adapter id
	 * 
	 * @param id Engagement adapter identifier
	 */
    public static DeleteEngagementAdapterBuilder delete(int id)  {
		return new DeleteEngagementAdapterBuilder(id);
	}
	
	public static class GenerateSharedSecretEngagementAdapterBuilder extends RequestBuilder<EngagementAdapter, EngagementAdapter.Tokenizer, GenerateSharedSecretEngagementAdapterBuilder> {
		
		public GenerateSharedSecretEngagementAdapterBuilder(int id) {
			super(EngagementAdapter.class, "engagementadapter", "generateSharedSecret");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Generate engagement adapter shared secret
	 * 
	 * @param id Engagement adapter identifier
	 */
    public static GenerateSharedSecretEngagementAdapterBuilder generateSharedSecret(int id)  {
		return new GenerateSharedSecretEngagementAdapterBuilder(id);
	}
	
	public static class GetEngagementAdapterBuilder extends RequestBuilder<EngagementAdapter, EngagementAdapter.Tokenizer, GetEngagementAdapterBuilder> {
		
		public GetEngagementAdapterBuilder(int id) {
			super(EngagementAdapter.class, "engagementadapter", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns all Engagement adapters for partner : id + name
	 * 
	 * @param id Engagement adapter identifier
	 */
    public static GetEngagementAdapterBuilder get(int id)  {
		return new GetEngagementAdapterBuilder(id);
	}
	
	public static class ListEngagementAdapterBuilder extends ListResponseRequestBuilder<EngagementAdapter, EngagementAdapter.Tokenizer, ListEngagementAdapterBuilder> {
		
		public ListEngagementAdapterBuilder() {
			super(EngagementAdapter.class, "engagementadapter", "list");
		}
	}

	/**
	 * Returns all Engagement adapters for partner : id + name
	 */
    public static ListEngagementAdapterBuilder list()  {
		return new ListEngagementAdapterBuilder();
	}
	
	public static class UpdateEngagementAdapterBuilder extends RequestBuilder<EngagementAdapter, EngagementAdapter.Tokenizer, UpdateEngagementAdapterBuilder> {
		
		public UpdateEngagementAdapterBuilder(int id, EngagementAdapter engagementAdapter) {
			super(EngagementAdapter.class, "engagementadapter", "update");
			params.add("id", id);
			params.add("engagementAdapter", engagementAdapter);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update Engagement adapter details
	 * 
	 * @param id Engagement adapter identifier
	 * @param engagementAdapter Engagement adapter Object
	 */
    public static UpdateEngagementAdapterBuilder update(int id, EngagementAdapter engagementAdapter)  {
		return new UpdateEngagementAdapterBuilder(id, engagementAdapter);
	}
}
