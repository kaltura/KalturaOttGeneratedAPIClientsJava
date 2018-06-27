package com.kaltura.client.test.utils.dbUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.PricePlan;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.Arrays;

import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.tests.BaseTest.partnerId;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;
import static com.kaltura.client.test.utils.dbUtils.IngestFixtureData.loadFirstPricePlanFromJsonArray;
import static org.assertj.core.api.Assertions.fail;

public class DBUtils extends BaseUtils {

    static final String ERROR_MESSAGE = "*** no data found ***";

    private static boolean isActivationNeeded = false;
    private static boolean isActivationNeededWasLoaded = false;

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

    private static SQLServerDataSource getDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser(getProperty(DB_USER));
        dataSource.setPassword(getProperty(DB_PASSWORD));
        dataSource.setServerName(getProperty(DB_URL));
        dataSource.setApplicationIntent("ReadOnly");
        dataSource.setMultiSubnetFailover(true);

        return dataSource;
    }

    // Return json array from DB
    static JSONArray getJsonArrayFromQueryResult(String query, boolean isNullResultAllowed, Object... queryParams) {
        SQLServerDataSource dataSource = getDataSource();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        JSONArray jsonArray = null;

        try {
            conn = dataSource.getConnection();
            if (queryParams.length > 0) {
                pstm = preparedStatementExecution(conn, query, queryParams);
            } else {
                pstm = conn.prepareStatement(query);
            }

            rs = pstm.executeQuery();

            if (rs != null && rs.isBeforeFirst() || isNullResultAllowed) {
                jsonArray = buildJsonArrayFromQueryResult(rs);
                Logger.getLogger(DBUtils.class).debug("query: " + query + "\nparams: " + Arrays.deepToString(queryParams));
                Logger.getLogger(DBUtils.class).debug("DB jsonArray: " + jsonArray.toString());
            } else {
                // TODO: 6/25/2018 move query log once we'll have private repo
                fail(ERROR_MESSAGE + "\nquery: " + query + "\nparams: " + Arrays.deepToString(queryParams));
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
                switch (args[i].getClass().getSimpleName()) {
                    case "String":
                        pstm.setString(i + 1, (String) args[i]);
                        break;
                    case "Integer":
                        pstm.setInt(i + 1, (int) args[i]);
                        break;
                    case "Double":
                        pstm.setDouble(i + 1, (double) args[i]);
                        break;
                    case "Long":
                        pstm.setLong(i + 1, (long) args[i]);
                        break;
                    default:
                        fail("No valid type found!");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstm;
    }


    // queries
    public static boolean isActivationOfUsersNeeded() {
        if (isActivationNeededWasLoaded) {
            return isActivationNeeded;
        }

        JSONArray jsonArray = getJsonArrayFromQueryResult(CHECK_IS_ACTIVATION_USERS_NEEDED, false, partnerId);
        int result = jsonArray.getJSONObject(0).getInt(IS_ACTIVATION_NEEDED);

        isActivationNeeded = result == 1;
        isActivationNeededWasLoaded = true;

        return isActivationNeeded;
    }

    public static String getUserData(String userRole) {
        String sqlQuery = USER_BY_ROLE_SELECT;

        if (isActivationOfUsersNeeded()) {
            sqlQuery += AND_ACTIVE_STATUS;
        }

        JSONArray jsonArray = getJsonArrayFromQueryResult(sqlQuery, false, userRole, partnerId);
        return jsonArray.getJSONObject(0).getString(USERNAME) + ":" + jsonArray.getJSONObject(0).getString(PASSWORD);
    }

    public static String getActivationToken(String username) {
        return getJsonArrayFromQueryResult(ACTIVATION_TOKEN_SELECT, false, username)
                .getJSONObject(0)
                .getString(ACTIVATION_TOKEN);
    }

    public static String getResetPasswordToken(String username) {
        try {
            return getJsonArrayFromQueryResult(RESET_PASSWORD_TOKEN_SELECT, false, username)
                    .getJSONObject(0)
                    .getString(CP_TOKEN);
        } catch (JSONException e) {
            return null;
        }
    }

    // Get epg channel name and linear asset id json array
    public static JSONArray getLinearAssetIdAndEpgChannelNameJsonArray() {
        return getJsonArrayFromQueryResult(ASSET_ID_SELECT, false, partnerId + 1);
    }

    // Get un active asset from DB (status = 2)
    public static int getUnActiveAsset() {
        return getJsonArrayFromQueryResult(UNACTIVE_ASSET_ID_SELECT, false, partnerId + 1)
                .getJSONObject(0)
                .getInt("id");
    }

    public static int getSubscriptionWithPremiumService() {
        return getJsonArrayFromQueryResult(SUBSCRIPTION_WITH_PREMIUM_SERVICE_SELECT, false, partnerId)
                .getJSONObject(0)
                .getInt(SUB_ID);
    }

    public static PricePlan loadPPWithWaiver() {
        JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_WITH_WAVER_SELECT, true, partnerId);
        return loadFirstPricePlanFromJsonArray(jsonArray);
    }

    public static PricePlan loadPPWithoutWaiver() {
        JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_WITHOUT_WAVER_SELECT, true, partnerId);
        return loadFirstPricePlanFromJsonArray(jsonArray);
    }

    public static JSONObject getHouseholdById(int householdId) {
        return getJsonArrayFromQueryResult(HOUSEHOLD_BY_ID_SELECT, false, partnerId, householdId)
                .getJSONObject(0);
    }

    public static JSONObject getUserById(int userId) {
        return getJsonArrayFromQueryResult(USER_BY_ID_SELECT, false, partnerId, userId)
                .getJSONObject(0);
    }
}
