package nefra.referee;

import javafx.event.ActionEvent;
import nefra.db.DBFunctions;

/**
 * The functions for the Referee GUIs (Create, Edit, View)
 */
public class GUIFunctions {
    private DBFunctions db = new DBFunctions();

    /**
     * Used if only the firstName and lastName is entered.
     *
     * @param e         the event passed through
     * @param firstName First Name of the referee
     * @param lastName  Last Name of the Referee
     */
    public void makeReferee(ActionEvent e, String firstName, String lastName) {
        e.consume();
        Referee referee = new Referee(firstName, lastName);
        db.insertReferee(firstName, lastName, "null", "null", referee.getWeeklyFee(), referee.getTotalFee());
    }

    /**
     * Used if firstName, lastName and only 1 contact is entered.
     * @param e the event passed through
     * @param firstName First Name of the referee
     * @param lastName Last Name of the Referee
     * @param contact Contact information, EITHER email OR phone
     * @param isEmail If true, then email is entered, else, phone is entered.
     */
    public void makeReferee(ActionEvent e, String firstName, String lastName, String contact, boolean isEmail) {
        e.consume();
        Referee referee = new Referee(firstName, lastName, contact, isEmail);
        if (isEmail)
            db.insertReferee(firstName, lastName, contact, "null", referee.getWeeklyFee(), referee.getTotalFee());
        else
            db.insertReferee(firstName, lastName, "null", contact, referee.getWeeklyFee(), referee.getTotalFee());
    }

    /**
     * Used if firstName, lastName and both email and phone is entered.
     * @param e the event passed through
     * @param firstName First Name of the referee
     * @param lastName Last Name of the Referee
     * @param email Email of the referee
     * @param phone Phone number of the referee
     */
    public void makeReferee(ActionEvent e, String firstName, String lastName, String email, String phone) {
        e.consume();
        Referee referee = new Referee(firstName, lastName, email, phone);
        db.insertReferee(firstName, lastName, email, phone, referee.getWeeklyFee(), referee.getTotalFee());
    }
}