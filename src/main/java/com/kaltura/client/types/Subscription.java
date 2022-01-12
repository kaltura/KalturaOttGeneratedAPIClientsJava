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
// Copyright (C) 2006-2022  Kaltura Inc.
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
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.PreviewModule;
import com.kaltura.client.types.PriceDetails;
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
 * Subscription details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Subscription.Tokenizer.class)
public class Subscription extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String id();
		RequestBuilder.ListTokenizer<BaseChannel.Tokenizer> channels();
		String channelsIds();
		String startDate();
		String endDate();
		RequestBuilder.ListTokenizer<IntegerValue.Tokenizer> fileTypes();
		String fileTypesIds();
		String isRenewable();
		String renewalsNumber();
		String isInfiniteRenewal();
		PriceDetails.Tokenizer price();
		DiscountModule.Tokenizer discountModule();
		String internalDiscountModuleId();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		String mediaId();
		String prorityInOrder();
		String pricePlanIds();
		PreviewModule.Tokenizer previewModule();
		String previewModuleId();
		String householdLimitationsId();
		String gracePeriodMinutes();
		RequestBuilder.ListTokenizer<PremiumService.Tokenizer> premiumServices();
		String maxViewsNumber();
		String viewLifeCycle();
		String waiverPeriod();
		String isWaiverEnabled();
		RequestBuilder.ListTokenizer<OTTUserType.Tokenizer> userTypes();
		RequestBuilder.ListTokenizer<CouponsGroup.Tokenizer> couponsGroups();
		RequestBuilder.ListTokenizer<SubscriptionCouponGroup.Tokenizer> subscriptionCouponGroup();
		RequestBuilder.ListTokenizer<ProductCode.Tokenizer> productCodes();
		String dependencyType();
		String externalId();
		String isCancellationBlocked();
		String preSaleDate();
		String adsPolicy();
		String adsParam();
		String isActive();
		String createDate();
		String updateDate();
	}

	/**
	 * Subscription identifier
	 */
	private String id;
	/**
	 * A list of channels associated with this subscription
	 */
	private List<BaseChannel> channels;
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
	 * A list of file types identifiers that are supported in this subscription
	 */
	private List<IntegerValue> fileTypes;
	/**
	 * Comma separated file types identifiers that are supported in this subscription
	 */
	private String fileTypesIds;
	/**
	 * Denotes whether or not this subscription can be renewed
	 */
	private Boolean isRenewable;
	/**
	 * Defines the number of times this subscription will be renewed
	 */
	private Integer renewalsNumber;
	/**
	 * Indicates whether the subscription will renew forever
	 */
	private Boolean isInfiniteRenewal;
	/**
	 * The price of the subscription
	 */
	private PriceDetails price;
	/**
	 * The internal discount module for the subscription
	 */
	private DiscountModule discountModule;
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
	 * Identifier of the media associated with the subscription
	 */
	private Integer mediaId;
	/**
	 * Subscription order (when returned in methods that retrieve subscriptions)
	 */
	private Long prorityInOrder;
	/**
	 * Comma separated subscription price plan IDs
	 */
	private String pricePlanIds;
	/**
	 * Subscription preview module
	 */
	private PreviewModule previewModule;
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
	 * The maximum number of times an item in this usage module can be viewed
	 */
	private Integer maxViewsNumber;
	/**
	 * The amount time an item is available for viewing since a user started watching
	  the item
	 */
	private Integer viewLifeCycle;
	/**
	 * Time period during which the end user can waive his rights to cancel a purchase.
	  When the time period is passed, the purchase can no longer be cancelled
	 */
	private Integer waiverPeriod;
	/**
	 * Indicates whether or not the end user has the right to waive his rights to
	  cancel a purchase
	 */
	private Boolean isWaiverEnabled;
	/**
	 * List of permitted user types for the subscription
	 */
	private List<OTTUserType> userTypes;
	/**
	 * List of Coupons group
	 */
	private List<CouponsGroup> couponsGroups;
	/**
	 * List of subscription Coupons group
	 */
	private List<SubscriptionCouponGroup> subscriptionCouponGroup;
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
	/**
	 * Specifies when was the Subscription created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the Subscription last updated. Date and time represented as
	  epoch.
	 */
	private Long updateDate;

	// id:
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}

	public void id(String multirequestToken){
		setToken("id", multirequestToken);
	}

	// channels:
	public List<BaseChannel> getChannels(){
		return this.channels;
	}
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

	// fileTypes:
	public List<IntegerValue> getFileTypes(){
		return this.fileTypes;
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

	// isRenewable:
	public Boolean getIsRenewable(){
		return this.isRenewable;
	}
	// renewalsNumber:
	public Integer getRenewalsNumber(){
		return this.renewalsNumber;
	}
	// isInfiniteRenewal:
	public Boolean getIsInfiniteRenewal(){
		return this.isInfiniteRenewal;
	}
	// price:
	public PriceDetails getPrice(){
		return this.price;
	}
	// discountModule:
	public DiscountModule getDiscountModule(){
		return this.discountModule;
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

	// mediaId:
	public Integer getMediaId(){
		return this.mediaId;
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

	// previewModule:
	public PreviewModule getPreviewModule(){
		return this.previewModule;
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

	// maxViewsNumber:
	public Integer getMaxViewsNumber(){
		return this.maxViewsNumber;
	}
	// viewLifeCycle:
	public Integer getViewLifeCycle(){
		return this.viewLifeCycle;
	}
	// waiverPeriod:
	public Integer getWaiverPeriod(){
		return this.waiverPeriod;
	}
	// isWaiverEnabled:
	public Boolean getIsWaiverEnabled(){
		return this.isWaiverEnabled;
	}
	// userTypes:
	public List<OTTUserType> getUserTypes(){
		return this.userTypes;
	}
	// couponsGroups:
	public List<CouponsGroup> getCouponsGroups(){
		return this.couponsGroups;
	}
	// subscriptionCouponGroup:
	public List<SubscriptionCouponGroup> getSubscriptionCouponGroup(){
		return this.subscriptionCouponGroup;
	}
	public void setSubscriptionCouponGroup(List<SubscriptionCouponGroup> subscriptionCouponGroup){
		this.subscriptionCouponGroup = subscriptionCouponGroup;
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

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}

	public Subscription() {
		super();
	}

	public Subscription(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		channels = GsonParser.parseArray(jsonObject.getAsJsonArray("channels"), BaseChannel.class);
		channelsIds = GsonParser.parseString(jsonObject.get("channelsIds"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		fileTypes = GsonParser.parseArray(jsonObject.getAsJsonArray("fileTypes"), IntegerValue.class);
		fileTypesIds = GsonParser.parseString(jsonObject.get("fileTypesIds"));
		isRenewable = GsonParser.parseBoolean(jsonObject.get("isRenewable"));
		renewalsNumber = GsonParser.parseInt(jsonObject.get("renewalsNumber"));
		isInfiniteRenewal = GsonParser.parseBoolean(jsonObject.get("isInfiniteRenewal"));
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), PriceDetails.class);
		discountModule = GsonParser.parseObject(jsonObject.getAsJsonObject("discountModule"), DiscountModule.class);
		internalDiscountModuleId = GsonParser.parseLong(jsonObject.get("internalDiscountModuleId"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		mediaId = GsonParser.parseInt(jsonObject.get("mediaId"));
		prorityInOrder = GsonParser.parseLong(jsonObject.get("prorityInOrder"));
		pricePlanIds = GsonParser.parseString(jsonObject.get("pricePlanIds"));
		previewModule = GsonParser.parseObject(jsonObject.getAsJsonObject("previewModule"), PreviewModule.class);
		previewModuleId = GsonParser.parseLong(jsonObject.get("previewModuleId"));
		householdLimitationsId = GsonParser.parseInt(jsonObject.get("householdLimitationsId"));
		gracePeriodMinutes = GsonParser.parseInt(jsonObject.get("gracePeriodMinutes"));
		premiumServices = GsonParser.parseArray(jsonObject.getAsJsonArray("premiumServices"), PremiumService.class);
		maxViewsNumber = GsonParser.parseInt(jsonObject.get("maxViewsNumber"));
		viewLifeCycle = GsonParser.parseInt(jsonObject.get("viewLifeCycle"));
		waiverPeriod = GsonParser.parseInt(jsonObject.get("waiverPeriod"));
		isWaiverEnabled = GsonParser.parseBoolean(jsonObject.get("isWaiverEnabled"));
		userTypes = GsonParser.parseArray(jsonObject.getAsJsonArray("userTypes"), OTTUserType.class);
		couponsGroups = GsonParser.parseArray(jsonObject.getAsJsonArray("couponsGroups"), CouponsGroup.class);
		subscriptionCouponGroup = GsonParser.parseArray(jsonObject.getAsJsonArray("subscriptionCouponGroup"), SubscriptionCouponGroup.class);
		productCodes = GsonParser.parseArray(jsonObject.getAsJsonArray("productCodes"), ProductCode.class);
		dependencyType = SubscriptionDependencyType.get(GsonParser.parseString(jsonObject.get("dependencyType")));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		isCancellationBlocked = GsonParser.parseBoolean(jsonObject.get("isCancellationBlocked"));
		preSaleDate = GsonParser.parseLong(jsonObject.get("preSaleDate"));
		adsPolicy = AdsPolicy.get(GsonParser.parseString(jsonObject.get("adsPolicy")));
		adsParam = GsonParser.parseString(jsonObject.get("adsParam"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscription");
		kparams.add("id", this.id);
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
		kparams.add("subscriptionCouponGroup", this.subscriptionCouponGroup);
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

