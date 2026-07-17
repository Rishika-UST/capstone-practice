package database;


import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {


    public static Connection getConnection()
            throws Exception {


        return DriverManager.getConnection(
                DatabaseContainer.getJdbcUrl(),
                DatabaseContainer.getUsername(),
                DatabaseContainer.getPassword()
        );

    }

}