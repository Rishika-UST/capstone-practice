package database;


import org.flywaydb.core.Flyway;
import org.testcontainers.containers.MySQLContainer;


public class DatabaseContainer {


    public static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("tripstack_test")
                    .withUsername("root")
                    .withPassword("root");


    static {

        mysql.start();


        Flyway.configure()
                .dataSource(
                        mysql.getJdbcUrl(),
                        mysql.getUsername(),
                        mysql.getPassword()
                )
                .locations("classpath:db/migration")
                .load()
                .migrate();

    }


    public static String getJdbcUrl(){
        return mysql.getJdbcUrl();
    }


    public static String getUsername(){
        return mysql.getUsername();
    }


    public static String getPassword(){
        return mysql.getPassword();
    }

}