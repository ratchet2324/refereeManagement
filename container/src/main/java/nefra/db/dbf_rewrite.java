package nefra.db;

import nefra.club.Club;
import nefra.game.Division;
import nefra.game.Game;
import nefra.referee.Referee;
import nefra.settings.Settings;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class dbf_rewrite {
    public dbf_rewrite()
    {
        try {
            System.out.println("Connecting....");
            Connection connection = getConnection();
            System.out.println(Settings.getSetting("DatabaseInstantiation"));
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (Settings.getSetting("DatabaseInstantiation").equals("true")) { instantiateDatabase(statement); }
            connection.close();
        }
        catch (SQLException sqle) { sqle.printStackTrace(); }
    }

    private Connection getConnection()
    {
        DBConnect dbConnect = new DBConnect();
        return dbConnect.dbConnection();
    }

    private void instantiateDatabase(Statement statement)
    {
        System.out.println("Instantiating Database");
        try
        {
            Settings.writeSetting("DatabaseInstantiation", "false");

            //CREATE REFEREE
            statement.executeUpdate("CREATE TABLE REFEREE(" +
                    "REFEREE_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "FIRST_NAME VARCHAR(50) NOT NULL," +
                    "LAST_NAME VARCHAR(50) NOT NULL," +
                    "EMAIL VARCHAR(50)," +
                    "PHONE VARCHAR(10)," +
                    "WEEKLYFEE DECIMAL(10,2)," +
                    "TOTALFEE DECIMAL(10,2)" +
                    ");");

            //CREATE CLUB
            statement.executeUpdate("CREATE TABLE CLUB(" +
                    "CLUB_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "CLUB_NAME VARCHAR(50) UNIQUE NOT NULL," +
                    "STREET VARCHAR(100)," +
                    "SUBURB VARCHAR(50)," +
                    "STATE VARCHAR(3)," +
                    "POSTCODE INTEGER(4)," +
                    "PRESIDENT_NAME VARCHAR(50)," +
                    "PRESIDENT_CONTACT VARCHAR(50)," +
                    "WEEKLYFEE DECIMAL(10,2)," +
                    "TOTALFEE DECIMAL (10,2)," +
                    ");");

            //CREATE DIVISION
            statement.executeUpdate("CREATE TABLE DIVISION(" +
                    "DIVISION_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "DIVISION_NAME VARCHAR(50) UNIQUE NOT NULL," +
                    "MAIN_REEFEREE_FEE DECIMAL(10,2)," +
                    "ASSISTANT_FEE DECIMAL(10,2)" +
                    ");");

            //CREATE GAME
            statement.executeUpdate("CREATE TABLE GAME(" +
                    "GAME_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "HOME INT NOT NULL," +
                    "AWAY INT NOT NULL," +
                    "DIVISION INT NOT NULL," +
                    "ROUND INT NOT NULL," +
                    "YEAR INT NOT NULL," +
                    "MAIN_REFEREE INT NOT NULL," +
                    "ASSISTANT_ONE INT NOT NULL," +
                    "ASSISTANT_TWO INT NOT NULL," +
                    "TOTAL_GAME_FEE DECIMAL(10,2) NOT NULL," +
                    "HOME_FEE DECIMAL(10,2) NOT NULL," +
                    "AWAY_FEE DECIMAL(10,2) NOT NULL," +
                    "ADMIN_FEE DECIMAL(10,2) NOT NULL," +
                    "UNIQUE (GAME_ID, HOME, AWAY, ROUND, YEAR)," +
                    "FOREIGN KEY (HOME) REFERENCES CLUB(CLUB_ID)," +
                    "FOREIGN KEY (AWAY) REFERENCES CLUB(CLUB_ID)," +
                    "FOREIGN KEY (DIVISION) REFERENCES DIVISION(DIVISION_ID)," +
                    "FOREIGN KEY (MAIN_REFEREE) REFERENCES REFEREE(REFEREE_ID)," +
                    "FOREIGN KEY (ASSISTANT_ONE) REFERENCES REFEREE(REFEREE_ID)," +
                    "FOREIGN KEY (ASSISTANT_TWO) REFERENCES REFEREE(REFEREE_ID)," +
                    "CHECK MAIN_REFEREE <> ASSISTANT_ONE AND MAIN_REFEREE <> ASSISTANT_TWO AND ASSISTANT_ONE <> ASSISTANT_TWO"+
                    ");");

        } catch (SQLException sqle) { sqle.printStackTrace(); }
    }

    /*
    1. LOAD DATABASE
    2. FROM DATABASE LOAD OBJECTS INTO MEMORY
        2.1 TO FILE
        2.2 DIRECTLY
            2.2.1 VIA MULTI-DIMENSIONAL ARRAY
            2.2.2 VIA ARRAY-LIST
        2.3 OBJECT TO DB
     */
    public void loadDatabase()
    {
        ResultSet rs;
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            //2.3
            //REFEREE
            rs = stmt.executeQuery("SELECT * FROM REFEREE;");
            while(rs.next()) {
                new Referee(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getDouble(6)
                        ,rs.getDouble(7));
            }

            //CLUB
            rs = stmt.executeQuery("SELECT * FROM CLUB;");
            while(rs.next()) {
                new Club(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getDouble(9),
                        rs.getDouble(10));
            }

            //DIVISION
            rs = stmt.executeQuery("SELECT * FROM DIVISION;");
            while(rs.next()) {
                new Division(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3),rs.getDouble(4));
            }

            rs = stmt.executeQuery("SELECT * FROM GAME;");
            while(rs.next()) {
                Club home = null;
                Club away = null;
                Division division = null;
                Referee main = null;
                Referee ar1 = null;
                Referee ar2 = null;
                for(Club c : Club.clubList)
                {
                    if(rs.getInt(2) == c.getClubId())
                        home = c;
                    else if (rs.getInt(3) == c.getClubId())
                        away = c;
                }
                for(Division d : Division.divisionList)
                {
                    if(rs.getInt(4) == d.getDivisionId())
                        division = d;
                }
                for(Referee r : Referee.refereeList)
                {
                    if(rs.getInt(7) == r.getRefereeId())
                        main = r;
                    else if (rs.getInt(8) == r.getRefereeId())
                        ar1 = r;
                    else if(rs.getInt(9) == r.getRefereeId())
                        ar2 = r;
                }
                new Game(rs.getInt(1), home, away, division, rs.getInt(5), rs.getInt(6),
                        main, ar1, ar2, rs.getDouble(10), rs.getDouble(11),
                        rs.getDouble(12), rs.getDouble(13));
            }
            connection.close();
        } catch (SQLException sqle) { sqle.printStackTrace(); }
    }

    public void insertReferee(Referee referee)
    {
        Connection connection = getConnection();
        try
        {
            //Insert into database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO REFEREE" +
                    "(FIRST_NAME, LAST_NAME, EMAIL, PHONE, WEEKLYFEE, TOTALFEE) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, referee.getFirstName());
            pstmt.setString(2, referee.getLastName());
            pstmt.setString(3, referee.getEmail());
            pstmt.setString(4, referee.getPhone());
            pstmt.setDouble(5, referee.getWeeklyFee());
            pstmt.setDouble(6, referee.getTotalFee());
            pstmt.executeUpdate();

            //Get Referee ID from database
            pstmt = connection.prepareStatement("SELECT REFEREE_ID FROM REFEREE WHERE FIRST_NAME = ? AND LAST_NAME = ?;");
            pstmt.setString(1, referee.getFirstName());
            pstmt.setString(2, referee.getLastName());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            referee.setReferee_id(rs.getInt(1));
            referee.displayInfo();
            connection.close();
        } catch (SQLException sqle) { sqle.printStackTrace(); }
    }

    public void insertClub(Club club)
    {
        Connection connection = getConnection();
        try
        {
            //Insert into database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CLUB" +
                    "(CLUB_NAME, STREET, SUBURB, STATE, POSTCODE, PRESIDENT_NAME, PRESIDENT_CONTACT, WEEKLYFEE, TOTALFEE)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, club.getClubName());
            pstmt.setString(2, club.getStreet());
            pstmt.setString(3, club.getSuburb());
            pstmt.setString(4, club.getState());
            pstmt.setInt(5, Integer.parseInt(club.getPostcode()));
            pstmt.setString(6, club.getPresidentName());
            pstmt.setString(7, club.getPresidentContact());
            pstmt.setDouble(8, club.getWeeklyFee());
            pstmt.setDouble(9, club.getTotalFee());
            pstmt.executeUpdate();

            //Get allocated club ID from database
            pstmt = connection.prepareStatement("SELECT CLUB_ID FROM CLUB WHERE CLUB_NAME = ?;");
            pstmt.setString(1, club.getClubName());
            ResultSet rs = pstmt.executeQuery();
            club.setClub_id(rs.getInt(1));
            connection.close();
        } catch (SQLException sqle) { sqle.printStackTrace(); }
    }

    public void insertDivision(Division division)
    {
        Connection connection = getConnection();
        try
        {
            //insert into database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO DIVISION " +
                    "(DIVISION_NAME, MAIN_REFEREE_FEE, ASSISTANT_FEE)" +
                    "VALUES (?, ?, ?)");
            pstmt.setString(1, division.getDivisionName());
            pstmt.setDouble(2, division.getMainRefereeFee());
            pstmt.setDouble(3, division.getArFee());
            pstmt.executeUpdate();

            //Get the Division ID from database
            pstmt = connection.prepareStatement("SELECT DIVISION_ID FROM DIVISION WHERE DIVISION_NAME = ?;");
            pstmt.setString(1, division.getDivisionName());
            ResultSet rs = pstmt.executeQuery();
            division.setDivision_id(rs.getInt(1));

            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void insertGame(Game game)
    {
        Connection connection = getConnection();
        try
        {
            //Insert Into Database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO GAME " +
                    "(HOME, AWAY, DIVISION, ROUND, " +
                    "YEAR, MAIN_REFEREE, ASSISTANT_ONE, ASSISTANT_TWO, " +
                    "TOTAL_GAME_FEE, HOME_FEE, AWAY_FEE, ADMIN_FEE)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, game.getHome().getClubId());
            pstmt.setInt(2, game.getAway().getClubId());
            pstmt.setInt(3, game.getDivision().getDivisionId());
            pstmt.setInt(4, game.getRound());
            pstmt.setInt(5, game.getYear());
            pstmt.setInt(6, game.getMain().getRefereeId());
            pstmt.setInt(7, game.getAr1().getRefereeId());
            pstmt.setInt(8, game.getAr2().getRefereeId());
            pstmt.setDouble(9, game.getTotalFee());
            pstmt.setDouble(10, game.getHomeClubFee());
            pstmt.setDouble(11, game.getAwayClubFee());
            pstmt.setDouble(12, game.getAdminFee());
            pstmt.executeUpdate();

            //Set Game ID From Database
            pstmt = connection.prepareStatement("SELECT GAME_ID FROM GAME WHERE HOME = ? AND" +
                    " AWAY = ? AND DIVISION = ? AND ROUND = ? AND YEAR = ?");
            pstmt.setInt(1, game.getHome().getClubId());
            pstmt.setInt(2, game.getAway().getClubId());
            pstmt.setInt(3, game.getDivision().getDivisionId());
            pstmt.setInt(4, game.getRound());
            pstmt.setInt(5, game.getYear());
            ResultSet rs = pstmt.executeQuery();
            game.setGameId(rs.getInt(1));

            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void printDatabase()
    {
        try
        {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;
            ResultSetMetaData rsmd;

            //REFEREE
            printColumns("referee");
            rs  = stmt.executeQuery("SELECT * FROM REFEREE");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            //CLUB
            printColumns("club");
            rs  = stmt.executeQuery("SELECT * FROM CLUB");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            //DIVISION
            printColumns("division");
            rs  = stmt.executeQuery("SELECT * FROM DIVISION");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }

            //GAME
            printColumns("game");
            rs  = stmt.executeQuery("SELECT * FROM GAME");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    private void printColumns(String table)
    {
        try
        {
            Connection connection = getConnection();
            String sql;
            switch (table.toLowerCase())
            {
                case "referee": sql = "SHOW COLUMNS FROM REFEREE"; break;
                case "club": sql = "SHOW COLUMNS FROM CLUB"; break;
                case "division": sql = "SHOW COLUMNS FROM DIVISION"; break;
                case "game": sql = "SHOW COLUMNS FROM GAME"; break;
                default: sql = null; break;
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i = 1; i <= rsmd.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
    }
}
