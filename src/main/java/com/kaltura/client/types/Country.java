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

/**  Country details  */
@SuppressWarnings("serial")
public class Country extends ObjectBase {

	/**  Country identifier  */
    private Integer id;
	/**  Country name  */
    private String name;
	/**  Country code  */
    private String code;
	/**  The main language code in the country  */
    private String mainLanguageCode;
	/**  All the languages code that are supported in the country  */
    private String languagesCode;
	/**  Currency code  */
    private String currency;
	/**  Currency Sign  */
    private String currencySign;
	/**  Vat Percent in the country  */
    private Double vatPercent;

    // id:
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    // name:
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    // code:
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    // mainLanguageCode:
    public String getMainLanguageCode(){
        return this.mainLanguageCode;
    }
    public void setMainLanguageCode(String mainLanguageCode){
        this.mainLanguageCode = mainLanguageCode;
    }

    // languagesCode:
    public String getLanguagesCode(){
        return this.languagesCode;
    }
    public void setLanguagesCode(String languagesCode){
        this.languagesCode = languagesCode;
    }

    // currency:
    public String getCurrency(){
        return this.currency;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }

    // currencySign:
    public String getCurrencySign(){
        return this.currencySign;
    }
    public void setCurrencySign(String currencySign){
        this.currencySign = currencySign;
    }

    // vatPercent:
    public Double getVatPercent(){
        return this.vatPercent;
    }
    public void setVatPercent(Double vatPercent){
        this.vatPercent = vatPercent;
    }


    public Country() {
       super();
    }

    public Country(JsonObject jsonObject) throws APIException {
        super(jsonObject);

        if(jsonObject == null) return;

        // set members values:
        id = GsonParser.parseInt(jsonObject.get("id"));
        name = GsonParser.parseString(jsonObject.get("name"));
        code = GsonParser.parseString(jsonObject.get("code"));
        mainLanguageCode = GsonParser.parseString(jsonObject.get("mainLanguageCode"));
        languagesCode = GsonParser.parseString(jsonObject.get("languagesCode"));
        currency = GsonParser.parseString(jsonObject.get("currency"));
        currencySign = GsonParser.parseString(jsonObject.get("currencySign"));
        vatPercent = GsonParser.parseDouble(jsonObject.get("vatPercent"));

    }

    public Params toParams() {
        Params kparams = super.toParams();
        kparams.add("objectType", "KalturaCountry");
        kparams.add("name", this.name);
        kparams.add("code", this.code);
        kparams.add("mainLanguageCode", this.mainLanguageCode);
        kparams.add("languagesCode", this.languagesCode);
        kparams.add("currency", this.currency);
        kparams.add("currencySign", this.currencySign);
        kparams.add("vatPercent", this.vatPercent);
        return kparams;
    }

}

