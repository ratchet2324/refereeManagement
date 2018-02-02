package nefra.jfx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nefra.club.Club;
import nefra.db.SysCreator;
import nefra.db.SysLoader;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.jfx.mainmenu.MainMenu;
import nefra.jfx.misc.NewSetup;
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
    private final Stage stage = new Stage();
    static int exitCode = 0;
    public NewSetup ns;

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
    Stage getStage() { return stage; }

    @Override
    public void start(Stage s) {
        MainMenu mm = new MainMenu();
        ns = new NewSetup();
        Settings.initSettings();
        if(Settings.getSetting("InitialisationNeeded").equals("true"))
        {
            SysCreator.getInstance().Setup();
            Settings.writeSetting("InitialisationNeeded", "false");
        }
        SysLoader.getInstance();

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
        Start();
        stage.setOnCloseRequest(e -> exitCode = nefra.misc.Exit.getInstance().exit(e));
    }

    public void Start()
    {
        if(Settings.getSetting("FirstRun").equals("true"))
            ns.FirstRun();
        if (Referee.refereeList.size() == 0)
            if(ns.referee()) return;
        if (Club.clubList.size() == 0)
            if(ns.club()) return;
        if (Division.divisionList.size() == 0)
            if(ns.division()) return;
        if (Game.gameList.size() == 0)
            ns.game();
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

    public static void main(String args[])
    {
        for(String s : args)
        {
            DelLog.getInstance().Log("Arg: " + s);
            if(StringUtils.trim(s).equalsIgnoreCase( "debug")) Debug.setDebugMode(true);
        }
        if (Debug.debugMode) loadDebug();
        launch(args);
        System.exit(exitCode);
    }

    private static void loadDebug() { Debug.debugInfo(); }
}