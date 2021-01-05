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
import com.kaltura.client.enums.BookmarkActionType;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BookmarkEvent.Tokenizer.class)
public class BookmarkEvent extends EventObject {
	
	public interface Tokenizer extends EventObject.Tokenizer {
		String userId();
		String householdId();
		String assetId();
		String fileId();
		String position();
		String action();
		String productType();
		String productId();
	}

	/**
	 * User Id
	 */
	private Long userId;
	/**
	 * Household Id
	 */
	private Long householdId;
	/**
	 * Asset Id
	 */
	private Long assetId;
	/**
	 * File Id
	 */
	private Long fileId;
	/**
	 * position
	 */
	private Integer position;
	/**
	 * Bookmark Action Type
	 */
	private BookmarkActionType action;
	/**
	 * Product Type
	 */
	private TransactionType productType;
	/**
	 * Product Id
	 */
	private Integer productId;

	// userId:
	public Long getUserId(){
		return this.userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public void userId(String multirequestToken){
		setToken("userId", multirequestToken);
	}

	// householdId:
	public Long getHouseholdId(){
		return this.householdId;
	}
	public void setHouseholdId(Long householdId){
		this.householdId = householdId;
	}

	public void householdId(String multirequestToken){
		setToken("householdId", multirequestToken);
	}

	// assetId:
	public Long getAssetId(){
		return this.assetId;
	}
	public void setAssetId(Long assetId){
		this.assetId = assetId;
	}

	public void assetId(String multirequestToken){
		setToken("assetId", multirequestToken);
	}

	// fileId:
	public Long getFileId(){
		return this.fileId;
	}
	public void setFileId(Long fileId){
		this.fileId = fileId;
	}

	public void fileId(String multirequestToken){
		setToken("fileId", multirequestToken);
	}

	// position:
	public Integer getPosition(){
		return this.position;
	}
	public void setPosition(Integer position){
		this.position = position;
	}

	public void position(String multirequestToken){
		setToken("position", multirequestToken);
	}

	// action:
	public BookmarkActionType getAction(){
		return this.action;
	}
	public void setAction(BookmarkActionType action){
		this.action = action;
	}

	public void action(String multirequestToken){
		setToken("action", multirequestToken);
	}

	// productType:
	public TransactionType getProductType(){
		return this.productType;
	}
	public void setProductType(TransactionType productType){
		this.productType = productType;
	}

	public void productType(String multirequestToken){
		setToken("productType", multirequestToken);
	}

	// productId:
	public Integer getProductId(){
		return this.productId;
	}
	public void setProductId(Integer productId){
		this.productId = productId;
	}

	public void productId(String multirequestToken){
		setToken("productId", multirequestToken);
	}


	public BookmarkEvent() {
		super();
	}

	public BookmarkEvent(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		userId = GsonParser.parseLong(jsonObject.get("userId"));
		householdId = GsonParser.parseLong(jsonObject.get("householdId"));
		assetId = GsonParser.parseLong(jsonObject.get("assetId"));
		fileId = GsonParser.parseLong(jsonObject.get("fileId"));
		position = GsonParser.parseInt(jsonObject.get("position"));
		action = BookmarkActionType.get(GsonParser.parseString(jsonObject.get("action")));
		productType = TransactionType.get(GsonParser.parseString(jsonObject.get("productType")));
		productId = GsonParser.parseInt(jsonObject.get("productId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBookmarkEvent");
		kparams.add("userId", this.userId);
		kparams.add("householdId", this.householdId);
		kparams.add("assetId", this.assetId);
		kparams.add("fileId", this.fileId);
		kparams.add("position", this.position);
		kparams.add("action", this.action);
		kparams.add("productType", this.productType);
		kparams.add("productId", this.productId);
		return kparams;
	}

}

