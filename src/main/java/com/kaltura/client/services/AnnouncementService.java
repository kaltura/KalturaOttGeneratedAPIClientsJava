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

import com.kaltura.client.types.Announcement;
import com.kaltura.client.types.AnnouncementFilter;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class AnnouncementService {
	
	public static class AddAnnouncementBuilder extends RequestBuilder<Announcement, Announcement.Tokenizer, AddAnnouncementBuilder> {
		
		public AddAnnouncementBuilder(Announcement announcement) {
			super(Announcement.class, "announcement", "add");
			params.add("announcement", announcement);
		}
	}

	/**
	 * Add a new future scheduled system announcement push notification
	 * 
	 * @param announcement The announcement to be added.
	 *             timezone parameter should be taken from the 'name of timezone' from:
	 * https://msdn.microsoft.com/en-us/library/ms912391(v=winembedded.11).aspx
	 *             Recipients values: All, LoggedIn, Guests
	 */
    public static AddAnnouncementBuilder add(Announcement announcement)  {
		return new AddAnnouncementBuilder(announcement);
	}
	
	public static class DeleteAnnouncementBuilder extends RequestBuilder<Boolean, String, DeleteAnnouncementBuilder> {
		
		public DeleteAnnouncementBuilder(long id) {
			super(Boolean.class, "announcement", "delete");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Delete an existing announcing. Announcement cannot be delete while being sent.
	 * 
	 * @param id Id of the announcement.
	 */
    public static DeleteAnnouncementBuilder delete(long id)  {
		return new DeleteAnnouncementBuilder(id);
	}
	
	public static class EnableSystemAnnouncementsAnnouncementBuilder extends RequestBuilder<Boolean, String, EnableSystemAnnouncementsAnnouncementBuilder> {
		
		public EnableSystemAnnouncementsAnnouncementBuilder() {
			super(Boolean.class, "announcement", "enableSystemAnnouncements");
		}
	}

	/**
	 * Enable system announcements
	 */
    public static EnableSystemAnnouncementsAnnouncementBuilder enableSystemAnnouncements()  {
		return new EnableSystemAnnouncementsAnnouncementBuilder();
	}
	
	public static class ListAnnouncementBuilder extends ListResponseRequestBuilder<Announcement, Announcement.Tokenizer, ListAnnouncementBuilder> {
		
		public ListAnnouncementBuilder(AnnouncementFilter filter, FilterPager pager) {
			super(Announcement.class, "announcement", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListAnnouncementBuilder list(AnnouncementFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Lists all announcements in the system.
	 * 
	 * @param filter Filter object
	 * @param pager Paging the request
	 */
    public static ListAnnouncementBuilder list(AnnouncementFilter filter, FilterPager pager)  {
		return new ListAnnouncementBuilder(filter, pager);
	}
	
	public static class UpdateAnnouncementBuilder extends RequestBuilder<Announcement, Announcement.Tokenizer, UpdateAnnouncementBuilder> {
		
		public UpdateAnnouncementBuilder(int announcementId, Announcement announcement) {
			super(Announcement.class, "announcement", "update");
			params.add("announcementId", announcementId);
			params.add("announcement", announcement);
		}
		
		public void announcementId(String multirequestToken) {
			params.add("announcementId", multirequestToken);
		}
	}

	/**
	 * Update an existing future system announcement push notification. Announcement
	  can only be updated only before sending
	 * 
	 * @param announcementId The announcement identifier
	 * @param announcement The announcement to update.
	 *             timezone parameter should be taken from the 'name of timezone' from:
	 * https://msdn.microsoft.com/en-us/library/ms912391(v=winembedded.11).aspx
	 *             Recipients values: All, LoggedIn, Guests
	 */
    public static UpdateAnnouncementBuilder update(int announcementId, Announcement announcement)  {
		return new UpdateAnnouncementBuilder(announcementId, announcement);
	}
	
	public static class UpdateStatusAnnouncementBuilder extends RequestBuilder<Boolean, String, UpdateStatusAnnouncementBuilder> {
		
		public UpdateStatusAnnouncementBuilder(long id, boolean status) {
			super(Boolean.class, "announcement", "updateStatus");
			params.add("id", id);
			params.add("status", status);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
		
		public void status(String multirequestToken) {
			params.add("status", multirequestToken);
		}
	}

	/**
	 * Update a system announcement status
	 * 
	 * @param id Id of the announcement.
	 * @param status Status to update to.
	 */
    public static UpdateStatusAnnouncementBuilder updateStatus(long id, boolean status)  {
		return new UpdateStatusAnnouncementBuilder(id, status);
	}
}
