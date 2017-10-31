package nefra.jfx.referee;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nefra.jfx.CommonGUI;
import nefra.referee.GUIFunctions;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class EditRefereeGUI {
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
        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label phoneLabel = new Label("Phone: ");
        Label createRefereeLabel = new Label("CREATE REFEREE");
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        //CheckBox isEmail = new CheckBox("Please tick if ONLY email is entered.");
        Button enterButton = new Button("Enter");

        //isEmail.setIndeterminate(false);

        /*
         * Set the action for the enter button based on what information was entered into the fields.
         */
        enterButton.setOnAction(e -> {
            System.out.println("FN: "+ firstName.getText());
            System.out.println("LN: "+ lastName.getText());
            System.out.println("EM: "+ email.getText());
            System.out.println("PH: "+ phone.getText());

            if(isNotEmpty(email.getText()) && isNotEmpty(phone.getText()))
                guif.makeReferee(e, firstName.getText(), lastName.getText(), email.getText(), phone.getText());
            else if(isNotEmpty(email.getText()) && isEmpty(phone.getText()))
                guif.makeReferee(e, firstName.getText(), lastName.getText(), email.getText(), true);
            else if(isEmpty(email.getText()) && isNotEmpty(phone.getText()))
                guif.makeReferee(e, firstName.getText(), lastName.getText(), phone.getText(), false);
            else guif.makeReferee(e, firstName.getText(), lastName.getText());
        });

        firstNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        lastNameLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        emailLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        phoneLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");
        //isEmail.setStyle("-fx-font-weight: bold;" +
        //    "-fx-font-size: 16px;");
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
        GridPane.setConstraints(firstNameLabel, 4, 3, 2, 1);
        GridPane.setConstraints(lastNameLabel, 4, 4, 2, 1);
        GridPane.setConstraints(emailLabel, 5, 5);
        GridPane.setConstraints(phoneLabel, 5, 6);
        GridPane.setConstraints(firstName, 6, 3,2, 1);
        GridPane.setConstraints(lastName, 6, 4, 2, 1);
        GridPane.setConstraints(email, 6, 5,2, 1);
        GridPane.setConstraints(phone, 6, 6, 2, 1);
        GridPane.setConstraints(enterButton, 8, 7);

        CommonGUI.getInstance().makeRowsAndCols(centre);

        centre.getChildren().addAll(firstName, firstNameLabel,
                lastNameLabel, lastName, emailLabel, email,
                phoneLabel, phone, createRefereeLabel, enterButton);

        //BackButton
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> CommonGUI.getInstance().back(e));
        backButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");

        //Container
        BorderPane referees = new BorderPane(centre, menu, null, backButton, null);
        referees.setPrefSize(640,480);

        CommonGUI.panes.add(referees);

        return referees;
    }
}
