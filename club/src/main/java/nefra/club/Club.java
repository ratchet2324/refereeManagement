package nefra.club;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

public class Club {
    private String clubName;
    private String street;
    private String suburb;
    private String postcode;
    private String presidentName;
    private String presidentContact;
    private double totalFee;
    private double weeklyFee;

    public Club(String clubName, String street, String suburb, String postcode, String presidentName, String presidentContact) {
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.postcode = postcode;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
    }

    public Club(String clubName, String street, String suburb, String postcode) {
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public Club(String clubName, String presidentName, String presidentContact) {
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
    }

    public Club(String clubName) { this.clubName = clubName; }

    DecimalFormat df = new DecimalFormat("0.00");

    public String getClubName() { return clubName; }

    public String getStreet() { return street; }

    public String getSuburb() { return suburb; }

    public String getPostcode() { return postcode; }

    public String getPresidentName() { return presidentName; }

    public String getPresidentContact() { return presidentContact; }

    public double getTotalFee() { return totalFee; }

    public double getWeeklyFee() { return weeklyFee; }

    public void setClubName(String clubName) { this.clubName = clubName; }

    public void setStreet(String street) { this.street = street; }

    public void setSuburb(String suburb) { this.suburb = suburb; }

    public void setPostcode(String postcode) { this.postcode = postcode; }

    public void setPresidentName(String presidentName) { this.presidentName = presidentName; }

    public void setPresidentContact(String presidentContact) { this.presidentContact = presidentContact; }

    public void addToTotalFee(double amount) { totalFee += amount; }
    public void addToWeeklyFee(double amount) { weeklyFee += amount; }
    public void resetWeeklyFee() { addToTotalFee(weeklyFee); weeklyFee = 0; }
    public void resetTotalFee() { totalFee = 0; }

    @Override
    public String toString() {

        if(
                StringUtils.isNotEmpty(getPresidentName()) &&
                StringUtils.isNotEmpty(getPresidentContact()) &&
                StringUtils.isNotEmpty(getStreet()) &&
                StringUtils.isNotEmpty(getSuburb()) &&
                StringUtils.isNotEmpty(getPostcode()))
        {
            return "Name: " + getClubName() + "\n" +
                    "President: " + getPresidentName() + "\n" +
                    "President Contact: " + getPresidentContact() + "\n" +
                    "Address: " + getStreet() + "\n" +
                    getSuburb() + ", " + getPostcode() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        }
        else if (
                StringUtils.isNotEmpty(getStreet()) &&
                StringUtils.isNotEmpty(getSuburb()) &&
                StringUtils.isNotEmpty(getPostcode()))
        {
            return "Name: " + getClubName() + "\n" +
                    "Address: " + getStreet() + "\n" +
                    getSuburb() + ", " + getPostcode() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        }
        else if(StringUtils.isNotEmpty(getPresidentName()) &&
                StringUtils.isNotEmpty(getPresidentContact()))
        {
            return "Name: " + getClubName() + "\n" +
                    "President: " + getPresidentName() + "\n" +
                    "President Contact: " + getPresidentContact() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        }
        else
        {
            return "Name: " + getClubName() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        }
    }
}
