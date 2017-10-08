package nefra.referee;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Referee {
    public ArrayList<Referee> refereeList = new ArrayList<>();
    private int referee_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private double weeklyFee;
    private double totalFee;

    /**
     *
     * @param referee_id The id assigned by the database upon entry.
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     * @param email The referee's email
     * @param phone The referee's phone number
     */
    public Referee(int referee_id, String firstName, String lastName, String email, String phone) {
        this.referee_id = referee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        refereeList.add(this);
    }

    /**
     * @param referee_id The id assigned by the database upon entry.
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     * @param contact The contact info for the referee (either email OR phone, NOT both)
     * @param isEmail Whether the contact info entered is the email; true = email is entered; false = phone is entered.
     */
    public Referee(int referee_id, String firstName, String lastName, String contact, boolean isEmail) {
        this.referee_id = referee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        if(isEmail) { this.email = contact; }
        else { this.phone = contact; }
        refereeList.add(this);
    }

    /**
     *
     * @param referee_id The id assigned by the database upon entry.
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     */
    public Referee(int referee_id, String firstName, String lastName)
    {
        this.referee_id = referee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        refereeList.add(this);
    }

    /**
     *
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     * @param email The referee's email
     * @param phone The referee's phone number
     */
    public Referee(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        refereeList.add(this);
    }

    /**
     *
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     * @param contact The contact info for the referee (either email OR phone, NOT both)
     * @param isEmail Whether the contact info entered is the email; true = email is entered; false = phone is entered.
     */
    public Referee(String firstName, String lastName, String contact, boolean isEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(isEmail) { this.email = contact; }
        else { this.phone = contact; }
        refereeList.add(this);
    }

    /**
     *
     * @param firstName The referee's first name
     * @param lastName The referee's last name
     */
    public Referee(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        refereeList.add(this);
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    public int getRefereeId() { return referee_id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public String getName () { return getFirstName() + " " + getLastName(); }

    public double getWeeklyFee() { return weeklyFee; }

    public double getTotalFee() { return totalFee; }

    public void setReferee_id(int referee_id) { this.referee_id = referee_id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void addToWeeklyFee(double amount) { this.weeklyFee += amount; }

    public void addToTotalFee(double amount) { this.totalFee += amount; }

    public void resetWeeklyFee() { addToTotalFee(weeklyFee); weeklyFee = 0; }

    public void resetTotalFee() { totalFee = 0; }

    @Override
    public String toString() {
        if(StringUtils.isNotEmpty(getPhone()) && StringUtils.isNotEmpty(getEmail()))
        {
            return "Name: " + getName() +
                    "\nPhone: " + getPhone() +
                    "\nEmail: " + getEmail() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        }
        else if(StringUtils.isNotEmpty(getEmail()))
        {
            return "Name: " + getName() +
                    "\nEmail: " + getEmail() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        }
        else if (StringUtils.isNotEmpty(getPhone()))
        {
            return "Name: " + getName() +
                    "\nPhone: " + getPhone() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        }
        else
        {
            return "Name: " + getName() +
                    "\nCurrent week's fees: " + df.format(getWeeklyFee()) +
                    "\nCurrent year to date fees: " + df.format(getTotalFee()) + "\n";
        }
    }
}
