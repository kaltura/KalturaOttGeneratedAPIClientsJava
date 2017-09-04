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
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Language details  */
@SuppressWarnings("serial")
public class Language extends ObjectBase {

	/**  Language name  */
    private String name;
	/**  Language system name  */
    private String systemName;
	/**  Language code  */
    private String code;
	/**  Language direction (LTR/RTL)  */
    private String direction;
	/**  Is the default language of the account  */
    private Boolean isDefault;

    // name:
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    // systemName:
    public String getSystemName(){
        return this.systemName;
    }
    public void setSystemName(String systemName){
        this.systemName = systemName;
    }

    // code:
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    // direction:
    public String getDirection(){
        return this.direction;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }

    // isDefault:
    public Boolean getIsDefault(){
        return this.isDefault;
    }
    public void setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }


    public Language() {
       super();
    }

    public Language(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        name = GsonParser.parseString(jsonObject.get("name"));
        systemName = GsonParser.parseString(jsonObject.get("systemName"));
        code = GsonParser.parseString(jsonObject.get("code"));
        direction = GsonParser.parseString(jsonObject.get("direction"));
        isDefault = GsonParser.parseBoolean(jsonObject.get("isDefault"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaLanguage");
        kparams.add("name", this.name);
        kparams.add("systemName", this.systemName);
        kparams.add("code", this.code);
        kparams.add("direction", this.direction);
        kparams.add("isDefault", this.isDefault);
        return kparams;
    }

}

