package com.kaltura.client.test.utils;

import java.io.PrintWriter;

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


}

