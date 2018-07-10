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

import com.kaltura.client.types.Channel;
import com.kaltura.client.types.ChannelsFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ChannelService {
	
	public static class AddChannelBuilder extends RequestBuilder<Channel, Channel.Tokenizer, AddChannelBuilder> {
		
		public AddChannelBuilder(Channel channel) {
			super(Channel.class, "channel", "add");
			params.add("channel", channel);
		}
	}

	/**
	 * Insert new channel for partner. Supports KalturaDynamicChannel or
	  KalturaManualChannel
	 * 
	 * @param channel KSQL channel Object
	 */
    public static AddChannelBuilder add(Channel channel)  {
		return new AddChannelBuilder(channel);
	}
	
	public static class DeleteChannelBuilder extends RequestBuilder<Boolean, String, DeleteChannelBuilder> {
		
		public DeleteChannelBuilder(int channelId) {
			super(Boolean.class, "channel", "delete");
			params.add("channelId", channelId);
		}
		
		public void channelId(String multirequestToken) {
			params.add("channelId", multirequestToken);
		}
	}

	/**
	 * Delete channel by its channel id
	 * 
	 * @param channelId channel identifier
	 */
    public static DeleteChannelBuilder delete(int channelId)  {
		return new DeleteChannelBuilder(channelId);
	}
	
	public static class GetChannelBuilder extends RequestBuilder<Channel, Channel.Tokenizer, GetChannelBuilder> {
		
		public GetChannelBuilder(int id) {
			super(Channel.class, "channel", "get");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns channel
	 * 
	 * @param id Channel Identifier
	 */
    public static GetChannelBuilder get(int id)  {
		return new GetChannelBuilder(id);
	}
	
	public static class ListChannelBuilder extends ListResponseRequestBuilder<Channel, Channel.Tokenizer, ListChannelBuilder> {
		
		public ListChannelBuilder(ChannelsFilter filter, FilterPager pager) {
			super(Channel.class, "channel", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListChannelBuilder list()  {
		return list(null);
	}

	public static ListChannelBuilder list(ChannelsFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Get the list of tags for the partner
	 * 
	 * @param filter Filter
	 * @param pager Page size and index
	 */
    public static ListChannelBuilder list(ChannelsFilter filter, FilterPager pager)  {
		return new ListChannelBuilder(filter, pager);
	}
	
	public static class UpdateChannelBuilder extends RequestBuilder<Channel, Channel.Tokenizer, UpdateChannelBuilder> {
		
		public UpdateChannelBuilder(int id, Channel channel) {
			super(Channel.class, "channel", "update");
			params.add("id", id);
			params.add("channel", channel);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update channel details. Supports KalturaDynamicChannel or KalturaManualChannel
	 * 
	 * @param id Channel identifier
	 * @param channel KSQL channel Object
	 */
    public static UpdateChannelBuilder update(int id, Channel channel)  {
		return new UpdateChannelBuilder(id, channel);
	}
}
