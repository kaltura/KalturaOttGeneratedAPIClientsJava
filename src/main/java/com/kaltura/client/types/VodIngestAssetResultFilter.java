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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(VodIngestAssetResultFilter.Tokenizer.class)
public class VodIngestAssetResultFilter extends Filter {
	
	public interface Tokenizer extends Filter.Tokenizer {
		String fileNameContains();
		String assetNameContains();
		String ingestStatusIn();
		String ingestDateGreaterThan();
		String ingestDateSmallerThan();
		String vodTypeSystemNameIn();
		String shopAssetUserRuleIdIn();
	}

	/**
	 * Filter KalturaVodIngestAssetResult elements based on the ingest XML file name or
	  partial name.
	 */
	private String fileNameContains;
	/**
	 * Filter KalturaVodIngestAssetResult elements based on the asset name or partial
	  name.
	 */
	private String assetNameContains;
	/**
	 * Comma separated values, representing multiple selection of ingest status state
	  (\&amp;quot;SUCCESS\&amp;quot;,\&amp;quot;FAIL\&amp;quot;,\&amp;quot;SUCCESS_WARNING\&amp;quot;EXTERNAL_FAIL\&amp;quot;).
	 */
	private String ingestStatusIn;
	/**
	 * Filter assets ingested after the greater than value. Date and time represented
	  as epoch.
	 */
	private Long ingestDateGreaterThan;
	/**
	 * Filter assets ingested before the smaller than value. Date and time represented
	  as epoch.
	 */
	private Long ingestDateSmallerThan;
	/**
	 * Comma separated asset types, representing multiple selection of VOD asset types
	  (e.g.
	  \&amp;quot;MOVIE\&amp;quot;,\&amp;quot;SERIES\&amp;quot;,\&amp;quot;SEASON\&amp;quot;,\&amp;quot;EPISODE\&amp;quot;...).
	 */
	private String vodTypeSystemNameIn;
	/**
	 * Comma separated Ids, pointing to AssetUserRules which hold the shop markers
	  (shop provider values)
	 */
	private String shopAssetUserRuleIdIn;

	// fileNameContains:
	public String getFileNameContains(){
		return this.fileNameContains;
	}
	public void setFileNameContains(String fileNameContains){
		this.fileNameContains = fileNameContains;
	}

	public void fileNameContains(String multirequestToken){
		setToken("fileNameContains", multirequestToken);
	}

	// assetNameContains:
	public String getAssetNameContains(){
		return this.assetNameContains;
	}
	public void setAssetNameContains(String assetNameContains){
		this.assetNameContains = assetNameContains;
	}

	public void assetNameContains(String multirequestToken){
		setToken("assetNameContains", multirequestToken);
	}

	// ingestStatusIn:
	public String getIngestStatusIn(){
		return this.ingestStatusIn;
	}
	public void setIngestStatusIn(String ingestStatusIn){
		this.ingestStatusIn = ingestStatusIn;
	}

	public void ingestStatusIn(String multirequestToken){
		setToken("ingestStatusIn", multirequestToken);
	}

	// ingestDateGreaterThan:
	public Long getIngestDateGreaterThan(){
		return this.ingestDateGreaterThan;
	}
	public void setIngestDateGreaterThan(Long ingestDateGreaterThan){
		this.ingestDateGreaterThan = ingestDateGreaterThan;
	}

	public void ingestDateGreaterThan(String multirequestToken){
		setToken("ingestDateGreaterThan", multirequestToken);
	}

	// ingestDateSmallerThan:
	public Long getIngestDateSmallerThan(){
		return this.ingestDateSmallerThan;
	}
	public void setIngestDateSmallerThan(Long ingestDateSmallerThan){
		this.ingestDateSmallerThan = ingestDateSmallerThan;
	}

	public void ingestDateSmallerThan(String multirequestToken){
		setToken("ingestDateSmallerThan", multirequestToken);
	}

	// vodTypeSystemNameIn:
	public String getVodTypeSystemNameIn(){
		return this.vodTypeSystemNameIn;
	}
	public void setVodTypeSystemNameIn(String vodTypeSystemNameIn){
		this.vodTypeSystemNameIn = vodTypeSystemNameIn;
	}

	public void vodTypeSystemNameIn(String multirequestToken){
		setToken("vodTypeSystemNameIn", multirequestToken);
	}

	// shopAssetUserRuleIdIn:
	public String getShopAssetUserRuleIdIn(){
		return this.shopAssetUserRuleIdIn;
	}
	public void setShopAssetUserRuleIdIn(String shopAssetUserRuleIdIn){
		this.shopAssetUserRuleIdIn = shopAssetUserRuleIdIn;
	}

	public void shopAssetUserRuleIdIn(String multirequestToken){
		setToken("shopAssetUserRuleIdIn", multirequestToken);
	}


	public VodIngestAssetResultFilter() {
		super();
	}

	public VodIngestAssetResultFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		fileNameContains = GsonParser.parseString(jsonObject.get("fileNameContains"));
		assetNameContains = GsonParser.parseString(jsonObject.get("assetNameContains"));
		ingestStatusIn = GsonParser.parseString(jsonObject.get("ingestStatusIn"));
		ingestDateGreaterThan = GsonParser.parseLong(jsonObject.get("ingestDateGreaterThan"));
		ingestDateSmallerThan = GsonParser.parseLong(jsonObject.get("ingestDateSmallerThan"));
		vodTypeSystemNameIn = GsonParser.parseString(jsonObject.get("vodTypeSystemNameIn"));
		shopAssetUserRuleIdIn = GsonParser.parseString(jsonObject.get("shopAssetUserRuleIdIn"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaVodIngestAssetResultFilter");
		kparams.add("fileNameContains", this.fileNameContains);
		kparams.add("assetNameContains", this.assetNameContains);
		kparams.add("ingestStatusIn", this.ingestStatusIn);
		kparams.add("ingestDateGreaterThan", this.ingestDateGreaterThan);
		kparams.add("ingestDateSmallerThan", this.ingestDateSmallerThan);
		kparams.add("vodTypeSystemNameIn", this.vodTypeSystemNameIn);
		kparams.add("shopAssetUserRuleIdIn", this.shopAssetUserRuleIdIn);
		return kparams;
	}

}

