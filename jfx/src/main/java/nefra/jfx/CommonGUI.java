package nefra.jfx;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.*;
import nefra.jfx.club.CreateClubGUI;
import nefra.jfx.game.CreateDivisionGUI;
import nefra.jfx.game.CreateGameGUI;
import nefra.jfx.mainmenu.MainMenu;
import nefra.jfx.referee.CreateRefereeGUI;

import java.util.ArrayList;

/**
 * A simple class to hold all the common gui components such as the menu, back button functionality
 * and the array of scenes to track the scene to go back to.
 */
public class CommonGUI {

    /**
     * Track scenes to allow changing back to the previous scene.
     */
    public static ArrayList<BorderPane> panes = new ArrayList<>();
    /**
     * Allows static and non-static methods to be called statically.
     */
    private static CommonGUI instance = new CommonGUI();

    private CommonGUI() { instance = this; }

    public static CommonGUI getInstance() { return instance; }

    /**
     * Menu bar at the top of the scene, this is so that it remains consistent across the different scenes
     * so that it remains familiar.
     *
     * @return The menubar containing the items.
     */
    public MenuBar loadMenu() {
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

        newClub.setOnAction(e -> {
            e.consume();
            CreateClubGUI ccGUI = new CreateClubGUI();
            panes.add(ccGUI.initGUI());
            Main.getInstance().changeScene(new Scene(ccGUI.initGUI()));
        });

        Menu game = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem editGame = new MenuItem("Edit Game");
        MenuItem viewGame = new MenuItem("View Games");
        game.getItems().addAll(newGame, editGame, viewGame);

        newGame.setOnAction(e -> {
            e.consume();
            CreateGameGUI cgGUI = new CreateGameGUI();
            panes.add(cgGUI.initGUI());
            Main.getInstance().changeScene(new Scene(cgGUI.initGUI()));
        });

        Menu division = new Menu("Division");
        MenuItem newDivision = new MenuItem("New Division");
        MenuItem editDivision = new MenuItem("Edit Division");
        MenuItem viewDivision = new MenuItem("View Division");
        division.getItems().addAll(newDivision, editDivision, viewDivision);

        newDivision.setOnAction(e -> {
            e.consume();
            CreateDivisionGUI cdGUI = new CreateDivisionGUI();
            panes.add(cdGUI.initGUI());
            Main.getInstance().changeScene(cdGUI.initGUI());
        });

        return new MenuBar(file, referee, club, game, division);
    }

    public void makeRowsAndCols(GridPane gp)
    {
        final int col = 12 ;
        final int row = 12 ;
        for (int i = 0; i < col; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setMinWidth(50);
            colConst.setPrefWidth(100);
            colConst.setMaxWidth(100);
            colConst.setHgrow(Priority.SOMETIMES);
            gp.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < row; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(30);
            rowConst.setPrefHeight(30);
            rowConst.setMaxHeight(50);
            rowConst.setVgrow(Priority.SOMETIMES);
            gp.getRowConstraints().add(rowConst);
        }
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
    public void back(ActionEvent event) {
        {
            MainMenu mm = new MainMenu();
            event.consume();
            Main.getInstance().changeScene(new Scene(mm.initGUI()));
            panes.remove(panes.size() - 1);
        }
    }
}
