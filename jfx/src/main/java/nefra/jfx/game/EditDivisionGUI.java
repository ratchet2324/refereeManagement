package nefra.jfx.game;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.db.GUIFunctions;
import nefra.game.Division;
import nefra.jfx.CommonGUI;

public class EditDivisionGUI {
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
        Label divisionLabel = new Label("Division: ");
        Label divisionNameLabel = new Label("Division Name: ");
        Label mainRefFeeLabel = new Label("Main Referee Fee: ");
        Label arFeeLabel = new Label("AR Fee: ");
        Label editDivisionLabel = new Label("EDIT DIVISION");
        ChoiceBox<Division> divisions = new ChoiceBox<>(FXCollections.observableArrayList(Division.divisionList));
        TextField divisionName = new TextField();
        TextField mainRefFee = new TextField();
        TextField arFee = new TextField();
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        divisionNameLabel.setVisible(false);
        mainRefFeeLabel.setVisible(false);
        arFeeLabel.setVisible(false);
        divisionName.setVisible(false);
        mainRefFee.setVisible(false);
        arFee.setVisible(false);
        clearButton.setVisible(false);
        updateButton.setVisible(false);

        divisions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue == null && newValue != null)
            {
                divisionName.setText(divisions.getValue().getDivisionName());
                mainRefFee.setText(( String.valueOf(divisions.getValue().getMainRefereeFee())));
                arFee.setText(( String.valueOf(divisions.getValue().getArFee())));
                divisionNameLabel.setVisible(true);
                mainRefFeeLabel.setVisible(true);
                arFeeLabel.setVisible(true);
                divisionName.setVisible(true);
                mainRefFee.setVisible(true);
                arFee.setVisible(true);
                clearButton.setVisible(true);
                updateButton.setVisible(true);
            }
            else if(oldValue != null && newValue != null)
            {
                divisionName.clear();
                mainRefFee.clear();
                arFee.clear();
                divisionName.setText(divisions.getValue().getDivisionName());
                mainRefFee.setText(( String.valueOf(divisions.getValue().getMainRefereeFee())));
                arFee.setText(( String.valueOf(divisions.getValue().getArFee())));
            }
            else
            {
                divisionName.clear();
                mainRefFee.clear();
                arFee.clear();
                divisionNameLabel.setVisible(false);
                mainRefFeeLabel.setVisible(false);
                arFeeLabel.setVisible(false);
                divisionName.setVisible(false);
                mainRefFee.setVisible(false);
                arFee.setVisible(false);
                clearButton.setVisible(false);
                updateButton.setVisible(false);
            }
        });

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        updateButton.setOnAction(e -> {
            guif.updateDivision(e, divisions.getValue(), divisionName.getText(),
                    Double.valueOf(mainRefFee.getText()), Double.valueOf(arFee.getText()));
            divisions.getSelectionModel().select(null);
            divisions.setItems(null);
            divisions.setItems(Division.divisionList);
        });

        clearButton.setOnAction(e -> divisions.getSelectionModel().select(null));

        divisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        divisionNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        mainRefFeeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        arFeeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        updateButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        clearButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(divisionLabel, HPos.RIGHT);
        GridPane.setHalignment(divisionNameLabel, HPos.RIGHT);
        GridPane.setHalignment(mainRefFeeLabel, HPos.RIGHT);
        GridPane.setHalignment(arFeeLabel, HPos.RIGHT);
        GridPane.setHalignment(editDivisionLabel, HPos.CENTER);
        GridPane.setValignment(editDivisionLabel, VPos.CENTER);

        editDivisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(editDivisionLabel, 5, 1, 4, 2);
        GridPane.setConstraints(divisionLabel, 4, 3, 2, 1);
        GridPane.setConstraints(divisionNameLabel, 4, 4, 2, 1);
        GridPane.setConstraints(mainRefFeeLabel, 4, 5, 2, 1);
        GridPane.setConstraints(arFeeLabel, 4, 6, 2, 1);
        GridPane.setConstraints(divisions, 6, 3, 2, 1);
        GridPane.setConstraints(divisionName, 6, 4,2, 1);
        GridPane.setConstraints(mainRefFee, 6, 5);
        GridPane.setConstraints(arFee, 6, 6);
        GridPane.setConstraints(clearButton, 9, 8);
        GridPane.setConstraints(updateButton, 8, 8);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(divisionLabel, editDivisionLabel, divisionNameLabel, divisions, divisionName,
                mainRefFeeLabel, mainRefFee, arFeeLabel, arFee, clearButton, updateButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane editDivisions = new BorderPane(centre, menu, null, backButton, null);
        editDivisions.setPrefSize(640,480);

        CommonGUI.panes.add(editDivisions);

        return editDivisions;
    }
}
