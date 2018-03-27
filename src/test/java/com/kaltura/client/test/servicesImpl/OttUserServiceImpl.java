package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.CustomAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;
import org.hamcrest.MatcherAssert;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.services.OttUserService.login;
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
    public static Response<LoginResponse> loginImpl(int partnerId, String username, String password, Map<String, StringValue> extraParams, String udid) {
        LoginOttUserBuilder loginOttUserBuilder = login(partnerId, username, password, extraParams, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
//                        MatcherAssert.assertThat(result.results.toParams().toString(), matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA));
                    }
                    loginResponse = result;
                    done.set(true);
                });

        CustomAPIOkRequestsExecutor.getExecutor().queue(loginOttUserBuilder.build(client));
        await().untilTrue(done);
        if (done.get()) {
            MatcherAssert.assertThat(CustomAPIOkRequestsExecutor.fullResponseAsString, matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA));
        }
        done.set(false);

        return loginResponse;
    }

    //register
    public static Response<OTTUser> registerImpl(int partnerId, OTTUser user, String password) {
        RegisterOttUserBuilder registerOttUserBuilder = register(partnerId, user, password)
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
    public static Response<LoginSession> anonymousLoginImpl(int partnerId, String udid) {
        AnonymousLoginOttUserBuilder anonymousLoginOttUserBuilder = anonymousLogin(partnerId, udid)
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
    public static Response<OTTUser> activateImpl(int partnerId, String username, String activationToken) {
        ActivateOttUserBuilder activateOttUserBuilder = activate(partnerId, username, activationToken)
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
    public static Response<Boolean> addRoleImpl(String ks, int userId, int roleId) {
        AddRoleOttUserBuilder addRoleOttUserBuilder = addRole(roleId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    booleanResponse = result;
                    done.set(true);
                });

        addRoleOttUserBuilder.setKs(ks);
        addRoleOttUserBuilder.setUserId(userId);
        CustomAPIOkRequestsExecutor.getExecutor().queue(addRoleOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    //delete
    public static Response<Boolean> deleteImpl(String ks, Integer userId) {
        DeleteOttUserBuilder deleteOttUserBuilder = delete()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    booleanResponse = result;
                    done.set(true);
                });

        deleteOttUserBuilder.setKs(ks);
        deleteOttUserBuilder.setUserId(userId);

        CustomAPIOkRequestsExecutor.getExecutor().queue(deleteOttUserBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    public static Response<OTTUser> getImpl(String ks, Optional<Integer> userId) {
        GetOttUserBuilder getOttUserBuilder = get()
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

    public static Response<ListResponse<OTTUser>> listImpl(String ks, OTTUserFilter ottUserFilter) {
        ListOttUserBuilder listOttUserBuilder = list(ottUserFilter)
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


