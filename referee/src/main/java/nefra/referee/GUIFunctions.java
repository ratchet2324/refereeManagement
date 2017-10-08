package nefra.referee;

import javafx.event.ActionEvent;
import nefra.db.DBFunctions;

public class GUIFunctions {
    private DBFunctions db = new DBFunctions();

    public void makeReferee(ActionEvent e, String firstName, String lastName)
    {
        e.consume();
        Referee referee = new Referee(firstName, lastName);
        db.insertReferee(firstName, lastName, "null", "null", referee.getWeeklyFee(), referee.getTotalFee());
    }
    public void makeReferee(ActionEvent e, String firstName, String lastName, String contact, boolean isEmail)
    {
        e.consume();
        Referee referee = new Referee(firstName, lastName, contact, isEmail);
        if (isEmail)
            db.insertReferee(firstName, lastName, contact, "null", referee.getWeeklyFee(), referee.getTotalFee());
        else
            db.insertReferee(firstName, lastName, "null", contact, referee.getWeeklyFee(), referee.getTotalFee());
    }
    public void makeReferee(ActionEvent e, String firstName, String lastName, String email, String phone)
    {
        e.consume();
        Referee referee = new Referee(firstName, lastName, email, phone);
        db.insertReferee(firstName, lastName, email, phone, referee.getWeeklyFee(), referee.getTotalFee());
    }
}
