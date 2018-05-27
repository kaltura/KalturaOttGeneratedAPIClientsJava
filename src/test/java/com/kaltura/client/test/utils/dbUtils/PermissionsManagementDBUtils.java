package com.kaltura.client.test.utils.dbUtils;

import org.json.JSONArray;
import java.sql.*;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;

public class PermissionsManagementDBUtils extends DBUtils {

    /**
     * Call Stored Procedure to create role
     */
    public static int insertRole(String role) {
        int result =-1;
        try {
            prepareCall(SP_INSERT_ROLE);
            cStmt.setInt(1, 0); // group_id == 0
            cStmt.setString(2, role);

            rs = cStmt.executeQuery();
            String columnName = rs.getMetaData().getColumnName(1);
            while (rs.next()) {
                result = rs.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return result;
        }
    }

    /**
     * Call Stored Procedure to delete role and its permissions
     */
    public static void deleteRoleAndItsPermissions(int roleId) {
        try {
            prepareCall(SP_DELETE_ROLE_AND_ITS_PERMISSIONS);
            cStmt.setInt(1, 0); // group_id == 0
            cStmt.setInt(2, roleId);

            cStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Call Stored Procedure to insert permissions
     */
    public static int insertPermission(String name, int type, String usersGroup) {
        int result =-1;
        try {
            prepareCall(SP_INSERT_PERMISSION);
            cStmt.setInt(1, 0); // group_id == 0
            cStmt.setString(2, name);
            cStmt.setInt(3, type);
            cStmt.setString(4, usersGroup);

            rs = cStmt.executeQuery();
            String columnName = rs.getMetaData().getColumnName(1);
            while (rs.next()) {
                result = rs.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return result;
        }
    }

    /**
     * Call Stored Procedure to delete permission
     */
    public static void deletePermission(int id) {
        try {
            prepareCall(SP_DELETE_PERMISSION);
            cStmt.setInt(1, id);

            cStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Call Stored Procedure to insert permission role
     */
    public static int insertPermissionRole(long roleId, long permissionId, int isExcluded) {
        int result =-1;
        try {
            prepareCall(SP_INSERT_PERMISSION_ROLE);
            cStmt.setInt(1, 0); // group_id == 0
            cStmt.setLong(2, roleId);
            cStmt.setLong(3, permissionId);
            cStmt.setInt(4, isExcluded);

            rs = cStmt.executeQuery();
            String columnName = rs.getMetaData().getColumnName(1);
            while (rs.next()) {
                result = rs.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return result;
        }
    }

    /**
     * Call Stored Procedure to insert permission item
     */
    public static int insertPermissionItem(String name, int type, String service, String action, String permissionItemObject, String parameter) {
        int result =-1;
        try {
            prepareCall(SP_INSERT_PERMISSION_ITEM);
            cStmt.setString(1, name);
            cStmt.setInt(2, type);
            cStmt.setString(3, service);
            cStmt.setString(4, action);
            cStmt.setString(5, permissionItemObject);
            cStmt.setString(6, parameter);
            rs = cStmt.executeQuery();

            String columnName = rs.getMetaData().getColumnName(1);
            while (rs.next()) {
                result = rs.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return result;
        }
    }

    /**
     * Call Stored Procedure to delete permission item
     */
    public static void deletePermissionItem(int id) {
        try {
            prepareCall(SP_DELETE_PERMISSION_ITEM);
            cStmt.setInt(1, id);

            cStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Call Stored Procedure to insert permission permission item
     */
    public static int insertPermissionPermissionItem(long permissionId, long permissionItemId, int isExcluded) {
        int result =-1;
        try {
            prepareCall(SP_INSERT_PERMISSION_PERMISSION_ITEM);
            cStmt.setInt(1, 0);; // group_id == 0
            cStmt.setLong(2, permissionId);
            cStmt.setLong(3, permissionItemId);
            cStmt.setInt(4, isExcluded);
            rs = cStmt.executeQuery();

            String columnName = rs.getMetaData().getColumnName(1);
            while (rs.next()) {
                result = rs.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return result;
        }
    }

    /**
     * Call Stored Procedure to delete permission permission item
     */
    public static void deletePermissionPermissionItem(int id) {
        try {
            prepareCall(SP_DELETE_PERMISSION_PERMISSION_ITEM);
            cStmt.setInt(1, id);

            cStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static int getCountRowsHavingRoleNameInRoles(String name, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_ROLE_NAME_IN_ROLES_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getIdRecordHavingRoleNameInRoles(String name, int groupId) {
        int id =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_ROLE_NAME_IN_ROLES_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                id = jsonArray.getJSONObject(0).getInt(ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public static int getCountRowsHavingRoleNameInPermissions(String name, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_ROLE_NAME_IN_PERMISSIONS_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getIdRecordHavingRoleNameInPermissions(String name, int groupId) {
        int count =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_ROLE_NAME_IN_PERMISSIONS_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getCountSpecificRowsFromRolesPermissions(int roleId, int permissionId, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_IN_ROLES_PERMISSIONS_SELECT,
                    roleId, permissionId, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getCountRowsHavingNameInPermissionItems(String name, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_NAME_IN_PERMISSION_ITEMS_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getIdRecordHavingNameInPermissionItems(String name, int groupId) {
        int count =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_NAME_IN_PERMISSION_ITEMS_SELECT,
                    name, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getCountSpecificRowsFromPermissionsPermissionsItems(int permissionId, int permissionItemId, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_IN_PERMISSIONS_PERMISSIONS_ITEMS_SELECT,
                    permissionId, permissionItemId, groupId), true);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
