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
import com.kaltura.client.types.SocialFriendActivity;
import com.kaltura.client.types.SocialFriendActivityFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SocialFriendActivityService {
	
	public static class ListSocialFriendActivityBuilder extends ListResponseRequestBuilder<SocialFriendActivity, SocialFriendActivity.Tokenizer, ListSocialFriendActivityBuilder> {
		
		public ListSocialFriendActivityBuilder(SocialFriendActivityFilter filter, FilterPager pager) {
			super(SocialFriendActivity.class, "socialfriendactivity", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSocialFriendActivityBuilder list()  {
		return list(null);
	}

	public static ListSocialFriendActivityBuilder list(SocialFriendActivityFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get a list of the social friends activity for a user
	 * 
	 * @param filter Social friend activity filter
	 * @param pager Pager
	 */
    public static ListSocialFriendActivityBuilder list(SocialFriendActivityFilter filter, FilterPager pager)  {
		return new ListSocialFriendActivityBuilder(filter, pager);
	}
}
