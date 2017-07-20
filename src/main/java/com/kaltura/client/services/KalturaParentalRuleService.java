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
import com.kaltura.client.enums.*;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.*;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaParentalRuleService extends KalturaServiceBase {
    public KalturaParentalRuleService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Disables a parental rule that was previously defined by the household master.
	  Disable can be at specific user or household level.  */
    public boolean disable(long ruleId, KalturaEntityReferenceBy entityReference) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ruleId", ruleId);
        kparams.add("entityReference", entityReference);
        this.kalturaClient.queueServiceCall("parentalrule", "disable", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Disables a parental rule that was defined at account level. Disable can be at
	  specific user or household level.  */
    public boolean disableDefault(KalturaEntityReferenceBy entityReference) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entityReference", entityReference);
        this.kalturaClient.queueServiceCall("parentalrule", "disableDefault", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Enable a parental rules for a user  */
    public boolean enable(long ruleId, KalturaEntityReferenceBy entityReference) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("ruleId", ruleId);
        kparams.add("entityReference", entityReference);
        this.kalturaClient.queueServiceCall("parentalrule", "enable", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Return the parental rules that applies for the user or household. Can include
	  rules that have been associated in account, household, or user level.           
	    Association level is also specified in the response.  */
    public KalturaParentalRuleListResponse list(KalturaParentalRuleFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("parentalrule", "list", kparams, KalturaParentalRuleListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaParentalRuleListResponse.class, resultXmlElement);
    }
}
