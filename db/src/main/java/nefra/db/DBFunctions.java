package nefra.db;

import java.sql.*;

public class DBFunctions {
    private static ResultSet rs;
    private static Statement statement;
    private Connection connection;

    public DBFunctions() {
        DBConnect dbConnect = new DBConnect();
        connection = dbConnect.dbConnection();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (nefra.settings.Settings.getSetting("DatabaseInstantiation").equals("true") |
                nefra.settings.Settings.getSetting("DatabaseInstantiation").equals("null")) {
            nefra.settings.Settings.writeSetting("DatabaseInstantiation", "false");
            instantiate();
        }
    }

    public static void insertString(String colName, String value) {
        try {
            rs.updateString(colName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertString(int colIndex, String value) {
        try {
            rs.updateString(colIndex, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDouble(String colName, Double value) {
        try {
            rs.updateDouble(colName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDouble(int colIndex, Double value) {
        try {
            rs.updateDouble(colIndex, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void insertRow(Connection connection) {
        try {
            rs = statement.executeQuery("");
            rs.moveToInsertRow();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

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

    public void getColumns() {
        System.out.println("\nCOLUMN NAMES");
        try {
            ResultSetMetaData rsmd;
            int colNum;

            rs = statement.executeQuery("SHOW COLUMNS FROM referee;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            rs = statement.executeQuery("SHOW COLUMNS FROM club;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            rs = statement.executeQuery("SHOW COLUMNS FROM division;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            rs = statement.executeQuery("SHOW COLUMNS FROM game;");
            rsmd = rs.getMetaData();
            colNum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= colNum; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
