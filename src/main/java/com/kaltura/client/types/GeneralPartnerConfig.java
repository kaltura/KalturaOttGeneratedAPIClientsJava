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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.enums.DeleteMediaPolicy;
import com.kaltura.client.enums.DowngradePolicy;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Partner General configuration
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(GeneralPartnerConfig.Tokenizer.class)
public class GeneralPartnerConfig extends PartnerConfiguration {
	
	public interface Tokenizer extends PartnerConfiguration.Tokenizer {
		String partnerName();
		String mainLanguage();
		String secondaryLanguages();
		String deleteMediaPolicy();
		String mainCurrency();
		String secondaryCurrencies();
		String downgradePolicy();
		String mailSettings();
		String dateFormat();
		String householdLimitationModule();
	}

	/**
	 * Partner name
	 */
	private String partnerName;
	/**
	 * Main metadata language
	 */
	private Integer mainLanguage;
	/**
	 * A list of comma separated languages ids.
	 */
	private String secondaryLanguages;
	/**
	 * Delete media policy
	 */
	private DeleteMediaPolicy deleteMediaPolicy;
	/**
	 * Main currency
	 */
	private Integer mainCurrency;
	/**
	 * A list of comma separated currency ids.
	 */
	private String secondaryCurrencies;
	/**
	 * Downgrade policy
	 */
	private DowngradePolicy downgradePolicy;
	/**
	 * Mail settings
	 */
	private String mailSettings;
	/**
	 * Default Date Format for Email notifications (default should be: DD Month YYYY)
	 */
	private String dateFormat;
	/**
	 * Household limitation&amp;#160;module
	 */
	private Integer householdLimitationModule;

	// partnerName:
	public String getPartnerName(){
		return this.partnerName;
	}
	public void setPartnerName(String partnerName){
		this.partnerName = partnerName;
	}

	public void partnerName(String multirequestToken){
		setToken("partnerName", multirequestToken);
	}

	// mainLanguage:
	public Integer getMainLanguage(){
		return this.mainLanguage;
	}
	public void setMainLanguage(Integer mainLanguage){
		this.mainLanguage = mainLanguage;
	}

	public void mainLanguage(String multirequestToken){
		setToken("mainLanguage", multirequestToken);
	}

	// secondaryLanguages:
	public String getSecondaryLanguages(){
		return this.secondaryLanguages;
	}
	public void setSecondaryLanguages(String secondaryLanguages){
		this.secondaryLanguages = secondaryLanguages;
	}

	public void secondaryLanguages(String multirequestToken){
		setToken("secondaryLanguages", multirequestToken);
	}

	// deleteMediaPolicy:
	public DeleteMediaPolicy getDeleteMediaPolicy(){
		return this.deleteMediaPolicy;
	}
	public void setDeleteMediaPolicy(DeleteMediaPolicy deleteMediaPolicy){
		this.deleteMediaPolicy = deleteMediaPolicy;
	}

	public void deleteMediaPolicy(String multirequestToken){
		setToken("deleteMediaPolicy", multirequestToken);
	}

	// mainCurrency:
	public Integer getMainCurrency(){
		return this.mainCurrency;
	}
	public void setMainCurrency(Integer mainCurrency){
		this.mainCurrency = mainCurrency;
	}

	public void mainCurrency(String multirequestToken){
		setToken("mainCurrency", multirequestToken);
	}

	// secondaryCurrencies:
	public String getSecondaryCurrencies(){
		return this.secondaryCurrencies;
	}
	public void setSecondaryCurrencies(String secondaryCurrencies){
		this.secondaryCurrencies = secondaryCurrencies;
	}

	public void secondaryCurrencies(String multirequestToken){
		setToken("secondaryCurrencies", multirequestToken);
	}

	// downgradePolicy:
	public DowngradePolicy getDowngradePolicy(){
		return this.downgradePolicy;
	}
	public void setDowngradePolicy(DowngradePolicy downgradePolicy){
		this.downgradePolicy = downgradePolicy;
	}

	public void downgradePolicy(String multirequestToken){
		setToken("downgradePolicy", multirequestToken);
	}

	// mailSettings:
	public String getMailSettings(){
		return this.mailSettings;
	}
	public void setMailSettings(String mailSettings){
		this.mailSettings = mailSettings;
	}

	public void mailSettings(String multirequestToken){
		setToken("mailSettings", multirequestToken);
	}

	// dateFormat:
	public String getDateFormat(){
		return this.dateFormat;
	}
	public void setDateFormat(String dateFormat){
		this.dateFormat = dateFormat;
	}

	public void dateFormat(String multirequestToken){
		setToken("dateFormat", multirequestToken);
	}

	// householdLimitationModule:
	public Integer getHouseholdLimitationModule(){
		return this.householdLimitationModule;
	}
	public void setHouseholdLimitationModule(Integer householdLimitationModule){
		this.householdLimitationModule = householdLimitationModule;
	}

	public void householdLimitationModule(String multirequestToken){
		setToken("householdLimitationModule", multirequestToken);
	}


	public GeneralPartnerConfig() {
		super();
	}

	public GeneralPartnerConfig(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		partnerName = GsonParser.parseString(jsonObject.get("partnerName"));
		mainLanguage = GsonParser.parseInt(jsonObject.get("mainLanguage"));
		secondaryLanguages = GsonParser.parseString(jsonObject.get("secondaryLanguages"));
		deleteMediaPolicy = DeleteMediaPolicy.get(GsonParser.parseString(jsonObject.get("deleteMediaPolicy")));
		mainCurrency = GsonParser.parseInt(jsonObject.get("mainCurrency"));
		secondaryCurrencies = GsonParser.parseString(jsonObject.get("secondaryCurrencies"));
		downgradePolicy = DowngradePolicy.get(GsonParser.parseString(jsonObject.get("downgradePolicy")));
		mailSettings = GsonParser.parseString(jsonObject.get("mailSettings"));
		dateFormat = GsonParser.parseString(jsonObject.get("dateFormat"));
		householdLimitationModule = GsonParser.parseInt(jsonObject.get("householdLimitationModule"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaGeneralPartnerConfig");
		kparams.add("partnerName", this.partnerName);
		kparams.add("mainLanguage", this.mainLanguage);
		kparams.add("secondaryLanguages", this.secondaryLanguages);
		kparams.add("deleteMediaPolicy", this.deleteMediaPolicy);
		kparams.add("mainCurrency", this.mainCurrency);
		kparams.add("secondaryCurrencies", this.secondaryCurrencies);
		kparams.add("downgradePolicy", this.downgradePolicy);
		kparams.add("mailSettings", this.mailSettings);
		kparams.add("dateFormat", this.dateFormat);
		kparams.add("householdLimitationModule", this.householdLimitationModule);
		return kparams;
	}

}
