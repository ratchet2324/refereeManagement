package nefra.jfx.club;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import nefra.club.Club;
import nefra.club.GUIFunctions;
import nefra.jfx.CommonGUI;

public class ViewClubGUI {
    private GUIFunctions guif = new GUIFunctions();
    private TableView<Club> table = new TableView<>();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the back button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        ObservableList<Club> club = FXCollections.observableArrayList(Club.clubList);

        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        final Label viewClubLabel = new Label("VIEW CLUBS");
        Button enterButton = new Button("Enter");


        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {

        });


        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(viewClubLabel, HPos.CENTER);
        GridPane.setValignment(viewClubLabel, VPos.CENTER);

        viewClubLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");

        GridPane.setConstraints(viewClubLabel, 5, 1, 4, 2);
        GridPane.setConstraints(table, 5,3,8,5);
        GridPane.setConstraints(enterButton, 6, 8);

        setupTable();
        table.setItems(club);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(viewClubLabel, table, enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane viewClubs = new BorderPane(centre, menu, null, backButton, null);
        viewClubs.setPrefSize(640,480);

        CommonGUI.panes.add(viewClubs);

        return viewClubs;
    }

    private void setupTable()
    {
        table.setEditable(false);
        final TableColumn<Club, Integer> idCol = new TableColumn<>("ID");
        idCol.setMinWidth(40);
        idCol.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getClubId()));

        final TableColumn<Club, String> clubNameCol = new TableColumn<>("Club Name");
        clubNameCol.setMinWidth(100);
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("ClubName"));

        final TableColumn<Club, String> streetCol = new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        final TableColumn<Club, String> suburbCol = new TableColumn<>("Suburb");
        suburbCol.setMinWidth(100);
        suburbCol.setCellValueFactory(new PropertyValueFactory<>("suburb"));

        final TableColumn<Club, String> stateCol = new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        final TableColumn<Club, String> postcodeCol = new TableColumn<>("Postcode");
        postcodeCol.setMinWidth(100);
        postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));

        final TableColumn<Club, String> presNameCol = new TableColumn<>("President Name");
        presNameCol.setMinWidth(110);
        presNameCol.setCellValueFactory(new PropertyValueFactory<>("presidentName"));

        final TableColumn<Club, String> presContactCol = new TableColumn<>("President Contact");
        presContactCol.setMinWidth(110);
        presContactCol.setCellValueFactory(new PropertyValueFactory<>("presidentContact"));

        final TableColumn<Club, Number> weeklyFeeCol = new TableColumn<>("Weekly Fee");
        weeklyFeeCol.setMinWidth(100);
        weeklyFeeCol.setCellValueFactory(new PropertyValueFactory<>("weeklyFee"));
        weeklyFeeCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Club, Number> totalFeeCol = new TableColumn<>("Total Fee");
        totalFeeCol.setMinWidth(100);
        totalFeeCol.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
        totalFeeCol.setCellFactory(tc -> setTableCell());

        table.setPlaceholder(new Label("There are no clubs to display"));
        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(clubNameCol);
        table.getColumns().add(streetCol);
        table.getColumns().add(suburbCol);
        table.getColumns().add(stateCol);
        table.getColumns().add(postcodeCol);
        table.getColumns().add(presNameCol);
        table.getColumns().add(presContactCol);
        table.getColumns().add(weeklyFeeCol);
        table.getColumns().add(totalFeeCol);
    }

    private TableCell<Club, Number> setTableCell() {
        return new TableCell<Club, Number>() {
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
