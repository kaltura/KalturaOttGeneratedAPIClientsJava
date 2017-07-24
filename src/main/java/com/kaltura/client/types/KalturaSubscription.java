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
package com.kaltura.client.types;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.enums.KalturaSubscriptionDependencyType;
import java.util.ArrayList;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Subscription details  */
@SuppressWarnings("serial")
public class KalturaSubscription extends KalturaObjectBase {
	/**  Subscription identifier  */
    public String id;
	/**  A list of channels associated with this subscription  */
    public ArrayList<KalturaBaseChannel> channels;
	/**  The first date the subscription is available for purchasing  */
    public long startDate = Long.MIN_VALUE;
	/**  The last date the subscription is available for purchasing  */
    public long endDate = Long.MIN_VALUE;
	/**  A list of file types identifiers that are supported in this subscription  */
    public ArrayList<KalturaIntegerValue> fileTypes;
	/**  Denotes whether or not this subscription can be renewed  */
    public boolean isRenewable;
	/**  Defines the number of times this subscription will be renewed  */
    public int renewalsNumber = Integer.MIN_VALUE;
	/**  Indicates whether the subscription will renew forever  */
    public boolean isInfiniteRenewal;
	/**  The price of the subscription  */
    public KalturaPriceDetails price;
	/**  The internal discount module for the subscription  */
    public KalturaDiscountModule discountModule;
	/**  Name of the subscription  */
    public String name;
	/**  Name of the subscription  */
    public KalturaMultilingualString multilingualName;
	/**  description of the subscription  */
    public String description;
	/**  description of the subscription  */
    public KalturaMultilingualString multilingualDescription;
	/**  Identifier of the media associated with the subscription  */
    public int mediaId = Integer.MIN_VALUE;
	/**  Subscription order (when returned in methods that retrieve subscriptions)  */
    public long prorityInOrder = Long.MIN_VALUE;
	/**  Comma separated subscription price plan IDs  */
    public String pricePlanIds;
	/**  Subscription preview module  */
    public KalturaPreviewModule previewModule;
	/**  The household limitation module identifier associated with this subscription  */
    public int householdLimitationsId = Integer.MIN_VALUE;
	/**  The subscription grace period in minutes  */
    public int gracePeriodMinutes = Integer.MIN_VALUE;
	/**  List of premium services included in the subscription  */
    public ArrayList<KalturaPremiumService> premiumServices;
	/**  The maximum number of times an item in this usage module can be viewed  */
    public int maxViewsNumber = Integer.MIN_VALUE;
	/**  The amount time an item is available for viewing since a user started watching
	  the item  */
    public int viewLifeCycle = Integer.MIN_VALUE;
	/**  Time period during which the end user can waive his rights to cancel a purchase.
	  When the time period is passed, the purchase can no longer be cancelled  */
    public int waiverPeriod = Integer.MIN_VALUE;
	/**  Indicates whether or not the end user has the right to waive his rights to
	  cancel a purchase  */
    public boolean isWaiverEnabled;
	/**  List of permitted user types for the subscription  */
    public ArrayList<KalturaOTTUserType> userTypes;
	/**  List of Coupons group  */
    public ArrayList<KalturaCouponsGroup> couponsGroups;
	/**  List of Subscription product codes  */
    public ArrayList<KalturaProductCode> productCodes;
	/**  Dependency Type  */
    public KalturaSubscriptionDependencyType dependencyType;
	/**  External ID  */
    public String externalId;

    public KalturaSubscription() {
    }

    public KalturaSubscription(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("channels")) {
                this.channels = ParseUtils.parseArray(KalturaBaseChannel.class, aNode);
                continue;
            } else if (nodeName.equals("startDate")) {
                this.startDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("fileTypes")) {
                this.fileTypes = ParseUtils.parseArray(KalturaIntegerValue.class, aNode);
                continue;
            } else if (nodeName.equals("isRenewable")) {
                this.isRenewable = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("renewalsNumber")) {
                this.renewalsNumber = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("isInfiniteRenewal")) {
                this.isInfiniteRenewal = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("price")) {
                this.price = ParseUtils.parseObject(KalturaPriceDetails.class, aNode);
                continue;
            } else if (nodeName.equals("discountModule")) {
                this.discountModule = ParseUtils.parseObject(KalturaDiscountModule.class, aNode);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("multilingualName")) {
                this.multilingualName = ParseUtils.parseObject(KalturaMultilingualString.class, aNode);
                continue;
            } else if (nodeName.equals("description")) {
                this.description = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("multilingualDescription")) {
                this.multilingualDescription = ParseUtils.parseObject(KalturaMultilingualString.class, aNode);
                continue;
            } else if (nodeName.equals("mediaId")) {
                this.mediaId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("prorityInOrder")) {
                this.prorityInOrder = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("pricePlanIds")) {
                this.pricePlanIds = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("previewModule")) {
                this.previewModule = ParseUtils.parseObject(KalturaPreviewModule.class, aNode);
                continue;
            } else if (nodeName.equals("householdLimitationsId")) {
                this.householdLimitationsId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("gracePeriodMinutes")) {
                this.gracePeriodMinutes = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("premiumServices")) {
                this.premiumServices = ParseUtils.parseArray(KalturaPremiumService.class, aNode);
                continue;
            } else if (nodeName.equals("maxViewsNumber")) {
                this.maxViewsNumber = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("viewLifeCycle")) {
                this.viewLifeCycle = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("waiverPeriod")) {
                this.waiverPeriod = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("isWaiverEnabled")) {
                this.isWaiverEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("userTypes")) {
                this.userTypes = ParseUtils.parseArray(KalturaOTTUserType.class, aNode);
                continue;
            } else if (nodeName.equals("couponsGroups")) {
                this.couponsGroups = ParseUtils.parseArray(KalturaCouponsGroup.class, aNode);
                continue;
            } else if (nodeName.equals("productCodes")) {
                this.productCodes = ParseUtils.parseArray(KalturaProductCode.class, aNode);
                continue;
            } else if (nodeName.equals("dependencyType")) {
                this.dependencyType = KalturaSubscriptionDependencyType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaSubscription");
        kparams.add("id", this.id);
        kparams.add("channels", this.channels);
        kparams.add("startDate", this.startDate);
        kparams.add("endDate", this.endDate);
        kparams.add("fileTypes", this.fileTypes);
        kparams.add("isRenewable", this.isRenewable);
        kparams.add("renewalsNumber", this.renewalsNumber);
        kparams.add("isInfiniteRenewal", this.isInfiniteRenewal);
        kparams.add("price", this.price);
        kparams.add("discountModule", this.discountModule);
        kparams.add("name", this.name);
        kparams.add("multilingualName", this.multilingualName);
        kparams.add("description", this.description);
        kparams.add("multilingualDescription", this.multilingualDescription);
        kparams.add("mediaId", this.mediaId);
        kparams.add("prorityInOrder", this.prorityInOrder);
        kparams.add("pricePlanIds", this.pricePlanIds);
        kparams.add("previewModule", this.previewModule);
        kparams.add("householdLimitationsId", this.householdLimitationsId);
        kparams.add("gracePeriodMinutes", this.gracePeriodMinutes);
        kparams.add("premiumServices", this.premiumServices);
        kparams.add("maxViewsNumber", this.maxViewsNumber);
        kparams.add("viewLifeCycle", this.viewLifeCycle);
        kparams.add("waiverPeriod", this.waiverPeriod);
        kparams.add("isWaiverEnabled", this.isWaiverEnabled);
        kparams.add("userTypes", this.userTypes);
        kparams.add("couponsGroups", this.couponsGroups);
        kparams.add("productCodes", this.productCodes);
        kparams.add("dependencyType", this.dependencyType);
        kparams.add("externalId", this.externalId);
        return kparams;
    }

}

