package nefra.jfx;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import nefra.referee.GUIFunctions;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class RefereeGUI {
    private GUIFunctions guif = new GUIFunctions();

    public BorderPane initGUI()
    {
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
        CheckBox isEmail = new CheckBox("Please tick if ONLY email is entered.");
        Button enterButton = new Button("Enter");

        isEmail.setIndeterminate(false);

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
        isEmail.setStyle("-fx-font-weight: bold;" +
            "-fx-font-size: 16px;");
        enterButton.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 16px;");
        GridPane.setHalignment(firstNameLabel, HPos.RIGHT);
        GridPane.setHalignment(emailLabel, HPos.RIGHT);
        GridPane.setHalignment(phoneLabel, HPos.RIGHT);

        createRefereeLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 36px;");


        GridPane.setConstraints(createRefereeLabel, 5, 1, 7, 2);
        GridPane.setConstraints(firstNameLabel, 2, 3, 2, 1);
        GridPane.setConstraints(lastNameLabel, 3, 4);
        GridPane.setConstraints(emailLabel, 3, 5);
        GridPane.setConstraints(phoneLabel, 3, 6);
        GridPane.setConstraints(firstName, 4, 3,2, 1);
        GridPane.setConstraints(lastName, 4, 4, 2, 1);
        GridPane.setConstraints(email, 4, 5,2, 1);
        GridPane.setConstraints(phone, 4, 6,2, 1);
        GridPane.setConstraints(isEmail, 6, 5, 3, 1);
        GridPane.setConstraints(enterButton, 6, 7);
        GridPane.setMargin(isEmail, new Insets(0,0,0,10));

        final int col = 12 ;
        final int row = 12 ;
        for (int i = 0; i < col; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setMinWidth(10);
            colConst.setPrefWidth(100);
            colConst.setHgrow(Priority.SOMETIMES);
            centre.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < row; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(10);
            rowConst.setPrefHeight(30);
            rowConst.setVgrow(Priority.SOMETIMES);
            centre.getRowConstraints().add(rowConst);
        }

        centre.getChildren().addAll(isEmail, firstName,firstNameLabel,
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
