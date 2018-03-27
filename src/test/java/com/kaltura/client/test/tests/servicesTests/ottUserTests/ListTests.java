package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.utils.Utils;
import com.kaltura.client.types.Household;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;

public class ListTests extends BaseTest {

    @Description("ottUser/action/list - list")
    @Test
    public void list() {
        Household household = Utils.createHouseHold(3, 1);
        household.getId();

        // TODO: 3/25/2018 finish test impl 
//        list()
//
//        assertThat(loginResponse.results).isNull();
//        assertThat(loginResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(1011).getCode());
    }
}
