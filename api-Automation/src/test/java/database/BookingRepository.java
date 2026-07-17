package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BookingRepository {


    public boolean bookingExists(String id)
            throws Exception {


        Connection connection =
                DatabaseConnection.getConnection();


        String query =
                "SELECT * FROM bookings WHERE id=?";


        PreparedStatement statement =
                connection.prepareStatement(query);


        statement.setString(1,id);


        ResultSet result =
                statement.executeQuery();


        return result.next();

    }

}