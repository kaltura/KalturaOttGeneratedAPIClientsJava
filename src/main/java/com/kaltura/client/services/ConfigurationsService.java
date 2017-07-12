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
import com.kaltura.client.types.Configurations;
import com.kaltura.client.types.ConfigurationsFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import com.kaltura.client.utils.request.ServeRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ConfigurationsService {

	/**  Add a new device configuration to a configuration group  */
    public static RequestBuilder<Configurations> add(Configurations configurations)  {
        Params kparams = new Params();
        kparams.add("configurations", configurations);

        return new RequestBuilder<Configurations>(Configurations.class, "configurations", "add", kparams);
    }

	/**  Delete a device configuration  */
    public static RequestBuilder<Boolean> delete(String id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Boolean>(Boolean.class, "configurations", "delete", kparams);
    }

	/**  Return the device configuration  */
    public static RequestBuilder<Configurations> get(String id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Configurations>(Configurations.class, "configurations", "get", kparams);
    }

	/**  Return a list of device configurations of a configuration group  */
    public static RequestBuilder<ListResponse<Configurations>> list(ConfigurationsFilter filter)  {
        Params kparams = new Params();
        kparams.add("filter", filter);

        return new ListResponseRequestBuilder<Configurations>(Configurations.class, "configurations", "list", kparams);
    }

    public static RequestBuilder<String> serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag)  {
        return serveByDevice(applicationName, clientVersion, platform, udid, tag, 0);
    }

	/**  Return a device configuration applicable for a specific device (UDID), app name,
	  software version, platform and optionally a configuration group’s tag  */
    public static RequestBuilder<String> serveByDevice(String applicationName, String clientVersion, String platform, String udid, String tag, int partnerId)  {
        Params kparams = new Params();
        kparams.add("applicationName", applicationName);
        kparams.add("clientVersion", clientVersion);
        kparams.add("platform", platform);
        kparams.add("udid", udid);
        kparams.add("tag", tag);
        kparams.add("partnerId", partnerId);

        return new ServeRequestBuilder("configurations", "serveByDevice", kparams);
    }

	/**  Update device configuration  */
    public static RequestBuilder<Configurations> update(String id, Configurations configurations)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("configurations", configurations);

        return new RequestBuilder<Configurations>(Configurations.class, "configurations", "update", kparams);
    }
}
