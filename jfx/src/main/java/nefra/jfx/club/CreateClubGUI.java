package nefra.jfx.club;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import nefra.db.GUIFunctions;
import nefra.exceptions.CannotCreateException;
import nefra.exceptions.DelLog;
import nefra.jfx.CommonGUI;
import nefra.misc.Debug;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CreateClubGUI {
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
        Label clubWarning = new Label("Club name is required.");
        Label clubNameLabel = new Label("Club Name: ");
        Label streetLabel = new Label("Address Line 1:");
        Label suburbLabel = new Label("Suburb: ");
        Label stateLabel = new Label("State: ");
        Label postcodeLabel = new Label("Postcode: ");
        Label presidentNameLabel = new Label("President's Name: ");
        Label presidentContactLabel = new Label("President's Contact: ");
        Label createClubLabel = new Label("CREATE CLUB");
        TextField clubName = new TextField();
        TextField street = new TextField();
        TextField suburb = new TextField();
        TextField state = new TextField();
        TextField postcode = new TextField();
        TextField presidentName = new TextField();
        TextField presidentContact = new TextField();
        Button enterButton = new Button("Enter");


        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            if(Debug.debugMode) {
                DelLog.getInstance().Log("CN: " + clubName.getText());
                DelLog.getInstance().Log("AD: " + street.getText());
                DelLog.getInstance().Log("SU: " + suburb.getText());
                DelLog.getInstance().Log("ST: " + state.getText());
                DelLog.getInstance().Log("PC: " + postcode.getText());
                DelLog.getInstance().Log("PN: " + presidentName.getText());
                DelLog.getInstance().Log("PC: " + presidentContact.getText());
            }

            if(isEmpty(clubName.getText()))
                guif.displayErrorClub(e);
            else
            {
                if(guif.makeClub(e, clubName.getText(), street.getText(), suburb.getText(), state.getText(),
                        postcode.getText(), presidentName.getText(), presidentContact.getText()))
                {
                    int code = CommonGUI.getInstance().multipleEntry(e, "club");
                    if(code == 1)
                    {
                        clubName.clear();
                        street.clear();
                        suburb.clear();
                        state.clear();
                        postcode.clear();
                        presidentName.clear();
                        presidentContact.clear();
                    }
                    else if (code == 0) CommonGUI.getInstance().backToMainMenu(e);
                    else if (code == -240)
                    {
                        DelLog.getInstance().Log(new CannotCreateException("Popup error for create club"));
                    }
                }
            }
        });

        clubWarning.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: red;" +
                "-fx-font-size: 16px;");
        clubNameLabel.setStyle("-fx-font-weight: bold;" +
                        "-fx-font-size: 16px;");
        streetLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        suburbLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        stateLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        postcodeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        presidentNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        presidentContactLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(clubNameLabel, HPos.RIGHT);
        GridPane.setHalignment(streetLabel, HPos.RIGHT);
        GridPane.setHalignment(suburbLabel, HPos.RIGHT);
        GridPane.setHalignment(stateLabel, HPos.CENTER);
        GridPane.setHalignment(postcodeLabel, HPos.CENTER);
        GridPane.setHalignment(presidentNameLabel, HPos.RIGHT);
        GridPane.setHalignment(presidentContactLabel, HPos.RIGHT);
        GridPane.setHalignment(state, HPos.LEFT);
        GridPane.setHalignment(postcode, HPos.LEFT);
        GridPane.setHalignment(createClubLabel, HPos.CENTER);
        GridPane.setValignment(createClubLabel, VPos.CENTER);

        createClubLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(createClubLabel, 5, 1, 4, 2);
        GridPane.setConstraints(clubWarning, 5, 3, 4, 1);
        GridPane.setConstraints(clubNameLabel, 2, 4, 2, 1);
        GridPane.setConstraints(streetLabel, 2, 5, 2, 1);
        GridPane.setConstraints(suburbLabel, 3, 6);
        GridPane.setConstraints(stateLabel, 5, 6);
        GridPane.setConstraints(postcodeLabel, 7, 6);
        GridPane.setConstraints(presidentNameLabel, 2, 7, 2, 1);
        GridPane.setConstraints(presidentContactLabel, 2, 8, 2, 1);
        GridPane.setConstraints(clubName, 4, 4,2, 1);
        GridPane.setConstraints(street, 4, 5, 2, 1);
        GridPane.setConstraints(suburb, 4, 6);
        GridPane.setConstraints(state, 6, 6);
        GridPane.setConstraints(postcode, 8, 6);
        GridPane.setConstraints(presidentName, 4, 7,2, 1);
        GridPane.setConstraints(presidentContact, 4, 8, 2, 1);
        GridPane.setConstraints(enterButton, 6, 9);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(clubWarning, createClubLabel, clubNameLabel, streetLabel,
                suburbLabel, stateLabel, postcodeLabel, presidentNameLabel, presidentContactLabel,
                clubName, street, suburb, state, postcode, presidentName, presidentContact, enterButton);

        //Container
        BorderPane createClubs = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        createClubs.setPrefSize(640,480);

        CommonGUI.panes.add(createClubs);

        return createClubs;
    }
}
