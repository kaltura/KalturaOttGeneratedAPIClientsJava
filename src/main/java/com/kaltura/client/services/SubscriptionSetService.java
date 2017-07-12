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
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.SubscriptionSet;
import com.kaltura.client.types.SubscriptionSetFilter;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class SubscriptionSetService {

	/**  Add a new subscriptionSet  */
    public static RequestBuilder<SubscriptionSet> add(SubscriptionSet subscriptionSet)  {
        Params kparams = new Params();
        kparams.add("subscriptionSet", subscriptionSet);

        return new RequestBuilder<SubscriptionSet>(SubscriptionSet.class, "subscriptionset", "add", kparams);
    }

	/**  Delete a subscriptionSet  */
    public static RequestBuilder<Boolean> delete(long id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<Boolean>(Boolean.class, "subscriptionset", "delete", kparams);
    }

	/**  Get the subscriptionSet according to the Identifier  */
    public static RequestBuilder<SubscriptionSet> get(long id)  {
        Params kparams = new Params();
        kparams.add("id", id);

        return new RequestBuilder<SubscriptionSet>(SubscriptionSet.class, "subscriptionset", "get", kparams);
    }

    public static RequestBuilder<ListResponse<SubscriptionSet>> list()  {
        return list(null);
    }

	/**  Returns a list of subscriptionSets requested by ids or subscription ids  */
    public static RequestBuilder<ListResponse<SubscriptionSet>> list(SubscriptionSetFilter filter)  {
        Params kparams = new Params();
        kparams.add("filter", filter);

        return new ListResponseRequestBuilder<SubscriptionSet>(SubscriptionSet.class, "subscriptionset", "list", kparams);
    }

	/**  Update the subscriptionSet  */
    public static RequestBuilder<SubscriptionSet> update(long id, SubscriptionSet subscriptionSet)  {
        Params kparams = new Params();
        kparams.add("id", id);
        kparams.add("subscriptionSet", subscriptionSet);

        return new RequestBuilder<SubscriptionSet>(SubscriptionSet.class, "subscriptionset", "update", kparams);
    }
}
