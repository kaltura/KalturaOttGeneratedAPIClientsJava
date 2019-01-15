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
import com.kaltura.client.enums.GroupByOrder;
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(BaseSearchAssetFilter.Tokenizer.class)
public abstract class BaseSearchAssetFilter extends AssetFilter {
	
	public interface Tokenizer extends AssetFilter.Tokenizer {
		String kSql();
		RequestBuilder.ListTokenizer<AssetGroupBy.Tokenizer> groupBy();
		String groupOrderBy();
	}

	/**
	 * Search assets using dynamic criteria. Provided collection of nested expressions
	  with key, comparison operators, value, and logical conjunction.             
	  Possible keys: any Tag or Meta defined in the system and the following reserved
	  keys: start_date, end_date.               epg_id, media_id - for specific asset
	  IDs.              geo_block - only valid value is &amp;quot;true&amp;quot;: When
	  enabled, only assets that are not restricted to the user by geo-block rules will
	  return.              parental_rules - only valid value is
	  &amp;quot;true&amp;quot;: When enabled, only assets that the user
	  doesn&amp;#39;t need to provide PIN code will return.             
	  user_interests - only valid value is &amp;quot;true&amp;quot;. When enabled,
	  only assets that the user defined as his interests (by tags and metas) will
	  return.              epg_channel_id – the channel identifier of the EPG
	  program.              entitled_assets - valid values: &amp;quot;free&amp;quot;,
	  &amp;quot;entitled&amp;quot;, &amp;quot;not_entitled&amp;quot;,
	  &amp;quot;both&amp;quot;. free - gets only free to watch assets. entitled - only
	  those that the user is implicitly entitled to watch.              asset_type -
	  valid values: &amp;quot;media&amp;quot;, &amp;quot;epg&amp;quot;,
	  &amp;quot;recording&amp;quot; or any number that represents media type in group.
	               Comparison operators: for numerical fields =, &amp;gt;, &amp;gt;=,
	  &amp;lt;, &amp;lt;=, : (in).               For alpha-numerical fields =, !=
	  (not), ~ (like), !~, ^ (any word starts with), ^= (phrase starts with), +
	  (exists), !+ (not exists).              Logical conjunction: and, or.           
	     Search values are limited to 20 characters each for the next operators: ~,
	  !~, ^, ^=              (maximum length of entire filter is 4096 characters)
	 */
	private String kSql;
	/**
	 * groupBy
	 */
	private List<AssetGroupBy> groupBy;
	/**
	 * order by of grouping
	 */
	private GroupByOrder groupOrderBy;

	// kSql:
	public String getKSql(){
		return this.kSql;
	}
	public void setKSql(String kSql){
		this.kSql = kSql;
	}

	public void kSql(String multirequestToken){
		setToken("kSql", multirequestToken);
	}

	// groupBy:
	public List<AssetGroupBy> getGroupBy(){
		return this.groupBy;
	}
	public void setGroupBy(List<AssetGroupBy> groupBy){
		this.groupBy = groupBy;
	}

	// groupOrderBy:
	public GroupByOrder getGroupOrderBy(){
		return this.groupOrderBy;
	}
	public void setGroupOrderBy(GroupByOrder groupOrderBy){
		this.groupOrderBy = groupOrderBy;
	}

	public void groupOrderBy(String multirequestToken){
		setToken("groupOrderBy", multirequestToken);
	}


	public BaseSearchAssetFilter() {
		super();
	}

	public BaseSearchAssetFilter(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		kSql = GsonParser.parseString(jsonObject.get("kSql"));
		groupBy = GsonParser.parseArray(jsonObject.getAsJsonArray("groupBy"), AssetGroupBy.class);
		groupOrderBy = GroupByOrder.get(GsonParser.parseString(jsonObject.get("groupOrderBy")));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaBaseSearchAssetFilter");
		kparams.add("kSql", this.kSql);
		kparams.add("groupBy", this.groupBy);
		kparams.add("groupOrderBy", this.groupOrderBy);
		return kparams;
	}

}

