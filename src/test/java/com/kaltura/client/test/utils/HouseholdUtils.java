package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.services.HouseholdDeviceService;
import com.kaltura.client.services.HouseholdPaymentGatewayService;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kaltura.client.services.HouseholdDeviceService.AddHouseholdDeviceBuilder;
import static com.kaltura.client.services.HouseholdDeviceService.ListHouseholdDeviceBuilder;
import static com.kaltura.client.services.HouseholdPaymentGatewayService.SetChargeIDHouseholdPaymentGatewayBuilder;
import static com.kaltura.client.services.HouseholdService.AddHouseholdBuilder;
import static com.kaltura.client.services.HouseholdUserService.*;
import static com.kaltura.client.services.HouseholdUserService.list;
import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;

public class HouseholdUtils extends BaseUtils {

    // create household
    public static Household createHousehold(int numberOfUsersInHoushold, int numberOfDevicesInHousehold, boolean isPreparePG) {

        // register master user
        RegisterOttUserBuilder registerOttUserBuilder = register(partnerId, generateOttUser(), defaultUserPassword);
        OTTUser masterUser = executor.executeSync(registerOttUserBuilder).results;

        // login master user
        LoginOttUserBuilder loginOttUserBuilder = login(partnerId, masterUser.getUsername(), defaultUserPassword, null, null);
        Response<LoginResponse> loginResponse = executor.executeSync(loginOttUserBuilder);
        masterUser = loginResponse.results.getUser();
        String masterUserKs = loginResponse.results.getLoginSession().getKs();

        // add household
        Household household = new Household();
        household.setName(masterUser.getFirstName() + "s Domain");
        household.setDescription(masterUser.getLastName() + " Description");

        AddHouseholdBuilder addHouseholdBuilder = HouseholdService.add(household)
                .setKs(masterUserKs);
        household = executor.executeSync(addHouseholdBuilder).results;

        // add additional users to household
        for (int i = 0; i < numberOfUsersInHoushold; i++) {
            // register additional user
            registerOttUserBuilder = register(partnerId, generateOttUser(), defaultUserPassword);
            OTTUser additionalUser = executor.executeSync(registerOttUserBuilder).results;

            HouseholdUser householdUser = new HouseholdUser();
            householdUser.setUserId(additionalUser.getId());
            householdUser.setIsMaster(false);

            // add additional user to household
            AddHouseholdUserBuilder addHouseholdUserBuilder = add(householdUser)
                    .setKs(masterUserKs);
            executor.executeSync(addHouseholdUserBuilder);
        }

        // add household devices
        for (int i = 0; i < numberOfDevicesInHousehold; i++) {
            // create household device
            HouseholdDevice householdDevice = generateHouseholdDevice();

            // add device to household
            AddHouseholdDeviceBuilder addHouseholdDeviceBuilder = HouseholdDeviceService.add(householdDevice)
                    .setKs(masterUserKs);
            executor.executeSync(addHouseholdDeviceBuilder);
        }

        if (isPreparePG) {
            // TODO: there should be added logic with getting and using default PG currently it all hardcoded
            //HouseholdPaymentGateway/action/setChargeId
            SetChargeIDHouseholdPaymentGatewayBuilder setChargeIDHouseholdPaymentGatewayBuilder = HouseholdPaymentGatewayService.setChargeID("0110151474255957105", "1234")
                    .setKs(getOperatorKs())
                    .setUserId(Integer.valueOf(masterUser.getId()));
            executor.executeSync(setChargeIDHouseholdPaymentGatewayBuilder);
        }

        return household;
    }

    // generate household device
    public static HouseholdDevice generateHouseholdDevice() {
        long uniqueString = System.currentTimeMillis();
        HouseholdDevice householdDevice = new HouseholdDevice();
        householdDevice.setUdid(String.valueOf(uniqueString));
        Random r = new Random();
        householdDevice.setBrandId(r.nextInt(28 - 1) + 1);
        householdDevice.setName(String.valueOf(uniqueString) + "device");

        return householdDevice;
    }

    // get users list from given household
    public static List<HouseholdDevice> getDevicesList(Household household) {

        HouseholdDeviceFilter filter = new HouseholdDeviceFilter();
        filter.setHouseholdIdEqual(Math.toIntExact(household.getId()));

        //HouseholdDevice/action/list
        ListHouseholdDeviceBuilder listHouseholdDeviceBuilder = HouseholdDeviceService.list(filter)
                .setKs(getAdministratorKs());
        Response<ListResponse<HouseholdDevice>> devicesResponse = executor.executeSync(listHouseholdDeviceBuilder);

        return devicesResponse.results.getObjects();
    }

    // get users list from given household
    public static List<HouseholdUser> getUsersList(Household household) {
        HouseholdUserFilter filter = new HouseholdUserFilter();
        filter.setHouseholdIdEqual(Math.toIntExact(household.getId()));

        ListHouseholdUserBuilder listHouseholdUserBuilder = list(filter)
                .setKs(getAdministratorKs());
        Response<ListResponse<HouseholdUser>> usersResponse = executor.executeSync(listHouseholdUserBuilder);

        return usersResponse.results.getObjects();
    }

    // get master user from given household
    public static HouseholdUser getMasterUser(Household household) {
        List<HouseholdUser> users = getUsersList(household);

        for (HouseholdUser user : users) {
            if (user.getIsMaster() != null && user.getIsMaster()) {
                return user;
            }
        }

        Logger.getLogger(BaseUtils.class).error("can't find master user in household");
        return null;
    }

    // get default user from given household
    public static HouseholdUser getDefaultUser(Household household) {
        List<HouseholdUser> users = getUsersList(household);

        for (HouseholdUser user : users) {
            if (user.getIsDefault() != null && user.getIsDefault()) {
                return user;
            }
        }

        Logger.getLogger(BaseUtils.class).error("can't find default user in household");
        return null;
    }

    // get regular users list from given household
    public static List<HouseholdUser> getRegularUsersList(Household household) {
        List<HouseholdUser> users = getUsersList(household);
        List<HouseholdUser> usersToRemove = new ArrayList<>();

        for (HouseholdUser user : users) {
            if (user.getIsDefault() != null && user.getIsDefault()) {
                usersToRemove.add(user);
            }
            if (user.getIsMaster() != null && user.getIsMaster()) {
                usersToRemove.add(user);
            }
        }
        users.removeAll(usersToRemove);
        return users;
    }

    // Get master KS by providing household object
    public static String getHouseholdMasterUserKs(Household household) {
        HouseholdUser masterUser = getMasterUser(household);
        return OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()));
    }

    // Get regular user KS by providing household object
    public static String getHouseholdUserKs(Household household) {
        HouseholdUser user = getRegularUsersList(household).get(0);
        return OttUserUtils.getKs(Integer.parseInt(user.getUserId()));
    }

    // Get master KS by providing household object
    public static String getHouseholdMasterUserKs(Household household, String udid) {
        HouseholdUser masterUser = getMasterUser(household);
        return OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);
    }

    // Get regular user KS by providing household object
    public static String getHouseholdUserKs(Household household, String udid) {
        HouseholdUser user = getRegularUsersList(household).get(0);
        return OttUserUtils.getKs(Integer.parseInt(user.getUserId()), udid);
    }
}
