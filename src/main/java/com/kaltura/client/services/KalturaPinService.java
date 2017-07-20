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
import com.kaltura.client.enums.*;
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
public class KalturaPinService extends KalturaServiceBase {
    public KalturaPinService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaPin get(KalturaEntityReferenceBy by, KalturaPinType type) throws KalturaApiException {
        return this.get(by, type, Integer.MIN_VALUE);
    }

	/**  Retrieve the parental or purchase PIN that applies for the household or user.
	  Includes specification of where the PIN was defined at – account, household or
	  user  level  */
    public KalturaPin get(KalturaEntityReferenceBy by, KalturaPinType type, int ruleId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("by", by);
        kparams.add("type", type);
        kparams.add("ruleId", ruleId);
        this.kalturaClient.queueServiceCall("pin", "get", kparams, KalturaPin.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPin.class, resultXmlElement);
    }

    public KalturaPin update(KalturaEntityReferenceBy by, KalturaPinType type, KalturaPin pin) throws KalturaApiException {
        return this.update(by, type, pin, Integer.MIN_VALUE);
    }

	/**  Set the parental or purchase PIN that applies for the user or the household.  */
    public KalturaPin update(KalturaEntityReferenceBy by, KalturaPinType type, KalturaPin pin, int ruleId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("by", by);
        kparams.add("type", type);
        kparams.add("pin", pin);
        kparams.add("ruleId", ruleId);
        this.kalturaClient.queueServiceCall("pin", "update", kparams, KalturaPin.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPin.class, resultXmlElement);
    }

    public boolean validate(String pin, KalturaPinType type) throws KalturaApiException {
        return this.validate(pin, type, Integer.MIN_VALUE);
    }

	/**  Validate a purchase or parental PIN for a user.  */
    public boolean validate(String pin, KalturaPinType type, int ruleId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("pin", pin);
        kparams.add("type", type);
        kparams.add("ruleId", ruleId);
        this.kalturaClient.queueServiceCall("pin", "validate", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
