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
public class KalturaHouseholdPaymentMethod extends KalturaObjectBase {
	/**  Household payment method identifier (internal)  */
    public int id = Integer.MIN_VALUE;
	/**  External identifier for the household payment method  */
    public String externalId;
	/**  Payment-gateway identifier  */
    public int paymentGatewayId = Integer.MIN_VALUE;
	/**  Description of the payment method details  */
    public String details;
	/**  indicates whether the payment method is set as default for the household  */
    public boolean isDefault;
	/**  Payment method profile identifier  */
    public int paymentMethodProfileId = Integer.MIN_VALUE;

    public KalturaHouseholdPaymentMethod() {
    }

    public KalturaHouseholdPaymentMethod(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("paymentGatewayId")) {
                this.paymentGatewayId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("details")) {
                this.details = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("isDefault")) {
                this.isDefault = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("paymentMethodProfileId")) {
                this.paymentMethodProfileId = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaHouseholdPaymentMethod");
        kparams.add("externalId", this.externalId);
        kparams.add("paymentGatewayId", this.paymentGatewayId);
        kparams.add("details", this.details);
        kparams.add("paymentMethodProfileId", this.paymentMethodProfileId);
        return kparams;
    }

}

