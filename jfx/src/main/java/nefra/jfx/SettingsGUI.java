package nefra.jfx;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SettingsGUI {
    public BorderPane initGUI() {
        //Top
        MenuBar menu = CommonGUI.getInstance().loadMenu();

        //Bottom
        Button back = new Button("Back");
        back.setOnAction(e -> CommonGUI.getInstance().back(e));
        HBox bottom = new HBox();
        bottom.getChildren().addAll(back);

        BorderPane settingsGUI = new BorderPane(null, menu, null, bottom, null);
        settingsGUI.setPrefSize(600, 768);
        CommonGUI.panes.add(settingsGUI);
        return settingsGUI;
    }
}
