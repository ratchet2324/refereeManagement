package nefra.db;

import javafx.scene.control.Alert;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnect contains the connection for the database and related functions (Open/Close)
 */
public class DBConnect {
    /**
     * function to load and connect to the embedded database (h2)
     * @return the database connection, so it can be accessed outside.
     */
    public Connection dbConnection()
    {
        Connection connection = null;
        /*String url = "jdbc:derby:./NEFRA;create=true";
        String dbLoc = System.getProperty("user.home") + "/Documents/NEFRA";
        System.setProperty("derby.system.home", dbLoc);*/

        try {
            /*File derbyJar = new File("libs/derby.jar");
            URL[] urls = { derbyJar.getAbsoluteFile().toURI().toURL() };
            URLClassLoader ucl = URLClassLoader.newInstance(urls);
            Driver d = (Driver) Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, ucl).newInstance();
            DriverManager.registerDriver(new DriverShim(d));
            connection = DriverManager.getConnection(url);*/

            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NEFRA Data/NEFRA;create=true", "admin", "admin");
            System.out.println("db connected to.\nConnection: " + connection);

            Alert popup = new Alert(Alert.AlertType.INFORMATION);
            popup.setTitle("DB Connection");
            popup.setContentText("DB Connected successfully!");
            popup.setGraphic(null);
            popup.setHeaderText(null);
            popup.show();

            /* Db functions here */

            connection.close();
        }
        catch (Exception e) { e.printStackTrace(); }

        return connection;
    }
}
