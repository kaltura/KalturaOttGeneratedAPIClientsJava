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
import com.kaltura.client.types.ConfigurationGroup;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupService {

	/**  Add a new configuration group  */
    public static RequestBuilder<ConfigurationGroup> add(ConfigurationGroup configurationGroup)  {
        Params kparams = new Params();
        kparams.add("configurationGroup", configurationGroup);

        return new RequestBuilder<ConfigurationGroup>(ConfigurationGroup.class, "configurationgroup", "add", kparams);
    }

	/**  Remove a configuration group, including its tags, device configurations and
	  devices associations  */
    public static RequestBuilder<Boolean> delete(String id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Boolean>(Boolean.class, "configurationgroup", "delete", kparams);
    }

	/**  Return the configuration group details, including group identifiers, tags, and
	  number of associated devices, and list of device configuration  */
    public static RequestBuilder<ConfigurationGroup> get(String id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<ConfigurationGroup>(ConfigurationGroup.class, "configurationgroup", "get", kparams);
    }

	/**  Return the list of configuration groups  */
    public static RequestBuilder<ListResponse<ConfigurationGroup>> list()  {
        Params kparams = new Params();

        return new ListResponseRequestBuilder<ConfigurationGroup>(ConfigurationGroup.class, "configurationgroup", "list", kparams);
    }

	/**  Update configuration group name  */
    public static RequestBuilder<ConfigurationGroup> update(String id, ConfigurationGroup configurationGroup)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("configurationGroup", configurationGroup);

        return new RequestBuilder<ConfigurationGroup>(ConfigurationGroup.class, "configurationgroup", "update", kparams);
    }
}
