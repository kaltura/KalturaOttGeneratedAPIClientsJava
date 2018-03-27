package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.CustomAPIOkRequestsExecutor;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import org.hamcrest.MatcherAssert;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.tests.BaseTest.client;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.awaitility.Awaitility.await;

public class OttUserServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static final String LOGIN_RESPONSE_SCHEMA = "schemas/KalturaLoginResponse_Schema.json";
    private static final String LOGIN_SESSION_SCHEMA = "KalturaLoginSession_Schema.json";
    private static final String OTT_USER_SCHEMA = "KalturaOttUser_Schema.json";

    private static Response<LoginResponse> loginResponse;
    private static Response<OTTUser> ottUserResponse;
    private static Response<LoginSession> loginSessionResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<ListResponse<OTTUser>> ottUserListResponse;


    //login
    public static Response<LoginResponse> login(int partnerId, String username, String password, @Nullable Map<String, StringValue> extraParams, @Nullable String udid) {
        LoginOttUserBuilder loginOttUserBuilder = OttUserService.login(partnerId, username, password, extraParams, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    loginResponse = result;
                    done.set(true);
                });

        CustomAPIOkRequestsExecutor.getExecutor().queue(loginOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        if (loginResponse.isSuccess()) {
            MatcherAssert.assertThat(CustomAPIOkRequestsExecutor.fullResponseAsString, matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA));
        }

        return loginResponse;
    }

    //register
    public static Response<OTTUser> register(int partnerId, OTTUser user, String password) {
        RegisterOttUserBuilder registerOttUserBuilder = OttUserService.register(partnerId, user, password)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    ottUserResponse = result;
                    done.set(true);
                });

        CustomAPIOkRequestsExecutor.getExecutor().queue(registerOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    //anonymousLogin
    public static Response<LoginSession> anonymousLogin(int partnerId, @Nullable String udid) {
        AnonymousLoginOttUserBuilder anonymousLoginOttUserBuilder = OttUserService.anonymousLogin(partnerId, udid)
                .setCompletion((ApiCompletion<LoginSession>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    loginSessionResponse = result;
                    done.set(true);
                });

        CustomAPIOkRequestsExecutor.getExecutor().queue(anonymousLoginOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginSessionResponse;
    }

    //activate
    public static Response<OTTUser> activate(int partnerId, String username, String activationToken) {
        ActivateOttUserBuilder activateOttUserBuilder = OttUserService.activate(partnerId, username, activationToken)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    ottUserResponse = result;
                    done.set(true);
                });

        CustomAPIOkRequestsExecutor.getExecutor().queue(activateOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    //addRole
    public static Response<Boolean> addRole(String ks, Optional<Integer> userId, int roleId) {
        AddRoleOttUserBuilder addRoleOttUserBuilder = OttUserService.addRole(roleId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        addRoleOttUserBuilder.setKs(ks);
        userId.ifPresent(addRoleOttUserBuilder::setUserId);

        CustomAPIOkRequestsExecutor.getExecutor().queue(addRoleOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    //delete
    public static Response<Boolean> delete(String ks, Optional<Integer> userId) {
        DeleteOttUserBuilder deleteOttUserBuilder = OttUserService.delete()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        deleteOttUserBuilder.setKs(ks);
        userId.ifPresent(deleteOttUserBuilder::setUserId);

        CustomAPIOkRequestsExecutor.getExecutor().queue(deleteOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    public static Response<OTTUser> get(String ks, Optional<Integer> userId) {
        GetOttUserBuilder getOttUserBuilder = OttUserService.get()
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    ottUserResponse = result;
                    done.set(true);
                });

        getOttUserBuilder.setKs(ks);
        userId.ifPresent(getOttUserBuilder::setUserId);

        CustomAPIOkRequestsExecutor.getExecutor().queue(getOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserResponse;
    }

    public static Response<ListResponse<OTTUser>> list(String ks, @Nullable OTTUserFilter ottUserFilter) {
        ListOttUserBuilder listOttUserBuilder = OttUserService.list(ottUserFilter)
                .setCompletion((ApiCompletion<ListResponse<OTTUser>>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    ottUserListResponse = result;
                    done.set(true);
                });

        listOttUserBuilder.setKs(ks);
        CustomAPIOkRequestsExecutor.getExecutor().queue(listOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return ottUserListResponse;
    }

//    public static <T> T loginWithPin(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T logout(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T refreshSession(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T resendActivationToken(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T resetPassword(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T setInitialPassword(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T update(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T updateLoginData(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T getEncryptedUserId(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
}


