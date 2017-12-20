package nefra.jfx.club;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.club.Club;
import nefra.club.GUIFunctions;
import nefra.jfx.CommonGUI;

public class EditClubGUI {
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
        Label clubLabel = new Label("Club: ");
        Label clubNameLabel = new Label("Club Name: ");
        Label streetLabel = new Label("Address Line 1:");
        Label suburbLabel = new Label("Suburb: ");
        Label stateLabel = new Label("State: ");
        Label postcodeLabel = new Label("Postcode: ");
        Label presidentNameLabel = new Label("President's Name: ");
        Label presidentContactLabel = new Label("President's Contact: ");
        Label createClubLabel = new Label("EDIT CLUB");
        ChoiceBox<Club> club = new ChoiceBox<>(FXCollections.observableArrayList(Club.clubList));
        TextField clubName = new TextField();
        TextField street = new TextField();
        TextField suburb = new TextField();
        TextField state = new TextField();
        TextField postcode = new TextField();
        TextField presidentName = new TextField();
        TextField presidentContact = new TextField();
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        clubNameLabel.setVisible(false);
        streetLabel.setVisible(false);
        suburbLabel.setVisible(false);
        stateLabel.setVisible(false);
        postcodeLabel.setVisible(false);
        presidentNameLabel.setVisible(false);
        presidentContactLabel.setVisible(false);
        clubName.setVisible(false);
        street.setVisible(false);
        suburb.setVisible(false);
        state.setVisible(false);
        postcode.setVisible(false);
        presidentName.setVisible(false);
        presidentContact.setVisible(false);
        clearButton.setVisible(false);
        updateButton.setVisible(false);

        club.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null)
            {
                clubName.setText(club.getValue().getClubName());
                street.setText(club.getValue().getStreet());
                suburb.setText(club.getValue().getSuburb());
                state.setText(club.getValue().getState());
                postcode.setText(club.getValue().getPostcode());
                presidentName.setText(club.getValue().getPresidentName());
                presidentContact.setText(club.getValue().getPresidentContact());
                clubNameLabel.setVisible(true);
                streetLabel.setVisible(true);
                suburbLabel.setVisible(true);
                stateLabel.setVisible(true);
                postcodeLabel.setVisible(true);
                presidentNameLabel.setVisible(true);
                presidentContactLabel.setVisible(true);
                clubName.setVisible(true);
                street.setVisible(true);
                suburb.setVisible(true);
                state.setVisible(true);
                postcode.setVisible(true);
                presidentName.setVisible(true);
                presidentContact.setVisible(true);
                clearButton.setVisible(true);
                updateButton.setVisible(true);
            }
            else if(oldValue != null && newValue != null)
            {
                clubName.clear();
                street.clear();
                suburb.clear();
                state.clear();
                postcode.clear();
                presidentName.clear();
                presidentContact.clear();
                clubName.setText(club.getValue().getClubName());
                street.setText(club.getValue().getStreet());
                suburb.setText(club.getValue().getSuburb());
                state.setText(club.getValue().getState());
                postcode.setText(club.getValue().getPostcode());
                presidentName.setText(club.getValue().getPresidentName());
                presidentContact.setText(club.getValue().getPresidentContact());
            }
            else
            {
                clubName.clear();
                street.clear();
                suburb.clear();
                state.clear();
                postcode.clear();
                presidentName.clear();
                presidentContact.clear();
                clubNameLabel.setVisible(false);
                streetLabel.setVisible(false);
                suburbLabel.setVisible(false);
                stateLabel.setVisible(false);
                postcodeLabel.setVisible(false);
                presidentNameLabel.setVisible(false);
                presidentContactLabel.setVisible(false);
                clubName.setVisible(false);
                street.setVisible(false);
                suburb.setVisible(false);
                state.setVisible(false);
                postcode.setVisible(false);
                presidentName.setVisible(false);
                presidentContact.setVisible(false);
                clearButton.setVisible(false);
                updateButton.setVisible(false);
            }
        });


        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        updateButton.setOnAction(e -> {
            guif.updateClub(e, club.getValue(), clubName.getText(), street.getText(), suburb.getText(),
                    state.getText(), postcode.getText(), presidentName.getText(), presidentContact.getText());
            club.getSelectionModel().select(null);
            club.setItems(null);
            club.setItems(Club.clubList);
        });

        clearButton.setOnAction(e -> club.getSelectionModel().select(null));

        clubLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
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
        updateButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        clearButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        GridPane.setHalignment(clubLabel, HPos.RIGHT);
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
        GridPane.setConstraints(clubLabel, 4, 3);
        GridPane.setConstraints(club, 6, 3);
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
        GridPane.setConstraints(updateButton, 6, 9);
        GridPane.setConstraints(clearButton, 8, 9);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(createClubLabel, clubNameLabel, streetLabel,
                suburbLabel, stateLabel, postcodeLabel, presidentNameLabel, presidentContactLabel,
                clubName, street, suburb, state, postcode, presidentName, presidentContact, clearButton,
                updateButton, clubLabel, club);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane editClubs = new BorderPane(centre, menu, null, backButton, null);
        editClubs.setPrefSize(640,480);

        CommonGUI.panes.add(editClubs);

        return editClubs;
    }
}
