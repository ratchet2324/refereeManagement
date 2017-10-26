package nefra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DBConnect contains the connection for the database and related functions (Open/Close)
 */
public class DBConnect {

    private static ArrayList<Connection> conns = new ArrayList<>();

    public static void closeConnections() {
        try {
            for (Connection e : conns) e.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * function to load and connect to the embedded database (h2)
     * @return the database connection, so it can be accessed outside.
     */
    Connection dbConnection()
    {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NEFRA Data/db/NEFRA.db;create=true");
            System.out.println("db connected to.\nConnection: " + connection);
            conns.add(connection);
        }
        catch (Exception e) { e.printStackTrace(); }
        return connection;
    }
}
