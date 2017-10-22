package nefra.jfx.club;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import nefra.jfx.CommonGUI;
import nefra.club.GUIFunctions;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class CreateClubGUI {
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

        //isEmail.setIndeterminate(false);

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            System.out.println("CN: "+ clubName.getText());
            System.out.println("AD: "+ street.getText());
            System.out.println("SU: "+ suburb.getText());
            System.out.println("ST: "+ state.getText());
            System.out.println("PC: "+ postcode.getText());
            System.out.println("PN: "+ presidentName.getText());
            System.out.println("PC: "+ presidentContact.getText());

            if(isEmpty(clubName.getText()))
                guif.displayError(e);
            else
            {
                if(isEmpty(presidentName.getText()) || isEmpty(presidentContact.getText()))
                    guif.makeClub(e, clubName.getText(),
                            street.getText(), suburb.getText(),
                            state.getText(), postcode.getText());
                else if (isEmpty(street.getText()) || isEmpty(suburb.getText())
                        || isEmpty(state.getText()) || isEmpty(postcode.getText()))
                    guif.makeClub(e, clubName.getText() , presidentName.getText(), presidentContact.getText());
                else if (isNotEmpty(presidentName.getText()) && isNotEmpty(presidentContact.getText())
                        && isNotEmpty(street.getText()) && isNotEmpty(suburb.getText())
                        && isNotEmpty(state.getText()) && isNotEmpty(postcode.getText()))
                    guif.makeClub(e, clubName.getText(), street.getText(), suburb.getText(), state.getText(),
                            postcode.getText(), presidentName.getText(), presidentContact.getText());
                else guif.makeClub(e, clubName.getText());
            }
        });

        clubNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        streetLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        suburbLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        stateLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        postcodeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        presidentNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        presidentContactLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
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
        GridPane.setConstraints(clubNameLabel, 2, 3, 2, 1);
        GridPane.setConstraints(streetLabel, 2, 4, 2, 1);
        GridPane.setConstraints(suburbLabel, 3, 5);
        GridPane.setConstraints(stateLabel, 5, 5);
        GridPane.setConstraints(postcodeLabel, 7, 5);
        GridPane.setConstraints(presidentNameLabel, 2, 6, 2, 1);
        GridPane.setConstraints(presidentContactLabel, 2, 7, 2, 1);
        GridPane.setConstraints(clubName, 4, 3,2, 1);
        GridPane.setConstraints(street, 4, 4, 2, 1);
        GridPane.setConstraints(suburb, 4, 5);
        GridPane.setConstraints(state, 6, 5);
        GridPane.setConstraints(postcode, 8, 5);
        GridPane.setConstraints(presidentName, 4, 6,2, 1);
        GridPane.setConstraints(presidentContact, 4, 7, 2, 1);
        GridPane.setConstraints(enterButton, 6, 8);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(createClubLabel, clubNameLabel, streetLabel,
                suburbLabel, stateLabel, postcodeLabel, presidentNameLabel, presidentContactLabel,
                clubName, street, suburb, state, postcode, presidentName, presidentContact, enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane clubs = new BorderPane(centre, menu, null, backButton, null);
        clubs.setPrefSize(640,480);

        CommonGUI.panes.add(clubs);

        return clubs;
    }
}
