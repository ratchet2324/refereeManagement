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
import nefra.db.GUIFunctions;
import nefra.exceptions.CannotCreateException;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.jfx.CommonGUI;
import nefra.misc.Debug;
import nefra.referee.Referee;

import java.util.Calendar;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CreateGameGUI {
    private final GUIFunctions guif = new GUIFunctions();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the backToMainMenu button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        Label gameWarning = new Label("All fields are required");
        Label homeTeamLabel = new Label("Home: ");
        Label awayTeamLabel = new Label("Away: ");
        Label divisionLabel = new Label("Division: ");
        Label roundLabel = new Label("Round: ");
        Label yearLabel = new Label("Year: ");
        Label mainRefereeLabel = new Label("Main Referee: ");
        Label ar1RefereeLabel = new Label("AR 1: ");
        Label ar2RefereeLabel = new Label("AR 2: ");
        Label createGameLabel = new Label("CREATE GAME");
        ChoiceBox<Club> homeTeam = new ChoiceBox<>(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox<Club> awayTeam = new ChoiceBox<>(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox<Division> division = new ChoiceBox<>(FXCollections.observableArrayList(Division.divisionList));
        TextField round = new TextField();
        TextField year = new TextField(String.format("%d", Calendar.getInstance().get(Calendar.YEAR)));
        ChoiceBox<Referee> mainReferee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox<Referee> ar1Referee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox<Referee> ar2Referee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
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
        //TODO: CLEAR TEXT BOXES (DEFAULT)
        enterButton.setOnAction(e -> {
            if (Debug.debugMode) {
                DelLog.getInstance().Log("HT: " + homeTeam.getValue());
                DelLog.getInstance().Log("AT: " + awayTeam.getValue());
                DelLog.getInstance().Log("DI: " + division.getValue());
                DelLog.getInstance().Log("RO: " + round.getText());
                DelLog.getInstance().Log("YR: " + year.getText());
                DelLog.getInstance().Log("MR: " + mainReferee.getValue());
                DelLog.getInstance().Log("AR: " + ar1Referee.getValue());
                DelLog.getInstance().Log("AR: " + ar2Referee.getValue());
            }
            if(homeTeam.getValue() == null || awayTeam.getValue() == null || division.getValue() == null ||
                    isEmpty(round.getText()) || isEmpty(year.getText()) ||
                    mainReferee.getValue() == null || ar1Referee.getValue() == null || ar2Referee.getValue() == null)
                guif.displayErrorGame(e);
            else
            {
                if(guif.makeGame(e, homeTeam.getValue(), awayTeam.getValue(), division.getValue(),
                        Integer.valueOf(round.getText()), Integer.valueOf(year.getText()), mainReferee.getValue(),
                        ar1Referee.getValue(), ar2Referee.getValue()))
                {
                    int code = CommonGUI.getInstance().multipleEntry(e, "game");
                    if(code == 1)
                    {
                        homeTeam.getSelectionModel().select(null);
                        awayTeam.getSelectionModel().select(null);
                        division.getSelectionModel().select(null);
                        round.clear();
                        year.setText(String.format("%d", Calendar.getInstance().get(Calendar.YEAR)));
                        mainReferee.getSelectionModel().select(null);
                        ar1Referee.getSelectionModel().select(null);
                        ar2Referee.getSelectionModel().select(null);
                    }
                    else if (code == 0) CommonGUI.getInstance().backToMainMenu(e);
                    else if (code == -240)
                    {
                        DelLog.getInstance().Log(new CannotCreateException("Popup error for create game"));
                    }
                }
            }
        });

        gameWarning.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: red;" +
                "-fx-font-size: 20px;");
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
        GridPane.setConstraints(gameWarning, 5, 3, 4, 1);
        GridPane.setConstraints(homeTeamLabel, 3,  4);
        GridPane.setConstraints(awayTeamLabel,7 , 4);
        GridPane.setConstraints(divisionLabel, 3, 5);
        GridPane.setConstraints(roundLabel, 7, 5);
        GridPane.setConstraints(yearLabel, 9, 5);
        GridPane.setConstraints(mainRefereeLabel,4 , 7, 2, 1);
        GridPane.setConstraints(ar1RefereeLabel, 3, 8);
        GridPane.setConstraints(ar2RefereeLabel, 7, 8);
        GridPane.setConstraints(homeTeam, 4, 4, 3, 1);
        GridPane.setConstraints(awayTeam, 8, 4, 3, 1);
        GridPane.setConstraints(division, 4, 5, 3, 1);
        GridPane.setConstraints(round, 8, 5);
        GridPane.setConstraints(year, 10, 5);
        GridPane.setConstraints(mainReferee, 6, 7, 3, 1);
        GridPane.setConstraints(ar1Referee, 4, 8, 3, 1);
        GridPane.setConstraints(ar2Referee, 8, 8, 3, 1);
        GridPane.setConstraints(enterButton, 9, 10);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(gameWarning, homeTeamLabel, awayTeamLabel, divisionLabel, roundLabel,
                yearLabel, mainRefereeLabel, ar1RefereeLabel, ar2RefereeLabel,
                homeTeam, awayTeam, division, round, year, mainReferee,
                ar1Referee, ar2Referee, createGameLabel, enterButton);

        //Container
        BorderPane createGames = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        createGames.setPrefSize(640,480);

        CommonGUI.panes.add(createGames);

        return createGames;
    }
}
