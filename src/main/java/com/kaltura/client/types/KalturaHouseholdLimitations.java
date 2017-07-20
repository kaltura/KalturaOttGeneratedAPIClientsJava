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

/**  Household limitations details  */
@SuppressWarnings("serial")
public class KalturaHouseholdLimitations extends KalturaObjectBase {
	/**  Household limitation module identifier  */
    public int id = Integer.MIN_VALUE;
	/**  Household limitation module name  */
    public String name;
	/**  Max number of streams allowed for the household  */
    public int concurrentLimit = Integer.MIN_VALUE;
	/**  Max number of devices allowed for the household  */
    public int deviceLimit = Integer.MIN_VALUE;
	/**  Allowed device change frequency code  */
    public int deviceFrequency = Integer.MIN_VALUE;
	/**  Allowed device change frequency description  */
    public String deviceFrequencyDescription;
	/**  Allowed user change frequency code  */
    public int userFrequency = Integer.MIN_VALUE;
	/**  Allowed user change frequency description  */
    public String userFrequencyDescription;
	/**  Allowed NPVR Quota in Seconds  */
    public int npvrQuotaInSeconds = Integer.MIN_VALUE;
	/**  Max number of users allowed for the household  */
    public int usersLimit = Integer.MIN_VALUE;
	/**  Device families limitations  */
    public ArrayList<KalturaHouseholdDeviceFamilyLimitations> deviceFamiliesLimitations;

    public KalturaHouseholdLimitations() {
    }

    public KalturaHouseholdLimitations(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("concurrentLimit")) {
                this.concurrentLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("deviceLimit")) {
                this.deviceLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("deviceFrequency")) {
                this.deviceFrequency = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("deviceFrequencyDescription")) {
                this.deviceFrequencyDescription = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("userFrequency")) {
                this.userFrequency = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("userFrequencyDescription")) {
                this.userFrequencyDescription = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("npvrQuotaInSeconds")) {
                this.npvrQuotaInSeconds = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("usersLimit")) {
                this.usersLimit = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("deviceFamiliesLimitations")) {
                this.deviceFamiliesLimitations = ParseUtils.parseArray(KalturaHouseholdDeviceFamilyLimitations.class, aNode);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaHouseholdLimitations");
        return kparams;
    }

}

