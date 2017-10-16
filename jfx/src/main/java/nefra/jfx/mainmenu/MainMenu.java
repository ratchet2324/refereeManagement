package nefra.jfx.mainmenu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import nefra.jfx.CommonGUI;
import nefra.jfx.Main;
import nefra.jfx.game.CreateGameGUI;

public class MainMenu {

    /**
     * Creates the GUI for the main menu, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the back button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Right
        Button viewRef = new Button("View Referees");
        viewRef.setAlignment(Pos.TOP_CENTER);
        viewRef.setContentDisplay(ContentDisplay.CENTER);
        viewRef.setMnemonicParsing(false);
        viewRef.setTextAlignment(TextAlignment.CENTER);
        viewRef.setWrapText(true);
        viewRef.setStyle("-fx-font-size: 34.0");
        viewRef.setPrefSize(200,125);


        Button viewClubs = new Button("View Clubs");
        viewClubs.setAlignment(Pos.TOP_CENTER);
        viewClubs.setContentDisplay(ContentDisplay.CENTER);
        viewClubs.setMnemonicParsing(false);
        viewClubs.setTextAlignment(TextAlignment.CENTER);
        viewClubs.setWrapText(true);
        viewClubs.setStyle("-fx-font-size: 34.0");
        viewClubs.setPrefSize(200,125);
        viewClubs.setLayoutY(125);

        Button addGame = new Button("Add Game");
        addGame.setAlignment(Pos.TOP_CENTER);
        addGame.setContentDisplay(ContentDisplay.CENTER);
        addGame.setMnemonicParsing(false);
        addGame.setTextAlignment(TextAlignment.CENTER);
        addGame.setWrapText(true);
        addGame.setStyle("-fx-font-size: 34.0");
        addGame.setPrefSize(200,125);
        addGame.setLayoutY(250);

        addGame.setOnAction(e -> {
            e.consume();
            CreateGameGUI cgGUI = new CreateGameGUI();
            CommonGUI.panes.add(cgGUI.initGUI());
            Main.getInstance().changeScene(new Scene(cgGUI.initGUI()));
        });

        VBox right = new VBox(viewRef, viewClubs, addGame);
        right.setSpacing(10);
        right.setMaxWidth(Region.USE_PREF_SIZE);
        right.setAlignment(Pos.CENTER);
        right.setPrefSize(200,375);

        //Centre
        Label welcome = new Label("Welcome");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setContentDisplay(ContentDisplay.CENTER);
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setWrapText(true);
        welcome.setStyle("-fx-font-size: 59.0");
        welcome.setPrefWidth(400);
        welcome.setLayoutY(250);

        StackPane centre = new StackPane(welcome);
        StackPane.setAlignment(welcome, Pos.CENTER);
        centre.setPrefSize(400,800);

        BorderPane mainMenu = new BorderPane(centre, menu, right, null, null);
        mainMenu.setPrefSize(600, 768);
        CommonGUI.panes.add(mainMenu);
        return mainMenu;
    }
}
