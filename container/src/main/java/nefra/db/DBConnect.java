package nefra.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DBConnect contains the connection for the database and related functions (Open/Close)
 */
public class DBConnect {
    /**
     * function to load and connect to the embedded database (h2)
     * @return the database connection, so it can be accessed outside.
     */
    protected Connection dbConnection()
    {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NEFRA Data/NEFRA;create=true", "admin", "admin");
            System.out.println("db connected to.\nConnection: " + connection);
        }
        catch (Exception e) { e.printStackTrace(); }
        return connection;
    }
}
