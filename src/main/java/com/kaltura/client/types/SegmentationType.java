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
import com.kaltura.client.enums.BooleanOperator;
import com.kaltura.client.types.BaseSegmentValue;
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
 * Segmentation type - defines at least one segment
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SegmentationType.Tokenizer.class)
public class SegmentationType extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String name();
		String description();
		RequestBuilder.ListTokenizer<BaseSegmentCondition.Tokenizer> conditions();
		String conditionsOperator();
		RequestBuilder.ListTokenizer<BaseSegmentAction.Tokenizer> actions();
		BaseSegmentValue.Tokenizer value();
		String createDate();
		String updateDate();
		String executeDate();
		String version();
		String assetUserRuleId();
	}

	/**
	 * Id of segmentation type
	 */
	private Long id;
	/**
	 * Name of segmentation type
	 */
	private String name;
	/**
	 * Description of segmentation type
	 */
	private String description;
	/**
	 * Segmentation conditions - can be empty
	 */
	private List<BaseSegmentCondition> conditions;
	/**
	 * Boolean operator between segmentation type&amp;#39;s conditions - defaults to
	  &amp;quot;And&amp;quot;
	 */
	private BooleanOperator conditionsOperator;
	/**
	 * Segmentation conditions - can be empty
	 */
	private List<BaseSegmentAction> actions;
	/**
	 * Segmentation values - can be empty (so only one segment will be created)
	 */
	private BaseSegmentValue value;
	/**
	 * Create date of segmentation type
	 */
	private Long createDate;
	/**
	 * Update date of segmentation type
	 */
	private Long updateDate;
	/**
	 * Last date of execution of segmentation type
	 */
	private Long executeDate;
	/**
	 * Segmentation type version
	 */
	private Long version;
	/**
	 * Asset User Rule Id
	 */
	private Long assetUserRuleId;

	// id:
	public Long getId(){
		return this.id;
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

	// conditions:
	public List<BaseSegmentCondition> getConditions(){
		return this.conditions;
	}
	public void setConditions(List<BaseSegmentCondition> conditions){
		this.conditions = conditions;
	}

	// conditionsOperator:
	public BooleanOperator getConditionsOperator(){
		return this.conditionsOperator;
	}
	public void setConditionsOperator(BooleanOperator conditionsOperator){
		this.conditionsOperator = conditionsOperator;
	}

	public void conditionsOperator(String multirequestToken){
		setToken("conditionsOperator", multirequestToken);
	}

	// actions:
	public List<BaseSegmentAction> getActions(){
		return this.actions;
	}
	public void setActions(List<BaseSegmentAction> actions){
		this.actions = actions;
	}

	// value:
	public BaseSegmentValue getValue(){
		return this.value;
	}
	public void setValue(BaseSegmentValue value){
		this.value = value;
	}

	// createDate:
	public Long getCreateDate(){
		return this.createDate;
	}
	// updateDate:
	public Long getUpdateDate(){
		return this.updateDate;
	}
	// executeDate:
	public Long getExecuteDate(){
		return this.executeDate;
	}
	// version:
	public Long getVersion(){
		return this.version;
	}
	// assetUserRuleId:
	public Long getAssetUserRuleId(){
		return this.assetUserRuleId;
	}
	public void setAssetUserRuleId(Long assetUserRuleId){
		this.assetUserRuleId = assetUserRuleId;
	}

	public void assetUserRuleId(String multirequestToken){
		setToken("assetUserRuleId", multirequestToken);
	}


	public SegmentationType() {
		super();
	}

	public SegmentationType(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		name = GsonParser.parseString(jsonObject.get("name"));
		description = GsonParser.parseString(jsonObject.get("description"));
		conditions = GsonParser.parseArray(jsonObject.getAsJsonArray("conditions"), BaseSegmentCondition.class);
		conditionsOperator = BooleanOperator.get(GsonParser.parseString(jsonObject.get("conditionsOperator")));
		actions = GsonParser.parseArray(jsonObject.getAsJsonArray("actions"), BaseSegmentAction.class);
		value = GsonParser.parseObject(jsonObject.getAsJsonObject("value"), BaseSegmentValue.class);
		createDate = GsonParser.parseLong(jsonObject.get("createDate"));
		updateDate = GsonParser.parseLong(jsonObject.get("updateDate"));
		executeDate = GsonParser.parseLong(jsonObject.get("executeDate"));
		version = GsonParser.parseLong(jsonObject.get("version"));
		assetUserRuleId = GsonParser.parseLong(jsonObject.get("assetUserRuleId"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSegmentationType");
		kparams.add("name", this.name);
		kparams.add("description", this.description);
		kparams.add("conditions", this.conditions);
		kparams.add("conditionsOperator", this.conditionsOperator);
		kparams.add("actions", this.actions);
		kparams.add("value", this.value);
		kparams.add("assetUserRuleId", this.assetUserRuleId);
		return kparams;
	}

}

