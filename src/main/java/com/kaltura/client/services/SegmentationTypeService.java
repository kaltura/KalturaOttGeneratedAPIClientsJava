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
import com.kaltura.client.types.SegmentationType;
import com.kaltura.client.types.SegmentationTypeFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SegmentationTypeService {
	
	public static class AddSegmentationTypeBuilder extends RequestBuilder<SegmentationType, SegmentationType.Tokenizer, AddSegmentationTypeBuilder> {
		
		public AddSegmentationTypeBuilder(SegmentationType segmentationType) {
			super(SegmentationType.class, "segmentationtype", "add");
			params.add("segmentationType", segmentationType);
		}
	}

	/**
	 * Adds a new segmentation type to the system
	 * 
	 * @param segmentationType The segmentation type to be added
	 */
    public static AddSegmentationTypeBuilder add(SegmentationType segmentationType)  {
		return new AddSegmentationTypeBuilder(segmentationType);
	}
	
	public static class DeleteSegmentationTypeBuilder extends RequestBuilder<Boolean, String, DeleteSegmentationTypeBuilder> {
		
		public DeleteSegmentationTypeBuilder(long id) {
			super(Boolean.class, "segmentationtype", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a segmentation type from the system
	 * 
	 * @param id Segmentation type id
	 */
    public static DeleteSegmentationTypeBuilder delete(long id)  {
		return new DeleteSegmentationTypeBuilder(id);
	}
	
	public static class ListSegmentationTypeBuilder extends ListResponseRequestBuilder<SegmentationType, SegmentationType.Tokenizer, ListSegmentationTypeBuilder> {
		
		public ListSegmentationTypeBuilder(SegmentationTypeFilter filter, FilterPager pager) {
			super(SegmentationType.class, "segmentationtype", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListSegmentationTypeBuilder list()  {
		return list(null);
	}

	public static ListSegmentationTypeBuilder list(SegmentationTypeFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Lists all segmentation types in group
	 * 
	 * @param filter Segmentation type filter - basically empty
	 * @param pager Simple pager
	 */
    public static ListSegmentationTypeBuilder list(SegmentationTypeFilter filter, FilterPager pager)  {
		return new ListSegmentationTypeBuilder(filter, pager);
	}
	
	public static class UpdateSegmentationTypeBuilder extends RequestBuilder<SegmentationType, SegmentationType.Tokenizer, UpdateSegmentationTypeBuilder> {
		
		public UpdateSegmentationTypeBuilder(long segmentationTypeId, SegmentationType segmentationType) {
			super(SegmentationType.class, "segmentationtype", "update");
			params.add("segmentationTypeId", segmentationTypeId);
			params.add("segmentationType", segmentationType);
		}
		
		public void segmentationTypeId(String multirequestToken) {
			params.add("segmentationTypeId", multirequestToken);
		}
	}

	/**
	 * Updates an existing segmentation type
	 * 
	 * @param segmentationTypeId The ID of the object that will be updated
	 * @param segmentationType The segmentation type to be updated
	 */
    public static UpdateSegmentationTypeBuilder update(long segmentationTypeId, SegmentationType segmentationType)  {
		return new UpdateSegmentationTypeBuilder(segmentationTypeId, segmentationType);
	}
}
