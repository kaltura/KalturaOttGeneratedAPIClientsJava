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

    private static boolean isActivationNeeded = false;
    private static boolean isActivationNeededWasLoaded = false;

    private static SQLServerDataSource dataSource;
    private static Connection conn;
    private static Statement stam;
    static ResultSet rs;
    static CallableStatement cStmt;

    static final String ERROR_MESSAGE = "No results found";

    public static boolean isActivationOfUsersNeeded() {
        Logger.getLogger(DBUtils.class).debug("isActivationOfUsersNeeded()");
        if (isActivationNeededWasLoaded) {
            return isActivationNeeded;
        }
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

    // Get epg channel name and linear asset id json array
    public static JSONArray getLinearAssetIdAndEpgChannelNameJsonArray() {
        JSONArray jsonArray = null;
        try {
            jsonArray = getJsonArrayFromQueryResult(String.format(ASSET_ID_SELECT, Integer.valueOf(getProperty(PARTNER_ID)) + 1), false);
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
            assetId = getJsonArrayFromQueryResult(String.format(UNACTIVE_ASSET_ID_SELECT,
                    Integer.valueOf(getProperty(PARTNER_ID)) + 1), false).getJSONObject(0).getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return assetId;
    }

    public static int getSubscriptionWithPremiumService() {
        int subscriptionId = 0;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(SUBSCRIPTION_WITH_PREMIUM_SERVICE_SELECT,
                    partnerId), false);
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

    static void closeConnection() {
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

    static void prepareCall(String sql) throws SQLException {
        openConnection();
        cStmt = conn.prepareCall(sql);
    }
}
