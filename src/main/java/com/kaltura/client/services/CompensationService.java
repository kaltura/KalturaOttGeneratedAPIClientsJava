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

import com.kaltura.client.types.Compensation;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class CompensationService {
	
	public static class AddCompensationBuilder extends RequestBuilder<Compensation, Compensation.Tokenizer, AddCompensationBuilder> {
		
		public AddCompensationBuilder(Compensation compensation) {
			super(Compensation.class, "compensation", "add");
			params.add("compensation", compensation);
		}
	}

	/**
	 * Adds a new compensation for a household for a given number of iterations of a
	  subscription renewal for a fixed amount / percentage of the renewal price.
	 * 
	 * @param compensation Compensation parameters
	 */
    public static AddCompensationBuilder add(Compensation compensation)  {
		return new AddCompensationBuilder(compensation);
	}
	
	public static class DeleteCompensationBuilder extends NullRequestBuilder {
		
		public DeleteCompensationBuilder(long id) {
			super("compensation", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete a compensation by identifier
	 * 
	 * @param id Compensation identifier
	 */
    public static DeleteCompensationBuilder delete(long id)  {
		return new DeleteCompensationBuilder(id);
	}
	
	public static class GetCompensationBuilder extends RequestBuilder<Compensation, Compensation.Tokenizer, GetCompensationBuilder> {
		
		public GetCompensationBuilder(long id) {
			super(Compensation.class, "compensation", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Get a compensation by identifier
	 * 
	 * @param id Compensation identifier
	 */
    public static GetCompensationBuilder get(long id)  {
		return new GetCompensationBuilder(id);
	}
}
