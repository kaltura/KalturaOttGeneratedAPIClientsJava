package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListTests extends BaseTest {

    private Household household;

    @BeforeClass
    private void ottUser_list_tests_setup() {
        household = HouseholdUtils.createHouseHold(4, 1);
    }

    @Description("ottUser/action/list - list from master ks")
    @Test
    private void list_from_master_ks() {

//        OTTUser masterUser = BaseUtils.getMasterUserFromHousehold(household);
//        OttUserServiceImpl.list(administratorKS, )
        // TODO: 3/25/2018 finish test impl 
//        list()
//
//        assertThat(loginResponse.results).isNull();
//        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());
    }
}
