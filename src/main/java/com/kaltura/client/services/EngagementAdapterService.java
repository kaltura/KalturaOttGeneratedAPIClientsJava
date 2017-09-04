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
import com.kaltura.client.types.EngagementAdapter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EngagementAdapterService {

	/**  Insert new Engagement adapter for partner  */
    public static RequestBuilder<EngagementAdapter> add(EngagementAdapter engagementAdapter)  {
        Params kparams = new Params();
        kparams.add("engagementAdapter", engagementAdapter);

        return new RequestBuilder<EngagementAdapter>(EngagementAdapter.class, "engagementadapter", "add", kparams);
    }

	/**  Delete Engagement adapter by Engagement adapter id  */
    public static RequestBuilder<Boolean> delete(int id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Boolean>(Boolean.class, "engagementadapter", "delete", kparams);
    }

	/**  Generate engagement adapter shared secret  */
    public static RequestBuilder<EngagementAdapter> generateSharedSecret(int id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<EngagementAdapter>(EngagementAdapter.class, "engagementadapter", "generateSharedSecret", kparams);
    }

	/**  Returns all Engagement adapters for partner : id + name  */
    public static RequestBuilder<EngagementAdapter> get(int id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<EngagementAdapter>(EngagementAdapter.class, "engagementadapter", "get", kparams);
    }

	/**  Returns all Engagement adapters for partner : id + name  */
    public static RequestBuilder<ListResponse<EngagementAdapter>> list()  {
        Params kparams = new Params();

        return new ListResponseRequestBuilder<EngagementAdapter>(EngagementAdapter.class, "engagementadapter", "list", kparams);
    }

	/**  Update Engagement adapter details  */
    public static RequestBuilder<EngagementAdapter> update(int id, EngagementAdapter engagementAdapter)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("engagementAdapter", engagementAdapter);

        return new RequestBuilder<EngagementAdapter>(EngagementAdapter.class, "engagementadapter", "update", kparams);
    }
}
