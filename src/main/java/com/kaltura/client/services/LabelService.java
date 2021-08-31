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

import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.Label;
import com.kaltura.client.types.LabelFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class LabelService {
	
	public static class AddLabelBuilder extends RequestBuilder<Label, Label.Tokenizer, AddLabelBuilder> {
		
		public AddLabelBuilder(Label label) {
			super(Label.class, "label", "add");
			params.add("label", label);
		}
	}

	/**
	 * Create a new label associated with a predefined entity attribute. Currently
	  supports only labels on KalturaMediaFile.
	 * 
	 * @param label KalturaLabel object with defined Value.
	 */
    public static AddLabelBuilder add(Label label)  {
		return new AddLabelBuilder(label);
	}
	
	public static class DeleteLabelBuilder extends RequestBuilder<Boolean, String, DeleteLabelBuilder> {
		
		public DeleteLabelBuilder(long id) {
			super(Boolean.class, "label", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Deletes the existing label by its identifier.
	 * 
	 * @param id The identifier of label.
	 */
    public static DeleteLabelBuilder delete(long id)  {
		return new DeleteLabelBuilder(id);
	}
	
	public static class ListLabelBuilder extends ListResponseRequestBuilder<Label, Label.Tokenizer, ListLabelBuilder> {
		
		public ListLabelBuilder(LabelFilter filter, FilterPager pager) {
			super(Label.class, "label", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListLabelBuilder list(LabelFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets list of labels which meet the filter criteria.
	 * 
	 * @param filter Filter.
	 * @param pager Page size and index.
	 */
    public static ListLabelBuilder list(LabelFilter filter, FilterPager pager)  {
		return new ListLabelBuilder(filter, pager);
	}
	
	public static class UpdateLabelBuilder extends RequestBuilder<Label, Label.Tokenizer, UpdateLabelBuilder> {
		
		public UpdateLabelBuilder(long id, Label label) {
			super(Label.class, "label", "update");
			params.add("id", id);
			params.add("label", label);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Updates the existing label with a new value.
	 * 
	 * @param id The identifier of label.
	 * @param label KalturaLabel object with new Value.
	 */
    public static UpdateLabelBuilder update(long id, Label label)  {
		return new UpdateLabelBuilder(id, label);
	}
}
