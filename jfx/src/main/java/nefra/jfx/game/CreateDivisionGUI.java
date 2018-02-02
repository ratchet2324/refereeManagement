package nefra.jfx.game;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.db.GUIFunctions;
import nefra.exceptions.CannotCreateException;
import nefra.exceptions.DelLog;
import nefra.jfx.CommonGUI;
import nefra.misc.Debug;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CreateDivisionGUI {
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
        Label divisionNameLabel = new Label("Division Name: ");
        Label divisionWarning = new Label("All fields must be entered.");
        Label mainRefFeeLabel = new Label("Main Referee Fee: ");
        Label arFeeLabel = new Label("AR Fee: ");
        Label createDivisionLabel = new Label("CREATE DIVISION");
        TextField divisionName = new TextField();
        TextField mainRefFee = new TextField();
        TextField arFee = new TextField();
        Button enterButton = new Button("Enter");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            if (Debug.debugMode) {
                DelLog.getInstance().Log("DN: " + divisionName.getText());
                DelLog.getInstance().Log("MF: " + mainRefFee.getText());
                DelLog.getInstance().Log("AR: " + arFee.getText());
            }

            if (isEmpty(divisionName.getText()) && isEmpty(mainRefFee.getText()) && isEmpty(arFee.getText()))
                guif.displayErrorDivision(e);
            else
                if(guif.makeDivision(e, divisionName.getText(), mainRefFee.getText(), arFee.getText()))
                {
                    int code = CommonGUI.getInstance().multipleEntry(e,"division");
                    if(code == 1)
                    {
                        divisionName.clear();
                        mainRefFee.clear();
                        arFee.clear();
                    }
                    else if (code == 0) CommonGUI.getInstance().backToMainMenu(e);
                    else if (code == -240)
                    {
                        DelLog.getInstance().Log(new CannotCreateException("Popup error for create division"));
                    }
                }
        });

        divisionWarning.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: red;" +
                "-fx-font-size: 20px;");
        divisionNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        mainRefFeeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        arFeeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(divisionNameLabel, HPos.RIGHT);
        GridPane.setHalignment(mainRefFeeLabel, HPos.RIGHT);
        GridPane.setHalignment(arFeeLabel, HPos.RIGHT);
        GridPane.setHalignment(createDivisionLabel, HPos.CENTER);
        GridPane.setValignment(createDivisionLabel, VPos.CENTER);

        createDivisionLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(createDivisionLabel, 5, 1, 4, 2);
        GridPane.setConstraints(divisionWarning, 5, 3, 3, 1);
        GridPane.setConstraints(divisionNameLabel, 4, 4, 2, 1);
        GridPane.setConstraints(mainRefFeeLabel, 4, 5, 2, 1);
        GridPane.setConstraints(arFeeLabel, 4, 6, 2, 1);
        GridPane.setConstraints(divisionName, 6, 4,2, 1);
        GridPane.setConstraints(mainRefFee, 6, 5);
        GridPane.setConstraints(arFee, 6, 6);
        GridPane.setConstraints(enterButton, 8, 8);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(createDivisionLabel, divisionWarning, divisionNameLabel, divisionName,
                mainRefFeeLabel, mainRefFee, arFeeLabel, arFee, enterButton);

        //Container
        BorderPane createDivisions = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        createDivisions.setPrefSize(640,480);

        CommonGUI.panes.add(createDivisions);

        return createDivisions;
    }
}
