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
import com.kaltura.client.types.IngestEpgDetailsAggregation;
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

@SuppressWarnings("serial")
@MultiRequestBuilder.Tokenizer(IngestEpgDetails.Tokenizer.class)
public class IngestEpgDetails extends IngestEpg {
	
	public interface Tokenizer extends IngestEpg.Tokenizer {
		RequestBuilder.ListTokenizer<EpgIngestErrorMessage.Tokenizer> errors();
		IngestEpgDetailsAggregation.Tokenizer aggregations();
	}

	/**
	 * Errors
	 */
	private List<EpgIngestErrorMessage> errors;
	/**
	 * Aggregated counters
	 */
	private IngestEpgDetailsAggregation aggregations;

	// errors:
	public List<EpgIngestErrorMessage> getErrors(){
		return this.errors;
	}
	public void setErrors(List<EpgIngestErrorMessage> errors){
		this.errors = errors;
	}

	// aggregations:
	public IngestEpgDetailsAggregation getAggregations(){
		return this.aggregations;
	}
	public void setAggregations(IngestEpgDetailsAggregation aggregations){
		this.aggregations = aggregations;
	}


	public IngestEpgDetails() {
		super();
	}

	public IngestEpgDetails(JsonObject jsonObject) throws APIException {
		super(jsonObject);

		if(jsonObject == null) return;

		// set members values:
		errors = GsonParser.parseArray(jsonObject.getAsJsonArray("errors"), EpgIngestErrorMessage.class);
		aggregations = GsonParser.parseObject(jsonObject.getAsJsonObject("aggregations"), IngestEpgDetailsAggregation.class);

	}

	public Params toParams() {
		Params kparams = super.toParams();
		kparams.add("objectType", "KalturaIngestEpgDetails");
		kparams.add("errors", this.errors);
		kparams.add("aggregations", this.aggregations);
		return kparams;
	}

}

