package nefra.jfx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nefra.club.Club;
import nefra.db.DBFunctions;
import nefra.game.Division;
import nefra.game.Game;
import nefra.jfx.mainmenu.MainMenu;
import nefra.misc.Debug;
import nefra.referee.Referee;
import nefra.settings.Settings;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;

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

    @Contract(pure = true)
    public Stage getStage() { return stage; }

    @Override
    public void start(Stage s) throws Exception {
        MainMenu mm = new MainMenu();
        Settings.initSettings();
        if (Debug.debugMode)
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
        String title = "New England Football Referees Association" + (Debug.debugMode ? " **DEBUG**" : "");
        stage.setTitle(title);
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
        DBFunctions dbFunctions = new DBFunctions();

        dbFunctions.loadDatabase();

        if(Debug.debugMode)
        {
            dbFunctions.printDatabase();
            for (Referee r : Referee.refereeList)
                r.displayInfo();
            for (Club c : Club.clubList)
                c.displayInfo();
            for (Division d : Division.divisionList)
                d.displayInfo();
            for (Game g : Game.gameList)
                System.out.println(g.displayInfo());
        }
    }

    public static void main(String args[])
    {
        for(String s : args)
        {
            System.out.println("Arg: " + s);
            if(StringUtils.trim(s).equalsIgnoreCase( "debug")) Debug.setDebugMode(true);
        }
        if (Debug.debugMode) loadDebug();
        launch(args);
    }

    private static void loadDebug() { Debug.debugInfo(); }
}