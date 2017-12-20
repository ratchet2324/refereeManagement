package nefra.club;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Class to create Clubs, able to be extended and/or modified for other sports.
 */
public class Club implements Serializable {
    public static final ObservableList<Club> clubList = FXCollections.observableArrayList();
    private UUID club_id;
    private String clubName;
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private String presidentName;
    private String presidentContact;
    private double totalFee;
    private double weeklyFee;

    /**
     * USED ONLY WHEN THE DATA IS LOADED FROM THE DATABASE
     *
     * @param club_id          ID number from database
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param state            The state the club is in
     * @param postcode         The postcode (zip code) for the suburb
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     * @param totalFee The total fee over the year to be paid by the club for admin and match officials
     * @param weeklyFee The total current weeks fee to be paid by the club for match officials and admin
     * @since 1.0
     */
    public Club(UUID club_id, String clubName, String street, String suburb, String state, String postcode,
                String presidentName, String presidentContact, double weeklyFee, double totalFee) {
        this.club_id = club_id;
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
        this.weeklyFee = weeklyFee;
        this.totalFee = totalFee;
        clubList.add(this);
    }

    /**
     * Creation of a club upon entering all fields on the GUI, no club number as it has not been assigned by the database
     *
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param state            The state the club is in
     * @param postcode         The postcode (zip code) for the suburb
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     * @since 1.0
     */
    public Club(String clubName, String street, String suburb, String state, String postcode, String presidentName, String presidentContact) {
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
        clubList.add(this);
    }

    private final DecimalFormat df = new DecimalFormat("0.00");

    public UUID getClubId() {
        return club_id;
    }

    public String getClubName() {
        return clubName;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() { return suburb; }

    public String getPostcode() { return postcode; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getPresidentName() { return presidentName; }

    public String getPresidentContact() { return presidentContact; }

    public double getTotalFee() { return totalFee; }

    public double getWeeklyFee() { return weeklyFee; }

    public void setClub_id(UUID club_id) { this.club_id = club_id; }

    public void setClubName(String clubName) { this.clubName = clubName; }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode; }

    public String getAddress() { return this.getStreet() + "\n" +
            this.getSuburb() + " " + this.getState() + " " + this.getPostcode(); }

    public void setPresidentName(String presidentName) { this.presidentName = presidentName; }

    public void setPresidentContact(String presidentContact) { this.presidentContact = presidentContact;
    }

    public void addToTotalFee(double amount) {
        totalFee += amount; }

    public void addToWeeklyFee(double amount) { weeklyFee += amount;
    }

    /**
     * Resets the weekly fee back to $0.00 and adds it to the total fee.
     * @since 1.0
     */
    public void resetWeeklyFee() { addToTotalFee(weeklyFee);
        weeklyFee = 0;
    }

    /**
     * Resets the total fee back to $0.00, to coincide with the end of the season.
     * @since 1.0
     */
    public void resetTotalFee() {
        totalFee = 0;
    }

    void delete()
    {
        clubList.remove(this);
        club_id = null;
        clubName = street = suburb = state = postcode = presidentName = presidentContact = null;
        weeklyFee = totalFee = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        return club_id == club.club_id &&
                clubName.equals(club.clubName);
    }

    @Override
    public int hashCode() {
        Random r = new Random();
        int result = r.nextInt();
        result = 31 * result + clubName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getClubName();
    }

    /**
     * Depending on the information entered, it adjusts the output accordingly. So if only the club name are
     * entered, then only that is displayed.
     * @return the string that is printed out.
     * @since 1.0
     */
    public String displayInfo() {

        if (
                StringUtils.isNotEmpty(getPresidentName()) &&
                        StringUtils.isNotEmpty(getPresidentContact()) &&
                        StringUtils.isNotEmpty(getAddress())) {
            return "Name: " + getClubName() + "\n" +
                    "President: " + getPresidentName() + "\n" +
                    "President Contact: " + getPresidentContact() + "\n" +
                    "Address: " + getAddress() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        } else if (
                StringUtils.isNotEmpty(getAddress())) {
            return "Name: " + getClubName() + "\n" +
                    "Address: " + getAddress() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        } else if(StringUtils.isNotEmpty(getPresidentName()) &&
                StringUtils.isNotEmpty(getPresidentContact())) {
            return "Name: " + getClubName() + "\n" +
                    "President: " + getPresidentName() + "\n" +
                    "President Contact: " + getPresidentContact() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        } else {
            return "Name: " + getClubName() + "\n" +
                    "Current week's fees: " + df.format(getWeeklyFee()) + "\n" +
                    "Current year to date fees: " + df.format(getTotalFee());
        }
    }
}
