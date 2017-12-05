package nefra.game;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.club.Club;
import nefra.db.DBFunctions;
import nefra.misc.Debug;
import nefra.referee.Referee;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * The functions for the Game and Division GUIs (Create, Edit, View)
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class GUIFunctions {
    private DBFunctions db = new DBFunctions();
    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * The GUI call to insert the created division and insert it into the database.
     * @param e the ActionEvent passed in, required to allow method to be used on GUI, only consumes the event.
     * @param divisionName Name of the division, this is displayed on the GUI in different places such as
     *                     when viewing the divisions available and when creating games
     * @param mainRefFee The fee for the main (centre) referee
     * @param arFee The fee for the assistant referees (sidelines/touchline)
     * @since 1.0
     */
    public void makeDivision(ActionEvent e, String divisionName, String mainRefFee, String arFee)
    {
        e.consume();
        try {
            double main = df.parse(mainRefFee).doubleValue();
            double ar = df.parse(arFee).doubleValue();
            df.format(main);
            df.format(ar);

            //TODO: REMOVE PRINT STATEMENTS
            System.out.println("Main: " + main);
            System.out.println("AR: " + ar);
            Division division = new Division(divisionName, main, ar);
            db.insertDivision(division);
            if(Debug.debugMode)
                db.printDatabase();
        } catch (ParseException pe) { pe.printStackTrace(); }
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
     */
    public void makeGame(ActionEvent e, Club home, Club away, Division division, int round, int year, Referee main,
                         Referee ar1, Referee ar2)
    {
        e.consume();
        Game game = new Game(home, away, division, round, year, main, ar1, ar2);
        game.gameFees(false);
        game.payReferee(false, null);
        db.insertGame(game);
        if(Debug.debugMode)
            db.printDatabase();
    }

    /**
     * Updates the division information with the information provided
     * @param e ActionEvent passed via GUI
     * @param division The division to modify
     * @param divisionName The new division name
     * @param mainFee The new main referee fee
     * @param arFee The new assistant referee fee
     * @since 1.0
     */
    public void updateDivision(ActionEvent e, Division division, String divisionName, double mainFee, double arFee)
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
        if(!db.updateDivision(division))
            displayError(e);
        if(Debug.debugMode)
            db.printDatabase();
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
     */
    public void updateGame(ActionEvent e, Game game, Club home, Club away, Division division, int round, int year,
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
        if(!db.updateGame(game))
            displayError(e);
        if(Debug.debugMode)
            db.printDatabase();
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
    public void displayError(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("All fields are required!\n" +
                "Need Division Name!\n" +
                "Need main referee fee!\n" +
                "Need assistant referee fee!");
    }
}
