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
// Copyright (C) 2006-2022  Kaltura Inc.
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

import com.kaltura.client.types.Epg;
import com.kaltura.client.types.EpgFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EpgService {
	
	public static class ListEpgBuilder extends ListResponseRequestBuilder<Epg, Epg.Tokenizer, ListEpgBuilder> {
		
		public ListEpgBuilder(EpgFilter filter) {
			super(Epg.class, "epg", "list");
			params.add("filter", filter);
		}
	}

	public static ListEpgBuilder list()  {
		return list(null);
	}

	/**
	 * Returns EPG assets.
	 * 
	 * @param filter Filters by EPG live asset identifier and date in unix timestamp, e.g.
	 * 1610928000(January 18, 2021 0:00:00), 1611014400(January 19, 2021 0:00:00)
	 */
    public static ListEpgBuilder list(EpgFilter filter)  {
		return new ListEpgBuilder(filter);
	}
}
