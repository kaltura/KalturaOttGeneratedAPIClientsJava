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
public class KalturaHouseholdPaymentMethodService extends KalturaServiceBase {
    public KalturaHouseholdPaymentMethodService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Add a new payment method for household  */
    public KalturaHouseholdPaymentMethod add(KalturaHouseholdPaymentMethod householdPaymentMethod) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("householdPaymentMethod", householdPaymentMethod);
        this.kalturaClient.queueServiceCall("householdpaymentmethod", "add", kparams, KalturaHouseholdPaymentMethod.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdPaymentMethod.class, resultXmlElement);
    }

	/**  Force remove of a payment method of the household.  */
    public boolean forceRemove(int paymentGatewayId, int paymentMethodId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        kparams.add("paymentMethodId", paymentMethodId);
        this.kalturaClient.queueServiceCall("householdpaymentmethod", "forceRemove", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Get a list of all payment methods of the household.  */
    public KalturaHouseholdPaymentMethodListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("householdpaymentmethod", "list", kparams, KalturaHouseholdPaymentMethodListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdPaymentMethodListResponse.class, resultXmlElement);
    }

	/**  Removes a payment method of the household.  */
    public boolean remove(int paymentGatewayId, int paymentMethodId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        kparams.add("paymentMethodId", paymentMethodId);
        this.kalturaClient.queueServiceCall("householdpaymentmethod", "remove", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Set a payment method as default for the household.  */
    public boolean setAsDefault(int paymentGatewayId, int paymentMethodId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        kparams.add("paymentMethodId", paymentMethodId);
        this.kalturaClient.queueServiceCall("householdpaymentmethod", "setAsDefault", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
