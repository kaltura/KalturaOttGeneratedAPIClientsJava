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
import com.kaltura.client.enums.MetaFieldName;
import com.kaltura.client.enums.MetaType;
import com.kaltura.client.enums.AssetType;
import com.google.gson.JsonObject;


/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Meta filter  */
@SuppressWarnings("serial")
public class MetaFilter extends Filter {

	/**  Meta system field name to filter by  */
    private MetaFieldName fieldNameEqual;
	/**  Meta system field name to filter by  */
    private MetaFieldName fieldNameNotEqual;
	/**  Meta type to filter by  */
    private MetaType typeEqual;
	/**  Asset type to filter by  */
    private AssetType assetTypeEqual;
	/**  Features  */
    private String featuresIn;

    // fieldNameEqual:
    public MetaFieldName getFieldNameEqual(){
        return this.fieldNameEqual;
    }
    public void setFieldNameEqual(MetaFieldName fieldNameEqual){
        this.fieldNameEqual = fieldNameEqual;
    }

    // fieldNameNotEqual:
    public MetaFieldName getFieldNameNotEqual(){
        return this.fieldNameNotEqual;
    }
    public void setFieldNameNotEqual(MetaFieldName fieldNameNotEqual){
        this.fieldNameNotEqual = fieldNameNotEqual;
    }

    // typeEqual:
    public MetaType getTypeEqual(){
        return this.typeEqual;
    }
    public void setTypeEqual(MetaType typeEqual){
        this.typeEqual = typeEqual;
    }

    // assetTypeEqual:
    public AssetType getAssetTypeEqual(){
        return this.assetTypeEqual;
    }
    public void setAssetTypeEqual(AssetType assetTypeEqual){
        this.assetTypeEqual = assetTypeEqual;
    }

    // featuresIn:
    public String getFeaturesIn(){
        return this.featuresIn;
    }
    public void setFeaturesIn(String featuresIn){
        this.featuresIn = featuresIn;
    }


    public MetaFilter() {
       super();
    }

    public MetaFilter(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        fieldNameEqual = MetaFieldName.get(GsonParser.parseString(jsonObject.get("fieldNameEqual")));
        fieldNameNotEqual = MetaFieldName.get(GsonParser.parseString(jsonObject.get("fieldNameNotEqual")));
        typeEqual = MetaType.get(GsonParser.parseString(jsonObject.get("typeEqual")));
        assetTypeEqual = AssetType.get(GsonParser.parseString(jsonObject.get("assetTypeEqual")));
        featuresIn = GsonParser.parseString(jsonObject.get("featuresIn"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaMetaFilter");
        kparams.add("fieldNameEqual", this.fieldNameEqual);
        kparams.add("fieldNameNotEqual", this.fieldNameNotEqual);
        kparams.add("typeEqual", this.typeEqual);
        kparams.add("assetTypeEqual", this.assetTypeEqual);
        kparams.add("featuresIn", this.featuresIn);
        return kparams;
    }

}

