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
import com.kaltura.client.enums.KalturaCompensationType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Compensation request parameters  */
@SuppressWarnings("serial")
public class KalturaCompensation extends KalturaObjectBase {
	/**  Compensation identifier  */
    public long id = Long.MIN_VALUE;
	/**  Subscription identifier  */
    public long subscriptionId = Long.MIN_VALUE;
	/**  Compensation type  */
    public KalturaCompensationType compensationType;
	/**  Compensation amount  */
    public double amount = Double.MIN_VALUE;
	/**  The number of renewals for compensation  */
    public int totalRenewalIterations = Integer.MIN_VALUE;
	/**  The number of renewals the compensation was already applied on  */
    public int appliedRenewalIterations = Integer.MIN_VALUE;
	/**  Purchase identifier  */
    public int purchaseId = Integer.MIN_VALUE;

    public KalturaCompensation() {
    }

    public KalturaCompensation(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("subscriptionId")) {
                this.subscriptionId = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("compensationType")) {
                this.compensationType = KalturaCompensationType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("amount")) {
                this.amount = ParseUtils.parseDouble(txt);
                continue;
            } else if (nodeName.equals("totalRenewalIterations")) {
                this.totalRenewalIterations = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("appliedRenewalIterations")) {
                this.appliedRenewalIterations = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("purchaseId")) {
                this.purchaseId = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaCompensation");
        kparams.add("compensationType", this.compensationType);
        kparams.add("amount", this.amount);
        kparams.add("totalRenewalIterations", this.totalRenewalIterations);
        kparams.add("purchaseId", this.purchaseId);
        return kparams;
    }

}

