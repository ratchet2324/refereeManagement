package nefra.club;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class to create Clubs, able to be extended and/or modified for other sports.
 */
public class Club {
    public static ArrayList<Club> clubList = new ArrayList<Club>();
    private int club_id;
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
     */
    public Club(int club_id, String clubName, String street, String suburb, String state, String postcode,
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
     * Full constructor for when all information is provided
     *
     * @param club_id          ID number from database
     * @param clubName         Name of the Club
     * @param street           The street address, also includes street number
     * @param suburb           The suburb the club is located in
     * @param state            The state the club is in
     * @param postcode         The postcode (zip code) for the suburb
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     */
    public Club(int club_id, String clubName, String street, String suburb, String state, String postcode, String presidentName, String presidentContact) {
        this.club_id = club_id;
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
        clubList.add(this);
    }

    /**
     * Full constructor for when only address information is provided
     *
     * @param club_id  ID number from database
     * @param clubName Name of the Club
     * @param street   The street address, also includes street number
     * @param suburb   The suburb the club is located in
     * @param state    The state the club is in
     * @param postcode The postcode (zip code) for the suburb
     */
    public Club(int club_id, String clubName, String street, String suburb, String state, String postcode) {
        this.club_id = club_id;
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        clubList.add(this);
    }

    /**
     * Full constructor for when only president information is provided
     *
     * @param club_id          ID number from database
     * @param clubName         Name of the Club
     * @param presidentName    The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     */
    public Club(int club_id, String clubName, String presidentName, String presidentContact) {
        this.club_id = club_id;
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
        clubList.add(this);
    }

    /**
     * Full constructor for when only club name is provided
     *
     * @param club_id  ID number from database
     * @param clubName Name of the Club
     */
    public Club(int club_id, String clubName) {
        this.club_id = club_id;
        this.clubName = clubName;
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

    /**
     * Creation of a club where only an address of the club is provided
     * @param clubName Name of the Club
     * @param street The street address, also includes street number
     * @param suburb The suburb the club is located in
     * @param state The state the club is in
     * @param postcode The postcode (zip code) for the suburb
     */
    public Club(String clubName, String street, String suburb, String state, String postcode) {
        this.clubName = clubName;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
        clubList.add(this);
    }

    /**
     * Creation of a club where only the president details are provided
     * @param clubName Name of the Club
     * @param presidentName The name of the president/chairman of the club
     * @param presidentContact Contact info for the president/chairman, can be phone or email
     */
    public Club(String clubName, String presidentName, String presidentContact) {
        this.clubName = clubName;
        this.presidentName = presidentName;
        this.presidentContact = presidentContact;
        clubList.add(this);
    }

    /**
     * Blank Club creation where only a name is given
     * @param clubName Name of the Club
     */
    public Club(String clubName) { this.clubName = clubName;
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    public ArrayList<Club> getClubList() { return clubList; }

    public int getClubId() {
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

    public void setClub_id(int club_id) { this.club_id = club_id; }

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
     */
    public void resetWeeklyFee() { addToTotalFee(weeklyFee);
        weeklyFee = 0;
    }

    /**
     * Resets the total fee back to $0.00, to coincide with the end of the season.
     */
    public void resetTotalFee() {
        totalFee = 0;
    }

    @Override
    public String toString() {
        return this.getClubName();
    }

    /**
     * Depending on the information entered, it adjusts the output accordingly. So if only the club name are
     * entered, then only that is displayed.
     * @return the string that is printed out.
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
