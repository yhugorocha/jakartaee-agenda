package io.hugodev.jakarta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Db util.
 */
public class DbUtil {

    private static String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/dbagenda";
    private static String user = "postgres";
    private static  String password = "postgres";


    /**
     * Gets connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection connection =  DriverManager.getConnection(url, user, password);
            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
