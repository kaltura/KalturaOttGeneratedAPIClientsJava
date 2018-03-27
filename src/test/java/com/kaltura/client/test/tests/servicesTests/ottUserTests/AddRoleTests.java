package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.servicesImpl.UserRoleServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.UserRole;
import com.kaltura.client.types.UserRoleFilter;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static com.kaltura.client.test.helper.Helper.generateOttUser;
import static com.kaltura.client.test.helper.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.helper.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;

public class AddRoleTests extends BaseTest {

    private OTTUser user;

    @BeforeClass
    private void ottUser_addRole_tests_setup() {
        user = generateOttUser();
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, user, GLOBAL_USER_PASSWORD);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/addRole - addRole")
    @Test(enabled = false)
    // TODO: 3/27/2018 finish and fix test 
    private void addRole() {
        OttUserServiceImpl.addRole(administratorKS, Optional.of(Integer.valueOf(user.getId())), 3);

        UserRoleFilter filter = new UserRoleFilter();
        filter.setIdIn(user.getId());

        Response<ListResponse<UserRole>> userRoleListResponse = UserRoleServiceImpl.list(administratorKS, filter);
        List<UserRole> userRoles = userRoleListResponse.results.getObjects();

        for (UserRole userRole : userRoles) {
            System.out.println(userRole.getId().toString());
        }

    }
}
