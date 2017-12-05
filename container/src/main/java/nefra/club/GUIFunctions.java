package nefra.club;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.db.DBFunctions;
import nefra.misc.Debug;

import javax.swing.*;

/**
 * The functions for the Club GUIs (Create, Edit, View)
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class GUIFunctions {
    private DBFunctions db = new DBFunctions();

    /**
     * Create and insert the club into the database
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param postcode         The postcode (zip code) for the suburb
     * @param state            The state the club is in
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     * @since 1.0
     */
    public void makeClub(ActionEvent e, String clubName, String street,
                            String suburb, String state, String postcode,
                            String presidentName, String presidentContact) {
        e.consume();
        Club club = new Club(clubName, street, suburb, state, postcode, presidentName, presidentContact);
        db.insertClub(club);
        if(Debug.debugMode)
            db.printDatabase();
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
     */
    public void updateClub(ActionEvent e, Club club, String clubName, String street,
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
        if(!db.updateClub(club))
            displayError(e);
        if(Debug.debugMode)
            db.printDatabase();
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
    public void displayError(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("Minimum required information is the Club Name");
    }
}
