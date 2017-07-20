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
public class KalturaConfigurationGroupTagService extends KalturaServiceBase {
    public KalturaConfigurationGroupTagService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add a new tag to a configuration group. If this tag is already associated to
	  another group, request fails  */
    public KalturaConfigurationGroupTag add(KalturaConfigurationGroupTag configurationGroupTag) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("configurationGroupTag", configurationGroupTag);
        this.kalturaClient.queueServiceCall("configurationgrouptag", "add", kparams, KalturaConfigurationGroupTag.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupTag.class, resultXmlElement);
    }

	/**  Remove a tag association from configuration group  */
    public boolean delete(String tag) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("tag", tag);
        this.kalturaClient.queueServiceCall("configurationgrouptag", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Return the configuration group the tag is associated to  */
    public KalturaConfigurationGroupTag get(String tag) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("tag", tag);
        this.kalturaClient.queueServiceCall("configurationgrouptag", "get", kparams, KalturaConfigurationGroupTag.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupTag.class, resultXmlElement);
    }

	/**  Return list of tags for a configuration group  */
    public KalturaConfigurationGroupTagListResponse list(KalturaConfigurationGroupTagFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("configurationgrouptag", "list", kparams, KalturaConfigurationGroupTagListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaConfigurationGroupTagListResponse.class, resultXmlElement);
    }
}
