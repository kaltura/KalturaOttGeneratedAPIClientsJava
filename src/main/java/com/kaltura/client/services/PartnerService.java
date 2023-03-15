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

import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.Partner;
import com.kaltura.client.types.PartnerFilter;
import com.kaltura.client.types.PartnerSetup;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PartnerService {
	
	public static class AddPartnerBuilder extends RequestBuilder<Partner, Partner.Tokenizer, AddPartnerBuilder> {
		
		public AddPartnerBuilder(Partner partner, PartnerSetup partnerSetup) {
			super(Partner.class, "partner", "add");
			params.add("partner", partner);
			params.add("partnerSetup", partnerSetup);
		}
	}

	/**
	 * Add a partner with default user
	 * 
	 * @param partner partner
	 * @param partnerSetup mandatory parameters to create partner
	 */
    public static AddPartnerBuilder add(Partner partner, PartnerSetup partnerSetup)  {
		return new AddPartnerBuilder(partner, partnerSetup);
	}
	
	public static class CreateIndexesPartnerBuilder extends RequestBuilder<Boolean, String, CreateIndexesPartnerBuilder> {
		
		public CreateIndexesPartnerBuilder() {
			super(Boolean.class, "partner", "createIndexes");
		}
	}

	/**
	 * Internal API !!! create ElasticSearch indexes for partner
	 */
    public static CreateIndexesPartnerBuilder createIndexes()  {
		return new CreateIndexesPartnerBuilder();
	}
	
	public static class DeletePartnerBuilder extends RequestBuilder<Boolean, String, DeletePartnerBuilder> {
		
		public DeletePartnerBuilder(int id) {
			super(Boolean.class, "partner", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Internal API !!! Delete Partner
	 * 
	 * @param id Partner id
	 */
    public static DeletePartnerBuilder delete(int id)  {
		return new DeletePartnerBuilder(id);
	}
	
	public static class ExternalLoginPartnerBuilder extends RequestBuilder<LoginSession, LoginSession.Tokenizer, ExternalLoginPartnerBuilder> {
		
		public ExternalLoginPartnerBuilder() {
			super(LoginSession.class, "partner", "externalLogin");
		}
	}

	/**
	 * Returns a login session for external system (like OVP)
	 */
    public static ExternalLoginPartnerBuilder externalLogin()  {
		return new ExternalLoginPartnerBuilder();
	}
	
	public static class ListPartnerBuilder extends ListResponseRequestBuilder<Partner, Partner.Tokenizer, ListPartnerBuilder> {
		
		public ListPartnerBuilder(PartnerFilter filter) {
			super(Partner.class, "partner", "list");
			params.add("filter", filter);
		}
	}

	public static ListPartnerBuilder list()  {
		return list(null);
	}

	/**
	 * Internal API !!! Returns the list of active Partners
	 * 
	 * @param filter Filter
	 */
    public static ListPartnerBuilder list(PartnerFilter filter)  {
		return new ListPartnerBuilder(filter);
	}
}
