package com.kaltura.client.test.tests.utils;

import com.kaltura.client.test.servicesImpl.HouseholdServiceImpl;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.LoginResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;

import static com.kaltura.client.test.helper.Helper.generateOttUser;
import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.loginImpl;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.registerImpl;

public class Utils {

    public static Household createHouseHold(int numberOfUsersInHoushold, int numberOfDevicesInHousehold) {

        // create and register
        Response<OTTUser> masterUserResponse = registerImpl(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        OTTUser masterUser = masterUserResponse.results;

        // login master user
        Response<LoginResponse> loginResponse = loginImpl(PARTNER_ID, masterUser.getUsername(), GLOBAL_USER_PASSWORD, null, null);
        String masterUserKS = loginResponse.results.getLoginSession().getKs();

        // create and add household
        Household household = new Household();
        household.setName(masterUser.getFirstName() + "'s Domain");
        household.setDescription(masterUser.getLastName() + "Description");
        HouseholdServiceImpl.addImpl(masterUserKS, household);
//
//        // create, register and add non-master user to household
//        for (int i = 0; i < numberOfUsersInHoushold - 1; i++) {
//            Response<OTTUser> additionalUserResponse = registerImpl(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
//            OTTUser additionalUser = additionalUserResponse.results;
//            HouseholdUser householdUser = new HouseholdUser();
//            householdUser.setUserId(additionalUser.getId());
//            householdUser.setIsMaster(false);
//            HouseholdUserServiceImpl.addImpl(masterUserKS, householdUser);
//        }
//
//        // create, add and save household device
//        for (int i = 0; i < numberOfDevicesInHousehold; i++) {
//            long uniqueString = System.currentTimeMillis();
//            HouseholdDevice householdDevice = new HouseholdDevice();
//            householdDevice.setUdid(String.valueOf(uniqueString));
//            Random r = new Random();
//            householdDevice.setBrandId(r.nextInt(30 - 1) + 1);
//            householdDevice.setName(String.valueOf(uniqueString) + "device");
//            HouseholdDeviceServiceImpl.addImpl(masterUserKS, householdDevice);
//        }

        return household;

//        // set PG to HH
//        String operatorKs = operatorUserKs;
//        HouseholdPaymentGatewayService.setChargeID(operatorKs, masterUser.getId(), default_payment_gateway_external_id,"1234");
    }
}
