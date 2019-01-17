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

import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.types.Entitlement;
import com.kaltura.client.types.EntitlementFilter;
import com.kaltura.client.types.EntitlementRenewal;
import com.kaltura.client.types.FilterPager;
import com.kaltura.client.utils.request.ListResponseRequestBuilder;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class EntitlementService {
	
	public static class CancelEntitlementBuilder extends RequestBuilder<Boolean, String, CancelEntitlementBuilder> {
		
		public CancelEntitlementBuilder(int assetId, TransactionType productType) {
			super(Boolean.class, "entitlement", "cancel");
			params.add("assetId", assetId);
			params.add("productType", productType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void productType(String multirequestToken) {
			params.add("productType", multirequestToken);
		}
	}

	/**
	 * Immediately cancel a subscription, PPV or collection. Cancel is possible only if
	  within cancellation window and content not already consumed
	 * 
	 * @param assetId The mediaFileID to cancel
	 * @param productType The product type for the cancelation
	 */
    public static CancelEntitlementBuilder cancel(int assetId, TransactionType productType)  {
		return new CancelEntitlementBuilder(assetId, productType);
	}
	
	public static class CancelRenewalEntitlementBuilder extends NullRequestBuilder {
		
		public CancelRenewalEntitlementBuilder(String subscriptionId) {
			super("entitlement", "cancelRenewal");
			params.add("subscriptionId", subscriptionId);
		}
		
		public void subscriptionId(String multirequestToken) {
			params.add("subscriptionId", multirequestToken);
		}
	}

	/**
	 * Cancel a household service subscription at the next renewal. The subscription
	  stays valid till the next renewal.
	 * 
	 * @param subscriptionId Subscription Code
	 */
    public static CancelRenewalEntitlementBuilder cancelRenewal(String subscriptionId)  {
		return new CancelRenewalEntitlementBuilder(subscriptionId);
	}
	
	public static class CancelScheduledSubscriptionEntitlementBuilder extends RequestBuilder<Boolean, String, CancelScheduledSubscriptionEntitlementBuilder> {
		
		public CancelScheduledSubscriptionEntitlementBuilder(long scheduledSubscriptionId) {
			super(Boolean.class, "entitlement", "cancelScheduledSubscription");
			params.add("scheduledSubscriptionId", scheduledSubscriptionId);
		}
		
		public void scheduledSubscriptionId(String multirequestToken) {
			params.add("scheduledSubscriptionId", multirequestToken);
		}
	}

	/**
	 * Cancel Scheduled Subscription
	 * 
	 * @param scheduledSubscriptionId Scheduled Subscription Identifier
	 */
    public static CancelScheduledSubscriptionEntitlementBuilder cancelScheduledSubscription(long scheduledSubscriptionId)  {
		return new CancelScheduledSubscriptionEntitlementBuilder(scheduledSubscriptionId);
	}
	
	public static class ExternalReconcileEntitlementBuilder extends RequestBuilder<Boolean, String, ExternalReconcileEntitlementBuilder> {
		
		public ExternalReconcileEntitlementBuilder() {
			super(Boolean.class, "entitlement", "externalReconcile");
		}
	}

	/**
	 * Reconcile the user household&amp;#39;s entitlements with an external
	  entitlements source. This request is frequency protected to avoid too frequent
	  calls per household.
	 */
    public static ExternalReconcileEntitlementBuilder externalReconcile()  {
		return new ExternalReconcileEntitlementBuilder();
	}
	
	public static class ForceCancelEntitlementBuilder extends RequestBuilder<Boolean, String, ForceCancelEntitlementBuilder> {
		
		public ForceCancelEntitlementBuilder(int assetId, TransactionType productType) {
			super(Boolean.class, "entitlement", "forceCancel");
			params.add("assetId", assetId);
			params.add("productType", productType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void productType(String multirequestToken) {
			params.add("productType", multirequestToken);
		}
	}

	/**
	 * Immediately cancel a subscription, PPV or collection. Cancel applies regardless
	  of cancellation window and content consumption status
	 * 
	 * @param assetId The mediaFileID to cancel
	 * @param productType The product type for the cancelation
	 */
    public static ForceCancelEntitlementBuilder forceCancel(int assetId, TransactionType productType)  {
		return new ForceCancelEntitlementBuilder(assetId, productType);
	}
	
	public static class GetNextRenewalEntitlementBuilder extends RequestBuilder<EntitlementRenewal, EntitlementRenewal.Tokenizer, GetNextRenewalEntitlementBuilder> {
		
		public GetNextRenewalEntitlementBuilder(int id) {
			super(EntitlementRenewal.class, "entitlement", "getNextRenewal");
			params.add("id", id);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Returns the data about the next renewal
	 * 
	 * @param id Purchase Id
	 */
    public static GetNextRenewalEntitlementBuilder getNextRenewal(int id)  {
		return new GetNextRenewalEntitlementBuilder(id);
	}
	
	public static class GrantEntitlementBuilder extends RequestBuilder<Boolean, String, GrantEntitlementBuilder> {
		
		public GrantEntitlementBuilder(int productId, TransactionType productType, boolean history, int contentId) {
			super(Boolean.class, "entitlement", "grant");
			params.add("productId", productId);
			params.add("productType", productType);
			params.add("history", history);
			params.add("contentId", contentId);
		}
		
		public void productId(String multirequestToken) {
			params.add("productId", multirequestToken);
		}
		
		public void productType(String multirequestToken) {
			params.add("productType", multirequestToken);
		}
		
		public void history(String multirequestToken) {
			params.add("history", multirequestToken);
		}
		
		public void contentId(String multirequestToken) {
			params.add("contentId", multirequestToken);
		}
	}

	public static GrantEntitlementBuilder grant(int productId, TransactionType productType, boolean history)  {
		return grant(productId, productType, history, 0);
	}

	/**
	 * Grant household for an entitlement for a PPV or Subscription.
	 * 
	 * @param productId Identifier for the product package from which this content is offered
	 * @param productType Product package type. Possible values: PPV, Subscription, Collection
	 * @param history Controls if the new entitlements grant will appear in the user’s history. True
	 * – will add a history entry. False (or if ommited) – no history entry will be
	 * added
	 * @param contentId Identifier for the content. Relevant only if Product type = PPV
	 */
    public static GrantEntitlementBuilder grant(int productId, TransactionType productType, boolean history, int contentId)  {
		return new GrantEntitlementBuilder(productId, productType, history, contentId);
	}
	
	public static class ListEntitlementBuilder extends ListResponseRequestBuilder<Entitlement, Entitlement.Tokenizer, ListEntitlementBuilder> {
		
		public ListEntitlementBuilder(EntitlementFilter filter, FilterPager pager) {
			super(Entitlement.class, "entitlement", "list");
			params.add("filter", filter);
			params.add("pager", pager);
		}
	}

	public static ListEntitlementBuilder list(EntitlementFilter filter)  {
		return list(filter, null);
	}

	/**
	 * Gets all the entitled media items for a household
	 * 
	 * @param filter Request filter
	 * @param pager Request pager
	 */
    public static ListEntitlementBuilder list(EntitlementFilter filter, FilterPager pager)  {
		return new ListEntitlementBuilder(filter, pager);
	}
	
	public static class SwapEntitlementBuilder extends RequestBuilder<Boolean, String, SwapEntitlementBuilder> {
		
		public SwapEntitlementBuilder(int currentProductId, int newProductId, boolean history) {
			super(Boolean.class, "entitlement", "swap");
			params.add("currentProductId", currentProductId);
			params.add("newProductId", newProductId);
			params.add("history", history);
		}
		
		public void currentProductId(String multirequestToken) {
			params.add("currentProductId", multirequestToken);
		}
		
		public void newProductId(String multirequestToken) {
			params.add("newProductId", multirequestToken);
		}
		
		public void history(String multirequestToken) {
			params.add("history", multirequestToken);
		}
	}

	/**
	 * Swap current entitlement (subscription) with new entitlement (subscription) -
	  only Grant
	 * 
	 * @param currentProductId Identifier for the current product package
	 * @param newProductId Identifier for the new product package
	 * @param history Controls if the new entitlements swap will appear in the user’s history. True
	 * – will add a history entry. False (or if ommited) – no history entry will be
	 * added
	 */
    public static SwapEntitlementBuilder swap(int currentProductId, int newProductId, boolean history)  {
		return new SwapEntitlementBuilder(currentProductId, newProductId, history);
	}
	
	public static class UpdateEntitlementBuilder extends RequestBuilder<Entitlement, Entitlement.Tokenizer, UpdateEntitlementBuilder> {
		
		public UpdateEntitlementBuilder(int id, Entitlement entitlement) {
			super(Entitlement.class, "entitlement", "update");
			params.add("id", id);
			params.add("entitlement", entitlement);
		}
		
		public void id(String multirequestToken) {
			params.add("id", multirequestToken);
		}
	}

	/**
	 * Update Kaltura Entitelment by Purchase id
	 * 
	 * @param id Purchase Id
	 * @param entitlement KalturaEntitlement object
	 */
    public static UpdateEntitlementBuilder update(int id, Entitlement entitlement)  {
		return new UpdateEntitlementBuilder(id, entitlement);
	}
}
