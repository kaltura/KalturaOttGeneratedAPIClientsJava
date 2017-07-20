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

/**  OSS Adapter  */
@SuppressWarnings("serial")
public class KalturaOSSAdapterProfile extends KalturaOSSAdapterBaseProfile {
	/**  OSS adapter active status  */
    public boolean isActive;
	/**  OSS adapter adapter URL  */
    public String adapterUrl;
	/**  OSS adapter extra parameters  */
    public HashMap<String, KalturaStringValue> ossAdapterSettings;
	/**  OSS adapter external identifier  */
    public String externalIdentifier;
	/**  Shared Secret  */
    public String sharedSecret;

    public KalturaOSSAdapterProfile() {
    }

    public KalturaOSSAdapterProfile(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("isActive")) {
                this.isActive = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("adapterUrl")) {
                this.adapterUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("ossAdapterSettings")) {
                this.ossAdapterSettings = ParseUtils.parseMap(KalturaStringValue.class, aNode);
                continue;
            } else if (nodeName.equals("externalIdentifier")) {
                this.externalIdentifier = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("sharedSecret")) {
                this.sharedSecret = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaOSSAdapterProfile");
        kparams.add("isActive", this.isActive);
        kparams.add("adapterUrl", this.adapterUrl);
        kparams.add("ossAdapterSettings", this.ossAdapterSettings);
        kparams.add("externalIdentifier", this.externalIdentifier);
        return kparams;
    }

}

