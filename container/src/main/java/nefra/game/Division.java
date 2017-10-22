package nefra.game;

import java.util.ArrayList;

public class Division {
    public static ArrayList<Division> divisionList = new ArrayList<>();
    private int division_id;
    private String divisionName;
    private double mainRefereeFee;
    private double arFee;

    /**
     * Full constructor for when the Division is loaded from the database.
     *
     * @param division_id    The ID from the database
     * @param divisionName   The name of the division
     * @param mainRefereeFee The fee to be paid to the main referee
     * @param arFee          The fee to be paid to the assistant referees.
     */
    public Division(int division_id, String divisionName, double mainRefereeFee, double arFee) {
        this.division_id = division_id;
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
        divisionList.add(this);
    }

    /**
     * Full constructor for when the Division is created from the GUI.
     * @param divisionName The name of the division
     * @param mainRefereeFee The fee to be paid to the main referee
     * @param arFee The fee to be paid to the assistant referees.
     */
    public Division(String divisionName, double mainRefereeFee, double arFee) {
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
        divisionList.add(this);
    }

    public ArrayList<Division> getDivisionList() { return divisionList; }

    public int getDivisionId() { return division_id; }

    public String getDivisionName() { return divisionName; }

    public double getMainRefereeFee() { return mainRefereeFee; }

    public double getArFee() { return arFee; }

    public void setDivision_id(int division_id) { this.division_id = division_id; }

    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    public void setMainRefereeFee(double mainRefereeFee) { this.mainRefereeFee = mainRefereeFee; }

    public void setArFee(double arFee) { this.arFee = arFee; }

    @Override
    public String toString() { return this.getDivisionName(); }

    public String displayInfo() {
        return String.format("Division Name: %s\nMain Referee Fee: $%.2f\nAssistant Referee Fee: $%.2f\n",
                getDivisionName(), getMainRefereeFee(), getArFee());
    }
}
