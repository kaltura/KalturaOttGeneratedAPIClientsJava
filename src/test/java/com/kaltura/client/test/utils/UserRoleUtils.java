package com.kaltura.client.test.utils;

import com.kaltura.client.types.UserRole;
import java.util.Random;

public class UserRoleUtils extends BaseUtils {


    public static UserRole generateUserRole() {
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
