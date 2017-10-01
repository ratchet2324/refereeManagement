package nefra.referee;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class Referee {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private double weeklyFee;
    private double totalFee;

    public Referee(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Referee(String firstName, String lastName, String contact, boolean isEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(isEmail) { this.email = contact; }
        else { this.phone = contact; }
    }

    public Referee(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    DecimalFormat df = new DecimalFormat("0.00");

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public String getName () { return getFirstName() + " " + getLastName(); }

    public double getWeeklyFee() { return weeklyFee; }

    public double getTotalFee() { return totalFee; }

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
