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
// Copyright (C) 2006-2023  Kaltura Inc.
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

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Program-asset info
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ProgramAsset.Tokenizer.class)
public class ProgramAsset extends Asset {
	
	public interface Tokenizer extends Asset.Tokenizer {
		String epgChannelId();
		String epgId();
		String relatedMediaId();
		String crid();
		String linearAssetId();
		String enableCdvr();
		String enableCatchUp();
		String enableStartOver();
		String enableTrickPlay();
		String externalOfferIds();
	}

	/**
	 * EPG channel identifier
	 */
	private Long epgChannelId;
	/**
	 * EPG identifier
	 */
	private String epgId;
	/**
	 * Ralated media identifier
	 */
	private Long relatedMediaId;
	/**
	 * Unique identifier for the program
	 */
	private String crid;
	/**
	 * Id of linear media asset
	 */
	private Long linearAssetId;
	/**
	 * Is CDVR enabled for this asset              Please, note that value of this
	  property is strictly connected with CDV-R setting on Partner and
	  KalturaLiveAsset levels.              In order to enable CDV-R for
	  KalturaProgramAsset, Partner and KalturaLiveAsset CDV-R settings should be
	  enabled.
	 */
	private Boolean enableCdvr;
	/**
	 * Is catch-up enabled for this asset              Please, note that value of this
	  property is strictly connected with Catch Up setting on Partner and
	  KalturaLiveAsset levels.              In order to enable Catch Up for
	  KalturaProgramAsset, Partner and KalturaLiveAsset Catch Up settings should be
	  enabled.
	 */
	private Boolean enableCatchUp;
	/**
	 * Is start over enabled for this asset              Please, note that value of
	  this property is strictly connected with Start Over setting on Partner and
	  KalturaLiveAsset levels.              In order to enable Start Over for
	  KalturaProgramAsset, Partner and KalturaLiveAsset Start Over settings should be
	  enabled.
	 */
	private Boolean enableStartOver;
	/**
	 * Is trick-play enabled for this asset              Please, note that value of
	  this property is strictly connected with Trick Play setting on Partner and
	  KalturaLiveAsset levels.              In order to enable Trick Play for
	  KalturaProgramAsset, Partner and KalturaLiveAsset Trick Play settings should be
	  enabled.
	 */
	private Boolean enableTrickPlay;
	/**
	 * Contains comma separate list of KalturaProgramAssetGroupOffer.externalOfferId
	  values indicating the PAGOs to which the Program Asset is bound.
	 */
	private String externalOfferIds;

	// epgChannelId:
	public Long getEpgChannelId(){
		return this.epgChannelId;
	}
	// epgId:
	public String getEpgId(){
		return this.epgId;
	}
	// relatedMediaId:
	public Long getRelatedMediaId(){
		return this.relatedMediaId;
	}
	public void setRelatedMediaId(Long relatedMediaId){
		this.relatedMediaId = relatedMediaId;
	}

	public void relatedMediaId(String multirequestToken){
		setToken("relatedMediaId", multirequestToken);
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

	// enableCdvr:
	public Boolean getEnableCdvr(){
		return this.enableCdvr;
	}
	public void setEnableCdvr(Boolean enableCdvr){
		this.enableCdvr = enableCdvr;
	}

	public void enableCdvr(String multirequestToken){
		setToken("enableCdvr", multirequestToken);
	}

	// enableCatchUp:
	public Boolean getEnableCatchUp(){
		return this.enableCatchUp;
	}
	public void setEnableCatchUp(Boolean enableCatchUp){
		this.enableCatchUp = enableCatchUp;
	}

	public void enableCatchUp(String multirequestToken){
		setToken("enableCatchUp", multirequestToken);
	}

	// enableStartOver:
	public Boolean getEnableStartOver(){
		return this.enableStartOver;
	}
	public void setEnableStartOver(Boolean enableStartOver){
		this.enableStartOver = enableStartOver;
	}

	public void enableStartOver(String multirequestToken){
		setToken("enableStartOver", multirequestToken);
	}

	// enableTrickPlay:
	public Boolean getEnableTrickPlay(){
		return this.enableTrickPlay;
	}
	public void setEnableTrickPlay(Boolean enableTrickPlay){
		this.enableTrickPlay = enableTrickPlay;
	}

	public void enableTrickPlay(String multirequestToken){
		setToken("enableTrickPlay", multirequestToken);
	}

	// externalOfferIds:
	public String getExternalOfferIds(){
		return this.externalOfferIds;
	}
	public void setExternalOfferIds(String externalOfferIds){
		this.externalOfferIds = externalOfferIds;
	}

	public void externalOfferIds(String multirequestToken){
		setToken("externalOfferIds", multirequestToken);
	}


	public ProgramAsset() {
		super();
	}

	public ProgramAsset(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		epgChannelId = GsonParser.parseLong(jsonObject.get("epgChannelId"));
		epgId = GsonParser.parseString(jsonObject.get("epgId"));
		relatedMediaId = GsonParser.parseLong(jsonObject.get("relatedMediaId"));
		crid = GsonParser.parseString(jsonObject.get("crid"));
		linearAssetId = GsonParser.parseLong(jsonObject.get("linearAssetId"));
		enableCdvr = GsonParser.parseBoolean(jsonObject.get("enableCdvr"));
		enableCatchUp = GsonParser.parseBoolean(jsonObject.get("enableCatchUp"));
		enableStartOver = GsonParser.parseBoolean(jsonObject.get("enableStartOver"));
		enableTrickPlay = GsonParser.parseBoolean(jsonObject.get("enableTrickPlay"));
		externalOfferIds = GsonParser.parseString(jsonObject.get("externalOfferIds"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaProgramAsset");
		kparams.add("relatedMediaId", this.relatedMediaId);
		kparams.add("crid", this.crid);
		kparams.add("linearAssetId", this.linearAssetId);
		kparams.add("enableCdvr", this.enableCdvr);
		kparams.add("enableCatchUp", this.enableCatchUp);
		kparams.add("enableStartOver", this.enableStartOver);
		kparams.add("enableTrickPlay", this.enableTrickPlay);
		kparams.add("externalOfferIds", this.externalOfferIds);
		return kparams;
	}

}

