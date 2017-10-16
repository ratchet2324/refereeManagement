package nefra.game;

import nefra.club.Club;
import nefra.referee.Referee;

import java.util.ArrayList;

/**
 * Game holds all the information necessary to "play" a game, it also works out the match fees payable to the referees
 * and adds it both to the clubs and to the referees fees.
 */
public class Game {
    public static ArrayList<Game> gameList = new ArrayList<>();
    private int game_id;
    private Club home;
    private Club away;
    private Division division;
    private int round;
    private int year;
    private Referee main;
    private Referee ar1;
    private Referee ar2;
    private double totalFee;
    private double homeClubFee;
    private double awayClubFee;
    private double adminFee;
    private ArrayList<Referee> extra = new ArrayList<>();

    /**
     * This constructor is called when the games are loaded from the database.
     *
     * @param game_id  the ID assigned by the database.
     * @param home     The home club.
     * @param away     The away club.
     * @param division The division the game is in.
     * @param round    The round the game is played in.
     * @param year     The year the game takes place (defaults to current year)
     * @param main     The main referee (centre referee).
     * @param ar1      The 1st Assistant Referee.
     * @param ar2      The 2nd Assistant Referee.
     * @param totalFee The total match fee to be paid by clubs for referees.
     * @param homeClubFee      The amount payable by the Home Club.
     * @param awayClubFee      The amount payable by the Away Club.
     * @param adminFee 10% of the fee is for administration.
     */
    public Game(int game_id, Club home, Club away,
                Division division, int round, int year,
                Referee main, Referee ar1, Referee ar2,
                double totalFee, double homeClubFee, double awayClubFee,
                double adminFee) {
        this.game_id = game_id;
        this.home = home;
        this.away = away;
        this.division = division;
        this.round = round;
        this.year = year;
        this.main = main;
        this.ar1 = ar1;
        this.ar2 = ar2;
        this.totalFee = totalFee;
        this.homeClubFee = homeClubFee;
        this.awayClubFee = awayClubFee;
        this.adminFee = adminFee;
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

    public ArrayList<Game> getGameList() { return gameList; }

    public int getGameId() { return game_id; }

    public Club getHome() { return home; }

    public Club getAway() { return away; }

    public Division getDivision() {
        return division;
    }

    public int getRound() { return round; }

    public Referee getMain() { return main; }

    public Referee getAr1() { return ar1; }

    public Referee getAr2() { return ar2; }

    public int getYear() { return year; }

    public double getTotalFee() { return totalFee; }

    public double getHomeClubFee() { return homeClubFee; }

    public double getAwayClubFee() { return awayClubFee; }

    public double getAdminFee() { return adminFee; }

    public ArrayList<Referee> getExtra() { return extra; }

    public void setGameId(int game_id) { this.game_id = game_id; }

    public void setHome(Club home) { this.home = home; }

    public void setAway(Club away) { this.away = away; }

    public void setDivision(Division division) { this.division = division; }

    private void setRound(int round) { this.round = round; }

    public void setYear(int year) { this.year = year; }

    public void setMain(Referee main) { this.main = main; }

    public void setAr1(Referee ar1) { this.ar1 = ar1; }

    public void setAr2(Referee ar2) { this.ar2 = ar2; }

    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    public void setHomeClubFee(double homeClubFee) { this.homeClubFee = homeClubFee; }

    public void setAwayClubFee(double awayClubFee) { this.awayClubFee = awayClubFee; }

    public void setAdminFee(double adminFee) { this.adminFee = adminFee; }

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

        this.adminFee = gameFees * 0.10;

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

        if(this.extra.size() > 0)
            for (Referee ref : extra) { ref.addToWeeklyFee(this.division.getArFee()); }

        if(replacementMainReferee) { replacement.addToWeeklyFee(this.division.getMainRefereeFee()); }
    }

    @Override
    public String toString() {
        return "Division: " + getDivision().getDivisionName() + " " +
                "Round: " + getRound() + "\n" +
                getHome().getClubName() + " vs " + getAway().getClubName() + "\n" +
                "Main Referee: " + getMain().getName() + "\n" +
                "Assistant Referee 1: " + getAr1().getName() + "\n" +
                "Assistant Referee 2: " + getAr2().getName();
    }
}