package nefra.club;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.db.DBFunctions;
import nefra.db.dbf_rewrite;

/**
 * The functions for the Referee GUIs (Create, Edit, View)
 */
public class GUIFunctions {
    private dbf_rewrite db = new dbf_rewrite();

    /**
     * Used if only the club name is entered.
     *
     * @param e the event passed through
     * @param clubName Name of the Club
     */
    public void makeClub(ActionEvent e, String clubName) {
        e.consume();
        Club club = new Club(clubName);
        db.insertClub(club);
    }

    /**
     * Used if only presidential information is entered
     * @param clubName Name of the Club
     * @param presidentName The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     */
    public void makeClub(ActionEvent e, String clubName, String presidentName, String presidentContact)
    {
        e.consume();
        Club club = new Club(clubName, presidentName, presidentContact);
        db.insertClub(club);
    }

    /**
     * Used if only address information is entered.
     * @param clubName Name of the Club
     * @param street The street address, also includes street number
     * @param suburb The suburb the club is located in
     * @param state The state the club is in
     * @param postcode The postcode (zip code) for the suburb
     */
    public void makeClub(ActionEvent e, String clubName, String street, String suburb, String state, String postcode) {
        e.consume();
        Club club = new Club(clubName, street, suburb, state, postcode);
        db.insertClub(club);
    }

    /**
     * Used if all information is provided
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param postcode         The postcode (zip code) for the suburb
     * @param state            The state the club is in
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     */
    public void makeClub(ActionEvent e, String clubName, String street,
                            String suburb, String state, String postcode,
                            String presidentName, String presidentContact) {
        e.consume();
        Club club = new Club(clubName, street, suburb, state, postcode, presidentName, presidentContact);
        db.insertClub(club);
    }

    public void displayError(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("Minimum required information is the Club Name.");
    }
}
