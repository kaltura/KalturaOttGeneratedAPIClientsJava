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
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.enums.*;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaTransactionService extends KalturaServiceBase {
    public KalturaTransactionService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  downgrade specific subscription for a household. entitlements will be updated on
	  the existing subscription end date.  */
    public void downgrade(KalturaPurchase purchase) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("purchase", purchase);
        this.kalturaClient.queueServiceCall("transaction", "downgrade", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

	/**  Retrieve the purchase session identifier  */
    public long getPurchaseSessionId(KalturaPurchaseSession purchaseSession) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("purchaseSession", purchaseSession);
        this.kalturaClient.queueServiceCall("transaction", "getPurchaseSessionId", kparams);
        if (this.kalturaClient.isMultiRequest())
            return 0;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBigint(resultText);
    }

	/**  Purchase specific product or subscription for a household. Upon successful
	  charge entitlements to use the requested product or subscription are granted.  */
    public KalturaTransaction purchase(KalturaPurchase purchase) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("purchase", purchase);
        this.kalturaClient.queueServiceCall("transaction", "purchase", kparams, KalturaTransaction.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaTransaction.class, resultXmlElement);
    }

	/**  This method shall set the waiver flag on the user entitlement table and the
	  waiver date field to the current date.  */
    public boolean setWaiver(int assetId, KalturaTransactionType transactionType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("assetId", assetId);
        kparams.add("transactionType", transactionType);
        this.kalturaClient.queueServiceCall("transaction", "setWaiver", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Updates a pending purchase transaction state.  */
    public void updateStatus(String paymentGatewayId, String externalTransactionId, String signature, KalturaTransactionStatus status) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("paymentGatewayId", paymentGatewayId);
        kparams.add("externalTransactionId", externalTransactionId);
        kparams.add("signature", signature);
        kparams.add("status", status);
        this.kalturaClient.queueServiceCall("transaction", "updateStatus", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

	/**  upgrade specific subscription for a household. Upon successful charge
	  entitlements to use the requested product or subscription are granted.  */
    public KalturaTransaction upgrade(KalturaPurchase purchase) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("purchase", purchase);
        this.kalturaClient.queueServiceCall("transaction", "upgrade", kparams, KalturaTransaction.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaTransaction.class, resultXmlElement);
    }

	/**  Verifies PPV/Subscription/Collection client purchase (such as InApp) and
	  entitles the user.  */
    public KalturaTransaction validateReceipt(KalturaExternalReceipt externalReceipt) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("externalReceipt", externalReceipt);
        this.kalturaClient.queueServiceCall("transaction", "validateReceipt", kparams, KalturaTransaction.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaTransaction.class, resultXmlElement);
    }
}
