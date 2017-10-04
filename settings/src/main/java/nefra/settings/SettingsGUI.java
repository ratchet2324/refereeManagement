package nefra.settings;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SettingsGUI {
    public Button back = new Button("Back");

    public BorderPane initGUI() {
        //Top
        MenuBar menu = loadMenu();

        //Bottom
        HBox bottom = new HBox();
        bottom.getChildren().addAll(back);

        BorderPane settingsGUI = new BorderPane(null, menu, null, bottom, null);
        settingsGUI.setPrefSize(600, 768);
        return settingsGUI;
    }

    private MenuBar loadMenu() {
        Menu file = new Menu("File");
        MenuItem settings = new MenuItem("Settings");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> nefra.misc.Exit.getInstance().exit(e));
        file.getItems().addAll(settings, new SeparatorMenuItem(), exit);

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
}
