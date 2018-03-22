package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.LoginSession;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.test.tests.BaseTest.client;
import static org.awaitility.Awaitility.await;

public class OttUserServiceImpl {

    private static final String LOGIN_RESPONSE_SCHEMA = "KalturaLoginResponse_Schema.json";
    private static final String LOGIN_SESSION_SCHEMA = "KalturaLoginSession_Schema.json";
    private static final String OTT_USER_SCHEMA = "KalturaOttUser_Schema.json";

    private static Response<LoginResponse> loginResponse;
    private static Response<OTTUser> ottUserResponse;
    private static Response<LoginSession> loginSessionResponse;


    public static Response<LoginResponse> loginImpl(int partnerId, String username, String password, Map<String, StringValue> extraParams, String udid) {
        final AtomicBoolean done = new AtomicBoolean(false);

        LoginOttUserBuilder loginOttUserBuilder = login(partnerId, username, password, extraParams, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
//                        MatcherAssert.assertThat(result.results.toParams().toString(), matchesJsonSchemaInClasspath(LOGIN_RESPONSE_SCHEMA));
                    }
                    loginResponse = result;
                    done.set(true);
                });
        APIOkRequestsExecutor.getExecutor().queue(loginOttUserBuilder.build(client));
        await().untilTrue(done);

        return loginResponse;
    }

    public static Response<OTTUser> registerImpl(int partnerId, OTTUser user, String password) {
        final AtomicBoolean done = new AtomicBoolean(false);

        RegisterOttUserBuilder registerOttUserBuilder = register(partnerId, user, password)
                .setCompletion((ApiCompletion<OTTUser>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    ottUserResponse = result;
                    done.set(true);
                });
        APIOkRequestsExecutor.getExecutor().queue(registerOttUserBuilder.build(client));
        await().untilTrue(done);

        return ottUserResponse;
    }

    public static Response<LoginSession> anonymousLoginImpl(int partnerId, String udid) {
        final AtomicBoolean done = new AtomicBoolean(false);

        AnonymousLoginOttUserBuilder anonymousLoginOttUserBuilder = anonymousLogin(partnerId, udid)
                .setCompletion((ApiCompletion<LoginSession>) result -> {
                    if (result.isSuccess()) {
                        // TODO: 3/22/2018 fix schema assertions
                    }
                    loginSessionResponse = result;
                    done.set(true);
                });
        APIOkRequestsExecutor.getExecutor().queue(anonymousLoginOttUserBuilder.build(client));
        await().untilTrue(done);

        return loginSessionResponse;
    }


//    public static <T> T activate(String ks, String username, String activationToken) {
//        String body = activateRequestBuilder(ks, username, activationToken);
//        Response response = setPostRequest(body, SERVICE, ACTIVATE_ACTION);
//
//        if (isApiException(response)) {
//            return (T) getApiException(response);
//        } else {
//            assertThat(response.asString(), matchesJsonSchemaInClasspath(OTT_USER_SCHEMA));
//        }
//        try {
//            return GsonParser.parseObject(response.asString(), (Class<T>) OTTUser.class);
//        } catch (APIException e) {
//            Logger.getLogger(OttUserService.class).error("OTTUser parse error");
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static <T> T addRole(String ks, int roleId) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T delete(String ks) {
//        String body = getBaseRequestBody(ks).toString();
//        Response response = setPostRequest(body, SERVICE, DELETE_ACTION);
//
//        if (isApiException(response)) {
//            return (T) getApiException(response);
//        } else {
//            try {
//                assertThat(response.asString(), matchesJsonSchemaInClasspath(BOOLEAN_RESPONSE_SCHEMA));
//                return GsonParser.parseObject(response.asString(), (Class<T>) Boolean.class);
//            } catch (APIException e) {
//                Logger.getLogger(OttUserService.class).error("Boolean.class parse error");
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    public static <T> T get(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
//    public static <T> T getEncryptedUserId(String ks) {
//        // TODO: 3/19/2018 implement function
//        return null;
//    }
//
///*    public static <T> T list(String ks, Optional<OTTUserFilter> ottUserFilter) {
//        String body = listRequestBuilder(ks, ottUserFilter);
//        Response response = setPostRequest(body, SERVICE, LIST_ACTION);
//
//        if (isApiException(response)) {
//            return (T) getApiException(response);
//        } else {
//            try {
//                assertThat(response.asString(), matchesJsonSchemaInClasspath(BOOLEAN_RESPONSE_SCHEMA));
//                return GsonParser.parseListResponse(response.asString(), OTTUser.class);
//            } catch (APIException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        // KalturaOTTUserListResponse
//        // TODO: 3/19/2018 implement function
//        return null;
//    }*/
//
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
}


