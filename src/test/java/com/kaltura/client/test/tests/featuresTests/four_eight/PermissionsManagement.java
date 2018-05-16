package com.kaltura.client.test.tests.featuresTests.four_eight;

import com.kaltura.client.test.utils.dbUtils.DBUtils;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-4885
 */
public class PermissionsManagement {
    // TODO: discuss where to save these values
    String path2Util = "C:\\123\\222\\";
    String mainFile = "PermissionsDeployment.exe";

    // TODO: change descriptions later
    @Test(enabled = false, description = "execute stored procedures related insert data into permission management related tables")
    private void insertDataIntoTables() {
        String role = "MaxTest";
        long roleId = DBUtils.insertRole(role);
        System.out.println("Role: " + roleId); // 468
        //DBUtils.deleteRoleAndItsPermissions(roleId);
        long permissionId = DBUtils.insertPermission(role, 2, "partner*");
        System.out.println("Permission: " + permissionId); // 26
        System.out.println("PermissionRole: " + DBUtils.insertPermissionRole(roleId, permissionId, 0)); // 622
        String name = "Asset_List_Max";
        String service = "asset";
        String action = "list";
        String permissionItemObject = "permissionItemObject";
        String parameter = "parameter";
        long permissionItemId = DBUtils.insertPermissionItem(name, 1, service, action, permissionItemObject, parameter);
        System.out.println("PermissionItem: " + permissionItemId); // 539
        long permissionPermissionItemId = DBUtils.insertPermissionPermissionItem(permissionId, permissionItemId, 0);
        System.out.println("PermissionPermissionItem: " + permissionPermissionItemId);//1063
    }

    @Test(enabled = false, description = "execute console util without parameters")
    private void checkRunningWithoutParameters() {
        StringBuilder output = new StringBuilder();

        ProcessBuilder pb = new ProcessBuilder(path2Util + mainFile);
        pb.redirectErrorStream(true);
        BufferedReader inStreamReader;
        try {
            Process process = pb.start();
            inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = inStreamReader.readLine()) != null){
                output.append(line);
            }

            assertThat(output.toString()).contains("Permissions deployment tool");
            assertThat(output.toString()).contains("Shortcut: e");
            assertThat(output.toString()).contains("Shortcut: i");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // return input stream back
            inStreamReader = new BufferedReader(new InputStreamReader(System.in));
        }
    }
}
