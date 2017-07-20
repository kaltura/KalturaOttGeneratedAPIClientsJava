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
import com.kaltura.client.enums.KalturaParentalRuleType;
import com.kaltura.client.enums.KalturaRuleLevel;
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

/**  Parental rule  */
@SuppressWarnings("serial")
public class KalturaParentalRule extends KalturaObjectBase {
	/**  Unique parental rule identifier  */
    public long id = Long.MIN_VALUE;
	/**  Rule display name  */
    public String name;
	/**  Explanatory description  */
    public String description;
	/**  Rule order within the full list of rules  */
    public int order = Integer.MIN_VALUE;
	/**  Media asset tag ID to in which to look for corresponding trigger values  */
    public int mediaTag = Integer.MIN_VALUE;
	/**  EPG asset tag ID to in which to look for corresponding trigger values  */
    public int epgTag = Integer.MIN_VALUE;
	/**  Content that correspond to this rule is not available for guests  */
    public boolean blockAnonymousAccess;
	/**  Rule type – Movies, TV series or both  */
    public KalturaParentalRuleType ruleType;
	/**  Media tag values that trigger rule  */
    public ArrayList<KalturaStringValue> mediaTagValues;
	/**  EPG tag values that trigger rule  */
    public ArrayList<KalturaStringValue> epgTagValues;
	/**  Is the rule the default rule of the account  */
    public boolean isDefault;
	/**  Where was this rule defined account, household or user  */
    public KalturaRuleLevel origin;

    public KalturaParentalRule() {
    }

    public KalturaParentalRule(Element node) throws KalturaApiException {
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
            } else if (nodeName.equals("order")) {
                this.order = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("mediaTag")) {
                this.mediaTag = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("epgTag")) {
                this.epgTag = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("blockAnonymousAccess")) {
                this.blockAnonymousAccess = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("ruleType")) {
                this.ruleType = KalturaParentalRuleType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("mediaTagValues")) {
                this.mediaTagValues = ParseUtils.parseArray(KalturaStringValue.class, aNode);
                continue;
            } else if (nodeName.equals("epgTagValues")) {
                this.epgTagValues = ParseUtils.parseArray(KalturaStringValue.class, aNode);
                continue;
            } else if (nodeName.equals("isDefault")) {
                this.isDefault = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("origin")) {
                this.origin = KalturaRuleLevel.get(ParseUtils.parseString(txt));
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaParentalRule");
        kparams.add("name", this.name);
        kparams.add("description", this.description);
        kparams.add("order", this.order);
        kparams.add("mediaTag", this.mediaTag);
        kparams.add("epgTag", this.epgTag);
        kparams.add("blockAnonymousAccess", this.blockAnonymousAccess);
        kparams.add("ruleType", this.ruleType);
        kparams.add("mediaTagValues", this.mediaTagValues);
        kparams.add("epgTagValues", this.epgTagValues);
        kparams.add("isDefault", this.isDefault);
        kparams.add("origin", this.origin);
        return kparams;
    }

}

