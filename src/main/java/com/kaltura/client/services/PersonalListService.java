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
import com.kaltura.client.types.PersonalList;
import com.kaltura.client.types.PersonalListFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PersonalListService {
	
	public static class AddPersonalListBuilder extends RequestBuilder<PersonalList, PersonalList.Tokenizer, AddPersonalListBuilder> {
		
		public AddPersonalListBuilder(PersonalList personalList) {
			super(PersonalList.class, "personallist", "add");
			params.add("personalList", personalList);
		}
	}

	/**
	 * Add a user&amp;#39;s personal list item to follow.
	 * 
	 * @param personalList Follow personal list item request parameters
	 */
    public static AddPersonalListBuilder add(PersonalList personalList)  {
		return new AddPersonalListBuilder(personalList);
	}
	
	public static class DeletePersonalListBuilder extends NullRequestBuilder {
		
		public DeletePersonalListBuilder(long personalListId) {
			super("personallist", "delete");
			params.add("personalListId", personalListId);
		}
		
		public void personalListId(String multirequestToken) {
			params.add("personalListId", multirequestToken);
		}
	}

	/**
	 * Remove followed item from user&amp;#39;s personal list
	 * 
	 * @param personalListId personalListId identifier
	 */
    public static DeletePersonalListBuilder delete(long personalListId)  {
		return new DeletePersonalListBuilder(personalListId);
	}
	
	public static class ListPersonalListBuilder extends ListResponseRequestBuilder<PersonalList, PersonalList.Tokenizer, ListPersonalListBuilder> {
		
		public ListPersonalListBuilder(PersonalListFilter filter, FilterPager pager) {
			super(PersonalList.class, "personallist", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListPersonalListBuilder list()  {
		return list(null);
	}

	public static ListPersonalListBuilder list(PersonalListFilter filter)  {
		return list(filter, null);
	}

	/**
	 * List user&amp;#39;s tv personal item to follow.              Possible status
	  codes:
	 * 
	 * @param filter Personal list filter
	 * @param pager pager
	 */
    public static ListPersonalListBuilder list(PersonalListFilter filter, FilterPager pager)  {
		return new ListPersonalListBuilder(filter, pager);
	}
}
