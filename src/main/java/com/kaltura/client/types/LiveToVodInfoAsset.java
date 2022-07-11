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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(LiveToVodInfoAsset.Tokenizer.class)
public class LiveToVodInfoAsset extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String linearAssetId();
		String epgId();
		String epgChannelId();
		String crid();
		String originalStartDate();
		String originalEndDate();
		String paddingBeforeProgramStarts();
		String paddingAfterProgramEnds();
	}

	/**
	 * Linear Asset Id
	 */
	private Long linearAssetId;
	/**
	 * EPG Id
	 */
	private String epgId;
	/**
	 * EPG Channel Id
	 */
	private Long epgChannelId;
	/**
	 * Crid
	 */
	private String crid;
	/**
	 * Original Start Date
	 */
	private Long originalStartDate;
	/**
	 * Original End Date
	 */
	private Long originalEndDate;
	/**
	 * Padding before program starts
	 */
	private Long paddingBeforeProgramStarts;
	/**
	 * Padding after program ends
	 */
	private Long paddingAfterProgramEnds;

	// linearAssetId:
	public Long getLinearAssetId(){
		return this.linearAssetId;
	}
	public void setLinearAssetId(Long linearAssetId){
		this.linearAssetId = linearAssetId;
	}

	public void linearAssetId(String multirequestToken){
		setToken("linearAssetId", multirequestToken);
	}

	// epgId:
	public String getEpgId(){
		return this.epgId;
	}
	public void setEpgId(String epgId){
		this.epgId = epgId;
	}

	public void epgId(String multirequestToken){
		setToken("epgId", multirequestToken);
	}

	// epgChannelId:
	public Long getEpgChannelId(){
		return this.epgChannelId;
	}
	public void setEpgChannelId(Long epgChannelId){
		this.epgChannelId = epgChannelId;
	}

	public void epgChannelId(String multirequestToken){
		setToken("epgChannelId", multirequestToken);
	}

	// crid:
	public String getCrid(){
		return this.crid;
	}
	public void setCrid(String crid){
		this.crid = crid;
	}

	public void crid(String multirequestToken){
		setToken("crid", multirequestToken);
	}

	// originalStartDate:
	public Long getOriginalStartDate(){
		return this.originalStartDate;
	}
	public void setOriginalStartDate(Long originalStartDate){
		this.originalStartDate = originalStartDate;
	}

	public void originalStartDate(String multirequestToken){
		setToken("originalStartDate", multirequestToken);
	}

	// originalEndDate:
	public Long getOriginalEndDate(){
		return this.originalEndDate;
	}
	public void setOriginalEndDate(Long originalEndDate){
		this.originalEndDate = originalEndDate;
	}

	public void originalEndDate(String multirequestToken){
		setToken("originalEndDate", multirequestToken);
	}

	// paddingBeforeProgramStarts:
	public Long getPaddingBeforeProgramStarts(){
		return this.paddingBeforeProgramStarts;
	}
	public void setPaddingBeforeProgramStarts(Long paddingBeforeProgramStarts){
		this.paddingBeforeProgramStarts = paddingBeforeProgramStarts;
	}

	public void paddingBeforeProgramStarts(String multirequestToken){
		setToken("paddingBeforeProgramStarts", multirequestToken);
	}

	// paddingAfterProgramEnds:
	public Long getPaddingAfterProgramEnds(){
		return this.paddingAfterProgramEnds;
	}
	public void setPaddingAfterProgramEnds(Long paddingAfterProgramEnds){
		this.paddingAfterProgramEnds = paddingAfterProgramEnds;
	}

	public void paddingAfterProgramEnds(String multirequestToken){
		setToken("paddingAfterProgramEnds", multirequestToken);
	}


	public LiveToVodInfoAsset() {
		super();
	}

	public LiveToVodInfoAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		linearAssetId = GsonParser.parseLong(jsonObject.get("linearAssetId"));
		epgId = GsonParser.parseString(jsonObject.get("epgId"));
		epgChannelId = GsonParser.parseLong(jsonObject.get("epgChannelId"));
		crid = GsonParser.parseString(jsonObject.get("crid"));
		originalStartDate = GsonParser.parseLong(jsonObject.get("originalStartDate"));
		originalEndDate = GsonParser.parseLong(jsonObject.get("originalEndDate"));
		paddingBeforeProgramStarts = GsonParser.parseLong(jsonObject.get("paddingBeforeProgramStarts"));
		paddingAfterProgramEnds = GsonParser.parseLong(jsonObject.get("paddingAfterProgramEnds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaLiveToVodInfoAsset");
		kparams.add("linearAssetId", this.linearAssetId);
		kparams.add("epgId", this.epgId);
		kparams.add("epgChannelId", this.epgChannelId);
		kparams.add("crid", this.crid);
		kparams.add("originalStartDate", this.originalStartDate);
		kparams.add("originalEndDate", this.originalEndDate);
		kparams.add("paddingBeforeProgramStarts", this.paddingBeforeProgramStarts);
		kparams.add("paddingAfterProgramEnds", this.paddingAfterProgramEnds);
		return kparams;
	}

}

