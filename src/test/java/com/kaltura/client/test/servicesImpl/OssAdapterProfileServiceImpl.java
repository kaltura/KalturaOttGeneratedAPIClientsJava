package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.OssAdapterProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.OSSAdapterProfile;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.OssAdapterProfileService.*;
import static org.awaitility.Awaitility.await;

public class OssAdapterProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<OSSAdapterProfile> ossAdapterProfileResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<OSSAdapterProfile>> ossAdapterProfileListResponse;

    // add
    public static Response<OSSAdapterProfile> add(Client client, OSSAdapterProfile ossAdapterProfile) {
        AddOssAdapterProfileBuilder addOssAdapterProfileBuilder = OssAdapterProfileService.add(ossAdapterProfile)
                .setCompletion((ApiCompletion<OSSAdapterProfile>) result -> {
                    ossAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ossAdapterProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int ossAdapterId) {
        DeleteOssAdapterProfileBuilder deleteOssAdapterProfileBuilder = OssAdapterProfileService.delete(ossAdapterId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generateSharedSecret
    public static Response<OSSAdapterProfile> generateSharedSecret(Client client, int ossAdapterId) {
        GenerateSharedSecretOssAdapterProfileBuilder generateSharedSecretOssAdapterProfileBuilder =
                OssAdapterProfileService.generateSharedSecret(ossAdapterId)
                        .setCompletion((ApiCompletion<OSSAdapterProfile>) result -> {
                            ossAdapterProfileResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ossAdapterProfileResponse;
    }

    // get
    public static Response<OSSAdapterProfile> get(Client client, int id) {
        GetOssAdapterProfileBuilder getOssAdapterProfileBuilder = OssAdapterProfileService.get(id)
                .setCompletion((ApiCompletion<OSSAdapterProfile>) result -> {
                    ossAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ossAdapterProfileResponse;
    }

    // list
    public static Response<ListResponse<OSSAdapterProfile>> list(Client client) {
        ListOssAdapterProfileBuilder listOssAdapterProfileBuilder = OssAdapterProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<OSSAdapterProfile>>) result -> {
                    ossAdapterProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ossAdapterProfileListResponse;
    }

    // update
    public static Response<OSSAdapterProfile> update(Client client, int ossAdapterId, OSSAdapterProfile ossAdapterProfile) {
        UpdateOssAdapterProfileBuilder updateOssAdapterProfileBuilder = OssAdapterProfileService.update(ossAdapterId, ossAdapterProfile)
                .setCompletion((ApiCompletion<OSSAdapterProfile>) result -> {
                    ossAdapterProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateOssAdapterProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ossAdapterProfileResponse;
    }
}
