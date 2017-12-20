package nefra.db;

import nefra.exceptions.DelLog;
import nefra.misc.Debug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DBConnect contains the connection for the database and related functions (Open/Close)
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class DBConnect {

    /**
     * List to hold all connections to terminate them when the program is closed.
     * @since 1.0
     */
    private static ArrayList<Connection> conns = new ArrayList<>();

    public static void closeConnections() {
        try {
            for (Connection e : conns) e.close();
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
        }
    }

    /**
     * function to load and connect to the embedded database (h2)
     * @return the database connection, so it can be accessed outside.
     * @since 1.0
     */
    Connection dbConnection()
    {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NEFRA Data/db/NEFRA.db;create=true");
            if(Debug.debugMode)
                DelLog.getInstance().Log("db connected to.\nConnection: " + connection);
            conns.add(connection);
        }
        catch (Exception e) { DelLog.getInstance().Log(e);
        }
        return connection;
    }
}
