package com.kaltura.client.test.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kaltura.client.Logger;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.GsonParser;
import org.json.JSONObject;

import java.util.Optional;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class OttUserServiceImpl {

    private static final String SERVICE = "ottUser";

    private static final String LOGIN_ACTION = "login";
    private static final String ANONYMOUSLOGIN_ACTION = "anonymousLogin";
    private static final String REGISTER_ACTION = "register";
    private static final String ACTIVATE_ACTION = "activate";
    private static final String DELETE_ACTION = "delete";
    private static final String LIST_ACTION = "list";

    private static final String LOGIN_RESPONSE_SCHEMA = "KalturaLoginResponse_Schema.json";
    private static final String LOGIN_SESSION_SCHEMA = "KalturaLoginSession_Schema.json";
    private static final String OTT_USER_SCHEMA = "KalturaOttUser_Schema.json";


    public static void login(String username, String password, Optional<String> udid, Optional<JsonObject> extra_params) {

    }

//    public static <T> T anonymousLogin(Optional<String> udid) {
//        String body = anonymousLoginRequestBuilder(udid);
//        Response response = setPostRequest(body, SERVICE, ANONYMOUSLOGIN_ACTION);
//
//        if (isApiException(response)) {
//            return (T) getApiException(response);
//        } else {
//            assertThat(response.asString(), matchesJsonSchemaInClasspath(LOGIN_SESSION_SCHEMA));
//            try {
//                return GsonParser.parseObject(response.asString(), (Class<T>) LoginSession.class);
//            } catch (APIException e) {
//                Logger.getLogger(OttUserService.class).error("LoginSession.class parse error");
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    public static <T> T register(OTTUser ottUser, String password) {
//        String body = registerRequestBuilder(ottUser, password);
//        Response response = setPostRequest(body, SERVICE, REGISTER_ACTION);
//
//        if (isApiException(response)) {
//            return (T) getApiException(response);
//        } else {
//            assertThat(response.asString(), matchesJsonSchemaInClasspath(OTT_USER_SCHEMA));
//            try {
//                return GsonParser.parseObject(response.asString(), (Class<T>) OTTUser.class);
//            } catch (APIException e) {
//                Logger.getLogger(OttUserService.class).error("OTTUser.class parse error");
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    // TODO: 3/15/2018 ask Max if we need to expose partnerId to the crud function level
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
//
//    ////////////////////////
//    /////help functions/////
//    ////////////////////////
//    private static String loginRequestBuilder(String username, String password, Optional<String> udid, Optional<JSONObject> extra_params) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject
//                .put("partnerId", partner_id)
//                .put("apiVersion", api_version)
//                .put("username", username)
//                .put("password", password);
//
//        udid.ifPresent(s -> jsonObject.put("udid", s));
//        extra_params.ifPresent(jsonObject1 -> jsonObject.put("extra_params", jsonObject1));
////        String argument = optionalArgument.or("reasonable default");
//
//        return jsonObject.toString();
//    }
//
//    private static String anonymousLoginRequestBuilder(Optional<String> udid) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject
//                .put("apiVersion", api_version)
//                .put("partnerId", partner_id);
//
//        udid.ifPresent(s -> jsonObject.put("udid", s));
//
//        return jsonObject.toString();
//    }
//
//    //TODO: add later fields "userType": OTTUserType and "dynamicData": map of StringValue if needed
//    private static String registerRequestBuilder(OTTUser ottUser, String password) {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("apiVersion", api_version);
//        jsonObject.addProperty("partnerId", partner_id);
//        jsonObject.addProperty("password", password);
//
//        JsonElement element = new Gson().fromJson(ottUser.toParams().toString(), JsonElement.class);
//        jsonObject.add("user", element);
//
//        return jsonObject.toString();
//    }
//
//    private static String activateRequestBuilder(String ks, String username, String activationToken) {
//        JSONObject jsonObject = getBaseRequestBody(ks);
//        jsonObject
//                .put("partnerId", partner_id)
//                .put("username", username)
//                .put("activationToken", activationToken);
//
//        return jsonObject.toString();
//    }
//
//    private static String listRequestBuilder(String ks, Optional<OTTUserFilter> ottUserFilter) {
//        JsonObject jsonObject = getBaseRequestBodyGson(ks);
//        if (ottUserFilter.isPresent()) {
//            JsonElement element = new Gson().fromJson(ottUserFilter.get().toParams().toString(), JsonElement.class);
//            jsonObject.add("filter", element);
//        }
//        return jsonObject.toString();
//    }
}


