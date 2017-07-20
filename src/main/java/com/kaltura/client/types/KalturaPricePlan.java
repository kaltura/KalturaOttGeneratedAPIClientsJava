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

/**  Price plan  */
@SuppressWarnings("serial")
public class KalturaPricePlan extends KalturaUsageModule {
	/**  Denotes whether or not this object can be renewed  */
    public boolean isRenewable;
	/**  Defines the number of times the module will be renewed (for the life_cycle
	  period)  */
    public int renewalsNumber = Integer.MIN_VALUE;
	/**  The discount module identifier of the price plan  */
    public long discountId = Long.MIN_VALUE;
	/**  The ID of the price details associated with this price plan  */
    public long priceDetailsId = Long.MIN_VALUE;

    public KalturaPricePlan() {
    }

    public KalturaPricePlan(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("isRenewable")) {
                this.isRenewable = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("renewalsNumber")) {
                this.renewalsNumber = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("discountId")) {
                this.discountId = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("priceDetailsId")) {
                this.priceDetailsId = ParseUtils.parseBigint(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaPricePlan");
        kparams.add("priceDetailsId", this.priceDetailsId);
        return kparams;
    }

}

