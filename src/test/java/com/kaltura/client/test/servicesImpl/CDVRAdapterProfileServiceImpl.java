package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CDVRAdapterProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.CDVRAdapterProfile;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CDVRAdapterProfileService.*;
import static org.awaitility.Awaitility.await;

public class CDVRAdapterProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<CDVRAdapterProfile> cdvrAdapterProfileResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<CDVRAdapterProfile>> cdvrAdapterProfileListResponse;

    // add
    public static Response<CDVRAdapterProfile> add(Client client, CDVRAdapterProfile cdvrAdapterProfile) {
        AddCDVRAdapterProfileBuilder addCDVRAdapterProfileBuilder = CDVRAdapterProfileService.add(cdvrAdapterProfile)
                .setCompletion((ApiCompletion<CDVRAdapterProfile>) result -> {
                    cdvrAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addCDVRAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdvrAdapterProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int adapterId) {
        DeleteCDVRAdapterProfileBuilder deleteCDVRAdapterProfileBuilder = CDVRAdapterProfileService.delete(adapterId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteCDVRAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generateSharedSecret
    public static Response<CDVRAdapterProfile> generateSharedSecret(Client client, int adapterId) {
        GenerateSharedSecretCDVRAdapterProfileBuilder generateSharedSecretCDVRAdapterProfileBuilder =
                CDVRAdapterProfileService.generateSharedSecret(adapterId).setCompletion((ApiCompletion<CDVRAdapterProfile>) result -> {
                    cdvrAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretCDVRAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdvrAdapterProfileResponse;
    }

    // list
    public static Response<ListResponse<CDVRAdapterProfile>> list(Client client) {
        ListCDVRAdapterProfileBuilder listCDVRAdapterProfileBuilder = CDVRAdapterProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<CDVRAdapterProfile>>) result -> {
                    cdvrAdapterProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listCDVRAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdvrAdapterProfileListResponse;
    }

    // update
    public static Response<CDVRAdapterProfile> update(Client client, int adapterId, CDVRAdapterProfile cdvrAdapterProfile) {
        UpdateCDVRAdapterProfileBuilder updateCDVRAdapterProfileBuilder = CDVRAdapterProfileService.update(adapterId, cdvrAdapterProfile)
                .setCompletion((ApiCompletion<CDVRAdapterProfile>) result -> {
                    cdvrAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateCDVRAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdvrAdapterProfileResponse;
    }
}
