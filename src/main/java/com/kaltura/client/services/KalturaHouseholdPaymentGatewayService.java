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
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.*;
import java.util.ArrayList;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaHouseholdPaymentGatewayService extends KalturaServiceBase {
    public KalturaHouseholdPaymentGatewayService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Disable payment-gateway on the household  */
    public boolean disable(int paymentGatewayId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "disable", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Enable a payment-gateway provider for the household.  */
    public boolean enable(int paymentGatewayId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "enable", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Get a household’s billing account identifier (charge ID) for a given payment
	  gateway  */
    public String getChargeID(String paymentGatewayExternalId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayExternalId", paymentGatewayExternalId);
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "getChargeID", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseString(resultText);
    }

	/**  Gets the Payment Gateway Configuration for the payment gateway identifier given  */
    public KalturaPaymentGatewayConfiguration invoke(int paymentGatewayId, String intent, ArrayList<KalturaKeyValue> extraParameters) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        kparams.add("intent", intent);
        kparams.add("extraParameters", extraParameters);
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "invoke", kparams, KalturaPaymentGatewayConfiguration.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaPaymentGatewayConfiguration.class, resultXmlElement);
    }

	/**  Get a list of all configured Payment Gateways providers available for the
	  account. For each payment is provided with the household associated payment
	  methods.  */
    public KalturaHouseholdPaymentGatewayListResponse list() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "list", kparams, KalturaHouseholdPaymentGatewayListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaHouseholdPaymentGatewayListResponse.class, resultXmlElement);
    }

	/**  Set user billing account identifier (charge ID), for a specific household and a
	  specific payment gateway  */
    public boolean setChargeID(String paymentGatewayExternalId, String chargeId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayExternalId", paymentGatewayExternalId);
        kparams.add("chargeId", chargeId);
        this.kalturaClient.queueServiceCall("householdpaymentgateway", "setChargeID", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
