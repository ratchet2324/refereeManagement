package nefra.jfx.mainmenu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import nefra.club.Club;
import nefra.game.Division;
import nefra.game.Game;
import nefra.jfx.CommonGUI;
import nefra.jfx.Main;
import nefra.jfx.club.CreateClubGUI;
import nefra.jfx.club.EditClubGUI;
import nefra.jfx.club.ViewClubGUI;
import nefra.jfx.game.*;
import nefra.jfx.misc.NewSetup;
import nefra.jfx.referee.CreateRefereeGUI;
import nefra.jfx.referee.EditRefereeGUI;
import nefra.jfx.referee.ViewRefereeGUI;
import nefra.referee.Referee;
import nefra.settings.Settings;

public class MainMenu {

    /**
     * Creates the GUI for the main menu, and sets it up with its own features.
     * It uses the CommonGUI for the menus and also to allow the backToMainMenu button function.
     *
     * @return the root BorderPane
     */
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Right
        Button addRef = new Button("Add Referee");
        addRef.setAlignment(Pos.TOP_CENTER);
        addRef.setContentDisplay(ContentDisplay.CENTER);
        addRef.setMnemonicParsing(false);
        addRef.setTextAlignment(TextAlignment.CENTER);
        addRef.setWrapText(true);
        addRef.setStyle("-fx-font-size: 34.0");
        addRef.setPrefSize(200,125);
        addRef.setLayoutY(250);

        addRef.setOnAction(e -> {
            e.consume();
            CreateRefereeGUI rGUI = new CreateRefereeGUI();
            CommonGUI.panes.add(rGUI.initGUI());
            Main.getInstance().changeScene(new Scene(rGUI.initGUI()));
        });

        Button addClub = new Button("Add Club");
        addClub.setAlignment(Pos.TOP_CENTER);
        addClub.setContentDisplay(ContentDisplay.CENTER);
        addClub.setMnemonicParsing(false);
        addClub.setTextAlignment(TextAlignment.CENTER);
        addClub.setWrapText(true);
        addClub.setStyle("-fx-font-size: 34.0");
        addClub.setPrefSize(200,125);
        addClub.setLayoutY(250);

        addClub.setOnAction(e -> {
            e.consume();
            CreateClubGUI cGUI = new CreateClubGUI();
            CommonGUI.panes.add(cGUI.initGUI());
            Main.getInstance().changeScene(new Scene(cGUI.initGUI()));
        });

        Button addDiv = new Button("Add Division");
        addDiv.setAlignment(Pos.TOP_CENTER);
        addDiv.setContentDisplay(ContentDisplay.CENTER);
        addDiv.setMnemonicParsing(false);
        addDiv.setTextAlignment(TextAlignment.CENTER);
        addDiv.setWrapText(true);
        addDiv.setStyle("-fx-font-size: 34.0");
        addDiv.setPrefSize(200,125);
        addDiv.setLayoutY(250);

        addDiv.setOnAction(e -> {
            e.consume();
            CreateDivisionGUI dGUI = new CreateDivisionGUI();
            CommonGUI.panes.add(dGUI.initGUI());
            Main.getInstance().changeScene(new Scene(dGUI.initGUI()));
        });

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
            CreateGameGUI gGUI = new CreateGameGUI();
            CommonGUI.panes.add(gGUI.initGUI());
            Main.getInstance().changeScene(new Scene(gGUI.initGUI()));
        });


        Button editRef = new Button("Edit Referee");
        editRef.setAlignment(Pos.TOP_CENTER);
        editRef.setContentDisplay(ContentDisplay.CENTER);
        editRef.setMnemonicParsing(false);
        editRef.setTextAlignment(TextAlignment.CENTER);
        editRef.setWrapText(true);
        editRef.setStyle("-fx-font-size: 34.0");
        editRef.setPrefSize(200,125);
        editRef.setLayoutY(250);

        editRef.setOnAction(e -> {
            e.consume();
            EditRefereeGUI rGUI = new EditRefereeGUI();
            CommonGUI.panes.add(rGUI.initGUI());
            Main.getInstance().changeScene(new Scene(rGUI.initGUI()));
        });

        Button editClub = new Button("Edit Club");
        editClub.setAlignment(Pos.TOP_CENTER);
        editClub.setContentDisplay(ContentDisplay.CENTER);
        editClub.setMnemonicParsing(false);
        editClub.setTextAlignment(TextAlignment.CENTER);
        editClub.setWrapText(true);
        editClub.setStyle("-fx-font-size: 34.0");
        editClub.setPrefSize(200,125);
        editClub.setLayoutY(250);

        editClub.setOnAction(e -> {
            e.consume();
            EditClubGUI cGUI = new EditClubGUI();
            CommonGUI.panes.add(cGUI.initGUI());
            Main.getInstance().changeScene(new Scene(cGUI.initGUI()));
        });

        Button editDiv = new Button("Edit Division");
        editDiv.setAlignment(Pos.TOP_CENTER);
        editDiv.setContentDisplay(ContentDisplay.CENTER);
        editDiv.setMnemonicParsing(false);
        editDiv.setTextAlignment(TextAlignment.CENTER);
        editDiv.setWrapText(true);
        editDiv.setStyle("-fx-font-size: 34.0");
        editDiv.setPrefSize(200,125);
        editDiv.setLayoutY(250);

        editDiv.setOnAction(e -> {
            e.consume();
            EditDivisionGUI dGUI = new EditDivisionGUI();
            CommonGUI.panes.add(dGUI.initGUI());
            Main.getInstance().changeScene(new Scene(dGUI.initGUI()));
        });

        Button editGame = new Button("Edit Game");
        editGame.setAlignment(Pos.TOP_CENTER);
        editGame.setContentDisplay(ContentDisplay.CENTER);
        editGame.setMnemonicParsing(false);
        editGame.setTextAlignment(TextAlignment.CENTER);
        editGame.setWrapText(true);
        editGame.setStyle("-fx-font-size: 34.0");
        editGame.setPrefSize(200,125);
        editGame.setLayoutY(250);

        editGame.setOnAction(e -> {
            e.consume();
            EditGameGUI gGUI = new EditGameGUI();
            CommonGUI.panes.add(gGUI.initGUI());
            Main.getInstance().changeScene(new Scene(gGUI.initGUI()));
        });


        Button viewRef = new Button("View Referees");
        viewRef.setAlignment(Pos.TOP_CENTER);
        viewRef.setContentDisplay(ContentDisplay.CENTER);
        viewRef.setMnemonicParsing(false);
        viewRef.setTextAlignment(TextAlignment.CENTER);
        viewRef.setWrapText(true);
        viewRef.setStyle("-fx-font-size: 34.0");
        viewRef.setPrefSize(200,125);

        viewRef.setOnAction(e -> {
            e.consume();
            ViewRefereeGUI vGUI = new ViewRefereeGUI();
            CommonGUI.panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });


        Button viewClubs = new Button("View Clubs");
        viewClubs.setAlignment(Pos.TOP_CENTER);
        viewClubs.setContentDisplay(ContentDisplay.CENTER);
        viewClubs.setMnemonicParsing(false);
        viewClubs.setTextAlignment(TextAlignment.CENTER);
        viewClubs.setWrapText(true);
        viewClubs.setStyle("-fx-font-size: 34.0");
        viewClubs.setPrefSize(200,125);
        viewClubs.setLayoutY(125);

        viewClubs.setOnAction(e -> {
            e.consume();
            ViewClubGUI vGUI = new ViewClubGUI();
            CommonGUI.panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });

        Button viewDiv = new Button("View Divisions");
        viewDiv.setAlignment(Pos.TOP_CENTER);
        viewDiv.setContentDisplay(ContentDisplay.CENTER);
        viewDiv.setMnemonicParsing(false);
        viewDiv.setTextAlignment(TextAlignment.CENTER);
        viewDiv.setWrapText(true);
        viewDiv.setStyle("-fx-font-size: 34.0");
        viewDiv.setPrefSize(200,125);

        viewDiv.setOnAction(e -> {
            e.consume();
            ViewDivisionGUI vGUI = new ViewDivisionGUI();
            CommonGUI.panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });


        Button viewGame = new Button("View Games");
        viewGame.setAlignment(Pos.TOP_CENTER);
        viewGame.setContentDisplay(ContentDisplay.CENTER);
        viewGame.setMnemonicParsing(false);
        viewGame.setTextAlignment(TextAlignment.CENTER);
        viewGame.setWrapText(true);
        viewGame.setStyle("-fx-font-size: 34.0");
        viewGame.setPrefSize(200,125);
        viewGame.setLayoutY(125);

        viewGame.setOnAction(e -> {
            e.consume();
            ViewGameGUI vGUI = new ViewGameGUI();
            CommonGUI.panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });


        GridPane right = new GridPane();
        right.setVgap(10);
        right.setHgap(10);
        right.setMaxWidth(Region.USE_PREF_SIZE);
        right.setAlignment(Pos.CENTER);
        right.setPrefSize((200*3) + (10*3),(125*4) + (4*10));
        //Ref
        right.add(addRef, 0,0);
        right.add(editRef, 1,0);
        right.add(viewRef, 2,0);
        //Club
        right.add(addClub, 0,1);
        right.add(editClub, 1,1);
        right.add(viewClubs, 2,1);
        ///Div
        right.add(addDiv, 0,2);
        right.add(editDiv, 1,2);
        right.add(viewDiv, 2,2);
        //Game
        right.add(addGame, 0,3);
        right.add(editGame, 1,3);
        right.add(viewGame, 2,3);


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
