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
import com.kaltura.client.enums.*;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaHouseholdService extends KalturaServiceBase {
    public KalturaHouseholdService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Creates a household for the user  */
    public KalturaHousehold add(KalturaHousehold household) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("household", household);
        this.kalturaClient.queueServiceCall("household", "add", kparams, KalturaHousehold.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHousehold.class, resultXmlElement);
    }

    public boolean delete() throws KalturaApiException {
        return this.delete(Integer.MIN_VALUE);
    }

	/**  Fully delete a household. Delete all of the household information, including
	  users, devices, entitlements, payment methods and notification date.  */
    public boolean delete(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("household", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaHousehold get() throws KalturaApiException {
        return this.get(Integer.MIN_VALUE);
    }

	/**  Returns the household model  */
    public KalturaHousehold get(int id) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        this.kalturaClient.queueServiceCall("household", "get", kparams, KalturaHousehold.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHousehold.class, resultXmlElement);
    }

	/**  Reset a household’s time limitation for removing user or device  */
    public KalturaHousehold resetFrequency(KalturaHouseholdFrequencyType frequencyType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("frequencyType", frequencyType);
        this.kalturaClient.queueServiceCall("household", "resetFrequency", kparams, KalturaHousehold.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHousehold.class, resultXmlElement);
    }

	/**  Resumed a given household service to its previous service settings  */
    public boolean resume() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("household", "resume", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Suspend a given household service. Sets the household status to
	  “suspended&amp;quot;.The household service settings are maintained for later
	  resume  */
    public boolean suspend() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("household", "suspend", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Update the household name and description  */
    public KalturaHousehold update(KalturaHousehold household) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("household", household);
        this.kalturaClient.queueServiceCall("household", "update", kparams, KalturaHousehold.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHousehold.class, resultXmlElement);
    }
}
