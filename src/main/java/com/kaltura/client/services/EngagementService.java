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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.Params;
import com.kaltura.client.types.Engagement;
import com.kaltura.client.types.EngagementFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EngagementService {

	/**  Insert new Engagement for partner  */
    public static RequestBuilder<Engagement> add(Engagement engagement)  {
        Params kparams = new Params();
        kparams.add("engagement", engagement);

        return new RequestBuilder<Engagement>(Engagement.class, "engagement", "add", kparams);
    }

	/**  Delete engagement by engagement adapter id  */
    public static RequestBuilder<Boolean> delete(int id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Boolean>(Boolean.class, "engagement", "delete", kparams);
    }

	/**  Return engagement  */
    public static RequestBuilder<Engagement> get(int id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Engagement>(Engagement.class, "engagement", "get", kparams);
    }

	/**  Returns all Engagement for partner  */
    public static RequestBuilder<ListResponse<Engagement>> list(EngagementFilter filter)  {
        Params kparams = new Params();
        kparams.add("filter", filter);

        return new ListResponseRequestBuilder<Engagement>(Engagement.class, "engagement", "list", kparams);
    }
}
