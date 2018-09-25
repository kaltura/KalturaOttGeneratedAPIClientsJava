package com.kaltura.client.test.utils;

import com.google.gson.*;
import com.kaltura.client.Logger;
import com.kaltura.client.test.tests.featuresTests.versions.four_eight.PermissionsManagementTests;
import com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionManagementUtils extends BaseUtils {

    // that file generated automatically
    public static String path2Log = "C:\\log\\permissions\\permissions.log";
    public static String path2Util = "C:\\123\\PermissionsExport\\bin\\Debug\\";
    public static String mainFile = "PermissionsDeployment.exe";
    public static String fullPath2Util = path2Util + mainFile;

    // these files are generated
    public static String dataFilePath = path2Util + "333\\" + "exp1.txt";
    public static String path2JsonFolder = path2Util + "333\\JSON\\";
    public static String generatedDataFilePath = path2Util + "333\\" + "import.txt";
    public static String path2JsonRoles = path2JsonFolder + "roles.json";
    public static String path2JsonPermissions = path2JsonFolder + "permissions.json";
    public static String path2JsonMethods = path2JsonFolder + "permission_items\\controllers\\";

    // json related strings
    public static final String SERVICE_PERMISSION_ITEMS_NODE = "permission_items";
    public static final String SERVICE_TYPE_DEFAULT_VALUE = "Action";
    public static final String SERVICE_PERMISSIONS_NODE = "permissions";
    public static final String SERVICE_EXCLUDED_PERMISSIONS_NODE = "excluded_permissions";
    public static final String SERVICE_NAME_NODE = "name";
    public static final String SERVICE_SERVICE_NODE = "service";
    public static final String SERVICE_ACTION_NODE = "action";
    public static final String SERVICE_TYPE_NODE = "type";

    public static final String PERMISSIONS_PERMISSIONS_NODE = "permissions";
    public static final String PERMISSIONS_NAME_NODE = "name";
    public static final String PERMISSIONS_USERS_GROUP_NODE = "users_group";

    public static final String ROLES_ROLES_NODE = "roles";
    public static final String ROLES_PERMISSIONS_NODE = "permissions";
    public static final String ROLES_EXCLUDED_PERMISSIONS_NODE = "excluded_permissions";
    public static final String ROLES_NAME_NODE = "name";

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

    private static void fillRolesFile(String roleName) {
        try {
            // get from file array of permissions
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(path2JsonRoles));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            JsonArray array = jsonObj.getAsJsonArray(ROLES_ROLES_NODE);

            // add into array of roles new role
            List<String> excludedPermissions = new ArrayList<String>();
            List<String> permissions = new ArrayList<String>();
            permissions.add(roleName);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonObj = new JsonObject();
            jsonObj.add(ROLES_PERMISSIONS_NODE, gson.toJsonTree(permissions).getAsJsonArray());
            jsonObj.add(ROLES_EXCLUDED_PERMISSIONS_NODE, gson.toJsonTree(excludedPermissions).getAsJsonArray());
            jsonObj.addProperty(ROLES_NAME_NODE, roleName);
            array.add(jsonObj);

            // overwrite role file
            jsonObj = new JsonObject();
            jsonObj.add(ROLES_ROLES_NODE, array);
            try (Writer writer = new FileWriter(path2JsonRoles, false)) {
                gson.toJson(jsonObj, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void fillServiceFile(String roleName, String permissionItemName, String serviceName, String actionName) {
        try {
            // get from file array of permissions related to service
            JsonParser parser = new JsonParser();
            String path2serviceFile = path2JsonMethods + serviceName + ".json";
            JsonElement jsonElement = parser.parse(new FileReader(path2serviceFile));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            JsonArray array = jsonObj.getAsJsonArray(SERVICE_PERMISSION_ITEMS_NODE);

            // create PermissionItem object with new data
            List<String> permissions = new ArrayList<String>();
            permissions.add(roleName);
            PermissionItem permissionItem = new PermissionItem()
                    .permissions(permissions)
                    .excludedPermissions(new ArrayList<>())
                    .name(permissionItemName)
                    .service(serviceName)
                    .action(actionName)
                    .type(SERVICE_TYPE_DEFAULT_VALUE);

            // add into array of permissions the PermissionItem object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonObj = getPermissionItemAsJsonObject(permissionItem, gson);
            array.add(jsonObj);

            // overwrite service file
            jsonObj = new JsonObject();
            jsonObj.add(SERVICE_PERMISSION_ITEMS_NODE, array);
            jsonObj.addProperty(SERVICE_TYPE_NODE, SERVICE_TYPE_DEFAULT_VALUE);

            try (Writer writer = new FileWriter(path2serviceFile, false)) {
                gson.toJson(jsonObj, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject getPermissionItemAsJsonObject(PermissionItem permissionItem, Gson gson) {
        JsonObject result = new JsonObject();

        result.add(SERVICE_PERMISSIONS_NODE, gson.toJsonTree(permissionItem.permissions()).getAsJsonArray());
        result.add(SERVICE_EXCLUDED_PERMISSIONS_NODE, gson.toJsonTree(permissionItem.excludedPermissions()).getAsJsonArray());
        result.addProperty(SERVICE_NAME_NODE, permissionItem.name());
        result.addProperty(SERVICE_SERVICE_NODE, permissionItem.service());
        result.addProperty(SERVICE_ACTION_NODE, permissionItem.action());
        result.addProperty(SERVICE_TYPE_NODE, SERVICE_TYPE_DEFAULT_VALUE);

        return result;
    }

    private static void fillPermissionsFile(String roleName, String usersGroup) {
        try {
            // get from file array of permissions
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(path2JsonPermissions));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            JsonArray array = jsonObj.getAsJsonArray(PERMISSIONS_PERMISSIONS_NODE);

            // add into array of permissions new permission data
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonObj = new JsonObject();
            jsonObj.addProperty(PERMISSIONS_NAME_NODE, roleName);
            jsonObj.addProperty(PERMISSIONS_USERS_GROUP_NODE, usersGroup);
            array.add(jsonObj);

            // overwrite permissions file
            jsonObj = new JsonObject();
            jsonObj.add(PERMISSIONS_PERMISSIONS_NODE, array);
            try (Writer writer = new FileWriter(path2JsonPermissions, false)) {
                gson.toJson(jsonObj, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void fillFilesWithImportData(String roleName, String permissionItemName, String usersGroup, String serviceName, String actionName) {
        fillRolesFile(roleName);
        fillPermissionsFile(roleName, usersGroup);
        fillServiceFile(roleName, permissionItemName, serviceName, actionName);
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

