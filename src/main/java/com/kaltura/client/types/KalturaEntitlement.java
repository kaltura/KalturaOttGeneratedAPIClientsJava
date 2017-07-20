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
import com.kaltura.client.enums.KalturaPaymentMethodType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Entitlement  */
@SuppressWarnings("serial")
public class KalturaEntitlement extends KalturaObjectBase {
	/**  Purchase identifier (for subscriptions and collections only)  */
    public int id = Integer.MIN_VALUE;
	/**  Entitlement identifier  */
    public String entitlementId;
	/**  The current number of uses  */
    public int currentUses = Integer.MIN_VALUE;
	/**  The end date of the entitlement  */
    public long endDate = Long.MIN_VALUE;
	/**  Current date  */
    public long currentDate = Long.MIN_VALUE;
	/**  The last date the item was viewed  */
    public long lastViewDate = Long.MIN_VALUE;
	/**  Purchase date  */
    public long purchaseDate = Long.MIN_VALUE;
	/**  Payment Method  */
    public KalturaPaymentMethodType paymentMethod;
	/**  The UDID of the device from which the purchase was made  */
    public String deviceUdid;
	/**  The name of the device from which the purchase was made  */
    public String deviceName;
	/**  Indicates whether a cancelation window period is enabled  */
    public boolean isCancelationWindowEnabled;
	/**  The maximum number of uses available for this item (only for subscription and
	  PPV)  */
    public int maxUses = Integer.MIN_VALUE;
	/**  The Identifier of the purchasing user  */
    public String userId;
	/**  The Identifier of the purchasing household  */
    public long householdId = Long.MIN_VALUE;

    public KalturaEntitlement() {
    }

    public KalturaEntitlement(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("entitlementId")) {
                this.entitlementId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("currentUses")) {
                this.currentUses = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("currentDate")) {
                this.currentDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("lastViewDate")) {
                this.lastViewDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("purchaseDate")) {
                this.purchaseDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("paymentMethod")) {
                this.paymentMethod = KalturaPaymentMethodType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("deviceUdid")) {
                this.deviceUdid = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("deviceName")) {
                this.deviceName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isCancelationWindowEnabled")) {
                this.isCancelationWindowEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("maxUses")) {
                this.maxUses = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("userId")) {
                this.userId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("householdId")) {
                this.householdId = ParseUtils.parseBigint(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaEntitlement");
        return kparams;
    }

}

