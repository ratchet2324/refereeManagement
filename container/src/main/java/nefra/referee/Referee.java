package nefra.referee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Referee class to hold all the information required of a
 * referee including contact information and the amount they are due
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class Referee implements Serializable {
    public static final ObservableList<Referee> refereeList = FXCollections.observableArrayList();
    private UUID referee_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private double weeklyFee;
    private double totalFee;

    /**
     * THIS IS ONLY USED WHEN LOADING FROM THE DATABASE
     * @param referee_id The id assigned by the database upon entry.
     * @param firstName The referee's first name.
     * @param lastName The referee's last name.
     * @param email The referee's email.
     * @param phone The referee's phone number.
     * @param totalFee The total fee over the year to be paid to the referee
     * @param weeklyFee The total current weeks fee to be paid to the referee
     * @since 1.0
     */
    public Referee(UUID referee_id, String firstName, String lastName,
                   String email, String phone, double weeklyFee, double totalFee) {
        this.referee_id = referee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.weeklyFee = weeklyFee;
        this.totalFee = totalFee;
        refereeList.add(this);
    }

    /**
     * THIS IS USED WHEN CREATING FROM THE GUI
     * @param firstName The referee's first name.
     * @param lastName The referee's last name.
     * @param email The referee's email.
     * @param phone The referee's phone number.
     * @since 1.0
     */
    public Referee(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        refereeList.add(this);
    }

    /**
     * Used for the dollar amount for the fees.
     * @since 1.0
     */
    private final DecimalFormat df = new DecimalFormat("0.00");

    public UUID getRefereeId() { return referee_id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public String getName () { return getFirstName() + " " + getLastName(); }

    public double getWeeklyFee() { return weeklyFee; }

    public double getTotalFee() { return totalFee; }

    public void setReferee_id(UUID referee_id) { this.referee_id = referee_id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void addToWeeklyFee(double amount) { this.weeklyFee += amount; }

    public void addToTotalFee(double amount) { this.totalFee += amount; }


    /**
     * Resets the total fee back to $0.00 and adds it to the total fees for the season.
     * @since 1.0
     */
    public void resetWeeklyFee() { addToTotalFee(weeklyFee); weeklyFee = 0; }

    /**
     * Resets the total fee back to $0.00, to coincide with the end of the season.
     * @since 1.0
     */
    public void resetTotalFee() { totalFee = 0; }

    void delete()
    {
        refereeList.remove(this);
        referee_id = null;
        firstName =  lastName =  email =  phone = null;
        weeklyFee = totalFee = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Referee referee = (Referee) o;

        return referee_id == referee.referee_id &&
                firstName.equals(referee.firstName) &&
                lastName.equals(referee.lastName);
    }

    @Override
    public int hashCode() {
        Random r = new Random();
        int result = r.nextInt();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() { return this.getName(); }

    /**
     * Depending on the information entered, it adjusts the output accordingly. So if only the first and last name are
     * entered, then only those are displayed.
     *
     * @return the string that is printed out.
     * @since 1.0
     */
    public String displayInfo() {
        if(StringUtils.isNotEmpty(getPhone()) && StringUtils.isNotEmpty(getEmail())) {
            return "Name: " + getName() +
                    "\nPhone: " + getPhone() +
                    "\nEmail: " + getEmail() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        } else if(StringUtils.isNotEmpty(getEmail())) {
            return "Name: " + getName() +
                    "\nEmail: " + getEmail() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        } else if (StringUtils.isNotEmpty(getPhone())) {
            return "Name: " + getName() +
                    "\nPhone: " + getPhone() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        } else {
            return "Name: " + getName() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        }
    }
}
