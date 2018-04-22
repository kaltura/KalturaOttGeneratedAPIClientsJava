package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.PpvService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Ppv;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.PpvService.*;
import static org.awaitility.Awaitility.await;

public class PpvServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Ppv> ppvResponse;

    // get
    public static Response<Ppv> get(Client client, long id) {
        GetPpvBuilder getPpvBuilder = PpvService.get(id).setCompletion((ApiCompletion<Ppv>) result -> {
            ppvResponse = result;
            done.set(true);
        });

        TestAPIOkRequestsExecutor.getExecutor().queue(getPpvBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ppvResponse;
    }
}
