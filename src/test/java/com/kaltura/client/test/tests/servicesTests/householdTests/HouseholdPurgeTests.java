package com.kaltura.client.test.tests.servicesTests.householdTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HouseholdPurgeTests extends BaseTest {

    private Household household;
    private HouseholdUser masterUser;
    private String masterUserKs;

    @BeforeClass
    private void household_purgeTests_beforeClass() {
        // set household
        int numberOfUsersInHousehold = 1;
        int numberOfDevicesInHousehold = 1;
        household = HouseholdUtils.createHousehold(numberOfUsersInHousehold, numberOfDevicesInHousehold, true);
        masterUser = HouseholdUtils.getMasterUser(household);

        // set masterUserKs
        String udid = HouseholdUtils.getDevicesList(household).get(0).getUdid();
        masterUserKs = OttUserUtils.getKs(Integer.parseInt(masterUser.getUserId()), udid);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("household/action/purge")
    @Test(enabled = false)
    private void purge() {
        Response<Boolean> booleanResponse = executor.executeSync(HouseholdService.purge(Math.toIntExact(household.getId()))
                .setKs(getOperatorKs()));
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        System.out.println(household.getId());


//        // delete devices until error 1014 return
//        List<HouseholdDevice> devices = HouseholdUtils.getDevicesList(household);
//
//        executor.executeSync(delete(devices.get(1).getUdid()).setKs(masterUserKs));
//        Response<Boolean> booleanResponse = executor.executeSync(delete(devices.get(2).getUdid()).setKs(masterUserKs));
//        assertThat(booleanResponse.error.getCode()).isEqualTo(BaseUtils.getAPIExceptionFromList(1014).getCode());
//
//        // reset frequency for household devices
//        Response<Household> householdResponse = executor.executeSync(resetFrequency(HouseholdFrequencyType.DEVICES)
//                .setKs(getOperatorKs())
//                .setUserId(Integer.valueOf(masterUser.getUserId())));
//        assertThat(householdResponse.error).isNull();
//        assertThat(householdResponse.results.getId()).isEqualTo(household.getId());
//
//        // delete additional device to verify frequency was reset
//        booleanResponse = executor.executeSync(delete(devices.get(2).getUdid()).setKs(masterUserKs));
//        assertThat(booleanResponse.results.booleanValue()).isTrue();
//
//        // assert devices list size = 1
//        devices = HouseholdUtils.getDevicesList(household);
//        assertThat(devices.size()).isEqualTo(1);
    }

    @AfterClass
    private void household_purgeTests_afterClass() {
        // delete household
        executor.executeSync(HouseholdService.delete(Math.toIntExact(household.getId())).setKs(getOperatorKs()));
    }
}
