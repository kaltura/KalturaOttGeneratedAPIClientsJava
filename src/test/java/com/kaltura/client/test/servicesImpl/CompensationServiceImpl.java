package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CompensationService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Compensation;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CompensationService.*;
import static org.awaitility.Awaitility.await;

public class CompensationServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Compensation> compensationResponse;

    // add
    public static Response<Compensation> add(Client client, Compensation compensation) {
        AddCompensationBuilder addCompensationBuilder = CompensationService.add(compensation)
                .setCompletion((ApiCompletion<Compensation>) result -> {
                    compensationResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addCompensationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return compensationResponse;
    }

    // delete
    public static void delete(Client client, long id) {
        DeleteCompensationBuilder deleteCompensationBuilder = CompensationService.delete(id);
        deleteCompensationBuilder.setCompletion((ApiCompletion<Void>) result -> done.set(true));

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteCompensationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);
    }

    // get
    public static Response<Compensation> get(Client client, long id) {
        GetCompensationBuilder getCompensationBuilder = CompensationService.get(id)
                .setCompletion((ApiCompletion<Compensation>) result -> {
                    compensationResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getCompensationBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return compensationResponse;
    }
}
