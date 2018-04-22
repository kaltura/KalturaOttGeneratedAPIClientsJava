package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.RecommendationProfileService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.RecommendationProfile;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.RecommendationProfileService.*;
import static org.awaitility.Awaitility.await;

public class RecommendationProfileServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<RecommendationProfile> recommendationProfileResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<RecommendationProfile>> recommendationProfileListResponse;

    // add
    public static Response<RecommendationProfile> add(Client client, RecommendationProfile recommendationProfile) {
        AddRecommendationProfileBuilder addRecommendationProfileBuilder = RecommendationProfileService.add(recommendationProfile)
                .setCompletion((ApiCompletion<RecommendationProfile>) result -> {
                    recommendationProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addRecommendationProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recommendationProfileResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int id) {
        DeleteRecommendationProfileBuilder deleteRecommendationProfileBuilder = RecommendationProfileService.delete(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteRecommendationProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generateSharedSecret
    public static Response<RecommendationProfile> generateSharedSecret(Client client, int recommendationProfieId) {
        GenerateSharedSecretRecommendationProfileBuilder generateSharedSecretRecommendationProfileBuilder =
                RecommendationProfileService.generateSharedSecret(recommendationProfieId)
                        .setCompletion((ApiCompletion<RecommendationProfile>) result -> {
                            recommendationProfileResponse = result;
                            done.set(true);
                        });

        TestAPIOkRequestsExecutor.getExecutor().queue(generateSharedSecretRecommendationProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recommendationProfileResponse;
    }

    // list
    public static Response<ListResponse<RecommendationProfile>> list(Client client) {
        ListRecommendationProfileBuilder listRecommendationProfileBuilder = RecommendationProfileService.list()
                .setCompletion((ApiCompletion<ListResponse<RecommendationProfile>>) result -> {
                    recommendationProfileListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listRecommendationProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recommendationProfileListResponse;
    }

    // update
    public static Response<RecommendationProfile> update(Client client, int recommendationProfieId, RecommendationProfile recommendationProfile) {
        UpdateRecommendationProfileBuilder updateRecommendationProfileBuilder = RecommendationProfileService.update(recommendationProfieId, recommendationProfile)
                .setCompletion((ApiCompletion<RecommendationProfile>) result -> {
                    recommendationProfileResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateRecommendationProfileBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return recommendationProfileResponse;
    }
}
