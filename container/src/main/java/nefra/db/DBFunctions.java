package nefra.db;

import nefra.settings.Settings;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static nefra.settings.Settings.writeSetting;


public class DBFunctions {
    private static ResultSet rs;
    private static Statement statement;
    private static String refereesFile = Settings.base + "/Referees/";
    private static String clubsFile = Settings.base + "/Clubs/";
    private static String divisionsFile = Settings.base + "/Divisions/";
    private static String gamesFile = Settings.base + "/Games/";

    /**
     * Create the DBFunctions class and gets the db connection and instantiate it if necessary.
     */
    public DBFunctions() {
        Connection connection;
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.dbConnection();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (nefra.settings.Settings.getSetting("DatabaseInstantiation").equals("true") |
                nefra.settings.Settings.getSetting("DatabaseInstantiation").equals("null")) {
            writeSetting("DatabaseInstantiation", "false");
            instantiate();
        }
    }

    /**
     * If required, instantiate sets up the tables in the database.
     */
    private void instantiate() {
        try {
            statement.executeUpdate("CREATE TABLE referee(" +
                    "referee_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(50) NOT NULL," +
                    "last_name VARCHAR(50) NOT NULL," +
                    "email VARCHAR(50)," +
                    "phone VARCHAR(10)," +
                    "weeklyFee DECIMAL(10,2)," +
                    "totalFee DECIMAL(10,2)," +
                    "UNIQUE (first_name, last_name)" +
                    ");");

            statement.executeUpdate("CREATE TABLE club(" +
                    "club_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "club_name VARCHAR(50) NOT NULL," +
                    "street VARCHAR(100)," +
                    "suburb VARCHAR(50)," +
                    "state VARCHAR(3)," +
                    "postcode INTEGER(4)," +
                    "president_name VARCHAR(50)," +
                    "president_contact VARCHAR(50)," +
                    "weeklyFee DECIMAL(10,2)," +
                    "totalFee DECIMAL(10,2)," +
                    "UNIQUE (club_name, club_id)" +
                    ");");

            statement.executeUpdate("CREATE TABLE division(" +
                    "division_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "division_name VARCHAR(50) UNIQUE," +
                    "main_referee_fee DECIMAL(10,2)," +
                    "assistant_ref_fee DECIMAL(10,2)" +
                    ");");

            statement.executeUpdate("CREATE TABLE game(" +
                    "game_id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "home_club INTEGER," +
                    "away_club INTEGER," +
                    "division INTEGER," +
                    "main_referee INTEGER," +
                    "assistant_ref_1 INTEGER," +
                    "assistant_ref_2 INTEGER," +
                    "UNIQUE (game_id, home_club, away_club)," +
                    "FOREIGN KEY (home_club) REFERENCES club(club_id)," +
                    "FOREIGN KEY (away_club) REFERENCES club(club_id)," +
                    "FOREIGN KEY (division) REFERENCES division(division_id)," +
                    "FOREIGN KEY (main_referee) REFERENCES referee(referee_id)," +
                    "FOREIGN KEY (assistant_ref_1) REFERENCES referee(referee_id)," +
                    "FOREIGN KEY (assistant_ref_2) REFERENCES referee(referee_id)" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a referee into the database
     *
     * @param firstName Referee's first name
     * @param lastName  Referee's last name
     * @param email     Referee's email
     * @param phone     Referee's phone
     * @param weeklyFee Referee's weekly fee
     * @param totalFee  Referee's total fee
     */
    public void insertReferee(String firstName, String lastName, String email, String phone, double weeklyFee, double totalFee) {
        try {
            rs = statement.executeQuery("SELECT * FROM referee");
            rs.moveToInsertRow();
            rs.updateString("first_name", firstName);
            rs.updateString("last_name", lastName);
            rs.updateString("email", email);
            rs.updateString("phone", phone);
            rs.updateDouble("weeklyFee", weeklyFee);
            rs.updateDouble("totalFee", totalFee);
            rs.insertRow();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void insertClub(String clubName, String street, String suburb,
                           String state, String postcode, String presidentName,
                           String presidentContact, double weeklyFee, double totalFee) {
        try {
            rs = statement.executeQuery("SELECT * FROM club");
            rs.moveToInsertRow();
            rs.updateString("club_name", clubName);
            rs.updateString("street", street);
            rs.updateString("suburb", suburb);
            rs.updateString("state", state);
            rs.updateString("postcode", postcode);
            rs.updateString("president_name", presidentName);
            rs.updateString("president_contact", presidentContact);
            rs.updateDouble("weeklyFee", weeklyFee);
            rs.updateDouble("totalFee", totalFee);
            rs.insertRow();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void insertDivision(String divisionName, double mainRefereeFee, double arFee) {
        try{
        rs = statement.executeQuery("SELECT * FROM division");
        rs.moveToInsertRow();
        rs.updateString("division_name", divisionName);
        rs.updateDouble("main_referee_fee", mainRefereeFee);
        rs.updateDouble("assistant_ref_fee", arFee);
        rs.insertRow();
    } catch(
    SQLException sqle) { sqle.printStackTrace(); }
}

    /**
     * Get the tables in the database to test db connection and creation
     */
    public void getTables() {
        System.out.println("\nTABLE NAMES");
        try {
            rs = statement.executeQuery("SHOW TABLES;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the columns in the database to test that they have been created properly
     */
    public void getAllColumns() {
        System.out.println("\nCOLUMN NAMES\n");
        try {
            ResultSetMetaData rsmd;
            int colNum;

            rs = statement.executeQuery("SHOW COLUMNS FROM referee;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            System.out.println("REFEREE:");
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            System.out.println("\nCLUB:");
            rs = statement.executeQuery("SHOW COLUMNS FROM club;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            System.out.println("\nDIVISION:");
            rs = statement.executeQuery("SHOW COLUMNS FROM division;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            System.out.println("\nGAME:");
            rs = statement.executeQuery("SHOW COLUMNS FROM game;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  ArrayList<String> loadFromDatabase(String table)
    {
        ArrayList<String> data = new ArrayList<>();
        String sql = getTable(table);
        try {
            ArrayList<String> colNames = getColumnNames(table);
            sql = String.format(sql, table);
            rs = statement.executeQuery(sql);
            while (rs.next()) { for (String i : colNames) data.add(i + "=" + rs.getString(i)); }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return data;
    }

    private String getTable(String table)
    {
        switch(table)
        {
            case "referee": return "SELECT * FROM referee;";
            case "club": return "SELECT * FROM club;";
            case "division": return "SELECT * FROM division;";
            case "game": return "SELECT * FROM game;";
            default: System.err.println("No Table!"); return null;
        }
    }

    private  ArrayList<String> getColumnNames(String table) {
        ArrayList<String> data = new ArrayList<>();
        String sql = getTable(table);
        try {
            sql = String.format(sql, table);
            rs = statement.executeQuery(sql);
            while (rs.next()) { data.add(rs.getString(1)); }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return data;
    }

    public ArrayList<String> loadDbToFile(String type) {
        ArrayList<String> data = new ArrayList<>();
        switch (type)
        {
            case "referee":
                loadFile(new File(refereesFile + "referee.properties"));
                data = loadFromDatabase(type);
                saveData(new File(refereesFile + "referee.properties"), data);
                break;
            case "club":
                loadFile(new File(clubsFile + "club.properties"));
                data = loadFromDatabase(type);
                saveData(new File(clubsFile + "club.properties"), data);
                break;
            case "division":
                loadFile(new File(divisionsFile + "division.properties"));
                data = loadFromDatabase(type);
                saveData(new File(divisionsFile + "division.properties"), data);
                break;
            case "game":
                loadFile(new File(gamesFile + "game.properties"));
                data = loadFromDatabase(type);
                saveData(new File(gamesFile + "game.properties"), data);
                break;
            default:
                System.err.println("No Properties file found!");
                break;
        }
        return data;
    }

    private void saveData(File filename, ArrayList<String> data)
    {
        String[] fileValue;
        String property;
        String value;
        for(String i : data) {
            fileValue = i.split("=");
            property = fileValue[0];
            value = fileValue[1];
            //writeToFile(filename, property, value);
        }
    }
    private static void loadFile(File file)
    {
        try {
            FileUtils.openOutputStream(file,true);
            InputStream input = new FileInputStream(file.getAbsoluteFile());
            new Properties().load(input);
            input.close();
        } catch (IOException IOE) { System.out.println("An IO Error occurred"); }
    }
}
