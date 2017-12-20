package nefra.db;

import nefra.club.Club;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.misc.Debug;
import nefra.referee.Referee;
import nefra.settings.Settings;

import java.sql.*;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Class to hold all functions for accessing, updating, removing and inserting into the database.
 * @author Cordel Murphy
 * @version 1.0
 * @deprecated See {@link SysCreator} and {@link SysLoader}
 * @since 1.0
 */
@Deprecated
public class DBFunctions {

    /**
     * Get an instantiation of the db
     */
    private DBConnect db = new DBConnect();
    /**
     * Connect to the database to provide results and inputs
     */
    private Connection connection = db.dbConnection();

    /**
     * Gets a connection from the database and sets an initial statement. If required the database is also set up using
     * instantiateDatabase.
     * @see #instantiateDatabase(Statement)
     * @since 1.0
     */
    public DBFunctions() {
        try {
            if (Debug.debugMode){
                DelLog.getInstance().Log("Connecting....");
                DelLog.getInstance().Log(Settings.getSetting("DatabaseInstantiation"));
            }
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (Settings.getSetting("DatabaseInstantiation").equals("true")) {
                instantiateDatabase(statement);
            }
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
        }
    }


    /**
     * If required it sets up the database by creating the tables and constraints.
     * @param statement Passes the connection into the function so it can continue to use the existing one.
     * @return If the instantiation is successful or not.
     * @deprecated See {@link SysCreator#Setup()}
     * @since 1.0
     */
    @Deprecated
    private boolean instantiateDatabase(Statement statement) {
        if (Debug.debugMode)
            DelLog.getInstance().Log("Instantiating Database");
        try {
            Settings.writeSetting("DatabaseInstantiation", "false");

            //CREATE REFEREE
            statement.executeUpdate("CREATE TABLE REFEREE(" +
                    "REFEREE_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "FIRST_NAME VARCHAR(50) NOT NULL," +
                    "LAST_NAME VARCHAR(50) NOT NULL," +
                    "EMAIL VARCHAR(50)," +
                    "PHONE VARCHAR(10)," +
                    "WEEKLYFEE DECIMAL(10,2)," +
                    "TOTALFEE DECIMAL(10,2)," +
                    "UNIQUE (FIRST_NAME, LAST_NAME)" +
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
                    "UNIQUE (CLUB_NAME)" +
                    ");");

            //CREATE DIVISION
            statement.executeUpdate("CREATE TABLE DIVISION(" +
                    "DIVISION_ID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "DIVISION_NAME VARCHAR(50) UNIQUE NOT NULL," +
                    "MAIN_REFEREE_FEE DECIMAL(10,2)," +
                    "ASSISTANT_FEE DECIMAL(10,2)," +
                    "UNIQUE (DIVISION_NAME, MAIN_REFEREE_FEE, ASSISTANT_FEE)" +
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
                    "FOREIGN KEY (HOME) REFERENCES CLUB(CLUB_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (AWAY) REFERENCES CLUB(CLUB_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (DIVISION) REFERENCES DIVISION(DIVISION_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (MAIN_REFEREE) REFERENCES REFEREE(REFEREE_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (ASSISTANT_ONE) REFERENCES REFEREE(REFEREE_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (ASSISTANT_TWO) REFERENCES REFEREE(REFEREE_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "CHECK MAIN_REFEREE <> ASSISTANT_ONE AND MAIN_REFEREE <> ASSISTANT_TWO AND ASSISTANT_ONE <> ASSISTANT_TWO," +
                    "UNIQUE (HOME, AWAY, DIVISION, ROUND, YEAR)" +
                    ");");
            return true;

        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Load the database into memory so it can be used in the GUI.
     * This also sets to foreign keys to what they should be (Clubs, Division, Referees).
     * @return If the loading of the database is successful or not.
     * @deprecated See {@link SysLoader#getInstance()}
     * @since 1.0
     */
    @Deprecated
    public boolean loadDatabase() {
        ResultSet rs;
        try {
            Statement stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT * FROM REFEREE;");
            while (rs.next()) {
                /*new Referee(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getDouble(6)
                        , rs.getDouble(7));*/
            }

            rs = stmt.executeQuery("SELECT * FROM CLUB;");
            while (rs.next()) {
                /*new Club(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getDouble(9),
                        rs.getDouble(10));*/
            }

            rs = stmt.executeQuery("SELECT * FROM DIVISION;");
            while (rs.next()) {
                /*new Division(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getDouble(4));*/
            }
/*
            rs = stmt.executeQuery("SELECT * FROM GAME;");
            while (rs.next()) {
                Club home = null;
                Club away = null;
                Division division = null;
                Referee main = null;
                Referee ar1 = null;
                Referee ar2 = null;
                for (Club c : Club.clubList) {
                    if (rs.getInt(2) == c.getClubId())
                        home = c;
                    else if (rs.getInt(3) == c.getClubId())
                        away = c;
                }
                for (Division d : Division.divisionList) {
                    if (rs.getInt(4) == d.getDivisionId())
                        division = d;
                }
                for (Referee r : Referee.refereeList) {
                    if (rs.getInt(7) == r.getRefereeId())
                        main = r;
                    else if (rs.getInt(8) == r.getRefereeId())
                        ar1 = r;
                    else if (rs.getInt(9) == r.getRefereeId())
                        ar2 = r;
                }
                new Game(rs.getInt(1), home, away, division, rs.getInt(5), rs.getInt(6),
                        main, ar1, ar2, rs.getDouble(10), rs.getDouble(11),
                        rs.getDouble(12), rs.getDouble(13));
            }*/
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Inserts a referee into the database using the data given from the GUI when entered. After successfully inserting
     * into the database, it gets the id from the database (auto increment column) and sets it to the object in memory.
     * @param referee The Referee object to be entered into the database using the information from the created object.
     * @return If the insertion is successful or not.
     * @deprecated See {@link SysCreator#Referee(Referee, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean insertReferee(Referee referee) {
        try {
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
            //referee.setReferee_id(rs.getInt(1));
            referee.displayInfo();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Inserts a club into the database using the data given from the GUI when entered. After successfully inserting
     * into the database, it gets the id from the database (auto increment column) and sets it to the object in memory.
     * @param club The Club object to be entered into the database using the information from the created object.
     * @return If the insertion is successful or not.
     * @deprecated See {@link SysCreator#Club(Club, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean insertClub(Club club) {
        try {
            //Insert into database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CLUB" +
                    "(CLUB_NAME, STREET, SUBURB, STATE, POSTCODE, PRESIDENT_NAME, PRESIDENT_CONTACT, WEEKLYFEE, TOTALFEE)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pstmt.setString(1, club.getClubName());
            if (isNotEmpty(club.getStreet())) pstmt.setString(2, club.getStreet());
            else pstmt.setString(2, null);
            if (isNotEmpty(club.getSuburb())) pstmt.setString(3, club.getSuburb());
            else pstmt.setString(3, null);
            if (isNotEmpty(club.getState())) pstmt.setString(4, club.getState());
            else pstmt.setString(4, null);
            if (isNotEmpty(club.getPostcode())) pstmt.setInt(5, Integer.parseInt(club.getPostcode()));
            else pstmt.setNull(5, Types.INTEGER);
            if (isNotEmpty(club.getPresidentName())) pstmt.setString(6, club.getPresidentName());
            else pstmt.setString(6, null);
            if (isNotEmpty(club.getPresidentContact())) pstmt.setString(7, club.getPresidentContact());
            else pstmt.setString(7, null);
            pstmt.setDouble(8, club.getWeeklyFee());
            pstmt.setDouble(9, club.getTotalFee());
            pstmt.executeUpdate();

            //Get allocated club ID from database
            pstmt = connection.prepareStatement("SELECT CLUB_ID FROM CLUB WHERE CLUB_NAME = ?;");
            pstmt.setString(1, club.getClubName());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //club.setClub_id(rs.getInt(1));
            club.displayInfo();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Inserts a division into the database using the data given from the GUI when entered. After successfully inserting
     * into the database, it gets the id from the database (auto increment column) and sets it to the object in memory.
     * @param division The Division object to be entered into the database using the information from the created object.
     * @return If the insertion is successful or not.
     * @deprecated See {@link SysCreator#Division(Division, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean insertDivision(Division division) {
        try {
            //insert into database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO DIVISION " +
                    "(DIVISION_NAME, MAIN_REFEREE_FEE, ASSISTANT_FEE)" +
                    "VALUES (?, ?, ?);");
            pstmt.setString(1, division.getDivisionName());
            pstmt.setDouble(2, division.getMainRefereeFee());
            pstmt.setDouble(3, division.getArFee());
            pstmt.executeUpdate();

            //Get the Division ID from database
            pstmt = connection.prepareStatement("SELECT DIVISION_ID FROM DIVISION WHERE DIVISION_NAME = ?;");
            pstmt.setString(1, division.getDivisionName());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //division.setDivisionId(rs.getInt(1));
            division.displayInfo();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Inserts a game into the database using the data given from the GUI when entered. After successfully inserting
     * into the database, it gets the id from the database (auto increment column) and sets it to the object in memory.
     * @param game The Game object to be entered into the database using the information from the created object.
     * @return If the insertion is successful or not.
     * @deprecated See {@link SysCreator#Game(Game, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean insertGame(Game game) {
        try {
            //Insert Into Database
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO GAME " +
                    "(HOME, AWAY, DIVISION, ROUND, " +
                    "YEAR, MAIN_REFEREE, ASSISTANT_ONE, ASSISTANT_TWO, " +
                    "TOTAL_GAME_FEE, HOME_FEE, AWAY_FEE, ADMIN_FEE)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            //pstmt.setInt(1, game.getHome().getClubId());
            //pstmt.setInt(2, game.getAway().getClubId());
            //pstmt.setInt(3, game.getDivision().getDivisionId());
            pstmt.setInt(4, game.getRound());
            pstmt.setInt(5, game.getYear());
            //pstmt.setInt(6, game.getMain().getRefereeId());
            //pstmt.setInt(7, game.getAr1().getRefereeId());
            //pstmt.setInt(8, game.getAr2().getRefereeId());
            pstmt.setDouble(9, game.getTotalFee());
            pstmt.setDouble(10, game.getHomeClubFee());
            pstmt.setDouble(11, game.getAwayClubFee());
            pstmt.setDouble(12, game.getAdminFee());
            pstmt.executeUpdate();

            //Set Game ID From Database
            pstmt = connection.prepareStatement("SELECT GAME_ID FROM GAME WHERE HOME = ? AND" +
                    " AWAY = ? AND DIVISION = ? AND ROUND = ? AND YEAR = ?;");
            //pstmt.setInt(1, game.getHome().getClubId());
            //pstmt.setInt(2, game.getAway().getClubId());
            //pstmt.setInt(3, game.getDivision().getDivisionId());
            pstmt.setInt(4, game.getRound());
            pstmt.setInt(5, game.getYear());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //game.setGameId(rs.getInt(1));
            System.out.println(game);
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates the information of the referee provided in the database
     * @param referee the referee to update
     * @return whether the operation was successful or not
     * @deprecated See {@link SysCreator#Referee(Referee, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean updateReferee(Referee referee)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE REFEREE " +
                    "SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE = ?, WEEKLYFEE = ?, TOTALFEE = ? " +
                    "WHERE REFEREE_ID = ?;");
            pstmt.setString(1, referee.getFirstName());
            pstmt.setString(2, referee.getLastName());
            if(referee.getEmail() != null) pstmt.setString(3, referee.getEmail());
            else pstmt.setNull(3, Types.NULL);
            if(referee.getPhone() != null) pstmt.setString(4, referee.getPhone());
            else pstmt.setNull(4, Types.NULL);
            pstmt.setDouble(5, referee.getTotalFee());
            pstmt.setDouble(6, referee.getTotalFee());
            //pstmt.setInt(7, referee.getRefereeId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates the information of the club provided in the database
     * @param club the club to update
     * @return whether the operation was successful or not
     * @deprecated See {@link SysCreator#Club(Club, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean updateClub(Club club)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE CLUB " +
                    "SET CLUB_NAME = ?, STREET = ?, SUBURB = ?, STATE = ?, POSTCODE = ?, PRESIDENT_NAME = ?, " +
                    "PRESIDENT_CONTACT = ?, WEEKLYFEE = ?, TOTALFEE = ? " +
                    "WHERE CLUB_ID = ?;");
            pstmt.setString(1, club.getClubName());
            if(club.getStreet() != null) pstmt.setString(2, club.getStreet());
            else pstmt.setNull(2, Types.NULL);
            if(club.getSuburb() != null) pstmt.setString(3, club.getSuburb());
            else pstmt.setNull(3, Types.NULL);
            if(club.getState() != null) pstmt.setString(4, club.getState());
            else pstmt.setNull(4, Types.NULL);
            if(club.getPostcode() != null) pstmt.setString(5, club.getPostcode());
            else pstmt.setNull(5, Types.NULL);
            if(club.getPresidentName() != null) pstmt.setString(6, club.getPresidentName());
            else pstmt.setNull(6, Types.NULL);
            if(club.getPresidentContact() != null) pstmt.setString(7, club.getPresidentContact());
            else pstmt.setNull(7, Types.NULL);
            pstmt.setDouble(8, club.getTotalFee());
            pstmt.setDouble(9, club.getTotalFee());
            //pstmt.setInt(10, club.getClubId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates the information of the division provided in the database
     * @param division the division to update
     * @return whether the operation was successful or not
     * @deprecated See {@link SysCreator#Division(Division, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean updateDivision(Division division)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE DIVISION " +
                    "SET DIVISION_NAME = ?, MAIN_REFEREE_FEE = ?, ASSISTANT_FEE = ? " +
                    "WHERE DIVISION_ID = ?;");
            pstmt.setString(1, division.getDivisionName());
            pstmt.setDouble(2, division.getMainRefereeFee());
            pstmt.setDouble(3, division.getArFee());
            //pstmt.setInt(4, division.getDivisionId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates the information of the game provided in the database
     * @param game the game to update
     * @return whether the operation was successful or not
     * @deprecated See {@link SysCreator#Game(Game, boolean)}
     * @since 1.0
     */
    @Deprecated
    public boolean updateGame(Game game)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE GAME " +
                    "SET HOME = ?, AWAY = ?, DIVISION = ?, ROUND = ?, YEAR = ?, " +
                    "MAIN_REFEREE = ?, ASSISTANT_ONE = ?, ASSISTANT_TWO = ?, " +
                    "TOTAL_GAME_FEE = ?, HOME_FEE = ?, AWAY_FEE = ?, ADMIN_FEE = ? " +
                    "WHERE GAME_ID = ?;");
            //pstmt.setInt(1, game.getHome().getClubId());
            //pstmt.setInt(2, game.getAway().getClubId());
            //pstmt.setInt(3, game.getDivision().getDivisionId());
            pstmt.setInt(4, game.getRound());
            pstmt.setInt(5, game.getYear());
           // pstmt.setInt(6, game.getMain().getRefereeId());
            //pstmt.setInt(7, game.getAr1().getRefereeId());
            //pstmt.setInt(8, game.getAr2().getRefereeId());
            pstmt.setDouble(9, game.getTotalFee());
            pstmt.setDouble(10, game.getHomeClubFee());
            pstmt.setDouble(11, game.getAwayClubFee());
            pstmt.setDouble(12, game.getAdminFee());
           // pstmt.setInt(13, game.getGameId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Removes the referee provided from the database
     * @param refereeID the ID of the referee to remove
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean removeReferee(UUID refereeID)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM REFEREE WHERE REFEREE_ID = ?;");
            //pstmt.setInt(1, refereeID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Removes the club provided from the database
     * @param clubID the ID of the club to remove
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean removeClub(UUID clubID)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CLUB WHERE CLUB_ID = ?;");
            //pstmt.setInt(1, clubID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Removes the division provided from the database
     * @param divisionID the ID of the division to remove
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean removeDivision(UUID divisionID)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM DIVISION WHERE DIVISION_ID = ?;");
            //pstmt.setInt(1, divisionID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Removes the game provided from the database
     * @param gameID the ID of the game to remove
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean removeGame(UUID gameID)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM GAME WHERE GAME_ID = ?;");
            //pstmt.setInt(1, gameID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates only the fees for the referee
     * @param refereeID ID of the referee to update
     * @param totalFee the total fee to update to
     * @param weeklyFee the weekly fee to update to
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean updateRefereeFees(int refereeID, double totalFee, double weeklyFee)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE REFEREE " +
                    "SET TOTALFEE = ?, WEEKLYFEE = ? " +
                    "WHERE REFEREE_ID = ?;");
            pstmt.setDouble(1, totalFee);
            pstmt.setDouble(2, weeklyFee);
            pstmt.setInt(3, refereeID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }

    /**
     * Updates only the fees for the club
     * @param clubID ID of the club to update
     * @param totalFee the total fee to update to
     * @param weeklyFee the weekly fee to update to
     * @return whether the operation was successful or not
     * @deprecated
     * @since 1.0
     */
    @Deprecated
    public boolean updateClubFees(int clubID, double totalFee, double weeklyFee)
    {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE CLUB " +
                    "SET TOTALFEE = ?, WEEKLYFEE = ? " +
                    "WHERE CLUB_ID = ?;");
            pstmt.setDouble(1, totalFee);
            pstmt.setDouble(2, weeklyFee);
            pstmt.setInt(3, clubID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            DelLog.getInstance().Log(sqle);
            return false;
        }
    }


    /**
     * Prints the database to standard out to check database is working properly.
     * @deprecated See {@link DelLog#DumpLog()}
     * @since 1.0
     */
    @Deprecated
    public void printDatabase() {
        if(Debug.debugMode) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs;
                ResultSetMetaData rsmd;

                //REFEREE
                printColumns("referee");
                rs = stmt.executeQuery("SELECT * FROM REFEREE");
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }

                //CLUB
                printColumns("club");
                rs = stmt.executeQuery("SELECT * FROM CLUB");
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }

                //DIVISION
                printColumns("division");
                rs = stmt.executeQuery("SELECT * FROM DIVISION");
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }

                //GAME
                printColumns("game");
                rs = stmt.executeQuery("SELECT * FROM GAME");
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints the columns from the specified table to standard out to check database is working properly.
     * @param table the table whose columns are to be printed.
     * @deprecated No longer required
     * @since 1.0
     */
    @Deprecated
    private void printColumns(String table) {
        if (Debug.debugMode) {
            try {
                String sql;
                switch (table.toLowerCase()) {
                    case "referee":
                        sql = "SHOW COLUMNS FROM REFEREE";
                        break;
                    case "club":
                        sql = "SHOW COLUMNS FROM CLUB";
                        break;
                    case "division":
                        sql = "SHOW COLUMNS FROM DIVISION";
                        break;
                    case "game":
                        sql = "SHOW COLUMNS FROM GAME";
                        break;
                    default:
                        sql = null;
                        break;
                }
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
