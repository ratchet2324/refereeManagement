package nefra.jfx;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import nefra.settings.SettingsGUI;

import java.util.ArrayList;

class CommonGUI {

    private static CommonGUI instance = new CommonGUI();
    static ArrayList<BorderPane> panes = new ArrayList<>();

    private CommonGUI() { instance = this; }

    static CommonGUI getInstance() { return instance; }

    MenuBar loadMenu()
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

        newRef.setOnAction(e -> {
            e.consume();
            RefereeGUI rGUI = new RefereeGUI();
            panes.add(rGUI.initGUI());
            Main.getInstance().changeScene(new Scene(rGUI.initGUI()));
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

    private void loadSettings(ActionEvent e)
    {
        e.consume();
        SettingsGUI sGUI = new SettingsGUI();
        panes.add(sGUI.initGUI());

        sGUI.back.setOnAction(this::back);
        Main.getInstance().changeScene(new Scene(sGUI.initGUI()));
    }

    void back(ActionEvent event)
    {
        {
            MainMenu mm = new MainMenu();
            event.consume();
            Main.getInstance().changeScene(new Scene(mm.initGUI()));
            panes.remove(panes.size() - 1);
        }
    }
}
