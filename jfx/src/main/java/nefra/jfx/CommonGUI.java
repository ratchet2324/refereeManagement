package nefra.jfx;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import nefra.jfx.club.CreateClubGUI;
import nefra.jfx.club.EditClubGUI;
import nefra.jfx.club.ViewClubGUI;
import nefra.jfx.game.*;
import nefra.jfx.mainmenu.MainMenu;
import nefra.jfx.referee.CreateRefereeGUI;
import nefra.jfx.referee.EditRefereeGUI;
import nefra.jfx.referee.ViewRefereeGUI;
import nefra.misc.Debug;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A simple class to hold all the common GUI components such as the menu, back button functionality
 * and the array of scenes to track the scene to go back to.
 */
public class CommonGUI {

    public static boolean singleSetting = false;
    /**
     * Track scenes to allow changing back to the previous scene.
     */
    public static final ArrayList<BorderPane> panes = new ArrayList<>();
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
        if(Debug.debugMode)
        {
            MenuItem settings = new MenuItem("Settings");
            settings.setOnAction(this::loadSettings);
            file.getItems().addAll(settings, new SeparatorMenuItem());
        }
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Main.exitCode = nefra.misc.Exit.getInstance().exit(e));
        file.getItems().add(exit);

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

        editRef.setOnAction(e -> {
            e.consume();
            EditRefereeGUI edGUI = new EditRefereeGUI();
            panes.add(edGUI.initGUI());
            Main.getInstance().changeScene(new Scene(edGUI.initGUI()));
        });

        /*viewRef.setOnAction(e -> {
            e.consume();
            ViewRefereeGUI vGUI = new ViewRefereeGUI();
            panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });*/

        Menu club = new Menu("Club");
        MenuItem newClub = new MenuItem("New Club");
        MenuItem editClub = new MenuItem("Edit Club");
        MenuItem viewClub = new MenuItem("View Clubs");
        club.getItems().addAll(newClub, editClub, viewClub);

        newClub.setOnAction(e -> {
            e.consume();
            CreateClubGUI crGUI = new CreateClubGUI();
            panes.add(crGUI.initGUI());
            Main.getInstance().changeScene(new Scene(crGUI.initGUI()));
        });

        editClub.setOnAction(e -> {
            e.consume();
            EditClubGUI edGUI = new EditClubGUI();
            panes.add(edGUI.initGUI());
            Main.getInstance().changeScene(new Scene(edGUI.initGUI()));
        });

        /*viewClub.setOnAction(e -> {
            e.consume();
            ViewClubGUI vGUI = new ViewClubGUI();
            panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });*/

        Menu game = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem editGame = new MenuItem("Edit Game");
        MenuItem viewGame = new MenuItem("View Games");
        game.getItems().addAll(newGame, editGame, viewGame);

        newGame.setOnAction(e -> {
            e.consume();
            CreateGameGUI crGUI = new CreateGameGUI();
            panes.add(crGUI.initGUI());
            Main.getInstance().changeScene(new Scene(crGUI.initGUI()));
        });

        editGame.setOnAction(e -> {
            e.consume();
            EditGameGUI edGUI = new EditGameGUI();
            panes.add(edGUI.initGUI());
            Main.getInstance().changeScene(new Scene(edGUI.initGUI()));
        });

        /*viewGame.setOnAction(e -> {
            e.consume();
            ViewGameGUI vGUI = new ViewGameGUI();
            panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
        });*/

        Menu division = new Menu("Division");
        MenuItem newDivision = new MenuItem("New Division");
        MenuItem editDivision = new MenuItem("Edit Division");
        MenuItem viewDivision = new MenuItem("View Division");
        division.getItems().addAll(newDivision, editDivision, viewDivision);

        newDivision.setOnAction(e -> {
            e.consume();
            CreateDivisionGUI crGUI = new CreateDivisionGUI();
            panes.add(crGUI.initGUI());
            Main.getInstance().changeScene(new Scene(crGUI.initGUI()));
        });

        editDivision.setOnAction(e -> {
            e.consume();
            EditDivisionGUI edGUI = new EditDivisionGUI();
            panes.add(edGUI.initGUI());
            Main.getInstance().changeScene(new Scene(edGUI.initGUI()));
        });

        viewDivision.setOnAction(e -> {
            e.consume();
            ViewDivisionGUI vGUI = new ViewDivisionGUI();
            panes.add(vGUI.initGUI());
            Main.getInstance().changeScene(new Scene(vGUI.initGUI()));
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
        if(!singleSetting)
        {
            singleSetting = true;
            event.consume();
            SettingsGUI sGUI = new SettingsGUI();
            sGUI.initGUI(Main.getInstance().getStage());
        }
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

    /**
     * Ask if user wants to continue entering data
     * @param event action event to make it available on the GUI
     * @param object the calling name, such as referee or game, used in text field.
     * @since 1.0
     * @return code to indicate response (Yes/No) or an error
     */
    public int multipleEntry(ActionEvent event,String object)
    {
        event.consume();
        Alert entry = new Alert(Alert.AlertType.CONFIRMATION);
        entry.setTitle(null);
        entry.setHeaderText(null);
        entry.setGraphic(null);
        entry.setContentText(String.format("Do you wish to another %s?", object));

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        entry.getDialogPane().getButtonTypes().clear();
        entry.getDialogPane().getButtonTypes().addAll(yes, no);

        Optional<ButtonType> result = entry.showAndWait();
        if (result.isPresent() && result.get() == yes) return 1;
        else if (result.isPresent() && result.get() == no) return 0;
        else return -240;
    }
}
