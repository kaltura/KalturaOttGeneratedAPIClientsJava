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
package com.kaltura.client.services;

import com.kaltura.client.types.SeriesRecording;
import com.kaltura.client.types.SeriesRecordingFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SeriesRecordingService {
	
	public static class AddSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, AddSeriesRecordingBuilder> {
		
		public AddSeriesRecordingBuilder(SeriesRecording recording) {
			super(SeriesRecording.class, "seriesrecording", "add");
			params.add("recording", recording);
		}
	}

	/**
	 * Issue a record request for a complete season or series
	 * 
	 * @param recording SeriesRecording Object
	 */
    public static AddSeriesRecordingBuilder add(SeriesRecording recording)  {
		return new AddSeriesRecordingBuilder(recording);
	}
	
	public static class CancelSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, CancelSeriesRecordingBuilder> {
		
		public CancelSeriesRecordingBuilder(long id) {
			super(SeriesRecording.class, "seriesrecording", "cancel");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Cancel a previously requested series recording. Cancel series recording can be
	  called for recording in status Scheduled or Recording Only
	 * 
	 * @param id Series Recording identifier
	 */
    public static CancelSeriesRecordingBuilder cancel(long id)  {
		return new CancelSeriesRecordingBuilder(id);
	}
	
	public static class CancelByEpgIdSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, CancelByEpgIdSeriesRecordingBuilder> {
		
		public CancelByEpgIdSeriesRecordingBuilder(long id, long epgId) {
			super(SeriesRecording.class, "seriesrecording", "cancelByEpgId");
			params.add("id", id);
			params.add("epgId", epgId);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void epgId(String multirequestToken) {
			params.add("epgId", multirequestToken);
		}
	}

	/**
	 * Cancel EPG recording that was recorded as part of series
	 * 
	 * @param id Series Recording identifier
	 * @param epgId epg program identifier
	 */
    public static CancelByEpgIdSeriesRecordingBuilder cancelByEpgId(long id, long epgId)  {
		return new CancelByEpgIdSeriesRecordingBuilder(id, epgId);
	}
	
	public static class CancelBySeasonNumberSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, CancelBySeasonNumberSeriesRecordingBuilder> {
		
		public CancelBySeasonNumberSeriesRecordingBuilder(long id, long seasonNumber) {
			super(SeriesRecording.class, "seriesrecording", "cancelBySeasonNumber");
			params.add("id", id);
			params.add("seasonNumber", seasonNumber);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void seasonNumber(String multirequestToken) {
			params.add("seasonNumber", multirequestToken);
		}
	}

	/**
	 * Cancel Season recording epgs that was recorded as part of series
	 * 
	 * @param id Series Recording identifier
	 * @param seasonNumber Season Number
	 */
    public static CancelBySeasonNumberSeriesRecordingBuilder cancelBySeasonNumber(long id, long seasonNumber)  {
		return new CancelBySeasonNumberSeriesRecordingBuilder(id, seasonNumber);
	}
	
	public static class DeleteSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, DeleteSeriesRecordingBuilder> {
		
		public DeleteSeriesRecordingBuilder(long id) {
			super(SeriesRecording.class, "seriesrecording", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete series recording(s). Delete series recording can be called recordings in
	  any status
	 * 
	 * @param id Series Recording identifier
	 */
    public static DeleteSeriesRecordingBuilder delete(long id)  {
		return new DeleteSeriesRecordingBuilder(id);
	}
	
	public static class DeleteBySeasonNumberSeriesRecordingBuilder extends RequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, DeleteBySeasonNumberSeriesRecordingBuilder> {
		
		public DeleteBySeasonNumberSeriesRecordingBuilder(long id, int seasonNumber) {
			super(SeriesRecording.class, "seriesrecording", "deleteBySeasonNumber");
			params.add("id", id);
			params.add("seasonNumber", seasonNumber);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void seasonNumber(String multirequestToken) {
			params.add("seasonNumber", multirequestToken);
		}
	}

	/**
	 * Delete Season recording epgs that was recorded as part of series
	 * 
	 * @param id Series Recording identifier
	 * @param seasonNumber Season Number
	 */
    public static DeleteBySeasonNumberSeriesRecordingBuilder deleteBySeasonNumber(long id, int seasonNumber)  {
		return new DeleteBySeasonNumberSeriesRecordingBuilder(id, seasonNumber);
	}
	
	public static class ListSeriesRecordingBuilder extends ListResponseRequestBuilder<SeriesRecording, SeriesRecording.Tokenizer, ListSeriesRecordingBuilder> {
		
		public ListSeriesRecordingBuilder(SeriesRecordingFilter filter) {
			super(SeriesRecording.class, "seriesrecording", "list");
			params.add("filter", filter);
		}
	}

	public static ListSeriesRecordingBuilder list()  {
		return list(null);
	}

	/**
	 * Return a list of series recordings for the household with optional filter by
	  status and KSQL.
	 * 
	 * @param filter Filter parameters for filtering out the result - support order by only -
	 * START_DATE_ASC, START_DATE_DESC, ID_ASC,ID_DESC,SERIES_ID_ASC, SERIES_ID_DESC
	 */
    public static ListSeriesRecordingBuilder list(SeriesRecordingFilter filter)  {
		return new ListSeriesRecordingBuilder(filter);
	}
}
