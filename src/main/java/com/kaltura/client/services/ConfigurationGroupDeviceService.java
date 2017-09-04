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
import com.kaltura.client.types.ConfigurationGroupDevice;
import com.kaltura.client.types.ConfigurationGroupDeviceFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationGroupDeviceService {

	/**  Associate a collection of devices to a configuration group. If a device is
	  already associated to another group – old association is replaced  */
    public static RequestBuilder<Boolean> add(ConfigurationGroupDevice configurationGroupDevice)  {
        Params kparams = new Params();
        kparams.add("configurationGroupDevice", configurationGroupDevice);

        return new RequestBuilder<Boolean>(Boolean.class, "configurationgroupdevice", "add", kparams);
    }

	/**  Remove a device association  */
    public static RequestBuilder<Boolean> delete(String udid)  {
        Params kparams = new Params();
        kparams.add("udid", udid);

        return new RequestBuilder<Boolean>(Boolean.class, "configurationgroupdevice", "delete", kparams);
    }

	/**  Return the configuration group to which a specific device is associated to  */
    public static RequestBuilder<ConfigurationGroupDevice> get(String udid)  {
        Params kparams = new Params();
        kparams.add("udid", udid);

        return new RequestBuilder<ConfigurationGroupDevice>(ConfigurationGroupDevice.class, "configurationgroupdevice", "get", kparams);
    }

    public static RequestBuilder<ListResponse<ConfigurationGroupDevice>> list(ConfigurationGroupDeviceFilter filter)  {
        return list(filter, null);
    }

	/**  Return the list of associated devices for a given configuration group  */
    public static RequestBuilder<ListResponse<ConfigurationGroupDevice>> list(ConfigurationGroupDeviceFilter filter, FilterPager pager)  {
        Params kparams = new Params();
        kparams.add("filter", filter);
        kparams.add("pager", pager);

        return new ListResponseRequestBuilder<ConfigurationGroupDevice>(ConfigurationGroupDevice.class, "configurationgroupdevice", "list", kparams);
    }
}
