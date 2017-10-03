package nefra.jfx;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nefra.db.DBConnect;
import nefra.settings.Settings;

import java.util.Optional;


public class Main extends Application
{
    public void start(Stage stage) throws Exception
    {
        MainMenu mm = new MainMenu();
        DBConnect db = new DBConnect();
        Settings settings = new Settings();

        //Get screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        // Load FXML and set it to the scene
        BorderPane root = mm.initGUI();

        root.setMaxHeight(screen.getHeight());
        root.setMaxWidth(screen.getWidth());

        //Set stage to screen size
        stage.setX(screen.getMinX());
        stage.setY(screen.getMinY());
        stage.setWidth(screen.getWidth());
        stage.setHeight(screen.getHeight());


        //Set title and default scene.
        stage.setTitle("New England Football Referees Association");
        stage.setScene(new Scene(root));
        stage.show();

        db.dbConnection();
        settings.initSettings();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // consume event
                event.consume();

                // show close dialog
                Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
                exit.setTitle("Exit");
                exit.setHeaderText(null);
                exit.setGraphic(null);
                exit.setContentText("Are you sure you want to exit?");

                Optional<ButtonType> result = exit.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
    }
}