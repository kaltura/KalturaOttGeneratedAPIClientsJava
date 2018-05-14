package com.kaltura.client.test.utils;

import com.google.common.base.Strings;
import com.kaltura.client.Logger;
import com.kaltura.client.enums.BillingAction;
import com.kaltura.client.enums.BillingPriceType;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.DiscountModule;
import com.kaltura.client.types.Price;
import com.kaltura.client.types.PriceDetails;
import com.kaltura.client.types.PricePlan;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

import static com.kaltura.client.test.Properties.*;

public class DBUtils extends BaseUtils {

    private static SQLServerDataSource dataSource;
    private static Connection conn;
    private static Statement stam;
    private static ResultSet rs;

    private static final String ERROR_MESSAGE = "No results found";

    //selects
    private static final String ACTIVATION_TOKEN_SELECT = "SELECT [ACTIVATION_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = '%S'";
    private static final String CHECK_IS_ACTIVATION_USERS_NEEDED = "select [IS_ACTIVATION_NEEDED]\n" +
            "from [Users].[dbo].[groups_parameters]\n" +
            "where group_id=%d";
    private static final String DISCOUNT_BY_CURENCY_AND_PERCENT_SELECT = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes] dc with(nolock)\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (dc.currency_cd=lc.id)\n" +
            "where lc.code3='%S'\n" + // CURRENCY
            "and dc.discount_percent=%d\n" + // percent amount
            "and dc.group_id=%d\n" + // group
            "and dc.[status]=1 and dc.is_active=1";
    private static final String DISCOUNT_BY_PRICE_AND_PERCENT_SELECT = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=%d\n" + // group
            "and price=%d\n" + // price amount
            "and discount_percent=%d";  // percent amount
    private static final String EPG_CHANNEL_ID_SELECT = "SELECT [ID] FROM [TVinci].[dbo].[epg_channels] WHERE [GROUP_ID] = %d AND [NAME] = '%S'";
    private static final String INGEST_ITEMS_DATA_SELECT = "select TOP (1) *\n" +
            "from [Tvinci].[dbo].[groups_passwords]\n" +
            "where [group_id]=%d order by UPDATE_DATE DESC";
    private static final String PRICE_CODE_SELECT = "select top 1 * from [Pricing].[dbo].[price_codes] pc\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (pc.currency_cd=lc.id)\n" +
            "where pc.[status]=1 and pc.is_active=1\n" +
            "and pc.group_id=%d and pc.price=%d and lc.CODE3='%S'";
    private static final String PRICE_PLAN_SELECT = "select top 1 * from [Pricing].[dbo].[usage_modules]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=%d and internal_discount_id=%d and pricing_id=%d";
    private static final String USER_BY_ROLE_SELECT = "select top(1) u.username, u.[password]\n" +
            "from [Users].[dbo].[users] u with(nolock)\n" +
            "join [Users].[dbo].[users_roles] ur with(nolock) on (u.id=ur.[user_id])\n" +
            "join [TVinci].[dbo].[roles] r with(nolock) on (r.id=ur.role_id)\n" +
            "where r.[NAME]='%S' and u.is_active=1 and u.[status]=1 and u.group_id=%d";

    public static PriceDetails loadPriceCode(Double priceAmount, String currency) {
        Logger.getLogger(DBUtils.class).debug("loadPricePlan(): priceAmount = " + priceAmount + " currency = " + currency);
        PriceDetails result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PRICE_CODE_SELECT, BaseTest.partnerId, priceAmount, currency), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }
            result = new PriceDetails();
            result.setName(jsonArray.getJSONObject(0).getString("code"));
            result.setId(jsonArray.getJSONObject(0).getInt("id"));
            Price price = new Price();
            price.setCurrency(currency);
            price.setAmount(priceAmount);
            result.setPrice(price);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("discount code can't be null");
        }
        return result;
    }

    public static DiscountModule loadDiscount(Double discountPrice, Double discountPercent) {
        Logger.getLogger(DBUtils.class).debug("loadDiscount(): discountPrice = " + discountPrice + " discountPercent = " + discountPercent);
        DiscountModule result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(DISCOUNT_BY_PRICE_AND_PERCENT_SELECT,
                    BaseTest.partnerId, discountPrice, discountPrice), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }
            result = new DiscountModule();
            result.setToken("id", jsonArray.getJSONObject(0).getString("id"));
            result.setToken("name", jsonArray.getJSONObject(0).getString("code"));
            result.setPercent(discountPercent);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("discount code can't be null");
        }
        return result;
    }

    public static PricePlan loadPricePlan(Double priceAmount, String currency, Double discountPrice, Double discountPercent) {
        Logger.getLogger(DBUtils.class).debug("loadPricePlan(): priceAmount = " + priceAmount + " currency = " + currency +
                " discountPrice = " + discountPrice + " discountPercent = " + discountPercent);
        PricePlan pricePlan = null;
        try {
            PriceDetails priceCode = loadPriceCode(priceAmount, currency);
            if (priceCode == null) {
                return pricePlan;
            }
            DiscountModule discountModule = loadDiscount(discountPrice, discountPercent);
            if (discountModule == null) {
                return pricePlan;
            }
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PRICE_PLAN_SELECT, BaseTest.partnerId,
                    Integer.valueOf(discountModule.toParams().get("id").toString()), priceCode.getId()), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return pricePlan;
            }
            //TODO: result = jsonArray.getJSONObject(0).getString("code");
            pricePlan = new PricePlan();
            pricePlan.setId(jsonArray.getJSONObject(0).getLong("id"));
            pricePlan.setName(jsonArray.getJSONObject(0).getString("name"));
            pricePlan.setViewLifeCycle(jsonArray.getJSONObject(0).getInt("view_life_cycle_min"));
            pricePlan.setIsRenewable("0".equals(jsonArray.getJSONObject(0).getString("is_renew")));

            pricePlan.דקא
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("price plan data can't be null");
        }


        return pricePlan;
    }

    public static String getIngestItemUserData(int accountId) {
        Logger.getLogger(DBUtils.class).debug("getIngestItemUserData(): accountId = " + accountId);
        String result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(INGEST_ITEMS_DATA_SELECT, accountId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            }

            result = jsonArray.getJSONObject(0).getString("username") + ":" +
                    jsonArray.getJSONObject(0).getString("password");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("data about ingest business module user can't be null");
        }

        return result;
    }

    public static String getDiscount(String currency, int percent) {
        Logger.getLogger(DBUtils.class).debug("getDiscount(): currency = " + currency + " percent = " + percent);
        String code = "";
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(DISCOUNT_BY_CURENCY_AND_PERCENT_SELECT, currency, percent, BaseTest.partnerId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            }

            code = jsonArray.getJSONObject(0).getString("code");
            if ("".equals(code)) {
                throw new SQLException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("code can't be null");
        }

        return code;
    }

    public static boolean isActivationOfUsersNeeded() {
        Logger.getLogger(DBUtils.class).debug("isActivationOfUsersNeeded()");
        int result = -1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(CHECK_IS_ACTIVATION_USERS_NEEDED, BaseTest.partnerId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            }

            result = jsonArray.getJSONObject(0).getInt("is_activation_needed");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("IS_ACTIVATION_NEEDED can't be null");
        }

        return result == 1;
    }

    public static String getUserData(String userRole) {
        Logger.getLogger(DBUtils.class).debug("getUserData(): userRole = " + userRole);
        String sqlQuery = USER_BY_ROLE_SELECT;
        if (isActivationOfUsersNeeded()) {
            sqlQuery += " and u.activate_status=1";
        }
        String userdData = "";
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(sqlQuery, userRole, BaseTest.partnerId), false);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(DBUtils.class).error(ERROR_MESSAGE);
            }

            userdData = jsonArray.getJSONObject(0).getString("username") + ":" +
                    jsonArray.getJSONObject(0).getString("password");
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
            }

            activationToken = jsonArray.getJSONObject(0).getString("activation_token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activationToken;
    }

    public static int getEpgChannelId(String channelName) {
        Logger.getLogger(DBUtils.class).debug("getEpgChannelId(): channelName = " + channelName);
        int epgChannelId =-1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(EPG_CHANNEL_ID_SELECT, BaseTest.partnerId + 1, channelName), false);
            epgChannelId = jsonArray.getJSONObject(0).getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DBUtils.class).error("epgChannelId can't be null");
        }

        return epgChannelId;
    }

    //TODO - change existing methods to work with the new getJsonArrayFromQueryResult method
    // Return json array from DB
    private static JSONArray getJsonArrayFromQueryResult(String query, boolean isNullResultAllowed) throws Exception {
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
            rs.close();
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
}
