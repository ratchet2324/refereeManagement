package nefra.jfx;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

/**
 * A simple class to hold all the common gui components such as the menu, back button functionality
 * and the array of scenes to track the scene to go back to.
 */
class CommonGUI {

    /**
     * Track scenes to allow changing back to the previous scene.
     */
    static ArrayList<BorderPane> panes = new ArrayList<>();
    /**
     * Allows static and non-static methods to be called statically.
     */
    private static CommonGUI instance = new CommonGUI();

    private CommonGUI() { instance = this; }

    static CommonGUI getInstance() { return instance; }

    /**
     * Menu bar at the top of the scene, this is so that it remains consistent across the different scenes
     * so that it remains familiar.
     *
     * @return The menubar containing the items.
     */
    MenuBar loadMenu() {
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

        newRef.setOnAction(e -> {
            e.consume();
            CreateRefereeGUI crGUI = new CreateRefereeGUI();
            panes.add(crGUI.initGUI());
            Main.getInstance().changeScene(new Scene(crGUI.initGUI()));
        });


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

    /**
     * Load the settings page.
     *
     * @param event the event
     */
    private void loadSettings(ActionEvent event) {
        event.consume();
        SettingsGUI sGUI = new SettingsGUI();
        Main.getInstance().changeScene(sGUI.initGUI());
    }

    /**
     * functionality for the back button so it can be easily debugged and extended.
     * @param event the event
     */
    void back(ActionEvent event) {
        {
            MainMenu mm = new MainMenu();
            event.consume();
            Main.getInstance().changeScene(new Scene(mm.initGUI()));
            panes.remove(panes.size() - 1);
        }
    }
}
