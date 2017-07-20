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
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaPurchase extends KalturaPurchaseBase {
	/**  Identifier for paying currency, according to ISO 4217  */
    public String currency;
	/**  Net sum to charge – as a one-time transaction. Price must match the previously
	  provided price for the specified content.  */
    public double price = Double.MIN_VALUE;
	/**  Identifier for a pre-entered payment method. If not provided – the
	  household’s default payment method is used  */
    public int paymentMethodId = Integer.MIN_VALUE;
	/**  Identifier for a pre-associated payment gateway. If not provided – the
	  account’s default payment gateway is used  */
    public int paymentGatewayId = Integer.MIN_VALUE;
	/**  Coupon code  */
    public String coupon;
	/**  Additional data for the adapter  */
    public String adapterData;

    public KalturaPurchase() {
    }

    public KalturaPurchase(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("currency")) {
                this.currency = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("price")) {
                this.price = ParseUtils.parseDouble(txt);
                continue;
            } else if (nodeName.equals("paymentMethodId")) {
                this.paymentMethodId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("paymentGatewayId")) {
                this.paymentGatewayId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("coupon")) {
                this.coupon = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("adapterData")) {
                this.adapterData = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPurchase");
        kparams.add("currency", this.currency);
        kparams.add("price", this.price);
        kparams.add("paymentMethodId", this.paymentMethodId);
        kparams.add("paymentGatewayId", this.paymentGatewayId);
        kparams.add("coupon", this.coupon);
        kparams.add("adapterData", this.adapterData);
        return kparams;
    }

}

