package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.SocialNetwork;
import com.kaltura.client.services.SocialService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.Social;
import com.kaltura.client.types.SocialConfig;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.SocialService.*;
import static org.awaitility.Awaitility.await;

public class SocialServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Social> socialResponse;
    private static Response<LoginResponse> loginResponse;
    private static Response<SocialConfig> socialConfigResponse;

    // get
    public static Response<Social> get(Client client, SocialNetwork socialNetwork) {
        GetSocialBuilder getSocialBuilder = SocialService.get(socialNetwork)
                .setCompletion((ApiCompletion<Social>) result -> {
                    socialResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialResponse;
    }

    // getByToken
    public static Response<Social> getByToken(Client client, int partnerId, String token, SocialNetwork socialNetwork) {
        GetByTokenSocialBuilder getByTokenSocialBuilder = SocialService.getByToken(partnerId, token, socialNetwork)
                .setCompletion((ApiCompletion<Social>) result -> {
                    socialResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getByTokenSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialResponse;
    }

    // getConfiguration
    public static Response<SocialConfig> getConfiguration(Client client, SocialNetwork socialNetwork, @Nullable int partnerId) {
        GetConfigurationSocialBuilder getConfigurationSocialBuilder = SocialService.getConfiguration(socialNetwork, partnerId)
                .setCompletion((ApiCompletion<SocialConfig>) result -> {
                    socialConfigResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getConfigurationSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialConfigResponse;
    }

    // login
    public static Response<LoginResponse> login(Client client, int partnerId, String token, SocialNetwork socialNetwork, @Nullable String udid) {
        LoginSocialBuilder loginSocialBuilder = SocialService.login(partnerId, token, socialNetwork, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    loginResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(loginSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginResponse;
    }

    // merge
    public static Response<Social> merge(Client client, String token, SocialNetwork socialNetwork) {
        MergeSocialBuilder mergeSocialBuilder = SocialService.merge(token, socialNetwork)
                .setCompletion((ApiCompletion<Social>) result -> {
                    socialResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(mergeSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialResponse;
    }

    // register
    public static Response<Social> register(Client client, int partnerId, String token, SocialNetwork socialNetwork, @Nullable String email) {
        RegisterSocialBuilder registerSocialBuilder = SocialService.register(partnerId, token, socialNetwork, email)
                .setCompletion((ApiCompletion<Social>) result -> {
                    socialResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(registerSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialResponse;
    }

    // unmerge
    public static Response<Social> unmerge(Client client, SocialNetwork socialNetwork) {
        UnmergeSocialBuilder unmergeSocialBuilder = SocialService.unmerge(socialNetwork)
                .setCompletion((ApiCompletion<Social>) result -> {
                    socialResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(unmergeSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialResponse;
    }

    // updateConfiguration
    public static Response<SocialConfig> updateConfiguration(Client client, SocialConfig socialConfig) {
        UpdateConfigurationSocialBuilder updateConfigurationSocialBuilder = SocialService.UpdateConfiguration(socialConfig)
                .setCompletion((ApiCompletion<SocialConfig>) result -> {
                    socialConfigResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateConfigurationSocialBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return socialConfigResponse;
    }
}
