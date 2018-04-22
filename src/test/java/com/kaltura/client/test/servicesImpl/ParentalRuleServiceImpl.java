package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.EntityReferenceBy;
import com.kaltura.client.services.ParentalRuleService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.ParentalRule;
import com.kaltura.client.types.ParentalRuleFilter;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ParentalRuleService.*;
import static org.awaitility.Awaitility.await;

public class ParentalRuleServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<ListResponse<ParentalRule>> parentalRuleListResponse;
    private static Response<Boolean> booleanResponse;

    // disable
    public static Response<Boolean> disable(Client client, long ruleId, EntityReferenceBy entityReferenceBy) {
        DisableParentalRuleBuilder disableParentalRuleBuilder = ParentalRuleService.disable(ruleId, entityReferenceBy)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(disableParentalRuleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // disableDefault
    public static Response<Boolean> disableDefault(Client client, EntityReferenceBy entityReferenceBy) {
        DisableDefaultParentalRuleBuilder disableDefaultParentalRuleBuilder = ParentalRuleService
                .disableDefault(entityReferenceBy).setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(disableDefaultParentalRuleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // enable
    public static Response<Boolean> enable(Client client, long ruleId, EntityReferenceBy entityReferenceBy) {
        EnableParentalRuleBuilder enableParentalRuleBuilder = ParentalRuleService.enable(ruleId, entityReferenceBy)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(enableParentalRuleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // list
    public static Response<ListResponse<ParentalRule>> list(Client client, ParentalRuleFilter parentalRuleFilter) {
        ListParentalRuleBuilder listParentalRuleBuilder = ParentalRuleService.list(parentalRuleFilter)
                .setCompletion((ApiCompletion<ListResponse<ParentalRule>>) result -> {
                    parentalRuleListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listParentalRuleBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return parentalRuleListResponse;
    }
}
