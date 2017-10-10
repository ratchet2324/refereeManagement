package nefra.game;

import nefra.club.Club;
import nefra.referee.Referee;

import java.util.ArrayList;

/**
 * Game holds all the information necessary to "play" a game, it also works out the match fees payable to the referees
 * and adds it both to the clubs and to the referees fees.
 */
public class Game {
    public ArrayList<Game> gameList = new ArrayList<>();
    private int game_id;
    private Club home;
    private Club away;
    private Division division;
    private int round;
    private Referee main;
    private Referee ar1;
    private Referee ar2;
    private ArrayList<Referee> extra = new ArrayList<>();
    private double tenPercentFee;

    /**
     * This constructor is called when the games are loaded from the database.
     *
     * @param game_id  the ID assigned by the database
     * @param home     The home club
     * @param away     The away club
     * @param division The division the game is in
     * @param round    The round the game is played in
     * @param main     The main referee (centre referee)
     * @param ar1      The 1st Assistant Referee
     * @param ar2      The 2nd Assistant Referee
     */
    public Game(int game_id, Club home, Club away, Division division, int round, Referee main, Referee ar1, Referee ar2) {
        this.game_id = game_id;
        this.home = home;
        this.away = away;
        this.division = division;
        this.round = round;
        this.main = main;
        this.ar1 = ar1;
        this.ar2 = ar2;
        gameList.add(this);
    }

    /**
     * This constructor is called when the games are entered via the GUI
     * @param home The home club
     * @param away The away club
     * @param division The division the game is in
     * @param round The round the game is played in
     * @param main The main referee (centre referee)
     * @param ar1 The 1st Assistant Referee
     * @param ar2 The 2nd Assistant Referee
     */
    public Game(Club home, Club away, Division division, int round, Referee main, Referee ar1, Referee ar2) {
        this.home = home;
        this.away = away;
        this.division = division;
        this.round = round;
        this.main = main;
        this.ar1 = ar1;
        this.ar2 = ar2;
        gameList.add(this);
    }

    public int getGameId() { return game_id; }

    public Club getHome() { return home; }

    public Club getAway() { return away; }

    public Division getDivision() {
        return division;
    }

    public Referee getMain() { return main; }

    public Referee getAr1() { return ar1; }

    public Referee getAr2() { return ar2; }

    public int getRound() { return round; }

    public ArrayList<Referee> getExtra() { return extra; }

    public void setGame_id(int game_id) { this.game_id = game_id; }

    public void setHome(Club home) { this.home = home; }

    public void setAway(Club away) { this.away = away; }

    public void setDivision(Division division) {
        this.division = division;
    }

    private void setRound(int round) { this.round = round; }

    public void setMain(Referee main) { this.main = main; }

    public void setAr1(Referee ar1) { this.ar1 = ar1; }

    public void setAr2(Referee ar2) { this.ar2 = ar2; }

    public void setExtra(ArrayList<Referee> extra) {
        this.extra = extra;
    }

    /**
     * Make the game fees ready to add to the referees afterwards. This function adds the required fees to the clubs
     * automatically. It also calculates the 10% required.
     * @param replacementMainReferee True if the centre referee was replaced (i.e. injury)
     * @return Returns the amount of the game fees.
     */
    public double makeGameFees(boolean replacementMainReferee) {
        double gameFees;
        double main = this.division.getMainRefereeFee();
        double ar = this.division.getArFee();
        double extraReferees = this.extra.size() * this.division.getArFee();

        if (replacementMainReferee)
            gameFees = (2 * main) + (2 * ar) + extraReferees;
        else
            gameFees = main + (2 * ar) + extraReferees;

        this.tenPercentFee = gameFees * 0.10;

        home.addToWeeklyFee(gameFees / 2);
        away.addToWeeklyFee(gameFees / 2);

        return gameFees;
    }

    /**
     * Pays the referees for the game (it sets it into the db)
     *
     * @param replacementMainReferee True if the centre referee was replaced (i.e. injury)
     * @param replacement            The replacement referee, so they can be paid. Only used if previous is true
     */
    public void payReferee(boolean replacementMainReferee, Referee replacement) {
        this.main.addToWeeklyFee(this.division.getMainRefereeFee());
        this.ar1.addToWeeklyFee(this.division.getArFee());
        this.ar2.addToWeeklyFee(this.division.getArFee());

        if(this.extra.size() > 0) {
            for(int i = 0; i < this.extra.size(); i ++)
                this.extra.get(i).addToWeeklyFee(this.division.getArFee());
        }

        if(replacementMainReferee) { replacement.addToWeeklyFee(this.division.getMainRefereeFee()); }
    }

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
