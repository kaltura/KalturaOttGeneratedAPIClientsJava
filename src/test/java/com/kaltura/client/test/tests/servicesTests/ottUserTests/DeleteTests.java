package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.tests.utils.Utils;
import com.kaltura.client.types.Household;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteTests extends BaseTest {

    @BeforeClass
    public void ottUser_login_tests_setup() {


//        HouseholdUserFilter filter = new HouseholdUserFilter();
//        filter.setHouseholdIdEqual(Math.toIntExact(household.getId()));
//
//        Response<ListResponse<HouseholdUser>> users = HouseholdUserServiceImpl.listImpl(administratorKS, filter);
//        System.out.println(users.results.getTotalCount());
    }

    @Description("ottUser/action/delete - delete")
    @Test
    private void delete() {
        Household household = Utils.createHouseHold(2, 0);
        System.out.println(household.getId());

//        deleteImpl(adminKS, Integer.parseInt(user.getId()));
        // TODO: 3/25/2018 finish test 
    }
}
