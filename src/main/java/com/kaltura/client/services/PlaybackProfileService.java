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
// Copyright (C) 2006-2019  Kaltura Inc.
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

import com.kaltura.client.types.PlaybackProfile;
import com.kaltura.client.types.PlaybackProfileFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class PlaybackProfileService {
	
	public static class AddPlaybackProfileBuilder extends RequestBuilder<PlaybackProfile, PlaybackProfile.Tokenizer, AddPlaybackProfileBuilder> {
		
		public AddPlaybackProfileBuilder(PlaybackProfile playbackProfile) {
			super(PlaybackProfile.class, "playbackprofile", "add");
			params.add("playbackProfile", playbackProfile);
		}
	}

	/**
	 * Insert new Playback adapter for partner
	 * 
	 * @param playbackProfile Playback adapter Object
	 */
    public static AddPlaybackProfileBuilder add(PlaybackProfile playbackProfile)  {
		return new AddPlaybackProfileBuilder(playbackProfile);
	}
	
	public static class DeletePlaybackProfileBuilder extends RequestBuilder<Boolean, String, DeletePlaybackProfileBuilder> {
		
		public DeletePlaybackProfileBuilder(int id) {
			super(Boolean.class, "playbackprofile", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete Playback adapter by Playback adapter id
	 * 
	 * @param id Playback adapter identifier
	 */
    public static DeletePlaybackProfileBuilder delete(int id)  {
		return new DeletePlaybackProfileBuilder(id);
	}
	
	public static class GenerateSharedSecretPlaybackProfileBuilder extends RequestBuilder<PlaybackProfile, PlaybackProfile.Tokenizer, GenerateSharedSecretPlaybackProfileBuilder> {
		
		public GenerateSharedSecretPlaybackProfileBuilder(int id) {
			super(PlaybackProfile.class, "playbackprofile", "generateSharedSecret");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Generate playback adapter shared secret
	 * 
	 * @param id Playback adapter identifier
	 */
    public static GenerateSharedSecretPlaybackProfileBuilder generateSharedSecret(int id)  {
		return new GenerateSharedSecretPlaybackProfileBuilder(id);
	}
	
	public static class ListPlaybackProfileBuilder extends ListResponseRequestBuilder<PlaybackProfile, PlaybackProfile.Tokenizer, ListPlaybackProfileBuilder> {
		
		public ListPlaybackProfileBuilder(PlaybackProfileFilter filter) {
			super(PlaybackProfile.class, "playbackprofile", "list");
			params.add("filter", filter);
		}
	}

	public static ListPlaybackProfileBuilder list()  {
		return list(null);
	}

	/**
	 * Returns all playback profiles for partner : id + name
	 * 
	 * @param filter Filter parameters for filtering out the result
	 */
    public static ListPlaybackProfileBuilder list(PlaybackProfileFilter filter)  {
		return new ListPlaybackProfileBuilder(filter);
	}
	
	public static class UpdatePlaybackProfileBuilder extends RequestBuilder<PlaybackProfile, PlaybackProfile.Tokenizer, UpdatePlaybackProfileBuilder> {
		
		public UpdatePlaybackProfileBuilder(int id, PlaybackProfile playbackProfile) {
			super(PlaybackProfile.class, "playbackprofile", "update");
			params.add("id", id);
			params.add("playbackProfile", playbackProfile);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update Playback adapter details
	 * 
	 * @param id Playback adapter identifier
	 * @param playbackProfile Playback adapter Object
	 */
    public static UpdatePlaybackProfileBuilder update(int id, PlaybackProfile playbackProfile)  {
		return new UpdatePlaybackProfileBuilder(id, playbackProfile);
	}
}
