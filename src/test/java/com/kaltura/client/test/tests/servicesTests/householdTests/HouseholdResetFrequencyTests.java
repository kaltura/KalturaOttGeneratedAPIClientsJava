package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.enums.HouseholdFrequencyType;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.HouseholdUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdDevice;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kaltura.client.services.HouseholdDeviceService.delete;
import static com.kaltura.client.services.HouseholdService.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdResetFrequencyTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;
    private String masterUserKs;

    @BeforeClass
    private void household_resetFrequencyTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 3;
        int numberOfDevicesInHousehold = 3;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        masterUser = HouseholdUtils.getMasterUser(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesList(household).get(0).getUdid();
        masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/resetFrequency - household devices")
    @Test
    private void resetFrequency_household_devices() {
        // delete devices until error 1014 return
        List<HouseholdDevice> devices = HouseholdUtils.getDevicesList(household);

        executor.executeSync(delete(devices.get(1).getUdid()).setKs(masterUserKs));
        Response<Boolean> booleanResponse = executor.executeSync(delete(devices.get(2).getUdid()).setKs(masterUserKs));
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1014).getCode());

        // reset frequency for household devices
        Response<Household> householdResponse = executor.executeSync(resetFrequency(HouseholdFrequencyType.DEVICES)
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));
        assertThat(householdResponse.error).isNull();
        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());

        // delete additional device to verify frequency was reset
        booleanResponse = executor.executeSync(delete(devices.get(2).getUdid()).setKs(masterUserKs));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert devices list size = 1
        devices = HouseholdUtils.getDevicesList(household);
        assertThat(devices.size()).isEqualTo(1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/resetFrequency - household users")
    @Test
    private void resetFrequency_household_users() {
        // delete users until error 1014 return
        List<HouseholdUser> users = HouseholdUtils.getRegularUsersList(household);

        executor.executeSync(HouseholdUserService.delete(users.get(0).getUserId()).setKs(masterUserKs));
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdUserService.delete(users.get(1).getUserId()).setKs(masterUserKs));
        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1014).getCode());

        // reset frequency for household users
        Response<Household> householdResponse = executor.executeSync(resetFrequency(HouseholdFrequencyType.USERS)
                .setKs(getOperatorKs())
                .setUserId(Integer.valueOf(masterUser.getUserId())));
        assertThat(householdResponse.error).isNull();
        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());

        // delete additional user to verify frequency was reset
        booleanResponse = executor.executeSync(HouseholdUserService.delete(users.get(1).getUserId()).setKs(masterUserKs));
        assertThat(booleanResponse.results.booleanValue()).isTrue();

        // assert regular users list size = 1
        users = HouseholdUtils.getRegularUsersList(household);
        assertThat(users.size()).isEqualTo(1);
    }

    @AfterClass
    private void household_resetFrequencyTests_afterClass() {
        // delete household
        executor.executeSync(HouseholdService.delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }
}
