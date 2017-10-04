package nefra.jfx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nefra.db.DBFunctions;
import org.jetbrains.annotations.Contract;

public class Main extends Application
{
    private static Main instance;
    private Stage stage = new Stage();

    public Main() {
        instance = this;
    }

    @Contract(pure = true)
    static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage s) throws Exception {
        MainMenu mm = new MainMenu();
        DBFunctions dbFunctions = new DBFunctions();
        //Settings settings = new Settings();

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

        dbFunctions.getTables();
        dbFunctions.getColumns();
        nefra.settings.Settings.initSettings();

        stage.setOnCloseRequest(e -> nefra.misc.Exit.getInstance().exit(e));
    }

    void changeScene(Scene scene) {
        stage.setScene(scene);
    }
}