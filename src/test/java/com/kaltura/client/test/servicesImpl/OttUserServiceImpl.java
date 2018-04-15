package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.OttUserService.*;
import static org.awaitility.Awaitility.await;

public class OttUserServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<LoginResponse> loginResponse;
    private static Response<OTTUser> ottUserResponse;
    private static Response<LoginSession> loginSessionResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<OTTUser>> ottUserListResponse;
    private static Response<StringValue> stringValueResponse;
    private static Response<OTTUserDynamicData> ottUserDynamicDataResponse;


    // activate
    public static Response<OTTUser> activate(Client client, int partnerId, String username, String activationToken) {
        ActivateOttUserBuilder activateOttUserBuilder = OttUserService.activate(partnerId, username, activationToken)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    ottUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(activateOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    // addRole
    public static Response<Boolean> addRole(Client client, int roleId) {
        AddRoleOttUserBuilder addRoleOttUserBuilder = OttUserService.addRole(roleId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addRoleOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // anonymousLogin
    public static Response<LoginSession> anonymousLogin(Client client, int partnerId, @Nullable String udid) {
        AnonymousLoginOttUserBuilder anonymousLoginOttUserBuilder = OttUserService.anonymousLogin(partnerId, udid)
                .setCompletion((ApiCompletion<LoginSession>) result -> {
                    loginSessionResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(anonymousLoginOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginSessionResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client) {
        DeleteOttUserBuilder deleteOttUserBuilder = OttUserService.delete()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<OTTUser> get(Client client) {
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    ottUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    // getEncryptedUserId
    public static Response<StringValue> getEncryptedUserId(Client client) {
        GetEncryptedUserIdOttUserBuilder getEncryptedUserIdOttUserBuilder = OttUserService.getEncryptedUserId()
                .setCompletion((ApiCompletion<StringValue>) result -> {
                    stringValueResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getEncryptedUserIdOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return stringValueResponse;
    }

    // list
    public static Response<ListResponse<OTTUser>> list(Client client, @Nullable OTTUserFilter ottUserFilter) {
        ListOttUserBuilder listOttUserBuilder = OttUserService.list(ottUserFilter)
                .setCompletion((ApiCompletion<ListResponse<OTTUser>>) result -> {
                    ottUserListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserListResponse;
    }

    // login
    public static Response<LoginResponse> login(Client client, int partnerId, @Nullable String username, @Nullable String password,
                                                @Nullable Map<String, StringValue> extraParams, @Nullable String udid) {
        LoginOttUserBuilder loginOttUserBuilder = OttUserService.login(partnerId, username, password, extraParams, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    loginResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(loginOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginResponse;
    }

    // loginWithPin
    public static Response<LoginResponse> loginWithPin(Client client, int partnerId, String pin, @Nullable String udid, String secret) {

        LoginWithPinOttUserBuilder loginWithPinOttUserBuilder = OttUserService.loginWithPin(partnerId, pin, udid, secret)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    loginResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(loginWithPinOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginResponse;
    }

    // logout
    public static Response<Boolean> logout(Client client) {
        LogoutOttUserBuilder logoutOttUserBuilder = OttUserService.logout()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(logoutOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // register
    public static Response<OTTUser> register(Client client, int partnerId, OTTUser user, String password) {
        RegisterOttUserBuilder registerOttUserBuilder = OttUserService.register(partnerId, user, password)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    ottUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(registerOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    // resendActivationToken
    public static Response<Boolean> resendActivationToken(Client client, int partnerId, String username) {
        ResendActivationTokenOttUserBuilder resendActivationTokenOttUserBuilder = OttUserService.resendActivationToken(partnerId, username)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(resendActivationTokenOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // resetPassword
    public static Response<Boolean> resetPassword(Client client, int partnerId, String username) {
        ResetPasswordOttUserBuilder resetPasswordOttUserBuilder = OttUserService.resetPassword(partnerId, username)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(resetPasswordOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // setInitialPassword
    public static Response<OTTUser> setInitialPassword(Client client, int partnerId, String token, String password) {
        SetInitialPasswordOttUserBuilder setInitialPasswordOttUserBuilder = OttUserService.setInitialPassword(partnerId, token, password)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    ottUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(setInitialPasswordOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    // update
    public static Response<OTTUser> update(Client client, OTTUser user, @Nullable String id) {
        UpdateOttUserBuilder updateOttUserBuilder = OttUserService.update(user, id)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    ottUserResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    // updateDynamicData
    public static Response<OTTUserDynamicData> updateDynamicData(Client client, String key, StringValue value) {
        UpdateDynamicDataOttUserBuilder updateDynamicDataOttUserBuilder = OttUserService.updateDynamicData(key, value)
                .setCompletion((ApiCompletion<OTTUserDynamicData>) result -> {
                    ottUserDynamicDataResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateDynamicDataOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserDynamicDataResponse;
    }

    // updateLoginData
    public static Response<Boolean> updateLoginData(Client client, String username, String oldPassword, String newPassword) {
        UpdateLoginDataOttUserBuilder updateLoginDataOttUserBuilder = OttUserService.updateLoginData(username, oldPassword, newPassword)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateLoginDataOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // updatePassword
    public static Response<Boolean> updatePassword(Client client, int partnerId, String username) {
        ResetPasswordOttUserBuilder resetPasswordOttUserBuilder = OttUserService.resetPassword(partnerId, username)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(resetPasswordOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}


