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
import com.kaltura.client.enums.KalturaBillingItemsType;
import com.kaltura.client.enums.KalturaBillingAction;
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

/**  Billing Transaction  */
@SuppressWarnings("serial")
public class KalturaBillingTransaction extends KalturaObjectBase {
	/**  Reciept Code  */
    public String recieptCode;
	/**  Purchased Item Name  */
    public String purchasedItemName;
	/**  Purchased Item Code  */
    public String purchasedItemCode;
	/**  Item Type  */
    public KalturaBillingItemsType itemType;
	/**  Billing Action  */
    public KalturaBillingAction billingAction;
	/**  price  */
    public KalturaPrice price;
	/**  Action Date  */
    public long actionDate = Long.MIN_VALUE;
	/**  Start Date  */
    public long startDate = Long.MIN_VALUE;
	/**  End Date  */
    public long endDate = Long.MIN_VALUE;
	/**  Payment Method  */
    public KalturaPaymentMethodType paymentMethod;
	/**  Payment Method Extra Details  */
    public String paymentMethodExtraDetails;
	/**  Is Recurring  */
    public boolean isRecurring;
	/**  Billing Provider Ref  */
    public int billingProviderRef = Integer.MIN_VALUE;
	/**  Purchase ID  */
    public int purchaseId = Integer.MIN_VALUE;
	/**  Remarks  */
    public String remarks;

    public KalturaBillingTransaction() {
    }

    public KalturaBillingTransaction(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("recieptCode")) {
                this.recieptCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("purchasedItemName")) {
                this.purchasedItemName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("purchasedItemCode")) {
                this.purchasedItemCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("itemType")) {
                this.itemType = KalturaBillingItemsType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("billingAction")) {
                this.billingAction = KalturaBillingAction.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("price")) {
                this.price = ParseUtils.parseObject(KalturaPrice.class, aNode);
                continue;
            } else if (nodeName.equals("actionDate")) {
                this.actionDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("startDate")) {
                this.startDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("paymentMethod")) {
                this.paymentMethod = KalturaPaymentMethodType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("paymentMethodExtraDetails")) {
                this.paymentMethodExtraDetails = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isRecurring")) {
                this.isRecurring = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("billingProviderRef")) {
                this.billingProviderRef = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("purchaseId")) {
                this.purchaseId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("remarks")) {
                this.remarks = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaBillingTransaction");
        return kparams;
    }

}

