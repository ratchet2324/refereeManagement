package nefra.jfx;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nefra.exceptions.DelLog;
import nefra.jfx.misc.SettingsGridPane;
import nefra.misc.Debug;
import nefra.jfx.misc.BorderTitlePane;

public class SettingsGUI {
    public void initGUI(Stage stage) {
        checkAndLoadFont();
        Stage settings = new Stage();

        GridPane centre = new GridPane();
        centre.setHgap(10);
        centre.setVgap(10);
        centre.setMinHeight(625);
        centre.setMinWidth(580);
        centre.setPadding(new Insets(0, 10 ,0 ,10));
        CheckBox debugMode = new CheckBox("Debug Mode");

        debugMode.setStyle("-fx-font-size: 12;");
        debugMode.setSelected(Debug.debugMode);

        debugMode.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue)
            {
                Debug.setDebugMode(true);
                Debug.debugInfo();
                stage.setTitle(stage.getTitle() + " **DEBUG**");
            }
            else
            {
                Debug.setDebugMode(false);
                stage.setTitle("New England Football Referees Association");
            }
        }));

        Button showAdvancedSettings = new Button("Show Advanced Settings");
        showAdvancedSettings.setStyle("-fx-font-size: 12;");

        SettingsGridPane topLeftGrid = new SettingsGridPane();
        SettingsGridPane topRightGrid = new SettingsGridPane();
        SettingsGridPane bottomLeftGrid = new SettingsGridPane();
        SettingsGridPane bottomRightGrid = new SettingsGridPane();
        BorderTitlePane topLeft = new BorderTitlePane(topLeftGrid, (centre.getMinWidth() / 2) - 10, (centre.getMinHeight() / 2) - 10);
        BorderTitlePane topRight = new BorderTitlePane(topRightGrid, (centre.getMinWidth() / 2) - 10, (centre.getMinHeight() / 2) - 10);
        BorderTitlePane bottomLeft = new BorderTitlePane(bottomLeftGrid, (centre.getMinWidth() / 2) - 10, (centre.getMinHeight() / 2) - 10);
        BorderTitlePane bottomRight = new BorderTitlePane("Advanced Settings", bottomRightGrid, (centre.getMinWidth() / 2) - 10, (centre.getMinHeight() / 2) - 10);
        bottomRight.setVisible(false);
        showAdvancedSettings.setOnAction(e -> {
            bottomRight.setVisible(true);
            showAdvancedSettings.setVisible(false);
        });
        Button hideAdvancedSettings = new Button("Hide Advanced Settings");
        hideAdvancedSettings.setStyle("-fx-font-size: 12;");
        hideAdvancedSettings.setOnAction(e -> {
            bottomRight.setVisible(false);
            showAdvancedSettings.setVisible(true);
        });

        bottomRightGrid.add(debugMode, 0,0);
        bottomRightGrid.add(hideAdvancedSettings, 0,2);

        centre.add(topLeft,0, 1);
        centre.add(topRight,1, 1);
        centre.add(bottomLeft,0, 2);
        centre.add(bottomRight,1, 2);
        centre.add(showAdvancedSettings,1, 2);

        ScrollPane centreScroll = new ScrollPane(centre);
        centreScroll.setVmax(625);
        centreScroll.setHmax(590);
        centreScroll.setStyle("-fx-background-color: transparent;");
        centreScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        centreScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


        Button ok = new Button("OK");
        ok.setOnAction(e -> close(e, settings));
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> close(e, settings));
        HBox bottom = new HBox();
        bottom.getChildren().addAll(ok, cancel);
        bottom.setPadding(new Insets(10));
        bottom.setSpacing(25);
        bottom.setAlignment(Pos.CENTER);
        bottom.setMaxHeight(100);
        bottom.setMaxWidth(600);
        for(Node b : bottom.getChildren()) b.setStyle("-fx-font-size: 14");

        BorderPane settingsGUI = new BorderPane(centreScroll, null,null, bottom, null);
        settingsGUI.setPrefSize(600, 768);

        settings.setScene(new Scene(settingsGUI));
        settings.setMaxHeight(768);
        settings.setMaxWidth(600);
        settings.setMinHeight(768);
        settings.setMinWidth(600);
        settings.setResizable(false);
        settings.setTitle("Settings");
        settings.show();

        settings.setOnCloseRequest(e -> close(e, settings));
    }

    private void checkAndLoadFont()
    {
        DelLog.getInstance().Log("\n\nSEARCHING FONTS!\n\n");
        int found = 0;
        for(String s : Font.getFamilies())
        {
            if (s.contains("Roboto")) {
                found = 1;
            }
        }
        if (found == 1) {Font.font("Roboto"); DelLog.getInstance().Log("\n\nFOUND ROBOTO!\n\n");}
        else
        {
            DelLog.getInstance().Log("\n\nFONT NOT FOUND!\nLOADING FONTS NOW!\n\n");

            Font f1 = Font.loadFont(getClass().getResourceAsStream("/nefra/jfx/misc/Roboto-Regular.ttf"), 12);
            Font f2 = Font.loadFont(getClass().getResourceAsStream("/nefra/jfx/misc/Roboto-Italic.ttf"), 12);
            Font f3 = Font.loadFont(getClass().getResourceAsStream("/nefra/jfx/misc/Roboto-Bold.ttf"), 12);
            DelLog.getInstance().Log(f1.toString());
            DelLog.getInstance().Log(f2.toString());
            DelLog.getInstance().Log(f3.toString());
        }
        for(String t : Font.getFamilies())
        {
            if (t.contains("Roboto"))
                DelLog.getInstance().Log("\n\nFOUND ROBOTO!\n\n");
        }
    }

    private void close(Event e, Stage stage)
    {
        e.consume();
        CommonGUI.singleSetting = false;
        stage.hide();
        stage.close();
    }
}
