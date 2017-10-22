package nefra.jfx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nefra.club.Club;
import nefra.db.DBFunctions;
import nefra.db.dbf_rewrite;
import nefra.game.Division;
import nefra.game.Game;
import nefra.jfx.mainmenu.MainMenu;
import nefra.referee.Referee;
import nefra.settings.Settings;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public class Main extends Application
{
    /**
     * Provide an instance to access non-static parts from a static instance.
     */
    private static Main instance;
    private Stage stage = new Stage();

    /**
     * constructor to make the instance
     */
    public Main() { instance = this; }

    /**
     * Make an instance to use both static and non-static methods.
     *
     * @return a static instance to also access non-static methods.
     */
    @Contract(pure = true)
    public static Main getInstance() { return instance; }

    @Override
    public void start(Stage s) throws Exception {
        MainMenu mm = new MainMenu();
        Settings.initSettings();
        System.out.println(Settings.getSetting("DatabaseInstantiation"));
        loadAll();

        //Get screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        BorderPane init = mm.initGUI();

        init.setMaxHeight(screen.getHeight());
        init.setMaxWidth(screen.getWidth());

        //Set stage to screen size
        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());


        //Set title and default scene.
        stage.setTitle("New England Football Referees Association");
        stage.setScene(new Scene(init));
        stage.show();


        stage.setOnCloseRequest(e -> nefra.misc.Exit.getInstance().exit(e));
    }

    /**
     * Change the scene to the called one.
     * @param scene The scene to change to.
     */
    public void changeScene(Scene scene) { stage.setScene(scene); }

    /**
     * Change the scene to the called one via its root BorderPane.
     *
     * @param root The BorderPane of the scene to change to.
     */
    void changeScene(BorderPane root) { stage.setScene(new Scene(root));}

    private void loadAll()
    {
        dbf_rewrite dbFunctions = new dbf_rewrite();

        dbFunctions.printDatabase();
        dbFunctions.loadDatabase();

        for(Referee r : Referee.refereeList)
            r.displayInfo();
        for(Club c : Club.clubList)
            c.displayInfo();
        for(Division d : Division.divisionList)
            d.displayInfo();
        for(Game g : Game.gameList)
            System.out.println(g.toString());

    }

}