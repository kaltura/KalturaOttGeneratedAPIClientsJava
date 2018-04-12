package com.kaltura.client.test.tests.servicesTests.entitlementTests;

import com.kaltura.client.Client;
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.enums.TransactionHistoryOrderBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.test.servicesImpl.EntitlementServiceImpl;
import com.kaltura.client.test.servicesImpl.TransactionHistoryServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.BillingTransaction;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.TransactionHistoryFilter;
import com.kaltura.client.utils.response.base.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GrantTests extends BaseTest {

    @Test(description = "entitlement/action/grant - grant with history = true")
    private void grant_subscription_with_history() {
        Client client = getClient(null);

        // TODO: 4/12/2018 remove hardcoded subscription Id
        int subscriptionId = 369426;
        int ppv = 30297;

        // grant subscription - history = true
        client.setKs(administratorKs);
        client.setUserId(Integer.valueOf(sharedUser.getUserId()));
        Response<Boolean> booleanResponse = EntitlementServiceImpl.grant(client, subscriptionId, TransactionType.SUBSCRIPTION, true, null);
        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);

        // check transaction return in transactionHistory
        client.setKs(sharedMasterUserKs);
        TransactionHistoryFilter filter = new TransactionHistoryFilter();
        filter.orderBy(TransactionHistoryOrderBy.CREATE_DATE_ASC.getValue());
        filter.entityReferenceEqual(EntityReferenceBy.USER.getValue());
        Response<ListResponse<BillingTransaction>> billingTransactionListResponse  = TransactionHistoryServiceImpl.list(client, filter, null);

        // assert list item
    }
}
