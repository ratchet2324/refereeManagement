package nefra.jfx.referee;

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

public class CreateRefereeGUI {
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
        Label refereeWarning = new Label("First & Last name required");
        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label phoneLabel = new Label("Phone: ");
        Label createRefereeLabel = new Label("CREATE REFEREE");
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        Button enterButton = new Button("Enter");

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            if(firstName.getText().isEmpty() || lastName.getText().isEmpty())
                guif.displayErrorReferee(e);
            else
            {
                if (Debug.debugMode)
                    DelLog.getInstance().Log("FN: " + firstName.getText() + "\n" +
                            "LN: " + lastName.getText() + "\n" +
                            "EM: " + email.getText() + "\n" +
                            "PH: " + phone.getText());

                if (guif.makeReferee(e, firstName.getText(), lastName.getText(), email.getText(), phone.getText())) {
                    int code = CommonGUI.getInstance().multipleEntry(e, "referee");
                    if (code == 1) {
                        firstName.clear();
                        lastName.clear();
                        email.clear();
                        phone.clear();
                    } else if (code == 0) CommonGUI.getInstance().backToMainMenu(e);
                    else if (code == -240) {
                        DelLog.getInstance().Log(new CannotCreateException("Popup error for create referee"));
                    }
                }
            }
        });

        refereeWarning.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: red;" +
                "-fx-font-size: 20px;");
        firstNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        lastNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        emailLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        phoneLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(firstNameLabel, HPos.RIGHT);
        GridPane.setHalignment(lastNameLabel, HPos.RIGHT);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);
        GridPane.setHalignment(phoneLabel, HPos.RIGHT);
        GridPane.setHalignment(createRefereeLabel, HPos.CENTER);
        GridPane.setValignment(createRefereeLabel, VPos.CENTER);

        createRefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(createRefereeLabel, 5, 1, 4, 2);
        GridPane.setConstraints(refereeWarning, 5, 3, 4, 1);
        GridPane.setConstraints(firstNameLabel, 4, 4, 2, 1);
        GridPane.setConstraints(lastNameLabel, 4, 5, 2, 1);
        GridPane.setConstraints(emailLabel, 5, 6);
        GridPane.setConstraints(phoneLabel, 5, 7);
        GridPane.setConstraints(firstName, 6, 4,2, 1);
        GridPane.setConstraints(lastName, 6, 5, 2, 1);
        GridPane.setConstraints(email, 6, 6,2, 1);
        GridPane.setConstraints(phone, 6, 7, 2, 1);
        GridPane.setConstraints(enterButton, 8, 8);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(refereeWarning, firstName, firstNameLabel,
                lastNameLabel, lastName, emailLabel, email,
                phoneLabel, phone, createRefereeLabel, enterButton);

        //Container
        BorderPane createReferees = new BorderPane(centre, menu, null, CommonGUI.getInstance().bottomBox(), null);
        createReferees.setPrefSize(640,480);

        CommonGUI.panes.add(createReferees);

        return createReferees;
    }
}
