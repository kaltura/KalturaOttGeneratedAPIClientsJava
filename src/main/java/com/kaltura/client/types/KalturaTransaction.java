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
public class KalturaTransaction extends KalturaObjectBase {
	/**  Kaltura unique ID representing the transaction  */
    public String id;
	/**  Transaction reference ID received from the payment gateway.               Value
	  is available only if the payment gateway provides this information.  */
    public String paymentGatewayReferenceId;
	/**  Response ID received from by the payment gateway.               Value is
	  available only if the payment gateway provides this information.  */
    public String paymentGatewayResponseId;
	/**  Transaction state: OK/Pending/Failed  */
    public String state;
	/**  Adapter failure reason code              Insufficient funds = 20, Invalid
	  account = 21, User unknown = 22, Reason unknown = 23, Unknown payment gateway
	  response = 24,              No response from payment gateway = 25, Exceeded
	  retry limit = 26, Illegal client request = 27, Expired = 28  */
    public int failReasonCode = Integer.MIN_VALUE;
	/**  Entitlement creation date  */
    public int createdAt = Integer.MIN_VALUE;

    public KalturaTransaction() {
    }

    public KalturaTransaction(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("paymentGatewayReferenceId")) {
                this.paymentGatewayReferenceId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("paymentGatewayResponseId")) {
                this.paymentGatewayResponseId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("state")) {
                this.state = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("failReasonCode")) {
                this.failReasonCode = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("createdAt")) {
                this.createdAt = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaTransaction");
        kparams.add("id", this.id);
        kparams.add("paymentGatewayReferenceId", this.paymentGatewayReferenceId);
        kparams.add("paymentGatewayResponseId", this.paymentGatewayResponseId);
        kparams.add("state", this.state);
        kparams.add("failReasonCode", this.failReasonCode);
        kparams.add("createdAt", this.createdAt);
        return kparams;
    }

}

