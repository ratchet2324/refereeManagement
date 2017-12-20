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
import nefra.game.Game;
import nefra.jfx.CommonGUI;
import nefra.referee.Referee;

import java.util.Calendar;

public class EditGameGUI {
    private final GUIFunctions guif = new GUIFunctions();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the back button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        Label gameLabel = new Label("Game: ");
        Label homeTeamLabel = new Label("Home: ");
        Label awayTeamLabel = new Label("Away: ");
        Label divisionLabel = new Label("Division: ");
        Label roundLabel = new Label("Round: ");
        Label yearLabel = new Label("Year: ");
        Label mainRefereeLabel = new Label("Main Referee: ");
        Label ar1RefereeLabel = new Label("AR 1: ");
        Label ar2RefereeLabel = new Label("AR 2: ");
        Label editGameLabel = new Label("EDIT GAME");
        ChoiceBox<Club> homeTeam = new ChoiceBox<>(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox<Club> awayTeam = new ChoiceBox<>(FXCollections.observableArrayList(Club.clubList));
        ChoiceBox<Division> division = new ChoiceBox<>(FXCollections.observableArrayList(Division.divisionList));
        TextField round = new TextField();
        TextField year = new TextField(String.format("%d", Calendar.getInstance().get(Calendar.YEAR)));
        ChoiceBox<Referee> mainReferee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox<Referee> ar1Referee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox<Referee> ar2Referee = new ChoiceBox<>(FXCollections.observableArrayList(Referee.refereeList));
        ChoiceBox<Game> game = new ChoiceBox<>(FXCollections.observableArrayList(Game.gameList));
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        homeTeamLabel.setVisible(false);
        homeTeam.setVisible(false);
        awayTeamLabel.setVisible(false);
        awayTeam.setVisible(false);
        divisionLabel.setVisible(false);
        division.setVisible(false);
        roundLabel.setVisible(false);
        round.setVisible(false);
        yearLabel.setVisible(false);
        year.setVisible(false);
        mainRefereeLabel.setVisible(false);
        mainReferee.setVisible(false);
        ar1RefereeLabel.setVisible(false);
        ar1Referee.setVisible(false);
        ar2RefereeLabel.setVisible(false);
        ar2Referee.setVisible(false);
        updateButton.setVisible(false);
        clearButton.setVisible(false);

        game.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue == null && newValue != null)
            {
                homeTeam.getSelectionModel().select(newValue.getHome());
                awayTeam.getSelectionModel().select(newValue.getAway());
                division.getSelectionModel().select(newValue.getDivision());
                round.setText(String.valueOf(game.getValue().getRound()));
                year.setText(String.valueOf(game.getValue().getYear()));
                mainReferee.getSelectionModel().select(newValue.getMain());
                ar1Referee.getSelectionModel().select(newValue.getAr1());
                ar2Referee.getSelectionModel().select(newValue.getAr2());
                homeTeamLabel.setVisible(true);
                awayTeamLabel.setVisible(true);
                divisionLabel.setVisible(true);
                roundLabel.setVisible(true);
                yearLabel.setVisible(true);
                mainRefereeLabel.setVisible(true);
                ar1RefereeLabel.setVisible(true);
                ar2RefereeLabel.setVisible(true);
                homeTeam.setVisible(true);
                awayTeam.setVisible(true);
                division.setVisible(true);
                round.setVisible(true);
                year.setVisible(true);
                mainReferee.setVisible(true);
                ar1Referee.setVisible(true);
                ar2Referee.setVisible(true);
                clearButton.setVisible(true);
                updateButton.setVisible(true);
            }
            else if(oldValue != null && newValue != null)
            {
                homeTeam.getSelectionModel().select(null);
                awayTeam.getSelectionModel().select(null);
                division.getSelectionModel().select(null);
                round.clear();
                year.clear();
                mainReferee.getSelectionModel().select(null);
                ar1Referee.getSelectionModel().select(null);
                ar2Referee.getSelectionModel().select(null);
                homeTeam.getSelectionModel().select(newValue.getHome());
                awayTeam.getSelectionModel().select(newValue.getAway());
                division.getSelectionModel().select(newValue.getDivision());
                round.setText(String.valueOf(game.getValue().getRound()));
                year.setText(String.valueOf(game.getValue().getYear()));
                mainReferee.getSelectionModel().select(newValue.getMain());
                ar1Referee.getSelectionModel().select(newValue.getAr1());
                ar2Referee.getSelectionModel().select(newValue.getAr2());
            }
            else
            {
                homeTeam.getSelectionModel().select(null);
                awayTeam.getSelectionModel().select(null);
                division.getSelectionModel().select(null);
                round.clear();
                year.clear();
                mainReferee.getSelectionModel().select(null);
                ar1Referee.getSelectionModel().select(null);
                ar2Referee.getSelectionModel().select(null);
                homeTeamLabel.setVisible(false);
                awayTeamLabel.setVisible(false);
                divisionLabel.setVisible(false);
                roundLabel.setVisible(false);
                yearLabel.setVisible(false);
                mainRefereeLabel.setVisible(false);
                ar1RefereeLabel.setVisible(false);
                ar2RefereeLabel.setVisible(false);
                homeTeam.setVisible(false);
                awayTeam.setVisible(false);
                division.setVisible(false);
                round.setVisible(false);
                year.setVisible(false);
                mainReferee.setVisible(false);
                ar1Referee.setVisible(false);
                ar2Referee.setVisible(false);
                clearButton.setVisible(false);
                updateButton.setVisible(false);
            }
        });

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
        updateButton.setOnAction(e -> {
            guif.updateGame(e, game.getValue(), homeTeam.getValue(), awayTeam.getValue(),
                    division.getValue(), Integer.valueOf(round.getText()), Integer.valueOf(year.getText()),
                    mainReferee.getValue(), ar1Referee.getValue(), ar2Referee.getValue());
            game.getSelectionModel().select(null);
            game.setItems(null);
            game.setItems(Game.gameList);
        });

        clearButton.setOnAction(e -> game.getSelectionModel().select(null));

        gameLabel.setStyle("-fx-font-weight: bold;" +
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
        updateButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        clearButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(gameLabel, HPos.CENTER);
        GridPane.setHalignment(homeTeamLabel, HPos.CENTER);
        GridPane.setHalignment(awayTeamLabel, HPos.CENTER);
        GridPane.setHalignment(divisionLabel, HPos.CENTER);
        GridPane.setHalignment(roundLabel, HPos.CENTER);
        GridPane.setHalignment(yearLabel, HPos.CENTER);
        GridPane.setHalignment(mainRefereeLabel, HPos.CENTER);
        GridPane.setHalignment(ar1RefereeLabel, HPos.CENTER);
        GridPane.setHalignment(ar2RefereeLabel, HPos.CENTER);
        GridPane.setHalignment(editGameLabel, HPos.CENTER);
        GridPane.setValignment(editGameLabel, VPos.CENTER);

        editGameLabel.setTextAlignment(TextAlignment.CENTER);
        editGameLabel.setAlignment(Pos.CENTER);
        editGameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(editGameLabel, 5, 1, 4, 2);
        GridPane.setConstraints(gameLabel, 3, 3);
        GridPane.setConstraints(game, 4, 3);
        GridPane.setConstraints(homeTeamLabel, 3, 4);
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
        GridPane.setConstraints(updateButton, 9, 10);
        GridPane.setConstraints(clearButton, 10, 10);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(homeTeamLabel, awayTeamLabel, divisionLabel, roundLabel,
                yearLabel, mainRefereeLabel, ar1RefereeLabel, ar2RefereeLabel,
                homeTeam, awayTeam, division, round, year, mainReferee,
                ar1Referee, ar2Referee, editGameLabel, updateButton, clearButton,
                game, gameLabel);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane editGames = new BorderPane(centre, menu, null, backButton, null);
        editGames.setPrefSize(640,480);

        CommonGUI.panes.add(editGames);

        return editGames;
    }
}
