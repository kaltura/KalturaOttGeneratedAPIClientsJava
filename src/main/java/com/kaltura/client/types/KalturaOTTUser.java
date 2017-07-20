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
import com.kaltura.client.enums.KalturaHouseholdSuspensionState;
import com.kaltura.client.enums.KalturaUserState;
import java.util.HashMap;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  User  */
@SuppressWarnings("serial")
public class KalturaOTTUser extends KalturaBaseOTTUser {
	/**  Household identifier  */
    public int householdId = Integer.MIN_VALUE;
	/**  Email  */
    public String email;
	/**  Address  */
    public String address;
	/**  City  */
    public String city;
	/**  Country identifier  */
    public int countryId = Integer.MIN_VALUE;
	/**  Zip code  */
    public String zip;
	/**  Phone  */
    public String phone;
	/**  Affiliate code  */
    public String affiliateCode;
	/**  External user identifier  */
    public String externalId;
	/**  User type  */
    public KalturaOTTUserType userType;
	/**  Dynamic data  */
    public HashMap<String, KalturaStringValue> dynamicData;
	/**  Is the user the household master  */
    public boolean isHouseholdMaster;
	/**  Suspension state  */
    public KalturaHouseholdSuspensionState suspensionState;
	/**  User state  */
    public KalturaUserState userState;

    public KalturaOTTUser() {
    }

    public KalturaOTTUser(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("householdId")) {
                this.householdId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("email")) {
                this.email = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("address")) {
                this.address = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("city")) {
                this.city = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("countryId")) {
                this.countryId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("zip")) {
                this.zip = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("phone")) {
                this.phone = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("affiliateCode")) {
                this.affiliateCode = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("userType")) {
                this.userType = ParseUtils.parseObject(KalturaOTTUserType.class, aNode);
                continue;
            } else if (nodeName.equals("dynamicData")) {
                this.dynamicData = ParseUtils.parseMap(KalturaStringValue.class, aNode);
                continue;
            } else if (nodeName.equals("isHouseholdMaster")) {
                this.isHouseholdMaster = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("suspensionState")) {
                this.suspensionState = KalturaHouseholdSuspensionState.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("userState")) {
                this.userState = KalturaUserState.get(ParseUtils.parseString(txt));
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaOTTUser");
        kparams.add("email", this.email);
        kparams.add("address", this.address);
        kparams.add("city", this.city);
        kparams.add("countryId", this.countryId);
        kparams.add("zip", this.zip);
        kparams.add("phone", this.phone);
        kparams.add("affiliateCode", this.affiliateCode);
        kparams.add("externalId", this.externalId);
        kparams.add("userType", this.userType);
        kparams.add("dynamicData", this.dynamicData);
        return kparams;
    }

}

