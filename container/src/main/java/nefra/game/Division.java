package nefra.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * Holds all the information required to make a division: name and match official fees.
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class Division implements Serializable {
    public static final ObservableList<Division> divisionList = FXCollections.observableArrayList();
    private UUID division_id;
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
     * @since 1.0
     */
    public Division(UUID division_id, String divisionName, double mainRefereeFee, double arFee) {
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
     * @since 1.0
     */
    public Division(String divisionName, double mainRefereeFee, double arFee) {
        this.divisionName = divisionName;
        this.mainRefereeFee = mainRefereeFee;
        this.arFee = arFee;
        divisionList.add(this);
    }

    public UUID getDivisionId() { return division_id; }

    public String getDivisionName() { return divisionName; }

    public double getMainRefereeFee() { return mainRefereeFee; }

    public double getArFee() { return arFee; }

    public void setDivisionId(UUID division_id) { this.division_id = division_id; }

    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    public void setMainRefereeFee(double mainRefereeFee) { this.mainRefereeFee = mainRefereeFee; }

    public void setArFee(double arFee) { this.arFee = arFee; }

    void delete()
    {
        divisionList.remove(this);
        division_id = null;
        divisionName = null;
        mainRefereeFee = arFee = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Division division = (Division) o;

        return division_id == division.division_id &&
                Double.compare(mainRefereeFee, division.mainRefereeFee) == 0 &&
                Double.compare(arFee, division.arFee) == 0 &&
                divisionName.equals(division.divisionName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        Random r = new Random();
        result = r.nextInt();
        result = 31 * result + divisionName.hashCode();
        temp = Double.doubleToLongBits(mainRefereeFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(arFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() { return this.getDivisionName(); }

    public void displayInfo() {
        System.out.printf("Division ID: %s\nDivision Name: %s\nMain Referee Fee: $%.2f\nAssistant Referee Fee: $%.2f\n",
                getDivisionId(), getDivisionName(), getMainRefereeFee(), getArFee());
    }
}
