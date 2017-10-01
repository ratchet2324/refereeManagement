package nefra.game;

import java.util.ArrayList;

public class Division {
    private String divisionName;
    private double mainRefereeFee;
    private double arFee;

    public Division(String divisionName, double mainRefereeFee, double arFee)
    {
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
    }

    public String getDivisionName() { return divisionName; }

    public double getMainRefereeFee() { return mainRefereeFee; }

    public double getArFee() { return arFee; }

    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    public void setMainRefereeFee(double mainRefereeFee) { this.mainRefereeFee = mainRefereeFee; }

    public void setArFee(double arFee) { this.arFee = arFee; }

    @Override
    public String toString() {
        return String.format("Division Name: %s\nMain Referee Fee: $%.2f\nAssistant Referee Fee: $%.2f\n",
                getDivisionName(), getMainRefereeFee(), getArFee());
    }
}
