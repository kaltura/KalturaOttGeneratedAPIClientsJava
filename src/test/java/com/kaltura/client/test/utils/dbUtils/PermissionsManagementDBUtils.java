package com.kaltura.client.test.utils.dbUtils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.json.JSONArray;
import java.sql.*;

import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;

public class PermissionsManagementDBUtils extends DBUtils {

    /**
     * Call Stored Procedure to create role
     */
    public static int insertRole(String role) {
        JSONArray jsonArray = getJsonArrayFromQueryResult(SP_INSERT_ROLE, 0, role); // group_id == 0
        return Integer.valueOf(jsonArray.getJSONObject(0).get(ID).toString());
    }

    /**
     * Call Stored Procedure to delete role and its permissions
     */
    public static void deleteRoleAndItsPermissions(int roleId) {
        executeQuery(SP_DELETE_ROLE_AND_ITS_PERMISSIONS, 0, roleId); // group_id == 0
    }

    /**
     * Call Stored Procedure to insert permissions
     */
    public static int insertPermission(String name, int type, String usersGroup) {
        JSONArray jsonArray = getJsonArrayFromQueryResult(SP_INSERT_PERMISSION, 0, name, type, usersGroup); // group_id == 0
        return Integer.valueOf(jsonArray.getJSONObject(0).get(ID).toString());
    }

    /**
     * Call Stored Procedure to delete permission
     */
    public static void deletePermission(int id) {
        executeQuery(SP_DELETE_PERMISSION, id);
    }

    /**
     * Call Stored Procedure to insert permission role
     */
    public static int insertPermissionRole(long roleId, long permissionId, int isExcluded) {
        JSONArray jsonArray = getJsonArrayFromQueryResult(SP_INSERT_PERMISSION_ROLE, 0, roleId, permissionId, isExcluded); // group_id == 0
        return Integer.valueOf(jsonArray.getJSONObject(0).get(ID).toString());
    }

    /**
     * Call Stored Procedure to insert permission item
     */
    public static int insertPermissionItem(String name, int type, String service, String action, String permissionItemObject, String parameter) {
        JSONArray jsonArray = getJsonArrayFromQueryResult(SP_INSERT_PERMISSION_ITEM, name, type, service, action, permissionItemObject, parameter);
        return Integer.valueOf(jsonArray.getJSONObject(0).get(ID).toString());
    }

    /**
     * Call Stored Procedure to delete permission item
     */
    public static void deletePermissionItem(int id) {
        executeQuery(SP_DELETE_PERMISSION_ITEM, id);
    }

    /**
     * Call Stored Procedure to insert permission permission item
     */
    public static int insertPermissionPermissionItem(long permissionId, long permissionItemId, int isExcluded) {
        JSONArray jsonArray = getJsonArrayFromQueryResult(SP_INSERT_PERMISSION_PERMISSION_ITEM, 0, permissionId, permissionItemId, isExcluded); // group_id == 0
        return Integer.valueOf(jsonArray.getJSONObject(0).get(ID).toString());
    }

    /**
     * Call Stored Procedure to delete permission permission item
     */
    public static void deletePermissionPermissionItem(int id) {
        executeQuery(SP_DELETE_PERMISSION_PERMISSION_ITEM, id);
    }

    static void executeQuery(String query, Object... queryParams) {
        SQLServerDataSource dataSource = DBUtils.getDataSource();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            if (queryParams.length > 0) {
                pstm = preparedStatementExecution(conn, query, queryParams);
            } else {
                pstm = conn.prepareStatement(query);
            }

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pstm);
            DbUtils.closeQuietly(conn);
        }
    }


    public static int getCountRowsHavingRoleNameInRoles(String name, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_ROLE_NAME_IN_ROLES_SELECT),
                    name, groupId);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_ROLE_NAME_IN_ROLES_SELECT),
                    name, groupId);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_ROLE_NAME_IN_PERMISSIONS_SELECT),
                    name, groupId);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getIdRecordHavingRoleNameInPermissions(String name, int groupId) {
        int id =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_ROLE_NAME_IN_PERMISSIONS_SELECT),
                    name, groupId);
            if (!jsonArray.isNull(0)) {
                id = jsonArray.getJSONObject(0).getInt(ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public static int getCountSpecificRowsFromRolesPermissions(int roleId, int permissionId, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_IN_ROLES_PERMISSIONS_SELECT),
                    roleId, permissionId, groupId);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getCountRowsHavingNameInPermissionItems(String name) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_BY_NAME_IN_PERMISSION_ITEMS_SELECT),
                    name);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static int getIdRecordHavingNameInPermissionItems(String name) {
        int id =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ID_BY_NAME_IN_PERMISSION_ITEMS_SELECT),
                    name);
            if (!jsonArray.isNull(0)) {
                id = jsonArray.getJSONObject(0).getInt(ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public static int getCountSpecificRowsFromPermissionsPermissionsItems(int permissionId, int permissionItemId, int groupId) {
        int count = 0;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COUNT_RECORDS_IN_PERMISSIONS_PERMISSIONS_ITEMS_SELECT),
                    permissionId, permissionItemId, groupId);
            if (!jsonArray.isNull(0)) {
                count = jsonArray.getJSONObject(0).getInt(ROW_COUNT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
