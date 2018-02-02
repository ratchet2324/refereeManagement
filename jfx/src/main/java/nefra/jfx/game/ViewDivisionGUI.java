package nefra.jfx.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.db.GUIFunctions;
import nefra.game.Division;
import nefra.jfx.CommonGUI;

public class ViewDivisionGUI {
    private final GUIFunctions guif = new GUIFunctions();
    private final TableView<Division> table = new TableView<>();

    /**
     * Creates the GUI for the create referee, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the backToMainMenu button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        ObservableList<Division> div = FXCollections.observableArrayList(Division.divisionList);

        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Centre
        GridPane centre = new GridPane();
        final Label viewDivisionLabel = new Label("VIEW DIVISIONS");
        Button removeButton = new Button("Remove");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        removeButton.setOnAction(e -> {
            if(guif.removeWarning("division") == 1)
                guif.removeDivision(e, table.getSelectionModel().getSelectedItem());
        });

        removeButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(viewDivisionLabel, HPos.CENTER);
        GridPane.setValignment(viewDivisionLabel, VPos.CENTER);

        viewDivisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");

        GridPane.setConstraints(viewDivisionLabel, 5, 1, 4, 2);
        GridPane.setConstraints(table, 5, 3, 5, 6);
        GridPane.setConstraints(removeButton, 8, 9);

        setupTable();
        table.setItems(div);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(table, viewDivisionLabel,removeButton);

        //Container
        BorderPane viewDivisions = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        viewDivisions.setPrefSize(640,480);

        CommonGUI.panes.add(viewDivisions);

        return viewDivisions;
    }

    private void setupTable() {
        table.setEditable(false);

        final TableColumn<Division, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        final TableColumn<Division, Number> mainCol = new TableColumn<>("Main Referee Fee");
        mainCol.setMinWidth(50);
        mainCol.setCellValueFactory(new PropertyValueFactory<>("mainRefereeFee"));
        mainCol.setCellFactory(tc -> setTableCell());

        final TableColumn<Division, Number> arCol = new TableColumn<>("Assistant Referee Fee");
        arCol.setMinWidth(50);
        arCol.setCellValueFactory(new PropertyValueFactory<>("arFee"));
        arCol.setCellFactory(tc -> setTableCell());

        table.setPlaceholder(new Label("There are no divisions to display"));
        table.getColumns().clear();
        table.getColumns().add(nameCol);
        table.getColumns().add(mainCol);
        table.getColumns().add(arCol);
    }


    private TableCell<Division, Number> setTableCell() {
        return new TableCell<Division, Number>() {
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
