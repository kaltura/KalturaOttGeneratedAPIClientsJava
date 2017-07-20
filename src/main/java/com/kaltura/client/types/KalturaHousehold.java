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
import com.kaltura.client.enums.KalturaHouseholdState;
import com.kaltura.client.enums.KalturaHouseholdRestriction;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Household details  */
@SuppressWarnings("serial")
public class KalturaHousehold extends KalturaObjectBase {
	/**  Household identifier  */
    public long id = Long.MIN_VALUE;
	/**  Household name  */
    public String name;
	/**  Household description  */
    public String description;
	/**  Household external identifier  */
    public String externalId;
	/**  Household limitation module identifier  */
    public int householdLimitationsId = Integer.MIN_VALUE;
	/**  The max number of the devices that can be added to the household  */
    public int devicesLimit = Integer.MIN_VALUE;
	/**  The max number of the users that can be added to the household  */
    public int usersLimit = Integer.MIN_VALUE;
	/**  The max number of concurrent streams in the household  */
    public int concurrentLimit = Integer.MIN_VALUE;
	/**  The households region identifier  */
    public int regionId = Integer.MIN_VALUE;
	/**  Household state  */
    public KalturaHouseholdState state;
	/**  Is household frequency enabled  */
    public boolean isFrequencyEnabled;
	/**  The next time a device is allowed to be removed from the household (epoch)  */
    public long frequencyNextDeviceAction = Long.MIN_VALUE;
	/**  The next time a user is allowed to be removed from the household (epoch)  */
    public long frequencyNextUserAction = Long.MIN_VALUE;
	/**  Household restriction  */
    public KalturaHouseholdRestriction restriction;

    public KalturaHousehold() {
    }

    public KalturaHousehold(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("description")) {
                this.description = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("householdLimitationsId")) {
                this.householdLimitationsId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("devicesLimit")) {
                this.devicesLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("usersLimit")) {
                this.usersLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("concurrentLimit")) {
                this.concurrentLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("regionId")) {
                this.regionId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("state")) {
                this.state = KalturaHouseholdState.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("isFrequencyEnabled")) {
                this.isFrequencyEnabled = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("frequencyNextDeviceAction")) {
                this.frequencyNextDeviceAction = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("frequencyNextUserAction")) {
                this.frequencyNextUserAction = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("restriction")) {
                this.restriction = KalturaHouseholdRestriction.get(ParseUtils.parseString(txt));
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaHousehold");
        kparams.add("name", this.name);
        kparams.add("description", this.description);
        kparams.add("externalId", this.externalId);
        return kparams;
    }

}

