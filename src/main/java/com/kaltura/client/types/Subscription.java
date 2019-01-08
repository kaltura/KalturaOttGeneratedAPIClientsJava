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
// Copyright (C) 2006-2018  Kaltura Inc.
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
import com.kaltura.client.enums.SubscriptionDependencyType;
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.PreviewModule;
import com.kaltura.client.types.PriceDetails;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;
import java.util.List;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Subscription details
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Subscription.Tokenizer.class)
public class Subscription extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		RequestBuilder.ListTokenizer<BaseChannel.Tokenizer> channels();
		String startDate();
		String endDate();
		RequestBuilder.ListTokenizer<IntegerValue.Tokenizer> fileTypes();
		String isRenewable();
		String renewalsNumber();
		String isInfiniteRenewal();
		PriceDetails.Tokenizer price();
		DiscountModule.Tokenizer discountModule();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		String mediaId();
		String prorityInOrder();
		String pricePlanIds();
		PreviewModule.Tokenizer previewModule();
		String householdLimitationsId();
		String gracePeriodMinutes();
		RequestBuilder.ListTokenizer<PremiumService.Tokenizer> premiumServices();
		String maxViewsNumber();
		String viewLifeCycle();
		String waiverPeriod();
		String isWaiverEnabled();
		RequestBuilder.ListTokenizer<OTTUserType.Tokenizer> userTypes();
		RequestBuilder.ListTokenizer<CouponsGroup.Tokenizer> couponsGroups();
		RequestBuilder.ListTokenizer<ProductCode.Tokenizer> productCodes();
		String dependencyType();
		String externalId();
		String isCancellationBlocked();
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
	public void setChannels(List<BaseChannel> channels){
		this.channels = channels;
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
	public void setFileTypes(List<IntegerValue> fileTypes){
		this.fileTypes = fileTypes;
	}

	// isRenewable:
	public Boolean getIsRenewable(){
		return this.isRenewable;
	}
	public void setIsRenewable(Boolean isRenewable){
		this.isRenewable = isRenewable;
	}

	public void isRenewable(String multirequestToken){
		setToken("isRenewable", multirequestToken);
	}

	// renewalsNumber:
	public Integer getRenewalsNumber(){
		return this.renewalsNumber;
	}
	public void setRenewalsNumber(Integer renewalsNumber){
		this.renewalsNumber = renewalsNumber;
	}

	public void renewalsNumber(String multirequestToken){
		setToken("renewalsNumber", multirequestToken);
	}

	// isInfiniteRenewal:
	public Boolean getIsInfiniteRenewal(){
		return this.isInfiniteRenewal;
	}
	public void setIsInfiniteRenewal(Boolean isInfiniteRenewal){
		this.isInfiniteRenewal = isInfiniteRenewal;
	}

	public void isInfiniteRenewal(String multirequestToken){
		setToken("isInfiniteRenewal", multirequestToken);
	}

	// price:
	public PriceDetails getPrice(){
		return this.price;
	}
	public void setPrice(PriceDetails price){
		this.price = price;
	}

	// discountModule:
	public DiscountModule getDiscountModule(){
		return this.discountModule;
	}
	public void setDiscountModule(DiscountModule discountModule){
		this.discountModule = discountModule;
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
	public void setMediaId(Integer mediaId){
		this.mediaId = mediaId;
	}

	public void mediaId(String multirequestToken){
		setToken("mediaId", multirequestToken);
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
	public void setPreviewModule(PreviewModule previewModule){
		this.previewModule = previewModule;
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
	public void setMaxViewsNumber(Integer maxViewsNumber){
		this.maxViewsNumber = maxViewsNumber;
	}

	public void maxViewsNumber(String multirequestToken){
		setToken("maxViewsNumber", multirequestToken);
	}

	// viewLifeCycle:
	public Integer getViewLifeCycle(){
		return this.viewLifeCycle;
	}
	public void setViewLifeCycle(Integer viewLifeCycle){
		this.viewLifeCycle = viewLifeCycle;
	}

	public void viewLifeCycle(String multirequestToken){
		setToken("viewLifeCycle", multirequestToken);
	}

	// waiverPeriod:
	public Integer getWaiverPeriod(){
		return this.waiverPeriod;
	}
	public void setWaiverPeriod(Integer waiverPeriod){
		this.waiverPeriod = waiverPeriod;
	}

	public void waiverPeriod(String multirequestToken){
		setToken("waiverPeriod", multirequestToken);
	}

	// isWaiverEnabled:
	public Boolean getIsWaiverEnabled(){
		return this.isWaiverEnabled;
	}
	public void setIsWaiverEnabled(Boolean isWaiverEnabled){
		this.isWaiverEnabled = isWaiverEnabled;
	}

	public void isWaiverEnabled(String multirequestToken){
		setToken("isWaiverEnabled", multirequestToken);
	}

	// userTypes:
	public List<OTTUserType> getUserTypes(){
		return this.userTypes;
	}
	public void setUserTypes(List<OTTUserType> userTypes){
		this.userTypes = userTypes;
	}

	// couponsGroups:
	public List<CouponsGroup> getCouponsGroups(){
		return this.couponsGroups;
	}
	public void setCouponsGroups(List<CouponsGroup> couponsGroups){
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


	public Subscription() {
		super();
	}

	public Subscription(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		channels = GsonParser.parseArray(jsonObject.getAsJsonArray("channels"), BaseChannel.class);
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		fileTypes = GsonParser.parseArray(jsonObject.getAsJsonArray("fileTypes"), IntegerValue.class);
		isRenewable = GsonParser.parseBoolean(jsonObject.get("isRenewable"));
		renewalsNumber = GsonParser.parseInt(jsonObject.get("renewalsNumber"));
		isInfiniteRenewal = GsonParser.parseBoolean(jsonObject.get("isInfiniteRenewal"));
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), PriceDetails.class);
		discountModule = GsonParser.parseObject(jsonObject.getAsJsonObject("discountModule"), DiscountModule.class);
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		mediaId = GsonParser.parseInt(jsonObject.get("mediaId"));
		prorityInOrder = GsonParser.parseLong(jsonObject.get("prorityInOrder"));
		pricePlanIds = GsonParser.parseString(jsonObject.get("pricePlanIds"));
		previewModule = GsonParser.parseObject(jsonObject.getAsJsonObject("previewModule"), PreviewModule.class);
		householdLimitationsId = GsonParser.parseInt(jsonObject.get("householdLimitationsId"));
		gracePeriodMinutes = GsonParser.parseInt(jsonObject.get("gracePeriodMinutes"));
		premiumServices = GsonParser.parseArray(jsonObject.getAsJsonArray("premiumServices"), PremiumService.class);
		maxViewsNumber = GsonParser.parseInt(jsonObject.get("maxViewsNumber"));
		viewLifeCycle = GsonParser.parseInt(jsonObject.get("viewLifeCycle"));
		waiverPeriod = GsonParser.parseInt(jsonObject.get("waiverPeriod"));
		isWaiverEnabled = GsonParser.parseBoolean(jsonObject.get("isWaiverEnabled"));
		userTypes = GsonParser.parseArray(jsonObject.getAsJsonArray("userTypes"), OTTUserType.class);
		couponsGroups = GsonParser.parseArray(jsonObject.getAsJsonArray("couponsGroups"), CouponsGroup.class);
		productCodes = GsonParser.parseArray(jsonObject.getAsJsonArray("productCodes"), ProductCode.class);
		dependencyType = SubscriptionDependencyType.get(GsonParser.parseString(jsonObject.get("dependencyType")));
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		isCancellationBlocked = GsonParser.parseBoolean(jsonObject.get("isCancellationBlocked"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSubscription");
		kparams.add("id", this.id);
		kparams.add("channels", this.channels);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("fileTypes", this.fileTypes);
		kparams.add("isRenewable", this.isRenewable);
		kparams.add("renewalsNumber", this.renewalsNumber);
		kparams.add("isInfiniteRenewal", this.isInfiniteRenewal);
		kparams.add("price", this.price);
		kparams.add("discountModule", this.discountModule);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("mediaId", this.mediaId);
		kparams.add("prorityInOrder", this.prorityInOrder);
		kparams.add("pricePlanIds", this.pricePlanIds);
		kparams.add("previewModule", this.previewModule);
		kparams.add("householdLimitationsId", this.householdLimitationsId);
		kparams.add("gracePeriodMinutes", this.gracePeriodMinutes);
		kparams.add("premiumServices", this.premiumServices);
		kparams.add("maxViewsNumber", this.maxViewsNumber);
		kparams.add("viewLifeCycle", this.viewLifeCycle);
		kparams.add("waiverPeriod", this.waiverPeriod);
		kparams.add("isWaiverEnabled", this.isWaiverEnabled);
		kparams.add("userTypes", this.userTypes);
		kparams.add("couponsGroups", this.couponsGroups);
		kparams.add("productCodes", this.productCodes);
		kparams.add("dependencyType", this.dependencyType);
		kparams.add("externalId", this.externalId);
		kparams.add("isCancellationBlocked", this.isCancellationBlocked);
		return kparams;
	}

}

