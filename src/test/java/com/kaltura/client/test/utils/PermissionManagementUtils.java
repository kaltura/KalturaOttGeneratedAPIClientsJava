package com.kaltura.client.test.utils;

import com.kaltura.client.test.utils.dbUtils.DBUtils;
import java.io.*;
import java.util.List;

public class PermissionManagementUtils extends BaseUtils {

    public static void printPermissionPermissionItem(PrintWriter writer, long id, long permissionId, long permissionItemId,
                                               int isExcluded, String permissionItemName, String permissionName) {
        writer.println("<permission_permission_item>");
        writer.println("<id>" + id + "</id>");
        writer.println("<permission_id>" + permissionId + "</permission_id>");
        writer.println("<permission_item_id>" + permissionItemId + "</permission_item_id>");
        writer.println("<is_excluded>" + isExcluded + "</is_excluded>");
        writer.println("<permission_item_name>" + permissionItemName + "</permission_item_name>");
        writer.println("<permission_name>" + permissionName + "</permission_name>");
        writer.println("</permission_permission_item>");
    }

    public static void printPermissionItem(PrintWriter writer, long id, String name, int type, String service, String action,
                                     String object, String parameter) {
        writer.println("<permission_item>");
        writer.println("<id>" + id + "</id>");
        writer.println("<name>" + name + "</name>");
        writer.println("<type>" + type + "</type>");
        writer.println("<service>" + service + "</service>");
        writer.println("<action>" + action + "</action>");
        writer.println("<object>" + object + "</object>");
        writer.println("<parameter>" + parameter + "</parameter>");
        writer.println("</permission_item>");
    }

    public static void printPermission(PrintWriter writer, long id, String name, int type, String usersGroup) {
        writer.println("<permission>");
        writer.println("<id>" + id + "</id>");
        writer.println("<name>" + name + "</name>");
        writer.println("<type>" + type + "</type>");
        writer.println("<users_group>" + usersGroup + "</users_group>");
        writer.println("</permission>");
    }

    public static void printRolePermission(PrintWriter writer, long permissionRoleId, long roleId, long permissionId,
                                     int isExcluded, String roleName, String permissionName) {
        writer.println("<role_permission>");
        writer.println("<id>" + permissionRoleId + "</id>");
        writer.println("<role_id>" + roleId + "</role_id>");
        writer.println("<permission_id>" + permissionId + "</permission_id>");
        writer.println("<is_excluded>" + isExcluded + "</is_excluded>");
        writer.println("<role_name>" + roleName + "</role_name>");
        writer.println("<permission_name>" + permissionName + "</permission_name>");
        writer.println("</role_permission>");
    }

    public static void printRole(PrintWriter writer, Long roleId, String roleName) {
        writer.println("<role>");
        writer.println("<id>" + roleId + "</id>");
        writer.println("<name>" + roleName + "</name>");
        writer.println("</role>");
    }


    public static String executeCommandsInColsole(List<String> commands) {
        StringBuilder output = new StringBuilder();

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectErrorStream(true);
        BufferedReader inStreamReader;
        try {
            Process process = pb.start();
            inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = inStreamReader.readLine()) != null){
                output.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            output.append(e.getMessage());
        }

        return output.toString();
    }

    public static void insertDataInAllTables(String path2Util, String role, String usersGroup, String permissionItemName,
                                    String service, String action, String permissionItemObject, String parameter) {
        long roleId = DBUtils.insertRole(role);
        long permissionId = DBUtils.insertPermission(role, 2, usersGroup);
        long permissionRoleId = DBUtils.insertPermissionRole(roleId, permissionId, 0);

        long permissionItemId = DBUtils.insertPermissionItem(permissionItemName, 1, service, action, permissionItemObject, parameter);
        long permissionPermissionItemId = DBUtils.insertPermissionPermissionItem(permissionId, permissionItemId, 0);

        generateFileWithInsertedIntoDBData(path2Util, role, usersGroup, permissionItemName, service, action,
                permissionItemObject, parameter, roleId, permissionId, permissionRoleId, permissionItemId, permissionPermissionItemId);
    }

    private static void generateFileWithInsertedIntoDBData(String path2Util, String role, String usersGroup, String permissionItemName,
        String service, String action, String permissionItemObject, String parameter, long roleId, long permissionId,
        long permissionRoleId, long permissionItemId, long permissionPermissionItemId) {
        try {
            // TODO: update file name as parameter
            File file = new File(path2Util + "333\\" + "import_data.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println("<?xml version=\"1.0\" standalone=\"yes\"?>");
            writer.println("<permissions_dataset>");
            printRole(writer, roleId, role);
            printRolePermission(writer, permissionRoleId, roleId, permissionId, 0, role, role);
            printPermission(writer, permissionId, role, 2, usersGroup);
            printPermissionItem(writer, permissionItemId, permissionItemName, 1, service, action, permissionItemObject, parameter);
            printPermissionPermissionItem(writer, permissionPermissionItemId, permissionId, permissionItemId, 0, permissionItemName, role);
            writer.println("</permissions_dataset>");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

