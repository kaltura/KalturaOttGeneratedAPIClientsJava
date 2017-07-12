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
import com.kaltura.client.types.ConfigurationGroupTag;
import com.kaltura.client.types.ConfigurationGroupTagFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupTagService {

	/**  Add a new tag to a configuration group. If this tag is already associated to
	  another group, request fails  */
    public static RequestBuilder<ConfigurationGroupTag> add(ConfigurationGroupTag configurationGroupTag)  {
        Params kparams = new Params();
        kparams.add("configurationGroupTag", configurationGroupTag);

        return new RequestBuilder<ConfigurationGroupTag>(ConfigurationGroupTag.class, "configurationgrouptag", "add", kparams);
    }

	/**  Remove a tag association from configuration group  */
    public static RequestBuilder<Boolean> delete(String tag)  {
        Params kparams = new Params();
        kparams.add("tag", tag);

        return new RequestBuilder<Boolean>(Boolean.class, "configurationgrouptag", "delete", kparams);
    }

	/**  Return the configuration group the tag is associated to  */
    public static RequestBuilder<ConfigurationGroupTag> get(String tag)  {
        Params kparams = new Params();
        kparams.add("tag", tag);

        return new RequestBuilder<ConfigurationGroupTag>(ConfigurationGroupTag.class, "configurationgrouptag", "get", kparams);
    }

	/**  Return list of tags for a configuration group  */
    public static RequestBuilder<ListResponse<ConfigurationGroupTag>> list(ConfigurationGroupTagFilter filter)  {
        Params kparams = new Params();
        kparams.add("filter", filter);

        return new ListResponseRequestBuilder<ConfigurationGroupTag>(ConfigurationGroupTag.class, "configurationgrouptag", "list", kparams);
    }
}
