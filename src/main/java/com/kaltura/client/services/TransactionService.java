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
import com.kaltura.client.types.ExternalReceipt;
import com.kaltura.client.types.Purchase;
import com.kaltura.client.types.PurchaseSession;
import com.kaltura.client.types.Transaction;
import com.kaltura.client.types.TransactionStatus;
import com.kaltura.client.utils.request.NullRequestBuilder;
import com.kaltura.client.utils.request.RequestBuilder;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class TransactionService {
	
	public static class DowngradeTransactionBuilder extends NullRequestBuilder {
		
		public DowngradeTransactionBuilder(Purchase purchase) {
			super("transaction", "downgrade");
			params.add("purchase", purchase);
		}
	}

	/**
	 * downgrade specific subscription for a household. entitlements will be updated on
	  the existing subscription end date.
	 * 
	 * @param purchase Purchase properties
	 */
    public static DowngradeTransactionBuilder downgrade(Purchase purchase)  {
		return new DowngradeTransactionBuilder(purchase);
	}
	
	public static class GetPurchaseSessionIdTransactionBuilder extends RequestBuilder<Long, String, GetPurchaseSessionIdTransactionBuilder> {
		
		public GetPurchaseSessionIdTransactionBuilder(PurchaseSession purchaseSession) {
			super(Long.class, "transaction", "getPurchaseSessionId");
			params.add("purchaseSession", purchaseSession);
		}
	}

	/**
	 * Retrieve the purchase session identifier
	 * 
	 * @param purchaseSession Purchase properties
	 */
    public static GetPurchaseSessionIdTransactionBuilder getPurchaseSessionId(PurchaseSession purchaseSession)  {
		return new GetPurchaseSessionIdTransactionBuilder(purchaseSession);
	}
	
	public static class PurchaseTransactionBuilder extends RequestBuilder<Transaction, Transaction.Tokenizer, PurchaseTransactionBuilder> {
		
		public PurchaseTransactionBuilder(Purchase purchase) {
			super(Transaction.class, "transaction", "purchase");
			params.add("purchase", purchase);
		}
	}

	/**
	 * Purchase specific product or subscription for a household. Upon successful
	  charge entitlements to use the requested product or subscription are granted.
	 * 
	 * @param purchase Purchase properties
	 */
    public static PurchaseTransactionBuilder purchase(Purchase purchase)  {
		return new PurchaseTransactionBuilder(purchase);
	}
	
	public static class SetWaiverTransactionBuilder extends RequestBuilder<Boolean, String, SetWaiverTransactionBuilder> {
		
		public SetWaiverTransactionBuilder(int assetId, TransactionType transactionType) {
			super(Boolean.class, "transaction", "setWaiver");
			params.add("assetId", assetId);
			params.add("transactionType", transactionType);
		}
		
		public void assetId(String multirequestToken) {
			params.add("assetId", multirequestToken);
		}
		
		public void transactionType(String multirequestToken) {
			params.add("transactionType", multirequestToken);
		}
	}

	/**
	 * This method shall set the waiver flag on the user entitlement table and the
	  waiver date field to the current date.
	 * 
	 * @param assetId Asset identifier
	 * @param transactionType The transaction type
	 */
    public static SetWaiverTransactionBuilder setWaiver(int assetId, TransactionType transactionType)  {
		return new SetWaiverTransactionBuilder(assetId, transactionType);
	}
	
	public static class UpdateStatusTransactionBuilder extends NullRequestBuilder {
		
		public UpdateStatusTransactionBuilder(String paymentGatewayId, String externalTransactionId, String signature, TransactionStatus status) {
			super("transaction", "updateStatus");
			params.add("paymentGatewayId", paymentGatewayId);
			params.add("externalTransactionId", externalTransactionId);
			params.add("signature", signature);
			params.add("status", status);
		}
		
		public void paymentGatewayId(String multirequestToken) {
			params.add("paymentGatewayId", multirequestToken);
		}
		
		public void externalTransactionId(String multirequestToken) {
			params.add("externalTransactionId", multirequestToken);
		}
		
		public void signature(String multirequestToken) {
			params.add("signature", multirequestToken);
		}
	}

	/**
	 * Updates a pending purchase transaction state.
	 * 
	 * @param paymentGatewayId Payment gateway identifier
	 * @param externalTransactionId external transaction identifier
	 * @param signature Security signature to validate the caller is a payment gateway adapter
	 * application
	 * @param status Status properties
	 */
    public static UpdateStatusTransactionBuilder updateStatus(String paymentGatewayId, String externalTransactionId, String signature, TransactionStatus status)  {
		return new UpdateStatusTransactionBuilder(paymentGatewayId, externalTransactionId, signature, status);
	}
	
	public static class UpgradeTransactionBuilder extends RequestBuilder<Transaction, Transaction.Tokenizer, UpgradeTransactionBuilder> {
		
		public UpgradeTransactionBuilder(Purchase purchase) {
			super(Transaction.class, "transaction", "upgrade");
			params.add("purchase", purchase);
		}
	}

	/**
	 * upgrade specific subscription for a household. Upon successful charge
	  entitlements to use the requested product or subscription are granted.
	 * 
	 * @param purchase Purchase properties
	 */
    public static UpgradeTransactionBuilder upgrade(Purchase purchase)  {
		return new UpgradeTransactionBuilder(purchase);
	}
	
	public static class ValidateReceiptTransactionBuilder extends RequestBuilder<Transaction, Transaction.Tokenizer, ValidateReceiptTransactionBuilder> {
		
		public ValidateReceiptTransactionBuilder(ExternalReceipt externalReceipt) {
			super(Transaction.class, "transaction", "validateReceipt");
			params.add("externalReceipt", externalReceipt);
		}
	}

	/**
	 * Verifies PPV/Subscription/Collection client purchase (such as InApp) and
	  entitles the user.
	 * 
	 * @param externalReceipt Receipt properties
	 */
    public static ValidateReceiptTransactionBuilder validateReceipt(ExternalReceipt externalReceipt)  {
		return new ValidateReceiptTransactionBuilder(externalReceipt);
	}
}
