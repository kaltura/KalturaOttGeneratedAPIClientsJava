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
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.UsageModule;
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
 * Collection
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Collection.Tokenizer.class)
public class Collection extends OTTObjectSupportNullable {
	
	public interface Tokenizer extends OTTObjectSupportNullable.Tokenizer {
		String id();
		RequestBuilder.ListTokenizer<BaseChannel.Tokenizer> channels();
		String channelsIds();
		String startDate();
		String endDate();
		DiscountModule.Tokenizer discountModule();
		String discountModuleId();
		String name();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualName();
		String description();
		RequestBuilder.ListTokenizer<TranslationToken.Tokenizer> multilingualDescription();
		UsageModule.Tokenizer usageModule();
		String usageModuleId();
		RequestBuilder.ListTokenizer<CouponsGroup.Tokenizer> couponsGroups();
		RequestBuilder.ListTokenizer<CollectionCouponGroup.Tokenizer> collectionCouponGroup();
		String externalId();
		RequestBuilder.ListTokenizer<ProductCode.Tokenizer> productCodes();
		String priceDetailsId();
		String isActive();
		String createDate();
		String updateDate();
		String virtualAssetId();
	}

	/**
	 * Collection identifier
	 */
	private String id;
	/**
	 * A list of channels associated with this collection               This property
	  will deprecated soon. Please use ChannelsIds instead of it.
	 */
	private List<BaseChannel> channels;
	/**
	 * Comma separated channels Ids associated with this collection
	 */
	private String channelsIds;
	/**
	 * The first date the collection is available for purchasing
	 */
	private Long startDate;
	/**
	 * The last date the collection is available for purchasing
	 */
	private Long endDate;
	/**
	 * The internal discount module for the collection              This property will
	  deprecated soon. Please use DiscountModuleId instead of it.
	 */
	private DiscountModule discountModule;
	/**
	 * The internal discount module identifier for the collection
	 */
	private Long discountModuleId;
	/**
	 * Name of the collection
	 */
	private String name;
	/**
	 * Name of the collection
	 */
	private List<TranslationToken> multilingualName;
	/**
	 * description of the collection
	 */
	private String description;
	/**
	 * description of the collection
	 */
	private List<TranslationToken> multilingualDescription;
	/**
	 * Collection usage module              This property will deprecated soon. Please
	  use usageModuleId instead of it.
	 */
	private UsageModule usageModule;
	/**
	 * The internal usage module identifier for the collection
	 */
	private Long usageModuleId;
	/**
	 * List of Coupons group              This property will deprecated soon. Please
	  use CollectionCouponGroup instead of it.
	 */
	private List<CouponsGroup> couponsGroups;
	/**
	 * List of collection Coupons group
	 */
	private List<CollectionCouponGroup> collectionCouponGroup;
	/**
	 * External ID
	 */
	private String externalId;
	/**
	 * List of Collection product codes
	 */
	private List<ProductCode> productCodes;
	/**
	 * The ID of the price details associated with this collection
	 */
	private Long priceDetailsId;
	/**
	 * Is active collection
	 */
	private Boolean isActive;
	/**
	 * Specifies when was the collection created. Date and time represented as epoch.
	 */
	private Long createDate;
	/**
	 * Specifies when was the collection last updated. Date and time represented as
	  epoch.
	 */
	private Long updateDate;
	/**
	 * Virtual asset id
	 */
	private Long virtualAssetId;

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

	// discountModule:
	public DiscountModule getDiscountModule(){
		return this.discountModule;
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

	// usageModule:
	public UsageModule getUsageModule(){
		return this.usageModule;
	}
	// usageModuleId:
	public Long getUsageModuleId(){
		return this.usageModuleId;
	}
	public void setUsageModuleId(Long usageModuleId){
		this.usageModuleId = usageModuleId;
	}

	public void usageModuleId(String multirequestToken){
		setToken("usageModuleId", multirequestToken);
	}

	// couponsGroups:
	public List<CouponsGroup> getCouponsGroups(){
		return this.couponsGroups;
	}
	// collectionCouponGroup:
	public List<CollectionCouponGroup> getCollectionCouponGroup(){
		return this.collectionCouponGroup;
	}
	public void setCollectionCouponGroup(List<CollectionCouponGroup> collectionCouponGroup){
		this.collectionCouponGroup = collectionCouponGroup;
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

	// productCodes:
	public List<ProductCode> getProductCodes(){
		return this.productCodes;
	}
	public void setProductCodes(List<ProductCode> productCodes){
		this.productCodes = productCodes;
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
	// virtualAssetId:
	public Long getVirtualAssetId(){
		return this.virtualAssetId;
	}

	public Collection() {
		super();
	}

	public Collection(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		channels = GsonParser.parseArray(jsonObject.getAsJsonArray("channels"), BaseChannel.class);
		channelsIds = GsonParser.parseString(jsonObject.get("channelsIds"));
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		discountModule = GsonParser.parseObject(jsonObject.getAsJsonObject("discountModule"), DiscountModule.class);
		discountModuleId = GsonParser.parseLong(jsonObject.get("discountModuleId"));
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualName"), TranslationToken.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseArray(jsonObject.getAsJsonArray("multilingualDescription"), TranslationToken.class);
		usageModule = GsonParser.parseObject(jsonObject.getAsJsonObject("usageModule"), UsageModule.class);
		usageModuleId = GsonParser.parseLong(jsonObject.get("usageModuleId"));
		couponsGroups = GsonParser.parseArray(jsonObject.getAsJsonArray("couponsGroups"), CouponsGroup.class);
		collectionCouponGroup = GsonParser.parseArray(jsonObject.getAsJsonArray("collectionCouponGroup"), CollectionCouponGroup.class);
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		productCodes = GsonParser.parseArray(jsonObject.getAsJsonArray("productCodes"), ProductCode.class);
		priceDetailsId = GsonParser.parseLong(jsonObject.get("priceDetailsId"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		virtualAssetId = GsonParser.parseLong(jsonObject.get("virtualAssetId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCollection");
		kparams.add("id", this.id);
		kparams.add("channelsIds", this.channelsIds);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("discountModuleId", this.discountModuleId);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("usageModuleId", this.usageModuleId);
		kparams.add("collectionCouponGroup", this.collectionCouponGroup);
		kparams.add("externalId", this.externalId);
		kparams.add("productCodes", this.productCodes);
		kparams.add("priceDetailsId", this.priceDetailsId);
		kparams.add("isActive", this.isActive);
		return kparams;
	}

}

