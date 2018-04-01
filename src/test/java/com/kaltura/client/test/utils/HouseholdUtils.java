package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.test.servicesImpl.HouseholdDeviceServiceImpl;
import com.kaltura.client.test.servicesImpl.HouseholdServiceImpl;
import com.kaltura.client.test.servicesImpl.HouseholdUserServiceImpl;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.List;
import java.util.Random;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.login;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.tests.BaseTest.administratorKs;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;

public class HouseholdUtils extends BaseUtils {

    // create household
    public static Household createHouseHold(int numberOfUsersInHoushold, int numberOfDevicesInHousehold) {

        // create and register
        Response<OTTUser> masterUserResponse = register(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        OTTUser masterUser = masterUserResponse.results;

        // login master user
        Response<LoginResponse> loginResponse = login(PARTNER_ID, masterUser.getUsername(), GLOBAL_USER_PASSWORD, null, null);
        masterUser = loginResponse.results.getUser();
        String masterUserKS = loginResponse.results.getLoginSession().getKs();

        // create and add household
        Household household = new Household();
        household.setName(masterUser.getFirstName() + "s Domain");
        household.setDescription(masterUser.getLastName() + " Description");

        Response<Household> householdResponse = HouseholdServiceImpl.add(masterUserKS, household);
        household = householdResponse.results;

        // create, register and add non-master user to household
        for (int i = 0; i < numberOfUsersInHoushold - 1; i++) {
            Response<OTTUser> additionalUserResponse = register(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
            OTTUser additionalUser = additionalUserResponse.results;
            HouseholdUser householdUser = new HouseholdUser();
            householdUser.setUserId(additionalUser.getId());
            householdUser.setIsMaster(false);
            HouseholdUserServiceImpl.add(masterUserKS, householdUser);
        }

        // create, add and save household device
        for (int i = 0; i < numberOfDevicesInHousehold; i++) {
            long uniqueString = System.currentTimeMillis();
            HouseholdDevice householdDevice = new HouseholdDevice();
            householdDevice.setUdid(String.valueOf(uniqueString));
            Random r = new Random();
            householdDevice.setBrandId(r.nextInt(30 - 1) + 1);
            householdDevice.setName(String.valueOf(uniqueString) + "device");
            HouseholdDeviceServiceImpl.add(masterUserKS, householdDevice);
        }

        return household;
    }

    // get users list from given household
    public static List<HouseholdUser> getUsersListFromHouseHold(Household household) {
        HouseholdUserFilter filter = new HouseholdUserFilter();
        filter.setHouseholdIdEqual(Math.toIntExact(household.getId()));
        Response<ListResponse<HouseholdUser>> usersResponse = HouseholdUserServiceImpl.list(administratorKs, filter);
        return usersResponse.results.getObjects();
    }

    // get master user from given household
    public static HouseholdUser getMasterUserFromHousehold(Household household) {
        List<HouseholdUser> users = getUsersListFromHouseHold(household);

        for (HouseholdUser user : users) {
            if (user.getIsMaster() != null && user.getIsMaster()) {
                return user;
            }
        }

        Logger.getLogger(BaseUtils.class).error("can't find master user in household");
        return null;
    }

    // get default user from given household
    public static HouseholdUser getDefaultUserFromHousehold(Household household) {
        List<HouseholdUser> users = getUsersListFromHouseHold(household);

        for (HouseholdUser user : users) {
            if (user.getIsDefault() != null && user.getIsDefault()) {
                return user;
            }
        }

        Logger.getLogger(BaseUtils.class).error("can't find default user in household");
        return null;
    }
}
