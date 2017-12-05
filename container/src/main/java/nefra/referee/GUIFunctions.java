package nefra.referee;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import nefra.db.DBFunctions;
import nefra.misc.Debug;

/**
 * The functions for the Referee GUIs (Create, Edit, View)
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class GUIFunctions {
    private DBFunctions db = new DBFunctions();

    /**
     * Used if firstName, lastName and both email and phone is entered.
     * @param e the event passed through.
     * @param firstName First Name of the referee.
     * @param lastName Last Name of the Referee.
     * @param email Email of the referee.
     * @param phone Phone number of the referee.
     * @since 1.0
     */
    public void makeReferee(ActionEvent e, String firstName, String lastName, String email, String phone) {
        e.consume();
        Referee referee = new Referee(firstName, lastName, email, phone);
        db.insertReferee(referee);
        if(Debug.debugMode)
            db.printDatabase();
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
     */
    public void updateReferee(ActionEvent e, Referee referee, String firstName, String lastName,
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
        if(!db.updateReferee(referee))
            displayError(e);
        if(Debug.debugMode)
            db.printDatabase();
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
    public void displayError(ActionEvent e)
    {
        e.consume();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Missing Information");
        error.setContentText("Minimum required information is:\n" +
                "First Name\n" +
                "Last Name");
    }
}
