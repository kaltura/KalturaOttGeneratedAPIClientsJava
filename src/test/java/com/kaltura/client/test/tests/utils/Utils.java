package com.kaltura.client.test.tests.utils;

import com.kaltura.client.test.servicesImpl.HouseholdDeviceServiceImpl;
import com.kaltura.client.test.servicesImpl.HouseholdServiceImpl;
import com.kaltura.client.test.servicesImpl.HouseholdUserServiceImpl;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import static com.kaltura.client.test.helper.Helper.generateOttUser;
import static com.kaltura.client.test.helper.Properties.*;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.loginImpl;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.registerImpl;

public class Utils {

    public static void createHouseHold() {

        // create, register and save master user
        Response<OTTUser> masterUserResponse = registerImpl(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        masterUser = masterUserResponse.results;

        // login master user and save ks
        Response<LoginResponse> loginResponse = loginImpl(PARTNER_ID, masterUser.getUsername(), GLOBAL_USER_PASSWORD, null, null);
        masterUserKS = loginResponse.results.getLoginSession().getKs();

        // create, add and save household
        Household household = new Household();
        household.setName(masterUser.getFirstName() + "'s Domain");
        household.setDescription(masterUser.getLastName() + "Description");
        HouseholdServiceImpl.addImpl(masterUserKS, household);
        sharedHousehold = household;

        // create, register and add non-master user to household
        Response<OTTUser> secondUserResponse = registerImpl(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        OTTUser secondUser = secondUserResponse.results;
//        loginImpl(PARTNER_ID, secondUser.getUsername(), GLOBAL_USER_PASSWORD, null, null);
        HouseholdUser householdUser = new HouseholdUser();
        householdUser.setUserId(secondUser.getId());
        householdUser.setIsMaster(false);
        HouseholdUserServiceImpl.addImpl(masterUserKS, householdUser);

        // create, add and save household device
        long uniqueString = System.currentTimeMillis();
        HouseholdDevice householdDevice = new HouseholdDevice();
        householdDevice.setUdid(String.valueOf(uniqueString));
        householdDevice.setBrandId(1);
        householdDevice.setName(String.valueOf(uniqueString) + "device");
        HouseholdDeviceServiceImpl.addImpl(masterUserKS, householdDevice);

        // login as master using household device
        loginResponse = loginImpl(PARTNER_ID, masterUser.getUsername(), GLOBAL_USER_PASSWORD, null, householdDevice.getUdid());
        masterUserKS = loginResponse.results.getLoginSession().getKs();

//        // set PG to HH
//        String operatorKs = operatorUserKs;
//        HouseholdPaymentGatewayService.setChargeID(operatorKs, masterUser.getId(), default_payment_gateway_external_id,"1234");
    }
}
