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

/**  Asset info  */
@SuppressWarnings("serial")
public abstract class KalturaAsset extends KalturaObjectBase {
	/**  Unique identifier for the asset  */
    public long id = Long.MIN_VALUE;
	/**  Identifies the asset type (EPG, Recording, Movie, TV Series, etc).              
	  Possible values: 0 – EPG linear programs, 1 - Recording; or any asset type ID
	  according to the asset types IDs defined in the system.  */
    public int type = Integer.MIN_VALUE;
	/**  Asset name  */
    public String name;
	/**  Asset name  */
    public KalturaMultilingualString multilingualName;
	/**  Asset description  */
    public String description;
	/**  Asset description  */
    public KalturaMultilingualString multilingualDescription;
	/**  Collection of images details that can be used to represent this asset  */
    public ArrayList<KalturaMediaImage> images;
	/**  Files  */
    public ArrayList<KalturaMediaFile> mediaFiles;
	/**  Dynamic collection of key-value pairs according to the String Meta defined in
	  the system  */
    public HashMap<String, KalturaValue> metas;
	/**  Dynamic collection of key-value pairs according to the Tag Types defined in the
	  system  */
    public HashMap<String, KalturaMultilingualStringValueArray> tags;
	/**  Date and time represented as epoch. For VOD – since when the asset is
	  available in the catalog. For EPG/Linear – when the program is aired (can be
	  in the future).  */
    public long startDate = Long.MIN_VALUE;
	/**  Date and time represented as epoch. For VOD – till when the asset be available
	  in the catalog. For EPG/Linear – program end time and date  */
    public long endDate = Long.MIN_VALUE;
	/**  Enable cDVR  */
    public boolean enableCdvr;
	/**  Enable catch-up  */
    public boolean enableCatchUp;
	/**  Enable start over  */
    public boolean enableStartOver;
	/**  Enable trick-play  */
    public boolean enableTrickPlay;
	/**  External identifier for the media file  */
    public String externalId;

    public KalturaAsset() {
    }

    public KalturaAsset(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("type")) {
                this.type = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("multilingualName")) {
                this.multilingualName = ParseUtils.parseObject(KalturaMultilingualString.class, aNode);
                continue;
            } else if (nodeName.equals("description")) {
                this.description = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("multilingualDescription")) {
                this.multilingualDescription = ParseUtils.parseObject(KalturaMultilingualString.class, aNode);
                continue;
            } else if (nodeName.equals("images")) {
                this.images = ParseUtils.parseArray(KalturaMediaImage.class, aNode);
                continue;
            } else if (nodeName.equals("mediaFiles")) {
                this.mediaFiles = ParseUtils.parseArray(KalturaMediaFile.class, aNode);
                continue;
            } else if (nodeName.equals("metas")) {
                this.metas = ParseUtils.parseMap(KalturaValue.class, aNode);
                continue;
            } else if (nodeName.equals("tags")) {
                this.tags = ParseUtils.parseMap(KalturaMultilingualStringValueArray.class, aNode);
                continue;
            } else if (nodeName.equals("startDate")) {
                this.startDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("endDate")) {
                this.endDate = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("enableCdvr")) {
                this.enableCdvr = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("enableCatchUp")) {
                this.enableCatchUp = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("enableStartOver")) {
                this.enableStartOver = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("enableTrickPlay")) {
                this.enableTrickPlay = ParseUtils.parseBool(txt);
                continue;
            } else if (nodeName.equals("externalId")) {
                this.externalId = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaAsset");
        kparams.add("type", this.type);
        kparams.add("name", this.name);
        kparams.add("multilingualName", this.multilingualName);
        kparams.add("description", this.description);
        kparams.add("multilingualDescription", this.multilingualDescription);
        kparams.add("images", this.images);
        kparams.add("mediaFiles", this.mediaFiles);
        kparams.add("metas", this.metas);
        kparams.add("tags", this.tags);
        kparams.add("startDate", this.startDate);
        kparams.add("endDate", this.endDate);
        kparams.add("enableCdvr", this.enableCdvr);
        kparams.add("enableCatchUp", this.enableCatchUp);
        kparams.add("enableStartOver", this.enableStartOver);
        kparams.add("enableTrickPlay", this.enableTrickPlay);
        kparams.add("externalId", this.externalId);
        return kparams;
    }

}

