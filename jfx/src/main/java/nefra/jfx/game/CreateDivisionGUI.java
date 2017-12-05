package nefra.jfx.game;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.game.GUIFunctions;
import nefra.jfx.CommonGUI;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CreateDivisionGUI {
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

        //Centre
        GridPane centre = new GridPane();
        Label divisionNameLabel = new Label("Division Name: ");
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
        //TODO: CLEAR TEXT BOXES (DEFAULT)
        enterButton.setOnAction(e -> {
            System.out.println("DN: "+ divisionName.getText());
            System.out.println("MF: "+ mainRefFee.getText());
            System.out.println("AR: "+ arFee.getText());

            if (isEmpty(divisionName.getText()) && isEmpty(mainRefFee.getText()) && isEmpty(arFee.getText()))
                guif.displayError(e);
            else
                guif.makeDivision(e, divisionName.getText(), mainRefFee.getText(), arFee.getText());
        });

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
        GridPane.setConstraints(divisionNameLabel, 4, 3, 2, 1);
        GridPane.setConstraints(mainRefFeeLabel, 4, 4, 2, 1);
        GridPane.setConstraints(arFeeLabel, 4, 5, 2, 1);
        GridPane.setConstraints(divisionName, 6, 3,2, 1);
        GridPane.setConstraints(mainRefFee, 6, 4);
        GridPane.setConstraints(arFee, 6, 5);
        GridPane.setConstraints(enterButton, 8, 7);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(createDivisionLabel, divisionNameLabel, divisionName,
                mainRefFeeLabel, mainRefFee, arFeeLabel, arFee, enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane createDivisions = new BorderPane(centre, menu, null, backButton, null);
        createDivisions.setPrefSize(640,480);

        CommonGUI.panes.add(createDivisions);

        return createDivisions;
    }
}
