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
public class KalturaUserLoginPinService extends KalturaServiceBase {
    public KalturaUserLoginPinService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaUserLoginPin add() throws KalturaApiException {
        return this.add(null);
    }

	/**  Generate a time and usage expiry login-PIN that can allow a single login per
	  PIN. If an active login-PIN already exists. Calling this API again for same user
	  will add another login-PIN  */
    public KalturaUserLoginPin add(String secret) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("secret", secret);
        this.kalturaClient.queueServiceCall("userloginpin", "add", kparams, KalturaUserLoginPin.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaUserLoginPin.class, resultXmlElement);
    }

	/**  Immediately deletes a given pre set login pin code for the user.  */
    public boolean delete(String pinCode) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("pinCode", pinCode);
        this.kalturaClient.queueServiceCall("userloginpin", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Immediately expire all active login-PINs for a user  */
    public boolean deleteAll() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("userloginpin", "deleteAll", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaUserLoginPin update(String pinCode) throws KalturaApiException {
        return this.update(pinCode, null);
    }

	/**  Set a time and usage expiry login-PIN that can allow a single login per PIN. If
	  an active login-PIN already exists. Calling this API again for same user will
	  add another login-PIN  */
    public KalturaUserLoginPin update(String pinCode, String secret) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("pinCode", pinCode);
        kparams.add("secret", secret);
        this.kalturaClient.queueServiceCall("userloginpin", "update", kparams, KalturaUserLoginPin.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaUserLoginPin.class, resultXmlElement);
    }
}
