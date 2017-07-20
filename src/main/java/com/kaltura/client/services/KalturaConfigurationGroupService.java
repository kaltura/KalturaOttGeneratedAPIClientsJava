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

import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaServiceBase;
import com.kaltura.client.types.*;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaConfigurationGroupService extends KalturaServiceBase {
    public KalturaConfigurationGroupService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add a new configuration group  */
    public KalturaConfigurationGroup add(KalturaConfigurationGroup configurationGroup) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("configurationGroup", configurationGroup);
        this.kalturaClient.queueServiceCall("configurationgroup", "add", kparams, KalturaConfigurationGroup.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroup.class, resultXmlElement);
    }

	/**  Remove a configuration group, including its tags, device configurations and
	  devices associations  */
    public boolean delete(String id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("configurationgroup", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Return the configuration group details, including group identifiers, tags, and
	  number of associated devices, and list of device configuration  */
    public KalturaConfigurationGroup get(String id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("configurationgroup", "get", kparams, KalturaConfigurationGroup.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroup.class, resultXmlElement);
    }

	/**  Return the list of configuration groups  */
    public KalturaConfigurationGroupListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("configurationgroup", "list", kparams, KalturaConfigurationGroupListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupListResponse.class, resultXmlElement);
    }

	/**  Update configuration group name  */
    public KalturaConfigurationGroup update(String id, KalturaConfigurationGroup configurationGroup) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("configurationGroup", configurationGroup);
        this.kalturaClient.queueServiceCall("configurationgroup", "update", kparams, KalturaConfigurationGroup.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroup.class, resultXmlElement);
    }
}
