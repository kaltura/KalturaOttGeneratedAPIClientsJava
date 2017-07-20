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
import com.kaltura.client.enums.KalturaExportDataType;
import com.kaltura.client.enums.KalturaExportType;
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

/**  Bulk export task  */
@SuppressWarnings("serial")
public class KalturaExportTask extends KalturaObjectBase {
	/**  Task identifier  */
    public long id = Long.MIN_VALUE;
	/**  Alias for the task used to solicit an export using API  */
    public String alias;
	/**  Task display name  */
    public String name;
	/**  The data type exported in this task  */
    public KalturaExportDataType dataType;
	/**  Filter to apply on the export, utilize KSQL.              Note: KSQL currently
	  applies to media assets only. It cannot be used for USERS filtering  */
    public String filter;
	/**  Type of export batch – full or incremental  */
    public KalturaExportType exportType;
	/**  How often the export should occur, reasonable minimum threshold should apply,
	  configurable in minutes  */
    public long frequency = Long.MIN_VALUE;
	/**  The URL for sending a notification when the task&amp;#39;s export process is
	  done  */
    public String notificationUrl;
	/**  List of media type identifiers (as configured in TVM) to export. used only in
	  case data_type = vod  */
    public ArrayList<KalturaIntegerValue> vodTypes;
	/**  Indicates if the task is active or not  */
    public boolean isActive;

    public KalturaExportTask() {
    }

    public KalturaExportTask(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("alias")) {
                this.alias = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("name")) {
                this.name = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("dataType")) {
                this.dataType = KalturaExportDataType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("filter")) {
                this.filter = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("exportType")) {
                this.exportType = KalturaExportType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("frequency")) {
                this.frequency = ParseUtils.parseBigint(txt);
                continue;
            } else if (nodeName.equals("notificationUrl")) {
                this.notificationUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("vodTypes")) {
                this.vodTypes = ParseUtils.parseArray(KalturaIntegerValue.class, aNode);
                continue;
            } else if (nodeName.equals("isActive")) {
                this.isActive = ParseUtils.parseBool(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() throws KalturaApiException {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaExportTask");
        kparams.add("alias", this.alias);
        kparams.add("name", this.name);
        kparams.add("dataType", this.dataType);
        kparams.add("filter", this.filter);
        kparams.add("exportType", this.exportType);
        kparams.add("frequency", this.frequency);
        kparams.add("notificationUrl", this.notificationUrl);
        kparams.add("vodTypes", this.vodTypes);
        kparams.add("isActive", this.isActive);
        return kparams;
    }

}

