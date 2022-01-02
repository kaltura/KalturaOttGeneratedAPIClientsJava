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
import com.kaltura.client.types.ProgramAssetGroupOffer;
import com.kaltura.client.types.ProgramAssetGroupOfferFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ProgramAssetGroupOfferService {
	
	public static class AddProgramAssetGroupOfferBuilder extends RequestBuilder<ProgramAssetGroupOffer, ProgramAssetGroupOffer.Tokenizer, AddProgramAssetGroupOfferBuilder> {
		
		public AddProgramAssetGroupOfferBuilder(ProgramAssetGroupOffer programAssetGroupOffer) {
			super(ProgramAssetGroupOffer.class, "programassetgroupoffer", "add");
			params.add("programAssetGroupOffer", programAssetGroupOffer);
		}
	}

	/**
	 * Insert new ProgramAssetGroupOffer for partner
	 * 
	 * @param programAssetGroupOffer programAssetGroupOffer object
	 */
    public static AddProgramAssetGroupOfferBuilder add(ProgramAssetGroupOffer programAssetGroupOffer)  {
		return new AddProgramAssetGroupOfferBuilder(programAssetGroupOffer);
	}
	
	public static class DeleteProgramAssetGroupOfferBuilder extends RequestBuilder<Boolean, String, DeleteProgramAssetGroupOfferBuilder> {
		
		public DeleteProgramAssetGroupOfferBuilder(long id) {
			super(Boolean.class, "programassetgroupoffer", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete programAssetGroupOffer
	 * 
	 * @param id ProgramAssetGroupOffer id
	 */
    public static DeleteProgramAssetGroupOfferBuilder delete(long id)  {
		return new DeleteProgramAssetGroupOfferBuilder(id);
	}
	
	public static class ListProgramAssetGroupOfferBuilder extends ListResponseRequestBuilder<ProgramAssetGroupOffer, ProgramAssetGroupOffer.Tokenizer, ListProgramAssetGroupOfferBuilder> {
		
		public ListProgramAssetGroupOfferBuilder(ProgramAssetGroupOfferFilter filter, FilterPager pager) {
			super(ProgramAssetGroupOffer.class, "programassetgroupoffer", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListProgramAssetGroupOfferBuilder list()  {
		return list(null);
	}

	public static ListProgramAssetGroupOfferBuilder list(ProgramAssetGroupOfferFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets all Program asset group offer
	 * 
	 * @param filter Filter
	 * @param pager Pager
	 */
    public static ListProgramAssetGroupOfferBuilder list(ProgramAssetGroupOfferFilter filter, FilterPager pager)  {
		return new ListProgramAssetGroupOfferBuilder(filter, pager);
	}
	
	public static class UpdateProgramAssetGroupOfferBuilder extends RequestBuilder<ProgramAssetGroupOffer, ProgramAssetGroupOffer.Tokenizer, UpdateProgramAssetGroupOfferBuilder> {
		
		public UpdateProgramAssetGroupOfferBuilder(long id, ProgramAssetGroupOffer programAssetGroupOffer) {
			super(ProgramAssetGroupOffer.class, "programassetgroupoffer", "update");
			params.add("id", id);
			params.add("programAssetGroupOffer", programAssetGroupOffer);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update ProgramAssetGroupOffer
	 * 
	 * @param id ProgramAssetGroupOffer id
	 * @param programAssetGroupOffer ProgramAssetGroupOffer
	 */
    public static UpdateProgramAssetGroupOfferBuilder update(long id, ProgramAssetGroupOffer programAssetGroupOffer)  {
		return new UpdateProgramAssetGroupOfferBuilder(id, programAssetGroupOffer);
	}
}
