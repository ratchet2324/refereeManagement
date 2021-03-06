package nefra.jfx.referee;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.db.GUIFunctions;
import nefra.jfx.CommonGUI;
import nefra.referee.Referee;

public class EditRefereeGUI {
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
        Label refereeLabel = new Label("Referee: ");
        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label phoneLabel = new Label("Phone: ");
        Label editRefereeLabel = new Label("EDIT REFEREE");
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        ChoiceBox<Referee> referee = new ChoiceBox<>(Referee.refereeList);
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");
        firstNameLabel.setVisible(false);
        firstName.setVisible(false);
        lastNameLabel.setVisible(false);
        lastName.setVisible(false);
        emailLabel.setVisible(false);
        email.setVisible(false);
        phoneLabel.setVisible(false);
        phone.setVisible(false);
        updateButton.setVisible(false);
        clearButton.setVisible(false);

        referee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue == null && newValue != null)
            {
                firstName.setText(referee.getValue().getFirstName());
                lastName.setText(referee.getValue().getLastName());
                email.setText(referee.getValue().getEmail());
                phone.setText(referee.getValue().getPhone());
                firstNameLabel.setVisible(true);
                lastNameLabel.setVisible(true);
                emailLabel.setVisible(true);
                phoneLabel.setVisible(true);
                firstName.setVisible(true);
                lastName.setVisible(true);
                email.setVisible(true);
                phone.setVisible(true);
                updateButton.setVisible(true);
                clearButton.setVisible(true);
            }
            else if(oldValue != null && newValue != null)
            {
                firstName.clear();
                lastName.clear();
                email.clear();
                phone.clear();
                firstName.setText(referee.getValue().getFirstName());
                lastName.setText(referee.getValue().getLastName());
                email.setText(referee.getValue().getEmail());
                phone.setText(referee.getValue().getPhone());
            }
            else
            {
                firstNameLabel.setVisible(false);
                lastNameLabel.setVisible(false);
                emailLabel.setVisible(false);
                phoneLabel.setVisible(false);
                firstName.setVisible(false);
                lastName.setVisible(false);
                email.setVisible(false);
                phone.setVisible(false);
                updateButton.setVisible(false);
                clearButton.setVisible(false);
            }
        });

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        updateButton.setOnAction(e -> {
            if(guif.updateReferee(e, referee.getValue(), firstName.getText(), lastName.getText(), email.getText(), phone.getText()))
            {
                referee.getSelectionModel().select(null);
                referee.setItems(null);
                referee.setItems(Referee.refereeList);
            }
        });

        clearButton.setOnAction(e -> referee.getSelectionModel().select(null));

        refereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        firstNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        lastNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        emailLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        phoneLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        clearButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        updateButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(refereeLabel, HPos.RIGHT);
        GridPane.setHalignment(firstNameLabel, HPos.RIGHT);
        GridPane.setHalignment(lastNameLabel, HPos.RIGHT);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);
        GridPane.setHalignment(phoneLabel, HPos.RIGHT);
        GridPane.setHalignment(editRefereeLabel, HPos.CENTER);
        GridPane.setValignment(editRefereeLabel, VPos.CENTER);

        editRefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(editRefereeLabel, 5, 1, 4, 2);
        GridPane.setConstraints(refereeLabel, 4, 3);
        GridPane.setConstraints(referee, 5, 3);
        GridPane.setConstraints(firstNameLabel, 4, 4, 2, 1);
        GridPane.setConstraints(lastNameLabel, 4, 5, 2, 1);
        GridPane.setConstraints(emailLabel, 5, 6);
        GridPane.setConstraints(phoneLabel, 5, 7);
        GridPane.setConstraints(firstName, 6, 4,2, 1);
        GridPane.setConstraints(lastName, 6, 5, 2, 1);
        GridPane.setConstraints(email, 6, 6,2, 1);
        GridPane.setConstraints(phone, 6, 7, 2, 1);
        GridPane.setConstraints(updateButton, 8, 8);
        GridPane.setConstraints(clearButton, 9, 8);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(firstName, firstNameLabel,
                lastNameLabel, lastName, emailLabel, email,
                phoneLabel, phone, editRefereeLabel, updateButton,
                clearButton, referee, refereeLabel);

        //Container
        BorderPane editReferees = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        editReferees.setPrefSize(640,480);

        CommonGUI.panes.add(editReferees);

        return editReferees;
    }
}
