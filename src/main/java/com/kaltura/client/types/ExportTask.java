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
import com.kaltura.client.enums.ExportDataType;
import com.kaltura.client.enums.ExportType;
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Bulk export task
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(ExportTask.Tokenizer.class)
public class ExportTask extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String alias();
		String name();
		String dataType();
		String filter();
		String exportType();
		String frequency();
		String notificationUrl();
		String isActive();
	}

	/**
	 * Task identifier
	 */
	private Long id;
	/**
	 * Alias for the task used to solicit an export using API
	 */
	private String alias;
	/**
	 * Task display name
	 */
	private String name;
	/**
	 * The data type exported in this task
	 */
	private ExportDataType dataType;
	/**
	 * Filter to apply on the export, utilize KSQL.              Note: KSQL currently
	  applies to media assets only. It cannot be used for USERS filtering
	 */
	private String filter;
	/**
	 * Type of export batch – full or incremental
	 */
	private ExportType exportType;
	/**
	 * How often the export should occur, reasonable minimum threshold should apply,
	  configurable in minutes
	 */
	private Long frequency;
	/**
	 * The URL for sending a notification when the task&amp;#39;s export process is
	  done
	 */
	private String notificationUrl;
	/**
	 * Indicates if the task is active or not
	 */
	private Boolean isActive;

	// id:
	public Long getId(){
		return this.id;
	}
	// alias:
	public String getAlias(){
		return this.alias;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}

	public void alias(String multirequestToken){
		setToken("alias", multirequestToken);
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

	// dataType:
	public ExportDataType getDataType(){
		return this.dataType;
	}
	public void setDataType(ExportDataType dataType){
		this.dataType = dataType;
	}

	public void dataType(String multirequestToken){
		setToken("dataType", multirequestToken);
	}

	// filter:
	public String getFilter(){
		return this.filter;
	}
	public void setFilter(String filter){
		this.filter = filter;
	}

	public void filter(String multirequestToken){
		setToken("filter", multirequestToken);
	}

	// exportType:
	public ExportType getExportType(){
		return this.exportType;
	}
	public void setExportType(ExportType exportType){
		this.exportType = exportType;
	}

	public void exportType(String multirequestToken){
		setToken("exportType", multirequestToken);
	}

	// frequency:
	public Long getFrequency(){
		return this.frequency;
	}
	public void setFrequency(Long frequency){
		this.frequency = frequency;
	}

	public void frequency(String multirequestToken){
		setToken("frequency", multirequestToken);
	}

	// notificationUrl:
	public String getNotificationUrl(){
		return this.notificationUrl;
	}
	public void setNotificationUrl(String notificationUrl){
		this.notificationUrl = notificationUrl;
	}

	public void notificationUrl(String multirequestToken){
		setToken("notificationUrl", multirequestToken);
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


	public ExportTask() {
		super();
	}

	public ExportTask(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		alias = GsonParser.parseString(jsonObject.get("alias"));
		name = GsonParser.parseString(jsonObject.get("name"));
		dataType = ExportDataType.get(GsonParser.parseString(jsonObject.get("dataType")));
		filter = GsonParser.parseString(jsonObject.get("filter"));
		exportType = ExportType.get(GsonParser.parseString(jsonObject.get("exportType")));
		frequency = GsonParser.parseLong(jsonObject.get("frequency"));
		notificationUrl = GsonParser.parseString(jsonObject.get("notificationUrl"));
		isActive = GsonParser.parseBoolean(jsonObject.get("isActive"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaExportTask");
		kparams.add("alias", this.alias);
		kparams.add("name", this.name);
		kparams.add("dataType", this.dataType);
		kparams.add("filter", this.filter);
		kparams.add("exportType", this.exportType);
		kparams.add("frequency", this.frequency);
		kparams.add("notificationUrl", this.notificationUrl);
		kparams.add("isActive", this.isActive);
		return kparams;
	}

}

