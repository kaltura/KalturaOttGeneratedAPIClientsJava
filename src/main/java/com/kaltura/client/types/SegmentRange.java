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
import com.kaltura.client.types.ObjectBase;
import com.kaltura.client.utils.GsonParser;
import com.kaltura.client.utils.request.MultiRequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**
 * Segment that is based on a range of values
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SegmentRange.Tokenizer.class)
public class SegmentRange extends ObjectBase {
	
	public interface Tokenizer extends ObjectBase.Tokenizer {
		String id();
		String systematicName();
		String name();
		String gte();
		String gt();
		String lte();
		String lt();
		String equals();
	}

	/**
	 * Id of segment
	 */
	private Long id;
	/**
	 * Systematic name of segment
	 */
	private String systematicName;
	/**
	 * Specific segment name
	 */
	private String name;
	/**
	 * Greater than or equals &amp;gt;=
	 */
	private Double gte;
	/**
	 * Greater than &amp;gt;
	 */
	private Double gt;
	/**
	 * Less than or equals
	 */
	private Double lte;
	/**
	 * Less than
	 */
	private Double lt;
	/**
	 * Equals
	 */
	private Double equals;

	// id:
	public Long getId(){
		return this.id;
	}
	// systematicName:
	public String getSystematicName(){
		return this.systematicName;
	}
	public void setSystematicName(String systematicName){
		this.systematicName = systematicName;
	}

	public void systematicName(String multirequestToken){
		setToken("systematicName", multirequestToken);
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

	// gte:
	public Double getGte(){
		return this.gte;
	}
	public void setGte(Double gte){
		this.gte = gte;
	}

	public void gte(String multirequestToken){
		setToken("gte", multirequestToken);
	}

	// gt:
	public Double getGt(){
		return this.gt;
	}
	public void setGt(Double gt){
		this.gt = gt;
	}

	public void gt(String multirequestToken){
		setToken("gt", multirequestToken);
	}

	// lte:
	public Double getLte(){
		return this.lte;
	}
	public void setLte(Double lte){
		this.lte = lte;
	}

	public void lte(String multirequestToken){
		setToken("lte", multirequestToken);
	}

	// lt:
	public Double getLt(){
		return this.lt;
	}
	public void setLt(Double lt){
		this.lt = lt;
	}

	public void lt(String multirequestToken){
		setToken("lt", multirequestToken);
	}

	// equals:
	public Double getEquals(){
		return this.equals;
	}
	public void setEquals(Double equals){
		this.equals = equals;
	}

	public void equals(String multirequestToken){
		setToken("equals", multirequestToken);
	}


	public SegmentRange() {
		super();
	}

	public SegmentRange(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		id = GsonParser.parseLong(jsonObject.get("id"));
		systematicName = GsonParser.parseString(jsonObject.get("systematicName"));
		name = GsonParser.parseString(jsonObject.get("name"));
		gte = GsonParser.parseDouble(jsonObject.get("gte"));
		gt = GsonParser.parseDouble(jsonObject.get("gt"));
		lte = GsonParser.parseDouble(jsonObject.get("lte"));
		lt = GsonParser.parseDouble(jsonObject.get("lt"));
		equals = GsonParser.parseDouble(jsonObject.get("equals"));

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSegmentRange");
		kparams.add("systematicName", this.systematicName);
		kparams.add("name", this.name);
		kparams.add("gte", this.gte);
		kparams.add("gt", this.gt);
		kparams.add("lte", this.lte);
		kparams.add("lt", this.lt);
		kparams.add("equals", this.equals);
		return kparams;
	}

}

