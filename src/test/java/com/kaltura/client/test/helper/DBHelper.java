package com.kaltura.client.test.helper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kaltura.client.test.helper.Config.*;

public class DBHelper {

    private static SQLServerDataSource dataSource;
    private static Connection conn;
    private static Statement stam;
    private static ResultSet rs;

    //selects
    private static final String ACTIVATION_TOKEN_SELECT = "SELECT [ACTIVATION_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = '%S'";


    public static String getActivationToken(String username) throws SQLException {
        openConnection();
        rs = stam.executeQuery(String.format(ACTIVATION_TOKEN_SELECT, username));
        rs.next();
        String activationToke = rs.getString("ACTIVATION_TOKEN");
        closeConnection();
        return activationToke;
    }

    private static void openConnection() throws SQLException {
        dataSource = new SQLServerDataSource();
        dataSource.setUser(getProperty(DB_USER));
        dataSource.setPassword(getProperty(DB_PASSWORD));
        dataSource.setServerName(getProperty(DB_URL));
        dataSource.setApplicationIntent("ReadOnly");
        dataSource.setMultiSubnetFailover(true);

        conn = dataSource.getConnection();
        stam = conn.createStatement();

    }

    private static void closeConnection() throws SQLException {
        rs.close();
        stam.close();
        conn.close();
    }
}
