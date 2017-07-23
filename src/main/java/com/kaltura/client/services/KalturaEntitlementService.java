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
public class KalturaEntitlementService extends KalturaServiceBase {
    public KalturaEntitlementService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Immediately cancel a subscription, PPV or collection. Cancel is possible only if
	  within cancellation window and content not already consumed  */
    public boolean cancel(int assetId, KalturaTransactionType transactionType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("assetId", assetId);
        kparams.add("transactionType", transactionType);
        this.kalturaClient.queueServiceCall("entitlement", "cancel", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Cancel a household service subscription at the next renewal. The subscription
	  stays valid till the next renewal.  */
    public void cancelRenewal(String subscriptionId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("subscriptionId", subscriptionId);
        this.kalturaClient.queueServiceCall("entitlement", "cancelRenewal", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

	/**  Cancel Scheduled Subscription  */
    public boolean cancelScheduledSubscription(long scheduledSubscriptionId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("scheduledSubscriptionId", scheduledSubscriptionId);
        this.kalturaClient.queueServiceCall("entitlement", "cancelScheduledSubscription", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Reconcile the user household&amp;#39;s entitlements with an external
	  entitlements source. This request is frequency protected to avoid too frequent
	  calls per household.  */
    public boolean externalReconcile() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("entitlement", "externalReconcile", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Immediately cancel a subscription, PPV or collection. Cancel applies regardless
	  of cancellation window and content consumption status  */
    public boolean forceCancel(int assetId, KalturaTransactionType transactionType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("assetId", assetId);
        kparams.add("transactionType", transactionType);
        this.kalturaClient.queueServiceCall("entitlement", "forceCancel", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public boolean grant(int productId, KalturaTransactionType productType, boolean history) throws KalturaApiException {
        return this.grant(productId, productType, history, 0);
    }

	/**  Grant household for an entitlement for a PPV or Subscription.  */
    public boolean grant(int productId, KalturaTransactionType productType, boolean history, int contentId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("productId", productId);
        kparams.add("productType", productType);
        kparams.add("history", history);
        kparams.add("contentId", contentId);
        this.kalturaClient.queueServiceCall("entitlement", "grant", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

    public KalturaEntitlementListResponse list(KalturaEntitlementFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  Gets all the entitled media items for a household  */
    public KalturaEntitlementListResponse list(KalturaEntitlementFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("entitlement", "list", kparams, KalturaEntitlementListResponse.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEntitlementListResponse.class, resultXmlElement);
    }

	/**  Swap current entitlement (subscription) with new entitlement (subscription) -
	  only Grant  */
    public boolean swap(int currentProductId, int newProductId, boolean history) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("currentProductId", currentProductId);
        kparams.add("newProductId", newProductId);
        kparams.add("history", history);
        this.kalturaClient.queueServiceCall("entitlement", "swap", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  Update Kaltura Entitelment by Purchase id  */
    public KalturaEntitlement update(int id, KalturaEntitlement entitlement) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("entitlement", entitlement);
        this.kalturaClient.queueServiceCall("entitlement", "update", kparams, KalturaEntitlement.class);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaEntitlement.class, resultXmlElement);
    }
}
