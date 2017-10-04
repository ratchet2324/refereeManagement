package nefra.game;

import nefra.club.Club;
import nefra.referee.Referee;

import java.util.ArrayList;

public class Game {
    private Club home;
    private Club away;
    private Division division;
    private int round;
    private Referee main;
    private Referee ar1;
    private Referee ar2;
    private ArrayList<Referee> extra = new ArrayList<>();

    public Game(Club home, Club away, Division division, int round, Referee main, Referee ar1, Referee ar2) {
        this.home = home;
        this.away = away;
        this.division = division;
        this.round = round;
        this.main = main;
        this.ar1 = ar1;
        this.ar2 = ar2;
    }

    public Club getHome() { return home; }

    public Club getAway() { return away; }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Referee getMain() { return main; }

    public Referee getAr1() { return ar1; }

    public Referee getAr2() { return ar2; }

    public ArrayList<Referee> getExtra() { return extra; }

    public void setHome(Club home) { this.home = home; }

    public void setAway(Club away) { this.away = away; }

    public int getRound() {
        return round;
    }

    private void setRound(int round) {
        this.round = round;
    }

    public void setMain(Referee main) { this.main = main; }

    public void setAr1(Referee ar1) { this.ar1 = ar1; }

    public void setAr2(Referee ar2) { this.ar2 = ar2; }

    public void setExtra(ArrayList<Referee> extra) { this.extra = extra; }

    @Override
    public String toString() {
        return "Home: " + getHome().getClubName() + "\n" +
                "Away: " + getAway().getClubName() + "\n" +
                "Division: " + getDivision().getDivisionName() + "\n" +
                "Round: " + getRound() + "\n" +
                "Main Referee: " + getMain().getName() + "\n" +
                "Assistant Referee 1: " + getAr1().getName() + "\n" +
                "Assistant Referee 2: " + getAr2().getName();
    }
}
