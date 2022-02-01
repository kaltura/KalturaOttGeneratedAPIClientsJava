// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platforms allow them to do with
// text.
//
// Copyright (C) 2006-2021  Kaltura Inc.
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
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Program asset group offer details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ProgramAssetGroupOffer.Tokenizer.class)
public class ProgramAssetGroupOffer extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String id();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String priceDetailsId();
		String fileTypesIds();
		String discountModuleId();
		String couponsGroupId();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		String virtualAssetId();
		String isActive();
		String createDate();
		String updateDate();
		String startDate();
		String endDate();
		String expiryDate();
		String externalId();
		String externalOfferId();
	}

	/**
	 * Unique Kaltura internal identifier for the module
	 */
	private Long id;
	/**
	 * Name of the Program asset group offer
	 */
	private String name;
	/**
	 * Name of the Program asset group offer
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * ID of the KalturaPriceDetails object which contains details of the price to be
	  paid for purchasing this KalturaProgramAssetGroupOffer.
	 */
	private Long priceDetailsId;
	/**
	 * Comma separated file types identifiers that are supported in this Program asset
	  group offer.              The subset of KalturaMediaFiles of the live linear
	  channel on which the associated Program Assets are carried to which households
	  entitled to this              Program Asset Group Offer are entitled to view
	  E.g.may be used to restrict entitlement only to HD flavour of the Program
	  Asset(and not the UHD flavour)              If this parameter is empty, the
	  Household shall be entitled to all KalturaMediaFiles associated with the
	  KalturaLiveAsset.
	 */
	private String fileTypesIds;
	/**
	 * The internal discount module identifier for the Program asset group offer
	 */
	private Long discountModuleId;
	/**
	 * Coupons group id for the Program asset group offer
	 */
	private Long couponsGroupId;
	/**
	 * A list of the descriptions of the Program asset group offer on different
	  languages (language code and translation)
	 */
	private String description;
	/**
	 * A list of the descriptions of the Program asset group offer on different
	  languages (language code and translation)
	 */
	private List<TranslationToken> multilingualDescription;
	/**
	 * The id of the paired asset
	 */
	private Long virtualAssetId;
	/**
	 * Indicates whether the PAGO is active or not (includes whether the PAGO can be
	  purchased and whether it is returned in list API response for regular users)
	 */
	private Boolean isActive;
	/**
	 * Specifies when was the pago created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the pago last updated. Date and time represented as epoch.
	 */
	private Long updateDate;
	/**
	 * he date/time at which the Program Asset Group Offer is first purchasable by
	  households. Date and time represented as epoch.
	 */
	private Long startDate;
	/**
	 * The date/time at which the Program Asset Group Offer is last purchasable by
	  households.Date and time represented as epoch.
	 */
	private Long endDate;
	/**
	 * The last date/time at which the system will attempt to locate Program Assets
	  that may be associated with this offer.Date and time represented as epoch.
	 */
	private Long expiryDate;
	/**
	 * External identifier
	 */
	private String externalId;
	/**
	 * Identifies the Program Assets which will be entitled by Households that purchase
	  this offer. Must be a unique value in the context of an account.
	 */
	private String externalOfferId;

	// id:
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
	}

	// name:
	public String getName(){
		return this.name;
	}
	// multilingualName:
	public List<TranslationToken> getMultilingualName(){
		return this.multilingualName;
	}
	public void setMultilingualName(List<TranslationToken> multilingualName){
		this.multilingualName = multilingualName;
	}

	// priceDetailsId:
	public Long getPriceDetailsId(){
		return this.priceDetailsId;
	}
	public void setPriceDetailsId(Long priceDetailsId){
		this.priceDetailsId = priceDetailsId;
	}

	public void priceDetailsId(String multirequestToken){
		setToken("priceDetailsId", multirequestToken);
	}

	// fileTypesIds:
	public String getFileTypesIds(){
		return this.fileTypesIds;
	}
	public void setFileTypesIds(String fileTypesIds){
		this.fileTypesIds = fileTypesIds;
	}

	public void fileTypesIds(String multirequestToken){
		setToken("fileTypesIds", multirequestToken);
	}

	// discountModuleId:
	public Long getDiscountModuleId(){
		return this.discountModuleId;
	}
	public void setDiscountModuleId(Long discountModuleId){
		this.discountModuleId = discountModuleId;
	}

	public void discountModuleId(String multirequestToken){
		setToken("discountModuleId", multirequestToken);
	}

	// couponsGroupId:
	public Long getCouponsGroupId(){
		return this.couponsGroupId;
	}
	public void setCouponsGroupId(Long couponsGroupId){
		this.couponsGroupId = couponsGroupId;
	}

	public void couponsGroupId(String multirequestToken){
		setToken("couponsGroupId", multirequestToken);
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	// multilingualDescription:
	public List<TranslationToken> getMultilingualDescription(){
		return this.multilingualDescription;
	}
	public void setMultilingualDescription(List<TranslationToken> multilingualDescription){
		this.multilingualDescription = multilingualDescription;
	}

	// virtualAssetId:
	public Long getVirtualAssetId(){
		return this.virtualAssetId;
	}
	// isActive:
	public Boolean getIsActive(){
		return this.isActive;
	}
	public void setIsActive(Boolean isActive){
		this.isActive = isActive;
	}

	public void isActive(String multirequestToken){
		setToken("isActive", multirequestToken);
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// startDate:
	public Long getStartDate(){
		return this.startDate;
	}
	public void setStartDate(Long startDate){
		this.startDate = startDate;
	}

	public void startDate(String multirequestToken){
		setToken("startDate", multirequestToken);
	}

	// endDate:
	public Long getEndDate(){
		return this.endDate;
	}
	public void setEndDate(Long endDate){
		this.endDate = endDate;
	}

	public void endDate(String multirequestToken){
		setToken("endDate", multirequestToken);
	}

	// expiryDate:
	public Long getExpiryDate(){
		return this.expiryDate;
	}
	public void setExpiryDate(Long expiryDate){
		this.expiryDate = expiryDate;
	}

	public void expiryDate(String multirequestToken){
		setToken("expiryDate", multirequestToken);
	}

	// externalId:
	public String getExternalId(){
		return this.externalId;
	}
	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public void externalId(String multirequestToken){
		setToken("externalId", multirequestToken);
	}

	// externalOfferId:
	public String getExternalOfferId(){
		return this.externalOfferId;
	}
	public void setExternalOfferId(String externalOfferId){
		this.externalOfferId = externalOfferId;
	}

	public void externalOfferId(String multirequestToken){
		setToken("externalOfferId", multirequestToken);
	}


	public ProgramAssetGroupOffer() {
		super();
	}

	public ProgramAssetGroupOffer(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		priceDetailsId = GsonParser.parseLong(jsonObject.get("priceDetailsId"));
		fileTypesIds = GsonParser.parseString(jsonObject.get("fileTypesIds"));
		discountModuleId = GsonParser.parseLong(jsonObject.get("discountModuleId"));
		couponsGroupId = GsonParser.parseLong(jsonObject.get("couponsGroupId"));
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		virtualAssetId = GsonParser.parseLong(jsonObject.get("virtualAssetId"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		expiryDate = GsonParser.parseLong(jsonObject.get("expiryDate"));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		externalOfferId = GsonParser.parseString(jsonObject.get("externalOfferId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaProgramAssetGroupOffer");
		kparams.add("id", this.id);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("priceDetailsId", this.priceDetailsId);
		kparams.add("fileTypesIds", this.fileTypesIds);
		kparams.add("discountModuleId", this.discountModuleId);
		kparams.add("couponsGroupId", this.couponsGroupId);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("isActive", this.isActive);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("expiryDate", this.expiryDate);
		kparams.add("externalId", this.externalId);
		kparams.add("externalOfferId", this.externalOfferId);
		return kparams;
	}

}

