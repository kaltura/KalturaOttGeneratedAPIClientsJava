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
import com.kaltura.client.enums.AdsPolicy;
import com.kaltura.client.enums.SubscriptionDependencyType;
import com.kaltura.client.types.ObjectBase;
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
 * Subscription
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SubscriptionInternal.Tokenizer.class)
public class SubscriptionInternal extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String channelsIds();
		String startDate();
		String endDate();
		String fileTypesIds();
		String internalDiscountModuleId();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		String prorityInOrder();
		String pricePlanIds();
		String previewModuleId();
		String householdLimitationsId();
		String gracePeriodMinutes();
		RequestBuilder.ListTokenizer<PremiumService.Tokenizer> premiumServices();
		RequestBuilder.ListTokenizer<SubscriptionCouponGroup.Tokenizer> couponsGroups();
		RequestBuilder.ListTokenizer<ProductCode.Tokenizer> productCodes();
		String dependencyType();
		String externalId();
		String isCancellationBlocked();
		String preSaleDate();
		String adsPolicy();
		String adsParam();
		String isActive();
	}

	/**
	 * Comma separated channels Ids associated with this subscription
	 */
	private String channelsIds;
	/**
	 * The first date the subscription is available for purchasing
	 */
	private Long startDate;
	/**
	 * The last date the subscription is available for purchasing
	 */
	private Long endDate;
	/**
	 * Comma separated file types identifiers that are supported in this subscription
	 */
	private String fileTypesIds;
	/**
	 * The internal discount module identifier for the subscription
	 */
	private Long internalDiscountModuleId;
	/**
	 * Name of the subscription
	 */
	private String name;
	/**
	 * Name of the subscription
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * description of the subscription
	 */
	private String description;
	/**
	 * description of the subscription
	 */
	private List<TranslationToken> multilingualDescription;
	/**
	 * Subscription order (when returned in methods that retrieve subscriptions)
	 */
	private Long prorityInOrder;
	/**
	 * Comma separated subscription price plan identifiers
	 */
	private String pricePlanIds;
	/**
	 * Subscription preview module identifier
	 */
	private Long previewModuleId;
	/**
	 * The household limitation module identifier associated with this subscription
	 */
	private Integer householdLimitationsId;
	/**
	 * The subscription grace period in minutes
	 */
	private Integer gracePeriodMinutes;
	/**
	 * List of premium services included in the subscription
	 */
	private List<PremiumService> premiumServices;
	/**
	 * List of Coupons group
	 */
	private List<SubscriptionCouponGroup> couponsGroups;
	/**
	 * List of Subscription product codes
	 */
	private List<ProductCode> productCodes;
	/**
	 * Dependency Type
	 */
	private SubscriptionDependencyType dependencyType;
	/**
	 * External ID
	 */
	private String externalId;
	/**
	 * Is cancellation blocked for the subscription
	 */
	private Boolean isCancellationBlocked;
	/**
	 * The Pre-Sale date the subscription is available for purchasing
	 */
	private Long preSaleDate;
	/**
	 * Ads policy
	 */
	private AdsPolicy adsPolicy;
	/**
	 * The parameters to pass to the ads server
	 */
	private String adsParam;
	/**
	 * Is active subscription
	 */
	private Boolean isActive;

	// channelsIds:
	public String getChannelsIds(){
		return this.channelsIds;
	}
	public void setChannelsIds(String channelsIds){
		this.channelsIds = channelsIds;
	}

	public void channelsIds(String multirequestToken){
		setToken("channelsIds", multirequestToken);
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

	// internalDiscountModuleId:
	public Long getInternalDiscountModuleId(){
		return this.internalDiscountModuleId;
	}
	public void setInternalDiscountModuleId(Long internalDiscountModuleId){
		this.internalDiscountModuleId = internalDiscountModuleId;
	}

	public void internalDiscountModuleId(String multirequestToken){
		setToken("internalDiscountModuleId", multirequestToken);
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

	// prorityInOrder:
	public Long getProrityInOrder(){
		return this.prorityInOrder;
	}
	public void setProrityInOrder(Long prorityInOrder){
		this.prorityInOrder = prorityInOrder;
	}

	public void prorityInOrder(String multirequestToken){
		setToken("prorityInOrder", multirequestToken);
	}

	// pricePlanIds:
	public String getPricePlanIds(){
		return this.pricePlanIds;
	}
	public void setPricePlanIds(String pricePlanIds){
		this.pricePlanIds = pricePlanIds;
	}

	public void pricePlanIds(String multirequestToken){
		setToken("pricePlanIds", multirequestToken);
	}

	// previewModuleId:
	public Long getPreviewModuleId(){
		return this.previewModuleId;
	}
	public void setPreviewModuleId(Long previewModuleId){
		this.previewModuleId = previewModuleId;
	}

	public void previewModuleId(String multirequestToken){
		setToken("previewModuleId", multirequestToken);
	}

	// householdLimitationsId:
	public Integer getHouseholdLimitationsId(){
		return this.householdLimitationsId;
	}
	public void setHouseholdLimitationsId(Integer householdLimitationsId){
		this.householdLimitationsId = householdLimitationsId;
	}

	public void householdLimitationsId(String multirequestToken){
		setToken("householdLimitationsId", multirequestToken);
	}

	// gracePeriodMinutes:
	public Integer getGracePeriodMinutes(){
		return this.gracePeriodMinutes;
	}
	public void setGracePeriodMinutes(Integer gracePeriodMinutes){
		this.gracePeriodMinutes = gracePeriodMinutes;
	}

	public void gracePeriodMinutes(String multirequestToken){
		setToken("gracePeriodMinutes", multirequestToken);
	}

	// premiumServices:
	public List<PremiumService> getPremiumServices(){
		return this.premiumServices;
	}
	public void setPremiumServices(List<PremiumService> premiumServices){
		this.premiumServices = premiumServices;
	}

	// couponsGroups:
	public List<SubscriptionCouponGroup> getCouponsGroups(){
		return this.couponsGroups;
	}
	public void setCouponsGroups(List<SubscriptionCouponGroup> couponsGroups){
		this.couponsGroups = couponsGroups;
	}

	// productCodes:
	public List<ProductCode> getProductCodes(){
		return this.productCodes;
	}
	public void setProductCodes(List<ProductCode> productCodes){
		this.productCodes = productCodes;
	}

	// dependencyType:
	public SubscriptionDependencyType getDependencyType(){
		return this.dependencyType;
	}
	public void setDependencyType(SubscriptionDependencyType dependencyType){
		this.dependencyType = dependencyType;
	}

	public void dependencyType(String multirequestToken){
		setToken("dependencyType", multirequestToken);
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

	// isCancellationBlocked:
	public Boolean getIsCancellationBlocked(){
		return this.isCancellationBlocked;
	}
	public void setIsCancellationBlocked(Boolean isCancellationBlocked){
		this.isCancellationBlocked = isCancellationBlocked;
	}

	public void isCancellationBlocked(String multirequestToken){
		setToken("isCancellationBlocked", multirequestToken);
	}

	// preSaleDate:
	public Long getPreSaleDate(){
		return this.preSaleDate;
	}
	public void setPreSaleDate(Long preSaleDate){
		this.preSaleDate = preSaleDate;
	}

	public void preSaleDate(String multirequestToken){
		setToken("preSaleDate", multirequestToken);
	}

	// adsPolicy:
	public AdsPolicy getAdsPolicy(){
		return this.adsPolicy;
	}
	public void setAdsPolicy(AdsPolicy adsPolicy){
		this.adsPolicy = adsPolicy;
	}

	public void adsPolicy(String multirequestToken){
		setToken("adsPolicy", multirequestToken);
	}

	// adsParam:
	public String getAdsParam(){
		return this.adsParam;
	}
	public void setAdsParam(String adsParam){
		this.adsParam = adsParam;
	}

	public void adsParam(String multirequestToken){
		setToken("adsParam", multirequestToken);
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


	public SubscriptionInternal() {
		super();
	}

	public SubscriptionInternal(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		channelsIds = GsonParser.parseString(jsonObject.get("channelsIds"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		fileTypesIds = GsonParser.parseString(jsonObject.get("fileTypesIds"));
		internalDiscountModuleId = GsonParser.parseLong(jsonObject.get("internalDiscountModuleId"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		prorityInOrder = GsonParser.parseLong(jsonObject.get("prorityInOrder"));
		pricePlanIds = GsonParser.parseString(jsonObject.get("pricePlanIds"));
		previewModuleId = GsonParser.parseLong(jsonObject.get("previewModuleId"));
		householdLimitationsId = GsonParser.parseInt(jsonObject.get("householdLimitationsId"));
		gracePeriodMinutes = GsonParser.parseInt(jsonObject.get("gracePeriodMinutes"));
		premiumServices = GsonParser.parseArray(jsonObject.getAsJsonArray("premiumServices"), PremiumService.class);
		couponsGroups = GsonParser.parseArray(jsonObject.getAsJsonArray("couponsGroups"), SubscriptionCouponGroup.class);
		productCodes = GsonParser.parseArray(jsonObject.getAsJsonArray("productCodes"), ProductCode.class);
		dependencyType = SubscriptionDependencyType.get(GsonParser.parseString(jsonObject.get("dependencyType")));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		isCancellationBlocked = GsonParser.parseBoolean(jsonObject.get("isCancellationBlocked"));
		preSaleDate = GsonParser.parseLong(jsonObject.get("preSaleDate"));
		adsPolicy = AdsPolicy.get(GsonParser.parseString(jsonObject.get("adsPolicy")));
		adsParam = GsonParser.parseString(jsonObject.get("adsParam"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscriptionInternal");
		kparams.add("channelsIds", this.channelsIds);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("fileTypesIds", this.fileTypesIds);
		kparams.add("internalDiscountModuleId", this.internalDiscountModuleId);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("prorityInOrder", this.prorityInOrder);
		kparams.add("pricePlanIds", this.pricePlanIds);
		kparams.add("previewModuleId", this.previewModuleId);
		kparams.add("householdLimitationsId", this.householdLimitationsId);
		kparams.add("gracePeriodMinutes", this.gracePeriodMinutes);
		kparams.add("premiumServices", this.premiumServices);
		kparams.add("couponsGroups", this.couponsGroups);
		kparams.add("productCodes", this.productCodes);
		kparams.add("dependencyType", this.dependencyType);
		kparams.add("externalId", this.externalId);
		kparams.add("isCancellationBlocked", this.isCancellationBlocked);
		kparams.add("preSaleDate", this.preSaleDate);
		kparams.add("adsPolicy", this.adsPolicy);
		kparams.add("adsParam", this.adsParam);
		kparams.add("isActive", this.isActive);
		return kparams;
	}

}

