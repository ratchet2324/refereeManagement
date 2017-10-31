package nefra.jfx.game;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import nefra.club.Club;
import nefra.game.Division;
import nefra.game.GUIFunctions;
import nefra.jfx.CommonGUI;
import nefra.referee.Referee;

import java.util.Calendar;

public class ViewGameGUI {
    private GUIFunctions guif = new GUIFunctions();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the back button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        /*
        this.home = home;
        this.away = away;
        this.division = division;
        this.round = round;
        this.main = main;
        this.ar1 = ar1;
        this.ar2 = ar2;
         */

        //Centre
        GridPane centre = new GridPane();
        Label homeTeamLabel = new Label("Home: ");
        Label awayTeamLabel = new Label("Away: ");
        Label divisionLabel = new Label("Division: ");
        Label roundLabel = new Label("Round: ");
        Label yearLabel = new Label("Year: ");
        Label mainRefereeLabel = new Label("Main Referee: ");
        Label ar1RefereeLabel = new Label("AR 1: ");
        Label ar2RefereeLabel = new Label("AR 2: ");
        Label createGameLabel = new Label("CREATE GAME");
        ChoiceBox homeTeam = new ChoiceBox(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox awayTeam = new ChoiceBox(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox division = new ChoiceBox(FXCollections.observableArrayList(Division.divisionList));
        TextField round = new TextField();
        TextField year = new TextField(String.format("%d", Calendar.getInstance().get(Calendar.YEAR)));
        ChoiceBox mainReferee = new ChoiceBox(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox ar1Referee = new ChoiceBox(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox ar2Referee = new ChoiceBox(FXCollections.observableArrayList(Referee.refereeList));
        Button enterButton = new Button("Enter");

        //Set size to fill gaps
        homeTeam.setMinSize(300,0);
        awayTeam.setMinSize(300,0);
        division.setMinSize(300,0);
        mainReferee.setMinSize(300,0);
        ar1Referee.setMinSize(300,0);
        ar2Referee.setMinSize(300,0);
        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            System.out.println("HT: "+ homeTeam.getValue());
            System.out.println("AT: "+ awayTeam.getValue());
            System.out.println("DI: "+ division.getValue());
            System.out.println("RO: "+ round.getText());
            System.out.println("MR: "+ mainReferee.getValue());
            System.out.println("AR: "+ ar1Referee.getValue());
            System.out.println("AR: "+ ar2Referee.getValue());
            guif.makeGame(e, (Club) homeTeam.getValue(), (Club) awayTeam.getValue(), (Division) division.getValue(),
                    Integer.valueOf(round.getText()), Integer.valueOf(year.getText()), (Referee) mainReferee.getValue(),
                    (Referee)ar1Referee.getValue(), (Referee) ar2Referee.getValue());
        });

        homeTeamLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        awayTeamLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        divisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        roundLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        yearLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        mainRefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        ar1RefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        ar2RefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(homeTeamLabel, HPos.CENTER);
        GridPane.setHalignment(awayTeamLabel, HPos.CENTER);
        GridPane.setHalignment(divisionLabel, HPos.CENTER);
        GridPane.setHalignment(roundLabel, HPos.CENTER);
        GridPane.setHalignment(yearLabel, HPos.CENTER);
        GridPane.setHalignment(mainRefereeLabel, HPos.CENTER);
        GridPane.setHalignment(ar1RefereeLabel, HPos.CENTER);
        GridPane.setHalignment(ar2RefereeLabel, HPos.CENTER);
        GridPane.setHalignment(createGameLabel, HPos.CENTER);
        GridPane.setValignment(createGameLabel, VPos.CENTER);

        createGameLabel.setTextAlignment(TextAlignment.CENTER);
        createGameLabel.setAlignment(Pos.CENTER);
        createGameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(createGameLabel, 5, 1, 4, 2);
        GridPane.setConstraints(homeTeamLabel, 3, 3);
        GridPane.setConstraints(awayTeamLabel,7 , 3);
        GridPane.setConstraints(divisionLabel, 3, 4);
        GridPane.setConstraints(roundLabel, 7, 4);
        GridPane.setConstraints(yearLabel, 9, 4);
        GridPane.setConstraints(mainRefereeLabel,4 , 6, 2, 1);
        GridPane.setConstraints(ar1RefereeLabel, 3, 7);
        GridPane.setConstraints(ar2RefereeLabel, 7, 7);
        GridPane.setConstraints(homeTeam, 4, 3, 3, 1);
        GridPane.setConstraints(awayTeam, 8, 3, 3, 1);
        GridPane.setConstraints(division, 4, 4, 3, 1);
        GridPane.setConstraints(round, 8, 4);
        GridPane.setConstraints(year, 10, 4);
        GridPane.setConstraints(mainReferee, 6, 6, 3, 1);
        GridPane.setConstraints(ar1Referee, 4, 7, 3, 1);
        GridPane.setConstraints(ar2Referee, 8, 7, 3, 1);
        GridPane.setConstraints(enterButton, 9, 9);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(homeTeamLabel, awayTeamLabel, divisionLabel, roundLabel,
                yearLabel, mainRefereeLabel, ar1RefereeLabel, ar2RefereeLabel,
                homeTeam, awayTeam, division, round, year, mainReferee,
                ar1Referee, ar2Referee, createGameLabel, enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane games = new BorderPane(centre, menu, null, backButton, null);
        games.setPrefSize(640,480);

        CommonGUI.panes.add(games);

        return games;
    }
}
