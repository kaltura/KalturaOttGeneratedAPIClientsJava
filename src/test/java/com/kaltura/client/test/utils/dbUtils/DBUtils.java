package com.kaltura.client.test.utils.dbUtils;

import com.google.common.base.Strings;
import com.kaltura.client.Logger;
import com.kaltura.client.test.utils.BaseUtils;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.partnerId;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;
import static org.assertj.core.api.Assertions.fail;

public class DBUtils extends BaseUtils {

    private static boolean isActivationNeeded = false;
    private static boolean isActivationNeededWasLoaded = false;

    static final String ERROR_MESSAGE = "No results found";

    public static boolean isActivationOfUsersNeeded() {
        Logger.getLogger(DBUtils.class).debug("isActivationOfUsersNeeded()");
        if (isActivationNeededWasLoaded) {
            return isActivationNeeded;
        }
        int result = -1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(CHECK_IS_ACTIVATION_USERS_NEEDED, false, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
                return false;
            }

            result = jsonArray.getJSONObject(0).getInt(IS_ACTIVATION_NEEDED);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error(IS_ACTIVATION_NEEDED + " can't be null");
        }
        isActivationNeeded = result == 1;
        isActivationNeededWasLoaded = true;

        return isActivationNeeded;
    }

    public static String getUserData(String userRole) {
        Logger.getLogger(DBUtils.class).debug("getUserData(): userRole = " + userRole);

        String sqlQuery = USER_BY_ROLE_SELECT;
        if (isActivationOfUsersNeeded()) {
            sqlQuery += AND_ACTIVE_STATUS;
        }
        String userdData = "";
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(sqlQuery, false, userRole, partnerId);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(ACTIVATION_TOKEN_SELECT, false, username);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(RESET_PASSWORD_TOKEN_SELECT, false, username);
            resetPasswordToken = jsonArray.getJSONObject(0).getString(CP_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resetPasswordToken;
    }

    // Get epg channel name and linear asset id json array
    public static JSONArray getLinearAssetIdAndEpgChannelNameJsonArray() {
        JSONArray jsonArray = null;
        try {
            jsonArray = getJsonArrayFromQueryResult(ASSET_ID_SELECT, false, Integer.valueOf(getProperty(PARTNER_ID)) + 1);
            if (jsonArray == null || jsonArray.length() <= 0) {
                Logger.getLogger("Response is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // Get un active asset from DB (status = 2)
    public static int getUnActiveAsset() {
        int assetId = 0;

        try {
            assetId = getJsonArrayFromQueryResult(UNACTIVE_ASSET_ID_SELECT, false, Integer.valueOf(getProperty(PARTNER_ID)) + 1).getJSONObject(0).getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return assetId;
    }

    public static int getSubscriptionWithPremiumService() {
        int subscriptionId = 0;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(SUBSCRIPTION_WITH_PREMIUM_SERVICE_SELECT, false, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return subscriptionId;
            }

            subscriptionId = jsonArray.getJSONObject(0).getInt(SUB_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("data about premium services can't be null");
        }

        return subscriptionId;
    }

    // Return json array from DB
//    static JSONArray getJsonArrayFromQueryResult(String query, boolean isNullResultAllowed) {
//        SQLServerDataSource dataSource = getDataSource();
//        Connection conn = null;
//        Statement stam = null;
//        ResultSet rs = null;
//
//        JSONArray jsonArray = null;
//
//        try {
//            conn = dataSource.getConnection();
//            stam = conn.createStatement();
//            rs = stam.executeQuery(query);
//
//            if (rs != null && rs.isBeforeFirst() || isNullResultAllowed) {
//                jsonArray = buildJsonArrayFromQueryResult(rs);
//                Logger.getLogger(DBUtils.class).debug("DB jsonArray: " + jsonArray.toString());
//            } else {
//                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbUtils.closeQuietly(rs);
//            DbUtils.closeQuietly(stam);
//            DbUtils.closeQuietly(conn);
//        }
//
//        return jsonArray;
//    }

    private static JSONArray buildJsonArrayFromQueryResult(ResultSet rs) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();

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
        return jsonArray;
    }

    private static SQLServerDataSource getDataSource(){
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser(getProperty(DB_USER));
        dataSource.setPassword(getProperty(DB_PASSWORD));
        dataSource.setServerName(getProperty(DB_URL));
        dataSource.setApplicationIntent("ReadOnly");
        dataSource.setMultiSubnetFailover(true);

        return dataSource;
    }


    // Return json array from DB
    static JSONArray getJsonArrayFromQueryResult(String query, boolean isNullResultAllowed, Object... args) {
        SQLServerDataSource dataSource = getDataSource();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        JSONArray jsonArray = null;

        try {
            conn = dataSource.getConnection();
            if (args.length >0) {
                pstm = preparedStatementExecution(conn, query, args);
            }
            else {
                pstm = conn.prepareStatement(query);
            }
            rs = pstm.executeQuery();

            if (rs != null && rs.isBeforeFirst() || isNullResultAllowed) {
                jsonArray = buildJsonArrayFromQueryResult(rs);
                Logger.getLogger(DBUtils.class).debug("DB jsonArray: " + jsonArray.toString());
            } else {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pstm);
            DbUtils.closeQuietly(conn);
        }

        return jsonArray;
    }

    private static PreparedStatement preparedStatementExecution(Connection conn, String query, Object... args) {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            for (int i = 0; i < args.length; i++) {
                switch(args[i].getClass().getSimpleName()){
                    case "String":
                        pstm.setString(i+1, (String) args[i]);
                        break;
                    case "Integer":
                        pstm.setInt(i+1, (int) args[i]);
                        break;
                    case "Double":
                        pstm.setDouble(i+1, (double) args[i]);
                        break;
                    case "Long":
                        pstm.setLong(i+1, (long) args[i]);
                        break;
                    default:
                        Logger.getLogger(DBUtils.class).error("No valid type found!");
                        fail("No valid type found!");
                        break;
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return pstm;
    }


//    private static void openConnection() {
//        SQLServerDataSource dataSource = getDataSource();
//
//        try {
//            conn = dataSource.getConnection();
//            stam = conn.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static void closeConnection() {
//        DbUtils.closeQuietly(rs);
//        DbUtils.closeQuietly(stam);
//        DbUtils.closeQuietly(conn);
//    }

//    static void prepareCall(String sql) throws SQLException {
//        openConnection();
//        cStmt = conn.prepareCall(sql);
//    }
}
