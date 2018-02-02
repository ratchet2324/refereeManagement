package nefra.db;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nefra.club.Club;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.referee.Referee;

import java.text.DecimalFormat;
import java.text.ParseException;

public class GUIFunctions {
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
        if(firstName.isEmpty() || lastName.isEmpty()) {
            displayErrorReferee(e);
            return false;
        }
        Referee referee = new Referee(firstName, lastName, email, phone);
        return (SysCreator.getInstance().Referee(referee, true));
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
        if(firstName.isEmpty() || lastName.isEmpty()) {
            displayErrorReferee(e);
            return false;
        }
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

        return (SysCreator.getInstance().Referee(referee, false));
    }

    /**
     *  Removes the referee from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param referee the referee to remove
     */
    public void removeReferee(ActionEvent e, Referee referee)
    {
        e.consume();
        if(SysCreator.getInstance().Remove("referee", referee.getRefereeId()))
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
        error.showAndWait();
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
        if(clubName.isEmpty())
        {
            displayErrorClub(e);
            return false;
        }
        Club club = new Club(clubName, street, suburb, state, postcode, presidentName, presidentContact);
        return (SysCreator.getInstance().Club(club, true));
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
        if(clubName.isEmpty())
        {
            displayErrorClub(e);
            return false;
        }
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
        return (SysCreator.getInstance().Club(club, false));
    }

    /**
     *  Removes the club from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param club the club to remove
     */
    public void removeClub(ActionEvent e, Club club)
    {
        int errorCode = 0;
        for(Game g : Game.gameList)
        {
            if(club.getClubId() == g.getHome().getClubId() || club.getClubId() == g.getAway().getClubId())
            {
                errorCode = 1;
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Cannot Remove!");
                error.setContentText("Unable to remove club due to being in a game.");
            }
        }

        if(errorCode != 0)
        {
            if(SysCreator.getInstance().Remove("club", club.getClubId()))
                for (Club c : Club.clubList) if(c.getClubId() == club.getClubId()) c.delete();
        }
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
        if(divisionName.isEmpty() || mainRefFee.isEmpty() || arFee.isEmpty())
        {
            displayErrorDivision(e);
            return false;
        }
        try {
            double main = df.parse(mainRefFee).doubleValue();
            double ar = df.parse(arFee).doubleValue();
            df.format(main);
            df.format(ar);
            Division division = new Division(divisionName, main, ar);
            if(SysCreator.getInstance().Division(division, true)) return true;
        } catch (ParseException pe) { DelLog.getInstance().Log(pe); }
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
        if(home == null || away == null || division == null ||
                round <=0 || year <= 0 || main == null || ar1 == null || ar2 == null)
        {
            displayErrorGame(e);
            return false;
        }
        Game game = new Game(home, away, division, round, year, main, ar1, ar2);
        game.gameFees(false);
        game.payReferee(false, null);
        return SysCreator.getInstance().Game(game, true);
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
        if(divisionName.isEmpty() || mainFee <= 0 || arFee <= 0)
        {
            displayErrorDivision(e);
            return false;
        }
        for(Game x : Game.gameList) if(x.getDivision().getDivisionId() == division.getDivisionId()) {
            x.getHome().addToWeeklyFee(-x.getHomeClubFee());
            x.getAway().addToWeeklyFee(-x.getAwayClubFee());
            x.getMain().addToWeeklyFee(-x.getDivision().getMainRefereeFee());
            x.getAr1().addToWeeklyFee(-x.getDivision().getArFee());
            x.getAr2().addToWeeklyFee(-x.getDivision().getArFee());
        }

        for (Division d : Division.divisionList)
            if(d.getDivisionId() == division.getDivisionId()) {
                d.setDivisionName(divisionName);
                d.setMainRefereeFee(mainFee);
                d.setArFee(arFee);
            }
        division.setDivisionName(divisionName);
        division.setMainRefereeFee(mainFee);
        division.setArFee(arFee);
        for(Game g : Game.gameList) if(g.getDivision().getDivisionId() == division.getDivisionId()) {
            g.gameFees(false);
            g.payReferee(false, null);
        }

        return SysCreator.getInstance().Division(division, false);
    }

    /**
     *  Removes the referee from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param division the referee to remove
     */
    public void removeDivision(ActionEvent e, Division division)
    {
        for(Game g : Game.gameList)
        {
            if(g.getDivision().getDivisionId() == division.getDivisionId())
            {
                g.getMain().addToWeeklyFee(-division.getMainRefereeFee());
                g.getAr1().addToWeeklyFee(-division.getArFee());
                g.getAr2().addToWeeklyFee(-division.getArFee());
                g.getHome().addToWeeklyFee(-g.getHomeClubFee());
                g.getAway().addToWeeklyFee(-g.getAwayClubFee());
                g.setDivision(null);
            }
        }
        if(SysCreator.getInstance().Remove("division", division.getDivisionId()))
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

        if(game == null || home == null || away == null || division == null ||
                round <=0 || year <= 0 || main == null || ar1 == null || ar2 == null)
        {
            displayErrorGame(e);
            return false;
        }

        game.getMain().addToWeeklyFee(-division.getMainRefereeFee());
        game.getAr1().addToWeeklyFee(-division.getArFee());
        game.getAr2().addToWeeklyFee(-division.getArFee());
        game.getHome().addToWeeklyFee(-game.getHomeClubFee());
        game.getAway().addToWeeklyFee(-game.getAwayClubFee());

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
        return SysCreator.getInstance().Game(game, false);
    }

    /**
     *  Removes the game from the database, memory and GUI.
     * @param e event passed from mouse click
     * @param game the game to remove
     */
    public void removeGame(ActionEvent e, Game game)
    {
        game.getMain().addToWeeklyFee(-game.getDivision().getMainRefereeFee());
        game.getAr1().addToWeeklyFee(-game.getDivision().getArFee());
        game.getAr2().addToWeeklyFee(-game.getDivision().getArFee());
        game.getHome().addToWeeklyFee(-game.getHomeClubFee());
        game.getAway().addToWeeklyFee(-game.getAwayClubFee());

        if(SysCreator.getInstance().Remove("game", game.getGameId()))
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

    public Label viewWarning(String name)
    {
        Label label =  new Label(String.format("To remove a %s click on it, then click remove and confirm it.", name));
        label.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: red;" +
                "-fx-font-size: 20px;");
        return label;
    }

    public int removeWarning(String name)
    {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation");
        confirm.setHeaderText(null);
        confirm.setContentText(String.format("Are you sure you wish to remove %s?", name));
        confirm.showAndWait();
        if(confirm.getResult() == ButtonType.OK) return 1;
        else return 0;
    }
}
