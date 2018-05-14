package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.UserRoleService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.UserRole;
import com.kaltura.client.types.UserRoleFilter;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.services.UserRoleService.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static com.kaltura.client.test.utils.OttUserUtils.getKs;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserAddRoleTests extends BaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/addRole - addRole")
    @Issue("BEO-5081")
    @Test(enabled = false)
    private void addRoleTest() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // generate new role
        UserRole userRole = generateUserRole();

        // add role
        AddUserRoleBuilder addUserRoleBuilder = add(userRole)
                .setKs(getAdministratorKs());
        executor.executeSync(addUserRoleBuilder);

        // add role to user
        AddRoleOttUserBuilder addRoleOttUserBuilder = addRole(userRole.getId())
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<Boolean> booleanResponse = executor.executeSync(addRoleOttUserBuilder);
        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);

        // get user roles
        UserRoleFilter filter = new UserRoleFilter();
        filter.setCurrentUserRoleIdsContains(true);

        int userId = Integer.parseInt(user.getId());
        ListUserRoleBuilder listUserRoleBuilder = UserRoleService.list(filter)
                .setKs(getKs(userId, null));
        List<UserRole> userRoles = executor.executeSync(listUserRoleBuilder).results.getObjects();

        // assert new added role is return
        List<Long> userRolesIds = new ArrayList<>();
        for (UserRole ur : userRoles) {
            userRolesIds.add(ur.getId());
        }

        assertThat(userRolesIds).contains(userRole.getId());

        // cleanup - delete role
        // TODO: 5/14/2018 finish test after when reletead bug will be fixed
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/addRole - addRole with invalid roleId - error ")
    @Issue("BEO-5083")
    @Test(enabled = false)
    private void addRole_with_invalid_roleId() {
        // register user
        OTTUser user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;

        // generate new role
//        UserRole userRole = generateUserRole();

        // add not existing role to user
        int invalidRoleId = -2;
        AddRoleOttUserBuilder addRoleOttUserBuilder = addRole(invalidRoleId)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<Boolean> booleanResponse = executor.executeSync(addRoleOttUserBuilder);

        assertThat(booleanResponse.results.booleanValue()).isEqualTo(true);
        
        // cleanup - delete role
        // TODO: 5/14/2018 finish test after when reletead bug will be fixed
    }

    private UserRole generateUserRole() {
        Random r = new Random();
        int low = 9000;
        int max = 10000;
        long roleId = r.nextInt(max - low) + low;

        UserRole userRole = new UserRole();
        userRole.setId(roleId);
        userRole.setName(String.valueOf(roleId));

        return userRole;
    }
}
