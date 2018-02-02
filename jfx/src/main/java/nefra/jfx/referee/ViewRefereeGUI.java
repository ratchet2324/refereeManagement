package nefra.jfx.referee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.db.GUIFunctions;
import nefra.jfx.CommonGUI;
import nefra.referee.Referee;

public class ViewRefereeGUI {
    private final GUIFunctions guif = new GUIFunctions();
    private final TableView<Referee> table = new TableView<>();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the backToMainMenu button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        ObservableList<Referee> referee = FXCollections.observableArrayList(Referee.refereeList);

        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        final Label viewRefereeLabel = new Label("VIEW REFEREES");
        Button removeButton = new Button("Remove");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        removeButton.setOnAction(e -> {
            if(guif.removeWarning("referee") == 1)
                guif.removeReferee(e, table.getSelectionModel().getSelectedItem());
        });

        removeButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(viewRefereeLabel, HPos.CENTER);
        GridPane.setValignment(viewRefereeLabel, VPos.CENTER);

        viewRefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");

        GridPane.setConstraints(viewRefereeLabel, 5, 1, 4, 2);
        GridPane.setConstraints(table, 5,3,5,6);
        GridPane.setConstraints(removeButton, 8, 9);

        setupTable();
        table.setItems(referee);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(table, viewRefereeLabel, removeButton);



        //Container
        BorderPane viewReferees = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        viewReferees.setPrefSize(640,480);

        CommonGUI.panes.add(viewReferees);

        return viewReferees;
    }

    private void setupTable() {
        table.setEditable(false);

        final TableColumn<Referee, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        final TableColumn<Referee, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));

        final TableColumn<Referee, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setMinWidth(50);
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        final TableColumn<Referee, Number> weeklyFeeCol = new TableColumn<>("Weekly Fee");
        weeklyFeeCol.setMinWidth(50);
        weeklyFeeCol.setCellValueFactory(new PropertyValueFactory<>("WeeklyFee"));
        weeklyFeeCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Referee, Number> totalFeeCol = new TableColumn<>("Total Fee");
        totalFeeCol.setMinWidth(50);
        totalFeeCol.setCellValueFactory(new PropertyValueFactory<>("TotalFee"));
        totalFeeCol.setCellFactory(tc -> setTableCell());

        table.setPlaceholder(new Label("There are no referees to display"));
        table.getColumns().clear();
        table.getColumns().add(nameCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(phoneCol);
        table.getColumns().add(weeklyFeeCol);
        table.getColumns().add(totalFeeCol);
    }


    private TableCell<Referee, Number> setTableCell() {
        return new TableCell<Referee, Number>() {
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
