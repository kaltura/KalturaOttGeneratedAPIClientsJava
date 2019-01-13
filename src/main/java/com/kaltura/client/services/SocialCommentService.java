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
import com.kaltura.client.types.SocialComment;
import com.kaltura.client.types.SocialCommentFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SocialCommentService {
	
	public static class ListSocialCommentBuilder extends ListResponseRequestBuilder<SocialComment, SocialComment.Tokenizer, ListSocialCommentBuilder> {
		
		public ListSocialCommentBuilder(SocialCommentFilter filter, FilterPager pager) {
			super(SocialComment.class, "socialcomment", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSocialCommentBuilder list(SocialCommentFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get a list of all social comments filtered by asset ID and social platform
	 * 
	 * @param filter Country filter
	 * @param pager Pager
	 */
    public static ListSocialCommentBuilder list(SocialCommentFilter filter, FilterPager pager)  {
		return new ListSocialCommentBuilder(filter, pager);
	}
}
