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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.NetworkActionStatus;
import com.kaltura.client.types.SocialAction;
import com.kaltura.client.types.SocialActionFilter;
import com.kaltura.client.types.UserSocialActionResponse;
import com.kaltura.client.utils.request.ArrayRequestBuilder;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SocialActionService {
	
	public static class AddSocialActionBuilder extends RequestBuilder<UserSocialActionResponse, UserSocialActionResponse.Tokenizer, AddSocialActionBuilder> {
		
		public AddSocialActionBuilder(SocialAction socialAction) {
			super(UserSocialActionResponse.class, "socialaction", "add");
			params.add("socialAction", socialAction);
		}
	}

	/**
	 * Insert new user social action
	 * 
	 * @param socialAction social Action Object
	 */
    public static AddSocialActionBuilder add(SocialAction socialAction)  {
		return new AddSocialActionBuilder(socialAction);
	}
	
	public static class DeleteSocialActionBuilder extends ArrayRequestBuilder<NetworkActionStatus, NetworkActionStatus.Tokenizer, DeleteSocialActionBuilder> {
		
		public DeleteSocialActionBuilder(String id) {
			super(NetworkActionStatus.class, "socialaction", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * delete user social action
	 * 
	 * @param id social Action Id
	 */
    public static DeleteSocialActionBuilder delete(String id)  {
		return new DeleteSocialActionBuilder(id);
	}
	
	public static class ListSocialActionBuilder extends ListResponseRequestBuilder<SocialAction, SocialAction.Tokenizer, ListSocialActionBuilder> {
		
		public ListSocialActionBuilder(SocialActionFilter filter, FilterPager pager) {
			super(SocialAction.class, "socialaction", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSocialActionBuilder list(SocialActionFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get list of user social actions
	 * 
	 * @param filter social action filter
	 * @param pager pager
	 */
    public static ListSocialActionBuilder list(SocialActionFilter filter, FilterPager pager)  {
		return new ListSocialActionBuilder(filter, pager);
	}
}
