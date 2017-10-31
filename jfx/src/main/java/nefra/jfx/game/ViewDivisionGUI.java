package nefra.jfx.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.game.Division;
import nefra.game.GUIFunctions;
import nefra.jfx.CommonGUI;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class ViewDivisionGUI {
    private GUIFunctions guif = new GUIFunctions();
    private TableView<Division> table = new TableView<Division>();

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
        final Label viewDivisionLabel = new Label("VIEW DIVISIONS");
        Button enterButton = new Button("Enter");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {

        });

        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(viewDivisionLabel, HPos.CENTER);
        GridPane.setValignment(viewDivisionLabel, VPos.CENTER);

        viewDivisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");

        GridPane.setConstraints(viewDivisionLabel, 5, 1, 4, 2);
        GridPane.setConstraints(table, 4, 3, 5, 4);
        GridPane.setConstraints(enterButton, 8, 7);

        table.setEditable(false);
        final TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<Division, String>("division_id"));
        final TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<Division, String>("divisionName"));
        final TableColumn mainCol = new TableColumn("Main Referee Fee");
        mainCol.setMinWidth(50);
        mainCol.setCellValueFactory(new PropertyValueFactory<Division, String>("mainRefereeFee"));
        final TableColumn arCol = new TableColumn("Assistant Referee Fee");
        arCol.setMinWidth(50);
        arCol.setCellValueFactory(new PropertyValueFactory<Division, String>("arFee"));

        ObservableList<Division> div = FXCollections.observableArrayList();
        div.addAll(Division.divisionList);
        table.setItems(div);
        table.getColumns().addAll(idCol, nameCol, mainCol, arCol);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(viewDivisionLabel, table,enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane viewDivisions = new BorderPane(centre, menu, null, backButton, null);
        viewDivisions.setPrefSize(640,480);

        CommonGUI.panes.add(viewDivisions);

        return viewDivisions;
    }
}
