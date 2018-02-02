package nefra.jfx.help;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelpGUI {
    private TextArea centre = new TextArea();

    public void initGUI()
    {
        final Accordion topics = new Accordion();
        centre.setEditable(false);

        centre.setText("Welcome to the help section.\n" +
                "Please use the categories to the left to find the topic you are looking for.\n" +
                "If you have any other issues, please see the about section for contact details.");

        VBox refBox = new VBox();
        refBox.setMinWidth(200);
        refBox.setMaxWidth(200);
        refBox.setPrefWidth(200);
        addRefTopics(refBox);
        TitledPane refTop = new TitledPane("Referee", refBox);

        VBox cluBox = new VBox();
        cluBox.setMinWidth(200);
        cluBox.setMaxWidth(200);
        cluBox.setPrefWidth(200);
        addCluTopics(cluBox);
        TitledPane cluTop = new TitledPane("Club", cluBox);

        VBox divBox = new VBox();
        divBox.setMinWidth(200);
        divBox.setMaxWidth(200);
        divBox.setPrefWidth(200);
        addDivTopics(divBox);
        TitledPane divTop = new TitledPane("Division", divBox);

        VBox gamBox = new VBox();
        gamBox.setMinWidth(200);
        gamBox.setMaxWidth(200);
        gamBox.setPrefWidth(200);
        addGamTopics(gamBox);
        TitledPane gamTop = new TitledPane("Game", gamBox);

        topics.getPanes().addAll(refTop, cluTop, divTop, gamTop);
        VBox left = new VBox(topics);
        left.setMinWidth(200);

        Button close = new Button("Close");

        BorderPane root = new BorderPane(centre, null, null, close, left);
        Stage stage = new Stage();
        close.setOnAction(e-> stage.close());
        stage.setMinHeight(800);
        stage.setMinWidth(800);
        stage.setScene(new Scene(root));
        stage.setTitle("Help");
        stage.show();
    }

    private void addRefTopics(VBox vb)
    {
        Button add = new Button("How to add a referee");
        add.setPrefWidth(vb.getMinWidth());
        add.setOnAction(e-> centre.setText("On the Main Menu click on \"Add Referee\"\n" +
                "The referee's first and last name are required at minimum.\n" +
                "Click on \"Enter\" to create the referee."));

        Button edit = new Button("How to edit a referee");
        edit.setPrefWidth(vb.getMinWidth());
        edit.setOnAction(e-> centre.setText("On the Main Menu click on \"Edit Referee\"\n" +
                "Select the desired referee to edit.\n" +
                "Change the required details.\n" +
                "Click on update."));

        Button remove = new Button("How to remove a referee");
        remove.setPrefWidth(vb.getMinWidth());
        remove.setOnAction(e-> centre.setText("On the Main Menu click on \"View Referees\"\n" +
                "Select the desired referee to remove.\n" +
                "Click on remove."));

        vb.setSpacing(2);
        vb.getChildren().addAll(add, edit, remove);
    }

    private void addCluTopics(VBox vb)
    {
        Button add = new Button("How to add a club");
        add.setPrefWidth(vb.getMinWidth());
        add.setOnAction(e-> centre.setText("On the Main Menu click on \"Add Club\"\n" +
                "The club's name is required at minimum.\n" +
                "Click on \"Enter\" to create the club."));

        Button edit = new Button("How to edit a club");
        edit.setPrefWidth(vb.getMinWidth());
        edit.setOnAction(e-> centre.setText("On the Main Menu click on \"Edit Club\"\n" +
                "Select the desired club to edit.\n" +
                "Change the required details.\n" +
                "Click on update."));

        Button remove = new Button("How to remove a club");
        remove.setPrefWidth(vb.getMinWidth());
        remove.setOnAction(e-> centre.setText("On the Main Menu click on \"View Clubs\"\n" +
                "Select the desired club to remove.\n" +
                "Click on remove."));

        vb.setSpacing(2);
        vb.getChildren().addAll(add, edit);
    }

    private void addDivTopics(VBox vb)
    {
        Button add = new Button("How to add a division");
        add.setPrefWidth(vb.getMinWidth());
        add.setOnAction(e-> centre.setText("On the Main Menu click on \"Add Division\"\n" +
                "All fields are required to be entered.\n" +
                "Click on \"Enter\" to create the division."));

        Button edit = new Button("How to edit a division");
        edit.setPrefWidth(vb.getMinWidth());
        edit.setOnAction(e-> centre.setText("On the Main Menu click on \"Edit Division\"\n" +
                "Select the desired division to edit.\n" +
                "Change the required details.\n" +
                "Click on update."));

        Button remove = new Button("How to remove a division");
        remove.setPrefWidth(vb.getMinWidth());
        remove.setOnAction(e-> centre.setText("On the Main Menu click on \"View Divisions\"\n" +
                "Select the desired division to remove.\n" +
                "Click on remove."));

        vb.setSpacing(2);
        vb.getChildren().addAll(add, edit, remove);
    }

    private void addGamTopics(VBox vb)
    {
        Button add = new Button("How to add a game");
        add.setPrefWidth(vb.getMinWidth());
        add.setOnAction(e-> centre.setText("On the Main Menu click on \"Add Game\"\n" +
                "All fields are required to be entered.\n" +
                "Click on \"Enter\" to create the game."));

        Button edit = new Button("How to edit a game");
        edit.setPrefWidth(vb.getMinWidth());
        edit.setOnAction(e-> centre.setText("On the Main Menu click on \"Edit Game\"\n" +
                "Select the desired game to edit.\n" +
                "Change the required details.\n" +
                "Click on update."));

        Button remove = new Button("How to remove a game");
        remove.setPrefWidth(vb.getMinWidth());
        remove.setOnAction(e-> centre.setText("On the Main Menu click on \"View Games\"\n" +
                "Select the desired game to remove.\n" +
                "Click on remove."));

        vb.setSpacing(2);
        vb.getChildren().addAll(add, edit, remove);
    }
}
