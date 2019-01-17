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
import com.kaltura.client.types.SegmentSource;
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

/**
 * Segmentation type that takes different ranges as segments
 */
@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(SegmentRanges.Tokenizer.class)
public class SegmentRanges extends BaseSegmentValue {
	
	public interface Tokenizer extends BaseSegmentValue.Tokenizer {
		SegmentSource.Tokenizer source();
		RequestBuilder.ListTokenizer<SegmentRange.Tokenizer> ranges();
	}

	/**
	 * Range source
	 */
	private SegmentSource source;
	/**
	 * List of ranges for segmentation
	 */
	private List<SegmentRange> ranges;

	// source:
	public SegmentSource getSource(){
		return this.source;
	}
	public void setSource(SegmentSource source){
		this.source = source;
	}

	// ranges:
	public List<SegmentRange> getRanges(){
		return this.ranges;
	}
	public void setRanges(List<SegmentRange> ranges){
		this.ranges = ranges;
	}


	public SegmentRanges() {
		super();
	}

	public SegmentRanges(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		source = GsonParser.parseObject(jsonObject.getAsJsonObject("source"), SegmentSource.class);
		ranges = GsonParser.parseArray(jsonObject.getAsJsonArray("ranges"), SegmentRange.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaSegmentRanges");
		kparams.add("source", this.source);
		kparams.add("ranges", this.ranges);
		return kparams;
	}

}

