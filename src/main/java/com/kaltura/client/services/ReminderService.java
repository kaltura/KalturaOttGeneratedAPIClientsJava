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
// Copyright (C) 2006-2017  Kaltura Inc.
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

import com.kaltura.client.Params;
import com.kaltura.client.enums.ReminderType;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.Reminder;
import com.kaltura.client.types.ReminderFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class ReminderService {

	/**  Add a new future reminder  */
    public static RequestBuilder<Reminder> add(Reminder reminder)  {
        Params kparams = new Params();
        kparams.add("reminder", reminder);

        return new RequestBuilder<Reminder>(Reminder.class, "reminder", "add", kparams);
    }

	/**  Delete a reminder. Reminder cannot be delete while being sent.  */
    public static RequestBuilder<Boolean> delete(long id, ReminderType type)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("type", type);

        return new RequestBuilder<Boolean>(Boolean.class, "reminder", "delete", kparams);
    }

    public static RequestBuilder<ListResponse<Reminder>> list(ReminderFilter filter)  {
        return list(filter, null);
    }

	/**  Return a list of reminders with optional filter by KSQL.  */
    public static RequestBuilder<ListResponse<Reminder>> list(ReminderFilter filter, FilterPager pager)  {
        Params kparams = new Params();
        kparams.add("filter", filter);
        kparams.add("pager", pager);

        return new ListResponseRequestBuilder<Reminder>(Reminder.class, "reminder", "list", kparams);
    }
}
