package com.kaltura.client.test.utils.dbUtils;

import com.google.common.base.Strings;
import com.kaltura.client.Logger;
import com.kaltura.client.test.utils.BaseUtils;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.partnerId;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;

public class DBUtils extends BaseUtils {

    private static SQLServerDataSource dataSource;
    private static Connection conn;
    private static Statement stam;
    private static ResultSet rs;
    private static CallableStatement cStmt;

    static final String ERROR_MESSAGE = "No results found";

    public static boolean isActivationOfUsersNeeded() {
        Logger.getLogger(DBUtils.class).debug("isActivationOfUsersNeeded()");
        int result = -1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(CHECK_IS_ACTIVATION_USERS_NEEDED, partnerId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
                return false;
            }

            result = jsonArray.getJSONObject(0).getInt(IS_ACTIVATION_NEEDED);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error(IS_ACTIVATION_NEEDED + " can't be null");
        }

        return result == 1;
    }

    public static String getUserData(String userRole) {
        Logger.getLogger(DBUtils.class).debug("getUserData(): userRole = " + userRole);
        String sqlQuery = USER_BY_ROLE_SELECT;
        if (isActivationOfUsersNeeded()) {
            sqlQuery += AND_ACTIVE_STATUS;
        }
        String userdData = "";
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(sqlQuery, userRole, partnerId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
                return null;
            }

            userdData = jsonArray.getJSONObject(0).getString(USERNAME) + ":" +
                    jsonArray.getJSONObject(0).getString(PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("username/password can't be null");
        }

        return userdData;
    }

    public static String getActivationToken(String username) {
        Logger.getLogger(DBUtils.class).debug("getActivationToken(): username = " + username);
        String activationToken = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(ACTIVATION_TOKEN_SELECT, username), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
                return null;
            }

            activationToken = jsonArray.getJSONObject(0).getString(ACTIVATION_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activationToken;
    }

    public static String getResetPasswordToken(String username) {
        String resetPasswordToken = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(RESET_PASSWORD_TOKEN_SELECT, username), false);
            resetPasswordToken = jsonArray.getJSONObject(0).getString(CP_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resetPasswordToken;
    }


//    public static List<Integer> getUserRoles(String userId) {
//        List<Integer> userRoles = new ArrayList<>();
//
//        try {
//            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(USER_ROLES_SELECT, userId));
//
//            if (Strings.isNullOrEmpty(jsonArray.toString())) {
//                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
//                return userRoles;
//            }
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                userRoles.add(jsonArray.getInt(i));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return userRoles;
//    }

    // Return json array from DB
    static JSONArray getJsonArrayFromQueryResult(String query, boolean isNullResultAllowed) throws Exception {
        openConnection();
        JSONArray jsonArray = new JSONArray();
        rs = stam.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();

        if (rs != null && rs.isBeforeFirst() || isNullResultAllowed) {
            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();

                for (int i = 1; i < numColumns + 1; i++) {
                    String column_name = rsmd.getColumnName(i).toLowerCase();

                    if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
                        obj.put(column_name, rs.getArray(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
                        obj.put(column_name, rs.getInt(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
                        obj.put(column_name, rs.getBoolean(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
                        obj.put(column_name, rs.getBlob(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
                        obj.put(column_name, rs.getDouble(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
                        obj.put(column_name, rs.getFloat(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
                        obj.put(column_name, rs.getInt(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
                        obj.put(column_name, rs.getNString(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
                        obj.put(column_name, rs.getString(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
                        obj.put(column_name, rs.getInt(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
                        obj.put(column_name, rs.getInt(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                        obj.put(column_name, rs.getDate(column_name));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
                        obj.put(column_name, rs.getTimestamp(column_name));
                    } else {
                        obj.put(column_name, rs.getObject(column_name));
                    }
                }
                jsonArray.put(obj);
            }
            closeConnection();
            Logger.getLogger(DBUtils.class).debug("DB jsonArray: " + jsonArray.toString());
            return jsonArray;

        } else {
            Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            closeConnection();
            return null;
        }

    }

    private static void openConnection() {
        dataSource = new SQLServerDataSource();
        dataSource.setUser(getProperty(DB_USER));
        dataSource.setPassword(getProperty(DB_PASSWORD));
        dataSource.setServerName(getProperty(DB_URL));
        dataSource.setApplicationIntent("ReadOnly");
        dataSource.setMultiSubnetFailover(true);

        try {
            conn = dataSource.getConnection();
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
        try {
            stam = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stam.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void prepareCall(String sql) throws SQLException {
        openConnection();
        cStmt = conn.prepareCall(sql);
    }

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
}
