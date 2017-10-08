package nefra.game;

import java.util.ArrayList;

public class Division {
    public ArrayList<Division> divisionList = new ArrayList<>();
    private int division_id;
    private String divisionName;
    private double mainRefereeFee;
    private double arFee;

    public Division(int division_id, String divisionName, double mainRefereeFee, double arFee)
    {
        this.division_id = division_id;
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
        divisionList.add(this);
    }

    public Division(String divisionName, double mainRefereeFee, double arFee)
    {
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
        divisionList.add(this);
    }

    public int getDivisionId() { return division_id; }

    public String getDivisionName() { return divisionName; }

    public double getMainRefereeFee() { return mainRefereeFee; }

    public double getArFee() { return arFee; }

    public void setDivision_id(int division_id) { this.division_id = division_id; }

    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    public void setMainRefereeFee(double mainRefereeFee) { this.mainRefereeFee = mainRefereeFee; }

    public void setArFee(double arFee) { this.arFee = arFee; }

    @Override
    public String toString() {
        return String.format("Division Name: %s\nMain Referee Fee: $%.2f\nAssistant Referee Fee: $%.2f\n",
                getDivisionName(), getMainRefereeFee(), getArFee());
    }
}
