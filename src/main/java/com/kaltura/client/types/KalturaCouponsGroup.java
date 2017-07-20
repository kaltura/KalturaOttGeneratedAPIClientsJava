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
import com.kaltura.client.enums.KalturaCouponGroupType;
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

/**  Coupons group details  */
@SuppressWarnings("serial")
public class KalturaCouponsGroup extends KalturaObjectBase {
	/**  Coupon group identifier  */
    public String id;
	/**  Coupon group name  */
    public String name;
	/**  A list of the descriptions of the coupon group on different languages (language
	  code and translation)  */
    public ArrayList<KalturaTranslationToken> descriptions;
	/**  The first date the coupons in this coupons group are valid  */
    public long startDate = Long.MIN_VALUE;
	/**  The last date the coupons in this coupons group are valid  */
    public long endDate = Long.MIN_VALUE;
	/**  Maximum number of uses for each coupon in the group  */
    public int maxUsesNumber = Integer.MIN_VALUE;
	/**  Maximum number of uses for each coupon in the group on a renewable subscription  */
    public int maxUsesNumberOnRenewableSub = Integer.MIN_VALUE;
	/**  Type of the coupon group  */
    public KalturaCouponGroupType couponGroupType;

    public KalturaCouponsGroup() {
    }

    public KalturaCouponsGroup(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("descriptions")) {
                this.descriptions = ParseUtils.parseArray(KalturaTranslationToken.class, aNode);
                continue;
            } else if (nodeName.equals("startDate")) {
                this.startDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("maxUsesNumber")) {
                this.maxUsesNumber = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("maxUsesNumberOnRenewableSub")) {
                this.maxUsesNumberOnRenewableSub = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("couponGroupType")) {
                this.couponGroupType = KalturaCouponGroupType.get(ParseUtils.parseString(txt));
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaCouponsGroup");
        kparams.add("name", this.name);
        kparams.add("descriptions", this.descriptions);
        kparams.add("startDate", this.startDate);
        kparams.add("endDate", this.endDate);
        kparams.add("maxUsesNumber", this.maxUsesNumber);
        kparams.add("maxUsesNumberOnRenewableSub", this.maxUsesNumberOnRenewableSub);
        kparams.add("couponGroupType", this.couponGroupType);
        return kparams;
    }

}

