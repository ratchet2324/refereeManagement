package nefra.jfx;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import nefra.settings.SettingsGUI;

import java.util.ArrayList;

public class MainMenu {

    private ArrayList<BorderPane> panes = new ArrayList<>();

    public BorderPane initGUI()
    {
        //Top
        MenuBar menu = loadMenu();

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
        panes.add(mainMenu);
        return mainMenu;
    }

    private MenuBar loadMenu()
    {
        Menu file = new Menu("File");
        MenuItem settings = new MenuItem("Settings");
        settings.setOnAction(this::loadSettings);
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> nefra.misc.Exit.getInstance().exit(e));
        file.getItems().addAll(settings, new SeparatorMenuItem(),exit);

        Menu referee = new Menu("Referee");
        MenuItem newRef = new MenuItem("New Referee");
        MenuItem editRef = new MenuItem("Edit Referee");
        MenuItem viewRef = new MenuItem("View Referees");
        referee.getItems().addAll(newRef, editRef, viewRef);

        Menu club = new Menu("Club");
        MenuItem newClub = new MenuItem("New Club");
        MenuItem editClub = new MenuItem("Edit Club");
        MenuItem viewClub = new MenuItem("View Clubs");
        club.getItems().addAll(newClub, editClub, viewClub);

        Menu games = new Menu("Games");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem editGame = new MenuItem("Edit Game");
        MenuItem viewGame = new MenuItem("View Games");
        games.getItems().addAll(newGame, editGame, viewGame);

        return new MenuBar(file, referee, club, games);
    }

    private void loadSettings(ActionEvent e)
    {
        e.consume();
        SettingsGUI sGUI = new SettingsGUI();
        panes.add(sGUI.initGUI());

        sGUI.back.setOnAction(event -> {
            MainMenu mm = new MainMenu();
            event.consume();
            Main.getInstance().changeScene(new Scene(mm.initGUI()));
            panes.remove(panes.size() - 1);
        });
        Main.getInstance().changeScene(new Scene(sGUI.initGUI()));
    }
}
