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
import com.kaltura.client.enums.KalturaMetaFieldName;
import com.kaltura.client.enums.KalturaMetaType;
import com.kaltura.client.enums.KalturaAssetType;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Asset meta  */
@SuppressWarnings("serial")
public class KalturaMeta extends KalturaObjectBase {
	/**  Meta name for the partner  */
    public String name;
	/**  Meta system field name  */
    public KalturaMetaFieldName fieldName;
	/**  Meta value type  */
    public KalturaMetaType type;
	/**  Asset type this meta is related to  */
    public KalturaAssetType assetType;
	/**  List of supported features  */
    public String features;
	/**  Meta id  */
    public String id;
	/**  Parent meta id  */
    public String parentId;
	/**  Partner Id  */
    public int partnerId = Integer.MIN_VALUE;

    public KalturaMeta() {
    }

    public KalturaMeta(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("fieldName")) {
                this.fieldName = KalturaMetaFieldName.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("type")) {
                this.type = KalturaMetaType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("assetType")) {
                this.assetType = KalturaAssetType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("features")) {
                this.features = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("id")) {
                this.id = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("parentId")) {
                this.parentId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("partnerId")) {
                this.partnerId = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaMeta");
        kparams.add("name", this.name);
        kparams.add("fieldName", this.fieldName);
        kparams.add("type", this.type);
        kparams.add("assetType", this.assetType);
        kparams.add("features", this.features);
        kparams.add("id", this.id);
        kparams.add("parentId", this.parentId);
        kparams.add("partnerId", this.partnerId);
        return kparams;
    }

}

