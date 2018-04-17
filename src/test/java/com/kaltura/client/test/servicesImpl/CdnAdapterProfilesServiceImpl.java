package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CdnAdapterProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.CDNAdapterProfile;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CdnAdapterProfileService.*;
import static com.kaltura.client.services.CdnAdapterProfileService.AddCdnAdapterProfileBuilder;
import static org.awaitility.Awaitility.await;

public class CdnAdapterProfilesServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<CDNAdapterProfile> cdnAdapterProfileResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<CDNAdapterProfile>> cdnAdapterProfileListResponse;

    // add
    public static Response<CDNAdapterProfile> add(Client client, CDNAdapterProfile cdnAdapterProfile) {
        AddCdnAdapterProfileBuilder addCdnAdapterProfileBuilder = CdnAdapterProfileService.add(cdnAdapterProfile)
                .setCompletion((ApiCompletion<CDNAdapterProfile>) result -> {
                    cdnAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addCdnAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnAdapterProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int adapterId) {
        DeleteCdnAdapterProfileBuilder deleteCdnAdapterProfileBuilder = CdnAdapterProfileService.delete(adapterId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteCdnAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generateSharedSecret
    public static Response<CDNAdapterProfile> generateSharedSecret(Client client, int adapterId) {
        GenerateSharedSecretCdnAdapterProfileBuilder generateSharedSecretCdnAdapterProfileBuilder =
                CdnAdapterProfileService.generateSharedSecret(adapterId).setCompletion((ApiCompletion<CDNAdapterProfile>) result -> {
                    cdnAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretCdnAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnAdapterProfileResponse;
    }

    // list
    public static Response<ListResponse<CDNAdapterProfile>> list(Client client) {
        ListCdnAdapterProfileBuilder listCdnAdapterProfileBuilder = CdnAdapterProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<CDNAdapterProfile>>) result -> {
                    cdnAdapterProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listCdnAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnAdapterProfileListResponse;
    }

    // update
    public static Response<CDNAdapterProfile> update(Client client, int adapterId, CDNAdapterProfile cdnAdapterProfile) {
        UpdateCdnAdapterProfileBuilder updateCdnAdapterProfileBuilder = CdnAdapterProfileService.update(adapterId, cdnAdapterProfile)
                .setCompletion((ApiCompletion<CDNAdapterProfile>) result -> {
                    cdnAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateCdnAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return cdnAdapterProfileResponse;
    }
}
