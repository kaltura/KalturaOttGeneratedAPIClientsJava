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

import com.google.gson.JsonObject;
import com.kaltura.client.Params;
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.MultilingualString;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.types.PriceDetails;
import com.kaltura.client.types.UsageModule;
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

/**  Collection  */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(Collection.Tokenizer.class)
public class Collection extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		RequestBuilder.ListTokenizer<BaseChannel.Tokenizer> channels();
		String startDate();
		String endDate();
		PriceDetails.Tokenizer price();
		DiscountModule.Tokenizer discountModule();
		String name();
		MultilingualString.Tokenizer multilingualName();
		String description();
		MultilingualString.Tokenizer multilingualDescription();
		UsageModule.Tokenizer usageModule();
		RequestBuilder.ListTokenizer<CouponsGroup.Tokenizer> couponsGroups();
		String externalId();
		RequestBuilder.ListTokenizer<ProductCode.Tokenizer> productCodes();
	}

	/**  Collection identifier  */
	private String id;
	/**  A list of channels associated with this collection  */
	private List<BaseChannel> channels;
	/**  The first date the collection is available for purchasing  */
	private Long startDate;
	/**  The last date the collection is available for purchasing  */
	private Long endDate;
	/**  The price of the subscription  */
	private PriceDetails price;
	/**  The internal discount module for the subscription  */
	private DiscountModule discountModule;
	/**  Name of the subscription  */
	private String name;
	/**  Name of the subscription  */
	private MultilingualString multilingualName;
	/**  description of the subscription  */
	private String description;
	/**  description of the subscription  */
	private MultilingualString multilingualDescription;
	/**  Collection usage module  */
	private UsageModule usageModule;
	/**  List of Coupons group  */
	private List<CouponsGroup> couponsGroups;
	/**  External ID  */
	private String externalId;
	/**  List of Collection product codes  */
	private List<ProductCode> productCodes;

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
	public void setName(String name){
		this.name = name;
	}

	public void name(String multirequestToken){
		setToken("name", multirequestToken);
	}

	// multilingualName:
	public MultilingualString getMultilingualName(){
		return this.multilingualName;
	}
	public void setMultilingualName(MultilingualString multilingualName){
		this.multilingualName = multilingualName;
	}

	// description:
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public void description(String multirequestToken){
		setToken("description", multirequestToken);
	}

	// multilingualDescription:
	public MultilingualString getMultilingualDescription(){
		return this.multilingualDescription;
	}
	public void setMultilingualDescription(MultilingualString multilingualDescription){
		this.multilingualDescription = multilingualDescription;
	}

	// usageModule:
	public UsageModule getUsageModule(){
		return this.usageModule;
	}
	public void setUsageModule(UsageModule usageModule){
		this.usageModule = usageModule;
	}

	// couponsGroups:
	public List<CouponsGroup> getCouponsGroups(){
		return this.couponsGroups;
	}
	public void setCouponsGroups(List<CouponsGroup> couponsGroups){
		this.couponsGroups = couponsGroups;
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


	public Collection() {
		super();
	}

	public Collection(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseString(jsonObject.get("id"));
		channels = GsonParser.parseArray(jsonObject.getAsJsonArray("channels"), BaseChannel.class);
		startDate = GsonParser.parseLong(jsonObject.get("startDate"));
		endDate = GsonParser.parseLong(jsonObject.get("endDate"));
		price = GsonParser.parseObject(jsonObject.getAsJsonObject("price"), PriceDetails.class);
		discountModule = GsonParser.parseObject(jsonObject.getAsJsonObject("discountModule"), DiscountModule.class);
		name = GsonParser.parseString(jsonObject.get("name"));
		multilingualName = GsonParser.parseObject(jsonObject.getAsJsonObject("multilingualName"), MultilingualString.class);
		description = GsonParser.parseString(jsonObject.get("description"));
		multilingualDescription = GsonParser.parseObject(jsonObject.getAsJsonObject("multilingualDescription"), MultilingualString.class);
		usageModule = GsonParser.parseObject(jsonObject.getAsJsonObject("usageModule"), UsageModule.class);
		couponsGroups = GsonParser.parseArray(jsonObject.getAsJsonArray("couponsGroups"), CouponsGroup.class);
		externalId = GsonParser.parseString(jsonObject.get("externalId"));
		productCodes = GsonParser.parseArray(jsonObject.getAsJsonArray("productCodes"), ProductCode.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaCollection");
		kparams.add("id", this.id);
		kparams.add("channels", this.channels);
		kparams.add("startDate", this.startDate);
		kparams.add("endDate", this.endDate);
		kparams.add("price", this.price);
		kparams.add("discountModule", this.discountModule);
		kparams.add("name", this.name);
		kparams.add("multilingualName", this.multilingualName);
		kparams.add("description", this.description);
		kparams.add("multilingualDescription", this.multilingualDescription);
		kparams.add("usageModule", this.usageModule);
		kparams.add("couponsGroups", this.couponsGroups);
		kparams.add("externalId", this.externalId);
		kparams.add("productCodes", this.productCodes);
		return kparams;
	}

}

