package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionManagementUtils extends BaseUtils {

    @Accessors(fluent = true)
    @Data
    public static class PermissionItem {
        private List<String> permissions;
        private List<String> excludedPermissions;
        private String name;
        private String service;
        private String action;
        private String type;
    }

    @Accessors(fluent = true)
    @Data
    public static class Permission {
        private String name;
        private String usersGroup;
    }

    @Accessors(fluent = true)
    @Data
    public static class Role {
        private List<String> permissions;
        private List<String> excludedPermissions;
        private String name;
    }


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
        Logger.getLogger(PermissionManagementUtils.class).debug("started executeCommandsInColsole");
        StringBuilder output = new StringBuilder();
        StringBuilder input = new StringBuilder();
        for(String command: commands){
            input.append(command + " ");
        }
        Logger.getLogger(PermissionManagementUtils.class).debug("INPUT: " + input.toString());

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

        Logger.getLogger(PermissionManagementUtils.class).debug("OUTPUT: " + output.toString());
        return output.toString();
    }

    public static void insertDataInAllTables(String path2ResultFile, String role, String usersGroup, String permissionItemName,
                                             String service, String action, String permissionItemObject, String parameter, boolean isJson) {
        long roleId = PermissionsManagementDBUtils.insertRole(role);
        long permissionId = PermissionsManagementDBUtils.insertPermission(role, 2, usersGroup);
        long permissionRoleId = PermissionsManagementDBUtils.insertPermissionRole(roleId, permissionId, 0);

        long permissionItemId = PermissionsManagementDBUtils.insertPermissionItem(permissionItemName, 1, service, action, permissionItemObject, parameter);
        long permissionPermissionItemId = PermissionsManagementDBUtils.insertPermissionPermissionItem(permissionId, permissionItemId, 0);

        generateFileWithInsertedIntoDBData(path2ResultFile, role, usersGroup, permissionItemName, service, action,
                permissionItemObject, parameter, roleId, permissionId, permissionRoleId, permissionItemId, permissionPermissionItemId, isJson);
    }

    public static void generateFileWithInsertedIntoDBData(String path2ResultFile, String role, String usersGroup, String permissionItemName,
                                                          String service, String action, String permissionItemObject, String parameter, long roleId, long permissionId,
                                                          long permissionRoleId, long permissionItemId, long permissionPermissionItemId, boolean isJson) {
        try {
            File file = new File(path2ResultFile);
            PrintWriter writer = new PrintWriter(file);
            if (isJson) {
                printRolesFormat(writer, role);
                // to separate
                writer.println(";");
                printPermissionsFormat(writer, role, usersGroup);
                // to separate
                writer.println(";");
                printServiceFormat(writer, role, permissionItemName, service, action);
            } else {
                // XML
                printOpenTag(writer);
                printRole(writer, roleId, role);
                printRolePermission(writer, permissionRoleId, roleId, permissionId, 0, role, role);
                printPermission(writer, permissionId, role, 2, usersGroup);
                printPermissionItem(writer, permissionItemId, permissionItemName, 1, service, action, permissionItemObject, parameter);
                printPermissionPermissionItem(writer, permissionPermissionItemId, permissionId, permissionItemId, 0, permissionItemName, role);
                printCloseTag(writer);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printServiceFormat(PrintWriter writer, String role, String permissionItemName, String service, String action) {
        writer.println("{");
        writer.println("\"permissions\": [");
        writer.println("\"" + role + "\"");
        writer.println("],");
        writer.println("\"excluded_permissions\": [],");
        writer.println("\"name\": \"" + permissionItemName + "\",");
        writer.println("\"service\": \"" + service + "\",");
        writer.println("\"action\": \"" + action + "\",");
        writer.println("\"type\": \"Action\"");
        writer.println("}");
    }

    private static void printPermissionsFormat(PrintWriter writer, String role, String usersGroup) {
        writer.println("{");
        writer.println("\"name\": \"" + role + "\",");
        writer.println("\"users_group\": \"" + usersGroup + "\"");
        writer.println("}");
    }

    private static void printRolesFormat(PrintWriter writer, String role) {
        writer.println("{");
        writer.println("\"permissions\": [");
        writer.println("\"" + role + "\"");
        writer.println("],");
        writer.println("\"excluded_permissions\": [],");
        writer.println("\"name\": \"" + role + "\"");
        writer.println("}");
    }

    public static void generateFileWithInvalidTagForRole(String path2ResultFile, String roleName, int roleId) {
        try {
            File file = new File(path2ResultFile);
            PrintWriter writer = new PrintWriter(file);
            printOpenTag(writer);
            writer.println("<role1>"); // this 1 here to make tag invalid
            writer.println("<id>" + roleId + "</id>");
            writer.println("<name>" + roleName + "</name>");
            writer.println("</role1>"); // this 1 here to make tag invalid
            printCloseTag(writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printOpenTag(PrintWriter writer) {
        writer.println("<?xml version=\"1.0\" standalone=\"yes\"?>");
        writer.println("<permissions_dataset>");
    }

    private static void printCloseTag(PrintWriter writer) {
        writer.println("</permissions_dataset>");
    }

    public static void generateFileForRole(String path2ResultFile, String roleName, long roleId) {
        try {
            File file = new File(path2ResultFile);
            PrintWriter writer = new PrintWriter(file);
            printOpenTag(writer);
            printRole(writer, roleId, roleName);
            printCloseTag(writer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getConsoleCommand(String path2Util, String parameters) {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util);
        commands.add(parameters);
        return commands;
    }
}

