package nefra.db;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.club.Club;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.misc.Debug;
import nefra.referee.Referee;

import java.text.DecimalFormat;
import java.text.ParseException;

public class GUIFunctions {
    private DBFunctions db = new DBFunctions();
    private final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Used if firstName, lastName and both email and phone is entered.
     * @param e the event passed through.
     * @param firstName First Name of the referee.
     * @param lastName Last Name of the Referee.
     * @param email Email of the referee.
     * @param phone Phone number of the referee.
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public boolean makeReferee(ActionEvent e, String firstName, String lastName, String email, String phone) {
        e.consume();
        Referee referee = new Referee(firstName, lastName, email, phone);
        if (SysCreator.getInstance().Referee(referee, true))
        {
            if (Debug.debugMode)
                db.printDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the referee information with the information provided
     * @param e ActionEvent passed via GUI
     * @param referee The referee to modify
     * @param firstName The new first name
     * @param lastName The new last name
     * @param email The new email address
     * @param phone The new phone
     * @since 1.0
     * @return whether the update was successful or not
     */
    public Boolean updateReferee(ActionEvent e, Referee referee, String firstName, String lastName,
                                 String email, String phone)
    {
        e.consume();
        for (Referee r : Referee.refereeList)
        {
            if(r.getRefereeId() == referee.getRefereeId())
            {
                r.setFirstName(firstName);
                r.setLastName(lastName);
                r.setEmail(email);
                r.setPhone(phone);
            }
        }
        referee.setFirstName(firstName);
        referee.setLastName(lastName);
        referee.setEmail(email);
        referee.setPhone(phone);
        if(SysCreator.getInstance().Referee(referee, false))
        {
            if (Debug.debugMode)
                db.printDatabase();
            return true;
        }
        else {
            displayErrorReferee(e);
            return false;
        }
    }

    /**
     *  Removes the referee from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param referee the referee to remove
     */
    //TODO: REMOVE REFERENCES ON GUI AND IN MEMORY
    public void removeReferee(ActionEvent e, Referee referee)
    {
        if(db.removeReferee(referee.getRefereeId()))
            db.loadDatabase();
        for (Referee r : Referee.refereeList) if(r.getRefereeId() == referee.getRefereeId()) r.delete();
    }

    /**
     * Alert popup if required information is not entered
     * @param e the event passed through.
     * @since 1.0
     */
    public void displayErrorReferee(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("Minimum required information is:\n" +
                "First Name\n" +
                "Last Name");
    }

    /**
     * Create and insert the club into the database
     * @param e ActionEvent passed via GUI
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param postcode         The postcode (zip code) for the suburb
     * @param state            The state the club is in
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public Boolean makeClub(ActionEvent e, String clubName, String street,
                            String suburb, String state, String postcode,
                            String presidentName, String presidentContact) {
        e.consume();
        Club club = new Club(clubName, street, suburb, state, postcode, presidentName, presidentContact);
        if(SysCreator.getInstance().Club(club, true))
        {
            if(Debug.debugMode)
                db.printDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the club information with the information provided
     * @param e ActionEvent passed via GUI
     * @param club The club to modify
     * @param clubName         New name of the Club
     * @param street           The new street address, also includes street number
     * @param suburb           The new suburb the club is located in
     * @param postcode         The new postcode (zip code) for the suburb
     * @param state            The new state the club is in
     * @param presidentName    The new name of the president/chairman of the club
     * @param presidentContact New contact info for the president/chairman, can be phone or email
     * @since 1.0
     * @return whether the update was successful or not
     */
    public Boolean updateClub(ActionEvent e, Club club, String clubName, String street,
                              String suburb, String state, String postcode,
                              String presidentName, String presidentContact) {
        e.consume();
        for (Club c : Club.clubList) {
            if(c.getClubId() == club.getClubId())
            {
                c.setClubName(clubName);
                c.setStreet(street);
                c.setSuburb(suburb);
                c.setState(state);
                c.setPostcode(postcode);
                c.setPresidentName(presidentName);
                c.setPresidentContact(presidentContact);
            }
        }
        club.setClubName(clubName);
        club.setStreet(street);
        club.setSuburb(suburb);
        club.setState(state);
        club.setPostcode(postcode);
        club.setPresidentName(presidentName);
        club.setPresidentContact(presidentContact);
        if(SysCreator.getInstance().Club(club, false))
        {
            if (Debug.debugMode)
                db.printDatabase();
            return true;
        }
        else
        {
            displayErrorClub(e);
            return false;
        }
    }

    /**
     *  Removes the club from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param club the club to remove
     */
    //TODO: REMOVE REFERENCES ON GUI AND IN MEMORY
    public void removeClub(ActionEvent e, Club club)
    {
        if(db.removeClub(club.getClubId()))
            db.loadDatabase();
        for (Club c : Club.clubList) if(c.getClubId() == club.getClubId()) c.delete();
        e.consume();
    }

    //TODO: Check inputted information
    /**
     * Alert popup if required information is not entered
     * @param e the event passed through
     * @since 1.0
     */
    public void displayErrorClub(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("Minimum required information is the Club Name");
    }

    /**
     * The GUI call to insert the created division and insert it into the database.
     * @param e the ActionEvent passed in, required to allow method to be used on GUI, only consumes the event.
     * @param divisionName Name of the division, this is displayed on the GUI in different places such as
     *                     when viewing the divisions available and when creating games
     * @param mainRefFee The fee for the main (centre) referee
     * @param arFee The fee for the assistant referees (sidelines/touchline)
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public Boolean makeDivision(ActionEvent e, String divisionName, String mainRefFee, String arFee)
    {
        e.consume();
        try {
            double main = df.parse(mainRefFee).doubleValue();
            double ar = df.parse(arFee).doubleValue();
            df.format(main);
            df.format(ar);

            if(Debug.debugMode)
            {
                DelLog.getInstance().Log("Main: " + main);
                DelLog.getInstance().Log("AR: " + ar);
            }
            Division division = new Division(divisionName, main, ar);
            if(SysCreator.getInstance().Division(division, true))
            {
                if (Debug.debugMode)
                    db.printDatabase();
                return true;
            }
        } catch (ParseException pe) { pe.printStackTrace(); }
        return false;
    }

    /**
     * The GUI call to insert the created game and insert it into the database.
     * @param e the ActionEvent passed in, required to allow method to be used on GUI, only consumes the event.
     * @param home The home team
     * @param away The away team
     * @param division The division the game was played in for example Under 16's, or All Age Men's Division 3
     * @param round The round number for the game for example 3 or 10
     * @param year The year the game is played in, required for uniqueness until multiple database is supported
     * @param main The main (centre) referee for the game
     * @param ar1 The first assistant referee (sideline/touchline)
     * @param ar2 The second assistant referee (sideline/touchline)
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public Boolean makeGame(ActionEvent e, Club home, Club away, Division division, int round, int year, Referee main,
                            Referee ar1, Referee ar2)
    {
        e.consume();
        Game game = new Game(home, away, division, round, year, main, ar1, ar2);
        game.gameFees(false);
        game.payReferee(false, null);
        if(SysCreator.getInstance().Game(game, true))
        {
            if(Debug.debugMode)
                db.printDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the division information with the information provided
     * @param e ActionEvent passed via GUI
     * @param division The division to modify
     * @param divisionName The new division name
     * @param mainFee The new main referee fee
     * @param arFee The new assistant referee fee
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public Boolean updateDivision(ActionEvent e, Division division, String divisionName, double mainFee, double arFee)
    {
        e.consume();
        for (Division d : Division.divisionList)
            if(d.getDivisionId() == division.getDivisionId()) {
                d.setDivisionName(divisionName);
                d.setMainRefereeFee(mainFee);
                d.setArFee(arFee);
            }
        division.setDivisionName(divisionName);
        division.setMainRefereeFee(mainFee);
        division.setArFee(arFee);
        for(Game g : Game.gameList) if(g.getDivision().getDivisionId() == division.getDivisionId()) g.gameFees(false);
        if(SysCreator.getInstance().Division(division, false))
        {
            if(Debug.debugMode)
                db.printDatabase();
            return true;
        }
        else
        {
            displayErrorDivision(e);
            return false;
        }
    }

    /**
     *  Removes the referee from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param division the referee to remove
     */
    //TODO: REMOVE REFERENCES ON GUI AND IN MEMORY
    public void removeDivision(ActionEvent e, Division division)
    {
        if(db.removeDivision(division.getDivisionId()))
            db.loadDatabase();
        for (Division d : Division.divisionList) if(d.getDivisionId() == division.getDivisionId()) d.delete();
        e.consume();
    }

    /**
     * Updates the referee information with the information provided
     * @param e ActionEvent passed via GUI
     * @param game The game to modify
     * @param home The new home team
     * @param away The new away team
     * @param division The new division
     * @param round The new round
     * @param year The new year
     * @param main The new main referee
     * @param ar1 The new assistant referee (1)
     * @param ar2 The new assistant referee (2)
     * @since 1.0
     * @return whether the creation was successful or not
     */
    public Boolean updateGame(ActionEvent e, Game game, Club home, Club away, Division division, int round, int year,
                              Referee main, Referee ar1, Referee ar2)
    {
        e.consume();

        for (Game g : Game.gameList)
        {
            if(g.getGameId() == game.getGameId())
            {
                g.setHome(home);
                g.setAway(away);
                g.setDivision(division);
                g.setRound(round);
                g.setYear(year);
                g.setMain(main);
                g.setAr1(ar1);
                g.setAr2(ar2);
                g.gameFees(false);
                g.payReferee(false, null);
            }
        }
        game.setHome(home);
        game.setAway(away);
        game.setDivision(division);
        game.setRound(round);
        game.setYear(year);
        game.setMain(main);
        game.setAr1(ar1);
        game.setAr2(ar2);
        game.gameFees(false);
        game.payReferee(false, null);
        if(SysCreator.getInstance().Game(game, false))
        {
            if(Debug.debugMode)
                db.printDatabase();
            return true;
        }
        else
        {
            displayErrorGame(e);
            return false;
        }
    }

    /**
     *  Removes the game from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param game the game to remove
     */
    //TODO: REMOVE REFERENCES ON GUI AND IN MEMORY
    public void removeGame(ActionEvent e, Game game)
    {
        if(db.removeGame(game.getGameId()))
            db.loadDatabase();
        for (Game g : Game.gameList) if(g.getGameId() == game.getGameId()) g.delete();
        e.consume();
    }

    //FIXME: Make unambiguous
    //TODO: Check inputted information.
    /**
     * Called if an Error is encountered
     * @param e the ActionEvent passed in, required to allow method to be used on GUI, only consumes the event.
     * @since 1.0
     */
    public void displayErrorDivision(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("All fields are required!\n" +
                "Need Division Name!\n" +
                "Need main referee fee!\n" +
                "Need assistant referee fee!");
    }

    //TODO: Check inputted information.
    /**
     * Called if an Error is encountered
     * @param e the ActionEvent passed in, required to allow method to be used on GUI, only consumes the event.
     * @since 1.0
     */
    public void displayErrorGame(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("All fields are required!\n" +
                "Need Home Team Name!\n" +
                "Need Away Team Name!\n" +
                "Need Division Name!\n" +
                "Need Round!\n" +
                "Need Year!\n" +
                "Need main referee!\n" +
                "Need assistant referees!");
    }
}
