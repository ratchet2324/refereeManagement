package nefra.game;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.club.Club;
import nefra.db.dbf_rewrite;
import nefra.referee.Referee;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * The functions for the Game and Division GUIs (Create, Edit, View)
 */
public class GUIFunctions {
    private dbf_rewrite db = new dbf_rewrite();
    private DecimalFormat df = new DecimalFormat("0.00");

    public void makeDivision(ActionEvent e, String divisionName, String mainRefFee, String arFee)
    {
        e.consume();
        try {
            double main = df.parse(mainRefFee).doubleValue();
            double ar = df.parse(arFee).doubleValue();
            df.format(main);
            df.format(ar);

            System.out.println("Main: " + main);
            System.out.println("AR: " + ar);
            Division division = new Division(divisionName, main, ar);
            db.insertDivision(division);
        } catch (ParseException pe) { pe.printStackTrace(); }
    }

    public void makeGame(ActionEvent e, Club home, Club away, Division division, int round, int year, Referee main,
                         Referee ar1, Referee ar2)
    {
        e.consume();
        Game game = new Game(home, away, division, round, main, ar1, ar2);
        db.insertGame(game);
    }

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
