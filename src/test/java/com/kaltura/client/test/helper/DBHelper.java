package com.kaltura.client.test.helper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kaltura.client.test.helper.Properties.*;

public class DBHelper {

    private static SQLServerDataSource dataSource;
    private static Connection conn;
    private static Statement stam;
    private static ResultSet rs;

    //selects
    private static final String ACTIVATION_TOKEN_SELECT = "SELECT [ACTIVATION_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = '%S'";


    public static String getActivationToken(String username) {
        openConnection();
        try {
            rs = stam.executeQuery(String.format(ACTIVATION_TOKEN_SELECT, username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String activationToken = null;
        try {
            activationToken = rs.getString("ACTIVATION_TOKEN");
        } catch (SQLException e) {
            e.printStackTrace();
//            Logger.getLogger(DBHelper.class).error("activationToken can't be null");
        }
        closeConnection();
        return activationToken;
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
