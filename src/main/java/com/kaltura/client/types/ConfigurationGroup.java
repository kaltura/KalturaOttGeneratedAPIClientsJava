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

import com.kaltura.client.Params;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.types.ObjectBase;
import java.util.List;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class ConfigurationGroup extends ObjectBase {

	/**  Configuration group identifier  */
    private String id;
	/**  Configuration group name  */
    private String name;
	/**  Partner id  */
    private Integer partnerId;
	/**  Is default  */
    private Boolean isDefault;
	/**  tags  */
    private List<StringValue> tags;
	/**  Number of devices  */
    private Long numberOfDevices;
	/**  Configuration identifiers  */
    private List<ConfigurationIdentifier> configurationIdentifiers;

    // id:
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }

    // name:
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    // partnerId:
    public Integer getPartnerId(){
        return this.partnerId;
    }
    public void setPartnerId(Integer partnerId){
        this.partnerId = partnerId;
    }

    // isDefault:
    public Boolean getIsDefault(){
        return this.isDefault;
    }
    public void setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }

    // tags:
    public List<StringValue> getTags(){
        return this.tags;
    }
    public void setTags(List<StringValue> tags){
        this.tags = tags;
    }

    // numberOfDevices:
    public Long getNumberOfDevices(){
        return this.numberOfDevices;
    }
    public void setNumberOfDevices(Long numberOfDevices){
        this.numberOfDevices = numberOfDevices;
    }

    // configurationIdentifiers:
    public List<ConfigurationIdentifier> getConfigurationIdentifiers(){
        return this.configurationIdentifiers;
    }
    public void setConfigurationIdentifiers(List<ConfigurationIdentifier> configurationIdentifiers){
        this.configurationIdentifiers = configurationIdentifiers;
    }


    public ConfigurationGroup() {
       super();
    }

    public ConfigurationGroup(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        id = GsonParser.parseString(jsonObject.get("id"));
        name = GsonParser.parseString(jsonObject.get("name"));
        partnerId = GsonParser.parseInt(jsonObject.get("partnerId"));
        isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));
        tags = GsonParser.parseArray(jsonObject.getAsJsonArray("tags"), StringValue.class);
        numberOfDevices = GsonParser.parseLong(jsonObject.get("numberOfDevices"));
        configurationIdentifiers = GsonParser.parseArray(jsonObject.getAsJsonArray("configurationIdentifiers"), ConfigurationIdentifier.class);

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaConfigurationGroup");
        kparams.add("name", this.name);
        kparams.add("isDefault", this.isDefault);
        return kparams;
    }

}

