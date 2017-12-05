package nefra.jfx.game;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class ViewGameGUI {
    private GUIFunctions guif = new GUIFunctions();
    private TableView<Game> table = new TableView<>();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the back button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        ObservableList<Game> game = FXCollections.observableArrayList(Game.gameList);
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        final Label viewGameLabel = new Label("VIEW GAMES");
        Button removeButton = new Button("Remove");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        removeButton.setOnAction(e -> {
            guif.removeGame(e, table.getSelectionModel().getSelectedItem());
        });

        removeButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(viewGameLabel, HPos.CENTER);
        GridPane.setValignment(viewGameLabel, VPos.CENTER);

        viewGameLabel.setTextAlignment(TextAlignment.CENTER);
        viewGameLabel.setAlignment(Pos.CENTER);
        viewGameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(viewGameLabel, 5, 1, 4, 2);
        GridPane.setConstraints(table, 3,3,10,5);
        GridPane.setConstraints(removeButton, 6, 9);

        setupTable();
        table.setItems(game);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(table, viewGameLabel, removeButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane viewGames = new BorderPane(centre, menu, null, backButton, null);
        viewGames.setPrefSize(640,480);

        CommonGUI.panes.add(viewGames);

        return viewGames;
    }

    private void setupTable() {
        table.setEditable(false);

        final TableColumn<Game, Integer> idCol = new TableColumn<>("ID");
        idCol.setMinWidth(40);
        idCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getGameId()));

        final TableColumn<Game, String> homeCol = new TableColumn<>("Home Team");
        homeCol.setMinWidth(100);
        homeCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getHome().getClubName()));

        final TableColumn<Game, String> awayCol = new TableColumn<>("Away Team");
        awayCol.setMinWidth(100);
        awayCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAway().getClubName()));

        final TableColumn<Game, String> divisionCol = new TableColumn<>("Division");
        divisionCol.setMinWidth(50);
        divisionCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getDivision().getDivisionName()));

        final TableColumn<Game, Integer> roundCol = new TableColumn<>("Round");
        roundCol.setMinWidth(50);
        roundCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getRound()));

        final TableColumn<Game, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setMinWidth(50);
        yearCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getYear()));

        final TableColumn<Game, String> mainCol = new TableColumn<>("Main Referee");
        mainCol.setMinWidth(50);
        mainCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getMain().getName()));

        final TableColumn<Game, String> ar1Col = new TableColumn<>("Assistant Referee 1");
        ar1Col.setMinWidth(50);
        ar1Col.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAr1().getName()));

        final TableColumn<Game, String> ar2Col = new TableColumn<>("Assistant Referee 2");
        ar2Col.setMinWidth(50);
        ar2Col.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAr2().getName()));

        final TableColumn<Game, Number> totalFeeCol = new TableColumn<>("Total Fee");
        totalFeeCol.setMinWidth(50);
        totalFeeCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getTotalFee()));
        totalFeeCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Game, Number> homeFeeCol = new TableColumn<>("Home Team Fee");
        homeFeeCol.setMinWidth(50);
        homeFeeCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getHomeClubFee()));
        homeFeeCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Game, Number> awayFeeCol = new TableColumn<>("Away Team Fee");
        awayFeeCol.setMinWidth(50);
        awayFeeCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAwayClubFee()));
        awayFeeCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Game, Number> adminFeeCol = new TableColumn<>("Admin Fee");
        adminFeeCol.setMinWidth(50);
        adminFeeCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdminFee()));
        adminFeeCol.setCellFactory(tc -> setTableCell());

        table.setPlaceholder(new Label("There are no games to display"));

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(homeCol);
        table.getColumns().add(awayCol);
        table.getColumns().add(divisionCol);
        table.getColumns().add(roundCol);
        table.getColumns().add(yearCol);
        table.getColumns().add(mainCol);
        table.getColumns().add(ar1Col);
        table.getColumns().add(ar2Col);
        table.getColumns().add(totalFeeCol);
        table.getColumns().add(homeFeeCol);
        table.getColumns().add(awayFeeCol);
        table.getColumns().add(adminFeeCol);
    }


    private TableCell<Game, Number> setTableCell() {
        return new TableCell<Game, Number>() {
            @Override
            protected void updateItem(Number value, boolean empty) {
                super.updateItem(value, empty) ;
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", value.doubleValue()));
                }
            }
        };
    }
}
