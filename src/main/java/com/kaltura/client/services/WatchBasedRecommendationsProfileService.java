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
package com.kaltura.client.services;

import com.kaltura.client.types.WatchBasedRecommendationsProfile;
import com.kaltura.client.types.WatchBasedRecommendationsProfileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class WatchBasedRecommendationsProfileService {
	
	public static class AddWatchBasedRecommendationsProfileBuilder extends RequestBuilder<WatchBasedRecommendationsProfile, WatchBasedRecommendationsProfile.Tokenizer, AddWatchBasedRecommendationsProfileBuilder> {
		
		public AddWatchBasedRecommendationsProfileBuilder(WatchBasedRecommendationsProfile profile) {
			super(WatchBasedRecommendationsProfile.class, "watchbasedrecommendationsprofile", "add");
			params.add("profile", profile);
		}
	}

	/**
	 * Add partner&amp;#39;s watch based recommendations profile.
	 * 
	 * @param profile watch based recommendations profile to add
	 */
    public static AddWatchBasedRecommendationsProfileBuilder add(WatchBasedRecommendationsProfile profile)  {
		return new AddWatchBasedRecommendationsProfileBuilder(profile);
	}
	
	public static class DeleteWatchBasedRecommendationsProfileBuilder extends NullRequestBuilder {
		
		public DeleteWatchBasedRecommendationsProfileBuilder(long id) {
			super("watchbasedrecommendationsprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete partner&amp;#39;s watch based recommendations profile.
	 * 
	 * @param id profile id to update
	 */
    public static DeleteWatchBasedRecommendationsProfileBuilder delete(long id)  {
		return new DeleteWatchBasedRecommendationsProfileBuilder(id);
	}
	
	public static class DeleteWatchBasedRecommendationsOfProfileWatchBasedRecommendationsProfileBuilder extends NullRequestBuilder {
		
		public DeleteWatchBasedRecommendationsOfProfileWatchBasedRecommendationsProfileBuilder(long id) {
			super("watchbasedrecommendationsprofile", "deleteWatchBasedRecommendationsOfProfile");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete all recommendations that were calculated based on specific profile.
	 * 
	 * @param id profile id
	 */
    public static DeleteWatchBasedRecommendationsOfProfileWatchBasedRecommendationsProfileBuilder deleteWatchBasedRecommendationsOfProfile(long id)  {
		return new DeleteWatchBasedRecommendationsOfProfileWatchBasedRecommendationsProfileBuilder(id);
	}
	
	public static class ListWatchBasedRecommendationsProfileBuilder extends ListResponseRequestBuilder<WatchBasedRecommendationsProfile, WatchBasedRecommendationsProfile.Tokenizer, ListWatchBasedRecommendationsProfileBuilder> {
		
		public ListWatchBasedRecommendationsProfileBuilder(WatchBasedRecommendationsProfileFilter filter) {
			super(WatchBasedRecommendationsProfile.class, "watchbasedrecommendationsprofile", "list");
			params.add("filter", filter);
		}
	}

	public static ListWatchBasedRecommendationsProfileBuilder list()  {
		return list(null);
	}

	/**
	 * Get partner&amp;#39;s watch based recommendations profiles.
	 * 
	 * @param filter Filtering parameters for watch based recommendations profiles
	 */
    public static ListWatchBasedRecommendationsProfileBuilder list(WatchBasedRecommendationsProfileFilter filter)  {
		return new ListWatchBasedRecommendationsProfileBuilder(filter);
	}
	
	public static class UpdateWatchBasedRecommendationsProfileBuilder extends RequestBuilder<WatchBasedRecommendationsProfile, WatchBasedRecommendationsProfile.Tokenizer, UpdateWatchBasedRecommendationsProfileBuilder> {
		
		public UpdateWatchBasedRecommendationsProfileBuilder(long id, WatchBasedRecommendationsProfile profile) {
			super(WatchBasedRecommendationsProfile.class, "watchbasedrecommendationsprofile", "update");
			params.add("id", id);
			params.add("profile", profile);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update partner&amp;#39;s watch based recommendations profile.
	 * 
	 * @param id profile id to update
	 * @param profile watch based recommendations profile to add
	 */
    public static UpdateWatchBasedRecommendationsProfileBuilder update(long id, WatchBasedRecommendationsProfile profile)  {
		return new UpdateWatchBasedRecommendationsProfileBuilder(id, profile);
	}
}
